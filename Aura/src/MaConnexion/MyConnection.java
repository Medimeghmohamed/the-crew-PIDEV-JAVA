/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MaConnexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author medimegh
 */
public class MyConnection {
    final static String URL="jdbc:Mysql://127.0.0.1:3306/aura";
  final static  String login="root";
  final static  String pwd="";
    Connection cnx;
    public static MyConnection instance ;

    public MyConnection() {
        try {
            cnx= DriverManager.getConnection(URL, login, pwd) ;
            System.out.println("Connexion etablie!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Connection getCnx() {
        return cnx;
    }
    
    public static MyConnection getInstance(){
     if(instance==null){
         instance= new MyConnection() ;
     }
     return instance ;
    }
}
