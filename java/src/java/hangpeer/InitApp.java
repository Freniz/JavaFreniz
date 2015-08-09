/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hangpeer;
import javax.servlet.*;
import java.sql.*;
import java.util.*;
import java.io.*;
/**
 *
 * @author abdulnizam
 */
public class InitApp implements  ServletContextListener{
    Connection con=null;
        public void contextInitialized
  (ServletContextEvent sce){
            try
            {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                con=DriverManager.getConnection("jdbc:mysql://localhost/test2","nizam","ajith786");
                NewMain obj=new NewMain(con);
                PreparedStatement pst=con.prepareStatement("select userid,email from user_info where userid!='default'");
                Set pages=new java.util.TreeSet();
                List userids=new ArrayList();
                List emailids=new ArrayList();
                ResultSet rs=pst.executeQuery();
                while(rs.next())
                {
                    emailids.add(rs.getString("email"));
                    userids.add(rs.getString("userid"));
                    
                }
                pst=con.prepareStatement("select pageid from pages where pageid!='default'");
                rs=pst.executeQuery();
                while(rs.next())
                    pages.add(rs.getString("pageid"));
                sce.getServletContext().setAttribute("emailids", emailids);
                sce.getServletContext().setAttribute("users", obj.getMiniProfile(userids));
                sce.getServletContext().setAttribute("pages", obj.getMiniPage(new java.util.ArrayList(pages)));
                System.out.print("context initialised :\n"+obj.getMiniProfile(userids)+"\n"+obj.getMiniPage(new java.util.ArrayList(pages))+"\n"+sce.getServletContext().getAttribute("onlineusers")+"\n___________________________________________\n");
                
            }
            catch(IllegalAccessException ie)
        {
            System.out.println(ie);
        }
        catch(InstantiationException ie)
        {
            System.out.println(ie);
        }
        catch(ClassNotFoundException ce)
        {
            System.out.println(ce);
        }
        catch(SQLException se)
        {
            System.out.println(se);
        }
        }
        public void contextDestroyed(ServletContextEvent
 sce){
            try{
            con.close();
            sce.getServletContext().removeAttribute("users");

 }
        catch(SQLException se)
        {
            System.out.println(se);
        }
    }
}
