/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import java.util.*;
import java.sql.*;
/**
 *
 * @author mehpa
 */
@Named(value = "buyitem")
@RequestScoped
public class buyitem {
        private String sell;
    private String title;
    private String buyerid;
    private String password;
    private String selecteditem;
    private int orderid;
    private ArrayList<product> productsfordisplay;
   private ArrayList<String> productfordisplayid;
   private ArrayList<Listings> orders;
   private String key;
  //  private ArrayList<product> productsforsearch;
   private ArrayList<String> productnameforsearch;
   
   public buyitem(String buyername,String pass){
       buyerid=buyername;
       password=pass;
       selecteditem="";
       
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        
        productsfordisplay=new ArrayList<product>();
        productfordisplayid=new ArrayList<String>();
        try
        {
            final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/patilm5937";
            
            //connect to the database with user name and password
            conn = DriverManager.getConnection(DATABASE_URL, "patilm5937", "1562444");   
            stat = conn.createStatement();
            
            rs = stat.executeQuery("Select * from product " );
            //add the products under this username
            while(rs.next())
            {
                productsfordisplay.add(new product(rs.getString(7), rs.getInt(1), rs.getString(2), 
                        rs.getString(3),rs.getDouble(5),rs.getInt(4),rs.getInt(6)));
                //add the product names
                productfordisplayid.add(rs.getString(2));
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

    public String getSell() {
        return sell;
    }

    public void setSell(String sell) {
        this.sell = sell;
    }

    public ArrayList<Listings> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Listings> orders) {
        this.orders = orders;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

//    public ArrayList<product> getProductsforsearch() {
//        return productsforsearch;
//    }
//
//    public void setProductsforsearch(ArrayList<product> productsforsearch) {
//        this.productsforsearch = productsforsearch;
//    }

    public ArrayList<String> getProductnameforsearch() {
        return productnameforsearch;
    }

    public void setProductnameforsearch(ArrayList<String> productnameforsearch) {
        this.productnameforsearch = productnameforsearch;
    }
   

   
   
    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
   
   

    public String getBuyerid() {
        return buyerid;
    }

    public void setBuyerid(String buyerid) {
        this.buyerid = buyerid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSelecteditem() {
        return selecteditem;
    }

    public void setSelecteditem(String selecteditem) {
        this.selecteditem = selecteditem;
    }

    public ArrayList<product> getProductsfordisplay() {
        return productsfordisplay;
    }

    public void setProductsfordisplay(ArrayList<product> productsfordisplay) {
        this.productsfordisplay = productsfordisplay;
    }

    public ArrayList<String> getProductfordisplayid() {
        return productfordisplayid;
    }

    public void setProductfordisplayid(ArrayList<String> productfordisplayid) {
        this.productfordisplayid = productfordisplayid;
    }
   
    
    
    public String additem(String item){
      title=item;
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
      // ResultSet rs1=null,
    
       
       try{
           conn=DriverManager.getConnection(DB_URL,"patilm5937","1562444");
           stat=conn.createStatement();
           rs=stat.executeQuery("SELECT * FROM buyerorder WHERE buyername = '" + buyerid + "' and title='" + item + "'");
           if(rs.next()){
               return ("error already bought");
           }
           else{
              rs=stat.executeQuery("Select * from product where title='"+item+"'");
              if(rs.next())
              {
                  int stockno=rs.getInt(4);
                  int avano=rs.getInt(6);
                   String status="invalid";
                   if(stockno>0){
                       if(avano==0){
                           status="valid";
                           
                           rs=stat.executeQuery("Select * from nextbuyerorderid");
           
                        if(rs.next())
                        {
                            orderid=+rs.getInt(1);
                         }
                        else{
                            return "error";
                        }
           
                          int j=stat.executeUpdate("Update nextbuyerorderid set "
                         + "nextoid = '" +  (rs.getInt(1)+1) + "'");
           
               
                            sell=getSeller(item);

                              int r = stat.executeUpdate("insert into buyerorder "
                          + "values ('" + orderid + "', '" + buyerid + "', '" + title + "','"
                                      + sell + "','" + status + "')");
                              
                             stockno=stockno-1;
                              avano=avano-1;
                               int z=stat.executeUpdate("update product set quantity='" + stockno + "' where title='"+title+"'");
                              int y=stat.executeUpdate("update product set num='" + avano + "' where title='"+title+"'");
                               int a1=stat.executeUpdate("update buyerorder set status='valid' where title='"+title+"'");  
                            return("product bought Successfully");
                           
                       }else{
                           
                           rs=stat.executeQuery("Select * from nextbuyerorderid");
           
                        if(rs.next())
                        {
                            orderid=+rs.getInt(1);
                         }
                        else{
                            return "error";
                        }
           
                          int j=stat.executeUpdate("Update nextbuyerorderid set "
                         + "nextoid = '" +  (rs.getInt(1)+1) + "'");
           
               
                            sell=getSeller(item);

                              int r = stat.executeUpdate("insert into buyerorder "
                          + "values ('" + orderid + "', '" + buyerid + "', '" + title + "','"
                                      + sell + "','" + status + "')");
                              stockno=stockno-1;
                              avano=avano-1;
                               int z=stat.executeUpdate("update product set quantity='" + stockno + "' where title='"+title+"'");
                              int y=stat.executeUpdate("update product set num='" + avano + "' where title='"+title+"'");
            
            
                            return("product bought Successfully but w8 for active");
                 
                       }
                       
                   }else{
                       return("out of stock");
                   }
               }else{
                  return ("no item found");
              }
              
              }
               
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
//               rs1.close();
              
           }
           catch(SQLException e){
               e.printStackTrace();
               return("internal error3");
           }
       }
       }
    
    public void loadHistory(){
         orders=new ArrayList<Listings>();
         Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
         try
        {
            final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/patilm5937";
            
            //connect to the database with user name and password
            conn = DriverManager.getConnection(DATABASE_URL, "patilm5937", "1562444");   
            stat = conn.createStatement();
            
            rs = stat.executeQuery("Select * from buyerorder where buyername='"+buyerid+"'" );
            //add the products under this username
            while(rs.next())
            {
                orders.add(new Listings(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
                //add the product names
               // productfordisplayid.add(rs.getString(2));
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
        } }
    
    public List<Listings> showHistory(){
        ArrayList<Listings> showorders=new ArrayList<Listings>();
        for(int i=0;i<orders.size();i++){
            if(orders.get(i).getBuyername().equals(buyerid)){
                showorders.add(orders.get(i));
            }
        }
        return showorders;
    }
    
    public String getSeller(String item){
        
        String sellername="";
        
        for(int i=0;i<productsfordisplay.size();i++){
            if(productsfordisplay.get(i).getTitle().equals(item)){
                sellername=productsfordisplay.get(i).getSellerid();
            }
        }
        
        return sellername;
        

        
    }
    
    public List<product> showSelectedItemDetails(){
        int index = -1;
        List<product> temp=new ArrayList<product>();
        for(int i=0; i<productsfordisplay.size(); i++)
        {
            if(productsfordisplay.get(i).getTitle().equals(selecteditem))
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
            temp.add(productsfordisplay.get(index));
            return temp ;
        }
        
    }
    
    public void searchProduct(){
        
        productnameforsearch =new ArrayList<String>();
        for(int i=0;i<productfordisplayid.size();i++){
            if(productfordisplayid.get(i).contains(key)){
              
                productnameforsearch.add(productfordisplayid.get(i));
            }
        }
    }
    
    
    
}
