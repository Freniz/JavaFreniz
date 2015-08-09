
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript">
            var request=new createXMLHttpRequest();
    
    function createXMLHttpRequest(){
  // See http://en.wikipedia.org/wiki/XMLHttpRequest
  // Provide the XMLHttpRequest class for IE 5.x-6.x:
  if( typeof XMLHttpRequest == "undefined" ) XMLHttpRequest = function() {
    try {return new ActiveXObject("Msxml2.XMLHTTP.6.0")} catch(e) {}
    try {return new ActiveXObject("Msxml2.XMLHTTP.3.0")} catch(e) {}
    try {return new ActiveXObject("Msxml2.XMLHTTP")} catch(e) {}
    try {return new ActiveXObject("Microsoft.XMLHTTP")} catch(e) {}
    throw new Error( "This browser does not support XMLHttpRequest." )
  };
  return new XMLHttpRequest();
}
            window.onload=function(){
               var i=setInterval(function(){
             request.onreadystatechange=redesign;
             request.open("get","memorycheck.jsp",true);
             request.send(null);
            },1000);}
            function redesign()
            {
                if(request.readyState==4 && request.status==200)
                    {
                        document.getElementById("1").innerHTML=request.responseText;
                    }
            }
            
        </script>
    </head>
    <body>
        <div id="1">
            
        </div>
    </body>
</html>
