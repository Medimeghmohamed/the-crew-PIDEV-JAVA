/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author NOUR
 */
public class Myconnexion {

 
     final static String URL="jdbc:mysql://localhost:3306/workshop";
    final static String LOGIN="root";
    final static String PWD="";
    static Myconnexion instance=null;
    private Connection cnx;
    private Myconnexion(){
    try {
    cnx=DriverManager.getConnection(URL,LOGIN,PWD);
    System.out.println("Ma Connexion est etablie "); 
   }
    catch (SQLException ex){
   
   System.out.println("Pas De Connexion");
   }
    
    
    }
    
public static Myconnexion getInstance(){
if(instance==null){

instance=new Myconnexion();
}
return instance ;
}
public Connection getConnection(){
return cnx;
}

}

