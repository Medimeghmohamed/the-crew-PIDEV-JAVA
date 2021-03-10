/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.challenge;
import entities.ligne_challenge;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Myconnexion;

/**
 *
 * @author NOUR
 */
public class ServiceLigneChallenge {
       Connection cnx;
       public ServiceLigneChallenge() {
        cnx = Myconnexion.getInstance().getConnection();

    }
       
       
       
       
    
      public ligne_challenge recup_LigneChallenge(challenge c){
          ligne_challenge lc= new ligne_challenge();
           

        try {
            Statement st = cnx.createStatement();
            String query = "select * FROM `ligne_challenge` WHERE id_challenge='" + c.getId()+ "'";
            ResultSet rst = st.executeQuery(query);
            while (rst.next()) {

                lc.setId(rst.getInt("id"));
                lc.setEtat(rst.getString("etat"));
              //  lc.setId_challenge(rst.getInt("id_challenge"));
               

            }

        } catch (SQLException ex) {
            System.out.println("erreur get IdOBJ pour suivi");
            System.out.println(ex);
        }

        return lc;
      }
      
      
      
      
      
        public String  get_etat(challenge c){
             Statement stm = null;
             String etat =null;
               try {

            stm = Myconnexion.getInstance().getConnection().createStatement();

            String query = "SELECT etat FROM `ligne_challenge`  WHERE id_challenge='" + c.getId() + "'";
            ResultSet rst = stm.executeQuery(query);
              etat=rst.getString("etat");

           

        } catch (SQLException ex) {
            Logger.getLogger(ServiceChallenge.class.getName()).log(Level.SEVERE, null, ex);
        }
           

            return etat;
        }
        
        
         public String  modifier_etat(ligne_challenge lc){
             Statement stm = null;
             String etat =null;
             
               try {
                   if(lc.getEtat()=="joined"){lc.setEtat("not joined yet");}else { lc.setEtat("joined");};

            stm = Myconnexion.getInstance().getConnection().createStatement();

            String query = "UPDATE ligne_challenge SET etat ='" + lc.getEtat() + "' WHERE id='" + lc.getId() + "'";
            ResultSet rst = stm.executeQuery(query);
            etat=rst.getString("etat");
               /*String query = "UPDATE ligne_challenge SET etat ='" + lc.getEtat() + "' WHERE id_challenge='" + lc.getId_challenge() + "'";
            ResultSet rst = stm.executeQuery(query);
            etat=rst.getString("etat");*/

           

        } catch (SQLException ex) {
            Logger.getLogger(ServiceChallenge.class.getName()).log(Level.SEVERE, null, ex);
        }
           

            return etat;
        }
      
}
