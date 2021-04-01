/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Coach;
import Interfaces.IserviceCoach;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import utils.Connexion;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author SeifBS
 */
public class ServiceCoach implements IserviceCoach {

    Connection cnx;

    public ServiceCoach() {
        cnx = Connexion.getInstance().getConnection();
    }

    @Override
    public void ajouterCoach(Coach c) {

        Statement stm;
        try {
            stm = cnx.createStatement();

            String query = "	INSERT INTO `user`(`id`, `nom`, `prenom`, `email`, `password`, `tel`,`specialite`,`adresse`,`role`,`picture`) VALUES ('" + c.getId() + "','" + c.getNom() + "','" + c.getPrenom() + "','" + c.getEmail() + "','" + c.getPassword() + "','" + c.getTel() + "','" + c.getSpecialite() + "','" + "" + " ','CoachNV','"+c.getPicture()+"')";

            stm.executeUpdate(query);

        } catch (SQLException ex) {
            Logger.getLogger(ServiceCoach.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void modifierCoach(Coach c) {

        Statement stm;
        try {
            stm = cnx.createStatement();

            String query = "UPDATE user SET id='" + c.getId() + "',nom='" + c.getNom() + "',prenom='" + c.getPrenom() + "',email='" + c.getEmail() + "',tel='" + c.getTel() + "',specialite='" + c.getSpecialite() + "' WHERE id='" + c.getId() + "'";
            stm.executeUpdate(query);

        } catch (SQLException ex) {
            Logger.getLogger(ServiceCoach.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public List<Coach> afficherCoach_Oui() { //afficher les coachs verifiés
        Statement stm = null;
        List<Coach> Coachs = new ArrayList<>();

        try {
            stm = cnx.createStatement();
            String query = "SELECT * FROM `user` WHERE role='CoachV' ";
            ResultSet rst = stm.executeQuery(query);

            while (rst.next()) {
                Coach c = new Coach();
                c.setId(rst.getString("id"));
                c.setNom(rst.getString("nom"));
                c.setPrenom(rst.getString("prenom"));
                c.setEmail(rst.getString("email"));
                c.setPassword(rst.getString("password"));
                c.setTel(rst.getString("tel"));
                c.setSpecialite(rst.getString("specialite"));

                Coachs.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return Coachs;

    }

    @Override
    public List<Coach> afficherCoach_All() { //afficher tous coachs verifiés
        Statement stm = null;
        List<Coach> Coachs = new ArrayList<>();

        try {
            stm = cnx.createStatement();
            String query = "SELECT * FROM `user` WHERE role LIKE '%Coach%'  ";
            ResultSet rst = stm.executeQuery(query);

            while (rst.next()) {
                Coach c = new Coach();
                c.setId(rst.getString("id"));
                c.setNom(rst.getString("nom"));
                c.setPrenom(rst.getString("prenom"));
                c.setEmail(rst.getString("email"));
                c.setPassword(rst.getString("password"));
                c.setTel(rst.getString("tel"));
                c.setSpecialite(rst.getString("specialite"));

                Coachs.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return Coachs;

    }

    @Override
    public List<Coach> afficherCoach_Non() {//afficher les coachs non verifiés
        Statement stm = null;
        List<Coach> Coachs = new ArrayList<>();

        try {
            stm = cnx.createStatement();
            String query = "SELECT * FROM `user` WHERE role='CoachNV' ";
            ResultSet rst = stm.executeQuery(query);

            while (rst.next()) {
                Coach c = new Coach();
                c.setId(rst.getString("id"));
                c.setNom(rst.getString("nom"));
                c.setPrenom(rst.getString("prenom"));
                c.setEmail(rst.getString("email"));
                c.setPassword(rst.getString("password"));
                c.setTel(rst.getString("tel"));
                c.setSpecialite(rst.getString("specialite"));

                Coachs.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return Coachs;

    }

    @Override
    public void supprimerCoach(String id) {
        Statement stm;
        try {
            stm = cnx.createStatement();

            String query = "DELETE FROM user WHERE id='" + id + "'";
            stm.executeUpdate(query);

        } catch (SQLException ex) {
            Logger.getLogger(ServiceCoach.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public Coach load_data_modify(String id) { // charger données coach pour la modification

        Statement stm = null;
        Coach c = new Coach();

        try {
            stm = cnx.createStatement();
            String query = "SELECT * FROM `user`  WHERE id='" + id + "' OR tel='" + id + "'OR email='" + id + "' ";
            ResultSet rst = stm.executeQuery(query);

            while (rst.next()) {
                c.setId(rst.getString("id"));
                c.setNom(rst.getString("nom"));
                c.setPrenom(rst.getString("prenom"));
                c.setEmail(rst.getString("email"));
                c.setPassword(rst.getString("password"));
                c.setTel(rst.getString("tel"));
                c.setSpecialite(rst.getString("specialite"));
                c.setRole(rst.getString("role"));
                c.setRme(rst.getString("rme"));
                c.setPicture(rst.getString("picture"));
                c.setSms(rst.getString("sms"));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return c;

    }

    @Override
    public Coach load_user_name(String id) { //get nom de l'identifiant apres login

        Statement stm = null;
        Coach c = new Coach();
        try {
            stm = cnx.createStatement();
            String query = "SELECT * FROM `user` WHERE id='" + id + "' OR tel='" + id + "'OR email='" + id + "'  ";
            ResultSet rst = stm.executeQuery(query);

            while (rst.next()) {
                c.setId(rst.getString("id"));
                c.setNom(rst.getString("nom"));
                c.setPrenom(rst.getString("prenom"));
                c.setEmail(rst.getString("email"));
                c.setPassword(rst.getString("password"));
                c.setTel(rst.getString("tel"));
                c.setSpecialite(rst.getString("specialite"));
                c.setRole(rst.getString("role"));
                c.setRme(rst.getString("rme"));
                c.setPicture(rst.getString("picture"));
                c.setSms(rst.getString("sms"));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return c;

    }

    public List<Coach> rechercherCoach(String id) { //Rechercher un coach
        Statement stm = null;
        List<Coach> Coachs = new ArrayList<>();

        try {
            stm = cnx.createStatement();
            String query = "SELECT * FROM `user` WHERE id LIKE '%" + id + "%' AND role LIKE '%Coach%'  ";
            ResultSet rst = stm.executeQuery(query);

            while (rst.next()) {
                Coach c = new Coach();
                c.setId(rst.getString("id"));
                c.setNom(rst.getString("nom"));
                c.setPrenom(rst.getString("prenom"));
                c.setEmail(rst.getString("email"));
                c.setPassword(rst.getString("password"));
                c.setTel(rst.getString("tel"));
                c.setSpecialite(rst.getString("specialite"));

                Coachs.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return Coachs;

    }
    public ObservableList <Coach> afficherCoach_Ouiob() { //afficher les coachs verifiés
        Statement stm = null;
        ObservableList<Coach> Coachs =FXCollections.observableArrayList();

        try {
            stm = cnx.createStatement();
            String query = "SELECT * FROM `user` WHERE role='CoachV' ";
            ResultSet rst = stm.executeQuery(query);

            while (rst.next()) {
                Coach c = new Coach();
                c.setId(rst.getString("id"));
                c.setNom(rst.getString("nom"));
                c.setPrenom(rst.getString("prenom"));
                c.setEmail(rst.getString("email"));
                c.setPassword(rst.getString("password"));
                c.setTel(rst.getString("tel"));
                c.setSpecialite(rst.getString("specialite"));

                Coachs.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return Coachs;

    }

}
