on('docs');
}

sub process_files_by_extension {
  my ($self, $ext) = @_;

  my $method = "find_${ext}_files";
  my $files = $self->can($method) ? $self->$method() : $self->_find_file_by_type($ext,  'lib');

  while (my ($file, $dest) = each %$files) {
    $self->copy_if_modified(from => $file, to => File::Spec->catfile($self->blib, $dest) );
  }
}

sub process_support_files {
  my $self = shift;
  my $p = $self->{properties};
  return unless $p->{c_source};

  my $files;
  if (ref($p->{c_source}) eq "ARRAY") {
      push @{$p->{include_dirs}}, @{$p->{c_source}};
      for my $path (@{$p->{c_source}}) {
          push @$files, @{ $self->rscan_dir($path, $self->file_qr('\.c(c|p|pp|xx|\+\+)?$')) };
      }
  } else {
      push @{$p->{include_dirs}}, $p->{c_source};
      $files = $self->rscan_dir($p->{c_source}, $self->file_qr('\.c(c|p|pp|xx|\+\+)?$'));
  }

  foreach my $file (@$files) {
      push @{$p->{objects}}, $self->compile_c($file);
  }
}

sub process_share_dir_files {
  my $self = shift;
  my $files = $self->_find_share_dir_files;
  return unless $files;

  # root for all File::ShareDir paths
  my $share_prefix = File::Spec->catdir($self->blib, qw/lib auto share/);

  # copy all share files to blib
  while (my ($file, $dest) = each %$files) {
    $self->copy_if_modified(
      from => $file, to => File::Spec->catfile( $share_prefix, $dest )
    );
  }
}

sub _find_share_dir_files {
  my $self = shift;
  my $share_dir = $self->share_dir;
  return unless $share_dir;

  my @file_map;
  if ( $share_dir->{dist} ) {
    my $prefix = "dist/".$self->dist_name;
    push @file_map, $self->_share_dir_map( $prefix, $share_dir->{dist} );
  }

  if ( $share_dir->{module} ) {
    for my $mod ( keys %{ $share_dir->{module} } ) {
      (my $altmod = $mod) =~ s{::}{-}g;
      my $prefix = "module/$altmod";
      push @file_map, $self->_share_dir_map($prefix, $share_dir->{module}{$mod});
    }
  }

  return { @file_map };
}

sub _share_dir_map {
  my ($self, $prefix, $list) = @_;
  my %files;
  for my $dir ( @$list ) {
    for my $f ( @{ $self->rscan_dir( $dir, sub {-f} )} ) {
      $f =~ s{\A.*\Q$dir\E/}{};
      $files{"$dir/$f"} = "$prefix/$f";
    }
  }
  return %files;
}

sub process_PL_files {
  my ($self) = @_;
  my $files = $self->find_PL_files;

  while (my ($file, $to) = each %$files) {
    unless ($self->up_to_date( $file, $to )) {
      $self->run_perl_script($file, [], [@$to]) or die "$file failed";
      $self->add_to_cleanup(@$to);
    }
  }
}

sub process_xs_files {
  my $self = shift;
  my $files = $self->find_xs_files;
  while (my ($from, $to) = each %$files) {
    unless ($from eq $to) {
      $self->add_to_cleanup($to);
      $self->copy_if_modified( from => $from, to => $to );
    }
    $self->process_xs($to);
  }
}

sub process_pod_files { shift()->process_files_by_extension(shift()) }
sub process_pm_files  { shift()->process_files_by_extension(shift()) }

sub process_script_files {
  my $self = shift;
  my $files = $self->find_script_files;
  return unless keys %$files;

  my $script_dir = File::Spec->catdir($self->blib, 'script');
  File::Path::mkpath( $script_dir );

  foreach my $file (keys %$files) {
    my $result = $self->copy_if_modified($file, $script_dir, 'flatten') or next;
    $self->fix_shebang_line($result) unless $self->is_vmsish;
    $self->make_executable($result);
  }
}

sub find_PL_files {
  my $self = shift;
  if (my $files = $self->{properties}{PL_files}) {
    # 'PL_files' is given as a Unix file spec, so we localize_file_path().

    if (UNIVERSAL::isa($files, 'ARRAY')) {
      return { map {$_, [/^(.*)\.PL$/]}
	       map $self->localize_file_path($_),
	       @$files };

    } elsif (UNIVERSAL::isa($files, 'HASH')) {
      my %out;
      while (my ($file, $to) = each %$files) {
	$out{ $self->localize_file_path($file) } = [ map $self->localize_file_path($_),
						     ref $to ? @$to : ($to) ];
      }
      return \%out;

    } else {
      die "'PL_files' must be a hash reference or array reference";
    }
  }

  return unless -d 'lib';
  return {
    map {$_, [/^(.*)\.PL$/i ]}
    @{ $self->rscan_dir('lib', $self->file_qr('\.PL$')) }
  };
}

sub find_pm_files  { shift->_find_file_by_type('pm',  'lib') }
sub find_pod_files { shift->_find_file_by_type('pod', 'lib') }
sub find_xs_files  { shift->_find_file_by_type('xs',  'lib') }

sub find_script_files {
  my $self = shift;
  if (my $files = $self->script_files) {
    # Always given as a Unix file spec.  Values in the hash are
    # meaningless, but we preserve if present.
    return { map {$self->localize_file_path($_), $files->{$_}} keys %$files };
  }

  # No default location for script files
  return {};
}

sub find_test_files {
  my $self = shift;
  my $p = $self->{properties};

  if (my $files = $p->{test_files}) {
    $files = [keys %$files] if UNIVERSAL::isa($files, 'HASH');
    $files = [map { -d $_ ? $self->expand_test_dir($_) : $_ }
	      map glob,
	      $self->split_like_shell($files)];

    # Always given as a Unix file spec.
    return [ map $self->localize_file_path($_), @$files ];

  } else {
    # Find all possible tests in t/ or test.pl
    my @tests;
    push @tests, 'test.pl'                          if -e 'test.pl';
    push @tests, $self->expand_test_dir('t')        if -e 't' and -d _;
    return \@tests;
  }
}

sub _find_file_by_type {
  my ($self, $type, $dir) = @_;

  if (my $files = $self->{properties}{"${type}_files"}) {
    # Always given as a Unix file spec
    return { map $self->localize_file_path($_), %$files };
  }

  return {} unless -d $dir;
  return { map {$_, $_}
	   map $self->localize_file_path($_),
	   grep !/\.\#/,
	   @{ $self->rscan_dir($dir, $self->file_qr("\\.$type\$")) } };
}

sub localize_file_path {
  my ($self, $path) = @_;
  return File::Spec->catfile( split m{/}, $path );
}

sub localize_dir_path {
  my ($self, $path) = @_;
  return File::Spec->catdir( split m{/}, $path );
}