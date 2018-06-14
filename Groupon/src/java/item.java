/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.*;
import java.sql.*;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author mehpa
 */
@Named(value = "item")
@RequestScoped
public class item {

    /**
     * Creates a new instance of item
     */
      private String sellerid;
      private String password;
    private int productid;
    private String title;
    private String desc;
    private double cost;
    private int quantity;
    private int num;
    
   private ArrayList<product> managedproducts;
    private ArrayList<String> managedproducttitle;
    private String selectedproduct;
   public item(String selleruname,String pass)
   {
       this.sellerid=selleruname;
       this.password=pass;
      
       Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        
        managedproducts=new ArrayList<product>();
        managedproducttitle=new ArrayList<String>();
        try
        {
            final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/patilm5937";
            
            //connect to the database with user name and password
            conn = DriverManager.getConnection(DATABASE_URL, "patilm5937", "1562444");   
            stat = conn.createStatement();
            
            rs = stat.executeQuery("Select * from product where username = '" + sellerid + "'" );
            //add the products under this username
            while(rs.next())
            {
                managedproducts.add(new product(rs.getString(7), rs.getInt(1), rs.getString(2), 
                        rs.getString(3),rs.getDouble(5),rs.getInt(4),rs.getInt(6)));
                //add the product names
                managedproducttitle.add(rs.getString(2));
            }
           
        }
        catch (SQLException e)
        {           
            e.printStackTrace();
        }
        finally
        {
            try
            {
                rs.close();
                stat.close();
                conn.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();         
            }
        }
       
   }

    
   
   
   
   
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

   
   
    public String getSelectedproduct() {
        return selectedproduct;
    }

    public void setSelectedproduct(String selectedproduct) {
        this.selectedproduct = selectedproduct;
    }

    public ArrayList<String> getManagedproducttitle() {
        return managedproducttitle;
    }

    public void setManagedproducttitle(ArrayList<String> managedproducttitle) {
        this.managedproducttitle = managedproducttitle;
    }

    
   

   
   
   

    public ArrayList<product> getManagedproducts() {
        return managedproducts;
    }

    public void setManagedproducts(ArrayList<product> managedproducts) {
        this.managedproducts = managedproducts;
    }
    
    

    
    public String getSellerid() {
        return sellerid;
    }

    public void setSellerid(String sellerid) {
        this.sellerid = sellerid;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
    
    public String additem(){
        
      
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
       
      // String sellerid="";
       
       try{
           conn=DriverManager.getConnection(DB_URL,"patilm5937","1562444");
           stat=conn.createStatement();
           rs=stat.executeQuery("Select * from nextproductid");
           
           if(rs.next()){
               productid=+rs.getInt(1);
           }
           
           int j=stat.executeUpdate("Update nextproductid set "
                        + "NEXTPID = '" +  (rs.getInt(1)+1) + "'");
           
          int r = stat.executeUpdate("insert into product "
                    + "values ('" + productid + "', '" + title + "', '" + desc + "','" + quantity + "','" + cost + "','" 
                  + num + "', '"+ sellerid + "')");
            
             return("confirm3");
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
    
       public List<product> showSelectedProductDetails(){
        int index = -1;
        List<product> temp=new ArrayList<product>();
        for(int i=0; i<managedproducts.size(); i++)
        {
            if(managedproducts.get(i).getTitle().equals(selectedproduct))
            {
                index = i;
                break;
            }
        }
        
        if(index < 0)
        {
            return null;
        }
        else
        {
            temp.add(managedproducts.get(index));
            return temp ;
        }
        
    }

     
    
}
