/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Admin;
import entities.Client;
import entities.Coach;
import Interfaces.IServiceUser;
import java.security.MessageDigest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import Utils.MaConnexion;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author SeifBS
 */
public class ServiceUser implements IServiceUser {

    public ServiceUser() {
        cnx = MaConnexion.getInstance().getCnx();

    }

    Connection cnx;

    @Override
    public String verifier_data(String id, String password) { //Login user

        Statement stm = null;
        ResultSet rst = null;

        try {
            stm = cnx.createStatement();
            String query = "SELECT * FROM `user` WHERE (id='" + id + "' OR tel='" + id + "'OR email='" + id + "') AND password='" + password + "'  ";
            rst = stm.executeQuery(query);
            if (rst.next()) {
                if ("CoachV".equals(rst.getString("role"))) {
                    return "CoachV";
                }
                if ("CoachNV".equals(rst.getString("role"))) {
                    return "CoachNV";
                }
                if ("Admin".equals(rst.getString("role"))) {
                    return "Admin";
                }
                if ("SAdmin".equals(rst.getString("role"))) {
                    return "SAdmin";
                }
                if ("Client".equals(rst.getString("role"))) {
                    return "Client";
                }
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return "No";
    }

    @Override
    public boolean check_password(String id, String password) { //Check password for modifier password

        Statement stm = null;
        ResultSet rst = null;

        try {
            stm = cnx.createStatement();
            String query = "SELECT * FROM `user` WHERE id='" + id + "' AND password='" + password + "'  ";
            rst = stm.executeQuery(query);
            if (rst.next()) {
                return true;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return false;
    }

    @Override
    public boolean verifier_id_email_bd(String id, String email) { //Mot De Passe oublié verification id et email

        Statement stm = null;
        ResultSet rst = null;

        try {
            stm = cnx.createStatement();
            String query = "SELECT * FROM `user` WHERE id='" + id + "' AND email='" + email + "' AND role!='CoachNV'  ";
            rst = stm.executeQuery(query);
            if (rst.next()) {
                return true;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return false;

    }

    @Override
    public void modifier_password(String id, String password) {

        Statement stm;
        try {
            stm = cnx.createStatement();

            String query = "UPDATE user SET password='" + password + "' WHERE id='" + id + "'";
            stm.executeUpdate(query);

        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public boolean verifier_id_bd(String id) { //Controle De Saisie si id existe
        Statement stm = null;
        ResultSet rst = null;

        try {
            stm = cnx.createStatement();
            String query = "SELECT * FROM `user` WHERE id='" + id + "'";
            rst = stm.executeQuery(query);
            if (rst.next()) {
                return true;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return false;
    }

    @Override
    public boolean verifier_email_bd(String email) { //Controle De Saisie si id existe
        Statement stm = null;
        ResultSet rst = null;

        try {
            stm = cnx.createStatement();
            String query = "SELECT * FROM `user` WHERE email='" + email + "'";
            rst = stm.executeQuery(query);
            if (rst.next()) {
                return true;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return false;
    }

    @Override
    public boolean verifier_tel_bd(String tel) { //Controle De Saisie si id existe
        Statement stm = null;
        ResultSet rst = null;

        try {
            stm = cnx.createStatement();
            String query = "SELECT * FROM `user` WHERE tel='" + tel + "'";
            rst = stm.executeQuery(query);
            if (rst.next()) {

                return true;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return false;
    }

    @Override
    public boolean verifier_email_bd_modify(String email, String id) { //Controle De Saisie si id existe lors de la modifciation
        Statement stm = null;
        ResultSet rst = null;

        try {
            stm = cnx.createStatement();
            String query = "SELECT * FROM `user` WHERE email='" + email + "' AND id!='" + id + "'";
            rst = stm.executeQuery(query);
            if (rst.next()) {
                return true;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return false;
    }

    @Override
    public boolean verifier_tel_bd_modify(String tel, String id) { //Controle De Saisie si id existe lors de la modifciation
        Statement stm = null;
        ResultSet rst = null;

        try {
            stm = cnx.createStatement();
            String query = "SELECT * FROM `user` WHERE tel='" + tel + "' AND id!='" + id + "'";
            rst = stm.executeQuery(query);
            if (rst.next()) {
                return true;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return false;
    }

    @Override
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

    public boolean test_num_telephonique(String tel) {
        int i;
        String[] tab = {"0", "1", "4", "6", "8"};
        for (i = 0; i < tab.length; i++) {
            if (tel.charAt(0) == tab[i].charAt(0)) {
                return false;
            }
        }

        return true;
    }

    @Override
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

    @Override
    public boolean test_Email(String mail) {
        int test = 0;
        int position = 0;
        int test2 = 0;
        String[] tab = {"/", ";", ",", ":", "'", "&", "=", ">", "-", "_", "+", " ","!"};

        for (int i = 0; i < mail.length(); i++) {
            if (mail.charAt(i) == "@".charAt(0)) {
                test++;
                position = i;
            }

        }
        for (int k = 0; k < mail.length(); k++) {

            for (String tab1 : tab) {
                if (mail.charAt(k) == tab1.charAt(0)) {
                    return false;
                }
            }
        }
        for (int i = 0; i < mail.length(); i++) {
            if ((test == 1) && (mail.charAt(i) == ".".charAt(0))) {

                if (((mail.length() > i + 2) && (i > position + 4))) {
                    for (int j = position; j < mail.length(); j++) {
                        if (mail.charAt(j) == ".".charAt(0)) {
                            test2++;

                        }
                    }
                    if (test2 > 1) {
                        return false;
                    }

                    return true;
                }

            }

        }
        return false;
    }

    @Override
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
    public void modifierPassword(String id, String password) {
        Statement stm;
        try {
            stm = cnx.createStatement();

            String query = "UPDATE user SET password='" + password + "' WHERE id='" + id + "'";
            stm.executeUpdate(query);

        } catch (SQLException ex) {
            Logger.getLogger(ServiceAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public int nb_admins() {
        Statement stm = null;
        List<Admin> Admins = new ArrayList<>();

        try {
            stm = cnx.createStatement();
            String AdminQ = "SELECT id FROM `user` WHERE role LIKE '%Admin%'";
            ResultSet rst = stm.executeQuery(AdminQ);

            while (rst.next()) {
                Admin a = new Admin();
                a.setId(rst.getString("id"));

                Admins.add(a);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return Admins.size();

    }

    @Override
    public int nb_coachsV() {
        Statement stm = null;
        List<Coach> Coachs = new ArrayList<>();

        try {
            stm = cnx.createStatement();
            String CoachQ = "SELECT id FROM `user` WHERE role='CoachV'";
            ResultSet rst = stm.executeQuery(CoachQ);

            while (rst.next()) {
                Coach c = new Coach();
                c.setId(rst.getString("id"));

                Coachs.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return Coachs.size();

    }

    @Override
    public int nb_coachsNV() {
        Statement stm = null;
        List<Coach> Coachs = new ArrayList<>();

        try {
            stm = cnx.createStatement();
            String CoachQ = "SELECT id FROM `user` WHERE role='CoachNV'";
            ResultSet rst = stm.executeQuery(CoachQ);

            while (rst.next()) {
                Coach c = new Coach();
                c.setId(rst.getString("id"));

                Coachs.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return Coachs.size();

    }

    @Override
    public int nb_clients() {
        Statement stm = null;
        List<Client> Clients = new ArrayList<>();

        try {
            stm = cnx.createStatement();
            String ClientQ = "SELECT id FROM `user` WHERE role='Client'";
            ResultSet rst = stm.executeQuery(ClientQ);

            while (rst.next()) {
                Client cl = new Client();
                cl.setId(rst.getString("id"));

                Clients.add(cl);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return Clients.size();

    }

    @Override
    public String crypter_password(String password) {
        String hashValue = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.update(password.getBytes());
            byte[] digestedBytes = messageDigest.digest();
            hashValue = DatatypeConverter.printHexBinary(digestedBytes).toLowerCase();

        } catch (Exception e) {
        }

        return hashValue;
    }

}
