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
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author sachin
 */
@Named(value = "visaApplication")
@RequestScoped
public class VisaApplication {

    /**
     * Creates a new instance of VisaApplication
     */
   private String nation;
   private String passportNumber;
   private String type;
   private String field;
   private String time;
   private String spousePassportNumber;
   private int applicationId;
   private String username;
   private String stat;
   private String applicationToSearch;
   private ArrayList<String>applications;
   private ArrayList<VisaApplication>detailapplication;
   private String valueforprint;
   

    public VisaApplication(int applicationID,String nation, String passportNumber, String type, String field, String time, String spousePassportNumber,String status) {
        this.applicationId=applicationID;
        this.nation = nation;
        this.passportNumber = passportNumber;
        this.type = type;
        this.field = field;
        this.time = time;
        this.spousePassportNumber = spousePassportNumber;
        this.stat=status;
    }

    public String getValueforprint() {
        return valueforprint;
    }

    public void setValueforprint(String valueforprint) {
        this.valueforprint = valueforprint;
    }

    
    public String getUsername() {
        return username;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public ArrayList<String> getApplications() {
        return applications;
    }

    public void setApplications(ArrayList<String> applications) {
        this.applications = applications;
    }

    public ArrayList<VisaApplication> getDetailapplication() {
        return detailapplication;
    }

    public void setDetailapplication(ArrayList<VisaApplication> detailapplication) {
        this.detailapplication = detailapplication;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getApplicationToSearch() {
        return applicationToSearch;
    }

    public void setApplicationToSearch(String applicationToSearch) {
        this.applicationToSearch = applicationToSearch;
    }

    
    public VisaApplication(String username)
    {
        this.username=username;
    }
    
    public String print()
    {
        CreatePage c=new CreatePage();
        return c.create(valueforprint);
        
    }
    
    public List<VisaApplication> showDetails()
    {
        ArrayList<VisaApplication> temp=new ArrayList<VisaApplication>();
        //detailapplication=new ArrayList<VisaApplication>();
        for(int i=0;i<detailapplication.size();i++)
        {
            String a=""+detailapplication.get(i).getApplicationId();
            if(a.equals(applicationToSearch))
            {
                temp.add(detailapplication.get(i));
               
            }
        }
        return temp;
    }
    
    public List<String> search()
    {
        applications=new ArrayList<String>();
        detailapplication=new ArrayList<VisaApplication>();
        Connection conn=null;
        Statement stat=null;
        ResultSet rs=null;
        
        try
        {
            final String URL="jdbc:mysql://mis-sql.uhcl.edu/kales2422";
            conn=DriverManager.getConnection(URL,"kales2422","1541249");
            stat=conn.createStatement();
            rs=stat.executeQuery("Select * from visaapplication where passportnumber='"+passportNumber+"'");
            while(rs.next())
            {
                String sax=""+rs.getInt(1);
                applications.add(sax);
                detailapplication.add(new VisaApplication(rs.getInt(1),rs.getString(3),rs.getString(2),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)));
                
            }
            return applications;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        
    }
    
    public String start()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return "driver problem";
        }
        Connection conn=null;
        Statement stat=null;
        ResultSet rs=null;
        
        try
        {
            final String URL="jdbc:mysql://mis-sql.uhcl.edu/kales2422";
            conn=DriverManager.getConnection(URL,"kales2422","1541249");
            stat=conn.createStatement();
            rs=stat.executeQuery("Select * from visaapplication where passportnumber='"+passportNumber+"'");
            
            if(type.equalsIgnoreCase("F1"))
            {
                if(rs.next())
                {
                    
                if(rs.getString(6).equalsIgnoreCase(time)&&(!rs.getString(3).equals("CHN")||!rs.getString(3).equals("VEN")))
                {
                  return "Application rejected since applied in same month of year";
                }
                
              else if(rs.getString(3).equals("CHN")&& rs.getString(5).equals("SCI")||rs.getString(3).equals("VEN") && rs.getString(5).equals("SCI"))
              {
                  rs=stat.executeQuery("Select * from nextapplicationid");
                  if(!rs.next())
                  {
                      return "innternal error";
                  }
                  else
                  {
                      applicationId=1+rs.getInt(1);
                      String status="Admin Proc";
                      int a=stat.executeUpdate("insert into visaapplication values('"+applicationId+"','"+passportNumber+"','"+nation+"','"+type+"','"+field+"','"+time+"','"+spousePassportNumber+"','"+status+"')");
                      int z=stat.executeUpdate("update nextapplicationid set id='"+applicationId+"'");
                      return "Application on hold";
                  }
                                       
              }
              else if(rs.getString(8).equalsIgnoreCase("Rejected"))
              {
                  rs=stat.executeQuery("Select * from nextapplicationid");
                  if(!rs.next())
                  {
                      return "innternal error";
                  }
                  else
                  {
                  applicationId=1+rs.getInt(1);
                  String status="Amin Proc";
                  int a=stat.executeUpdate("insert into visaapplication values('"+applicationId+"','"+passportNumber+"','"+nation+"','"+type+"','"+field+"','"+time+"','"+spousePassportNumber+"','"+status+"')");
                   int z=stat.executeUpdate("update nextapplicationid set id='"+applicationId+"'");
                    return "Application on hold Your application ID is "+applicationId;
                  }
              }
              else if(rs.getString(8).equalsIgnoreCase("Admin Proc"))
              {
                  rs=stat.executeQuery("Select * from nextapplicationid");
                  if(!rs.next())
                  {
                      return "innternal error";
                  }
                  else
                  {
                  applicationId=1+rs.getInt(1);
                  String status="Rejected";
                  int a=stat.executeUpdate("insert into visaapplication values('"+applicationId+"','"+passportNumber+"','"+nation+"','"+type+"','"+field+"','"+time+"','"+spousePassportNumber+"','"+status+"')");
                  int z=stat.executeUpdate("update nextapplicationid set id='"+applicationId+"'");
                   return "Application Rejected since prevoius application was in Admin Proc";
                  }
              }
              else
              {
                  rs=stat.executeQuery("Select * from nextapplicationid");
                  if(!rs.next())
                  {
                      return "innternal error";
                  }
                  else
                  {
                  applicationId=1+rs.getInt(1);
                  String status="Approved";
                  int a=stat.executeUpdate("insert into visaapplication values('"+applicationId+"','"+passportNumber+"','"+nation+"','"+type+"','"+field+"','"+time+"','"+spousePassportNumber+"','"+status+"')");
                  int z=stat.executeUpdate("update nextapplicationid set id='"+applicationId+"'");
                   return "Application Approved Your application ID is "+applicationId;
                  }
              }
            }
            else
            {
                if(rs.getString(5).equals("SCI")||rs.getString(3).equals("VEN"))
                {
                    rs=stat.executeQuery("Select * from nextapplicationid");
                if(!rs.next())
                {
                    return "error in nextapplication id";
                }
                applicationId=1+rs.getInt(1);
                String status="Admin Proc";
                int a=stat.executeUpdate("insert into visaapplication values('"+applicationId+"','"+passportNumber+"','"+nation+"','"+type+"','"+field+"','"+time+"','"+spousePassportNumber+"','"+status+"')");
                int z=stat.executeUpdate("update nextapplicationid set id='"+applicationId+"'");
                return "Application On hold Your application ID is "+applicationId;
                }
                else
                {
                rs=stat.executeQuery("Select * from nextapplicationid");
                if(!rs.next())
                {
                    return "error in nextapplication id";
                }
                applicationId=1+rs.getInt(1);
                String status="Approved";
                int a=stat.executeUpdate("insert into visaapplication values('"+applicationId+"','"+passportNumber+"','"+nation+"','"+type+"','"+field+"','"+time+"','"+spousePassportNumber+"','"+status+"')");
                int z=stat.executeUpdate("update nextapplicationid set id='"+applicationId+"'");
                return "Application Approved Your application ID is "+applicationId;
                }
            }
            
            }
            else//visa application for F2
            {
                
              rs=stat.executeQuery("Select * from visaapplication where spousepassportnumber='"+passportNumber+"'"); 
              if(rs.next())
              {
                if(rs.getString(8).equalsIgnoreCase("Rejected"))
                 {
                     rs=stat.executeQuery("Select * from nextapplicationid");
                  if(!rs.next())
                  {
                      return "innternal error";
                  }
                  else
                  {
                  applicationId=1+rs.getInt(1);
                  String status="Amin Proc";
                  int a=stat.executeUpdate("insert into visaapplication values('"+applicationId+"','"+passportNumber+"','"+nation+"','"+type+"','"+field+"','"+time+"','"+spousePassportNumber+"','"+status+"')");
                   int z=stat.executeUpdate("update nextapplicationid set id='"+applicationId+"'");
                    return "Application on hold Your application ID is "+applicationId;
                  }
                 }
              else if(rs.getString(8).equalsIgnoreCase("Admin Proc"))
                {
                    rs=stat.executeQuery("Select * from nextapplicationid");
                  if(!rs.next())
                  {
                      return "innternal error";
                  }
                  else
                  {
                  applicationId=1+rs.getInt(1);
                  String status="Rejected";
                  int a=stat.executeUpdate("insert into visaapplication values('"+applicationId+"','"+passportNumber+"','"+nation+"','"+type+"','"+field+"','"+time+"','"+spousePassportNumber+"','"+status+"')");
                  int z=stat.executeUpdate("update nextapplicationid set id='"+applicationId+"'");
                   return "Application Rejected since prevoius application was in Admin Proc";
                  }
                 }
              else
                {
                    rs=stat.executeQuery("Select * from nextapplicationid");
                  if(!rs.next())
                  {
                      return "innternal error";
                  }
                  else
                  {
                  applicationId=1+rs.getInt(1);
                  String status="Approved";
                  int a=stat.executeUpdate("insert into visaapplication values('"+applicationId+"','"+passportNumber+"','"+nation+"','"+type+"','"+field+"','"+time+"','"+spousePassportNumber+"','"+status+"')");
                  int z=stat.executeUpdate("Update nextapplicationid set id='"+applicationId+"'");
                   return "Application Approved Your application ID is "+applicationId;
                  }
                 }  
              }
              else
              {
                  return "Application rejected Since no related person";
              }
                    
                
                
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return "connection error";
        }
    }
    
    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    
    
    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSpousePassportNumber() {
        return spousePassportNumber;
    }

    public void setSpousePassportNumber(String spousePassportNumber) {
        this.spousePassportNumber = spousePassportNumber;
    }
   
    
}
