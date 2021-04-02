/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author SeifBS
 */
public class Connexion {
    final static String URL="jdbc:mysql://localhost:3306/aura";
    final static String LOGIN="root";
    final static String PWD="";
    static Connexion instance=null;
    private Connection cnx;
    private Connexion(){
    try {
    cnx=DriverManager.getConnection(URL,LOGIN,PWD);
    System.out.println("Ma Connexion est etablie "); 
   }
    catch (SQLException ex){
   
   System.out.println("Pas De Connexion");
   }
    
    
    }
    
public static Connexion getInstance(){
if(instance==null){

instance=new Connexion();
}
return instance ;
}
public Connection getConnection(){
return cnx;
}

}
    

