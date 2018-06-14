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
public class LoginUser {
    
    public static void login(){
        Scanner input=new Scanner(System.in);
        String username;
        String password;
        String selection="";
        while(!selection.equals("x")){
            System.out.println("Please select your choice");
            System.out.println("1: Buyer Login");
            System.out.println("2: Seller Login");
            System.out.println("x: Go back ");
            selection=input.next();
            
            
            if(selection.equals("1")){
                BuyerLogin();
            }
            else if(selection.equals("2")){
            SellerLogin();
        }
            else if(selection.equals("x")){
                break;
            }
            else{
                ;
            }
            
            
        }
    }
    
    public static void BuyerLogin(){
            //input
    Scanner input=new Scanner(System.in);
    String username,password;
    boolean idFound=false;
    
    System.out.println("Please enter your login ID");
    username=input.next();
    
    System.out.println("Please enter yourpassword");
    password=input.next();
    
    for(Buyer abuyer:UHCLGroupon.allBuyers){
        if(username.equals(abuyer.getUsername())){
            idFound=true;
            
            if(password.equals(abuyer.getPassword())){
                // password is good
                System.out.println("**Login Successful**");
                Buyer.MainPage(abuyer.getBuyerid());
            }
            else{
                System.out.println("Invalid password");
            }
        }
        
    }
    if(!idFound){
        System.out.println("Invalid username");
    }
    
    }
    
    public static void SellerLogin(){
                //input
    Scanner input=new Scanner(System.in);
    String username,password;
    boolean idFound=false;
    
    System.out.println("Please enter your login ID");
    username=input.next();
    
    System.out.println("Please enter yourpassword");
    password=input.next();
    
    for(Seller aseller:UHCLGroupon.allSellers){
        if(username.equals(aseller.getUsername())){
            idFound=true;
            
            if(password.equals(aseller.getPassword())){
                // password is good
                System.out.println("**Login Successful**");
                
                Seller.MainPage(aseller.getSellerid());
            }
            else{
                System.out.println("Invalid password");
            }
        }
        
    }
    if(!idFound){
        System.out.println("Invalid username");
    }
    }
    
}
