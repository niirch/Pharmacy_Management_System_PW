

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package pharmacy_management_system;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author user
 */
public class Database {
    
    public static Connection connectDb(){
    
        try{
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/pharmacy","root","");
            return connect; 
        }catch(Exception e){e.printStackTrace();}
        return null ;
    }
    
}
