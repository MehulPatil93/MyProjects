/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import java.sql.*;

/**
 *
 * @author mehpa
 */
@Named(value = "createNewSeller")
@RequestScoped
public class createNewSeller {

    /**
     * Creates a new instance of createNewSeller
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
   
   public String createSeller(){
       
       try{
           Class.forName("com.mysql.jdbc.Driver");
       }
       catch(Exception e){
           return ("Internal error1");
       }
       
       final String DB_URL="jdbc:mysql://mis-sql.uhcl.edu/patilm5937";
       Connection conn=null;
       Statement stat=null;
       ResultSet rs=null;
       
       String sellerid="";
       
       try{
           conn=DriverManager.getConnection(DB_URL,"patilm5937","1562444");
           stat=conn.createStatement();
           rs=stat.executeQuery("Select * from nextsellerid");
           
           if(rs.next()){
               sellerid=""+rs.getInt(1);
           }
           
           int j=stat.executeUpdate("Update nextsellerid set "
                        + "NEXTSID = '" +  (rs.getInt(1)+1) + "'");
           
          int r = stat.executeUpdate("insert into sellaccount "
                    + "values ('" + sellerid + "', '" + username + "', '"+ password + "')");
            
             return("Congratulations you have created your account successfully");
       }
       catch(SQLException e){
           e.printStackTrace();
           return("internal error 2");
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
