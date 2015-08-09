package blog;

import java.sql.*;
import java.io.*;
import java.util.*;
public class MainClass
{
Statement st;
ResultSet rs;
Blob b;
public String name,eid,college,dept,sex,curcity,homtown;
public int year;
public java.util.Date bday;
public MainClass()
{
try
{
    Class.forName("com.mysql.jdbc.Driver");
    Connection con=DriverManager.getConnection("Jdbc:Mysql://localhost/nizam","nizam","ajith786");
    st=con.createStatement();
    
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
public MainClass getData(String uid)
{
try{
rs=st.executeQuery("select * from maintable where userid='"+uid+"'");
rs.next();
name=rs.getString(2);
eid=rs.getString(3);
college=rs.getString(4);
dept=rs.getString(5);
year=Integer.parseInt(rs.getString(6));
bday=rs.getDate(7);
sex=rs.getString(8);
curcity=rs.getString(9);
homtown=rs.getString(10);
}
catch(SQLException se)
{
System.out.println(se);
}
	return this;
}

public void updateProfile(String uid,String name,String eid,String coll,String dept,Integer yr,java.util.Date bday,String sex,String curcity,String homtown)
{
try{
st.executeUpdate("update maintable set name1="+name+",eid="+eid+",college="+coll+",dept="+dept+",year1="+yr+",bday="+bday+",sex="+sex+",curcity="+curcity+",homtown="+homtown+"where userid="+uid);
}
catch(SQLException se)
{
System.out.println(se);
}}

public void newPost(String uid,String post)
{
int temp;
try{
rs=st.executeQuery("select blogid from keytable");
rs.next();
temp=Integer.parseInt(rs.getString(1));
st.executeUpdate("Insert into blogtable values('"+(temp+1)+"','"+uid+"','"+post+"')");
st.executeUpdate("update keytable set blogid='"+(temp+1)+"'");
}
catch(SQLException se)
{
System.out.println(se);
}

}


public void comment(String uid,String bid,String cmnt)
{
int temp;
try{
rs=st.executeQuery("select commentid from keytable");
rs.next();
temp=Integer.parseInt(rs.getString(1));
st.executeUpdate("insert into commenttable('"+temp+1+"','"+uid+"','"+bid+"','"+cmnt+"')");
}
catch(SQLException se)
{
System.out.println(se);
}
}

public void deletePost(String bid)
{
try{
st.executeUpdate("delete from commenttable where blogid="+bid);
st.executeUpdate("delete from blogtable where blogid="+bid);
}
catch(SQLException se)
{
System.out.println(se);
}
}

public String[] getPost(String uid)
{
String temp[]=new String[100];
try{
rs=st.executeQuery("select post from blogtable where userid='"+uid+"'");
for(int i=0;rs.next();i++)
{
temp[i]=rs.getString(1);
}
}
catch(SQLException se)
{
System.out.println(se);
}
return temp;
}


public String[] getComment(String bid)
{
String temp[]=new String[200];
try{
rs=st.executeQuery("select comment from commenttable where blogid="+bid);
for(int i=0;rs.next();i++)
{
temp[i]=rs.getString(i+1);
}
}
catch(SQLException se)
{
System.out.println(se);
}return temp;
}
/*public void frndlist()
    {
try{

    rs=st.executeQuery("select * from blobtable");
    rs.next();
    System.out.println(rs.getString(1));

    b=rs.getBlob(2);
    System.out.println("meeran");
    ObjectOutputStream os=new ObjectOutputStream(b.setBinaryStream(1));
   System.out.println("meeran1");
    String s="meeran";
    List a= new ArrayList();
    a.add(s);
    System.out.println(a.size());
    System.out.println(a);
    os.writeObject(a);
    st.execute("update blobtable set list='"+b+"'");
    
    rs= st.executeQuery("select list from blobtable where id='1'");
    rs.next();
    b=rs.getBlob(1);
    
    Object data=(Object)b.getBytes(1, (int)b.length());
    System.out.println(data.toString());

    List a1=(List) data;
    System.out.println(a1);
    System.out.println(a1.get(1));
        }

catch(SQLException se)
{
    System.out.println(se);
        }
catch(ClassNotFoundException ce)
{
System.out.println(ce);
}
catch(IOException ie)
{
    System.out.println(ie);
        }

    }*/

public void frndlist()
    {
    try
    {
        String s="meeran";
        String s1="meeran1";
        String s2="meeran2";
        String s3="meeran3";
        String a1;
        String[] a2;
        List a=new ArrayList();
        a.add(s);a.add(s1);a.add(s2);a.add(s3);a.add(new java.util.Date());
        System.out.println(a+"\nmeeran");
        //st.execute("insert into blobtable values('1','"+a+"')");
        st.execute("insert into blobtable values('1','"+(Object)a+"')");
        rs=st.executeQuery("select * from blobtable");
        rs.next();
        List a3=(List)rs.getArray(2);
        for(int i=1;i<=a3.size();i++)
        {
            System.out.println(a3.get(i));
        }
        System.out.println("nizam nizam");
       rs=st.executeQuery("select * from blobtable");
        rs.next();
        a1=rs.getString(2);
        a2=a1.substring(1,a1.length()-2).split(",");
        for(int i=0;i<a2.length;i++)
        System.out.println(a2[i].trim());
        }
    catch(SQLException se)
{
    System.out.println(se);
        }

    }

}

