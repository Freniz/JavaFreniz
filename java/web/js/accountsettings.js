
var request=new createXMLHttpRequest();
    
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
function basicaccount()
{
    
     var fname = document.getElementById('fname');
     var lname = document.getElementById('lname');
     var bdm = document.getElementById('bdm');
     var bdd = document.getElementById('bdd');
     var bdy = document.getElementById('bdy');
     var sex = document.getElementById('sex');
     var status = document.getElementById('status');
     var religious = document.getElementById('religious');
     
     var ccity = document.getElementById('ccity');
     var htown = document.getElementById('htown');
    
     if(fname.value != '' && lname.value != '' && bdm.value != -1 && bdd.value != -1 && bdy.value != -1 && sex.value != 0 && status.value != '' && religious.value != '' && ccity.value != '' && htown.value != '')
         {
            
             var sex1;
             if(sex.value == 1)
                 sex1 = "female";
             else
                 sex1 = "male";
             request.onreadystatechange = basicaccountsettings;
             request.open("get","ajax/updatebasicinfo.jsp?fname="+fname.value+"&lname="+lname.value+"&bdm="+bdm.value+"&bdd="+bdd.value+"&bdy="+bdy.value+"&sex="+sex1+"&status="+status.value+"&religious="+religious.value+"&ccity="+ccity.value+"&htown="+htown.value,true);
             request.send(null);
         }
         
 }
 function basicaccountsettings()
 {
    
     if(request.readyState==4 && request.status==200)
         {
             
             var json = eval('('+request.responseText+')');
             alert(json.status);
         }
 }
 


function basiceducation()
 {
    
     
     var employer = document.getElementById("employer");
    
     if(employer.value != '')
         {
            
             request.onreadystatechange = educationdetails;
            
             request.open("get","ajax/educationinfo.jsp?employer="+employer.value,true);
             request.send(null);
             
         }
 }
 
function educationdetails()
{
    
    if(request.readyState==4 && request.status==200)
        {
            var jason = eval('('+ request.responseText+')');
            alert(jason.status);
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
    
    if(request.readyState==4 && request.status==200)
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
    
    if(request.readyState==4 && request.status==200)
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
    if(request.readyState==4 && request.status==200)
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
    
    if(request.readyState==4 && request.status==200)
        {
            var json=eval('('+request.responseText+')');
            alert(json.status);
        }
}
function addfavgames(pageid)
{
    request.onreadytatechange=addgames;
    request.open("get","ajax/addfavgames.jsp?pageid="+pageid,true);
    request.send(null);
}
function addgames()
{
    if(request.readyState==4 && request.status==200)
        {
            var json=eval('('+request.responseText+')');
            alert(json.status);
        }
}
function addfavsports(pageid)
{
    request.onreadystatechange=addsports;
    request.open("get","ajax/addfavsports.jsp?pageid="+pageid,true);
    request.send(null);
}
function addsports()
{
    if(request.readyState==4 && request.status==200)
        {
            var json=eval('('+request.responseText+')');
            alert(json.status);
        }
}
function addfavothers(pageid)
{
    request.onreadystatechange=addothers;
    request.open("get","ajax/addfavothers.jsp?pageid="+pageid,true);
    request.send(null);
}
function addothers()
{
    if(request.readyState==4 && request.status==200)
        {
            var json=eval('('+request.responseText+')');
            alert(json.status);
        }
}
function addlanguages(pageid)
{
    
    request.onreadystatechange=addlanguage;
    request.open("get","ajax/addlanguage.jsp?pageid="+pageid,true);
    request.send(null);
}
function addlanguage()
{
    
    
    if(request.readyState==4 && request.status==200)
        {
            var json=eval('('+request.responseText+')');
            alert(json.status);
        }
}
function addschools(pageid)
{
   
    request.onreadystatechange=addschool;
    request.open("get","ajax/addschool.jsp?pageid="+pageid,true);
    request.send(null);
}
function addschool()
{
    
    if(request.readyState==4 && request.status==200)
        {
            var json=eval('('+request.responseText+')');
            alert(json.status);
        }
}

function addcolleges(pageid)
{
   
    request.onreadystatechange=addcollege;
    request.open("get","ajax/addcollege.jsp?pageid="+pageid,true);
    request.send(null);
}
function addcollege()
{
    
    if(request.readyState==4 && request.status==200)
        {
            var json=eval('('+request.responseText+')');
            alert(json.status);
        }
}
