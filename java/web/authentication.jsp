<%-- 
    Document   : authentication
    Created on : Aug 19, 2011, 3:56:29 PM
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
       <%@page import="hangpeer.*,java.util.*" %>
<%
    NewMain obj=(NewMain) session.getAttribute("object");
    if(obj.validate(request.getParameter("userid"),request.getParameter("pass")))
    {
    session.setAttribute("userid",request.getParameter("userid"));
    HashMap friends_vote=(HashMap)obj.getFriendsAndVote(request.getParameter("userid"));
    session.setAttribute("friendlist",friends_vote.get("friendlist"));
      session.setMaxInactiveInterval(-1);
        response.sendRedirect("profile.jsp");

    }
else
    response.sendRedirect("error.jsp");
%>
    </body>
</html>
