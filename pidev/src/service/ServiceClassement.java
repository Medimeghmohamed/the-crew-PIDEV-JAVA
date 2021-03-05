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

    public void ajouterClassement(classement c) {
        c.setNb_points(5);

        try {
            if (cnx != null) {

                Statement stm;
                stm = Myconnexion
                        .getInstance()
                        .getConnection()
                        .createStatement();
                assert stm != null;

                String query = "INSERT INTO `ligne_classement`(`id`, `id_client`, `id_niveau`, `position`, `nb_points`) "
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

    public ObservableList<classement> afficherClassement() {
        Statement stm = null;
        ObservableList<classement> classements = FXCollections.observableArrayList();

        try {

            stm = Myconnexion.getInstance().getConnection().createStatement();

            String query = "SELECT * FROM `ligne_classement` ";
            ResultSet rst = stm.executeQuery(query);

            while (rst.next()) {
                classement c = new classement();
                c.setId(rst.getInt(1));
                c.setClient(rst.getString("id_client"));
                c.setNiveau(rst.getInt("id_niveau"));
                c.setPosition(rst.getInt("position"));
                c.setNb_points(rst.getInt("nb_points"));

                classements.add(c);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceChallenge.class.getName()).log(Level.SEVERE, null, ex);
        }

        return classements;
    }

    public classement recup_classement(int id) {
        classement ch = new classement();

        try {
            Statement st = cnx.createStatement();
            String query = "select * FROM `ligne_classement` WHERE id='" + id + "'";
            ResultSet rst = st.executeQuery(query);
            while (rst.next()) {

                ch.setId(rst.getInt(1));
                ch.setClient(rst.getString(2));
                ch.setNiveau(rst.getInt(3));
                ch.setPosition(rst.getInt(4));
                ch.setNb_points(rst.getInt(5));

            }

        } catch (SQLException ex) {
            System.out.println("erreur get IdOBJ pour suivi");
            System.out.println(ex);
        }

        return ch;

    }

    public void modifierClassement(classement c) {
        try {
            Statement st = cnx.createStatement();

            String query = "UPDATE  ligne_classement SET id_niveau= '" + c.getNiveau() + "', id_client = '" + c.getClient() + "', position = '" + c.getPosition() + "', nb_points = '" + c.getNb_points() + "'  WHERE id = " + c.getId() + "";
            st.executeUpdate(query);
            System.out.println("modification avec succes");
        } catch (SQLException ex) {
            System.out.println("erreur modifier classement");
            System.out.println(ex);
        }
    }

    public void supprimerChallenge(String id) {

        int Id = Integer.parseInt(id);
        try {
            Statement st = cnx.createStatement();
            String query = "DELETE FROM `ligne_classement` WHERE id = " + Id + "";
            st.executeUpdate(query);
            System.out.println("suppression avec succes");
        } catch (SQLException ex) {
            System.out.println("erreur supprimer classement");
            System.out.println(ex);
        }
    }

    public ObservableList<classement> trierClassement() {
        Statement stm = null;
        ObservableList<classement> classements = FXCollections.observableArrayList();

        try {

            stm = Myconnexion.getInstance().getConnection().createStatement();

            String query = "SELECT * FROM `ligne_classement` ORDER BY position  ";
            ResultSet rst = stm.executeQuery(query);

            while (rst.next()) {
                classement c = new classement();
                c.setId(rst.getInt(1));
                c.setClient(rst.getString("id_client"));
                c.setNiveau(rst.getInt("id_niveau"));
                c.setPosition(rst.getInt("position"));
                c.setNb_points(rst.getInt("nb_points"));

                classements.add(c);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceChallenge.class.getName()).log(Level.SEVERE, null, ex);
        }

        return classements;

    }

    public ObservableList<classement> RechercherClassement(String pos) {

        ObservableList<classement> Oclassement = FXCollections.observableArrayList();
        try {
            Statement stm = cnx.createStatement();
            String query = " select * from ligne_classement  WHERE  position='" + pos + "'";
            //" select * from niveau  WHERE titre = LIKE '%tr%'";

            ResultSet rst = stm.executeQuery(query);

            while (rst.next()) {
                classement nv = new classement();

                nv.setId(rst.getInt(1));
                nv.setClient(rst.getString(2));
                nv.setNiveau(rst.getInt(3));
                nv.setPosition(rst.getInt(4));
                nv.setNb_points(rst.getInt(5));

                Oclassement.add(nv);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceNiveau.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Oclassement;
    }


         
        
    
}
