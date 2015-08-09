/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hangpeer;
import javax.servlet.http.*;


/**
 *
 * @author abdulnizam
 */
public class GetConnection implements javax.servlet.http.HttpSessionListener{
    public  void sessionCreated(HttpSessionEvent hse)
    {
      try
      {
          javax.sql.DataSource datasource = (javax.sql.DataSource) hse.getSession().getServletContext()
          .getAttribute("datasource");
          hangpeer.NewMain obj=new NewMain(datasource.getConnection());
          obj.start();
          hse.getSession().setAttribute("object", obj);
           System.out.println("session initialized");
      }
      catch(java.sql.SQLException se)
      {
          System.out.println(se);
      }
                
    }
    public void sessionDestroyed(HttpSessionEvent hse)
    {
           try{
               hangpeer.NewMain obj=(hangpeer.NewMain)hse.getSession().getAttribute("object");
            obj.stop();
            hse.getSession().removeAttribute("object");
            if(hse.getSession().getAttribute("userid")!=null)
            {
                java.util.List onlineusers=(java.util.List)hse.getSession().getServletContext().getAttribute("onlineusers");
                if(onlineusers.contains(hse.getSession().getAttribute("userid")))
                    onlineusers.remove(hse.getSession().getAttribute("userid"));
                System.out.print("session destroyed:"+onlineusers);
                hse.getSession().getServletContext().setAttribute("onlineusers", onlineusers);
            }
            System.out.println("session destroyed");
           }
           catch(Exception se)
           {
               System.out.println(se);
           }
        
    }

}
