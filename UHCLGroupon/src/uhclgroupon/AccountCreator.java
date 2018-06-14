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
public class AccountCreator {
    
    public static void create(){
        
        String choice="";
        Scanner input=new Scanner(System.in);
        while(!choice.equals("x")){
        System.out.println("Please enter your choice");
        System.out.println("1: To create Buyer account");
        System.out.println("2: To create Seller Account");
        System.out.println("x: To go back");
        choice=input.next();
        
        
        
        if(choice.equals("1")){
            createBuyer();
        }
        else if(choice.equals("2")){
       createSeller();
    }
        else if(choice.equals("x")){
            System.out.println(" exit");
            break;
        }
        else{
            ;
        }
        } 
    }
    
    public static void createBuyer(){
        Scanner input=new Scanner(System.in);
        String fname;
        String lname;
        String email;
        String username;
        String password;
        long phoneno;
        System.out.println("Please enter your first name");
        fname=input.next();
        System.out.println("Please enter your last name");
        lname=input.next();
        System.out.println("Please enter your User name");
        username=input.next();
        System.out.println("Please enter your password");
        password=input.next();
        
        
        boolean idUsed=false;
        boolean pwdUsed=false;
        for(Buyer abuyer:UHCLGroupon.allBuyers){
            //searching
            if(abuyer.getUsername().equals(username)){
                idUsed=true;
            }
            if(abuyer.getPassword().equals(password)){
                pwdUsed=true;
            }
        }
        
        if(idUsed){
            System.out.println("****The username  is not available***");
        }
        else if(pwdUsed){
            System.out.println("****The password is not available***");
        }
        else{
        Buyer aNew=new Buyer(fname,lname,username,password);
            UHCLGroupon.allBuyers.add(aNew);
            System.out.println("Buyer Account Created Successfully");
            System.out.println();
        }
    }
    
    public static void createSeller(){
        Scanner input=new Scanner(System.in);
        String fname;
        String lname;
        String email;
        String username;
        String password;
        long phoneno;
        System.out.println("Please enter your first name");
        fname=input.next();
        System.out.println("Please enter your last name");
        lname=input.next();
        System.out.println("Please enter your User name");
        username=input.next();
        System.out.println("Please enter your password");
        password=input.next();
        
        
         boolean idUsed=false;
        boolean pwdUsed=false;
        for(Seller aseller:UHCLGroupon.allSellers){
            //searching
            if(aseller.getUsername().equals(username)){
                idUsed=true;
            }
            if(aseller.getPassword().equals(password)){
                pwdUsed=true;
            }
        }
        
        if(idUsed){
            System.out.println("****The username  is not available***");
        }
        else if(pwdUsed){
            System.out.println("****The password is not available***");
        }
        else{
         Seller aNew=new Seller(fname,lname,username,password);
        UHCLGroupon.allSellers.add(aNew);
        System.out.println("Seller Account Created Successfully");
        System.out.println();
    }
    }
}
