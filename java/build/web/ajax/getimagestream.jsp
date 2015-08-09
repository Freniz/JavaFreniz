<?xml version="1.0" encoding="UTF-8" ?>
<%@page contentType="text/xml" pageEncoding="UTF-8"%>
<%@page import="hangpeer.NewMain,java.util.*" %>
<% NewMain obj=(NewMain)session.getAttribute("object");
    HashMap images=obj.getImageStreams((String)session.getAttribute("userid"),(List)session.getAttribute("friends") ,Long.parseLong(request.getParameter("from")));
    List keyset=new ArrayList(images.keySet());
    Collections.sort(keyset);
    %>
    <images>
        <% for(int i=keyset.size()-1;i>=0;i++){
            HashMap image=(HashMap)images.get(keyset.get(0));
            %>
            <post>
                <id><%out.print(keyset.get(i));%></id>
                <url><%out.print(image.get("url"));%></url>
                <userid><%out.print(image.get("userid"));%></userid>
                <albumid><%out.print(image.get("albumid"));%></albumid>
                <albumname><%out.print(image.get("albumname"));%></albumname>
                <vote><%out.print(image.get("vote"));%></vote>
                <vote_count><%out.print(((List)image.get("vote")).size());%></vote_count>
                <pinnedpeople><%out.print(image.get("pinnedpeople"));%></pinnedpeople>
                <pinnedpeople_count><%out.print(((List)image.get("pinnedpeople")).size());%></pinnedpeople_count>
                <date><%out.print(image.get("date"));%></date>
             </post>
            <%image=null;}images=null;%>
     </images>