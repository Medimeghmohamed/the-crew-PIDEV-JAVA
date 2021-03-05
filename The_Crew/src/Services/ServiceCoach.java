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

/**
 *
 * @author SeifBS
 */
public class ServiceCoach implements IserviceCoach {

    Connection cnx;

    public ServiceCoach() {
        cnx = Connexion.getInstance().getConnection();
    }

    public void ajouterCoach(Coach c) {
        String id;

        Statement stm;
        try {
            stm = cnx.createStatement();
            id = "C" + c.getId();

            String query = "	INSERT INTO `coach`(`id`, `nom`, `prenom`, `email`, `password`, `tel`,`specialite`) VALUES ('" + id + "','" + c.getNom() + "','" + c.getPrenom() + "','" + c.getEmail() + "','" + c.getPassword() + "','" + c.getTel() + "','" + c.getSpecialite() + "')";

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

            String query = "UPDATE coach SET id='" + c.getId() + "',nom='" + c.getNom() + "',prenom='" + c.getPrenom() + "',email='" + c.getEmail() + "',password='" + c.getPassword() + "',tel='" + c.getTel() + "',specialite='" + c.getSpecialite() + "' WHERE id='" + c.getId() + "'";
            stm.executeUpdate(query);

        } catch (SQLException ex) {
            Logger.getLogger(ServiceCoach.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Coach> afficherCoach_Oui() {
        Statement stm = null;
        List<Coach> Coachs = new ArrayList<>();

        try {
            stm = cnx.createStatement();
            String query = "SELECT * FROM `coach` WHERE etat='OUI' ";
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
     public List<Coach> afficherCoach_All() {
        Statement stm = null;
        List<Coach> Coachs = new ArrayList<>();

        try {
            stm = cnx.createStatement();
            String query = "SELECT * FROM `coach`  ";
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
      public List<Coach> afficherCoach_Non() {
        Statement stm = null;
        List<Coach> Coachs = new ArrayList<>();

        try {
            stm = cnx.createStatement();
            String query = "SELECT * FROM `coach` WHERE etat='NON' ";
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
    public List<String> afficherCoach_combobx() {
        Statement stm = null;
        List<String> Coachs = new ArrayList<>();

        try {
            stm = cnx.createStatement();
            String query = "SELECT * FROM `coach` WHERE etat='OUI' ";
            ResultSet rst = stm.executeQuery(query);

            while (rst.next()) {
                Coachs.add(rst.getString("id"));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return Coachs;

    }
    

    public void supprimerCoach(String id) {
        Statement stm;
        try {
            stm = cnx.createStatement();

            String query = "DELETE FROM coach  WHERE id='" + id + "'";
            stm.executeUpdate(query);

        } catch (SQLException ex) {
            Logger.getLogger(ServiceCoach.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Coach load_data_modify(String id) {

        Statement stm = null;
        Coach c = new Coach();

        try {
            stm = cnx.createStatement();
            String query = "SELECT * FROM `coach` WHERE id='" + id + "' ";
            ResultSet rst = stm.executeQuery(query);

            while (rst.next()) {
                c.setId(rst.getString("id"));
                c.setNom(rst.getString("nom"));
                c.setPrenom(rst.getString("prenom"));
                c.setEmail(rst.getString("email"));
                c.setPassword(rst.getString("password"));
                c.setTel(rst.getString("tel"));
                c.setSpecialite(rst.getString("specialite"));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return c;

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
        int position = 0;
        for (int i = 0; i < mail.length(); i++) {
            if (mail.charAt(i) == "@".charAt(0)) {
                test++;
                position = i;
            }
            if (mail.charAt(i) == " ".charAt(0)) {
                return false;
            }
        }
        for (int i = 0; i < mail.length(); i++) {
            if ((test == 1) && (mail.charAt(i) == ".".charAt(0))) {
                if (((mail.length() > i + 2) && (i > position))) {
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
    public boolean verifier_id_email_bd(String id, String email) { //mot de passe oublie 

        Statement stm = null;
        ResultSet rst = null;

        try {
            stm = cnx.createStatement();
            String query = "SELECT * FROM `coach` WHERE id='" + id + "' AND email='" + email + "'  ";
            rst = stm.executeQuery(query);
            if (rst.next()) {
                return true;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return false;

    }
    
   

    public void modifier_password(String id, String password) {

        Statement stm;
        try {
            stm = cnx.createStatement();

            String query = "UPDATE coach SET password='" + password + "' WHERE id='" + id + "'";
            stm.executeUpdate(query);

        } catch (SQLException ex) {
            Logger.getLogger(ServiceCoach.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean verifier_data(String id, String password) {

        Statement stm = null;
        ResultSet rst = null;

        try {
            stm = cnx.createStatement();
            String query = "SELECT * FROM `coach` WHERE id='" + id + "' AND password='" + password + "'  ";
            rst = stm.executeQuery(query);
            if (rst.next()) {
                return true;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return false;
    }
    
      public boolean verifier_etat_coach(String id) {

        Statement stm = null;
        ResultSet rst = null;

        try {
            stm = cnx.createStatement();
            String query = "SELECT * FROM `coach` WHERE id='" + id + "' AND etat='OUI'  ";
            rst = stm.executeQuery(query);
            if (rst.next()) {
                return true;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return false;

    }

    public boolean verifier_id_bd(String id) { //Controle de Saisie Ajouter Coach compte
        Statement stm = null;
        ResultSet rst = null;
        String id_true = "C" + id;

        try {
            stm = cnx.createStatement();
            String query = "SELECT * FROM `coach` WHERE id='" + id_true + "'";
            rst = stm.executeQuery(query);
            if (rst.next()) {
                return true;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return false;
    }

    public Coach load_user_name(String id) {

        Statement stm = null;
        Coach c = new Coach();
        try {
            stm = cnx.createStatement();
            String query = "SELECT * FROM `coach` WHERE id='" + id + "'  ";
            ResultSet rst = stm.executeQuery(query);

            while (rst.next()) {
                c.setId(rst.getString("id"));
                c.setNom(rst.getString("nom"));
                c.setPrenom(rst.getString("prenom"));
                c.setEmail(rst.getString("email"));
                c.setPassword(rst.getString("password"));
                c.setTel(rst.getString("tel"));
                c.setSpecialite(rst.getString("specialite"));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return c;

    }

    public List<Coach> rechercherCoach(String id) {
        Statement stm = null;
        List<Coach> Coachs = new ArrayList<>();

        try {
            stm = cnx.createStatement();
            String query = "SELECT * FROM `coach` WHERE id LIKE '%" + id + "%'  ";
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
    
     public List<String>afficherCoach_combobx_etat() {
        Statement stm = null;
        List<String> Coachs = new ArrayList<>();

        try {
            stm = cnx.createStatement();
            String query = "SELECT * FROM `coach` WHERE etat='NON' ";
            ResultSet rst = stm.executeQuery(query);

            while (rst.next()) {
                Coachs.add(rst.getString("id"));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return Coachs;

    }
    

}
