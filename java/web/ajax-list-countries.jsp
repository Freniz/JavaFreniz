
<%@page contentType="text/html" import="java.sql.*" pageEncoding="UTF-8"%>
<%
    try{
        Class.forName("com.mysql.jdbc.Driver").newInstance();
           Connection con=DriverManager.getConnection("jdbc:mysql://localhost/test2","nizam","ajith786");
              if(request.getParameter("getCountriesByLetters")!=null && request.getParameter("letters")!=null){
               String letters=request.getParameter("letters");
               Statement st=con.createStatement();
               ResultSet rs=st.executeQuery("select id,countryname from ajax_countries where countryName like '"+letters+"%'");
               while(rs.next())
                   out.println(rs.getString("id")+"###"+rs.getString("countryname")+"|");
              }
    }
    catch(Exception e){
        System.out.println(e);
    }
%>