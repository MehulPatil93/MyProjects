/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uhclgroupon;
import java.util.*;
import java.text.*;
/**
 *
 * @author mehpa
 */
public class DateAndTime {
    
    public static final String DateFormat="EEE,d MM YYYY HH:mm:ss";
    
    public static String DateTime(){
    Calendar cal=Calendar.getInstance();
    SimpleDateFormat sdf=new SimpleDateFormat(DateFormat);
    return sdf.format(cal.getTime());
    
    }
}
