/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Suivi;
import Interfaces.IServiceSuivi;
import Utils.MaConnexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Chirine
 */
public class ServiceSuivi implements IServiceSuivi {

    Connection cnx;

    public ServiceSuivi() {
        cnx = MaConnexion.getInstance().getCnx();
    }

    @Override
    public void ajouterSuivi(Suivi s) {
        try {
            Statement st = cnx.createStatement();
            String query = "INSERT INTO `suivi`(`id`, `valeur`, `idClient`, `idObjectif`, `date`) "
                    + "VALUES ('" + s.getIdSuiv() + "','"
                    + s.getValeurSuiv() + "','"
                    + s.getIdClientSuiv() + "','"
                    + s.getIdObjectifSuiv() + "','"
                    + s.getDateSuiv() + "')";
            st.executeUpdate(query);
            System.out.println("ajout suivi avec succes");
        } catch (SQLException ex) {
            System.out.println("erreur ajout suivi");
            System.out.println(ex);
        }
    }

    @Override
    public ObservableList<Suivi> afficherSuivi() {
        ObservableList<Suivi> suivis = FXCollections.observableArrayList();
        try {
            Statement st = cnx.createStatement();
            String query = "select * from `suivi`";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Suivi s = new Suivi();
                s.setIdSuiv(rs.getString(1));
                s.setValeurSuiv(rs.getInt(2));
                s.setIdClientSuiv(rs.getString(3));
                s.setIdObjectifSuiv(rs.getString(4));
                s.setDateSuiv(rs.getString(5));

                suivis.add(s);
            }

        } catch (SQLException ex) {
            System.out.println("erreur afficher suivis");
            System.out.println(ex);
        }
        return suivis;
    }

    @Override
    public int getValeur(String idObj, String date) {
        int valeur = 0;
        try {
            Statement st = cnx.createStatement();
            String query = "select `valeur` FROM `suivi` WHERE idObjectif='" + idObj + "' AND date='" + date + "'";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                valeur = rs.getInt("valeur");
            }

        } catch (SQLException ex) {
            System.out.println("erreur getValeur Suivi (stats)");
            System.out.println(ex);
        }
        return valeur;
    }

    @Override
    public ObservableList<Suivi> rechercherSuivi(String s) {
        ObservableList<Suivi> suivis = FXCollections.observableArrayList();
        try {
            Statement st = cnx.createStatement();
            String query = "select * from `suivi` WHERE id LIKE '%" + s + "%' OR valeur LIKE '%" + s + "%' OR idClient LIKE '%" + s + "%' OR idObjectif LIKE '%" + s + "%' OR date LIKE '%" + s + "%'";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Suivi ss = new Suivi();
                ss.setIdSuiv(rs.getString(1));
                ss.setValeurSuiv(rs.getInt(2));
                ss.setIdClientSuiv(rs.getString(3));
                ss.setIdObjectifSuiv(rs.getString(4));
                ss.setDateSuiv(rs.getString(5));

                suivis.add(ss);
            }

        } catch (SQLException ex) {
            System.out.println("erreur rechercher suivi");
            System.out.println(ex);
        }
        return suivis;
    }

    @Override
    public ObservableList<Suivi> trierSuiviparIdClient() {
        ObservableList<Suivi> suivis = FXCollections.observableArrayList();
        try {
            Statement st = cnx.createStatement();
            String query = "select * from `suivi` ORDER BY idClient";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Suivi s = new Suivi();
                s.setIdSuiv(rs.getString(1));
                s.setValeurSuiv(rs.getInt(2));
                s.setIdClientSuiv(rs.getString(3));
                s.setIdObjectifSuiv(rs.getString(4));
                s.setDateSuiv(rs.getString(5));
                suivis.add(s);
            }

        } catch (SQLException ex) {
            System.out.println("erreur trier suivi par idClient");
            System.out.println(ex);
        }
        return suivis;
    }

    @Override
    public ObservableList<Suivi> trierSuiviparId() {
        ObservableList<Suivi> suivis = FXCollections.observableArrayList();
        try {
            Statement st = cnx.createStatement();
            String query = "select * from `suivi` ORDER BY id";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Suivi s = new Suivi();
                s.setIdSuiv(rs.getString(1));
                s.setValeurSuiv(rs.getInt(2));
                s.setIdClientSuiv(rs.getString(3));
                s.setIdObjectifSuiv(rs.getString(4));
                s.setDateSuiv(rs.getString(5));
                suivis.add(s);
            }

        } catch (SQLException ex) {
            System.out.println("erreur trier suivi par id");
            System.out.println(ex);
        }
        return suivis;
    }

    @Override
    public ObservableList<Suivi> trierSuiviparIdObjectif() {
        ObservableList<Suivi> suivis = FXCollections.observableArrayList();
        try {
            Statement st = cnx.createStatement();
            String query = "select * from `suivi` ORDER BY idObjectif";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Suivi s = new Suivi();
                s.setIdSuiv(rs.getString(1));
                s.setValeurSuiv(rs.getInt(2));
                s.setIdClientSuiv(rs.getString(3));
                s.setIdObjectifSuiv(rs.getString(4));
                s.setDateSuiv(rs.getString(5));
                suivis.add(s);
            }

        } catch (SQLException ex) {
            System.out.println("erreur trier suivi par idObjectif");
            System.out.println(ex);
        }
        return suivis;
    }

    @Override
    public ObservableList<String> getObjectifBilan(String idClient) {
        ObservableList<String> suivis = FXCollections.observableArrayList();
        try {
            Statement st = cnx.createStatement();
            String query = "select idObjectif from `suivi` WHERE idClient ='" + idClient + "'";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                suivis.add(rs.getString("idObjectif"));
            }

        } catch (SQLException ex) {
            System.out.println("erreur get objectif suivi bilan");
            System.out.println(ex);
        }
        return suivis;
    }

    @Override
    public ObservableList<String> getDateBilan(String idObjectif) {
 ObservableList<String> suivis = FXCollections.observableArrayList();
        try {
            Statement st = cnx.createStatement();
            String query = "select date from `suivi` WHERE idObjectif ='" + idObjectif + "'";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                suivis.add(rs.getString("date"));
            }

        } catch (SQLException ex) {
            System.out.println("erreur get date suivi bilan");
            System.out.println(ex);
        }
        return suivis;    }

}
