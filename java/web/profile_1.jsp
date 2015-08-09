<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page  import="java.util.*,hangpeer.NewMain" %>
<%  NewMain obj=(NewMain)session.getAttribute("object");
%>
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




<div  style=" position:absolute; width:250px; right:0px; top:40px">

<ul class="roundbuttons profilerdwidth" style=" right:5px; top: 25px;">
    <% List frnds=(List)session.getAttribute("friends");
if(frnds.contains(request.getParameter("userid"))){
%>
<li ><a  onclick="removefrnd('<%out.print(request.getParameter("userid"));%>')">Remove </a></li>
<%}else if(((List)session.getAttribute("sentrequest")).contains(request.getParameter("userid"))) {%>
<li ><a  onclick="cancelfrndreq('<%out.print(request.getParameter("userid"));%>')">Cancel Req </a></li>
<%}else if(((List)session.getAttribute("bendingrequest")).contains(request.getParameter("userid"))){%>
<li ><a  onclick="respondfrnd('<%out.print(request.getParameter("userid"));%>')">Respond to Req </a></li>
<%}else{%>
<li ><a  onclick="sendfrndreq('<%out.print(request.getParameter("userid"));%>')">Invite </a></li>
<%}%>
<li ><a  onclick="document.getElementById('light3').style.display='block';document.getElementById('fade3').style.display='block';" >Message</a></li>
<li ><a  >Music</a></li>
<li ><a  onclick="slambook('<%out.print(request.getParameter("userid"));%>')">Slambook</a></li>
<%List vote=(List)obj.getFriendsAndVote(request.getParameter("userid")).get("vote");
if(vote.contains(session.getAttribute("userid"))){
%>
<li ><a onclick="withdrawuservote('<%out.print(request.getParameter("userid"));%>')">Withdraw</a></li>
<%}else{%>
<li ><a  onclick="voteuser('<%out.print(request.getParameter("userid"));%>')">Vote</a></li>
<%}%>
</ul>
</div>



<div id="center" style="float:left; width:1024px; border:solid 1px">
 <div style="width:200px; height:600px;float:left;   ">
     <div style="width:200px; height: 30px; float: left"><%out.print(ud.get("fname")+" "+ud.get("lname"));%></div>
     <div style="width:200px;height:16px; text-align: right"><%out.print(((List)((HashMap)((HashMap)application.getAttribute("users")).get(request.getParameter("userid"))).get("votes")).size());%></div>
     <div class="profiledetailsfontcolor" style="width:200px">
  B'day:<%out.print((java.util.Date)ud.get("dob"));%><br />
     Sex:<%out.print((String)ud.get("sex"));%><br />
     Status:<%out.print((String)ud.get("rstatus"));%><br />
     Religion: <%out.print((String)ud.get("religion"));%><br />
     Current City:<%out.print((String)ud.get("currentcity"));%><br />
     Hometown:<%out.print((String)ud.get("hometown"));%><br />
      Language: <br />
     <%List languages=(List)ud.get("language");
  for(int i=0;i<languages.size();i++)
           {
      if(i<3){
          out.print("<a href='pages.jsp?pageid="+languages.get(i)+"'>"+languages.get(i)+"</a></br>");
           }
      }
if(languages.size()>2)
    out.print("<a href='#'>seemore</a>");
%>
 </div>
 <div style="width:200px">
 <li class=" favorites">  Education & Occupation<div class="arrow"></div></li>
 <div class="profiledetailsfontcolor" style="width:200px">
 school:<br>
 <%List schools=(List)ud.get("school");
  for(int i=0;i<schools.size();i++)
           {
      if(i<3){
          out.print("<a href='pages.jsp?pageid="+schools.get(i)+"'>"+schools.get(i)+"</a></br>");
           }
      }
if(schools.size()>2)
    out.print("<a href='#'>seemore</a>");
%>
 </div>
 <div class="profiledetailsfontcolor" style="width:200px">
 college:<br>
  <% List colleges=(List)ud.get("college");
  for(int i=0;i<colleges.size();i++)
           {
      if(i<3){
          out.print("<a href='pages.jsp?pageid="+colleges.get(i)+"'>"+colleges.get(i)+"</a></br>");
           }
      }
if(colleges.size()>2)
    out.print("<a href='#'>seemore</a>");
%>
 </div>
 <div class="profiledetailsfontcolor" style="width:200px">
 Worked In:<br>
 <%out.print(ud.get("employer"));%>
 </div>
 </div>

 <div style="width:200px">
 <li class="favorites" >Favourites <div class="arrow"></div> </li>
 
 <div class="profiledetailsfontcolor" style="width:200px">
 Books:<br>
<%List books=(List)ud.get("books");
  for(int i=0;i<books.size();i++)
           {
      if(i<3){
          out.print("<a href='pages.jsp?pageid="+books.get(i)+"'>"+books.get(i)+"</a></br>");
           }
      }
if(books.size()>3)
    out.print("<a href='#'>seemore</a>");
%>
 </div>
 
 <div class="profiledetailsfontcolor" style="width:200px">
 Music:
 <div class="profiledetailsfontcolor" style="width:200px">
 Album:
 <%List musics=(List)ud.get("musics");
  for(int i=0;i<musics.size();i++)
           {
      if(i<3){
          out.print("<a href='pages.jsp?pageid="+musics.get(i)+"'>"+musics.get(i)+"</a></br>");
           }
      }
if(musics.size()>3)
    out.print("<a href='#'>seemore</a>");
%>
 </div>
 <div class="profiledetailsfontcolor" style="width:200px">
 Songs:
 <%List playlist=(List)ud.get("playlist");
  for(int i=0;i<playlist.size();i++)
           {
      if(i<3){
          out.print("<a href='pages.jsp?pageid="+playlist.get(i)+"'>"+playlist.get(i)+"</a></br>");
           }
      }
if(playlist.size()>3)
    out.print("<a href='#'>seemore</a>");
%>
 </div>
 </div>
 <div class="profiledetailsfontcolor" style="width:200px">
 Movies:
 <%List movies=(List)ud.get("movies");
  for(int i=0;i<movies.size();i++)
           {
      if(i<3){
          out.print("<a href='pages.jsp?pageid="+movies.get(i)+"'>"+movies.get(i)+"</a></br>");
           }
      }
if(movies.size()>3)
    out.print("<a href='#'>seemore</a>");
%>
 </div>
 <div class="profiledetailsfontcolor" style="width:200px">
 Celebrities:
 <%List celebrities=(List)ud.get("celebrities");
  for(int i=0;i<celebrities.size();i++)
           {
      if(i<3){
          out.print("<a href='pages.jsp?pageid="+celebrities.get(i)+"'>"+celebrities.get(i)+"</a></br>");
           }
      }
if(celebrities.size()>3)
    out.print("<a href='#'>seemore</a>");
%>
 </div>
 <div class="profiledetailsfontcolor" style="width:200px">
 Games:
 <%List games=(List)ud.get("games");
  for(int i=0;i<games.size();i++)
           {
      if(i<3){
          out.print("<a href='pages.jsp?pageid="+games.get(i)+"'>"+games.get(i)+"</a></br>");
           }
      }
if(games.size()>3)
    out.print("<a href='#'>seemore</a>");
%>
 </div>
 <div class="profiledetailsfontcolor" style="width:200px">
 Sports:
 <%List sports=(List)ud.get("sports");
  for(int i=0;i<sports.size();i++)
           {
      if(i<3){
          out.print("<a href='pages.jsp?pageid="+sports.get(i)+"'>"+sports.get(i)+"</a></br>");
           }
      }
if(sports.size()>3)
    out.print("<a href='#'>seemore</a>");
%>
 </div>
 <div class="profiledetailsfontcolor" style="width:200px">
 Others:
 <%List other=(List)ud.get("others");
  for(int i=0;i<other.size();i++)
           {
      if(i<3){
          out.print("<a href='pages.jsp?pageid="+other.get(i)+"'>"+other.get(i)+"</a></br>");
           }
      }
if(other.size()>3)
    out.print("<a href='#'>seemore</a>");
%>
 </div>
 </div>
</div>


<div id="innercenter" style="width:624px; float: left; border:solid 1px; " >
<div id="profpic" style=" width:380px;  float:left">
<div  class="profilepic" style="">
    <img src="images/<%out.print(obj.getImage((String)ud.get("propic")).get("url")); %>" width="150" height="200"/>
</div>
<div class="secondaryprofilepic" style=" float:right; border:solid 1px;">
<div class="innersecondaryprofilepic_1" style="border:solid 1px;">
</div>
<div class="innersecondaryprofilepic_2" style="border:solid 1px;">
</div>
</div>
</div>
<div id="pinnedpic" style="width:215px; border:solid 1px; overflow: scroll">
    <%List pinnedpics=(List)ud.get("pinnedpic");
      for(int i=0;i<pinnedpics.size();i++){
  out.print("<img src='images/"+obj.getImage((String)pinnedpics.get(i)).get("url") +"' width='50' height='50'/>");
  if((i+1)%6==0)
      out.print("</br>");
  }
%>
    
</div>
<div id="stature" style="width: 604px;border:solid 1px;">
    <%obj.getStature(request.getParameter("userid")); %>
</div>
<div id="update" style="width:624px; border:solid 1px; float:left; height:20px;">
<a  >My stream</a>
<a  >My profile</a>
<a onclick="getalbums('<%out.print(request.getParameter("userid")); %>')" >My picz</a>
</div>
<div style="height:40px; width:624px; float:left">
    <form name="postform" onsubmit="dopost('<%out.print(request.getParameter("userid")); %>')">
<input type="text" name="post" style="width:500px; height:25px; float:left" />
<input type="button" value="update" onclick="dopost('<%out.print(request.getParameter("userid")); %>')"/>
    </form>
</div>
<div style="width:624px; float: left">
<div style="width:500px; float: left">
            <input type="text" id="albumname" size="70"/>
            <input type="button" value="Create Album" onclick="createalbum()" size="20"/>
        
    </div>
    <div style="width:624px; float: left" id="streams">
        
    </div>
    <div style="width:624px; float: left" id="myalbum">
        
    </div>
    
</div>
</div>


<div style="height:600px; width:200px; border:solid 1px; float:right; margin-top:50px;"  >
<div style="width:200px; height:50px;">
<div style="width:50px; height:50px; float:left">
<img src="Blushing_smiley_face.png" height="50" width="50" />
</div>
<div class="headerfont" style="width:150px; height:50px; float:right">
My freniz
</div>

</div>
<%List frnds1=(List)obj.getFriendsAndVote(request.getParameter("userid")).get("friendlist");
for(int i=0;i<frnds1.size();i++){%>
<div class="innerpic" >
<img src="preview.jpg" height="50" width="50" />
</div>
<div class="innername" >
    <% out.print("<a href='#profile_1?userid="+frnds1.get(i)+"'>"+((HashMap)((HashMap)application.getAttribute("users")).get(frnds1.get(i))).get("username")+"</a>");%>
</div>
<div class="innername" >
    <% out.print("Votes :"+((List)((HashMap)((HashMap)application.getAttribute("users")).get(frnds1.get(i))).get("votes")).size());%>
</div>
<%}%>
</div>


</div>

<div id="light3" style="width:550px; height:240px; " class="white_content">
        
       
<div style="width:500px; height:200px; margin-left:20px; margin-top:20px; ">
<div style="width:30px; height:30px; margin-top:5px; margin-left:5px; float:left; ">
To:
</div>
<form name="sendmessage" onsubmit="sendmsguser()">


<div style="width:400px; height:30px; margin-top:6px; margin-left:5px; float:left; ">
    <input size="40" type="text" disabled="disable" name="msgto" value="<%out.print(ud.get("fname")+" "+ud.get("lname"));%>"/>
    <input type="hidden" value="<%out.print(request.getParameter("userid"));%>" name="to"/>
</div>
<div style="width:300px; height:100px; margin-top:10px; margin-left:60px; float:left;">
<textarea rows="4" cols="50" name="msg" >
</textarea>
</div>


<div style="width:300px; ">

  <ul class="roundbuttons sendmessagewidth">
  <li><input type="button" name="cancel" value="cancel" onClick="document.getElementById('light3').style.display='none';   document.getElementById('fade3').style.display='none';"  /></li>
  <li><input type="button" name="send" value="send" onclick="sendmsguser()" /></li>
  </ul>


</div>
</form>
</div>
        
        </div>
		
		<div id="fade3" onClick="document.getElementById('light3').style.display='none';   document.getElementById('fade3').style.display='none'" class="black_overlay">
        </div>


<div id="light4" style="width:550px; height:240px; " class="white_content">
       
        
        </div>
		
		<div id="fade4" onClick="document.getElementById('light4').style.display='none';   document.getElementById('fade4').style.display='none'" class="black_overlay">
        </div>
<div id="light5" style="width:550px; height:240px; " class="white_content">
    <div id="light5_image" style="width:624px;">
        
    </div>
       
    <div id="light5_comments" style="width:624px;">
        
    </div>
        </div>
		
		<div id="fade5" onClick="document.getElementById('light5').style.display='none';   document.getElementById('fade5').style.display='none'" class="black_overlay">
        </div>
