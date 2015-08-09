<%-- 
    Document   : welcome
    Created on : Oct 6, 2011, 2:54:36 AM
    Author     : abdulnizam
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Welcome to Freniz</title>
<link href="css/style.css" rel="stylesheet" />
<link type="text/css" rel="stylesheet" media="all" href="css/chat.css" />
<link type="text/css" rel="stylesheet" media="all" href="css/screen.css" />


<link rel="stylesheet" href="css/drop.css" type="text/css" media="screen" />
<script type="text/javascript" src="js/jquery-latest.js"></script> 
<script type="text/javascript" src="js/onload.js"></script> 
<script src="js/ajax.js" type="text/javascript"></script>
<script src="js/audio-player.js" type="text/javascript"></script>
<script type="text/javascript" src="js/audio-player.js"></script> 
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/chat.js"></script>
<script type="text/javascript" src="js/accountsettings.js"></script>

   <script type="text/javascript">
       
       function redirect()
       {
   <%if(((String)session.getAttribute("userid"))!=null){%>
           initlisteners();
           showprofile('<%out.print(session.getAttribute("userid")); %>');
           <%}else{%>
               var e=document.getElementById("container");
               e.style.visibility='visible';
               <%}%>
       }
         
         AudioPlayer.setup("player.swf", {  
                width: 275 
			
            });  
       
        function slideright()
{
	var slidingDiv = document.getElementById("d1");
	var stopPosition = -5;
	
	if (parseInt(slidingDiv.style.left) < stopPosition )
	{
		
	slidingDiv.style.left = parseInt(slidingDiv.style.left) + 2 + "px";
	setTimeout(slideright,1);
		
	}
	else
	{
		var e=document.getElementById("d2");
		e.onclick=slideleft;
	}
	
	
}
function slideleft()
{
	var slidingDiv = document.getElementById("d1");
	var stopPosition1 = -280;
	
	if (parseInt(slidingDiv.style.left) > stopPosition1 )
	{
		slidingDiv.style.left = parseInt(slidingDiv.style.left) - 2 + "px";
		setTimeout(slideleft,1);
	}
	else
	{
		var e=document.getElementById("d2");
		e.onclick=slideright;
	}
}

//]]>
</script> 
<style type="text/css" >
.playlist ul {
	list-style-image:url(dropdown_linkbg.gif);
	list-style-type:none;
	margin:0;
	padding:0 25px;
	
}
.playlist li {
	font-family:"Comic Sans MS", cursive;
	font-size:14px;
	color:#FFF;
	padding:5px 0 4px 20px;
	border-bottom:1px solid #000;
}
.playlist {
	background-color:#5A5;
	border-top:1px solid #000;
}

.openplayer{
	background-image: url(open1.png);
	height:24px;
	width:25px;
}

</style>  


<style type="text/css">

      .subfont {
	font-family: "Comic Sans MS", cursive;
	font-size: 14px;
	font-style: normal;
	font-weight: bold;
}

.maindiv{
	width:300px; 
	float:left;
	border:solid 1px;
}


.userpic{
	width:50px; 
	height:50px;
	 margin-top:5px; 
	 margin-left:20px;
	 float:left; 
	 border:solid 1px;
	
}
.writtenon{
	float:left; 
	margin-left:20px; 
	margin-top:20px; 
	height:20px; 
	width:100px;
	font-family: "Comic Sans MS", cursive;
	font-size: 14px;
	font-style: normal;
	font-weight: bold;
}
.frdpic{
	width:50px; 
	height:50px; 
	margin-top:5px; 
	margin-right:20px;
	float:right; 
	border:solid 1px; 
}
.username{
	float:left; 
	margin-left:5px; 
	height:20px;
	 width:100px;
}
.frdname{
	float:right; 
	margin-right:5px;   
	height:20px; 
	width:100px;
}
.commentbox
{
	width:300px; 
	margin-top:15px; 
	height:50px; 
	float:left; 
	border:solid 1px;
}
.gap{
	width:300px; 
	height:1px; 
	float:right;
}
.commenttab{
	width:300px; 
	height:20px; 
	float:left; 
	border:solid 1px; 
}
.votesymbol
{
	background-image:url(images/vote.png);
	height:16px;
	width:16px;
	float:left;
}
.streamwidth1{
    width:624px;
    height: 100%;
}


body {
	background-color: #eeeeee;
	padding:0;
	margin:0 auto;
	font-family:"Lucida Grande",Verdana,Arial,"Bitstream Vera Sans",sans-serif;
	font-size:11px;
}
</style>
       <style>
ul.mood li
{
	display:inline-block;
}
ul.mood li img
{
	width:32px;
	height:32px;
}
</style>

</head>


<body onhashchange="OnHashChange('event');">
<div style=" height:70px; ">
<div id="name" style="height:70px; margin-top:-10px; border:solid 2px; margin-left:-10px">
<img id="name1" src="greenhead.png" width="1024" height="70"  />
</div> 
</div>

<div style="width:210px; height:70px; position:absolute; float:left; left: 25px; top: 2px; ">
 <img src="freniz.png" height="70" width="220"/>
</div>
<ul class=" roundbuttons profilerdwidth" style="left:220px; top: 20px;">
<li ><a >Streams</a></li>
<li ><a >Profile</a></li>
<li ><a  id="message">Message</a></li>
<li ><a >Music</a></li>
<li ><a >Apps</a></li>
</ul>

<div  style=" position:absolute; width:250px; right:0px; top:40px">
<ul class="topnav"  style=" right:50px; margin-top:17px;">
    <li><a  id="invites">Invites</a></li>
    
    <li><a >Options </a>
      <ul class="subnav">
            <li><a >Account settings</a></li>
             <li><a >LetMe Out</a></li>
             
      </ul>
    </li>
    <li><a >Search</a>
    <ul class="subnav">
   <li> <div id="searchwrapper"><form action="">
<input type="text" class="searchbox" name="s" value="" />
<input type="image" src="button.png" class="searchbox_submit" value="" />
</form></div>
</li></ul>
    </li>
</ul>
</div>  
    <div style="width:300px;  height:200px; ">
<ul class="mood" style="display:block; max-height:200px; overflow-y:scroll">
<li><img src="images/mood/6smiley_face.gif" onclick="updatemood('6smiley_face.gif.png')"/></li>
<li><img src="images/mood/130.png" onclick="updatemood('130.png')" /></li>
<li><img src="images/mood/22461291.png" onclick="updatemood('22461291.png')" /></li>
<li><img src="images/mood/angel.png" onclick="updatemood('angel.png')" /></li>
<li><img src="images/mood/att.png" onclick="updatemood('att.png')" /></li>
<li><img  src="images/mood/att2.png" onclick="updatemood('att2.png')" /></li>
<li><img src="images/mood/cas.png" onclick="updatemood('cas.png')" /></li>
<li><img src="images/mood/cry.gif" onclick="updatemood('2cry.gif')" /></li>
<li><img src="images/mood/cry1.png" onclick="updatemood('cry1.png')" /></li>
<li><img src="images/mood/cry2.png" onclick="updatemood('cry2.png')" /></li>
<li><img src="images/mood/cry3.png" onclick="updatemood('cry3.png')" /></li>
<li><img src="images/mood/irritate.png" onclick="updatemood('irritate.png')" /></li>
<li><img src="images/mood/kiss.png" onclick="updatemood('kiss.png')" /></li>
<li><img src="images/mood/love.png" onclick="updatemood('love.png')" /></li>
<li><img src="images/mood/love2.png" onclick="updatemood('love2.png')" /></li>
<li><img src="images/mood/love3.png" onclick="updatemood('love3.png')" /></li>
<li><img src="images/mood/love4.png" onclick="updatemood('love4.png')"/></li>
<li><img src="images/mood/love5.png" onclick="updatemood('love5.png')"/></li>
<li><img src="images/mood/love.6png.png" onclick="updatemood('love.6png.png')"/></li>
<li><img src="images/mood/nospeak.png" onclick="updatemood('nospeak.png')"/></li>
<li><img src="images/mood/prirate.png" onclick="updatemood('prirate.png')"/></li>
<li><img src="images/mood/resign.png" onclick="updatemood('resign.png')"/></li>
<li><img src="images/mood/sad.png" onclick="updatemood('sad.png')"/></li>
<li><img  src="images/mood/sad1.png" onclick="updatemood('sad1.png')"/></li>
<li><img src="images/mood/sad2.png" onclick="updatemood('sad2.png')"/></li>
<li><img src="images/mood/sad3.png" onclick="updatemood('sad3.png')" /></li>
<li><img src="images/mood/sad4.png" onclick="updatemood('sad4.png')"/></li>
<li><img src="images/mood/sad5.png" onclick="updatemood('sad5.png')"/></li>
<li><img src="images/mood/Sad06.gif" onclick="updatemood('Sad06.gif')"/></li>
<li><img src="images/mood/shut.png" onclick="updatemood('shut.png')"/></li>
<li><img src="images/mood/shut1.png" onclick="updatemood('shut1.png')"/></li>
<li><img src="images/mood/sleep.png" onclick="updatemood('sleep.png')"/></li>
<li><img src="images/mood/sleep2.png" onclick="updatemood('sleep2.png')"/></li>
<li><img src="images/mood/sm2.png" onclick="updatemood('sm2.png')"/></li>
<li><img src="images/mood/sm3.png" onclick="updatemood('sm3.png')"/></li>
<li><img src="images/mood/sm4.png" onclick="updatemood('sm4.png')"/></li>
<li><img src="images/mood/sm5.png" onclick="updatemood('sm5.png')"/></li>
<li><img src="images/mood/sm6.png" onclick="updatemood('sm6.png')"/></li>
<li><img src="images/mood/sm7.png" onclick="updatemood('sm7.png')"/></li>
<li><img src="images/mood/happy.png" onclick="updatemood('happy.png')"/></li>
<li><img src="images/mood/sm9.png" onclick="updatemood('sm9.png')"/></li>
<li><img src="images/mood/smile.png" onclick="updatemood('smile.png')"/></li>
<li><img src="images/mood/smile2.png" onclick="updatemood('smile2.png')"/></li>
<li><img src="images/mood/smile3.png" onclick="updatemood('smile3.png')"/></li>
<li><img src="images/mood/SmileyCoffeeTired.jpg" onclick="updatemood('SmileyCoffeeTired.jpg')"/></li>
<li><img src="images/mood/smiley-sad.png" onclick="updatemood('smiley-sad.png')"/></li>
<li><img src="images/mood/stop.png" onclick="updatemood('stop.png')"/></li>
</ul>

<div >

    <div id="container" style="visibility: hidden">
        
        
<div style="height:150px; margin-top:200px; width:300px; float:right; border:solid 4px">
<form action="" method="post" onsubmit="login()">
<table class="subfont" style="margin-top:10px; margin-left:20px">
        <tr>
        <td>User Name:</td><td><input type="text" name="username" id="userid" size="20"/></td></tr>
        <tr>
        <td>Password:</td><td><input type="password" name="password" id="password" size="20"/></td></tr>
        </table>
<div class="sublink" style="margin-top:10px; margin-left:20px">
 <a href = "javascript:void(0)" onclick = "document.getElementById('light').style.display='block';document.getElementById('fade').style.display='block'">New User</a>
<a href = "javascript:void(0)" onclick = "document.getElementById('light2').style.display='block';document.getElementById('fade').style.display='block'"> Forget Account?</a>

</div>
<div style="width:100px; height:40px; float:right">
 <ul class="roundbuttons singlerdwidth" >
     <li><a  onclick="login()" >LetMe In</a></li>
        </ul>
</div>
</form>
</div>
<div id="light" style="width:800x; height:500px" class="white_content">

       <div style="width:800px; height:500px; float:left; ">
       <div onClick="document.getElementById('light2').style.display='none';document.getElementById('light').style.display='none';   document.getElementById('fade').style.display='none'"; style=" cursor:pointer;height:20px; font-weight:bold; text-align:center;width:20px; float:right; border:solid 3px">
       X
       </div>
<div class="headerfont">
Create Account
</div>
<br/>
<div style="width:400px; margin-left:150px; height:400px; float:left">
<form action="" method="post">
<table border="0" width="360" class="subfont" style="text-align:right" cellpadding="8">
<tr>
<td>User Name :</td>
<td><input type="text" size="30" height="20" name="username" id="username" onkeyup="checkusername()" /></td>
</tr>
<tr>
<td>E-Mail ID :</td>
<td><input type="text" size="30" height="20" name="eid" id="eid" onkeyup="checkemail()" onfocus="checkmail()" /></td>
</tr>
<tr>
<td>Password :</td>
<td><input type="password" size="30" height="20" name="password1" id="password1" onkeyup="checkpassword()" /></td>
</tr>
<tr>
<td>Re-Password :</td>
 <td><input type="password" size="30" height="20" name="cpassword" id="cpassword" onkeyup="matchpassword()" /></td>
 </tr>
<tr>
<td>First Name :</td>
<td><input type="text" size="30" height="20" name="fname" id="fname" /></td>
</tr>
<tr></tr>
<tr>
<td>Last Name :</td>
<td><input type="text" size="30" height="20" name="lname" id="lname"/></td>
</tr>

<tr>
<td>Sex :</td>
<td style="text-align:left"><select class="select" name="sex" id="sex"><option value="0">Select Sex:</option><option value="1">Female</option><option value="2">Male</option></select>
</td>
</tr>
<tr>
<td>Birth Day :</td>
<td style="text-align:left"><select class="" id="birthday_month" name="birthday_month"><option value="-1">Month:</option><option value="1">Jan</option>
<option value="2">Feb</option>
<option value="3">Mar</option>
<option value="4">Apr</option>
<option value="5">May</option>
<option value="6">Jun</option>
<option value="7">Jul</option>
<option value="8">Aug</option>
<option value="9">Sep</option>
<option value="10">Oct</option>

<option value="11">Nov</option>
<option value="12">Dec</option>
</select> <select name="birthday_day" id="birthday_day"  onchange="bagofholding" autocomplete="off"><option value="-1">Day:</option><option value="1">1</option>
<option value="2">2</option>
<option value="3">3</option>
<option value="4">4</option>
<option value="5">5</option>
<option value="6">6</option>

<option value="7">7</option>
<option value="8">8</option>
<option value="9">9</option>
<option value="10">10</option>
<option value="11">11</option>
<option value="12">12</option>
<option value="13">13</option>
<option value="14">14</option>
<option value="15">15</option>

<option value="16">16</option>
<option value="17">17</option>
<option value="18">18</option>
<option value="19">19</option>
<option value="20">20</option>
<option value="21">21</option>
<option value="22">22</option>
<option value="23">23</option>
<option value="24">24</option>

<option value="25">25</option>
<option value="26">26</option>
<option value="27">27</option>
<option value="28">28</option>
<option value="29">29</option>
<option value="30">30</option>
<option value="31">31</option>
</select> <select name="birthday_year" id="birthday_year"  autocomplete="off"><option value="-1">Year:</option><option value="2011">2011</option>

<option value="2010">2010</option>
<option value="2009">2009</option>
<option value="2008">2008</option>
<option value="2007">2007</option>
<option value="2006">2006</option>
<option value="2005">2005</option>
<option value="2004">2004</option>
<option value="2003">2003</option>
<option value="2002">2002</option>

<option value="2001">2001</option>
<option value="2000">2000</option>
<option value="1999">1999</option>
<option value="1998">1998</option>
<option value="1997">1997</option>
<option value="1996">1996</option>
<option value="1995">1995</option>
<option value="1994">1994</option>
<option value="1993">1993</option>

<option value="1992">1992</option>
<option value="1991">1991</option>
<option value="1990">1990</option>
<option value="1989">1989</option>
<option value="1988">1988</option>
<option value="1987">1987</option>
<option value="1986">1986</option>
<option value="1985">1985</option>
<option value="1984">1984</option>

<option value="1983">1983</option>
<option value="1982">1982</option>
<option value="1981">1981</option>
<option value="1980">1980</option>
<option value="1979">1979</option>
<option value="1978">1978</option>
<option value="1977">1977</option>
<option value="1976">1976</option>
<option value="1975">1975</option>

<option value="1974">1974</option>
<option value="1973">1973</option>
<option value="1972">1972</option>
<option value="1971">1971</option>
<option value="1970">1970</option>
<option value="1969">1969</option>
<option value="1968">1968</option>
<option value="1967">1967</option>
<option value="1966">1966</option>

<option value="1965">1965</option>
<option value="1964">1964</option>
<option value="1963">1963</option>
<option value="1962">1962</option>
<option value="1961">1961</option>
<option value="1960">1960</option>
<option value="1959">1959</option>
<option value="1958">1958</option>
<option value="1957">1957</option>

<option value="1956">1956</option>
<option value="1955">1955</option>
<option value="1954">1954</option>
<option value="1953">1953</option>
<option value="1952">1952</option>
<option value="1951">1951</option>
<option value="1950">1950</option>
<option value="1949">1949</option>
<option value="1948">1948</option>

<option value="1947">1947</option>
<option value="1946">1946</option>
<option value="1945">1945</option>
<option value="1944">1944</option>
<option value="1943">1943</option>
<option value="1942">1942</option>
<option value="1941">1941</option>
<option value="1940">1940</option>
<option value="1939">1939</option>

<option value="1938">1938</option>
<option value="1937">1937</option>
<option value="1936">1936</option>
<option value="1935">1935</option>
<option value="1934">1934</option>
<option value="1933">1933</option>
<option value="1932">1932</option>
<option value="1931">1931</option>
<option value="1930">1930</option>

<option value="1929">1929</option>
<option value="1928">1928</option>
<option value="1927">1927</option>
<option value="1926">1926</option>
<option value="1925">1925</option>
<option value="1924">1924</option>
<option value="1923">1923</option>
<option value="1922">1922</option>
<option value="1921">1921</option>

<option value="1920">1920</option>
<option value="1919">1919</option>
<option value="1918">1918</option>
<option value="1917">1917</option>
<option value="1916">1916</option>
<option value="1915">1915</option>
<option value="1914">1914</option>
<option value="1913">1913</option>
<option value="1912">1912</option>

<option value="1911">1911</option>
<option value="1910">1910</option>
<option value="1909">1909</option>
<option value="1908">1908</option>
<option value="1907">1907</option>
<option value="1906">1906</option>
<option value="1905">1905</option>
</select></td>
</tr>
</table>
<br />

<div style=" margin-left:40px; width:100px; height:40px; float:left">
 <ul class="roundbuttons rdwidth" style="margin-left:100px">
        <li><a  onclick="createaccount()">CREATE ACCOUNT</a></li>
        </ul>
</div>
</form>

</div>
</div>

        </div>



<div id="light2" style="width:800x; height:400px" class="white_content">

       <div style="width:800px; height:200px; float:left; ">
       <div onClick="document.getElementById('light2').style.display='none';document.getElementById('light').style.display='none';   document.getElementById('fade').style.display='none'"; style=" cursor:pointer;height:20px; font-weight:bold; text-align:center;width:20px; float:right; border:solid 3px">
       X
       </div>
<div class="headerfont">
Forget Account
</div>
<br/>
<div style="width:400px; margin-left:150px; height:200px; float:left">
<form action="" method="post">
<table border="0" width="360" class="subfont" style="text-align:right" cellpadding="8">
<tr>
<td>E-Mail ID :</td>
<td><input type="text" size="30" height="20" name="eid" /></td>
</tr>
<tr>
</table>
<br />

<div style="width:400px; height:100px; border:solid 1px">
  <div style="height:100px; width:100px; float:left; border:solid 1px">
   </div>
  <div style="height:20px; width:100px; float:left; margin-top:40px; margin-left:10px;border:solid 1px">
   </div>
</div><br/>

<div style=" margin-left:40px; width:100px; height:40px; float:left">
 <ul class="roundbuttons rdwidth" style="margin-left:100px">
        <li><a >Retrive Account</a></li>
        </ul>
</div>
</form>

</div>
</div>




        </div>


  <div id="fade" onClick="document.getElementById('light2').style.display='none';document.getElementById('light').style.display='none';   document.getElementById('fade').style.display='none'" class="black_overlay">
        </div>

       
    </div>

 <div id="d1" style="position:fixed; top:60px; left:-280px; width:300px; height:24px; border: solid 2px; ">

 <p id="audioplayer_1"><span>Update Required</span>
  <a href="http://get.adobe.com/flashplayer/" target="_blank">Flash plugin</a></p>  
 <script type="text/javascript">  
        AudioPlayer.embed("audioplayer_1", {soundFile: "1.mp3,2.mp3"});  
        </script> 
   <div id="d2" class="openplayer" onclick="slideright()" style=" margin-top:-24px; float:right; "  >
  </div> 
  <div class="playlist" style=" width:280px">
  <ul>
    <li> minnale </li>
    <li> Alaipayuthe </li>
  </ul>
  </div> 

</body>
</html>
