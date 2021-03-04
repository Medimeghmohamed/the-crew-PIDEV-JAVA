/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Admin;
import Services.IserviceAdmin;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import utils.Connexion;
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
        cnx = Connexion.getInstance().getConnection();
    }

    public void ajouterAdmin(Admin a) {

        Statement stm;
        try {
            stm = cnx.createStatement();

            String query = "	INSERT INTO `admin`(`id`, `nom`, `prenom`, `email`, `password`, `tel`) VALUES ('" + a.getId() + "','" + a.getNom() + "','" + a.getPrenom() + "','" + a.getEmail() + "','" + a.getPassword() + "','" + a.getTel() + "')";

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

            String query = "UPDATE admin SET id='" + a.getId() + "',nom='" + a.getNom() + "',prenom='" + a.getPrenom() + "',email='" + a.getEmail() + "',password='" + a.getPassword() + "',tel='" + a.getTel() + "' WHERE id='" + a.getId() + "'";
            stm.executeUpdate(query);

        } catch (SQLException ex) {
            Logger.getLogger(ServiceAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Admin> afficherAdmin() {
        Statement stm = null;
        List<Admin> Admins = new ArrayList<>();

        try {
            stm = cnx.createStatement();
            String query = "SELECT * FROM `admin` ";
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
            String query = "SELECT `id` FROM `admin` ";
            ResultSet rst = stm.executeQuery(query);

            while (rst.next()) {
                Admins.add(rst.getString("id"));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return Admins;

    }

    public void supprimerAdmin(String id) {
        Statement stm;
        try {
            stm = cnx.createStatement();

            String query = "DELETE FROM admin  WHERE id='" + id + "'";
            stm.executeUpdate(query);

        } catch (SQLException ex) {
            Logger.getLogger(ServiceAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Admin load_data_modify(String id) {

        Statement stm = null;
        Admin a = new Admin();

        try {
            stm = cnx.createStatement();
            String query = "SELECT * FROM `admin` WHERE id='" + id + "' ";
            ResultSet rst = stm.executeQuery(query);

            while (rst.next()) {
                a.setId(rst.getString("id"));
                a.setNom(rst.getString("nom"));
                a.setPrenom(rst.getString("prenom"));
                a.setEmail(rst.getString("email"));
                a.setPassword(rst.getString("password"));

                a.setTel(rst.getString("tel"));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return a;

    }

    public boolean test_Cin(String cin) {

        int i, length;
        length = cin.length();

        if (length != 8) {
            return false;
        }

        for (i = 0; i < length; i++) {

            if (!(cin.charAt(i) >= '0' && cin.charAt(i) <= '9')) {
                return false;
            }

        }
        return true;

    }

    boolean test_num_telephonique(String tel) {
        int i;
        String[] tab = {"0", "1", "4", "6", "8"};
        for (i = 0; i < tab.length; i++) {
            if (tel.charAt(0) == tab[i].charAt(0)) {
                return false;
            }
        }

        return true;
    }

    public boolean test_Tel(String tel) {

        int i, length;
        length = tel.length();

        if (length != 8) {
            return false;
        }

        for (i = 0; i < length; i++) {

            if ((!(tel.charAt(i) >= '0' && tel.charAt(i) <= '9')) || (test_num_telephonique(tel) == false)) {
                return false;
            }

        }
        return true;

    }

    public boolean test_Email(String mail) {
        int test = 0;
        for (int i = 0; i < mail.length(); i++) {
            if (mail.charAt(i) == "@".charAt(0)) {
                test++;
            }
            if (mail.charAt(i) == " ".charAt(0)) {
                return false;
            }
        }
        for (int i = 0; i < mail.length(); i++) {
            if ((test == 1) && (mail.charAt(i) == ".".charAt(0))) {
                if (((mail.length() > i + 1) && (i - test > 2))) {
                    return true;
                }

            }

        }
        return false;
    }

    public boolean test_Password(String password) {

        int nombre_Maj = 0;
        int nombre_Entier = 0;
        int nombre_Min = 0;

        int ascii;

        for (int i = 0; i < password.length(); i++) {
            ascii = password.charAt(i);

            if ((ascii >= 65) && (ascii <= 90)) {
                nombre_Maj++;
            }
            if (password.charAt(i) >= '0' && password.charAt(i) <= '9') {
                nombre_Entier++;
            }
            if ((ascii >= 97) && (ascii <= 122)) {
                nombre_Min++;
            }

        }
        if ((nombre_Entier >= 1) && (nombre_Maj >= 1) && (nombre_Min >= 1) && (password.length() >= 8)) {
            return true;
        }
        return false;

    }

    @Override
    public boolean verifier_id_email_bd(String id, String email) {

        Statement stm = null;
        ResultSet rst = null;

        try {
            stm = cnx.createStatement();
            String query = "SELECT * FROM `admin` WHERE id='" + id + "' AND email='" + email + "'  ";
            rst = stm.executeQuery(query);
            if (rst.next()) {
                return true;
            } 

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }


        return false;

    }
    
       public void modifier_password(String id,String password){
       
       Statement stm;
        try {
            stm = cnx.createStatement();

            String query = "UPDATE admin SET password='" + password + "' WHERE id='" + id + "'";
            stm.executeUpdate(query);

        } catch (SQLException ex) {
            Logger.getLogger(ServiceAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       }


}
