<%-- 
    Document   : imageupload
    Created on : Oct 14, 2011, 4:46:59 PM
    Author     : abdulnizam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page  import="hangpeer.NewMain,org.json.simple.*" %>
<% NewMain obj=(NewMain)session.getAttribute("object");
   JSONObject json=new JSONObject();
   if(obj.uploadImage(request, session))
             {
       json.put("status", "success");
   }
   else
             {
       json.put("status", "failiure please try again");
   }
   out.print(json);
%>