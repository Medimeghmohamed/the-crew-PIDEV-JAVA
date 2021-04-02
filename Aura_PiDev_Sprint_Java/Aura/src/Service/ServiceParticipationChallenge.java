/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.participation_challenge;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.Connexion;

/**
 *
 * @author NOUR
 */
public class ServiceParticipationChallenge {

    Connection cnx;

    public ServiceParticipationChallenge() {
        cnx = Connexion.getInstance().getConnection();
    }
    
    

    public ObservableList<participation_challenge> recup_participation(int id_client) {
        participation_challenge n = new participation_challenge();
        ObservableList<participation_challenge>Oparticipation=FXCollections.observableArrayList();

        try {
            Statement st = cnx.createStatement();
            String query = "select * FROM `participation_challenge` WHERE id_client='" + id_client + "'";
            ResultSet rst = st.executeQuery(query);
            while (rst.next()) {

                n.setId(rst.getInt(1));
                n.setId_challenge(rst.getInt("id_challenge"));
                n.setId_client(rst.getString("id_client"));
                Oparticipation.add(n);
                

            }

        } catch (SQLException ex) {
            System.out.println("erreur get IdOBJ pour suivi");
            System.out.println(ex);
        }

        return Oparticipation;

    }
    
    public participation_challenge verif_participation(String id_client,int id_challenge){
        
        participation_challenge pc = new participation_challenge();
        
        try {
            Statement st=null;
            st = Connexion.getInstance().getConnection().createStatement();
            String query = "select * FROM participation_challenge WHERE id_client='" + id_client + "'AND id_challenge='"+id_challenge+"'";
            ResultSet rst = st.executeQuery(query);
           
            while (rst.next()){
                System.out.println("555555");
                pc.setId(rst.getInt("id"));
                pc.setId_challenge(rst.getInt("id_challenge"));
                pc.setId_client(rst.getString("id_client"));
                pc.setEtat(rst.getString("etat"));
                
            
            
            }
            
             
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceParticipationChallenge.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
        System.out.println(pc);
       
            
        
        
        
        
        return pc ;
    
    }
}
