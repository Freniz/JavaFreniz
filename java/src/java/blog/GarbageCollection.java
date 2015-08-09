/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blog;

/**
 *
 * @author abdulnizam
 */
public class GarbageCollection 
{
   int size = 1000000;
public   void useMemory(javax.servlet.http.HttpSession session) 
{
      for (int i=0; i<size; i++) 
{
    java.util.HashMap intA = new java.util.HashMap();
        for(int j=0;j<1000;j++)
    intA.put("meeran", (session.getAttribute("object")));
      }
   }
   
      
}