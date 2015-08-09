<%-- 
    Document   : memorycheck
    Created on : Oct 20, 2011, 4:06:29 PM
    Author     : abdulnizam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
<%           
               
out.print((Runtime.getRuntime().totalMemory()/1024)+"\t"+(Runtime.getRuntime().freeMemory()/1024)+"\t"+new java.util.Date()+"<br>");
Runtime.getRuntime().gc();
System.gc();
out.print((Runtime.getRuntime().totalMemory()/1024)+"\t"+(Runtime.getRuntime().freeMemory()/1024)+"\t"+new java.util.Date()+"<br>");
 blog.GarbageCollection GarbageC = new blog.GarbageCollection();
      Runtime rtime = Runtime.getRuntime();
      rtime.gc();
      long present = rtime.freeMemory()/1024;
      out.println("At program start we have : " + present + " bytes");
      GarbageC.useMemory(session);
      long present1 = rtime.freeMemory()/1024;
      out.println("After running the program, we have :  " + present1 + " bytes");
      rtime.gc();
      long present2 = rtime.freeMemory()/1024;
      out.println("After collecting garbage we have :    " + present2 + " bytes");
      long freedMem = present2 - present1;
      out.println("Garbage collection freed :    " + freedMem + " bytes");
   
   %>
    
        
    </body>
</html>
        