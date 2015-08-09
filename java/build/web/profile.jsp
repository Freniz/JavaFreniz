<%-- 
    Document   : profile
    Created on : Aug 19, 2011, 3:18:48 PM
    Author     : abdulnizam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.sql.*,java.util.*,hangpeer.*" %>

<% NewMain obj=(NewMain)session.getAttribute("object");
HashMap ud=obj.getUserDetails((String)session.getAttribute("userid"));
List keys= new ArrayList(ud.keySet());
for(int i=0;i<keys.size();i++)
    out.print(keys.get(i)+":"+ud.get(keys.get(i))+"<br>");
out.print("<br><br> friends list <br><br>");
List frdz=(List)session.getAttribute("friendlist");
if(frdz!=null)
for(int i=0;i<frdz.size();i++)
    out.print(frdz.get(i)+"<br>");
else
    out.print("<br> no friends <br>");

for(int i=0;i<5000;i++)
  {
  obj.doPost((String)session.getAttribute("userid"), "meeran", "hiiiiiii");
  obj.sleep(1000);
  }

//obj.doComment("1", "meeran", "hello");
//obj.deleteComment("1");
//obj.deletePost("2");
//obj.createAlbum((String)session.getAttribute("userid"), "faizu");
//obj.sentMessage((String)session.getAttribute("userid"),"meeran", "hiiii");
HashMap up= obj.getUsersPost("nizam");
List postkeys=new ArrayList(up.keySet());
for(int i=0;i<postkeys.size();i++)
    out.print(up.get(postkeys.get(i))+"<br>");

HashMap uc= obj.getComments("1");
//List commentkeys=new ArrayList(uc.keySet());
//for(int i=0;i<commentkeys.size();i++)
  //  out.print(uc.get(commentkeys.get(i))+"<br>");
out.print(uc+"<br>");

out.print(obj.getUnreadMessageCount((String)session.getAttribute("userid"))+"<br>");
out.print(obj.getSentMessages((String)session.getAttribute("userid")));

HashMap vote=obj.getPost((String)postkeys.get(0));
List votelist=(List)vote.get("vote");
//votelist.add(session.getAttribute("userid"));
//obj.voteStatus((String)postkeys.get(0), votelist);



%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript">
            var formdata=document.
        </script>
    </head>
    <body>

        <form enctype="multipart/form-data" action="newjsp.jsp" method="post">
            <input type="hidden" value="<%=(String)session.getAttribute("userid")%>" name="userid"/>
            <input type="hidden" value="1" name="album" />
            <input type="file" name="file" />
            <input type="submit" value="submit"/>
        </form>
        

    </body>
</html>
