<%-- 
    Document   : test
    Created on : Sep 29, 2011, 2:11:06 PM
    Author     : abdulnizam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@page  import="hangpeer.NewMain" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
        NewMain nm=(NewMain) session.getAttribute("object");
        java.util.Date date= new java.util.Date("5/11/1993");
        nm.createUser("faizunisa", "mohamedmeeran", "Faizunisa", "M", date,"female", "faizunisa@gmail.com");
        %>

    </body>
</html>
