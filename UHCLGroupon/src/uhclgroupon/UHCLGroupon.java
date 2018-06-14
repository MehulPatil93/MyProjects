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
public class UHCLGroupon {

    /**
     * @param args the command line arguments
     */
    
    public static ArrayList<Buyer>allBuyers;
    public static ArrayList<Seller>allSellers;
    public static ArrayList<Listings>allProducts;
    public static ArrayList<Listings>allServices;
    public static ArrayList<Orders>allOrders;
    public static ArrayList<Integer>allHistory;
    
    public static void main(String[] args) {
        // TODO code application logic here
        allBuyers=new ArrayList<Buyer>();
        allSellers=new ArrayList<Seller>();
       allProducts=new ArrayList<Listings>();
       allServices=new ArrayList<Listings>();
       allOrders=new ArrayList<Orders>();
       allHistory=new ArrayList<Integer>(); 
       
        String selection="";
        Scanner input=new Scanner(System.in);
        
        
        System.out.println("Welcome to UHCL_Groupon");
        while(!selection.equals("x")){
            System.out.println();
            System.out.println("Please make your decision");
            System.out.println("1: To create new account");
            System.out.println("2: Login into your account");
            System.out.println("x: Finish the simulation");
            
            selection=input.next();
            System.out.println();
            if(selection.equals("1")){
                
                AccountCreator.create();
                
                
            }
            else if(selection.equals("2")){
                
                LoginUser.login();
                
                
            }
            else if(selection.equals("x")){
                
                System.out.println("Thank you for shopping with us");
                break;
                
            }
            else{
                ;
            }
            
            
        }
        
    }
    
}
