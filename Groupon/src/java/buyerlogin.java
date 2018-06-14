/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.*;
import java.util.*;

/**
 *
 * @author mehpa
 */
@Named(value = "buyerlogin")
@SessionScoped
public class buyerlogin implements Serializable {

    /**
     * Creates a new instance of buyerlogin
     */
    private String username;
   private String password;
   
   private buyitem buyerdetail;

    public buyitem getBuyerdetail() {
        return buyerdetail;
    }

    public void setBuyerdetail(buyitem buyerdetail) {
        this.buyerdetail = buyerdetail;
    }
   
   

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

    public String buyerlogin(){
         try{
           Class.forName("com.mysql.jdbc.Driver");
       }
       catch(Exception e){
           e.printStackTrace();
           return ("internalerror");
       }
       
       final String DB_URL= "jdbc:mysql://mis-sql.uhcl.edu/patilm5937";
       
       Connection conn=null;
       Statement stat=null;
       ResultSet rs=null;
       
       
       try{
           conn=DriverManager.getConnection(DB_URL,"patilm5937","1562444");
           stat=conn.createStatement();
           rs= stat.executeQuery("Select * from buyaccount "
                    + "where username = '" + 
                    username + "'" );
         // sellerid= rs.getInt(1);
           if(rs.next())
           {
               
               
               if(password.equals(rs.getString(3))){
                   
                   buyerdetail=new buyitem(username,password);
                   
                   return"welcomebuyer";
               }
               else{
                   username = "";
                    password = "";
                    //display loginNotOK.xhtml
                    return "loginnotok";
               }
           }
           else{
               username= "";
                    password = "";
                  //  display loginNotOK.xhtml
                    return "loginnotok";
           }
           
            
       }
       catch(SQLException e){
           e.printStackTrace();
           return"internalerror";
       }
       finally{
           try{
               conn.close();
               stat.close();
               rs.close();
           }
           catch(SQLException e){
               e.printStackTrace();
               return("internal error3");
           }
       }
    }
   
   
    
}
