/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.classement;
import services.IServiceClassement;
import utils.Myconnexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NOUR
 */
public class ServiceClassement {
      Connection cnx;

    public ServiceClassement() {
        cnx = Myconnexion.getInstance().getConnection();

    }
    
      public void ajouterClassement(classement c){
            c.setNb_points(5);
     
     
        try {
            if (cnx != null) {

                Statement stm;
                stm = Myconnexion
                        .getInstance()
                        .getConnection()
                        .createStatement();
                assert stm != null;

                String query = "INSERT INTO `classement`(`id`, `id_client`, `id_niveau`, `position`, `nb_points` "
                        + "VALUES ( NULL,'"
                        + c.getClient() + "','"
                        + c.getNiveau() + "','"
                        + c.getPosition() + "','"
                        + c.getNb_points() + "')";
                stm.executeUpdate(query);
                System.out.println("ajout avec succes");
            } else {
                System.out.println("cnx NULL");
            }
        } catch (SQLException ex) {
           
            Logger.getLogger(ServiceChallenge.class.getName()).log(Level.SEVERE, null, ex);
        }
          
      }
      
    
}
