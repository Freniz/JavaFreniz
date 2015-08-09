
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*,hangpeer.NewMain"%>
<%  List onlineusers=null;
    if(session.getAttribute("userid")!=null){
    onlineusers=(List)application.getAttribute("onlineusers");
    List onlinefrnds= new ArrayList(onlineusers);
    onlinefrnds.retainAll((List)session.getAttribute("friends"));
%>
<div style="width:220px;float:right">
    <div id="onlinefrnds_display" style="width:20px; float: left">
        <%out.print(onlinefrnds.size()); %>
    </div>
    <div id="onlinefrnds_list" style="width:200px; float: left">
        <ul style="display: block; padding-top: 5px" >
            <%for(int i=0;i<onlinefrnds.size(); i++){ %>
            <li style="display:block; padding: 5px; overflow: hidden;" >
                <a href="javascript:void(0);" onclick="javascript:chatWith('<%out.print(onlinefrnds.get(i)); %>')"><%out.print(((HashMap)((HashMap)application.getAttribute("users")).get(onlinefrnds.get(i))).get("username")); %></a>
            </li>
            <%}%>
        </ul>
    </div>
</div>
        <%}%>