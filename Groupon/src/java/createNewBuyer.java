/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import java.util.*;
import java.sql.*;
import javax.faces.*;

/**
 *
 * @author mehpa
 */
@Named(value = "createNewBuyer")
@RequestScoped
public class createNewBuyer {

    /**
     * Creates a new instance of createNewBuyer
     */
    
   private String username;
   private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
   
   public String createBuyer(){
       try{
           Class.forName("com.mysql.jdbc.Driver");
       }
       catch(Exception e){
           return("Internal Error 1");
       }
       
       
       final String Database_URL="jdbc:mysql://mis-sql.uhcl.edu/patilm5937";
       Connection conn=null;
       Statement stat=null;
       ResultSet rs=null;
       
       String buyerid="";
       
       try{
           
           conn=DriverManager.getConnection(Database_URL,"patilm5937","1562444");
           
           stat=conn.createStatement();
           
           rs=stat.executeQuery("Select * from nextbuyerid");
           
           if(rs.next()){
               buyerid=""+rs.getInt(1);
           }
           
           int j=stat.executeUpdate("Update nextbuyerid set "
                        + "NEXTBID = '" +  (rs.getInt(1)+1) + "'");
           
           int r = stat.executeUpdate("insert into buyaccount "
                    + "values ('" + buyerid + "', '" + username + "', '"+ password + "')");
           
           
           return("Congratulations you have created your account successfully");
       }
       catch(SQLException e){
           e.printStackTrace();
           return ("Internal Error 2");
       }
       finally{
           try{
              conn.close();
              stat.close();
              rs.close();
           }
           catch(SQLException e){
               e.printStackTrace();
           }
       }
   }
    
   
    
}
