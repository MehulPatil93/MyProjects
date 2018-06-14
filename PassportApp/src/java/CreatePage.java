/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author sachin
 */
@Named(value = "fileWriter")
@RequestScoped
public class CreatePage {

    /**
     * Creates a new instance of CreatePage
     */

   public String create(String AppId)
   {
       int applicationId=Integer.parseInt(AppId);
       
       Connection conn=null;
       Statement stat=null;
       ResultSet rs=null;
       
       try
       {
           FileWriter fw=new FileWriter("C:\\Users\\sachin\\Documents\\FilePracticeWeb\\data.txt");
           BufferedWriter bw=new BufferedWriter(fw);
           final String URL="jdbc:mysql://mis-sql.uhcl.edu/kales2422";
           conn=DriverManager.getConnection(URL,"kales2422","1541249");
           stat=conn.createStatement();
           rs=stat.executeQuery("Select * from visaapplication where applicationid='"+applicationId+"'");
           if(rs.next())
           {
               bw.write("Passport Number:"+rs.getString(2));
               bw.newLine();
               bw.write("Nationality:"+rs.getString(3));
               bw.newLine();
               bw.write("Type of visa:"+rs.getString(4));
               bw.newLine();
               bw.write("Field of study:"+rs.getString(5));
               bw.newLine();
               bw.write("Time of Application:"+rs.getString(6));
               bw.newLine();
               bw.write("Status of Application:"+rs.getString(8));
               bw.newLine();
               bw.close();
               fw.close();
               return "Word format of the application has been created";
           }
           else
           {
               bw.close();
               return "No application found";
           }
           
       }
       catch(IOException e)
       {
           e.printStackTrace();
           return "IO error";
       }
       catch(SQLException e)
       {
           e.printStackTrace();
           return "sql error";
       }
       finally
       {
           
           try
           {
             rs.close();;
             stat.close();
             conn.close();
           }
           catch(SQLException e)
           {
               e.printStackTrace();
               return "error while close";
           }
       }
        
       
   }
    
}
