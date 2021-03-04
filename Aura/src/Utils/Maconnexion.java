/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author Nour Dekhil
 */
public class Maconnexion {

    final static String URL = "jdbc:mysql://127.0.0.1:3306/aura";
    final static String LOGIN = "root";
    final static String PWD = "";
    static Maconnexion instance = null;
    private Connection cnx;

    private Maconnexion() {
        try {
            cnx = DriverManager.getConnection(URL, LOGIN, PWD);
            System.out.println("connexion etablie");
        } catch (SQLException ex) {
            System.out.println("ERREUR");
        }
    }

    public static Maconnexion getInstance() {
        if(instance==null)
        {
            instance= new Maconnexion();
        }
        
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }
    
    
    
    
    
    

}
