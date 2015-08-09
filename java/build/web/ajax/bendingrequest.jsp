<?xml version="1.0" encoding="UTF-8" ?>
<%@page contentType="text/xml" pageEncoding="UTF-8"%>
<%@page  import="hangpeer.NewMain,java.util.*" %>
<% NewMain obj=(NewMain) session.getAttribute("object");
   HashMap bendingrequests=obj.bendingRequest((String)session.getAttribute("userid"));
   List incomingrequest=(List)bendingrequests.get("incomingrequest");
   List sentrequest=(List)bendingrequests.get("sentrequest");
   
%>