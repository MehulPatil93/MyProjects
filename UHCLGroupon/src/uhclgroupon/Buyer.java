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
public class Buyer {
    
private static int nextbuyerid=100001;
private int buyerid;
private String fname;
private String lname;


private String username;
private String password;
    
public Buyer(String f,String l,String u,String pw){
    buyerid=nextbuyerid;
    nextbuyerid++;
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

    public int getBuyerid() {
        return buyerid;
    }

    public void setBuyerid(int buyerid) {
        this.buyerid = buyerid;
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

   

    
    
    
    
    public static void MainPage(int b){
        
        Scanner input=new Scanner(System.in);
        String selection="";
        
        while(!selection.equals("x")){
        
        System.out.println();
        System.out.println("Welcome to Buyer page");
        System.out.println("1: To see all Products & Services to buy ");
        System.out.println("2: To Search the items");
        System.out.println("3: To check history");
        System.out.println("x: To go back");
        
        selection=input.next();
        
        if(selection.equals("1")){
            showitems(b);
        }
        else if(selection.equals("2")){
            search(b);
        }
        else if(selection.equals("3")){
            showOrders(b);
        }
        
        else if(selection.equals("x")){
            break;
        }
        
        
    }
        
    }
    
    
    public static void search(int b){
        Scanner input=new Scanner(System.in);
        String keyword="";
        System.out.println("Please enter the keyword");
        keyword=input.next();
        int c=1;
        
        for(Listings aitem:UHCLGroupon.allProducts){
            if(aitem.getTitle().contains(keyword)){
                System.out.printf("%d: %s \n",c,aitem.getTitle());
                c++;
            }
        }

        for(Listings aitem:UHCLGroupon.allServices){
            if(aitem.getTitle().contains(keyword)){
                System.out.printf("%d: %s \n",c,aitem.getTitle());
                c++;
            }
        }
        
        
       
        }
        
        
        
        
    
    
    
    public static void showitems(int b){
        int countp=1,counts=1;
        System.out.println("The products for buying are:");
        for(int i=0;i<UHCLGroupon.allProducts.size();i++){
            
                System.out.printf("%d: %s \n",countp,UHCLGroupon.allProducts.get(i).getTitle());
                countp++;
            }
        System.out.println();
        System.out.println("The servicess for buying are:");
        for(int i=0;i<UHCLGroupon.allServices.size();i++){
            
                System.out.printf("%d: %s \n",counts,UHCLGroupon.allServices.get(i).getTitle());
                counts++;
            }
        System.out.println();
        String selection="";
                Scanner input=new Scanner(System.in);
        while(!selection.equals("x")){
            System.out.println();
            System.out.println("1: To Buy product");
            System.out.println("2: To Buy service");
            System.out.println("x: To go back");
            selection=input.next();
            
            if(selection.equals("1")){
                buyProduct(b);
            }
            if(selection.equals("2")){
                buyService(b);
            }
            if(selection.equals("x")){
                ;
            }
            else{
                ;
            }
        }
        }
    public static void buyProduct(int b){
        String selection="";
        String choice="";
        int type=0;
        Scanner input=new Scanner(System.in);
        int countp=1;
        while(!selection.equals("x")){
            System.out.println();
         System.out.println("The products for buying are:");
        for(int i=0;i<UHCLGroupon.allProducts.size();i++){
            
           
               System.out.printf("%d: %s \n",countp,UHCLGroupon.allProducts.get(i).getTitle());
                countp++;
            }
            countp=1;
        
        
        System.out.println("X: go back");
        selection=input.next();
        if(isInteger(selection)){
            int intselection=Integer.parseInt(selection);
            getDetails(type,intselection);
            
            System.out.println("Do you want to buy this product: Y/N");
            choice=input.next();
            if(choice.equalsIgnoreCase("y")){
                purchase(type,b,intselection);
            }
            else if(choice.equalsIgnoreCase("n")){
                break;
            }
        }
       else if(selection.equals("x")){
            break;
        }
        }
    }
    
    public static void buyService(int b){
        String selection="";
        String choice="";
        int type=1;
        Scanner input=new Scanner(System.in);
        int counts=1;
        while(!selection.equals("x")){
            System.out.println();
         System.out.println("The servicess for buying are:");
        for(int i=0;i<UHCLGroupon.allServices.size();i++){
            
                System.out.printf("%d: %s \n",counts,UHCLGroupon.allServices.get(i).getTitle());
                counts++;
            }
        counts=1;
        
        System.out.println("X: go back");
        selection=input.next();
        if(isInteger(selection)){
            int intselection=Integer.parseInt(selection);
            getDetails(type,intselection);
            
            System.out.println("Do you want to get this Service: Y/N");
            choice=input.next();
        if(choice.equalsIgnoreCase("y")){
                purchase(type,b,intselection);
            }
            else if(choice.equalsIgnoreCase("n")){
                break;
            }
        }
       else if(selection.equals("x")){
            break;
        }
        
        }
    }
    
    
    public static void purchase(int c,int b,int x){
        if(c==0){
        for(int j=0;j<UHCLGroupon.allProducts.size();j++){
            if(j==(x-1)){
                if(UHCLGroupon.allProducts.get(j).getQuantity()>0){
                Orders aNew=new Orders(DateAndTime.DateTime(),UHCLGroupon.allProducts.get(j).getProductid(),UHCLGroupon.allProducts.get(j).getTitle(),b,UHCLGroupon.allProducts.get(j).getSellerid(),UHCLGroupon.allProducts.get(j).getTitle(),UHCLGroupon.allProducts.get(j).getMin_num(),UHCLGroupon.allProducts.get(j).getPrice());
                UHCLGroupon.allOrders.add(aNew);
                UHCLGroupon.allProducts.get(j).setQuantity(UHCLGroupon.allProducts.get(j).getQuantity()-1);
            }
                else{
                    System.out.println("This product is sold out");
                }
            }
        }
        for(int j=0;j<UHCLGroupon.allOrders.size();j++){
            if(UHCLGroupon.allOrders.get(j).getBuyerid()==b){
                UHCLGroupon.allHistory.add(UHCLGroupon.allOrders.get(j).getProductid());
            }
        }
        }
        else if(c==1){
            for(int j=0;j<UHCLGroupon.allServices.size();j++){
            if(j==(x-1)){
                if(UHCLGroupon.allServices.get(j).getQuantity()>0){
                Orders aNew=new Orders(DateAndTime.DateTime(),UHCLGroupon.allServices.get(j).getProductid(),UHCLGroupon.allServices.get(j).getTitle(),b,UHCLGroupon.allServices.get(j).getSellerid(),UHCLGroupon.allServices.get(j).getTitle(),UHCLGroupon.allServices.get(j).getMin_num(),UHCLGroupon.allServices.get(j).getPrice());
                UHCLGroupon.allOrders.add(aNew);
                UHCLGroupon.allServices.get(j).setQuantity(UHCLGroupon.allServices.get(j).getQuantity()-1);
            }
                else{
                    System.out.println("This service is sold out");
                }
            }
        }
        for(int j=0;j<UHCLGroupon.allOrders.size();j++){
            if(UHCLGroupon.allOrders.get(j).getBuyerid()==b){
                UHCLGroupon.allHistory.add(UHCLGroupon.allOrders.get(j).getProductid());
            }
        }
        }
    }
    
    public static void getDetails(int c,int i){
        if(c==0){
        for(int j=0;j<UHCLGroupon.allProducts.size();j++){
            if(j==(i-1)){
                System.out.println();
                System.out.println("The product title is: "+UHCLGroupon.allProducts.get(j).getTitle());
                System.out.println("The product id is: "+UHCLGroupon.allProducts.get(j).getProductid());
                System.out.println("The product Description is: "+UHCLGroupon.allProducts.get(j).getDescription());
                System.out.println("The product price is: "+UHCLGroupon.allProducts.get(j).getPrice());
                System.out.println("The product quantity available is:"+UHCLGroupon.allProducts.get(j).getQuantity());
            }
        }
        }
        else if(c==1){
             for(int j=0;j<UHCLGroupon.allServices.size();j++){
            if(j==(i-1)){
                System.out.println();
                System.out.println("The product title is: "+UHCLGroupon.allServices.get(j).getTitle());
                System.out.println("The product id is: "+UHCLGroupon.allServices.get(j).getProductid());
                System.out.println("The product Description is: "+UHCLGroupon.allServices.get(j).getDescription());
                System.out.println("The product price is: "+UHCLGroupon.allServices.get(j).getPrice());
                System.out.println("The product quantity available is:"+UHCLGroupon.allServices.get(j).getQuantity());
            }
        }
        }
    }
    
    
    
    public static void showOrders(int b){
        int counti=0;
        for(int j=0;j<UHCLGroupon.allOrders.size();j++){
            for(int k=0;k<UHCLGroupon.allOrders.size();k++){
                if(UHCLGroupon.allOrders.get(j).getProductid()==UHCLGroupon.allOrders.get(k).getProductid()&&j!=k){
                    counti++;
                }   
            }
            if(counti>=UHCLGroupon.allOrders.get(j).getMin_num()){
                changeStatus(UHCLGroupon.allOrders.get(j).getProductid());
            }
        }
        
        for(int j=0;j<UHCLGroupon.allOrders.size();j++){
            if(UHCLGroupon.allOrders.get(j).getBuyerid()==b){
                System.out.println();
                System.out.println("The order id is: "+UHCLGroupon.allOrders.get(j).getOrderid());
                System.out.println("The product id is: "+UHCLGroupon.allOrders.get(j).getProductid());
                System.out.println("The product  is: "+UHCLGroupon.allOrders.get(j).getItemtitle());
                System.out.println("The product price is: "+UHCLGroupon.allOrders.get(j).getItemprice());
                System.out.println("the status is: "+UHCLGroupon.allOrders.get(j).getStatus());
            }
        }
    }
    
    public static void changeStatus(int pi){
        for(int j=0;j<UHCLGroupon.allOrders.size();j++){
            if(UHCLGroupon.allOrders.get(j).getProductid()==pi){
                UHCLGroupon.allOrders.get(j).setStatus("Active");
            }
        }
    }
    
    
    private static boolean isInteger(String a){
        try{
            int i=Integer.parseInt(a);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    
    
}
