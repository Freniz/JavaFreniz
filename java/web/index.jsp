<%-- 
    Document   : index
    Created on : Aug 19, 2011, 3:03:37 PM
    Author     : abdulnizam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form name="login" method="get" action="authentication.jsp">
            <input type="text" name="userid" size="100"/>
            <input type="password" name="pass" size="100"/>
            <input type="submit" value="submit"/>
        </form>
        
        <% out.print(session.getId()+"<br>"+session.getAttribute("userid")+"<br>" +session.getCreationTime()+"<br>"+session.getMaxInactiveInterval()+"<br>"+application.getAttribute("onlineusers")+"<br>"+session.getAttribute("object")); %>

        
    </body>

</html>
