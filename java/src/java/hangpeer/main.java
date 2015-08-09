/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hangpeer;

/**
 *
 * @author abdulnizam
 */
import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.regex.*;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.*;
import compress.MyImageWriteParam;
import javax.servlet.http.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;


public class main extends Thread {
    Statement st;
    public main()
    {
        try
{
    Class.forName("com.mysql.jdbc.Driver").newInstance();
    Connection con=DriverManager.getConnection("Jdbc:Mysql://localhost/test1","nizam","ajith786");
    st=con.createStatement();
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
    public boolean validate(String userid, String pass)
    {
        try{
    ResultSet rs=st.executeQuery("select userid,pass from userstable where userid='"+userid+"'");
    if(rs.next())
        {
        if(pass.equals(rs.getString("pass")))
            {
        return true;
        }
        else
            return false;
    }
    else
        return false;
}
catch(SQLException se)
{
System.out.println(se);
}
    return false;
    }

    public List getUserDetails(String userid)
    {
        List ud=new ArrayList();

        try
        {
            ResultSet rs=st.executeQuery("select fname,lname,dob,sex,school,email,hometown,currentcity,language,rstatus,employer,religion,myphilosophy,state,country,propic,pinnedpic,books,musics,movies,celebrities,games,sports,other from user_info where userid='"+userid+"'");
            if(rs.next())
            {
                ud.add(rs.getString("fname"));
                ud.add(rs.getString("lname"));
                ud.add(rs.getDate("dob"));
                ud.add(rs.getString("sex"));
                List schools=new ArrayList();
                String s=rs.getString("school");
                if(s!=null)
                ud.add(Arrays.asList(s.split(",")));
                else
                    ud.add(new ArrayList());
                ud.add(rs.getString("email"));
                ud.add(rs.getString("hometown"));
                ud.add(rs.getString("currentcity"));
                s=rs.getString("language");
                if(s!=null)
                    ud.add(Arrays.asList(s.split(",")));
                else
                    ud.add(new ArrayList());
                ud.add(rs.getString("rstatus"));
                s=rs.getString("employer");
                if(s!=null)
                    ud.add(Arrays.asList(s.split(",")));
                else
                    ud.add(new ArrayList());
                ud.add(rs.getString("religion"));
                ud.add(rs.getString("myphilosophy"));
                ud.add(rs.getString("state"));
                ud.add(rs.getString("country"));
                ud.add(rs.getString("propic"));
                s=rs.getString("pinnedpic");
                if(s!=null)
                    ud.add(Arrays.asList(s.split(",")));
                else
                    ud.add(new ArrayList());
                s=rs.getString("books");
                System.out.println(s);
                if(s!=null)
                    ud.add(Arrays.asList(s.split(",")));
                else
                    ud.add(new ArrayList());
                s=rs.getString("musics");
                if(s!=null)
                    ud.add(Arrays.asList(s.split(",")));
                else
                    ud.add(new ArrayList());
                s=rs.getString("movies");
                if(s!=null)
                    ud.add(Arrays.asList(s.split(",")));
                else
                    ud.add(new ArrayList());
                s=rs.getString("celebrities");
                if(s!=null)
                    ud.add(Arrays.asList(s.split(",")));
                else
                    ud.add(new ArrayList());
                s=rs.getString("games");
                if(s!=null)
                    ud.add(Arrays.asList(s.split(",")));
                else
                    ud.add(new ArrayList());
                s=rs.getString("sports");
                if(s!=null)
                    ud.add(Arrays.asList(s.split(",")));
                else
                    ud.add(new ArrayList());
                s=rs.getString("other");
                if(s!=null)
                    ud.add(Arrays.asList(s.split(",")));
                else
                    ud.add(new ArrayList());
                
            }
            return ud;
        }
        catch(SQLException se)
        {
            System.out.println(se);

            return ud;
        }
    }

    
    public List[] getFriendsAndVote(String userid)
    {
        List[] friends_vote= new ArrayList[2];
        try
        {
            ResultSet rs=st.executeQuery("select friendlist,vote from friends_vote where userid='"+userid+"'");
            if(rs.next())
            {
                String a=rs.getString("friendlist");
                if(a!=null)
                {
                    String a1[] = a.split(",");
                    friends_vote[1]= Arrays.asList(a1);
                }
                else
                    friends_vote[1]=new ArrayList();
                String v = rs.getString("vote");
                if(v!=null)
                {
                    String v1[]=v.split(",");
                    friends_vote[2]= Arrays.asList(v1);
                }
                else
                    friends_vote[2]=new ArrayList();

                return friends_vote;
            }
            return null;
        }
        catch(SQLException se)
        {
            System.out.println(se);
        }
        return null;
    }







    public boolean doPost(String suserid,String ruserid,String status)
    {
        try
        {
            st.executeUpdate("insert into status (suserid,ruserid,status,date) values('"+suserid+"','"+ruserid+"','"+status+"',now())");
            return true;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            return false;
        }
    }

    public boolean doComment(String statusid,String userid,String comment)
    {
        try
        {
            st.executeUpdate("insert into comment (statusid,userid,comment,date) values('"+statusid+"','"+userid+"','"+comment+"',now())");
            return true;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            return false;
        }
    }

    public boolean deletePost(String statusid)
    {
        try
        {
            st.executeUpdate("delete from status where statusid='"+statusid+"'");
            return true;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            return false;
        }
    }

    public boolean deleteComment(String commentid)
    {
        try
        {
            st.executeUpdate("delete from comment where commentid='"+commentid+"'");
            return true;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            return false;
        }
    }

    public List getUsersPost(String userid)
    {
        List s=new ArrayList();
        try
        {
            ResultSet rs=st.executeQuery("select statusid,suserid,status,vote,date from status where ruserid='"+userid+"' order by date desc");
            while(rs.next())
            {
                List s1=new ArrayList();
                s1.add(rs.getString("statusid"));
                s1.add(rs.getString("suserid"));
                s1.add(rs.getString("status"));
                String vote=rs.getString("vote");
                if(vote!=null)
                    s1.add(Arrays.asList(vote.split(",")));
                else
                    s1.add(new ArrayList());
                s1.add(rs.getTimestamp("date").toGMTString());
                s.add(s1);
            }
            return s;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            return null;
        }
    }

    public List getComments(String statusid)
    {
        List c=new ArrayList();
        try
        {
            ResultSet rs=st.executeQuery("select commentid,userid,comment,vote,date from comment where statusid='"+statusid+"' order by date asc");
            while(rs.next())
            {
                List c1=new ArrayList();
                c1.add(rs.getString("commentid"));
                c1.add(rs.getString("userid"));
                c1.add(rs.getString("comment"));
                String vote=rs.getString("vote");
                if(vote!=null)
                    c1.add(Arrays.asList(vote.split(",")));
                else
                    c1.add(new ArrayList());
                c1.add(rs.getDate("date"));
                c.add(c1);
            }
            return c;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            return null;
        }
    }



    public List getPost(String statusid)
    {
        List status=new ArrayList();
        try
        {
            ResultSet rs=st.executeQuery("select * from status where statusid='"+statusid+"'");
            if(rs.next())
            {
                status.add(rs.getString("suserid"));
                status.add(rs.getString("status"));
                String vote=rs.getString("vote");
                if(vote!=null)
                    status.add(Arrays.asList(vote.split(",")));
                else
                    status.add(new ArrayList());
                status.add(rs.getDate("date"));
                return status;
            }
            return null;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            return null;
        }
    }

    public boolean voteStatus(String statusid,List vote)
    {
        try
        {
            st.executeUpdate("update status set vote='"+vote+"' where statusid='"+statusid+"'");
            return true;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            return false;
        }
    }

    public boolean voteComment(String commentid,List vote)
    {
        try
        {
            st.executeUpdate("update comment set vote='"+vote+"' where statusid='"+commentid+"'");
            return true;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            return false;
        }
    }

    public boolean voteUser(String userid,List vote)
    {
        try
        {
            st.executeUpdate("update friends_vote set vote='"+vote+"' where userid='"+userid+"'");
            return true;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            return false;
        }
    }

    public boolean voteImage(String userid,List vote)
    {
        try
        {
            st.executeUpdate("update image_vote set vote='"+vote+"' where userid='"+userid+"'");
            return true;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            return false;
        }
    }

    public boolean voteImageComment(String userid,List vote)
    {
        try
        {
            st.executeUpdate("update image_Comments set vote='"+vote+"' where userid='"+userid+"'");
            return true;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            return false;
        }
    }


    public boolean sentMessage(String suserid,String ruserid,String message)
    {
        try
        {
            st.executeUpdate("insert into message (suserid,ruserid,message,date,read1) values('"+suserid+"','"+ruserid+"','"+message+"',now(),'0')");
            return true;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            return false;
        }
    }
    public boolean setRead(String messageid)
    {
        try
        {
            st.executeUpdate("update message set read1='1'");
            return true;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            return false;
        }
    }
    public List getUserMessages(String userid)
    {
        try{
            List messages=new ArrayList();
            ResultSet rs=st.executeQuery("select suerid,message,date,read1 where ruserid='"+userid+"' order by date desc");
            while(rs.next())
            {
                List msg1=new ArrayList();
                msg1.add(rs.getString("suserid"));
                msg1.add(rs.getString("message"));
                msg1.add(rs.getDate("date"));
                msg1.add(rs.getString("read1"));
                messages.add(msg1);
            }
            return messages;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            return null;
        }

    }

    public long getUnreadMessageCount(String userid)
    {
        try
        {
            ResultSet rs=st.executeQuery("select count(messageid) where ruserid='"+userid+"' and read1='0'");
            if(rs.next())
            return Long.parseLong(rs.getString(1));
            return 0;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            return 0;
        }
    }

    public List getSentMessages(String userid)
    {
        try{
            List messages=new ArrayList();
            ResultSet rs=st.executeQuery("select ruerid,message,date,read1 where suserid='"+userid+"' order by date desc");
            while(rs.next())
            {
                List msg1=new ArrayList();
                msg1.add(rs.getString("ruserid"));
                msg1.add(rs.getString("message"));
                msg1.add(rs.getDate("date"));
                msg1.add(rs.getString("read1"));
                messages.add(msg1);
            }
            return messages;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            return null;
        }

    }

    public boolean notify(String userid,String notification)
    {
        try
        {
            st.executeUpdate("insert into notification (userid,notification,read1,date) values('"+userid+"','"+notification+"','1',now())");
            return true;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            return false;
        }
    }

    public List getNotification(String userid)
    {
        try
        {
            List notification=new ArrayList();
            ResultSet rs=st.executeQuery("select notification,read1,date from notification where userid='"+userid+"' oreder by date desc");
            while(rs.next())
            {
                List n1=new ArrayList();
                n1.add(rs.getString("notification"));
                n1.add(rs.getString("read1"));
                n1.add(rs.getDate("date"));
                notification.add(n1);
            }
            return notification;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            return null;
        }
    }

    public boolean setReadNotification(String messageid)
    {
        try
        {
            st.executeUpdate("update notification set read1='1'");
            return true;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            return false;
        }
    }

    public boolean createAlbum(String userid,String name)
    {
        try
        {
         st.executeUpdate("insert into album values(userid,name)('"+userid+"','"+name+"')");
         return true;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            return false;
        }
    }

    public boolean uploadImage(HttpServletRequest request,String albumid)
    {
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (!isMultipart) {
		return false;
		}
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			List items = null;

			try {
				items = upload.parseRequest(request);

			} catch (Exception e) {
				e.printStackTrace();
                                return false;
			}
			Iterator itr = items.iterator();
			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				if (!item.isFormField()) {
					try {


						String itemName = item.getName();
						Random generator = new Random();
						int r = Math.abs(generator.nextInt());

						String reg = "[.*]";
						String replacingtext = "";
						Pattern pattern = Pattern.compile(reg);
						Matcher matcher = pattern.matcher(itemName);
						StringBuffer buffer = new StringBuffer();
                                                while (matcher.find()) {
							matcher.appendReplacement(buffer, replacingtext);
						}
						int IndexOf = itemName.indexOf(".");
						String domainName = itemName.substring(IndexOf);

						String finalimage = buffer.toString()+"_"+r+domainName;
						File savedFile = new File(request.getRealPath("/")+"/images/"+finalimage);
                                                item.write(savedFile);
                                                MyImageWriteParam m=new MyImageWriteParam();
						if(savedFile.length()>=300000)
                                                    {
                                                    m.compressJpegFile(savedFile, savedFile,0.2f);
                                                    }

						try {

							st.executeUpdate("insert into image values(url,albumid,date)('"+finalimage+"','"+albumid+"',now())");
						} catch (Exception e) {
							System.out.println(e);
                                                        return false;
						}

					} catch (Exception e) {
						e.printStackTrace();
                                                return false;
					}
				}

			}
                        return true;
    }

    public boolean setImageComment(String imageid,String userid,String comment)
    {

        try
        {
            st.executeUpdate("insert into comment (imageid,userid,comment,date) values('"+imageid+"','"+userid+"','"+comment+"',now())");
            return true;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            return false;
        }
    }

     public boolean deleteImageComment(String commentid)
    {
        try
        {
            st.executeUpdate("delete from image_comment where commentid='"+commentid+"'");
            return true;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            return false;
        }
    }

     public List getImageComments(String imageid)
    {
        List c=new ArrayList();
        try
        {
            ResultSet rs=st.executeQuery("select commentid,userid,comment,vote,date from image_comment where imageid='"+imageid+"' order by date asc");
            while(rs.next())
            {
                List c1=new ArrayList();
                c1.add(rs.getString("commentid"));
                c1.add(rs.getString("userid"));
                c1.add(rs.getString("comment"));
                String vote=rs.getString("vote");
                if(vote!=null)
                    c1.add(Arrays.asList(vote.split(",")));
                else
                    c1.add(new ArrayList());
                c1.add(rs.getDate("date"));
                c.add(c1);
            }
            return c;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            return null;
        }
    }

     public boolean deleteImage(String imageid)
    {
        try
        {
            st.executeUpdate("delete from image where imageid='"+imageid+"'");
            return true;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            return false;
        }
    }

     public boolean setAsProPic(String userid,String albumid,String imageid,int x,int y,int width,int height)
    {
         try{
             ResultSet rs=st.executeQuery("select url from image where imageid='"+imageid+"'");
             String iname=rs.getString("url");
             if(rs.next())
             {
                 Image image = Toolkit.getDefaultToolkit().getImage("images/"+iname);
                 image = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(),new CropImageFilter(x, y, width, height)));
                 BufferedImage bi=new BufferedImage(200,200,BufferedImage.TYPE_INT_RGB);
                 Graphics2D g=bi.createGraphics();
                 g.drawImage(image,null, null);
                 g.dispose();
                 ImageIO.write(bi,"jpg", new FileOutputStream(new File("images/propic_"+iname),false));
                 st.executeUpdate("insert into image (url,albumid,date) values('"+"propic_"+iname+"','"+albumid+"',now())");
                 st.executeUpdate("update user_info set propic='"+"propic_"+iname+"' where userid='"+userid+"'");
                 return true;
            }
            return false;
         }
         catch(IOException ioe)
         {
             System.out.println(ioe);
             return false;
         }
         catch(SQLException se)
        {
            System.out.println(se);
            return false;

        }
     }
}

