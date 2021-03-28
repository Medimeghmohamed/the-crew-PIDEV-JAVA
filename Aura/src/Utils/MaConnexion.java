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
 * @author Chirine
 */
public class MaConnexion {
    //DB
    final static String URL="jdbc:mysql://127.0.0.1:3306/aura";
    final static String USERNAME="root";
    final static String PWD = "";  
    //connection init
    static MaConnexion instance = null;
    private Connection cnx;
    
    //constructeur
    private MaConnexion(){
        try {
            cnx = DriverManager.getConnection(URL, USERNAME, PWD);
        } catch (SQLException ex) {
            System.out.println("connexion NON etablie");
        }
    };
    
    public static MaConnexion getInstance() {
        if(instance == null)
        {
            instance = new MaConnexion();
        }
        return instance;
    }
    
    public Connection getCnx() {
        return cnx;
    }
    
}