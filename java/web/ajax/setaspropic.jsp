
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="hangpeer.NewMain,java.util.*" %>
<% NewMain obj=(NewMain)session.getAttribute("object");
   org.json.simple.JSONObject json=new org.json.simple.JSONObject();
   if(request.getParameter("imageid")!=null &&request.getParameter("x")!=null &&request.getParameter("y")!=null &&request.getParameter("width")!=null &&request.getParameter("height")!=null && session.getAttribute("userid")!=null){
       if(!((String)((HashMap)session.getAttribute("userdetails")).get("propicalbum")).equals((String)obj.getImage(request.getParameter("imageid")).get("albumid"))){
           if(obj.setAsProPic(request.getRealPath("/")+"images/",(String)session.getAttribute("userid"),(String)((java.util.HashMap)session.getAttribute("userdetails")).get("propicalbum"),request.getParameter("imageid"), Integer.parseInt(request.getParameter("x")), Integer.parseInt(request.getParameter("y")), Integer.parseInt(request.getParameter("width")), Integer.parseInt(request.getParameter("height"))))
               json.put("status", "your propic updated successfully");
           else
               json.put("status", "error while updating your propic");
       }
       else{
           obj.updatepropic((String)session.getAttribute("userid"), request.getParameter("imageid"));
           json.put("status", "your propic updated");
         }
   }
   else
       json.put("status", "please provide valid informations");
   out.print(json);

%>