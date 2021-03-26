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
                    + s.getClient().getId() + "','"
                    + s.getObjectif().getId() + "','"
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
        ServiceClient sc = new ServiceClient();
        ServiceObjectif so = new ServiceObjectif();

        try {
            Statement st = cnx.createStatement();
            String query = "select * from `suivi`";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Suivi s = new Suivi();
                s.setIdSuiv(rs.getInt(1));
                s.setValeurSuiv(rs.getInt(2));
                s.setClient(sc.load_data_modify(rs.getString(3)));
                s.setObjectif(so.load_data_modify(rs.getInt(4)));
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
    public int getValeur(int idObj, String date) {
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
        ServiceClient sc = new ServiceClient();
        ServiceObjectif so = new ServiceObjectif();

        try {
            Statement st = cnx.createStatement();
            String query = "select * from `suivi` WHERE id LIKE '%" + s + "%' OR valeur LIKE '%" + s + "%' OR idClient LIKE '%" + s + "%' OR idObjectif LIKE '%" + s + "%' OR date LIKE '%" + s + "%'";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Suivi ss = new Suivi();
                ss.setIdSuiv(rs.getInt(1));
                ss.setValeurSuiv(rs.getInt(2));
                ss.setClient(sc.load_data_modify(rs.getString(3)));
                ss.setObjectif(so.load_data_modify(rs.getInt(4)));
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
        ServiceClient sc = new ServiceClient();
        ServiceObjectif so = new ServiceObjectif();

        try {
            Statement st = cnx.createStatement();
            String query = "select * from `suivi` ORDER BY idClient";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Suivi s = new Suivi();
                s.setIdSuiv(rs.getInt(1));
                s.setValeurSuiv(rs.getInt(2));
                s.setClient(sc.load_data_modify(rs.getString(3)));
                s.setObjectif(so.load_data_modify(rs.getInt(4)));
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
        ServiceClient sc = new ServiceClient();
        ServiceObjectif so = new ServiceObjectif();

        try {
            Statement st = cnx.createStatement();
            String query = "select * from `suivi` ORDER BY id";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Suivi s = new Suivi();
                s.setIdSuiv(rs.getInt(1));
                s.setValeurSuiv(rs.getInt(2));
                s.setClient(sc.load_data_modify(rs.getString(3)));
                s.setObjectif(so.load_data_modify(rs.getInt(4)));
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
        ServiceClient sc = new ServiceClient();
        ServiceObjectif so = new ServiceObjectif();

        try {
            Statement st = cnx.createStatement();
            String query = "select * from `suivi` ORDER BY idObjectif";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Suivi s = new Suivi();
                s.setIdSuiv(rs.getInt(1));
                s.setValeurSuiv(rs.getInt(2));
                s.setClient(sc.load_data_modify(rs.getString(3)));
                s.setObjectif(so.load_data_modify(rs.getInt(4)));
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
    public ObservableList<String> getDateBilan(int idObjectif) {
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
        return suivis;
    }

    @Override
    public String getJour(int idSuiv) {
        String jour = "";
        try {
            Statement st = cnx.createStatement();
            String query = "select SUBSTR(date, 1, 2)  from `suivi` WHERE id = '" + idSuiv + "'";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                jour = rs.getString("SUBSTR(date, 1, 2)");
            }

        } catch (SQLException ex) {
            System.out.println("erreur get jour suivi");
            System.out.println(ex);
        }
        return jour;
    }

    @Override
    public ObservableList<Suivi> getListSuivi(int idObj) {
        ObservableList<Suivi> suivis = FXCollections.observableArrayList();
        ServiceClient sc = new ServiceClient();
        ServiceObjectif so = new ServiceObjectif();

        try {
            Statement st = cnx.createStatement();
            String query = "select * from `suivi` WHERE idObjectif LIKE '" + idObj + "'";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Suivi ss = new Suivi();
                ss.setIdSuiv(rs.getInt(1));
                ss.setValeurSuiv(rs.getInt(2));
                ss.setClient(sc.load_data_modify(rs.getString(3)));
                ss.setObjectif(so.load_data_modify(rs.getInt(4)));
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
    public boolean rechercherDate(int id, String date) {
        ObservableList<Suivi> suivis = FXCollections.observableArrayList();
        ServiceClient sc = new ServiceClient();
        ServiceObjectif so = new ServiceObjectif();

        try {
            Statement st = cnx.createStatement();
            String query = "select date from `suivi` WHERE idObjectif LIKE '" + id + "' AND date LIKE'" + date + "'";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Suivi ss = new Suivi();
                ss.setDateSuiv(rs.getString(5));

                suivis.add(ss);
            }

        } catch (SQLException ex) {
            System.out.println("erreur rechercher date suivi");
            System.out.println(ex);
        }
        if(suivis.isEmpty()){
            return true;
        }
        return false;
    }

}
