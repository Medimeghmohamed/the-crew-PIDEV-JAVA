/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Admin;
import interfaces.IserviceAdmin;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import MaConnexion.MyConnection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SeifBS
 */
public class ServiceAdmin implements IserviceAdmin {

    Connection cnx;

    public ServiceAdmin() {
        cnx = MyConnection.getInstance().getCnx();
    }

    @Override
    public void ajouterAdmin(Admin a) {

        Statement stm;
        try {
            stm = cnx.createStatement();

            String query = "INSERT INTO `user`(`id`, `nom`, `prenom`, `email`, `password`, `tel`,`specialite`,`adresse`,`role`) VALUES ('" + a.getId() + "','" + a.getNom() + "','" + a.getPrenom() + "','" + a.getEmail() + "','" + a.getPassword() + "','" + a.getTel() + "','" + "" + "','" + "" + " ','Admin')";

            stm.executeUpdate(query);

        } catch (SQLException ex) {
            Logger.getLogger(ServiceAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void modifierAdmin(Admin a) {

        Statement stm;
        try {
            stm = cnx.createStatement();

            String query = "UPDATE user SET id='" + a.getId() + "',nom='" + a.getNom() + "',prenom='" + a.getPrenom() + "',email='" + a.getEmail() + "',tel='" + a.getTel() + "' WHERE id='" + a.getId() + "'";
            stm.executeUpdate(query);

        } catch (SQLException ex) {
            Logger.getLogger(ServiceAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public List<Admin> afficherAdmin() {
        Statement stm = null;
        List<Admin> Admins = new ArrayList<>();

        try {
            stm = cnx.createStatement();
            String query = "SELECT * FROM `user` WHERE role LIKE '%Admin%'  ";
            ResultSet rst = stm.executeQuery(query);

            while (rst.next()) {
                Admin a = new Admin();
                a.setId(rst.getString("id"));
                a.setNom(rst.getString("nom"));
                a.setPrenom(rst.getString("prenom"));
                a.setEmail(rst.getString("email"));
                a.setPassword(rst.getString("password"));
                a.setTel(rst.getString("tel"));

                Admins.add(a);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return Admins;

    }

    @Override
    public List<String> afficherAdmin_combobx() {
        Statement stm = null;
        List<String> Admins = new ArrayList<>();

        try {
            stm = cnx.createStatement();
            String query = "SELECT * FROM `user` WHERE role LIKE '%Admin%' ";
            ResultSet rst = stm.executeQuery(query);

            while (rst.next()) {
                Admins.add(rst.getString("id"));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return Admins;

    }

    @Override
    public void supprimerAdmin(String id) {
        Statement stm;
        try {
            stm = cnx.createStatement();

            String query = "DELETE FROM user  WHERE id='" + id + "'";
            stm.executeUpdate(query);

        } catch (SQLException ex) {
            Logger.getLogger(ServiceAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public Admin load_data_modify(String id) { //Charger Les Information d'un admin pour la modifictaion

        Statement stm = null;
        Admin a = new Admin();

        try {
            stm = cnx.createStatement();
            String query = "SELECT * FROM `user` WHERE id='" + id + "' OR tel='" + id + "'OR email='" + id + "' ";
            ResultSet rst = stm.executeQuery(query);

            while (rst.next()) {
                a.setId(rst.getString("id"));
                a.setNom(rst.getString("nom"));
                a.setPrenom(rst.getString("prenom"));
                a.setEmail(rst.getString("email"));
                a.setPassword(rst.getString("password"));

                a.setTel(rst.getString("tel"));
                a.setRole(rst.getString("role"));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return a;

    }

    @Override
    public Admin load_user_name(String id) { //recuperer nom d'un identifiant et afficher dans le home 

        Statement stm = null;
        List<Admin> Admins = new ArrayList<>();
        Admin a = new Admin();

        try {
            stm = cnx.createStatement();
            String query = "SELECT * FROM `user` WHERE id='" + id + "' OR tel='" + id + "'OR email='" + id + "'   ";
            ResultSet rst = stm.executeQuery(query);

            while (rst.next()) {
                a.setId(rst.getString("id"));
                a.setNom(rst.getString("nom"));
                a.setPrenom(rst.getString("prenom"));
                a.setEmail(rst.getString("email"));
                a.setPassword(rst.getString("password"));
                a.setTel(rst.getString("tel"));
                a.setRole(rst.getString("role"));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return a;

    }

    @Override
    public List<Admin> rechercherAdmin(String id, String critere) { //Recherche avancée aadmin
        Statement stm = null;
        List<Admin> Admins = new ArrayList<>();
        if (critere == null) {
            critere = "id";
        }

        try {
            stm = cnx.createStatement();
            String query = "SELECT * FROM `user` WHERE " + critere + " LIKE '%" + id + "%' AND role LIKE '%Admin%'  ";
            ResultSet rst = stm.executeQuery(query);

            while (rst.next()) {
                Admin a = new Admin();
                a.setId(rst.getString("id"));
                a.setNom(rst.getString("nom"));
                a.setPrenom(rst.getString("prenom"));
                a.setEmail(rst.getString("email"));
                a.setPassword(rst.getString("password"));
                a.setTel(rst.getString("tel"));

                Admins.add(a);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return Admins;

    }

    @Override
    public void accepter_coach(String id) { //Accepter coach non verifié

        Statement stm;
        try {
            stm = cnx.createStatement();

            String query = "UPDATE user SET role='CoachV' WHERE id='" + id + "'";
            stm.executeUpdate(query);

        } catch (SQLException ex) {
            Logger.getLogger(ServiceAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void rejeter_coach(String id) { //Accepter coach non verifié

        Statement stm;
        try {
            stm = cnx.createStatement();

            String query = "UPDATE user SET role='CoachNV' WHERE id='" + id + "'";
            stm.executeUpdate(query);

        } catch (SQLException ex) {
            Logger.getLogger(ServiceAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public boolean verif_super_admin(String id) {

        Statement stm;
        String role = "";

        try {
            stm = cnx.createStatement();

            String query = "SELECT * FROM `user` WHERE id='" + id + "'";
            ResultSet rst = stm.executeQuery(query);

            while (rst.next()) {
                role = rst.getString("role");
                if ("SAdmin".equals(role)) {

                    return true;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
