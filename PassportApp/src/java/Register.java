/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
@Named(value = "register")
@RequestScoped
public class Register {

    /**
     * Creates a new instance of Register
     */
   private String username;
   private String password;
   
   
    public String register()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return "error 1";
        }
        Connection conn=null;
        Statement stat=null;
        ResultSet rs=null;
        
        try
        {
            final String URL="jdbc:mysql://mis-sql.uhcl.edu/kales2422";
            conn=DriverManager.getConnection(URL,"kales2422","1541249");
            stat=conn.createStatement();
            rs=stat.executeQuery("Select * from passportusers where id='"+username+"'");
            if(rs.next())
            {
                return"Username already used,Please enter different Username!!";
            }
            else
            {
                int r=stat.executeUpdate("insert into passportusers values('"+username+"','"+password+"')");
                return "!!Registration Successfull!!,Please return to login";
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return "error 2";
        }
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
    
}
