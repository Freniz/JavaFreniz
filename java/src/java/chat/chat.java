/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;
import javax.servlet.http.*;
import java.sql.*;
import java.util.*;
import org.json.simple.*;
/**
 *
 * @author abdulnizam
 */
public class chat {
    HttpSession session;
    Statement st;
    public chat(HttpSession session1)
    {
     try
     {
         session=session1;
         Class.forName("com.mysql.jdbc.Driver");
         Connection con=DriverManager.getConnection("jdbc:mysql://localhost/test","nizam","ajith786");
         st=con.createStatement();
     }
     catch(SQLException se)
     {
         System.out.println(se);
     }
     catch(ClassNotFoundException ce)
     {
         System.out.println(ce);
     }
    }
    public JSONObject chatHeartbeat()
    {
        JSONObject json=new JSONObject();
        try
        {
        String sql="select * from chat where (chat.to = '"+session.getAttribute("userid") +"' AND recd = 0) order by id ASC";
        ResultSet rs=st.executeQuery(sql);
        List items=new ArrayList();
        while(rs.next())
        {
            if(((HashMap)session.getAttribute("openChatBoxes")).get(rs.getString("from"))==null && ((HashMap)session.getAttribute("chatHistory")).get(rs.getString("from"))!=null)
            {
                items=(List)((HashMap)session.getAttribute("cahtHistory")).get(rs.getString("from"));
            }
            String message=this.sanitize(rs.getString("message"));
            HashMap item=new HashMap();
            item.put("s", "0");
            item.put("f", rs.getString("from"));
            item.put("m", message);
            items.add(item);
            if(((HashMap)session.getAttribute("chatHistory")).get(rs.getString("from"))==null)
            {
                HashMap chathistory=(HashMap)session.getAttribute("chatHistory");
                chathistory.put(rs.getString("from"), new ArrayList());
                session.setAttribute("chatHistory", chathistory);
               
            }
            HashMap chathistory=(HashMap)session.getAttribute("chatHistory");
            List from=(List)chathistory.get(rs.getString("from"));
            from.add(item);
            chathistory.put("from",from);
            session.setAttribute("chatHistory", chathistory);
            
            
            
            HashMap openchatbox=(HashMap)session.getAttribute("openChatBoxes");
            openchatbox.put(rs.getString("from"), rs.getTimestamp("sent"));
            session.setAttribute("openChatBoxes", openchatbox);
        } 
            
        if(((HashMap)session.getAttribute("openChatBoxes")).isEmpty())
            {
                HashMap openchatboxes=(HashMap)session.getAttribute("openChatBoxes");
                List keyset=new ArrayList(openchatboxes.keySet());
                for(int i=0;i<keyset.size();i++)
                {
                    long now=System.currentTimeMillis()-((java.util.Date)openchatboxes.get(keyset.get(i))).getTime();
                    String message1="sent at "+(java.util.Date)openchatboxes.get(keyset.get(i));
                    if(now>180)
                    {
                        HashMap item1=new HashMap();
                        item1.put("s", "2");
                        item1.put("f", keyset.get(i));
                        item1.put("m", message1);
                        items.add(item1);
                        if(((HashMap)session.getAttribute("chatHistory")).get(keyset.get(i))==null)
                        {
                            HashMap chatHistory=(HashMap)session.getAttribute("chatHistory");
                            chatHistory.put(keyset.get(i), new ArrayList());
                            session.setAttribute("chatHistory", chatHistory);
                        }
                        HashMap chathistory1=(HashMap)session.getAttribute("chatHistory");
                        List from1=(List)chathistory1.get(keyset.get(i));
                        from1.add(item1);
                        chathistory1.put("from",from1);
                        session.setAttribute("chatHistory", chathistory1);
            
                        
                    }
                }
            }
            String sql1 = "update chat set recd = 1 where chat.to = '"+session.getAttribute("userid") +"' and recd = 0";
            st.executeUpdate(sql1);
            json.put("items", items);
            return json;
        
        }
        catch(SQLException se)
        {
            System.out.println(se);
            json.clear();
            return json;
        }
    }
    public List chatBoxSession(Object chatbox)
    {
        if(((HashMap)session.getAttribute("chatHistory")).get(chatbox)!=null)
        {
            return((List)((HashMap)session.getAttribute("chatHistory")).get(chatbox));
        }
        return new ArrayList();
    }
    
    
    public JSONObject startChatSession()
    {
        List items=new ArrayList();
        HashMap openchatbox=(HashMap)session.getAttribute("openChatBoxes");
        JSONObject json=new JSONObject();
        if(!openchatbox.isEmpty())
        {
            List keyset=new ArrayList(openchatbox.keySet());
            for(int i=keyset.size()-1;i>=0;i--)
                items.addAll(this.chatBoxSession(keyset.get(i)));
            json.put("username", (String)session.getAttribute("userid"));
            json.put("items",items);
        }
        return json;
    }
    
    public void closeChat(String chatbox)
    {
        HashMap openchatbox=(HashMap)session.getAttribute("openChatBoxes");
        openchatbox.remove(chatbox);
        session.setAttribute("openchatBoxes", openchatbox);
    }
    
    
    public void sendChat(String message, String to)
    {
        String from=(String)session.getAttribute("userid");
        
        HashMap openchatbox=(HashMap)session.getAttribute("openChatBoxes");
        openchatbox.put(to, new java.util.Date());
        session.setAttribute("openChatBoxes", openchatbox);
        message=this.sanitize(message);
        if(((HashMap)session.getAttribute("chatHistory")).get(to)==null)
        {
            HashMap openchatbox1=(HashMap)session.getAttribute("chatHistory");
            openchatbox1.put(to, new ArrayList());
            session.setAttribute("chatHistory", openchatbox1);
        }
        
        HashMap chathistory=(HashMap)session.getAttribute("chatHistory");
        List items= (List) chathistory.get(to);
        HashMap item=new HashMap();
        item.put("s", "1");
        item.put("f", to);
        item.put("m", message);
        items.add(item);
        
        try
        {
            String sql="insert into chat (chat.from,chat.to,message,sent) values ('"+from+"', '"+to+"','"+message+"',NOW())";
            st.executeUpdate(sql);
    
        }
        catch(SQLException se)
        {
            System.out.println(se);
        }
    }
    public String sanitize(String text) {
	text = text.replaceAll("\n\r","\n");
	text = text.replaceAll("\r\n","\n");
	text = text.replace("\n","<br>");
	return text;
}
    
}
