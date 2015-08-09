/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function OnHashChange (event) {
        var hash=window.location.hash;
            var page=hash.substring(1,hash.indexOf('?'));
            var query=hash.substring(hash.indexOf('?')+1,hash.length);
            request.onreadystatechange=changepage;
            request.open("get",page+".jsp?"+query,true);
            request.send(null);
            
            
        }
    function changepage()
    {
        
        if(request.readyState==4 && request.status==200)
            {
                var e=document.getElementById("container");
                e.innerHTML=request.responseText;
                e.style.visibility=visible;
            formatdate();   
                
            }
    }
function initlisteners()
{
    createchat();
    var i=setInterval(function(){
        getonlineusers();
        getbendingfrndrequestcount();
        getmsgcount();
        },1000);
        getstreams();
    formatdate();    
    
}
    var request=new createXMLHttpRequest();
    var request1=new createXMLHttpRequest();
    var request2=new createXMLHttpRequest();
    var request3=new createXMLHttpRequest();
    var request4=new createXMLHttpRequest();
    
    function createXMLHttpRequest(){
  // See http://en.wikipedia.org/wiki/XMLHttpRequest
  // Provide the XMLHttpRequest class for IE 5.x-6.x:
  if( typeof XMLHttpRequest == "undefined" ) XMLHttpRequest = function() {
    try {return new ActiveXObject("Msxml2.XMLHTTP.6.0")} catch(e) {}
    try {return new ActiveXObject("Msxml2.XMLHTTP.3.0")} catch(e) {}
    try {return new ActiveXObject("Msxml2.XMLHTTP")} catch(e) {}
    try {return new ActiveXObject("Microsoft.XMLHTTP")} catch(e) {}
    throw new Error( "This browser does not support XMLHttpRequest." )
  };
  return new XMLHttpRequest();
}
function login()
{
    var userid=document.getElementById("userid").value;
    var pass=document.getElementById("password").value;
    request.onreadystatechange=validate;
    request.open("get","ajax/validate.jsp?userid="+userid+"&password="+pass,true);
    request.send(null);
}
function validate()
{
    if((request.readyState==4)&& (request.status==200))
        {
            var json=eval('('+request.responseText+')');
            if(json.status=='true')
            {
                request.onreadystatechange=myprofile;
                request.open("get","profile_1.jsp?userid="+json.userid,true);
                request.send(null);
                initlisteners();

            }
            else
                {
                document.getElementById("userid").value='';
                document.getElementById("password").value='';
                alert('wrong login details')
                }
        }

}
function myprofile()
{
    if((request.readyState==4) && (request.status==200))
        {
            var e=document.getElementById("container");
            e.innerHTML=request.responseText;
            e.style.visibility='visible';
        }

}


function checkusername()
{
    var e=document.getElementById("username");
    if(e.value.indexOf(' ')==-1)
        {
            request.onreadystatechange=resultusername;
            request.open("get","ajax/checkusername.jsp?userid="+e.value,true);
            request.send(null);
        }
        else
            e.style.background='red';

}
function showprofile(userid)
{
                request4.onreadystatechange=frndprofile;
                request4.open("get","profile_1.jsp?userid="+userid,true);
                request4.send(null);
}
function frndprofile()
{
    if((request4.readyState==4) && (request4.status==200))
        {
            var e=document.getElementById("container");
            e.innerHTML=request4.responseText;
            e.style.visibility='visible';
        }
}

function resultusername()
{
    if(request.readyState==4 && request.status==200)
        {
            var json=eval('('+request.responseText+')');
            if(json.status=='true')
                {
                    var e=document.getElementById("username");
                    e.style.background='green';
                }
                else
                    {
                    var e=document.getElementById("username");
                    e.style.background='red';
                    }
        }
}
function checkemail()
{
    var e=document.getElementById("eid");
    var value=e.value;
    if(value.indexOf(' ')==-1 && value.indexOf('@')!=-1 && value.indexOf('.')!=-1 && value.indexOf('@')!=0 && value.indexOf('@')!=value.length-1 && value.indexOf('.')!=0 && value.indexOf('.')!=value.length-1)
        {
            request.onreadystatechange=resultemail;
            request.open("get","ajax/checkemail.jsp?emailid="+value,true);
            request.send(null);
            
        }
    else
        {
            e.style.background='red';
        }
}
function resultemail()
{
    if(request.readyState==4 && request.status==200)
        {
            var json=eval('('+request.responseText+')')
            if(json.status=='true')
                {
                    var e=document.getElementById("eid");
                    e.style.background="green";

                }
                else
                    {
                          e=document.getElementById("eid");
                        e.style.background="red";
                    }
        }
}
function checkpassword()
{
    var e=document.getElementById("password1");
    var value=e.value;
    if(value.length<6)
        e.style.background='red';
    else
        e.style.background='green';
}
function matchpassword()
{
    var e=document.getElementById("password1");
    var e1=document.getElementById("cpassword");
    if(e.value==e1.value)
        e1.style.background='green';
    else
        e1.style.background='red';
}

function createaccount()
{
    var un=document.getElementById("username");
    var pass=document.getElementById("password1");
    var cpass=document.getElementById("cpassword");
    var email=document.getElementById("eid");
    var fname=document.getElementById("fname");
    var lname=document.getElementById("lname");
    var sex=document.getElementById("sex");
    var bdd=document.getElementById("birthday_day");
    var bdm=document.getElementById("birthday_month");
    var bdy=document.getElementById("birthday_year");
    if(sex.value==1)
        var sex1='female';
    else if(sex.value==2)
         sex1='male';
    if(un.style.backgroundColor=='green' && pass.style.backgroundColor=='green' && cpass.style.backgroundColor=='green' && email.style.backgroundColor=='green' && fname.value!='' && lname.value!='' && sex.value!=0 && bdd.value!=-1 && bdm.value!=-1 && bdy.value!=-1)
        {
            request.onreadystatechange=resultcreateacc;
            request.open("get","ajax/createacc.jsp?un="+un.value+"&pass="+pass.value+"&email="+email.value+"&fname="+fname.value+"&lname="+lname.value+"&sex="+sex1+"&bdd="+bdd.value+"&bdm="+bdm.value+"&bdy="+bdy.value,true);
            request.send(null);
        }
        else
            {
                var e=document.getElementById("light");
                var c=document.createElement("div");
                c.style.height="20px";
                c.style.width=e.style.width;
                c.style.color='red';
                c.innerHTML="*** Please fill the mantatory fields ***";
                e.appendChild(c);

            }
}
function resultcreateacc()
{
    if(request.readyState==4 && request.status==200)
        {
            var json=eval('('+request.responseText+')');
            var e=document.getElementById("light");
            e.style.width="400px";
            e.style.height="400px";
            e.innerHTML=json.html;
        }
}
var msginterval,streamsstate=0,streamsinterval,imgstreaminterval,imgstreamstate=0,notificationinterval,bendingrequestinterval;
function getmsgcount()
{
            request.onreadystatechange = unreadmsgcount;
            request.open("get","ajax/unreadmsgs.jsp",true);
            request.send(null);
        
}
function unreadmsgcount()
{
    if(request.readyState==4 && request.status==200)
        {
            var json=eval('('+request.responseText+')');
            if(json.session=='true')
                {
                    var e= document.getElementById("message");
                    e.innerHTML="message("+json.count+")";
                }
                else
                    {
                        alert('your session has expired please login login');
                    }
        }
}
function getstreams()
{
  
    streamsinterval=setInterval(function(){
        request3.onreadystatechange=friendsstreams;
        request3.open("get","ajax/getstreams.jsp?from=0",true);
        request3.send(null);},1000);
    
    
}
function friendsstreams()
{
    if(request3.readyState==4 && request3.status==200)
        {
            var xml=request3.responseXML;
            var ids=xml.getElementsByTagName("id");
            var sendusers=xml.getElementsByTagName("suserid");
            var susername=xml.getElementsByTagName("susername");
            var suserpic=xml.getElementsByTagName("suserpic");
            var suserplace=xml.getElementsByTagName("suserplace");
            var suseruserfrnds=xml.getElementsByTagName("suserfrnds");
            var suservotes=xml.getElementsByTagName("suservotes");
            var suserpages=xml.getElementsByTagName("suserpages");
            var receivedusers=xml.getElementsByTagName("ruserid");
            var rusername=xml.getElementsByTagName("rusername");
            var ruserpic=xml.getElementsByTagName("ruserpic");
            var ruserplace=xml.getElementsByTagName("ruserplace");
            var ruseruserfrnds=xml.getElementsByTagName("ruserfrnds");
            var ruservotes=xml.getElementsByTagName("ruservotes");
            var ruserpages=xml.getElementsByTagName("ruserpages");
            var statuses=xml.getElementsByTagName("status");
            var votecounts=xml.getElementsByTagName("vote_count");
            var votes=xml.getElementsByTagName("vote");
            var votecontains=xml.getElementsByTagName("votecontains");
            var commentcounts=xml.getElementsByTagName("commentcount");
            var dates=xml.getElementsByTagName("date");
            var e1=document.getElementById("streams");
            var b=document.createElement('div');
            b.classname='streamwidth1';
            for(var i=0;i<ids.length;i++)
                {
                    var c=document.createElement('div');
                    c.className='maindiv';
                    c.id=ids[i].childNodes[0].nodeValue;
                    var d=document.createElement('div');
                    d.className='userpic';
                    d.style.backgroundImage='url('+suserpic[i].childNodes[0].nodeValue+')';
                    c.appendChild(d);
                    var e=document.createElement('div');
                    e.className='writtenon';
                    e.innerHTML='Wirtten On';
                    c.appendChild(e);
                    var f=document.createElement('div');
                    f.className='frdpic';
                    f.style.backgroundImage='url('+ruserpic[i].childNodes[0].nodeValue+')';
                    c.appendChild(f);
                    var g=document.createElement('div');
                    g.className='gap';
                    c.appendChild(g);
                    var h=document.createElement('div');
                    h.className='username subfont';
                    h.innerHTML=susername[i].childNodes[0].nodeValue;
                    c.appendChild(h);
                    var i1=document.createElement('div');
                    i1.className='frdname subfont';
                    i1.innerHTML=rusername[i].childNodes[0].nodeValue;
                    c.appendChild(i1);
                    var j=document.createElement('div');
                    j.className='commentbox';
                    j.innerHTML=statuses[i].childNodes[0].nodeValue;
                    c.appendChild(j);
                    var k=document.createElement('div');
                    k.className='commenttab';
                    k.innerHTML='<div class="votesymbol"></div>';
                    if(votecontains[i].childNodes[0].nodeValue!="yes")
                        k.innerHTML+='<a onclick="votepost('+ids[i].childNodes[0].nodeValue+')" style="float:left">vote'+votecounts[i].childNodes[0].nodeValue+'</a>';
                    else
                        k.innerHTML+='<a onclick="withdrawpostvote('+ids[i].childNodes[0].nodeValue+')" style="float:left">withdraw'+votecounts[i].childNodes[0].nodeValue+'</a>';
                    k.innerHTML+='<date'+dates[i].childNodes[0].nodeValue+'</div><a href="#" style="float:right">viewcomments('+commentcounts[i].childNodes[0].nodeValue+')</a>'
                    c.appendChild(k);
                    b.appendChild(c);
                }
                
            e1.innerHTML=b.innerHTML;
        
            

        }
}
function reqmorestreams()
{
    streamsstate=streamsstate+100;
    request.onreadystatechange=viewmorestreams;
    request.open("get","ajax/getstreams.jsp?from="+streamsstate,true);
    request.send(null);


}
function viewmorestreams()
{
 if(request.readyState==4 && request.status==200)
     {
        
       var xml=request.responseXML;
            var ids=xml.getElementsByTagName("id");
            var sendusers=xml.getElementsByTagName("suserid");
            var susername=xml.getElementsByTagName("susername");
            var suserpic=xml.getElementsByTagName("suserpic");
            var suserplace=xml.getElementsByTagName("suserplace");
            var suseruserfrnds=xml.getElementsByTagName("suserfrnds");
            var suservotes=xml.getElementsByTagName("suservotes");
            var suserpages=xml.getElementsByTagName("suserpages");
            var receivedusers=xml.getElementsByTagName("ruserid");
            var rusername=xml.getElementsByTagName("rusername");
            var ruserpic=xml.getElementsByTagName("ruserpic");
            var ruserplace=xml.getElementsByTagName("ruserplace");
            var ruseruserfrnds=xml.getElementsByTagName("ruserfrnds");
            var ruservotes=xml.getElementsByTagName("ruservotes");
            var ruserpages=xml.getElementsByTagName("ruserpages");
            var statuses=xml.getElementsByTagName("status");
            var votecounts=xml.getElementsByTagName("vote_count");
            var votes=xml.getElementsByTagName("vote");
            var dates=xml.getElementsByTagName("date");
            var b=document.getElementById("morestreams");
            for(var i=0;i<ids.length;i++)
                {
                    var c=document.createElement('div');
                    c.className='maindiv';
                    c.id=ids[i].childNodes[0].nodeValue;
                    var d=document.createElement('div');
                    d.className='userpic';
                    d.style.backgroundImage='url('+suserpic[i].childNodes[0].nodeValue+')';
                    c.appendChild(d);
                    var e=document.createElement('div');
                    e.className='writtenon';
                    e.innerHTML='Wirtten On';
                    c.appendChild(e);
                    var f=document.createElement('div');
                    f.className='frdpic';
                    f.style.backgroundImage='url('+ruserpic[i].childNodes[0].nodeValue+')';
                    c.appendChild(f);
                    var g=document.createElement('div');
                    g.className='gap';
                    c.appendChild(g);
                    var h=document.createElement('div');
                    h.className='username subfont';
                    h.innerHTML=susername[i].childNodes[0].nodeValue;
                    c.appendChild(h);
                    var i1=document.createElement('div');
                    i1.className='frdname subfont';
                    i1.innerHTML=rusername[i].childNodes[0].nodeValue;
                    c.appendChild(i1);
                    var j=document.createElement('div');
                    j.className='commentbox';
                    j.innerHTML=statuses[i].childNodes[0].nodeValue;
                    c.appendChild(j);
                    var k=document.createElement('div');
                    k.className='commenttab';
                    k.innerHTML='<div class="votesymbol"></div><a href="#" style="float:left">vote'+votecounts[i].childNodes[0].nodeValue+'</a>'+dates[i].childNodes[0].nodeValue+'<a href="#" style="float:right">comment</a>';
                    c.appendChild(k);
                    b.appendChild(c);
                }
               
     
     }
}

function getimagestreams()
{
    imgstreaminterval=setInterval(function(){
        request.onreadystatechange=imgstreams;
        request.open("get","ajax/getimagestream.jsp?from=0",true);
        request.send(null);
    },1000);
}
function imgstreams()
{
    if(request.readyState==4 && request.status==200)
        {
            var xml=request.responseXML;
            var ids=xml.getElementsByTagName("id");
            var users=xml.getElementsByTagName("userid");
            var albumids=xml.getElementsByTagName("albumid");
            var albumnames=xml.getElementsByTagName("albumname");
            var votecounts=xml.getElementsByTagName("vote_count");
            var votes=xml.getElementsByTagName("vote");
            var pinnedpeoples=xml.getElementTagName("pinnedpeople");
            var pinnedpeople_counts=xml.getElementTagName("pinnedpeople_count");
            var dates=xml.getElementsByTagName("date");
            var b=document.createElement("div");
            var e=document.getElementById("imagestrams");
            for(var i=0;i<ids.length;i++)
                {
                    var c=document.createElement('div');
                    c.id=ids[i].childNodes[0].nodeValue;
                    c.style.width=620+"px";
                    c.style.height=70+"px";
                    c.innerHTML=c.id+"<br>"+sendusers[i].childNodes[0].nodeValue+"<br>"+statuses[i].childNodes[0].nodeValue+"<br>"+ dates[i].childNodes[0].nodeValue;
                    b.appendChild(c);
                }
                e.innerHTML=b.innerHTML;

        }
}
function moreimagestreams()
{
    imgstreamstate=imgstreamstate+100;
        request.onreadystatechange=moreimgstreams;
        request.open("get","ajax/getimagestream.jsp?from="+imgstreamstate,true);
        request.send(null);

}
function moreimgstreams()
{
    if(request.readyState==4 && request.status==200)
        {
            var xml=request.responseXML;
            var ids=xml.getElementsByTagName("id");
            var users=xml.getElementsByTagName("userid");
            var albumids=xml.getElementsByTagName("albumid");
            var albumnames=xml.getElementsByTagName("albumname");
            var votecounts=xml.getElementsByTagName("vote_count");
            var votes=xml.getElementsByTagName("vote");
            var pinnedpeoples=xml.getElementTagName("pinnedpeople");
            var pinnedpeople_counts=xml.getElementTagName("pinnedpeople_count");
            var dates=xml.getElementsByTagName("date");
            var b=document.createElement("div");
            var e=document.getElementById("imagestrams");
            for(var i=0;i<ids.length;i++)
                {
                    var c=document.createElement('div');
                    c.id=ids[i].childNodes[0].nodeValue;
                    c.style.width=620+"px";
                    c.style.height=70+"px";
                    c.innerHTML=c.id+"<br>"+users[i].childNodes[0].nodeValue+"<br>"+albumids[i].childNodes[0].nodeValue+"<br>"+ dates[i].childNodes[0].nodeValue;
                    b.appendChild(c);
                }
                e.innerHTML=b.innerHTML;
        }
}

function getmystreams(userid)
{
    streamsinterval=setInterval(function(){
        request.onreadystatechange=mystreams;
        request.open("get","ajax/mystreams.jsp?userid="+userid+"&from=0",true);
        request.send(null);

    },10000);
}
function mystreams()
{
    if(request.readyState==4 && request.status==200)
        {
            var xml=request.responseXML;
            var ids=xml.getElementsByTagName("id");
            var sendusers=xml.getElementsByTagName("suserid");
            var susername=xml.getElementByTagName("susername");
            var suserpic=xml.getElementByTagName("suserpic");
            var suserplace=xml.getElementByTagName("suserplace");
            var suseruserfrnds=xml.getElementsByTagName("suserfrnds");
            var suservotes=xml.getElementsByTagName("suservotes");
            var suserpages=xml.getElementByTagName("suserpages");
            var receivedusers=xml.getElementsByTagName("ruserid");
            var rusername=xml.getElementByTagName("rusername");
            var ruserpic=xml.getElementByTagName("ruserpic");
            var ruserplace=xml.getElementByTagName("ruserplace");
            var ruseruserfrnds=xml.getElementsByTagName("ruserfrnds");
            var ruservotes=xml.getElementsByTagName("ruservotes");
            var ruserpages=xml.getElementByTagName("ruserpages");
            var statuses=xml.getElementsByTagName("status");
            var votecounts=xml.getElementsByTagName("vote_count");
            var votes=xml.getElementsByTagName("vote");
            var dates=xml.getElementsByTagName("date");
            var e=document.getElementById("streams");
            var b=document.createElement('div');
        
            for(var i=0;i<ids.length;i++)
                {
                    var c=document.createElement('div');
                    c.id=ids[i].childNodes[0].nodeValue;
                    c.style.width=620+"px";
                    c.style.height=70+"px";
                    c.innerHTML=c.id+"<br>"+sendusers[i].childNodes[0].nodeValue+"<br>"+statuses[i].childNodes[0].nodeValue+"<br>"+ dates[i].childNodes[0].nodeValue;
                    b.appendChild(c);

                }

                e.innerHTML=b.innerHTML;


        }
}
function reqmoremystreams(userid)
{

    mystreamsstate=mystreamsstate+500;
    request.onreadystatechange=viewmoremystreams;
    request.open("get","ajax/mystreams.jsp?userid="+userid+"&from="+mystreamsstate,true);
    request.send(null);


}
function viewmoremystreams()
{
 if(request.readyState==4 && request.status==200)
     {

       var xml=request.responseXML;
            var ids=xml.getElementsByTagName("id");
            var sendusers=xml.getElementsByTagName("suserid");
            var susername=xml.getElementByTagName("susername");
            var suserpic=xml.getElementByTagName("suserpic");
            var suserplace=xml.getElementByTagName("suserplace");
            var suseruserfrnds=xml.getElementsByTagName("suserfrnds");
            var suservotes=xml.getElementsByTagName("suservotes");
            var suserpages=xml.getElementByTagName("suserpages");
            var receivedusers=xml.getElementsByTagName("ruserid");
            var rusername=xml.getElementByTagName("rusername");
            var ruserpic=xml.getElementByTagName("ruserpic");
            var ruserplace=xml.getElementByTagName("ruserplace");
            var ruseruserfrnds=xml.getElementsByTagName("ruserfrnds");
            var ruservotes=xml.getElementsByTagName("ruservotes");
            var ruserpages=xml.getElementByTagName("ruserpages");
            var statuses=xml.getElementsByTagName("status");
            var votecounts=xml.getElementsByTagName("vote_count");
            var votes=xml.getElementsByTagName("vote");
            var dates=xml.getElementsByTagName("date");
            var b=document.getElementById("morestreams");
            for(var i=0;i<ids.length;i++)
                {
                    var c=document.createElement('div');
                    c.id=ids[i].childNodes[0].nodeValue;
                    c.style.width=620+"px";
                    c.style.height=70+"px";
                    c.innerHTML=c.id+"<br>"+sendusers[i].childNodes[0].nodeValue+"<br>"+statuses[i].childNodes[0].nodeValue+"<br>"+ dates[i].childNodes[0].nodeValue;
                    b.appendChild(c);
                }


     }
}





function sendfrndreq(userid)
{
    request.onreadystatechange=frndreq;
    request.open("get","ajax/frndreq.jsp?userid="+userid,true);
    request.send(null);
}
function frndreq()
{
    if(request.readyState==4 && request.status==200)
        {
            var json=eval('('+request.responseText+')');
            alert(json.status);
        }
}
function removefrnd(userid)
{
    request.onreadystatechange=remvefrnd;
    request.open("get","ajax/removefrnd.jsp?userid="+userid,true);
    request.send(null);
}
function remvefrnd()
{
    if(request.readyState==4 && request.status==200)
        {
            var json=eval('('+request.responseText+')');
            alert(json.status);
        }
}
function cancelfrndreq(userid)
{
    request.onreadystatechange=cancelfrnd;
    request.open("get","ajax/cancelfrndreq.jsp?userid="+userid,true);
    request.send(null);
}
function cancelfrnd()
{
    if(request.readyState==4 && request.status==200)
        {
            var json=eval('('+request.responseText+')');
            alert(json.status);
        }
}
function getnotificationscount()
{
  notificationinterval=setInterval( function(){request.onreadystatechange=notificationcount;
    request.open("get","ajax/notificationcount.jsp",true);
    request.send(null);},1000);
}
function notificationcount()
{
    if(request.readyState==4 && request.status==200)
        {
            var json=eval('('+request.responseText+')');
            alert(json.count);
        }
}
function getbendingfrndrequestcount()
{
      request2.onreadystatechange=bendingrequestcount;
        request2.open("get","ajax/bendingrequestcount.jsp",true);
        request2.send(null);
        
    
}
function bendingrequestcount()
{
    if(request2.readyState==4 && request2.status==200)
        {
            var json=eval('('+request2.responseText+')');
                  var e=document.getElementById("invites");
                    e.innerHTML="Invites("+json.reqcount+")";
        }
}
function getbendingrequest()
{
    request.onreadystatechange=bendingrequest;
    request.open("get","ajax/bendingrequest.jsp",true);
    request.send(null);
}
function bendingrequest()
{
    if(request.readyState==4 && request.status==200)
        {
            var xml=request.responseXML;

        }
}
function addfrnd(userid)
{
    request.onreadystate=acceptfrnd;
    request.open("get","ajax/addfrnd.jsp?userid="+userid,true);
    request.send(null);
}
function acceptfrnd()
{
    if(request.readyState==4 && request.status==200)
        {
            var json=eval('('+request.responseText+')');
            alert(json.status);
        }
}
function ignorerequest(userid)
{
    request.onreadystatechange=ignore;
    request.open("get","ajax/ignorereq.jsp?userid="+userid,true);
    request.send(null);
}
function ignore()
{
    if(request.readyState==4 && request.status==200)
        {
            var json=eval('('+request.responseText+')');
            alert(json.status);
        }
}
function getincomingrequests()
{
   request.onreadystatechange=incomingrequests;
   request.open("get","ajax/incomingrequest.jsp",true);
   request.send(null);
}
function incomingrequests()
{
    if(request.readyState==4 && request.status==200)
        {
            var xml=request.responseXML;
        }
}
function getsentrequests()
{
    request.onreadystatechange=sentrequest;
    request.open("get","ajax/sentrequest.jsp",true);
    request.send(null);
}
function sentrequests()
{
    if(request.readyState==4 && request.status==200)
        {
            var xml=reqquest.responseXML;
        }
}
function getMessages()
{
    request.onreadystatechange=messages;
    request.open("get","ajax/messages.jsp",true);
    request.send(null);
}
function messages()
{
    if(request.readyState==4 && request.status==200)
        {
            var xml=request.responseXML;
        }
}
function getsentmessages()
{
    request.onreadystatechange=sentmessages;
    request.open("get","ajax/sentmessages.jsp",true);
    request.send(null);
}
function sentmessages()
{
    if(request.readyState==4 && request.status==200)
        {
            var xml=request.responseXML;
        }
}
function sentmessage(userid)
{
    request.onreadystatechange=senttouser;
    request.open("get","ajax/sentmessage.jsp?user="+userid,true);
    request.send(null);
}
function senttouser()
{
    if(request.readyState==4 && request.status==200)
        {
            var json=eval('('+request.responseText+')');
            alert(json.status);
        }
}
function searchuser(key)
{
    request.onreadystatechange=searchuserresults;
    request.open("get","ajax/searchuser?key="+key,true);
    request.send(null);
}
function searchuserresults()
{
    if(request.readyState==4 && request.status==200)
        {
            var xml=request.responseXML;
            
        }
}
function searchpages(key)
{
    request.onreadystatechange=searchpageresults;
    request.open("get","ajax/searchpages?key="+key,true);
    request.send(null);
}
function searchpagesresult(key)
{
    if(request.readyState==4 && request.status==200)
        {
            var xml=request.responseXML;
        }
}
function searchbooks(key)
{
    request.onreadystatechange=searchbooksresults;
    request.open("get","ajax/searchpages?key="+key+"&from=books",true);
    request.send(null);
}
function searchbooksresult(key)
{
    if(request.readyState==4 && request.status==200)
        {
            var xml=request.responseXML;
        }
}
function searchmusics(key)
{
    request.onreadystatechange=searchmusicsresults;
    request.open("get","ajax/searchpages?key="+key+"&from=musics",true);
    request.send(null);
}
function searchmusicsresult(key)
{
    if(request.readyState==4 && request.status==200)
        {
            var xml=request.responseXML;
        }
}
function searchmovies(key)
{
    request.onreadystatechange=searchmoviesresults;
    request.open("get","ajax/searchpages?key="+key+"&from=movies",true);
    request.send(null);
}
function searchmoviesresult(key)
{
    if(request.readyState==4 && request.status==200)
        {
            var xml=request.responseXML;
        }
}
function searchcelebrities(key)
{
    request.onreadystatechange=searchcelebritiesresults;
    request.open("get","ajax/searchpages?key="+key+"&from=celebrities",true);
    request.send(null);
}
function searchcelebritiesresult(key)
{
    if(request.readyState==4 && request.status==200)
        {
            var xml=request.responseXML;
        }
}
function searchgames(key)
{
    request.onreadystatechange=searchgamesresults;
    request.open("get","ajax/searchpages?key="+key+"&from=games",true);
    request.send(null);
}
function searchgamesresult(key)
{
    if(request.readyState==4 && request.status==200)
        {
            var xml=request.responseXML;
        }
}
function searchsports(key)
{
    request.onreadystatechange=searchsportsresults;
    request.open("get","ajax/searchpages?key="+key+"&from=sports",true);
    request.send(null);
}
function searchsportsresult(key)
{
    if(request.readyState==4 && request.status==200)
        {
            var xml=request.responseXML;
        }
}
function searchothers(key)
{
    request.onreadystatechange=searchothersresults;
    request.open("get","ajax/searchpages?key="+key+"&from=books",true);
    request.send(null);
}
function searchothersresult(key)
{
    if(request.readyState==4 && request.status==200)
        {
            var xml=request.responseXML;
        }
}
function searchsongs(key)
{
    request.onreadystatechange=searchsongsresults;
    request.open("get","ajax/searchpages?key="+key+"&from=songs",true);
    request.send(null);
}
function searchsongsresult(key)
{
    if(request.readyState==4 && request.status==200)
        {
            var xml=request.responseXML;
        }
}
function addtoplaylist(songurl)
{
    request.onreadystatechange=songadded;
    request.open("get","ajax/addtoplaylist.jsp?url="+songurl,true);
    request.send(null);
}
function songadded()
{
    if(request.readyState==4 && request.status==200)
        {
            var json=eval('('+request.responseText+')');
            alert(json.status);
        }
}
function addsong(url,title,artist)
{
    request.onreadystatechange=addsongs;
    request.open("get","ajax/addsong.jsp?url="+url+"&title="+title+"&artist="+artist,true);
    request.send(null);
}
function addsongs()
{
    if(request.readyState==4 && request.status==200)
        {
            var json=eval('('+request.responseText+')');
            alert(json.status);
        }
}
function addpage(name,description)
{
    request.onreadystatechange=addpages;
    request.open("get","ajax/addpage.jsp?name="+name+"&description="+description,true);
    request.send(null);
}
function addpages()
{
    if(request.readyState==4 && reaquest.status==200)
        {
            var json=eval('('+request.responseText+')');
            alert(json.status);
        }
}
function dopost(userid)
{
    request.onreadystatechange=post;
    request.open("post","dopost.jsp?",true);
    var params=document.getElementById("postbox");
    request.header("content-type","application/www-url-encodedform")
    request.send("userid="+userid+"&message="+params.value);
}
function post()
{
    if(request.readyState==4 && request.status==200)
        {
            var json=eval('('+request.responseText+')');
            alert(json.status);
        }
}
function docomment(postid)
{
    request.onreadystatechange=comment;
    request.open("post","docomment.jsp",true);
    var params=document.getElementById(postid+"_commentbox");
    request.header("content-type","application/www-url-encoded");
    request.send("postid="+postid+"&message="+message);
}
function comment()
{
    if(request.readyState==4 && request.status==200)
        {
            var json=eval('('+request.responseText+')');
            alert(json.status);
        }
}
function voteuser(userid)
{
    request.onreadystatechange=voteusr;
    request.open("get","ajax/voteuser.jsp?userid="+userid,true);
    request.send(null);
}
function voteusr()
{
    if(request.readyState==4 && request.status==200)
        {
            var json=eval('('+request.responseText+')');
            alert(json.status);
        }
}
function withdrawuservote(userid)
{
    request.onreadystatechange=withdrawuser;
    request.open("get","ajax/withdrawuser.jsp?userid="+userid,true);
    request.send(null);
}
function withdrawuser()
{
    if(request.readyState==4 && request.status==200)
        {
            var json=eval('('+request.responseText+')');
            alert(json.status);
        }
}
function votepost(postid)
{
    request.onreadystatechange=votepst;
    request.open("get","ajax/votepost.jsp?postid="+postid,true);
    request.send(null);
}
function votepst()
{
    if(request.readyState==4 && request.status==200)
        {
            var json=eval('('+request.responseText+')');
            alert(json.status);
        }
}
function withdrawpostvote(postid)
{
    request.onreadystatechange=withdrawpost;
    request.open("get","ajax/withdrawpost.jsp?postid="+postid,true);
    request.send(null);
}

function withdrawpost()
{
    if(request.readyState==4 && request.status==200)
        {
            var json=eval('('+request.responseText+')');
            alert(json.status);
        }
}

function sendmsguser()
{
    request.onreadystatechange=sendmessage;
    request.open("post","ajax/sendmessage.jsp",true);
    request.setRequestHeader("content-type","application/x-www-form-urlencoded");
    var userid=escape(document.sendmessage.to.value);
    var message=escape(document.sendmessage.msg.value);
    var username=escape(document.sendmessage.msgto.value);
    var parameters="userid="+userid+"&message="+message+"&username="+username;
    request.setRequestHeader("connection","close");
    request.setRequestHeader("content-length",parameters.length);
    request.send(parameters);
}
function sendmessage()
{
     if(request.readyState==4 && request.status==200)
        {
            alert(request.responseText);
            var json=eval('('+request.responseText+')');
            var e=document.getElementById("light3");
            e.style.width=500+'px';
            e.style.height=250+"px";
            e.innerHTML=json.status+'<ul class="roundbuttons sendmessagewidth"><li><input type="button" name="cancel" value="cancel" onClick="document.getElementById("light3").style.display="none";   document.getElementById("fade3").style.display="none";  /></li></ul>';
            
        }
}
function createchat()
{
    var c=document.createElement("div");
    c.id='onlineusers';
    c.style.height=window.innerHeight;
    c.style.width=220+'px';
    c.style.right=0+"px";
    c.style.top=0+"px";
    c.style.position='fixed';
    c.style.border="solid 1px";
    document.body.appendChild(c);
}
function getonlineusers()
{
    
    request1.onreadystatechange=onlinefrnds;
        request1.open("get","ajax/getonlinefrnds.jsp",true);
        request1.send(null);
        
}
function onlinefrnds()
{
    if(request1.readyState==4 && request1.status==200)
        {
            var e= document.getElementById("onlineusers");
            e.innerHTML=request1.responseText;
        }
}
function getusername(userid)
{
    request.onreadystatechange=function(){
        if(request.readystate==4 && request.status==200)
            {
                var json=eval('('+request.responseText+')');
                return json.username;
            }
    };
    request.open("get","getusername.jsp?userid="+userid);
    request.send(null);
}

function createalbum()
{
    if(document.getElementById('albumname').value!=''){
        
    request.onreadystatechange=cretealbum;
    request.open("get","ajax/createalbum.jsp?albumname="+document.getElementById('albumname').value,true);
    request.send(null);
    }
}
function cretealbum()
{
    if(request.readyState==4 && request.status==200)
        {
            var json=eval('('+request.responseText+')');
            alert(json.status);
            document.getElementById('albumname').value='';
            getalbums(json.user);
        }
}
function getalbums(userid)
{
   clearInterval(streamsinterval);
   document.getElementById("streams").innerHTML='';
   document.getElementById("streams").style.height=0+'px';
    request.onreadystatechange=getalbum;
    request.open("get","http://localhost/Fz-test/ajax/getalbum.php?userid="+userid);
    request.send(null);
}
function getalbum()
{
    alert(request.status);
    if(request.readyState==4 && request.status==200)
        {
            var xml=request.responseXML;
            var ids=xml.getElementsByTagName("id");
            var names=xml.getElementsByTagName("name");
            var dates=xml.getElementsByTagName("date");
            var e=document.getElementById('myalbum');
            e.innerHTML='';
            for(var i=0; i<ids.length; i++)
                {
                    var album=document.createElement('div');
                    album.style.width="100px";
                    album.style.height="100px";
                    album.style.float='left';
                    album.innerHTML="<a onclick='getimages("+ids[i].childNodes[0].nodeValue+")'>"+names[i].childNodes[0].nodeValue+"</a>";
                    e.appendChild(album);
                }
                
        }
}

function getimages(albumid)
{
    request.onreadystatechange=images;
    request.open("get","ajax/getimages.jsp?albumid="+albumid,true);
    request.send(null);
}
function images()
{
    if(request.readyState==4 && request.status==200)
        {
            var xml=request.responseXML;
            var ids=xml.getElementsByTagName("id");
            var urls=xml.getElementsByTagName('url');
            var albumids=xml.getElementsByTagName("albumid");
            var albumnames=xml.getElementsByTagName("albumname");
            var userids=xml.getElementsByTagName("userid");
            var votecounts=xml.getElementsByTagName("vote_count");
            var votes=xml.getElementsByTagName("vote");
            var pinnedpeoples=xml.getElementsByTagName("pinnedpeople");
            var pinnedpeoplecounts=xml.getElementsByTagName("pinnedpeople_count");
            var dates=xml.getElementsByTagName("date");
            var e=document.getElementById("myalbum");
            e.innerHTML='<input type="button" value="upload" onclick="uploadimages('+albumids[0].childNodes[0].nodeValue+')" />';
            for(var i=0; i<ids.length; i++)
                {
                 
                    var image=document.createElement('div');
                    image.style.width='200px';
                    image.style.height='220px';
                    image.style.float='left';
                    var options={"id":ids[i].childNodes[0].nodeValue, "url":urls[i].childNodes[0].nodeValue,"albumid":albumids[i].childNodes[0].nodeValue,"albumname":albumnames[i].childNodes[0].nodeValue,"userid":userids[i].childNodes[0].nodeValue,"votecount":votecounts[i].childNodes[0].nodeValue,"vote":votes[i],"pinnedpeople":pinnedpeoples[i],"pinnedpeoplecount":pinnedpeoplecounts[i].childNodes[0].nodeValue,"date":dates[i].childNodes[0].nodeValue};
                    image.innerHTML="<a href='/sn/gallery/gallery.jsp?albumid="+albumids[i].childNodes[0].nodeValue+"#"+ids[i].childNodes[0].nodeValue+"'><img style='float:left' height='200' width='200' src='images/"+urls[i].childNodes[0].nodeValue+"' /></a>";
                    e.appendChild(image);
                }
        }
}
function uploadimages(albumid)
{
    var e=document.getElementById("light4");
    e.innerHTML="<iframe src='ajax/uploadimages.jsp?albumid="+albumid+"' width='600px' height='400px' />";
    e.style.display='block';
    var f=document.getElementById('fade4');
    f.style.display='block';
}
function closeupload(albumid)
{
    getimages(albumid);
    document.getElementById('light4').style.display='none';
    document.getElementById('fade4').style.display='none';
}
function showimage(options)
{
    alert('1');
    var e=document.getElementById("light5_image");
    var e1=document.getElementById("light5");
    e1.style.display='block';
    var f=document.getElementById('fade5');
    f.style.display='block';
    e.innerHTML="<image src='images/"+options.url+"/>";
    var imageinterval=setInterval(function(){
    request.onreadystatechange=getimagecomments;
    request.open("get","getimagecomments.jsp?imageid="+options.id,true);
    request.send(null);
},3000)
}
function getimagecomments()
{
    if(request.readyState==4 && request.status==200)
        {
            var e=document.getElementById("light5_comments");
            var d=document.createElement('div');
            d.style.overflow="scroll";
            d.style.width=624+'px';
            d.style.height=600+'px';
            var xml=request.responseXML;
            var cids=xml.getElementsByTagName("id");
            var userids=xml.getElementsByTagName("userid");
            var comments=xml.getElementsByTagName("comment");
            var votes=xml.getElementsByTagName("vote");
            var votecounts=xml.getElementsByTagName("vote_count");
            var dates=xml.getElementsByTagName("date");
            for(var i=0;i<cids.length; i++)
            {
                var c=document.createElement('div');
                var f=document.createElement('div');
                f.style.width=624+'px';
                f.style.height=20+'px';
                f.innerHTML="<a href=#profile_1?userid="+userids[i].childNodes[0].nodeValue+">"+userids[0].childNodes[0].nodeValue+"</a>";
                c.appendChild(f);
                var g=document.createElement('div');
                g.style.width=624+'px';
                g.innerHTML=comments[i].childNodes[0].nodeValue;
                c.appendChild(g);
                d.appendChild(c);
            }
            e.innerHTML=d.innerHTML;

                
        }
}
function dopost(userid)
{
    var text=document.postform.post.value;
    if(text!='')
        {
           request.onreadystatechange=post;
           request.open("post","ajax/dopost.jsp",true);
           request.setRequestHeader("content-type", "application/x-www-form-urlencoded");
           request.setRequestHeader("connection","close");
           var text1=escape(text);
           var userid1=escape(userid);
           var parameters="userid="+userid1+"&text="+text1;
           request.setRequestHeader("content-length",parameters.length);
           request.send(parameters);
        }
        else
            alert("message cannot be posted blank");
}
function post()
{
    if(request.readyState==4 && request.status==200)
        {
            var json=eval('('+request.responseText+')');
            document.postform.post.value='';
            alert(json.status);
            
        }
}
function docomment(postid)
{
    var text=document.getElementById("commentbox_"+postid);
    if(text!='')
        {
            request.onreadystate=comment;
            request.open("post","docomment.jsp",true);
            request.setRequestHeader("content-type", "application/x-www-form-urlencoded");
            request.setRequestHeader("connection","close");
            var text1=escape(text);
            var postid1=escape(postid);
            var parameters="postid="+postid1+"&text="+text1;
            request.setRequestHeader("content-length",parameters.length);
            request.send(parameters);
        }
}
function comment()
{
    if(request.readyStatus==4 && request.status==200)
        {
            var json=eval('('+request.responseText+')');
            alert(json);
        }
}

function setaspropic(imageid,url,x1,y1,width1,height1)
{
    var image=new Image();
    image.src=url;
    var height2=image.height;
    var width2=image.width;
    var x=(x1/200)*width2;
    var y=(y1/200)*height2;
    var width=(width1/200)*width2;
    var height=(height1/200)*height2;
    request.onreadystatechange=setpropic;
    request.open("get","/sn/ajax/setaspropic.jsp?imageid="+imageid+"&x="+x+"&y="+y+"&width="+width+"&height="+height,true);
    request.send(null)
}
function setpropic()
{
    if(request.readyState==4 && request.status==200)
        {
            var json=eval('('+request.responseText+')');
            alert(json.status);
        }
}

function updatemood(mood)
{
    request.onreadystatechange=setmood;
    request.open("get","ajax/updatemood.jsp?mood="+mood,true);
    request.send(null);
}
function setmood()
{
    if(request.readyState==4 && request.status==200)
        {
            var json=eval('('+request.responseText+')');
            alert(json.status);
        }
}
function setsecondarypic1(imageid)
{
    request.onreadystatechange=updatesecondarypic1;
    request.open("get","ajax/setsp1.jsp?imageid="+imageid,true);
    request.send(null);
}
function updatesecondarypic1()
{
    if(request.readystate==4 && request.status==200)
        {
            var json=eval('('+request.responseText+')');
            alert(json.status);
        }
}
function setsecondarypic2(imageid)
{
    request.onreadystatechange=updatesecondarypic2;
    request.open("get","ajax/setsp2.jsp?imagid="+imageid,true);
    request.send(null);
}
function updatesecondarypic2()
{
    if(request.readystate==4 && request.status==200)
        {
            var json=eval('('+request.responseText+')');
            alert(json.status);
        }
}

function addfavbooks(pageid)
{
    request.onreadystatechange=addbooks;
    request.open("get","ajax/addfavbooks.jsp?pageid="+pageid,true);
    request.send(null);
}
function addbooks()
{
    if(request.readystate==4 && request.status==200)
        {
            var json=eval('('+request.responseText+')');
            alert(json.status);
        }
}
function addfavmusics(pageid)
{
    request.onreadystatechange=addmusics;
    request.open("get","ajax/addfavmusics.jsp?pageid="+pageid,true);
    request.send(null);
}
function addmusics()
{
    if(request.readystate==4 && request.status==200)
        {
            var json=eval('('+request.responseText+')');
            alert(json.status);
        }
}
function addfavmovies(pageid)
{
    request.onreadystatechange=addmovies;
    request.open("get","ajax/addfavmovies.jsp?pageid="+pageid,true);
    request.send(null);
}
function addmovies()
{
    if(request.readystate==4 && request.status==200)
        {
            var json=eval('('+request.responseText+')');
            alert(json.status);
        }
}
function addfavcelebrities(pageid)
{
    request.onreadystatechange=addcelebrities;
    request.open("get","ajax/addfavcelebrities.jsp?pageid="+pageid,true);
    request.send(null);
}
function addcelebrities()
{
    if(request.readystate==4 && request.status==200)
        {
            var json=eval('('+request.responseText+')');
            alert(json.status);
        }
}
function addfavgames(pageid)
{
    request.onreadystatechange=addgames;
    request.open("get","ajax/addfavgames.jsp?pageid="+pageid,true);
    request.send(null);
}
function addgames()
{
    if(request.readystate==4 && request.status==200)
        {
            var json=eval('('+request.responseText+')');
            alert(json.status);
        }
}
function addfavsports(pageid)
{
    request.onreadystatechange=addbooks;
    request.open("get","ajax/addfavsports.jsp?pageid="+pageid,true);
    request.send(null);
}
function addsports()
{
    if(request.readystate==4 && request.status==200)
        {
            var json=eval('('+request.responseText+')');
            alert(json.status);
        }
}
function addfavothers(pageid)
{
    request.onreadystatechange=addbooks;
    request.open("get","ajax/addfavothers.jsp?pageid="+pageid,true);
    request.send(null);
}
function addothers()
{
    if(request.readystate==4 && request.status==200)
        {
            var json=eval('('+request.responseText+')');
            alert(json.status);
        }
}
function deletepost(postid)
{
    request.onreadystatechange=delpost;
    request.open("get","deletepost.jsp?postid="+postid,true);
    request.send(null);
}
function delpost()
{
    if(request.readyState==4 && request.status==200)
        {
            var json=eval('('+request.responseText+')');
            alert(json.status);
        }
}
function deletecomment(commentid)
{
    request.onreadystatechange=delcomment;
    request.open("get","deletecomment.jsp?commentid="+commentid,true);
    request.send(null);
}
function delcomment()
{
    if(request.readyState==4 && request.status==200)
        {
            var json=eval('('+request.responseText+')');
            alert(json.status);
        }
}
function formatdate()
{
    var dates=document.getElementsByName("date");
    for (var i=0;i<dates.length;i++)
        {
            var date=new date(dates[i].innerHTML);
            alert(date.toLocaleString());
        }
}
function doimagecomment(imageid)
{
    var e=document.getElementById(imageid+"_comment");
    if(e.value!=''){
    request.onreadystatechange=commentimage;
    request.open("post","/sn/ajax/doimagecomment.jsp",true);
    var imageid1=escape(imageid);
    var comment=escape(e.value);
    request.setRequestHeader("content-type", "application/x-www-form-urlencoded");
    request.setRequestHeader("connection","close");
    var parameters="imageid="+imageid+"&comment="+comment;
    request.setRequestHeader("content-length",parameters.length);
    request.send(parameters);
    }
    
}
function commentimage()
{
    if(request.readyState==4 && request.status==200)
        {
            var json=eval('('+request.responseText+')');
            alert(json.status);
        }
}
function deleteimagecomment(commentid)
{
    request.onreadystatechange=delimgcomment;
    request.open("get","/sn/ajax/deleteimgcomment.jsp?commentid="+commentid,true);
    request.send(null);
}
function delimgcomment()
{
    if(request.readyState==4 && request.status==200)
        {
            var json=eval('('+request.responseText+')');
            alert(json.status);
        }
}
function deleteimage(imageid)
{
    request.onreadystatechange=delimage;
    request.open("get","deleteimage.jsp?image="+imageid,true);
    request.send(null);
}
function delimage()
{
    if(request.readyState==4 && request.status==200)
        {
            var json=eval('('+request.responseText+')');
            alert(json.status);
        }
}
function addpin(imageid)
{
    var flag=0;
    var d=document.getElementById(imageid+"_pinuser");
    var e=document.getElementById(imageid+"_pinpeople");
    if(d.value!='')
        {
            if(e.value!='')
                {
                    var f=e.value.split(",");
                    for(var i=0;i<f.length;i++)
                        {
                            if(f[i]==d.value)
                                flag=1;
                        }
                        if(flag!=1)
                            {
                            e.value=e.value+","+d.value;
                            d.value='';
                            }
                        else
                            {
                            alert("already added to list");
                            d.value='';
                            }
                }
                else
                    {
                    e.value=d.value;
                    d.value='';
                    }
        }
}

function pinpeople(imageid)
{
    var e=document.getElementById(imageid+"_pinpeople");
    if(e.value!='')
        {
            request.onreadystatechange=addpinpeople;
            request.open("post","/sn/ajax/addpin.jsp",true);
            request.setRequestHeader("content-type", "application/x-www-form-urlencoded");
            request.setRequestHeader("connection","close");
            var imageid1=escape(imageid);
            var people=escape(e.value);
            var parameters="imageid="+imageid1+"&people="+people;
            request.setRequestHeader("content-length",parameters.length);
            request.send(parameters);
    
        }
}
function addpinpeople()
{
    if(request.readyState==4 && request.status==200)
        {
            var json=eval('('+request.responseText+')');
            alert(json.status);
            window.location=json.href;
            var e=document.getElementById(imageid+"_pinpeople");
            e.value='';
        }
}
function removepin(imageid,userid)
{
    request.onreadystatechange=removepinpeople;
    request.open("get","/sn/ajax/removepin.jsp?imageid="+imageid+"&userid="+userid);
    request.send(null);
}
function removepinpeople()
{
    if(request.readyState==4 && request.status==200)
        {
            var json=eval('('+request.rsponsetext+')');
            alert(json.status);
        }
}

function writeslambook(userid)
{
    request.onreadystatechange=writeslam;
    request.open("post","/sn/ajax/updateslambook.jsp",true);
    var userid=escape(userid);
    var name=escape(document.aboutme.name.value);
    var bday=escape(document.aboutme.bday.value);
    var email=escape(document.aboutme.email.value);
    var phone=escape(document.aboutme.phone.value);
    var ambition=escape(document.aboutme.ambition.value);
    var hobby=escape(document.aboutme.hobby.value);
    var believe=escape(document.aboutme.believe.value);
    var friendship=escape(document.aboutme.friendship.value);
    var love=escape(document.aboutme.love.value);
    var hate=escape(document.aboutme.hate.value);
    var philosophy=escape(document.aboutme.philosophy.value);
    var film=escape(document.fav.film.value);
    var music=escape(document.fav.music.value);
    var actor=escape(document.fav.actor.value);
    var actress=escape(document.fav.actress.value);
    var sports=escape(document.fav.sports.value);
    var sportsman=escape(document.fav.sportsman.value);
    var dress=escape(document.fav.dress.value);
    var food=escape(document.fav.food.value);
    var place=escape(document.fav.place.value);
    var friends=escape(document.fav.friends.value);
    var feel=escape(document.fav.feel.value);
    var advice=escape(document.advice.advice.value);
    var parameters="userid="+userid+"&Name="+name+"&Born On="+bday+"&Email="+email+"&Ring Me="+phone+"&Ambition="+ambition+"&My Hobby="+hobby+"&I Believed in="+believe+"&About Friendship="+friendship+"&About Love="+love+"&I hate="+hate+"&My Philosophy="+philosophy+"&Fav Film="+film+"&Fav Music"+music+"&Fav Actor="+actor+"&Fav Actress"+actress+"&Fav Sports="+sports+"&Fav Sportsman="+sportsman+"&Fav Dress="+dress+"&Fav Food="+food+"&Fav Place="+place+"&Close Friends="+friends+"&I Feel About You="+feel+"&My Advice for You="+advice;
    request.setRequestHeader("content-type", "application/x-www-form-urlencoded");
    request.setRequestHeader("connection","close");
    request.setRequestHeader("content-length",parameters.length);
    request.send(parameters);       
}
function writeslam()
{
    if(request.readyState==4 && request.status==200)
        {
            var json=eval('('+request.responseText+')');
            alert(json.status);
            
        }
}
function writediary(date)
{
    request.onreadystatechange=wrtediary;
    request.open("post","/sn/ajax/updatediary.jsp","true");
    var notes=escape(document.diary.notes.value);
    var parameters="date="+date+"&notes="+notes;
    request.setRequestHeader("content-type", "application/x-www-form-urlencoded");
    request.setRequestHeader("connection","close");
    request.setRequestHeader("content-length",parameters.length);
    request.send(parameters); 
}
function wrtediary()
{
    if(request.readyState==4 && request.status==200)
        {
            var json=eval('('+request.responseText+')');
            alert(json.status);
            
        }
}
