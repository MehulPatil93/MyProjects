/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uhclgroupon;
/**
 *
 * @author mehpa
 */
public class Listings {
    
    private static int nextproductid=50001;
   private  int productid;
    private String description="";
    private String title="";
    private double price=0.0;
   private int quantity=0;
    private int min_num=0;
    private int sellerid;

   
    
    public Listings(int s,String t,String d,double p,int q,int m){
        productid=nextproductid;
        nextproductid++;
        title=t;
        description=d;
        price=p;
        quantity=q;
        min_num=m;
        sellerid=s;
    }

    public static int getNextproductid() {
        return nextproductid;
    }

    public static void setNextproductid(int nextproductid) {
        Listings.nextproductid = nextproductid;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getMin_num() {
        return min_num;
    }

    public void setMin_num(int min_num) {
        this.min_num = min_num;
    }

    public int getSellerid() {
        return sellerid;
    }

    public void setSellerid(int sellerid) {
        this.sellerid = sellerid;
    }

    
    
}
