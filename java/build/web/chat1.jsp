<%-- 
    Document   : caht1
    Created on : Oct 13, 2011, 7:21:54 AM
    Author     : abdulnizam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page  import="java.util.*,java.sql.*,chat.chat" %>
<%  chat chat=new chat(session);
    if(session.getAttribute("chatHistory")==null){
        session.setAttribute("chatHistory", new HashMap());
    }
    if(session.getAttribute("openChatBoxes")==null){
        session.setAttribute("openChatBoxes", new HashMap());
    }
    
    if(request.getParameter("action").equals("chatheartbeat")){ out.print(chat.chatHeartbeat());}
    if(request.getParameter("action").equals("sendchat")){ chat.sendChat(request.getParameter("message"), request.getParameter("to")); }
    if(request.getParameter("action").equals("closechat")){ chat.closeChat(request.getParameter("chatbox")); }
    if(request.getParameter("action").equals("startchatsession")){ out.print(chat.startChatSession()); }
    

%>
