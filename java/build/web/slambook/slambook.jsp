<%-- 
    Document   : slambook
    Created on : Dec 31, 2011, 7:52:29 AM
    Author     : abdulnizam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page  import="hangpeer.NewMain,java.util.*" %>
<html>
  <head>
    <title>Slam Book</title>

<link rel="stylesheet" href="style.css" type="text/css" charset="utf-8"/>
        <script type="text/javascript" src="jquery-1.3.2.js"></script>

<script type="text/javascript" src="js/jquery.min.js"></script>

<!-- Page Flip -->
<script type="text/javascript" src="js/pageflip.js"></script>

<script type="text/javascript">
	/* pageflip timing */
	var flipTime = 1000; // in ms [recommended 1000]
</script>

<link rel="stylesheet" type="text/css" href="style/reset.css">
<link rel="stylesheet" type="text/css" href="style/pageflip.css">

<style>
body {
	font-size: .9em;
	font-family: Verdana, Arial, Helvetica;
	line-height: 1.4em;
}
</style>
<style type="text/css">
td {
	font-family:"Comic Sans MS", cursive;
	font-size:20px;
	font-weight:bold;
	color:#090;
}
</style>

<script type="text/javascript">
            $(function() {
                $('#navigation a').stop().animate({'marginLeft':'-120px'},1000);

                $('#navigation > li').hover(
                    function () {
                        $('a',$(this)).stop().animate({'marginLeft':'-5px'},200);
                    },
                    function () {
                        $('a',$(this)).stop().animate({'marginLeft':'-120px'},200);
                    }
                );
            });
        </script>

</head>
<%
    if(session.getAttribute("userid")!=null){
    NewMain obj=(NewMain)session.getAttribute("object");
    HashMap slambook=obj.getSlamBook((String)session.getAttribute("userid"));
    List slamusers=new ArrayList(slambook.keySet());
    HashMap slam=new HashMap();
    
%>



<body>
    <div style="width:100%; height:100px; float:left; ">
<div style="width:40%; height:100px; float:left">
</div>
<div style="width:40%; font-size:50px; font-weight:bold; font-family:'Comic Sans MS', cursive; height:100px; float:left">
Slam Book
</div>

    
    <div id = "pagesContainer">

<!-- the page DIVs at least 4, in multiples of 2. --> 

<!-- insert your DIVs here, classed "pageContent" -->
<% for(int i=0;i<slamusers.size();i++){
        slam=(HashMap)slambook.get(slamusers.get(i));
    %>   
<div class="pageContent">
 


<div style="width:500px; background-color: #999; height: 700px;  float:left">
<form name="slambook" >
    <table style=" margin-left: 20px; margin-top: 20px" cellpadding="5px" cellspacing="3px">
<tr>
<td>Name:</td><td><div style="width:200px; height:50px; border:solid 1px"></div></td></tr>
<tr>
<td>Born On:</td><td><div style="width:200px; height:50px; border:solid 1px"></div></td></tr>
<tr>
<td>Email:</td><td><div style="width:200px; height:50px; border:solid 1px"></div></td></tr>
<tr>
<td>Ring me:</td><td><div style="width:200px; height:50px; border:solid 1px"></div></td></tr>
<tr>
<td>Ambition:</td><td><div style="width:200px; height:50px; border:solid 1px"></div></td></tr>
<tr>
<td>My Hobby:</td><td><div style="width:200px; height:50px; border:solid 1px"></div></td></tr>
<tr>
<td>I Believe in:</td><td><div style="width:200px; height:50px; border:solid 1px"></div></td></tr>
<tr>
<td>About Friendship:</td><td><div style="width:200px; height:50px; border:solid 1px"></div></td></tr>
<tr>
<td>About Love:</td><td><div style="width:200px; height:50px; border:solid 1px"></div></td></tr>
<tr>
<td>i hate:</td><td><div style="width:200px; height:50px; border:solid 1px"></div></td></tr>
<tr>
<td>My Philosophy:</td><td><div style="width:200px; height:50px; border:solid 1px"></div></td></tr>
</table></form>


</div>
</div>
<div class="pageContent">
 
<div style="width:500px; background-color: #aaa; height:700px;  float:left">
<form>
    <table style=" margin-left: 20px; margin-top: 20px" cellpadding="5px" cellspacing="3px">
<tr>
<td>favourite Film:</td><td><div style="width:200px; height:50px; border:solid 1px"></div></td></tr>
<tr>
<td>favourite Music:</td><td><div style="width:200px; height:50px; border:solid 1px"></div></td></tr>
<tr>
<td>favourite Actor:</td><td><div style="width:200px; height:50px; border:solid 1px"></div></td></tr>
<tr>
<td>favourite Actress:</td><td><div style="width:200px; height:50px; border:solid 1px"></div></td></tr>
<tr>
<td>favourite Sports:</td><td><div style="width:200px; height:50px; border:solid 1px"></div></td></tr>
<tr>
<td>favourite Sportsman:</td><td><div style="width:200px; height:50px; border:solid 1px"></div></td></tr>
<tr>
<td>favourite Dress:</td><td><div style="width:200px; height:50px; border:solid 1px"></div></td></tr>
<tr>
<td>favourite Food:</td><td><div style="width:200px; height:50px; border:solid 1px"></div></td></tr>
<tr>
<td>favourite Place:</td><td><div style="width:200px; height:50px; border:solid 1px"></div></td></tr>
<tr>
<td>Close Friends:</td><td><div style="width:200px; height:50px; border:solid 1px"></div></td></tr>
<tr>
<td>I Feel About You:</td><td><div style="width:200px; height:50px; border:solid 1px"></div></td></tr>
<tr>
    <td>My Advice For You:</td><td><div style="width:200px; height:50px; border:solid 1px"></div></td></tr>
</table></form>

</div>


</div>
 <% } %>
</div>
   
    </div>
<div>
    <%for(int i=0;i<slamusers.size();i++){ 
        out.print("<img src='"+((HashMap)application.getAttribute("users")).get("propic")+"' onClick=\"gotoPage('"+i*2+"')\" />");
    }%>
</div>
</body>
</html>
<% } %>