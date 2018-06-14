/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uhclgroupon;

import java.util.Scanner;

/**
 *
 * @author mehpa
 */
public class Seller {
    
    
    private static int nextSellerid=100001;
private int sellerid;
private String fname;
private String lname;

private String username;
private String password;
    
public Seller(String f,String l,String u,String pw){
    sellerid=nextSellerid;
    nextSellerid++;
    fname=f;
    lname=l;
    
    username=u;
    password=pw;
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

    public int getSellerid() {
        return sellerid;
    }

    public void setSellerid(int sellerid) {
        this.sellerid = sellerid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

   

   
    
    
    public static void MainPage(int s){
        Scanner input=new Scanner(System.in);
        String selection="";
        int sellerid=s;
        while(!selection.equals("x")){
            System.out.println("Welcome to sellers Page");
            System.out.println("1: Add product or service to sell");
            System.out.println("2: List all the products & services you have listed");
            System.out.println("x: To go back");
            
            selection=input.next();
            
            if(selection.equals("1")){
                
                addListing( sellerid);        
                
            }
            else if(selection.equals("2")){
                showListings(sellerid);
                
            }
            else if(selection.equals("x")){
                
                
            }
            else{
                ;
            }
        }
    }
    
    public static void addListing(int s){
        Scanner input=new Scanner(System.in);
        String selection="";
         while(!selection.equals("x")){
            System.out.println("Welcome to sellers Page");
            System.out.println("1: Add product  to sell");
            System.out.println("2: Add service to sell");
            System.out.println("x: To go back");
            
            selection=input.next();
            
            if(selection.equals("1")){
                
               System.out.println("Enter the product title");
               String title=input.next();
               System.out.println("Enter product descripition");
               String description=input.next();
               System.out.println("Enter the price for the product");
               double price=input.nextDouble();
               System.out.println("Enter product quantity"); 
               int quantity=input.nextInt();
               System.out.println("Enter no. of orders to activate the product");
               int min_num=input.nextInt();
               
               Listings aNew=new Listings(s,title,description,price,quantity,min_num);
               UHCLGroupon.allProducts.add(aNew);
               System.out.println("Product is added Successfully");
               
            }
            else if(selection.equals("2")){
                System.out.println("Enter the Service title");
               String title=input.next();
               System.out.println("Enter Service descripition");
               String description=input.next();
               System.out.println("Enter the price for the Service");
               double price=input.nextDouble();
               System.out.println("Enter the quantity"); 
               int quantity=input.nextInt();
               System.out.println("Enter no. of orders to activate the Service");
               int min_num=input.nextInt();
               
               Listings aNew=new Listings(s,title,description,price,quantity,min_num);
                UHCLGroupon.allServices.add(aNew);
                System.out.println("Service added Successfully");
            }
            else if(selection.equals("x")){
                break;
                
            }
            else{
                ;
            }
        }
        
    }
    
    public static void showListings(int s){
        int sellerid=s;
        int countp=1,counts=1;
        System.out.println("My products to sell are:");
        
        for(int i=0;i<UHCLGroupon.allProducts.size();i++){
            if(UHCLGroupon.allProducts.get(i).getSellerid()==sellerid){
                System.out.printf("%d: %s \n",countp,UHCLGroupon.allProducts.get(i).getTitle());
                countp++;
            }
        }
        
        System.out.println("My services to provide are:");
        for(int i=0;i<UHCLGroupon.allServices.size();i++){
            if(UHCLGroupon.allServices.get(i).getSellerid()==sellerid){
                System.out.printf("%d: %s \n",counts,UHCLGroupon.allServices.get(i).getTitle());
                counts++;
            }
        }
    }
    
    
    
    
    
    
}
