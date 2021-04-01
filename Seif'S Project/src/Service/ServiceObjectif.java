/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Objectif;
import Interfaces.IServiceObjectif;
import com.pdfjet.Table;
import utils.Connexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Cell;

/**
 *
 * @author Chirine
 */
public class ServiceObjectif implements IServiceObjectif {

    Connection cnx;

    public ServiceObjectif() {
        cnx = Connexion.getInstance().getConnection();
    }

    @Override
    public void ajouterObjectif(Objectif o, int checked) {

        String date = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
        try {
            Statement st = cnx.createStatement();
            String query = "INSERT INTO `objectif`(`id`, `description`, `reponse`, `dateDebut`, `duree`, `idClient`, `mailchecked`) "
                    + "VALUES ('" + o.getId() + "','"
                    + o.getDescription() + "','"
                    + o.getReponse() + "','"
                    + date + "','"
                    + o.getDuree() + "','"
                    + o.getCli().getId() + "','"
                    + checked + "')";
            st.executeUpdate(query);
            System.out.println("ajout avec succes");
        } catch (SQLException ex) {
            System.out.println("erreur ajouter objectifs");
            System.out.println(ex);
        }
    }

    @Override
    public ObservableList<Objectif> afficherObjectifs(String idCli) {
        ObservableList<Objectif> objectifs = FXCollections.observableArrayList();
        ServiceClient sc = new ServiceClient();
        try {
            Statement st = cnx.createStatement();
            String query = "select * from `objectif` WHERE idClient='"+idCli+"'";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Objectif o = new Objectif();
                o.setId(rs.getInt(1));
                o.setDescription(rs.getString(2));
                o.setReponse(rs.getInt(3));
                o.setDate(rs.getString(4));
                o.setDuree(rs.getInt(5));
                o.setCli(sc.load_data_modify(rs.getString(6)));
                objectifs.add(o);
            }

        } catch (SQLException ex) {
            System.out.println("erreur afficher objectifs");
            System.out.println(ex);
        }
        return objectifs;
    }

    @Override
    public void modifierObjectif(Objectif o) {
        try {
            Statement st = cnx.createStatement();
            //nb: on ne peut pas modifier la date
            String query = "UPDATE  objectif SET description  = '" + o.getDescription() + "', reponse = '" + o.getReponse() + "', duree = '" + o.getDuree() + "', dateDebut = '" + o.getDate() + "' WHERE id = '" + o.getId() + "'";
            st.executeUpdate(query);
            System.out.println("modification avec succes");
        } catch (SQLException ex) {
            System.out.println("erreur modifier objectif");
            System.out.println(ex);
        }
    }

    @Override
    public void supprimerObjectif(int id) {
        try {
            Statement st = cnx.createStatement();
            String query = "DELETE FROM `objectif` WHERE id = '" + id + "'";
            st.executeUpdate(query);
            System.out.println("suppression avec succes");
        } catch (SQLException ex) {
            System.out.println("erreur supprimer objectif");
            System.out.println(ex);
        }
    }

    @Override
    public ObservableList<String> getMesObjectifs(int id) {
        ObservableList<String> mesObjectifs = FXCollections.observableArrayList();
        try {
            Statement st = cnx.createStatement();
            String query = "select `description` FROM `objectif` WHERE idClient='" + id + "'";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                mesObjectifs.add(rs.getString("description"));
            }

        } catch (SQLException ex) {
            System.out.println("erreur afficher objectifs");
            System.out.println(ex);
        }
        return mesObjectifs;
    }

    @Override
    public String getIdObj(String obj, String idd) {
        String id = "";
        try {
            Statement st = cnx.createStatement();
            String query = "select `id` FROM `objectif` WHERE description='" + obj + "' AND idClient='" + idd + "'";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                id = rs.getString("id");
            }

        } catch (SQLException ex) {
            System.out.println("erreur get IdOBJ pour suivi");
            System.out.println(ex);
        }
        return id;
    }

    @Override
    public int getIdObjparDesc(String desc) {
        int id = 0;
        try {
            Statement st = cnx.createStatement();
            String query = "select `id` FROM `objectif` WHERE description='" + desc + "'";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                id = rs.getInt("id");
            }

        } catch (SQLException ex) {
            System.out.println("erreur get IdOBJ pour suivi");
            System.out.println(ex);
        }
        return id;
    }
    @Override
    public int getDureeparDesc(String desc) {
        int id = 0;
        try {
            Statement st = cnx.createStatement();
            String query = "select `duree` FROM `objectif` WHERE description='" + desc + "'";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                id = rs.getInt("duree");
            }

        } catch (SQLException ex) {
            System.out.println("erreur get IdOBJ pour suivi");
            System.out.println(ex);
        }
        return id;
    }

    //stats
    @Override
    public int getRepObj(int idObj) {
        int rep = 0;
        try {
            Statement st = cnx.createStatement();
            String query = "select `reponse` FROM `objectif` WHERE id='" + idObj + "'";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                rep = rs.getInt("reponse");
            }

        } catch (SQLException ex) {
            System.out.println("erreur getRepObj pour suivi (stats)");
            System.out.println(ex);
        }
        return rep;
    }
    
    

    @Override
    public ObservableList<Objectif> trierObjectifparRep() {
        ObservableList<Objectif> objectifs = FXCollections.observableArrayList();
        ServiceClient sc = new ServiceClient();
        try {
            Statement st = cnx.createStatement();
            String query = "select * from `objectif` ORDER BY reponse";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Objectif o = new Objectif();
                o.setId(rs.getInt(1));
                o.setDescription(rs.getString(2));
                o.setReponse(rs.getInt(3));
                o.setDate(rs.getString(4));
                //o.setDate(rs.getTimestamp(4).toString());
                o.setDuree(rs.getInt(5));
                o.setCli(sc.load_data_modify(rs.getString(6)));
                //o.setIdCli(rs.getString(6));
                objectifs.add(o);
            }

        } catch (SQLException ex) {
            System.out.println("erreur trier objectifs par reponse objectifs");
            System.out.println(ex);
        }
        return objectifs;
    }

    @Override
    public ObservableList<Objectif> trierObjectifparId() {
        ObservableList<Objectif> objectifs = FXCollections.observableArrayList();
        ServiceClient sc = new ServiceClient();
        try {
            Statement st = cnx.createStatement();
            String query = "select * from `objectif` ORDER BY id";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Objectif o = new Objectif();
                o.setId(rs.getInt(1));
                o.setDescription(rs.getString(2));
                o.setReponse(rs.getInt(3));
                o.setDate(rs.getString(4));
                //o.setDate(rs.getTimestamp(4).toString());
                o.setDuree(rs.getInt(5));
                o.setCli(sc.load_data_modify(rs.getString(6)));
                objectifs.add(o);
            }

        } catch (SQLException ex) {
            System.out.println("erreur trier objectifs par id objectifs");
            System.out.println(ex);
        }
        return objectifs;
    }

    @Override
    public ObservableList<Objectif> trierObjectifparDesc() {
        ObservableList<Objectif> objectifs = FXCollections.observableArrayList();
        ServiceClient sc = new ServiceClient();
        try {
            Statement st = cnx.createStatement();
            String query = "select * from `objectif` ORDER BY description";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Objectif o = new Objectif();
                o.setId(rs.getInt(1));
                o.setDescription(rs.getString(2));
                o.setReponse(rs.getInt(3));
                o.setDate(rs.getString(4));
                //o.setDate(rs.getTimestamp(4).toString());
                o.setDuree(rs.getInt(5));
                o.setCli(sc.load_data_modify(rs.getString(6)));
                objectifs.add(o);
            }

        } catch (SQLException ex) {
            System.out.println("erreur trier objectifs par description objectifs");
            System.out.println(ex);
        }
        return objectifs;
    }

    @Override
    public ObservableList<Objectif> rechercherObjectif(String s) {
        ObservableList<Objectif> objectifs = FXCollections.observableArrayList();
        ServiceClient sc = new ServiceClient();
        try {
            Statement st = cnx.createStatement();
            String query = "select * from `objectif` WHERE id LIKE '%" + s + "%' OR description LIKE '%" + s + "%' OR reponse LIKE '%" + s + "%' OR dateDebut LIKE '%" + s + "%' OR duree LIKE '%" + s + "%'";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Objectif o = new Objectif();
                o.setId(rs.getInt(1));
                o.setDescription(rs.getString(2));
                o.setReponse(rs.getInt(3));
                o.setDate(rs.getString(4));
                o.setDuree(rs.getInt(5));
                o.setCli(sc.load_data_modify(rs.getString(6)));
                objectifs.add(o);
            }

        } catch (SQLException ex) {
            System.out.println("erreur rechercher objectif");
            System.out.println(ex);
        }
        return objectifs;
    }

    @Override
    public String getJourDateDebutObj(int idObj, String idCli) {
        String jour = "";
        try {
            Statement st = cnx.createStatement();
            String query = "select SUBSTRING(dateDebut, 1, 2)  from `objectif` WHERE id = '" + idObj + "' AND idClient ='" + idCli + "'";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                jour = rs.getString("SUBSTRING(dateDebut, 1, 2)");
            }

        } catch (SQLException ex) {
            System.out.println("erreur get jour date debut objectif");
            System.out.println(ex);
        }
        return jour;
    }
    
    @Override
    public String getDateDebutObj(int idObj, String idCli) {
        String date = "";
        try {
            Statement st = cnx.createStatement();
            String query = "select dateDebut  from `objectif` WHERE id = '" + idObj + "' AND idClient ='" + idCli + "'";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                date = rs.getString("dateDebut");
            }

        } catch (SQLException ex) {
            System.out.println("erreur get jour date debut objectif");
            System.out.println(ex);
        }
        return date;
    }

    @Override
    public int getDureeObj(int idObj, String idCli) {
        int duree = 0;
        try {
            Statement st = cnx.createStatement();
            String query = "select duree  from `objectif` WHERE id = '" + idObj + "' AND idClient ='" + idCli + "'";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                duree = rs.getInt("duree");
            }

        } catch (SQLException ex) {
            System.out.println("erreur get jour date debut objectif");
            System.out.println(ex);
        }
        return duree;
    }

    @Override
    public Objectif load_data_modify(int id) {
        Statement stm = null;
        Objectif o = new Objectif();
        try {
            stm = cnx.createStatement();
            String query = "SELECT * FROM `objectif` WHERE id='" + id + "'";
            ResultSet rs = stm.executeQuery(query);

            while (rs.next()) {
                o.setId(rs.getInt(1));
                o.setDescription(rs.getString(2));
                o.setReponse(rs.getInt(3));
                o.setDate(rs.getString(4));
                o.setDuree(rs.getInt(5));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return o;
    }

    @Override
    public void mailCkeckedObj() {
        try {
            Statement st = cnx.createStatement();
            String query = "INSERT INTO `objectif`(`mailchecked`) VALUES (1)";
            st.executeUpdate(query);
            System.out.println("ajout mailchecked avec succes");
        } catch (SQLException ex) {
            System.out.println("erreur ajouter mailchecked objectifs");
            System.out.println(ex);
        }
    }
    
    @Override
    public int retrunmailCkeckedObj(int idObj, int idCli) {
         int checked = 0;
        try {
            Statement st = cnx.createStatement();
            String query = "select mailchecked  from `objectif` WHERE id = '" + idObj + "' AND idClient ='" + idCli + "'";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                checked = rs.getInt("mailchecked");
            }

        } catch (SQLException ex) {
            System.out.println("erreur get mail checked objectif");
            System.out.println(ex);
        }
        return checked;
    }

}
