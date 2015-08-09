<%-- 
    Document   : newjsp
    Created on : Sep 29, 2011, 5:27:09 PM
    Author     : abdulnizam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@page  import="hangpeer.NewMain,java.util.*,org.apache.commons.fileupload.*;" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <% NewMain obj= (NewMain)session.getAttribute("object");
        obj.uploadImage(request,session);
         out.print(session.getAttribute("object"));
        //response.sendRedirect("profile.jsp");
        %>
       
    </body>
</html>
