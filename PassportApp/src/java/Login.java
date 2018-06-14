/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.faces.context.FacesContext;

/**
 *
 * @author sachin
 */
@Named(value = "login")
@SessionScoped
public class Login implements Serializable {

    /**
     * Creates a new instance of Login
     */
   private String username;
   private String password;
   private VisaApplication user;
  
   
    
   public String login()
   {
       try
       {
           Class.forName("com.mysql.jdbc.Driver");
       }
       catch(Exception e)
       {
           e.printStackTrace();
           return "loginNotOk";
       }
       Connection conn=null;
       Statement stat=null;
       ResultSet rs=null;
       try
       {
           final String URL="jdbc:mysql://mis-sql.uhcl.edu/kales2422";
           conn=DriverManager.getConnection(URL,"kales2422", "1541249");
           stat=conn.createStatement();
           rs=stat.executeQuery("Select * from passportusers where id='"+username+"'");
           if(rs.next())
           {
              if(password.equals(rs.getString(2)))
              {
                  user=new VisaApplication(username);
                  return "welcome";
              }
              else
              {
                  return "loginNotOk";
              }
           }
           else
           {
               return "loginNotOk";
           }
       }
       catch(SQLException e)
       {
           e.printStackTrace();
           return "loginNotOk";
       }
       finally
       {
           try
           {
               rs.close();
               stat.close();
               conn.close();
           }
           catch(SQLException e)
           {
               e.printStackTrace();
               return "loginNotOk";
           }
       }      
        
   }

   public String signOut()
    {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index.xhtml";

        
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

    public VisaApplication getUser() {
        return user;
    }

    public void setUser(VisaApplication user) {
        this.user = user;
    }
   
   
    
}
