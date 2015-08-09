> '\cP',
     "\cQ" => '\cQ',
     "\cR" => '\cR',
     "\cS" => '\cS',
     "\cT" => '\cT',
     "\cU" => '\cU',
     "\cV" => '\cV',
     "\cW" => '\cW',
     "\cX" => '\cX',
     "\cY" => '\cY',
     "\cZ" => '\cZ',
     "\c[" => '\c[',	# unused
     "\c\\" => '\c\\',	# unused
     "\c]" => '\c]',	# unused
     "\c_" => '\c_',	# unused
    );

# character escapes, but not delimiters that might need to be escaped
sub escape_str { # ASCII, UTF8
    my($str) = @_;
    $str =~ s/(.)/ord($1) > 255 ? sprintf("\\x{%x}", ord($1)) : $1/eg;
    $str =~ s/\a/\\a/g;
#    $str =~ s/\cH/\\b/g; # \b means something different in a regex
    $str =~ s/\t/\\t/g;
    $str =~ s/\n/\\n/g;
    $str =~ s/\e/\\e/g;
    $str =~ s/\f/\\f/g;
    $str =~ s/\r/\\r/g;
    $str =~ s/([\cA-\cZ])/$unctrl{$1}/ge;
    $str =~ s/([[:^print:]])/sprintf("\\%03o", ord($1))/ge;
    return $str;
}

# For regexe