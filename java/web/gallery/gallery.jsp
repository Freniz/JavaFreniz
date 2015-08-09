<%-- 
    Document   : gallery
    Created on : Oct 27, 2011, 10:49:16 PM
    Author     : abdulnizam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="hangpeer.NewMain,java.util.* " %>
<%NewMain obj=(NewMain)session.getAttribute("object");
    if(request.getParameter("albumid")!=null && session.getAttribute("userid")!=null){
        HashMap images=obj.getImages(request.getParameter("albumid"));
        List keyset=new ArrayList(images.keySet());
        Collections.sort(keyset);
%>

<html>
	<head>
		<meta http-equiv="Content-type" content="text/html; charset=utf-8">
		<title>Galleriffic | Custom layout with external controls</title>
		<link rel="stylesheet" href="css/basic.css" type="text/css" />
		<link rel="stylesheet" href="css/galleriffic-5.css" type="text/css" />
		
		<!-- <link rel="stylesheet" href="css/white.css" type="text/css" /> -->
		<link rel="stylesheet" href="css/black.css" type="text/css" />
		
		<script type="text/javascript" src="js/jquery-1.3.2.js"></script>
        <script type="text/javascript" src="js/scroll.js"></script>
		<script type="text/javascript" src="js/jquery.history.js"></script>
		<script type="text/javascript" src="js/jquery.galleriffic.js"></script>
		<script type="text/javascript" src="/sn/js/ajax.js"></script>
                <script type="text/javascript" src="js/jquery.opacityrollover.js"></script>
		<!-- We only want the thunbnails to display when javascript is disabled -->
		<script type="text/javascript">
			document.write('<style>.noscript { display: none; }</style>');
		</script>
	</head>
	<body>
		<div id="page">
			<div id="container">
				
				

				<!-- Start Advanced Gallery Html Containers -->				
				<div class="navigation-container">
					<div id="thumbs" class="navigation">
						<a class="pageLink prev" style="visibility: hidden;" href="#" title="Previous Page"></a>
					
						<ul class="thumbs noscript">
							
						  <%for(int i=keyset.size()-1;i>=0;i--){
                                                        HashMap image=(HashMap)images.get(keyset.get(i));
                                                    %>
							
						<li> 
                                                    <a class="thumb"  name="<%out.print(keyset.get(i)); %>" href="http://localhost:8080/sn/images/<%out.print(image.get("url")); %>" title="<%out.print(image.get("title")); %>">
                                                        <img src="http://localhost:8080/sn/images/75/75_<%out.print(image.get("url")); %>" height="75" width="75" alt="<%out.print(image.get("url")); %>" />
								
                                                    </a>
								<div class="caption">
									<div style="width:220px; height:50px; border:solid 1px; border-color:#999">
                                                                        <%out.print(image.get("title")); %>
                    </div>
                    
                    <div style="width:220px; height:100px; border:solid 1px; border-color:#999">
                        <input id="<%out.print(keyset.get(i));%>_pinuser" value="" onsubmit="addpin('<%out.print(keyset.get(i));%>')"/><input type="button" value="add pin" onclick="addpin('<%out.print(keyset.get(i));%>')"/>
                        <textarea id="<%out.print(keyset.get(i));%>_pinpeople" ></textarea>
                        <input type="button" value="pin this pic" onclick="pinpeople('<%out.print(keyset.get(i));%>')"/>
                    </div>
                    
                    <div style="width:220px; height:100px; border:solid 1px; border-color:#999">
                        <%out.print(image.get("pinnedpeople")); %>
                    </div>
                     <div style="width:220px; height:250px; border:solid 1px; border-color:#999">
                         <%out.print(image.get("description")); %>
                    </div>
								</div>
                               <div class="comment">
         <div style="height:30px; float:left; width:600px; ">
             <textarea id="<%out.print(keyset.get(i)); %>_comment" style="width:530px; height:30px"></textarea>
<input type="button" style="float:right; margin-top:5px;" value="update" onclick="doimagecomment('<%out.print(keyset.get(i));%>')"/>
</div>                      
								 <div id="scrollholder" style="height:500px; width:600px; overflow:auto;z-index: 1;top: 30px; position:absolute">
        <div id="scroll" class="scroll">
         
<%
    HashMap imgcomments=obj.getImageComments((String)keyset.get(i));
    List commentkeyset=new ArrayList(imgcomments.keySet());
    Collections.sort(commentkeyset);
    for(int j=commentkeyset.size()-1;j>=0;j--){
        HashMap imgcomment=(HashMap)imgcomments.get(commentkeyset.get(j));
%>
<div style="width:560px; float:left; margin-top:2px; background-color: #CCC; padding:10px; ">
<div style="width:560px; height:20px; background-color:#CCC;  float:left ">
    <a onclick="deleteimagecomment('<%out.print(commentkeyset.get(j)); %>')" >delete</a>
</div>
<div style="width:525px; background-color:#CCC;  float:left ">

    <%out.print(imgcomment.get("comment")); %>
</div>
<div style="width:32px; height:32px; border:solid 2px; float:right">
    <img src="/sn/images/<%out.print(obj.getImage((String)((HashMap)((HashMap)application.getAttribute("users")).get(imgcomment.get("userid"))).get("propic")).get("url")); %>" height="32" width="32" />
</div>

</div>
<%}%>
        </div>
 <script type="text/javascript">
    <!--
        ScrollLoad ("scrollholder", "scroll", true);
    //-->
    </script>

  

</div>
	
								</div>
							</li>
							  <%}%>
	
						</ul>
						<a class="pageLink next" style="visibility: hidden;" href="#" title="Next Page"></a>
					</div>
				</div>
				
                
                <div class="content">
					<div class="slideshow-container">
						<div id="controls" class="controls"></div>
						<div id="loading" class="loader"></div>
						<div id="slideshow" class="slideshow"></div>
					</div>
					<div id="caption" class="caption-container">
                    				<div class="photo-index"></div>
					</div>
                  
                  
                  
</div>

      <div id="comment" class="comment-container">
                 


                  </div>              
                   
                    </div> 
                    
				</div>
                
				<!-- End Gallery Html Containers -->
				
                <div style="clear: both;"></div>
		
		<script type="text/javascript">
			jQuery(document).ready(function($) {
				// We only want these styles applied when javascript is enabled
				$('div.content').css('display', 'block');

				// Initially set opacity on thumbs and add
				// additional styling for hover effect on thumbs
				var onMouseOutOpacity = 0.67;
				$('#thumbs ul.thumbs li, div.navigation a.pageLink').opacityrollover({
					mouseOutOpacity:   onMouseOutOpacity,
					mouseOverOpacity:  1.0,
					fadeSpeed:         'fast',
					exemptionSelector: '.selected'
				});
				
				// Initialize Advanced Galleriffic Gallery
				var gallery = $('#thumbs').galleriffic({
					delay:                     2500,
					numThumbs:                 10,
					preloadAhead:              10,
					enableTopPager:            false,
					enableBottomPager:         false,
					imageContainerSel:         '#slideshow',
					controlsContainerSel:      '#controls',
					captionContainerSel:       '#caption',
					commentContainerSel:       '#comment',
					loadingContainerSel:       '#loading',
					renderSSControls:          true,
					renderNavControls:         true,
					playLinkText:              'Play Slideshow',
					pauseLinkText:             'Pause Slideshow',
					prevLinkText:              '&lsaquo; Previous Photo',
					nextLinkText:              'Next Photo &rsaquo;',
					nextPageLinkText:          'Next &rsaquo;',
					prevPageLinkText:          '&lsaquo; Prev',
					enableHistory:             true,
					autoStart:                 false,
					syncTransitions:           true,
					defaultTransitionDuration: 900,
					onSlideChange:             function(prevIndex, nextIndex) {
						// 'this' refers to the gallery, which is an extension of $('#thumbs')
						this.find('ul.thumbs').children()
							.eq(prevIndex).fadeTo('fast', onMouseOutOpacity).end()
							.eq(nextIndex).fadeTo('fast', 1.0);

						// Update the photo index display
						this.$captionContainer.find('div.photo-index')
							.html('Photo '+ (nextIndex+1) +' of '+ this.data.length);
					},
					onPageTransitionOut:       function(callback) {
						this.fadeTo('fast', 0.0, callback);
					},
					onPageTransitionIn:        function() {
						var prevPageLink = this.find('a.prev').css('visibility', 'hidden');
						var nextPageLink = this.find('a.next').css('visibility', 'hidden');
						
						// Show appropriate next / prev page links
						if (this.displayedPage > 0)
							prevPageLink.css('visibility', 'visible');

						var lastPage = this.getNumPages() - 1;
						if (this.displayedPage < lastPage)
							nextPageLink.css('visibility', 'visible');

						this.fadeTo('fast', 1.0);
					}
				});

				/**************** Event handlers for custom next / prev page links **********************/

				gallery.find('a.prev').click(function(e) {
					gallery.previousPage();
					e.preventDefault();
				});

				gallery.find('a.next').click(function(e) {
					gallery.nextPage();
					e.preventDefault();
				});

				/****************************************************************************************/

				/**** Functions to support integration of galleriffic with the jquery.history plugin ****/

				// PageLoad function
				// This function is called when:
				// 1. after calling $.historyInit();
				// 2. after calling $.historyLoad();
				// 3. after pushing "Go Back" button of a browser
				function pageload(hash) {
					// alert("pageload: " + hash);
					// hash doesn't contain the first # character.
					if(hash) {
						$.galleriffic.gotoImage(hash);
					} else {
						gallery.gotoIndex(0);
					}
				}

				// Initialize history plugin.
				// The callback is called at once by present location.hash. 
				$.historyInit(pageload, "advanced.html");

				// set onlick event for buttons using the jQuery 1.3 live method
				$("a[rel='history']").live('click', function(e) {
					if (e.button != 0) return true;

					var hash = this.href;
					hash = hash.replace(/^.*#/, '');

					// moves to a new page. 
					// pageload is called at once. 
					// hash don't contain "#", "?"
					$.historyLoad(hash);

					return false;
				});

				/****************************************************************************************/
			});
		</script>
	</body>
</html>
<%}%>