<%--
    Document   : profile
    Created on : Oct 5, 2011, 1:27:05 AM
    Author     : abdulnizam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.sql.*,java.util.*,hangpeer.*" %>



<% NewMain obj=(NewMain) session.getAttribute("object");%>


<body style="float:left">


<ul class=" roundbuttons profilerdwidth">
    <li ><a href="#" onclick="sendfrndreq('<%out.print(request.getParameter("userid"));%>')">Add me </a></li>
<li ><a href="#" onclick="showprofile('faizunisa')">Message</a></li>
<li ><a href="#">Music</a></li>
<li ><a href="#">Slambook</a></li>
<li ><a href="#">Withdraw</a></li>

</ul>

<div style="float:left; width:1024px">
<div style="width:200px; height:600px;float:left;   ">
    <% HashMap ud=new HashMap();
        HashMap fv=new HashMap();
        List votes=new ArrayList();

    if(session.getAttribute("userid")!=null)
        {
        ud=obj.getUserDetails(request.getParameter("userid"));
        fv=obj.getFriendsAndVote(request.getParameter("userid"));
        votes=(List)fv.get("vote");
        }
        else
            out.print("javascript:alert('please login to <a href=\"/index.jp\">login page</a>')");

%>
<div style=" height: 30px; width: 200px; font-size: 24px"><%out.print(ud.get("fname"));%>  <%out.print(ud.get("lname"));%>
    <div style="height:10px; font-size: 9px; float: right"><input type="hidden" value="<%out.print(votes);%>" name="uservotes" id="uservotes"/> <a href="#"><%out.print(votes.size());%> user(s) voted</a></div>
</div>
 <div style="width:200px">
     B'day:<%out.print((java.util.Date)ud.get("dob"));%><br />
     Sex:<%out.print((String)ud.get("sex"));%><br />
     Status:<%ud.get("rstatus");%><br />
     Religion: <%out.print((String)ud.get("religion"));%><br />
     Language: <%out.print((String)ud.get("language"));%><br />
     Current City:<%out.print((String)ud.get("currentcity"));%><br />
     Hometown:<%out.print((String)ud.get("hometown"));%>
 </div>
 <div style="width:200px">
 <li class=" favorites">  Education & Occupation<div class="arrow"></div></li>
 <div style="width:200px">
 School:
 </div>
 <div style="width:200px">
 College:
 </div>
 <div style="width:200px">
 Worked In:
 </div>
 </div>

 <div style="width:200px">
 <li class="favorites" >Favourites <div class="arrow"></div> </li>

 <div style="width:200px">
 Books:
 </div>

 <div style="width:200px">
 Music:
 <div style="width:200px">
 Album:
 </div>
 <div style="width:200px">
 Songs:
 </div>
 </div>
 <div style="width:200px">
 Movies:
 </div>
 <div style="width:200px">
 Celebrities:
 </div>
 <div style="width:200px">
 Games:
 </div>
 <div style="width:200px">
 Sports:
 </div>
 <div style="width:200px">
 Others:
 </div>
 </div>



</div>


<div style="width:624px; height:100% ;float:left;">

<div style="width:624px; height:300px;    " >


<div  class="profilepic">
    <img src="images/<%out.print(ud.get("propic"));%>" height="200" width="150"/>
</div>


<div class="secondaryprofilepic" style="">
<div class="innersecondaryprofilepic_1" style="">
</div>
<div class="innersecondaryprofilepic_2" style="">
</div>
</div>

<div id="pinnedpic" style="">
</div>


<div id="stature" style="">

    <%out.print(obj.getStature(request.getParameter("userid")));%>
</div>
</div>




<div id="streams6" style="width:624px; height:100%;">

</div>
<div id="morestreams" style="width:624px; height:100%;">

</div>
<center><a onclick="reqmorestreams()">more streams</a></center>
</div>



<div style="height:600px; width:200px;  float:left; margin-top:50px;"  >



    <%List frnd=(List)obj.getFriendsAndVote(request.getParameter("userid")).get("friendlist");
    for(int i=0;i<frnd.size();i++){
%>

<div style="width:200px; height:50px; float:left; ">
<div class="innerpic" >
<img src="preview.jpg" height="50" width="50" />
</div>
<div class="innername" >
    <a href="#showprofile?userid=<%out.print(frnd.get(i));%>">  <%out.print(frnd.get(i));%></a>
</div>
<div class="innername" >
MSAJCE
</div>
</div>
<%}%>

</div>

</div>








</body>
</html>

