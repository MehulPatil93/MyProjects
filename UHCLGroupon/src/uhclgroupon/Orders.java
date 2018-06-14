/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uhclgroupon;
import java.util.*;
/**
 *
 * @author mehpa
 */
public class Orders {
    private static int nextorderid=600001;
    private int orderid;
    private int productid;
    private int buyerid;
    private int sellerid;
    private String itemtitle="";
    private int min_num;
    private double itemprice;
    private String status;
    private String date;
    
    
    
    public Orders(String da,int pd,String it,int b,int se,String t,int m,double p){
        orderid=nextorderid;
        nextorderid++;
        productid=pd;
        itemtitle=it;
        buyerid=b;
        sellerid=se;
        min_num=m;
        itemprice=p;
        status="inactive";
        date=da;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    
    
    public static int getNextorderid() {
        return nextorderid;
    }

    public static void setNextorderid(int nextorderid) {
        Orders.nextorderid = nextorderid;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public int getBuyerid() {
        return buyerid;
    }

    public void setBuyerid(int buyerid) {
        this.buyerid = buyerid;
    }

    public int getSellerid() {
        return sellerid;
    }

    public void setSellerid(int sellerid) {
        this.sellerid = sellerid;
    }

    public String getItemtitle() {
        return itemtitle;
    }

    public void setItemtitle(String itemtitle) {
        this.itemtitle = itemtitle;
    }

    public int getMin_num() {
        return min_num;
    }

    public void setMin_num(int min_num) {
        this.min_num = min_num;
    }

    public double getItemprice() {
        return itemprice;
    }

    public void setItemprice(double itemprice) {
        this.itemprice = itemprice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
  
    
    
    
}
