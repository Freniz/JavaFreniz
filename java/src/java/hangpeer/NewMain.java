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
import compress.*;
import java.awt.Color;
import javax.servlet.http.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;

public class NewMain extends Thread{
    Connection con;
    PreparedStatement pst;
    public Connection returnCon()
    {
        return con;
    }
    public NewMain(Connection con1)
    {
            
            if (con1!= null)
             con=con1;
        /*try
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con=DriverManager.getConnection("Jdbc:Mysql://localhost/test2","nizam","ajith786");
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
        }*/

    }

    public synchronized boolean validate(String userid, String pass)
    {
        try{

            pst=con.prepareStatement("select userid,pass from userstable where userid='"+userid+"'");
    ResultSet rs=pst.executeQuery();
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

    public synchronized String getStature(String userid)
    {
        String stature="no updates";
        try
        {
            pst=con.prepareStatement("select status from status where suserid='"+userid+"' and ruserid='"+userid+"' order by date desc limit 0,1");
            ResultSet rs=pst.executeQuery();
            if(rs.next())
                stature=rs.getString("status");
            return stature;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            return stature;
        }

    }


    public synchronized HashMap getUserDetails(String userid)
    {
        HashMap ud=new HashMap();
        try
        {
            ud.clear();
            pst=con.prepareStatement("select profiletype,fname,lname,dob,sex,school,college,email,hometown,currentcity,language,rstatus,employer,religion,myphilosophy,state,country,propic,pinnedpic,books,musics,movies,celebrities,games,sports,other,playlist,mood,secondarypic1,secondarypic2,propicalbum from user_info where userid='"+userid+"'");
            ResultSet rs=pst.executeQuery();
            if(rs.next())
            {
                ud.put("profiletype", rs.getString("profiletype"));
                ud.put("fname", rs.getString("fname"));
                ud.put("lname", rs.getString("lname"));
                ud.put("dob", rs.getDate("dob"));
                ud.put("sex", rs.getString("sex"));
                ud.put("email", rs.getString("email"));
                ud.put("hometown", rs.getString("hometown"));
                ud.put("currentcity", rs.getString("currentcity"));
                ud.put("rstatus", rs.getString("rstatus"));
                ud.put("employer", rs.getString("employer"));
                ud.put("religion", rs.getString("religion"));
                ud.put("myphilosophy", rs.getString("myphilosophy"));
                ud.put("state", rs.getString("state"));
                ud.put("country", rs.getString("country"));
                ud.put("propic", rs.getString("propic"));
                ud.put("propicalbum", rs.getString("propicalbum"));
                ObjectInputStream ois=new ObjectInputStream(rs.getBlob("pinnedpic").getBinaryStream());
                ud.put("pinnedpic",(List)ois.readObject());
                
                
                 ois=new ObjectInputStream(rs.getBlob("language").getBinaryStream());
                ud.put("language", (java.util.List)ois.readObject());
               ois=new ObjectInputStream(rs.getBlob("school").getBinaryStream());
                ud.put("school", (java.util.List)ois.readObject());

                ois=new ObjectInputStream(rs.getBlob("college").getBinaryStream());
                ud.put("college", (java.util.List)ois.readObject());

                
                ois=new ObjectInputStream(rs.getBlob("books").getBinaryStream());
                ud.put("books", (java.util.List)ois.readObject());
                ois=new ObjectInputStream(rs.getBlob("musics").getBinaryStream());
                ud.put("musics", (java.util.List)ois.readObject());
                ois=new ObjectInputStream(rs.getBlob("movies").getBinaryStream());
                ud.put("movies", (java.util.List)ois.readObject());
                ois=new ObjectInputStream(rs.getBlob("celebrities").getBinaryStream());
                ud.put("celebrities", (java.util.List)ois.readObject());
                ois=new ObjectInputStream(rs.getBlob("games").getBinaryStream());
                ud.put("games", (java.util.List)ois.readObject());
                ois=new ObjectInputStream(rs.getBlob("sports").getBinaryStream());
                ud.put("sports", (java.util.List)ois.readObject());
                ois=new ObjectInputStream(rs.getBlob("other").getBinaryStream());
                ud.put("others", (java.util.List)ois.readObject());
                ois=new ObjectInputStream(rs.getBlob("playlist").getBinaryStream());
                ud.put("playlist", (java.util.List)ois.readObject());
                ud.put("mood", rs.getString("mood"));
                ud.put("secondarypic1", rs.getString("secondarypic1"));
                ud.put("secondarypic2", rs.getString("secondarypic2"));
                
                

            }
            return ud;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            ud.clear();
            return ud;
        }
        catch(IOException se)
        {
            System.out.println(se);
            ud.clear();
            return ud;
        }
        catch(NullPointerException se)
        {
            System.out.println(se);
            ud.clear();
            return ud;
        }
        catch(ClassNotFoundException se)
        {
            System.out.println(se);
            ud.clear();
            return ud;
        }
    }
    
    
    
    public synchronized boolean updateLanguage(String userid,List language)
    {
        try
        {
            pst=con.prepareStatement("update user_info set language=? where userid='"+userid+"'");
            pst.setObject(1, language);
            pst.executeUpdate();
            return true;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            return false;
        }
    }


  public synchronized boolean updateSchool(String userid,List school)
    {
        try
        {
            pst=con.prepareStatement("update user_info set school=? where userid='"+userid+"'");
            pst.setObject(1, school);
            pst.executeUpdate();
            return true;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            return false;
        }
    }


  public synchronized boolean updateCollege(String userid,List college)
    {
        try
        {
            pst=con.prepareStatement("update user_info set college=? where userid='"+userid+"'");
            pst.setObject(1, college);
            pst.executeUpdate();
            return true;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            return false;
        }
    }

    
    
    

    public synchronized boolean updatePassword(String userid,String password)
    {
     try
     {
         pst=con.prepareStatement("update userstable set password='"+password+"'where userid='"+userid+"'");
         pst.executeUpdate();
         return true;
     }
     catch(SQLException se)
     {
         System.out.println(se);
         return false;
     }
    }

    public synchronized boolean updateMusics(String userid,List musics)
    {
        try
        {
            pst=con.prepareStatement("update user_info set musics=? where userid='"+userid+"'");
            pst.setObject(1, musics);
            pst.executeUpdate();
            return true;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            return false;
        }
    }
    public synchronized boolean updateBooks(String userid,List books)
    {
        try
        {
            pst=con.prepareStatement("update user_info set books=? where userid='"+userid+"'");
            pst.setObject(1, books);
            pst.executeUpdate();
            return true;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            return false;
        }
    }
    public synchronized boolean updateGames(String userid,List games)
    {
        try
        {
            pst=con.prepareStatement("update user_info set games=? where userid='"+userid+"'");
            pst.setObject(1, games);
            pst.executeUpdate();
            return true;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            return false;
        }
    }

    public synchronized boolean updatemovies(String userid,List movies)
    {
        try
        {
            pst=con.prepareStatement("update user_info set movies=? where userid='"+userid+"'");
            pst.setObject(1, movies);
            pst.executeUpdate();
            return true;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            return false;
        }
    }
    public synchronized boolean updateCelebrities(String userid,List celebrities)
    {
        try
        {
            pst=con.prepareStatement("update user_info set celebrities=? where userid='"+userid+"'");
            pst.setObject(1, celebrities);
            pst.executeUpdate();
            return true;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            return false;
        }
    }

    public synchronized boolean updateOther(String userid,List other)
    {
        try
        {
            pst=con.prepareStatement("update user_info set other=? where userid='"+userid+"'");
            pst.setObject(1, other);
            pst.executeUpdate();
            return true;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            return false;
        }
    }
    public synchronized boolean updateSports(String userid,List sports)
    {
        try
        {
            pst=con.prepareStatement("update user_info set sports=? where userid='"+userid+"'");
            pst.setObject(1, sports);
            pst.executeUpdate();
            return true;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            return false;
        }
    }

    public synchronized boolean updateSchool(String userid, String school)
    {
        try
        {
            pst=con.prepareStatement("update user_info set school='"+school+"' where userid='"+userid+"'");
            pst.executeUpdate();
            return true;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            return false;
        }
    }
    public synchronized boolean updateEmployer(String userid, String employer)
    {
        try
        {
            pst=con.prepareStatement("update user_info set employer='"+employer+"' where userid='"+userid+"'");
            pst.executeUpdate();
            return true;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            return false;
        }
    }
    
    public synchronized boolean updateBasicInfo(String userid, String fname,String lname, String sex, String rstatus, String religion,java.util.Date dob, String hometown, String currentcity)
    {
        try
        {
            java.text.SimpleDateFormat formatter=new java.text.SimpleDateFormat("yyyy-MM-dd");
            String dob1=formatter.format(dob);
            pst=con.prepareStatement("update user_info set hometown='"+hometown+"',currentcity='"+currentcity+"', fname='"+fname+"',lname='"+lname+"',sex='"+sex+"',rstatus='"+rstatus+"',religion='"+religion+"',dob='"+dob1+"' where userid='"+userid+"'");
            pst.executeUpdate();
            return true;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            return false;
        }
    }
    public synchronized boolean updateContactDeatails(String userid,String state,String country)
    {
        try
        {
            pst=con.prepareStatement("update user_info set state='"+state+"',country='"+country+"' where userid='"+userid+"'");
            pst.executeUpdate();
            return true;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            return false;
        }
    }
    
    public synchronized boolean updateMood(String userid,String mood)
    {
        try
        {
        pst=con.prepareStatement("update user_info set mood='"+mood+"' where userid='"+userid+"'");
        pst.executeUpdate();
        return true;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            return false;
        }
    }
    
    public synchronized boolean updateSecondarypic1(String userid,String url)
    {
        try
        {
           pst=con.prepareStatement("update user_info set secondarypic1='"+url+"' where userid='"+userid+"'");
           pst.executeUpdate();
           return true;

        }
        catch(SQLException se)
        {
            System.out.println(se);
            return false;
        }
    }
public synchronized boolean updateSecondarypic2(String userid,String url)
    {
        try
        {
           pst=con.prepareStatement("update user_info set secondarypic2='"+url+"' where userid='"+userid+"'");
           pst.executeUpdate();
           return true;

        }
        catch(SQLException se)
        {
            System.out.println(se);
            return false;
        }
    }

    public synchronized boolean createUser(String userid,String password,String fname,String lname,java.util.Date date,String sex,String email)
    {
        try
        {
            java.text.SimpleDateFormat formater=new java.text.SimpleDateFormat("yyyy-MM-dd");
            String date1=formater.format(date);
            pst=con.prepareStatement("insert into freniz values('"+userid+"','user','profile?userid="+userid+"')");
            pst.executeUpdate();
            pst=con.prepareStatement("insert into userstable (userid,pass) values('"+userid+"','"+password+"')");
            pst.executeUpdate();
            pst=con.prepareStatement("insert into user_info (userid,fname,lname,dob,sex,email,date,musics,books,movies,games,celebrities,other,pinnedpic,sports,playlist,school,college,language,adminpages,url) values('"+userid+"','"+fname+"','"+lname+"','"+date1+"','"+sex+"','"+email+"',now(),?,?,?,?,?,?,?,?,?,?,?,?,?,'profile?userid="+userid+"')");
            for(int i=1;i<=13;i++)
                pst.setObject(i, new ArrayList());
            pst.executeUpdate();
            pst=con.prepareStatement("insert into apps values('"+userid+"',?,?,?)");
            for(int i=1;i<=3;i++)
                pst.setObject(i, new HashMap());
            pst.executeUpdate();
            pst=con.prepareStatement("insert into friends_vote (userid,friendlist,incomingrequest,sentrequest,vote) values('"+userid+"',?,?,?,?)");
            for(int i=1;i<=4;i++)
                pst.setObject(i, new ArrayList());
            pst.executeUpdate();
            return true;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            return false;
        }
    }

    public synchronized HashMap getFriendsAndVote(String userid)
    {
        HashMap friends_vote= new HashMap();
        try
        {
            System.out.println(userid);
            pst=con.prepareStatement("select friendlist,vote from friends_vote where userid='"+userid+"'");
            ResultSet rs=pst.executeQuery();
            if(rs.next())
            {
             friends_vote.clear();
             friends_vote.put("friendlist",(java.util.List)(new ObjectInputStream(rs.getBlob("friendlist").getBinaryStream()).readObject()));
             friends_vote.put("vote",(java.util.List)(new ObjectInputStream(rs.getBlob("vote").getBinaryStream()).readObject()));

            }

            return friends_vote;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            friends_vote.clear();
            return friends_vote;
        }
         catch(IOException se)
        {
            System.out.println(se);
            friends_vote.clear();
            return friends_vote;

        }
        catch(NullPointerException se)
        {
            System.out.println(se);
            friends_vote.clear();
            return friends_vote;

        }
        catch(ClassNotFoundException se)
        {
            System.out.println(se);
            friends_vote.clear();
            return friends_vote;
        }
    }

    public synchronized HashMap bendingRequest(String userid)
    {
        HashMap bending=new HashMap();
        try
        {
            bending.clear();
            pst=con.prepareStatement("select sentrequest,incomingrequest from friends_vote where userid='"+userid+"'");
            ResultSet rs=pst.executeQuery();
            if(rs.next())
            {
                bending.put("sentrequest",(java.util.List)(new ObjectInputStream(rs.getBlob("sentrequest").getBinaryStream()).readObject()));
                bending.put("incomingrequest",(java.util.List)(new ObjectInputStream(rs.getBlob("incomingrequest").getBinaryStream()).readObject()));
            }
            return bending;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            bending.clear();
            return bending;
        }
         catch(IOException se)
        {
            System.out.println(se);
            bending.clear();
            return bending;
        }
        catch(NullPointerException se)
        {
            System.out.println(se);
            bending.clear();
            return bending;
        }
        catch(ClassNotFoundException se)
        {
            System.out.println(se);
            bending.clear();
            return bending;
        }
    }

    public synchronized boolean updateFriendList(String userid,List friendlist,List sentrequest,List incomingrequest)
    {
        try
        {
            pst=con.prepareStatement("update friends_vote set friendlist=? , sentrequest=?,incomingrequest=? where userid='"+userid+"'");
            pst.setObject(1, friendlist);
            pst.setObject(2, sentrequest);
            pst.setObject(3, incomingrequest);
            pst.executeUpdate();
            return true;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            return false;
        }
    }




    public synchronized boolean writeTestimonial(String suserid,String ruserid,String message)
    {
        try
        {
            pst=con.prepareStatement("insert into testimonial (suserid,ruserid,message,date,vote) values('"+suserid+"','"+ruserid+"','"+message+"',now(),?)");
            pst.setObject(1,new ArrayList());
            pst.executeUpdate();
            return true;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            return false;
        }
    }

    public synchronized HashMap getTestimonial(String userid)
    {
        HashMap testy = new HashMap();
        try
        {
            pst=con.prepareStatement("select testyid,suerid,message,vote,date from testimonial where ruserid='"+userid+"'");
            ResultSet rs=pst.executeQuery();
            for(int i=0;rs.next();i++)
            {
                HashMap testy1=new HashMap();
                testy1.put("suserid",rs.getString("suserid"));
                testy1.put("message",rs.getString("message"));
                testy1.put("vote",(List)(new ObjectInputStream(rs.getBlob("vote").getBinaryStream()).readObject()));
                testy1.put("date",new java.util.Date(rs.getTimestamp("date").toGMTString()));
                testy.put(rs.getString("testyid"), testy1);
            }
            return testy;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            testy.clear();
            return testy;
        }
         catch(IOException se)
        {
            System.out.println(se);
            testy.clear();
            return testy;
        }
        catch(NullPointerException se)
        {
            System.out.println(se);
            testy.clear();
             return testy;
        }
        catch(ClassNotFoundException se)
        {
            System.out.println(se);
            testy.clear();
             return testy;
        }
    }


    public synchronized boolean deleteTestimonial(String testyid)
    {
        try
        {
            pst=con.prepareStatement("delete freom testy where testyid='"+testyid+"'");
            pst.executeUpdate();
            return true;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            return false;
        }
    }


    public synchronized boolean doPost(String suserid,String ruserid,String status)
    {
        try
        {
            pst=con.prepareStatement("insert into status (suserid,ruserid,status,date,vote) values('"+suserid+"','"+ruserid+"','"+status+"',now(),?)");
            pst.setObject(1,new ArrayList());
            pst.executeUpdate();
            return true;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            return false;
        }

    }

    public synchronized boolean doComment(String statusid,String userid,String comment)
    {
        try
        {
            pst=con.prepareStatement("insert into comment (statusid,userid,comment,date,vote) values('"+statusid+"','"+userid+"','"+comment+"',now(),?)");
            pst.setObject(1,new ArrayList());
            pst.executeUpdate();
            pst=con.prepareStatement("select commentcount from status where statusid='"+statusid+"'");
            ResultSet rs=pst.executeQuery();
            if(rs.next())
            {
            pst=con.prepareStatement("update status set commentcount='"+(rs.getLong("commentcount")+1) +"' where statusid='"+statusid+"'");
            pst.executeUpdate();
            }
            return true;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            return false;
        }
    }

    public synchronized boolean deletePost(String statusid)
    {
        try
        {
            pst=con.prepareStatement("delete from status where statusid='"+statusid+"'");
            pst.executeUpdate();
            return true;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            return false;
        }
    }

    public synchronized boolean deleteComment(String commentid)
    {
        try
        {
            pst=con.prepareStatement("select statusid from comment where commentid='"+commentid+"'");
            ResultSet rs1=pst.executeQuery();
            if(rs1.next())
            {
            pst=con.prepareStatement("select commentcount from status where statusid='"+rs1.getString("statusid")+"'");
            ResultSet rs=pst.executeQuery();
            if(rs.next())
            {
            pst=con.prepareStatement("update status set commentcount='"+(rs.getLong("commentcount)")-1) +"' where statusid='"+rs1.getString("statusid") +"'");
            pst.executeUpdate();
            }
            rs.close();
            }
            pst=con.prepareStatement("delete from comment where commentid='"+commentid+"'");
            pst.executeUpdate("delete from comment where commentid='"+commentid+"'");
            rs1.close();
            return true;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            return false;
        }
    }

    public synchronized HashMap getUsersPost(String userid)
    {
        HashMap s=new HashMap();
        Runtime.runFinalizersOnExit(true);
        
        try
        {
            pst=con.prepareStatement("select statusid,suserid,ruserid,status,vote,date from status where ruserid='"+userid+"' order by date desc");
            ResultSet rs=pst.executeQuery();
            while(rs.next())
            {
                HashMap s1=new HashMap();
                //s1.add(rs.getString("statusid"));
                s1.put("suserid",rs.getString("suserid"));
                s1.put("ruserid", rs.getString("ruserid"));
                s1.put("status",rs.getString("status"));
                s1.put("vote",(java.util.List)(new ObjectInputStream(rs.getBlob("vote").getBinaryStream()).readObject()));
                s1.put("date",new java.util.Date(rs.getTimestamp("date").toGMTString()));
                s1.put("comment", this.getComments(rs.getString("statusid")));
                s.put(rs.getString("statusid"),s1);
            }
            Runtime.getRuntime().gc();
            return s;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            s.clear();
            return s;
        }
         catch(IOException se)
        {
            System.out.println(se);
            s.clear();
            return s;
        }
        catch(NullPointerException se)
        {
            System.out.println(se);
            s.clear();
            return s;
        }
        catch(ClassNotFoundException se)
        {
            System.out.println(se);
            s.clear();
            return s;
        }
    }
    public synchronized HashMap getComment(String commentid)
    {
        HashMap c=new HashMap();
        try
        {
            pst=con.prepareStatement("select userid,statusid,comment,vote,date from comment where commentid='"+commentid+"' order by date asc");
            ResultSet rs=pst.executeQuery();
            while(rs.next())
            {
                c.put("userid",rs.getString("userid"));
                c.put("comment", rs.getString("comment"));
                c.put("statusid", rs.getString("statusid"));
                c.put("vote",(java.util.List)(new ObjectInputStream(rs.getBlob("vote").getBinaryStream()).readObject()));
                c.put("date",rs.getTimestamp("date"));
                
            }
            return c;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            c.clear();
            return c;
        }
         catch(IOException se)
        {
            System.out.println(se);
            c.clear();
            return c;
        }
        catch(NullPointerException se)
        {
            System.out.println(se);
            c.clear();
            return c;
        }
        catch(ClassNotFoundException se)
        {
            System.out.println(se);
            c.clear();
            return c;
        }
    }
    
    public synchronized HashMap getComments(String statusid)
    {
        HashMap c=new HashMap();
        try
        {
            pst=con.prepareStatement("select commentid,userid,comment,vote,date from comment where statusid='"+statusid+"' order by date asc");
            ResultSet rs=pst.executeQuery();
            while(rs.next())
            {
                HashMap c1=new HashMap();
                //c1.put(rs.getString("commentid"));
                c1.put("userid",rs.getString("userid"));
                c1.put("comment", rs.getString("comment"));
                c1.put("vote",(java.util.List)(new ObjectInputStream(rs.getBlob("vote").getBinaryStream()).readObject()));
                c1.put("date",rs.getTimestamp("date"));
                c.put(rs.getString("commentid"),c1);
            }
            return c;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            c.clear();
            return c;
        }
         catch(IOException se)
        {
            System.out.println(se);
            c.clear();
            return c;
        }
        catch(NullPointerException se)
        {
            System.out.println(se);
            c.clear();
            return c;
        }
        catch(ClassNotFoundException se)
        {
            System.out.println(se);
            c.clear();
            return c;
        }
    }

    public synchronized HashMap getPost(String statusid)
    {
        HashMap status=new HashMap();
        try
        {
            pst=con.prepareStatement("select * from status where statusid='"+statusid+"'");
            ResultSet rs=pst.executeQuery();
            if(rs.next())
            {
                status.put("suserid",rs.getString("suserid"));
                status.put("ruserid", rs.getString("ruserid"));
                status.put("status",rs.getString("status"));
                status.put("vote",(java.util.List)(new ObjectInputStream(rs.getBlob("vote").getBinaryStream()).readObject()));
                status.put("comments", this.getComments(statusid));
                status.put("date",new java.util.Date(rs.getTimestamp("date").toGMTString()));
            }
            return status;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            status.clear();
            return status;
        }
         catch(IOException se)
        {
            System.out.println(se);
            status.clear();
            return status;
        }
        catch(NullPointerException se)
        {
            System.out.println(se);
            status.clear();
            return status;
        }
        catch(ClassNotFoundException se)
        {
            System.out.println(se);
            status.clear();
            return status;
        }
    }



    public synchronized boolean voteStatus(String statusid,List vote)
    {
        try
        {
            pst=con.prepareStatement("update status set vote=? where statusid='"+statusid+"'");
            pst.setObject(1, vote);
            pst.executeUpdate();
            return true;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            return false;
        }
    }

    public synchronized boolean voteComment(String commentid,List vote)
    {
        try
        {
            pst=con.prepareStatement("update comment set vote=? where statusid='"+commentid+"'");
            pst.setObject(1, vote);
            pst.executeUpdate();
            return true;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            return false;
        }
    }

    public synchronized boolean voteUser(String userid,List vote)
    {
        try
        {
            pst=con.prepareStatement("update friends_vote set vote=? where userid='"+userid+"'");
            pst.setObject(1, vote);
            pst.executeUpdate();
            return true;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            return false;
        }
    }

     public synchronized boolean voteTesty(String testyid,List vote)
    {
        try
        {
            pst=con.prepareStatement("update testimonial set vote=? where testyid='"+testyid+"'");
            pst.setObject(1, vote);
            pst.executeUpdate();
            return true;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            return false;
        }
    }


    public synchronized boolean voteImage(String imageid,List vote)
    {
        try
        {
            pst=con.prepareStatement("update image set vote=? where imageid='"+imageid+"'");
            pst.setObject(1, vote);
            pst.executeUpdate();
            return true;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            return false;
        }
    }


    public synchronized boolean voteImageComment(String commentid,List vote)
    {
        try
        {
            pst=con.prepareStatement("update image_Comments set vote=? where commentid='"+commentid+"'");
            pst.setObject(1, vote);
            pst.executeUpdate();
            return true;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            return false;
        }
    }


    public synchronized boolean sentMessage(String suserid,String ruserid,String message)
    {
        try
        {
            pst=con.prepareStatement("insert into message (suserid,ruserid,message,date,read1) values('"+suserid+"','"+ruserid+"','"+message+"',now(),'0')");
            pst.executeUpdate();
            return true;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            return false;
        }
    }
    public synchronized boolean setRead(String messageid)
    {
        try
        {
            pst=con.prepareStatement("update message set read1='1'");
            pst.executeUpdate();
            return true;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            return false;
        }
    }

    public synchronized HashMap getUserMessages(String userid)
    {
        HashMap messages=new HashMap();
        try{
            pst=con.prepareStatement("select messageid,suerid,message,date,read1 where ruserid='"+userid+"' order by date desc");
            ResultSet rs=pst.executeQuery();
            while(rs.next())
            {
                HashMap msg1=new HashMap();
                msg1.put("suerid",rs.getString("suserid"));
                msg1.put("message",rs.getString("message"));
                msg1.put("date",new java.util.Date(rs.getTimestamp("date").toGMTString()));
                msg1.put("read",rs.getString("read1"));
                messages.put(rs.getString("messageid"),msg1);
            }
            return messages;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            messages.clear();
            return messages;
        }

    }

    public synchronized long getUnreadMessageCount(String userid)
    {
        try
        {
            pst=con.prepareStatement("select count(messageid) from message where ruserid='"+userid+"' and read1='0'");
            ResultSet rs=pst.executeQuery();
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

    public synchronized HashMap getSentMessages(String userid)
    {
        HashMap messages=new HashMap();
        try{
            pst=con.prepareStatement("select messageid,ruserid,message,date,read1 from message where suserid='"+userid+"' order by date desc");
            ResultSet rs=pst.executeQuery();
            while(rs.next())
            {
                HashMap msg1=new HashMap();
                msg1.put("ruserid",rs.getString("ruserid"));
                msg1.put("message",rs.getString("message"));
                msg1.put("date",new java.util.Date(rs.getTimestamp("date").toGMTString()));
                msg1.put("read",rs.getString("read1"));
                messages.put(rs.getString("messageid"),msg1);
            }
            return messages;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            messages.clear();
            return messages;
        }

    }

    public synchronized boolean notify(String userid,String contenturl,String notification)
    {
        try
        {
            pst=con.prepareStatement("select * from notification where contenturl='"+contenturl+"'");
            ResultSet rs=pst.executeQuery();
            if(rs.next())
            {
                pst=con.prepareStatement("update notification set notification='"+notification+"', read1='0', date=now() where contenturl='"+contenturl+"'");
                return true;
            }
            pst=con.prepareStatement("insert into notification (contenturl,userid,notification,read1,date) values('"+contenturl+"','"+userid+"','"+notification+"','0',now())");
            pst.executeUpdate();
            return true;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            return false;
        }
    }

    public synchronized HashMap getNotification(String userid)
    {
        try
        {
            HashMap notification=new HashMap();
            pst=con.prepareStatement("select conetenturl,notification,read1,date from notification where userid='"+userid+"' oreder by date desc");
            ResultSet rs=pst.executeQuery();
            while(rs.next())
            {
                HashMap n1=new HashMap();
                n1.put("notification",rs.getString("notification"));
                n1.put("read",rs.getString("read1"));
                n1.put("date",new java.util.Date(rs.getTimestamp("date").toGMTString()));
                notification.put("contenturl",n1);
            }
            return notification;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            return null;
        }
    }

    public synchronized boolean setReadNotification(String messageid)
    {
        try
        {
            pst=con.prepareStatement("update notification set read1='1'");
            pst.executeUpdate();
            return true;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            return false;
        }
    }

    public synchronized boolean createAlbum(String userid,String name)
    {
        try
        {
            pst=con.prepareStatement("insert into album (userid,name,date) values('"+userid+"','"+name+"',now())");
            pst.executeUpdate();
            return true;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            return false;
        }
    }
    public synchronized HashMap getAlbum(String userid)
    {
        HashMap albums=new HashMap();
        try
        {
            pst=con.prepareStatement("select albumid,name,date from album where userid='"+userid+"'");
            ResultSet rs=pst.executeQuery();
            while(rs.next())
            {
                HashMap album=new HashMap();
                album.put("name", rs.getString("name"));
                album.put("date",new java.util.Date(rs.getTimestamp("date").toGMTString()));
                albums.put(rs.getString("albumid"),album);
            }
            return albums;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            albums.clear();
            return albums;
        }
    }
    public synchronized boolean uploadImage(HttpServletRequest request,HttpSession session)
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
				if (item.isFormField())
                                {
                                    String name=item.getFieldName();
                                    String value=item.getString();
                                    if(!value.equals("null"))
                                    session.setAttribute(name, value);
                                }
                                else if(((String)session.getAttribute("userid")).equals((String)(this.getAlbum((String)session.getAttribute("album")).get("userid")))) 
                                {
                                    
					try {
						String itemName = item.getName();
						Random generator = new Random();
						int r = Math.abs(generator.nextInt());
                                                int r1=Math.abs(generator.nextInt());
                                                int r2=Math.abs(generator.nextInt());
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

						String finalimage = buffer.toString()+"_"+r+"_"+r1+"_"+r2+domainName;
						File savedFile = new File(request.getRealPath("/")+"/images/"+finalimage);
                                                item.write(savedFile);
                                                MyImageWriteParam m=new MyImageWriteParam();

                                                if(savedFile.length()>=100000)
                                                    {
                                                        m.compressJpegFile(savedFile, savedFile,0.2f);
                                                    }

                                                 ResizeImage ri=new ResizeImage(request.getRealPath("/"),savedFile);
						try {

                                                        pst=con.prepareStatement("insert into image (title,description,url,albumid,userid,date,pinnedpeople,vote) values('"+session.getAttribute("title")+"','"+session.getAttribute("description")+"','"+finalimage+"','"+(String)session.getAttribute("album")+"','"+(String)session.getAttribute("userid")+"',now(),?,?)");
							pst.setObject(1,new ArrayList());
                                                        pst.setObject(2, new ArrayList());
                                                        pst.executeUpdate();


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
    public synchronized HashMap getImages(String albumid)
    {
        HashMap images=new HashMap();
        try
        {
            pst=con.prepareStatement("select imageid,title,description,url,userid,pinnedpeople,vote,date from image where albumid='"+albumid+"'");
            ResultSet rs=pst.executeQuery();
            while(rs.next())
            {
                HashMap i=new HashMap();
                i.clear();
                i.put("url", rs.getString("url"));
                i.put("userid", rs.getString("userid"));
                i.put("albumid", albumid);
                i.put("title",rs.getString("title"));
                
                i.put("description",rs.getString("description"));
                i.put("pinnedpeople",(List)(new ObjectInputStream(rs.getBlob("pinnedpeople").getBinaryStream()).readObject()));
                i.put("vote",(List)(new ObjectInputStream(rs.getBlob("vote").getBinaryStream()).readObject()));
                i.put("date", rs.getTimestamp("date"));
                images.put(rs.getString("imageid"), i);
            }
            return images;
            
        }
        catch(SQLException se)
        {
            System.out.println(se);
            images.clear();
            return images;
        }
         catch(IOException se)
        {
            System.out.println(se);
            images.clear();
            return images;
        }
        catch(NullPointerException se)
        {
            System.out.println(se);
            images.clear();
            return images;
        }
        catch(ClassNotFoundException se)
        {
            System.out.println(se);
            images.clear();
            return images;
        }


    }
    public synchronized HashMap getImage(String imageid)
    {
        HashMap i=new HashMap();
        try
        {
            pst=con.prepareStatement("select title,description,url,albumid,userid,pinnedpeople,vote,date from image where imageid='"+imageid+"'");
            ResultSet rs=pst.executeQuery();
            while(rs.next())
            {
                i.clear();
                i.put("url", rs.getString("url"));
                i.put("userid", rs.getString("userid"));
                i.put("albumid", rs.getString("albumid"));
                i.put("title",rs.getString("title"));
                i.put("description",rs.getString("description"));
                if(rs.getBlob("pinnedpeople")!=null)
                i.put("pinnedpeople",(List)(new ObjectInputStream(rs.getBlob("pinnedpeople").getBinaryStream()).readObject()));
                if(rs.getBlob("vote")!=null)
                i.put("vote",(List)(new ObjectInputStream(rs.getBlob("vote").getBinaryStream()).readObject()));
                i.put("date", rs.getTimestamp("date"));
                
            }
            return i;
            
        }
        catch(SQLException se)
        {
            System.out.println(se);
            i.clear();
            return i;
        }
         catch(IOException se)
        {
            System.out.println(se);
            i.clear();
            return i;
        }
        catch(NullPointerException se)
        {
            System.out.println(se);
            i.clear();
            return i;
        }
        catch(ClassNotFoundException se)
        {
            System.out.println(se);
            i.clear();
            return i;
        }


    }
    
    public synchronized boolean doImageComment(String imageid,String userid,String comment)
    {

        try
        {
            pst=con.prepareStatement("insert into image_comments (imageid,userid,comment,date,vote) values('"+imageid+"','"+userid+"','"+comment+"',now(),?)");
            pst.setObject(1,new ArrayList());

            pst.executeUpdate();
            return true;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            return false;
        }
    }

    public synchronized boolean deleteImageComment(String commentid)
    {
        try
        {
            pst=con.prepareStatement("delete from image_comments where commentid='"+commentid+"'");
            pst.executeUpdate();
            return true;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            return false;
        }
    }

public synchronized HashMap getImageComments(String imageid)
    {
        HashMap c=new HashMap();
        try
        {
            pst=con.prepareStatement("select commentid,userid,comment,vote,date from image_comments where imageid='"+imageid+"' order by date asc");
            ResultSet rs=pst.executeQuery();
            while(rs.next())
            {
                HashMap c1=new HashMap();
                c1.put("userid",rs.getString("userid"));
                c1.put("comment",rs.getString("comment"));
                c1.put("vote",(java.util.List)(new ObjectInputStream(rs.getBlob("vote").getBinaryStream()).readObject()));
                c1.put("date",new java.util.Date(rs.getTimestamp("date").toGMTString()));
                c.put(rs.getString("commentid"),c1);
            }
            return c;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            c.clear();
            return c;
        }
         catch(IOException se)
        {
            System.out.println(se);
            c.clear();
            return c;
        }
        catch(NullPointerException se)
        {
            System.out.println(se);
            c.clear();
            return c;
        }
        catch(ClassNotFoundException se)
        {
            System.out.println(se);
            c.clear();
            return c;
        }
    }
    public synchronized HashMap getImageComment(String commentid)
    {
        HashMap c=new HashMap();
        try
        {
            pst=con.prepareStatement("select userid,imageid,comment,vote,date from image_comments where commentid='"+commentid+"' order by date asc");
            ResultSet rs=pst.executeQuery();
            while(rs.next())
            {
                c.put("userid",rs.getString("userid"));
                c.put("imagid", rs.getString("imageid"));
                c.put("comment",rs.getString("comment"));
                c.put("vote",(java.util.List)(new ObjectInputStream(rs.getBlob("vote").getBinaryStream()).readObject()));
                c.put("date",new java.util.Date(rs.getTimestamp("date").toGMTString()));
                
            }
            return c;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            c.clear();
            return c;
        }
         catch(IOException se)
        {
            System.out.println(se);
            c.clear();
            return c;
        }
        catch(NullPointerException se)
        {
            System.out.println(se);
            c.clear();
            return c;
        }
        catch(ClassNotFoundException se)
        {
            System.out.println(se);
            c.clear();
            return c;
        }
    }

     public synchronized boolean deleteImage(String imageid)
    {
        try
        {
            pst=con.prepareStatement("delete from image where imageid='"+imageid+"'");
            pst.executeUpdate();
            return true;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            return false;
        }
    }

     public synchronized boolean setAsProPic(String path,String userid,String albumid,String imageid,int x,int y,int width,int height)
    {
         try{
             pst=con.prepareStatement("select url from image where imageid='"+imageid+"'");
             ResultSet rs=pst.executeQuery();
             if(rs.next())
             {
                 String iname="propic_"+rs.getString("url");
                 BufferedImage i1=ImageIO.read(new File(path+rs.getString("url")));
                 java.awt.image.ImageProducer ip = new FilteredImageSource(i1.getSource(),new CropImageFilter(x, y, width, height));
                 Image image = Toolkit.getDefaultToolkit().createImage(ip);
                 
                 BufferedImage bi=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
                 Graphics2D g=bi.createGraphics();
                 g.drawImage(image,0, 0,null);
                 
                 g.dispose();
                 ImageIO.write(bi,"jpg",new java.io.FileOutputStream(new File(path+iname), false));
                 pst=con.prepareStatement("insert into image (url,userid,albumid,date,pinnedpeople,vote) values('"+iname+"','"+userid+"','"+albumid+"',now(),?,?)");
                 pst.setObject(1, new ArrayList());
                 pst.setObject(2, new ArrayList());
                 pst.executeUpdate();
                 pst=con.prepareStatement("select imageid from image where userid='"+userid+"' and url='"+iname+"' order by imageid desc limit 0,1");
                 ResultSet rs1=pst.executeQuery();
                 if(rs1.next()){
                     pst=con.prepareStatement("update user_info set propic='"+rs1.getString("imageid")+"' where userid='"+userid+"'");
                     pst.executeUpdate();
                 }
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
     public synchronized boolean updatepropic(String userid,String imageid)
     {
         try
         {
             pst=con.prepareStatement("update user_info set propic='"+imageid+"' where userid='"+userid+"'");
             pst.executeUpdate();
             return true;
         }
         catch(SQLException se)
         {
             System.out.println(se);
             return false;
         }
     }



     public synchronized boolean addPinnedPeople(String imageid,List userslist)
     {
         ResultSet rs;
         List pinnedpic;
         try
         {
             pst=con.prepareStatement("select pinnedpeople from image where imageid='"+imageid+"'");
             ResultSet rs1=pst.executeQuery();
             if(rs1.next()){
                 List pp=(List)((new ObjectInputStream(rs1.getBlob("pinnedpeople").getBinaryStream())).readObject());
                 userslist.removeAll(pp);
                 pp.addAll(userslist);
                 pst=con.prepareStatement("update image set pinnedpeople=? where imageid='"+imageid+"'");
                 pst.setObject(1, pp);
                 pst.executeUpdate();
                 for(int i=0;i<userslist.size();i++)
                 {
                     pst=con.prepareStatement("select pinnedpic from user_info where userid='"+userslist.get(i)+"'");
                     rs=pst.executeQuery();
                     if(rs.next())
                     {
                         pinnedpic=(java.util.List)(new ObjectInputStream(rs.getBlob("pinnedpic").getBinaryStream()).readObject());
                         if(!pinnedpic.contains(imageid))
                             pinnedpic.add(imageid);
                         System.out.println(pinnedpic);
                         pst=con.prepareStatement("update user_info set pinnedpic=? where userid='"+userslist.get(i) +"'");
                         pst.setObject(1, pinnedpic);
                         pst.executeUpdate();
                     }
                 }
             }
             return true;
         }
         catch(SQLException se)
        {
            System.out.println(se);
            return false;
        }
         catch(IOException se)
        {
            System.out.println(se);
            return false;
        }
        catch(NullPointerException se)
        {
            System.out.println(se);
            return false;
        }
        catch(ClassNotFoundException se)
        {
            System.out.println(se);
            return false;
        }
     }
     public synchronized boolean removePinnedPeople(String imageid,String user)
     {
         ResultSet rs;
         List pinnedpic;
         try
         {
             pst=con.prepareStatement("select pinnedpeople from image where imageid='"+imageid+"'");
             ResultSet rs1=pst.executeQuery();
             if(rs1.next()){
                 List pp=(List)((new ObjectInputStream(rs1.getBlob("pinnedpeople").getBinaryStream())).readObject());
                pp.remove(user);
                 pst=con.prepareStatement("update image set pinnedpeople=? where imageid='"+imageid+"'");
                 pst.setObject(1, pp);
                 pst.executeUpdate();
                     pst=con.prepareStatement("select pinnedpic from user_info where userid='"+user+"'");
                     rs=pst.executeQuery();
                     if(rs.next())
                     {
                         pinnedpic=(java.util.List)(new ObjectInputStream(rs.getBlob("pinnedpic").getBinaryStream()).readObject());
                         if(pinnedpic.contains(imageid))
                             pinnedpic.remove(imageid);
                         pst=con.prepareStatement("update user_info set pinnedpic=? where userid='"+user+"'");
                         pst.setObject(1, pinnedpic);
                         pst.executeUpdate();
                 
                     }
             }
             return true;
         }
         catch(SQLException se)
        {
            System.out.println(se);
            return false;
        }
         catch(IOException se)
        {
            System.out.println(se);
            return false;
        }
        catch(NullPointerException se)
        {
            System.out.println(se);
            return false;
        }
        catch(ClassNotFoundException se)
        {
            System.out.println(se);
            return false;
        }
     }
     




   
     

     

     public synchronized List getMyPlayList(String userid)
     {
         List songs=new ArrayList();
         try
         {
             pst=con.prepareStatement("select playlist from user_info where userid='"+userid+"'");
             ResultSet rs=pst.executeQuery();
             if(rs.next())
             {
                 songs=(List) (new ObjectInputStream(rs.getBlob(userid).getBinaryStream()).readObject());
             }
             return songs;

         }
         catch(SQLException se)
         {
             System.out.println(se);
             return songs;
         }
         catch(IOException ie)
         {
             System.out.println(ie);
             return songs;
         }
         catch(ClassNotFoundException ce)
         {
             System.out.println(ce);
             return songs;
         }
     }

     public synchronized boolean updatePlayList(String userid,List songs)
    {
         try
         {
             pst=con.prepareStatement("update user_info set playlist=? where userid='"+userid+"'");
             pst.executeUpdate();
             return true;
         }
         catch(SQLException se)
         {
             System.out.println(se);
             return false;
         }
     }
     public synchronized String getcommentcount(String statusid)
     {
         Runtime.runFinalizersOnExit(true);
         try
         {
          pst=con.prepareStatement("select count(commentid) from comment where statusid='"+statusid+"'");
             ResultSet rs=pst.executeQuery();
             if(rs.next())
             {
                 String s=rs.getString(1);
                 rs.close();
                 Runtime.getRuntime().gc();
                 return s;
             }
             return "";
         }
         catch(SQLException se)
        {
            System.out.println(se);
            return "";
        }
     }

     public synchronized HashMap getUserStreams(String userid, List frnds,long from)
    {
        String query = "select statusid,suserid,ruserid,status,commentcount,vote,date from status where suserid='" + userid + "'or ruserid='" + userid + "'";
        for(int i=0;i<frnds.size();i++)
            query+="or suserid='"+frnds.get(i)+"' or ruserid='"+frnds.get(i)+"'";
        query+="order by date desc limit "+from+",500";
        HashMap posts=new HashMap();
        try
        {
         pst=con.prepareStatement(query);
         ResultSet rs=pst.executeQuery();
         while (rs.next())
         {
             HashMap post=new HashMap();
             post.put("suserid", rs.getString("suserid"));
             post.put("ruserid", rs.getString("ruserid"));
             post.put("status", rs.getString("status"));
             post.put("commentcount",rs.getString("commentcount"));
             post.put("vote",(List)(new ObjectInputStream(rs.getBlob("vote").getBinaryStream()).readObject()));
             post.put("date", new java.util.Date(rs.getTimestamp("date").toGMTString()));
             posts.put(rs.getString("statusid"), post);
             
         }
         rs.close();
         return posts;
        
        }
        catch(SQLException se)
        {
            System.out.println(se);
            posts.clear();
            return posts;
        }
        catch(IOException se)
        {
            System.out.println(se);
            posts.clear();
            return posts;
        }
        catch(ClassNotFoundException se)
        {
            System.out.println(se);
            posts.clear();
            return posts;
        }
        catch(NullPointerException se)
        {
            System.out.println(se);
            posts.clear();
            return posts;
        }
        catch(Throwable te)
        {
            System.out.println(te);
            posts.clear();
            return posts;
        }
        
     }

     public synchronized long getStreamscount(String userid,List frnds)
    {
         long l=0;
         try
         {
             String query = "select count(statusid) from status where suserid='" + userid + "'or ruserid='" + userid + "'";
        for(int i=0;i<frnds.size();i++)
            query+="or suserid='"+frnds.get(i)+"' or ruserid='"+frnds.get(i)+"'";
             pst=con.prepareStatement(query);
             ResultSet rs=pst.executeQuery();
             if(rs.next())
                 l=rs.getLong(1);
             return l;
         }
         catch(SQLException se)
         {
             System.out.println(se);
             return 0;
         }
     }
     public synchronized String getAlbumName(String albumid)
    {
         try
         {
             pst=con.prepareStatement("select name from album where albumid='"+albumid+"'");
             ResultSet rs=pst.executeQuery();
             if(rs.next())
             {
                 return rs.getString("name");
             }
             return "";
         }
         catch(SQLException se)
         {
             System.out.print(se);
             return "";
         }
     }
     public synchronized HashMap getImageStreams(String userid, List frnds,long from)
    {
         String query = "select imageid,url,userid,pinnedpeople,vote,albumid,date from status where userid='" + userid;
        for(int i=0;i<frnds.size();i++)
            query+="or userid='"+frnds.get(i);
        query+="order by date desc limit "+from+",100";
        HashMap images=new HashMap();
        try
        {
         pst=con.prepareStatement(query);
         ResultSet rs=pst.executeQuery();
         while (rs.next())
         {
             HashMap image=new HashMap();
             image.put("url", rs.getString("url"));
             image.put("userid", rs.getString("userid"));
             image.put("albumid", rs.getString("albumid"));
             image.put("albumname",this.getAlbumName(rs.getString("albumid")));
             image.put("vote",(List)(new ObjectInputStream(rs.getBlob("vote").getBinaryStream()).readObject()));
             image.put("pinnedpeople",(List)(new ObjectInputStream(rs.getBlob("pinnedpeople").getBinaryStream()).readObject()));
             image.put("date", new java.util.Date(rs.getTimestamp("date").toGMTString()));
             images.put(rs.getString("imageid"), image);
         }
         return images;
        }
        catch(SQLException se)
        {
            System.out.println(se);
            images.clear();
            return images;
        }
        catch(IOException se)
        {
            System.out.println(se);
            images.clear();
            return images;
        }
        catch(ClassNotFoundException se)
        {
            System.out.println(se);
            images.clear();
            return images;
        }
        catch(NullPointerException se)
        {
            System.out.println(se);
            images.clear();
            return images;
        }
     }

     public synchronized long getnotificationcount()
    {
         long l;
         try
         {
            pst=con.prepareStatement("select count(contenturl) from notification where read1=1");
            ResultSet rs=pst.executeQuery();
            if(rs.next())
            {
                return Long.parseLong(rs.getString(1));
            }
            return 0;
         }
         catch(SQLException se)
         {
             System.out.print(se);
             return 0;
         }
     }

     public boolean updateInitAlbums(String userid)
     {
         try
         {
             pst=con.prepareStatement("select albumid from album where userid='"+userid+"' and name='Profilepics'");
             ResultSet rs=pst.executeQuery();
             if(rs.next()){
                 pst=con.prepareStatement("update user_info set propicalbum='"+rs.getString("albumid")+"' where userid='"+userid+"'");
                 pst.executeUpdate();
             }
             /*pst=con.prepareStatement("select albumid from album where userid='"+userid+"' and name='Pinnedpics'");
             ResultSet rs1=pst.executeQuery();
             if(rs1.next()){
                 pst=con.prepareStatement("update user_info set pinnedpicalbum='"+rs1.getString("albumid")+"' where userid='"+userid+"'");
                 pst.executeUpdate();
             }*/
             
             return true;
         }
             catch(SQLException se)
             {
                 System.out.println(se);
                 return false;
             }
     }
     
     public synchronized HashMap getMiniProfile(List userids)
     {
         HashMap uds=new HashMap(),ud=new HashMap();
         try
         {
             if(!userids.isEmpty()){
                 String query="select userid,fname,lname,propic,currentcity,books,musics,movies,celebrities,games,sports,playlist,other,school,college,language,url,adminpages from user_info where ";
                 for(int i=0;i<userids.size()-1;i++)
                     query+="userid='"+userids.get(i)+"' or ";
                 query+="userid='"+userids.get(userids.size()-1)+"'";
                 pst=con.prepareStatement(query);
                       ResultSet rs1=pst.executeQuery();
                    while(rs1.next())
                    {
                        ud.put("username", rs1.getString("fname").concat(" "+rs1.getString("lname")));
                        ud.put("propic", rs1.getString("propic"));
                        ud.put("currentcity", rs1.getString("currentcity"));
                        ud.put("url", rs1.getString("url"));
                        ud.put("adminpages",new ObjectInputStream(rs1.getBlob("school").getBinaryStream()).readObject());
                        List pages=new ArrayList();
                        //HashMap pages1=new HashMap();
                        pages.addAll((List)(new ObjectInputStream(rs1.getBlob("school").getBinaryStream()).readObject()));
                        pages.addAll((List)(new ObjectInputStream(rs1.getBlob("college").getBinaryStream()).readObject()));
                        pages.addAll((List)(new ObjectInputStream(rs1.getBlob("language").getBinaryStream()).readObject()));

                        pages.addAll((List)(new ObjectInputStream(rs1.getBlob("musics").getBinaryStream()).readObject()));
                        pages.addAll((List)(new ObjectInputStream(rs1.getBlob("movies").getBinaryStream()).readObject()));
                        pages.addAll((List)(new ObjectInputStream(rs1.getBlob("books").getBinaryStream()).readObject()));
                        pages.addAll((List)(new ObjectInputStream(rs1.getBlob("celebrities").getBinaryStream()).readObject()));
                        pages.addAll((List)(new ObjectInputStream(rs1.getBlob("games").getBinaryStream()).readObject()));
                        pages.addAll((List)(new ObjectInputStream(rs1.getBlob("sports").getBinaryStream()).readObject()));
                        pages.addAll((List)(new ObjectInputStream(rs1.getBlob("playlist").getBinaryStream()).readObject()));
                        pages.addAll((List)(new ObjectInputStream(rs1.getBlob("other").getBinaryStream()).readObject()));
                        ud.put("pages",pages);
                        HashMap fv=this.getFriendsAndVote(rs1.getString("userid"));
                        ud.put("friends",(List)fv.get("friendlist"));
                        ud.put("votes", (List)fv.get("vote"));
                        uds.put(rs1.getString("userid"), ud);

                    }
                 }
                return uds;
                
         }
         catch(SQLException se)
        {
            System.out.println(se);
            ud.clear();
            return ud;
        }
         catch(IOException se)
        {
            System.out.println(se);
            ud.clear();
            return ud;
        }
        catch(NullPointerException se)
        {
            System.out.println(se);
            ud.clear();
            return ud;
        }
        catch(ClassNotFoundException se)
        {
            System.out.println(se);
            ud.clear();
            return ud;
        }
     }
     
     public synchronized HashMap getMiniProfile(String userid)
     {
         HashMap uds=new HashMap(),ud=new HashMap();
         try
         {
             
                 String query="select userid,fname,lname,propic,currentcity,books,musics,movies,celebrities,games,sports,playlist,other,school,college,language,url,adminpages from user_info where ";
                 query+="userid='"+userid+"'";
                 pst=con.prepareStatement(query);
                       ResultSet rs1=pst.executeQuery();
                    while(rs1.next())
                    {
                        ud.put("username", rs1.getString("fname").concat(" "+rs1.getString("lname")));
                        ud.put("propic", rs1.getString("propic"));
                        ud.put("currentcity", rs1.getString("currentcity"));
                        ud.put("url", rs1.getString("url"));
                        ud.put("adminpages",new ObjectInputStream(rs1.getBlob("adminpages").getBinaryStream()).readObject());
                        List pages=new ArrayList();
                        //HashMap pages1=new HashMap();
                        pages.addAll((List)(new ObjectInputStream(rs1.getBlob("school").getBinaryStream()).readObject()));
                        pages.addAll((List)(new ObjectInputStream(rs1.getBlob("college").getBinaryStream()).readObject()));
                        pages.addAll((List)(new ObjectInputStream(rs1.getBlob("language").getBinaryStream()).readObject()));

                        pages.addAll((List)(new ObjectInputStream(rs1.getBlob("musics").getBinaryStream()).readObject()));
                        pages.addAll((List)(new ObjectInputStream(rs1.getBlob("movies").getBinaryStream()).readObject()));
                        pages.addAll((List)(new ObjectInputStream(rs1.getBlob("books").getBinaryStream()).readObject()));
                        pages.addAll((List)(new ObjectInputStream(rs1.getBlob("celebrities").getBinaryStream()).readObject()));
                        pages.addAll((List)(new ObjectInputStream(rs1.getBlob("games").getBinaryStream()).readObject()));
                        pages.addAll((List)(new ObjectInputStream(rs1.getBlob("sports").getBinaryStream()).readObject()));
                        pages.addAll((List)(new ObjectInputStream(rs1.getBlob("playlist").getBinaryStream()).readObject()));
                        pages.addAll((List)(new ObjectInputStream(rs1.getBlob("other").getBinaryStream()).readObject()));
                        ud.put("pages",pages);
                        HashMap fv=this.getFriendsAndVote(rs1.getString("userid"));
                        ud.put("friends",(List)fv.get("friendlist"));
                        ud.put("votes", (List)fv.get("vote"));
                        
                    }
                return ud;
                
         }
         catch(SQLException se)
        {
            System.out.println(se);
            ud.clear();
            return ud;
        }
         catch(IOException se)
        {
            System.out.println(se);
            ud.clear();
            return ud;
        }
        catch(NullPointerException se)
        {
            System.out.println(se);
            ud.clear();
            return ud;
        }
        catch(ClassNotFoundException se)
        {
            System.out.println(se);
            ud.clear();
            return ud;
        }
     }
     
     public synchronized String createPage(String pagename,String creator,String category,String type)
     {
         try
         {
             List admins=new ArrayList();
             admins.add(creator);
             Random generator = new Random();
             int r = Math.abs(generator.nextInt());
             int r1=Math.abs(generator.nextInt());
             int r2=Math.abs(generator.nextInt());

             pst=con.prepareStatement("insert into freniz values('leaf_"+r+"_"+r1+"','page','leaf?leafid=leaf_"+r+"_"+r1+"')");
             pst.executeUpdate();
             pst=con.prepareStatement("insert into pages (pageid,pagename,type,category,creator,admins,vote,date,url) values('leaf_"+r+"_"+r1+"','"+pagename+"','"+type+"','"+category+"','"+creator+"',?,?,now(),'leaf?leafid=leaf_"+r+"_"+r1+"')");
             pst.setObject(1, admins);
             pst.setObject(2, new ArrayList());
             pst.executeUpdate();
             pst=con.prepareStatement("insert into page_info(pageid) values('leaf_"+r+"_"+r1+"')");
             pst.executeUpdate();
             pst=con.prepareStatement("select adminpages from userid where userid='"+creator+"'");
             ResultSet rs=pst.executeQuery();
             if(rs.next())
             {
                 List adminpages=(List)(new ObjectInputStream(rs.getBlob("adminpages").getBinaryStream()).readObject());
                 if(!adminpages.contains(creator))
                     adminpages.add(creator);
             }
             return "leaf_"+r+"_"+r1;
         }
         catch(SQLException se)
         {
             System.out.println(se);
             return null;
         }
          catch(IOException se)
        {
            System.out.println(se);
            return null;
        }
        catch(NullPointerException se)
        {
            System.out.println(se);
            return null;
        }
        catch(ClassNotFoundException se)
        {
            System.out.println(se);
            return null;
        }
     }
     public synchronized boolean updatePageDescription(String pageid, String description)
    {
         try
         {
             pst=con.prepareStatement("update pages set description='"+description+"' where pageid='"+pageid+"'");
             pst.executeUpdate();
             return true;
         }
         catch(SQLException se)
         {
             System.out.println(se);
             return false;
         }
     }
     public synchronized boolean updatePageAbout(String pageid, String about)
    {
         try
         {
             pst=con.prepareStatement("update pages set about='"+about+"' where pageid='"+pageid+"'");
             pst.executeUpdate();
             return true;
         }
         catch(SQLException se)
         {
             System.out.println(se);
             return false;
         }
     }
     public synchronized boolean updatePageWebsite(String pageid, String website)
    {
         try
         {
             pst=con.prepareStatement("update pages set website='"+website+"' where pageid='"+pageid+"'");
             pst.executeUpdate();
             return true;
         }
         catch(SQLException se)
         {
             System.out.println(se);
             return false;
         }
     }
     public synchronized boolean updatePageViews(String pageid)
    {
         try
         {
             long views=0;
             pst=con.prepareStatement("select views from pages where pageid='"+pageid+"'");
             ResultSet rs=pst.executeQuery();
             if(rs.next())
                 views=rs.getLong("views");
             pst=con.prepareStatement("update pages set views='"+views+"' where pageid='"+pageid+"'");
             pst.executeUpdate();
             return true;
         }
         catch(SQLException se)
         {
             System.out.println(se);
             return false;
         }
     }
     public synchronized boolean updatePageAdmins(String pageid,List admins)
     {
         try
         {
             pst=con.prepareStatement("update pages set admins=? where pageid='"+pageid+"'");
             pst.setObject(1, admins);
             pst.executeUpdate();
             return true;
         }
         catch(SQLException se)
         {
             System.out.println(se);
             return false;
         }
     }
     public synchronized boolean updatePageUserid(String pageid,String newpageid)
     {
         try{
             pst=con.prepareStatement("update freniz set userid='"+newpageid+"',url='"+newpageid+"' where userid='"+pageid+"'");
             pst.executeUpdate();
             return true;
         }
         catch(SQLException se)
         {
             System.out.println(se);
             return false;
         }
     }
     
     public synchronized boolean updatePageVote(String pageid,List vote)
    {
         try
         {
             pst=con.prepareStatement("update pages set vote=? where pageid='"+pageid+"'");
             pst.setObject(1,vote);
             pst.executeUpdate();
             return true;
         }
         catch(SQLException se)
         {
             System.out.println(se);
             return false;
         }
     }
     public synchronized boolean updateReleaseDate(String pageid,java.util.Date date)
     {
         try
         {
             java.text.SimpleDateFormat formatter=new java.text.SimpleDateFormat("yyyy-MM-dd");
             String date1=formatter.format(date);
             pst=con.prepareStatement("update page_info set releasedate='"+date1+"' where pageid='"+pageid+"'");
             pst.executeUpdate();
             return true;
         }
         catch(SQLException se)
         {
             System.out.println(se);
             return false;
         }
     }
     public synchronized boolean updateISBN(String pageid,String ISBN)
     {
         try
         {
             pst=con.prepareStatement("update page_info set isbn='"+ISBN+"' where pageid='"+pageid+"'");
             pst.executeUpdate();
             return true;
         }
         catch(SQLException se)
         {
             System.out.println(se);
             return false;
         }
     }
     public synchronized boolean updatePublisher(String pageid,String publisher)
     {
         try
         {
             pst=con.prepareStatement("update page_info set publisher='"+publisher+"' where pageid='"+pageid+"'");
             pst.executeUpdate();
             return true;
         }
         catch(SQLException se)
         {
             System.out.println(se);
             return false;
         }
     }
     public synchronized boolean updateAuthor(String pageid,String author)
     {
         try
         {
             pst=con.prepareStatement("update page_info set author='"+author+"' where pageid='"+pageid+"'");
             pst.executeUpdate();
             return true;
         }
         catch(SQLException se)
         {
             System.out.println(se);
             return false;
         }
     }
     public synchronized boolean updateStarring(String pageid,String starring)
     {
         try
         {
             pst=con.prepareStatement("update page_info set starring='"+starring+"' where pageid='"+pageid+"'");
             pst.executeUpdate();
             return true;
         }
         catch(SQLException se)
         {
             System.out.println(se);
             return false;
         }
     }
     public synchronized boolean updateDirectedBy(String pageid,String directedby)
     {
         try
         {
             pst=con.prepareStatement("update page_info set directedby='"+directedby+"' where pageid='"+pageid+"'");
             pst.executeUpdate();
             return true;
         }
         catch(SQLException se)
         {
             System.out.println(se);
             return false;
         }
     }
     public synchronized boolean updateProducedBy(String pageid,String producedby)
     {
         try
         {
             pst=con.prepareStatement("update page_info set producedby='"+producedby+"' where pageid='"+pageid+"'");
             pst.executeUpdate();
             return true;
         }
         catch(SQLException se)
         {
             System.out.println(se);
             return false;
         }
     }
     public synchronized boolean updateGenere(String pageid,String genere)
     {
         try
         {
             pst=con.prepareStatement("update page_info set genere='"+genere+"' where pageid='"+pageid+"'");
             pst.executeUpdate();
             return true;
         }
         catch(SQLException se)
         {
             System.out.println(se);
             return false;
         }
     }
     public synchronized boolean updateMembers(String pageid,String members)
     {
         try
         {
             pst=con.prepareStatement("update page_info set members='"+members+"' where pageid='"+pageid+"'");
             pst.executeUpdate();
             return true;
         }
         catch(SQLException se)
         {
             System.out.println(se);
             return false;
         }
     }
     public synchronized boolean updateRecordLabel(String pageid,String recordlabel)
     {
         try
         {
             pst=con.prepareStatement("update page_info set recordlabel='"+recordlabel+"' where pageid='"+pageid+"'");
             pst.executeUpdate();
             return true;
         }
         catch(SQLException se)
         {
             System.out.println(se);
             return false;
         }
     }
     public synchronized boolean updateLocation(String pageid,String location)
     {
         try
         {
             pst=con.prepareStatement("update page_info set location='"+location+"' where pageid='"+pageid+"'");
             pst.executeUpdate();
             return true;
         }
         catch(SQLException se)
         {
             System.out.println(se);
             return false;
         }
     }
     public synchronized boolean updateAwards(String pageid,String awards)
     {
         try
         {
             pst=con.prepareStatement("update page_info set awards='"+awards+"' where pageid='"+pageid+"'");
             pst.executeUpdate();
             return true;
         }
         catch(SQLException se)
         {
             System.out.println(se);
             return false;
         }
     }
     public synchronized boolean updateGender(String pageid,String gender)
     {
         try
         {
             pst=con.prepareStatement("update page_info set gender='"+gender+"' where pageid='"+pageid+"'");
             pst.executeUpdate();
             return true;
         }
         catch(SQLException se)
         {
             System.out.println(se);
             return false;
         }
     }
     public synchronized boolean updateEmail(String pageid,String email)
     {
         try
         {
             pst=con.prepareStatement("update page_info set email='"+email+"' where pageid='"+pageid+"'");
             pst.executeUpdate();
             return true;
         }
         catch(SQLException se)
         {
             System.out.println(se);
             return false;
         }
     }
     public synchronized boolean updateBiography(String pageid,String biography)
     {
         try
         {
             pst=con.prepareStatement("update page_info set biography='"+biography+"' where pageid='"+pageid+"'");
             pst.executeUpdate();
             return true;
         }
         catch(SQLException se)
         {
             System.out.println(se);
             return false;
         }
     }
     public synchronized boolean updateFounded(String pageid,String founded)
     {
         try
         {
             pst=con.prepareStatement("update page_info set founded='"+founded+"' where pageid='"+pageid+"'");
             pst.executeUpdate();
             return true;
         }
         catch(SQLException se)
         {
             System.out.println(se);
             return false;
         }
     }
     public synchronized boolean updateMission(String pageid,String mission)
     {
         try
         {
             pst=con.prepareStatement("update page_info set mission='"+mission+"' where pageid='"+pageid+"'");
             pst.executeUpdate();
             return true;
         }
         catch(SQLException se)
         {
             System.out.println(se);
             return false;
         }
     }
     public synchronized boolean updateRegion(String pageid,String region)
     {
         try
         {
             pst=con.prepareStatement("update page_info set region='"+region+"' where pageid='"+pageid+"'");
             pst.executeUpdate();
             return true;
         }
         catch(SQLException se)
         {
             System.out.println(se);
             return false;
         }
     }
     public synchronized boolean updateCountry(String pageid,String country)
     {
         try
         {
             pst=con.prepareStatement("update page_info set country='"+country+"' where pageid='"+pageid+"'");
             pst.executeUpdate();
             return true;
         }
         catch(SQLException se)
         {
             System.out.println(se);
             return false;
         }
     }
     public synchronized boolean updateOverview(String pageid,String overview)
     {
         try
         {
             pst=con.prepareStatement("update page_info set overview='"+overview+"' where pageid='"+pageid+"'");
             pst.executeUpdate();
             return true;
         }
         catch(SQLException se)
         {
             System.out.println(se);
             return false;
         }
     }
     public synchronized boolean updateNearBy(String pageid,String nearby)
     {
         try
         {
             pst=con.prepareStatement("update page_info set nearby='"+nearby+"' where pageid='"+pageid+"'");
             pst.executeUpdate();
             return true;
         }
         catch(SQLException se)
         {
             System.out.println(se);
             return false;
         }
     }
     public synchronized boolean updateAddress(String pageid,String address)
     {
         try
         {
             pst=con.prepareStatement("update page_info set address='"+address+"' where pageid='"+pageid+"'");
             pst.executeUpdate();
             return true;
         }
         catch(SQLException se)
         {
             System.out.println(se);
             return false;
         }
     }
     public synchronized boolean updateCity(String pageid,String city)
     {
         try
         {
             pst=con.prepareStatement("update page_info set city='"+city+"' where pageid='"+pageid+"'");
             pst.executeUpdate();
             return true;
         }
         catch(SQLException se)
         {
             System.out.println(se);
             return false;
         }
     }
     public synchronized boolean updateZip(String pageid,int zip)
     {
         try
         {
             pst=con.prepareStatement("update page_info set zip='"+zip+"' where pageid='"+pageid+"'");
             pst.executeUpdate();
             return true;
         }
         catch(SQLException se)
         {
             System.out.println(se);
             return false;
         }
     }
     public synchronized boolean updatePhone(String pageid,String phone)
     {
         try
         {
             pst=con.prepareStatement("update page_info set phone='"+phone+"' where pageid='"+pageid+"'");
             pst.executeUpdate();
             return true;
         }
         catch(SQLException se)
         {
             System.out.println(se);
             return false;
         }
     }
     public synchronized boolean updateBday(String pageid,java.util.Date date)
     {
         try
         {
             java.text.SimpleDateFormat formatter=new java.text.SimpleDateFormat("yyyy-MM-dd");
             String date1=formatter.format(date);
             pst=con.prepareStatement("update page_info set bday='"+date1+"' where pageid='"+pageid+"'");
             pst.executeUpdate();
             return true;
         }
         catch(SQLException se)
         {
             System.out.println(se);
             return false;
         }
     }
     
     
     public synchronized HashMap getMiniPage(List pageids)
     {
         HashMap mps=new HashMap(),mp=new HashMap();
         try
         {
             if(!pageids.isEmpty()){
                 String query="select pageid,pagename,category,creator,admins,vote,pagepic,website,views,type,date,url from pages where ";
                 for(int i=0;i<pageids.size()-1;i++)
                     query+="pageid='"+pageids.get(i)+"' or ";
                 query+="pageid='"+pageids.get(pageids.size()-1)+"'";
                 pst=con.prepareStatement(query);
                 ResultSet rs=pst.executeQuery();
                 while(rs.next())
                 {
                     mp.put("username", rs.getString("pagename"));
                     mp.put("category", rs.getString("category"));
                     mp.put("vote", ((new ObjectInputStream(rs.getBlob("vote").getBinaryStream())).readObject()));
                     mp.put("creator", rs.getString("creator"));
                     mp.put("admins", ((new ObjectInputStream(rs.getBlob("admins").getBinaryStream())).readObject()));
                     mp.put("pagepic", rs.getString("pagepic"));
                     mp.put("type", rs.getString("type"));
                     mp.put("date", rs.getString("date"));
                     mp.put("url", rs.getString("url"));
                     mp.put("views", rs.getString("views"));
                     mps.put(rs.getString("pageid"), mp);

                 }
             }
             return mps;
         }
         catch(SQLException se)
        {
            System.out.println(se);
            mps.clear();
            return mps;
        }
        catch(IOException se)
        {
            System.out.println(se);
            mps.clear();
            return mps;
        }
        catch(ClassNotFoundException se)
        {
            System.out.println(se);
            mps.clear();
            return mps;
        }
        catch(NullPointerException se)
        {
            System.out.println(se);
            mps.clear();
            return mps;
        }
     }
     
     public synchronized HashMap getPageDetails(String pageid,List pi)
     {
         HashMap pds=new HashMap(),pd=new HashMap();
         try
         {
             String query="select ";
             for(int i=0;i<pi.size()-1;i++)
                 query+=pi.get(i)+",";
             query=pi.get(pi.size()-1)+" from page_info where pageid='"+pageid+"'";
             pst=con.prepareStatement(query);
             ResultSet rs=pst.executeQuery();
             if(rs.next())
             {
                 for(int i=0;i<pi.size();i++)
                     pd.put(pi.get(i), rs.getString((String)pi.get(i)));
                 pds.put(pageid, pd);
             }
             return pds;
         }
         catch(SQLException se)
         {
             System.out.println(se);
             pds.clear();
             return pds;
         }
         
     }
     
     public synchronized boolean updateSlamBook(String userid,HashMap slambook)
     {
         try{
             pst=con.prepareStatement("select slambook from apps where userid='"+userid+"'");
             ResultSet rs=pst.executeQuery();
             if(rs.next())
             {
                 HashMap slam=(HashMap)(new ObjectInputStream(rs.getBlob("slambook").getBinaryStream()).readObject());
                 slam.putAll(slambook);
                 pst=con.prepareStatement("update apps set slambook=? where userid='"+userid+"'");
                 pst.setObject(1, slam);
                 pst.executeUpdate();
             }
             return true;
         }
        catch(SQLException se)
        {
            System.out.println(se);
            return false;
        }
        catch(IOException se)
        {
            System.out.println(se);
            return false;
        }
        catch(ClassNotFoundException se)
        {
            System.out.println(se);
            return false;
        }
        catch(NullPointerException se)
        {
            System.out.println(se);
            return false;
        }
         
     }
     
     public synchronized boolean updateDiary(String userid,HashMap diary)
     {
         try{
             pst=con.prepareStatement("select diary from apps where userid='"+userid+"'");
             ResultSet rs=pst.executeQuery();
             if(rs.next())
             {
                 HashMap slam=(HashMap)(new ObjectInputStream(rs.getBlob("diary").getBinaryStream()).readObject());
                 slam.putAll(diary);
                 pst=con.prepareStatement("update apps set diary=? where userid='"+userid+"'");
                 pst.setObject(1, slam);
                 pst.executeUpdate();
             }
             return true;
         }
        catch(SQLException se)
        {
            System.out.println(se);
            return false;
        }
        catch(IOException se)
        {
            System.out.println(se);
            return false;
        }
        catch(ClassNotFoundException se)
        {
            System.out.println(se);
            return false;
        }
        catch(NullPointerException se)
        {
            System.out.println(se);
            return false;
        }
         
     }
     public synchronized HashMap getSlamBook(String userid)
     {
         HashMap slambook=new HashMap();
         try
         {
             pst=con.prepareStatement("select slambook from apps where userid='"+userid+"'");
             ResultSet rs=pst.executeQuery();
             if(rs.next())
                 slambook=(HashMap)(new ObjectInputStream(rs.getBlob("slambook").getBinaryStream()).readObject());
             return slambook;
         }
         catch(SQLException se)
        {
            System.out.println(se);
            return slambook;
        }
        catch(IOException se)
        {
            System.out.println(se);
            return slambook;
        }
        catch(ClassNotFoundException se)
        {
            System.out.println(se);
            return slambook;
        }
        catch(NullPointerException se)
        {
            System.out.println(se);
            return slambook;
        }
     }
     public synchronized HashMap getDiary(String userid)
     {
         HashMap diary=new HashMap();
         try
         {
             pst=con.prepareStatement("select diary from apps where userid='"+userid+"'");
             ResultSet rs=pst.executeQuery();
             if(rs.next())
                 diary=(HashMap)(new ObjectInputStream(rs.getBlob("diary").getBinaryStream()).readObject());
             return diary;
         }
         catch(SQLException se)
        {
            System.out.println(se);
            return diary;
        }
        catch(IOException se)
        {
            System.out.println(se);
            return diary;
        }
        catch(ClassNotFoundException se)
        {
            System.out.println(se);
            return diary;
        }
        catch(NullPointerException se)
        {
            System.out.println(se);
            return diary;
        }
     }
     
}
