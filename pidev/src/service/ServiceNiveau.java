/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.niveau;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.Myconnexion;

/**
 *
 * @author NOUR
 */
public class ServiceNiveau {

    Connection cnx;

    public ServiceNiveau() {
        cnx = Myconnexion.getInstance().getConnection();
    }

    public void ajouterNiveau(niveau n) {

        try {
            if (cnx != null) {

                Statement stm;
                stm = Myconnexion
                        .getInstance()
                        .getConnection()
                        .createStatement();
                assert stm != null;

                String query = "INSERT INTO `niveau`(`id`,`titre`) "
                        + "VALUES ( NULL,'"
                        + n.getTitre() + "')";
                stm.executeUpdate(query);
                System.out.println("ajout avec succes");
            } else {
                System.out.println("cnx NULL");
            }
        } catch (SQLException ex) {

            Logger.getLogger(ServiceChallenge.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ObservableList<niveau> afficherNiveau() {
        Statement stm = null;
        ObservableList<niveau> Oniveaux = FXCollections.observableArrayList();

        try {

            stm = Myconnexion.getInstance().getConnection().createStatement();

            String query = "SELECT * FROM `niveau` ";
            ResultSet rst = stm.executeQuery(query);

            while (rst.next()) {
                niveau c = new niveau();
                c.setId(rst.getInt(1));
                c.setTitre(rst.getString("titre"));

                Oniveaux.add(c);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceChallenge.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Oniveaux;
    }

    public niveau recup_niveau(int id) {
        niveau n = new niveau();

        try {
            Statement st = cnx.createStatement();
            String query = "select * FROM `niveau` WHERE id='" + id + "'";
            ResultSet rst = st.executeQuery(query);
            while (rst.next()) {

                n.setId(rst.getInt(1));
                n.setTitre(rst.getString("titre"));

            }

        } catch (SQLException ex) {
            System.out.println("erreur get IdOBJ pour suivi");
            System.out.println(ex);
        }

        return n;

    }
    
     public void modifierNiveau(niveau n) {
        try {
            Statement st = cnx.createStatement();
           
            String query = "UPDATE  niveau SET titre= '" + n.getTitre()+ "'  WHERE id = " + n.getId()+ "";
            st.executeUpdate(query);
            System.out.println("modification avec succes");
        } catch (SQLException ex) {
            System.out.println("erreur modifier classement");
            System.out.println(ex);
        }
    }
       public void supprimerNiveau(String id) {
       
       int Id=Integer.parseInt(id);
        try {
            Statement st = cnx.createStatement();
            String query = "DELETE FROM `niveau` WHERE id = " + Id + "";
            st.executeUpdate(query);
            System.out.println("suppression avec succes");
        } catch (SQLException ex) {
            System.out.println("erreur supprimer niveau");
            System.out.println(ex);
        }
    }
    public ObservableList<niveau> RechercherNiveau(String tr) {
         ObservableList<niveau> Oniveaux = FXCollections.observableArrayList();
        try {
            Statement stm = cnx.createStatement();
            String query = " select * from niveau  WHERE  titre='" + tr + "'";
            //" select * from niveau  WHERE titre = LIKE '%tr%'";

            ResultSet rst = stm.executeQuery(query);

            while (rst.next()) {
                niveau nv = new niveau();

                nv.setId(rst.getInt(1));
                nv.setTitre(rst.getString(2));
               
                Oniveaux.add(nv);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceNiveau.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Oniveaux;
    }
}
