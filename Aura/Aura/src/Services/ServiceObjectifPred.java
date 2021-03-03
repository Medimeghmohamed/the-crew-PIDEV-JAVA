/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Objectif;
import Entities.ObjectifPred;
import Interfaces.IServiceObjectifPred;
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
public class ServiceObjectifPred implements IServiceObjectifPred {

    Connection cnx;

    public ServiceObjectifPred() {
        cnx = MaConnexion.getInstance().getCnx();
    }

    @Override
    public void ajouterObjectifPred(ObjectifPred o) {
        try {
            Statement st = cnx.createStatement();
            String query = "INSERT INTO `objectif_pred`(`id`, `description`, `duree`, `idAdmin`) "
                    + "VALUES ('" + o.getIdP() + "','"
                    + o.getDescriptionP() + "','"
                    + o.getDureeP() + "','"
                    + o.getIdAdminP() + "')";
            st.executeUpdate(query);
            System.out.println("ajout objectif pred avec succes");
        } catch (SQLException ex) {
            System.out.println("erreur ajouter objectifs predefinis");
            System.out.println(ex);
        }
    }

    @Override
    public ObservableList<ObjectifPred> afficherObjectifsPred() {
        ObservableList<ObjectifPred> objectifsP = FXCollections.observableArrayList();
        try {
            Statement st = cnx.createStatement();
            String query = "SELECT * FROM `objectif_pred`";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                ObjectifPred o = new ObjectifPred();
                o.setIdP(rs.getString(1));
                o.setDescriptionP(rs.getString(2));
                o.setDureeP(rs.getInt(3));
                o.setIdAdminP(rs.getString(4));
                objectifsP.add(o);
            }

        } catch (SQLException ex) {
            System.out.println("erreur afficher objectifs predefinis");
            System.out.println(ex);
        }
        return objectifsP;
    }

    @Override
    public void modifierObjectifPred(ObjectifPred o) {
        try {
            Statement st = cnx.createStatement();
            //"', reponse = '" + o.getReponseP()+ ", duree = " + o.getDureeP()+
            String query = "UPDATE  objectif_pred SET description  = '" + o.getDescriptionP() + "', duree = '" + o.getDureeP() + "'  WHERE id ='" + o.getIdP() + "'";
            st.executeUpdate(query);
            System.out.println("modification avec succes");
        } catch (SQLException ex) {
            System.out.println("erreur modifier objectif");
            System.out.println(ex);
        }
    }

    @Override
    public void supprimerObjectifPred(String id) {
        try {
            Statement st = cnx.createStatement();
            String query = "DELETE FROM `objectif_pred` WHERE id='"+ id +"'";
            st.executeUpdate(query);
            System.out.println("supprimer objectif pred succes");
        } catch (SQLException ex) {
            System.out.println("erreur supprimer objectif");
            System.out.println(ex);
        }
    }

    @Override
    public ObservableList<String> getValuesObjectifs() {
        ObservableList<String> objectifsP = FXCollections.observableArrayList();
        try {
            Statement st = cnx.createStatement();
            String query = "SELECT `description` FROM `objectif_pred`";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                objectifsP.add(rs.getString("description"));
            }

        } catch (SQLException ex) {
            System.out.println("erreur get values objectifs (pour comboBox)");
            System.out.println(ex);
        }
        return objectifsP;
    }

    @Override
    public ObservableList<ObjectifPred> rechercherObjectifPred(String s) {
        ObservableList<ObjectifPred> objectifs = FXCollections.observableArrayList();
        try {
            Statement st = cnx.createStatement();
            String query = "select * from `objectif_pred` WHERE id LIKE '%" + s + "%' OR description LIKE '%" + s + "%' OR duree LIKE '%" + s + "%' OR idAdmin LIKE '%" + s + "%'";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                ObjectifPred o = new ObjectifPred();
                o.setIdP(rs.getString(1));
                o.setDescriptionP(rs.getString(2));
                o.setDureeP(rs.getInt(3));
                o.setIdAdminP(rs.getString(4));
                objectifs.add(o);
            }

        } catch (SQLException ex) {
            System.out.println("erreur rechercher objectif prédéfini");
            System.out.println(ex);
        }
        return objectifs;
    }

    @Override
    public ObservableList<ObjectifPred> trierObjectifPredparDuree() {
        ObservableList<ObjectifPred> objectifs = FXCollections.observableArrayList();
        try {
            Statement st = cnx.createStatement();
            String query = "select * from `objectif_pred` ORDER BY duree";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                ObjectifPred o = new ObjectifPred();
                o.setIdP(rs.getString(1));
                o.setDescriptionP(rs.getString(2));
                o.setDureeP(rs.getInt(3));
                o.setIdAdminP(rs.getString(4));
                objectifs.add(o);
            }

        } catch (SQLException ex) {
            System.out.println("erreur trier objectifs prédéfinis par durée objectifs");
            System.out.println(ex);
        }
        return objectifs;
    }

    @Override
    public ObservableList<ObjectifPred> trierObjectifPredparId() {
        ObservableList<ObjectifPred> objectifs = FXCollections.observableArrayList();
        try {
            Statement st = cnx.createStatement();
            String query = "select * from `objectif_pred` ORDER BY id";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                ObjectifPred o = new ObjectifPred();
                o.setIdP(rs.getString(1));
                o.setDescriptionP(rs.getString(2));
                o.setDureeP(rs.getInt(3));
                o.setIdAdminP(rs.getString(4));
                objectifs.add(o);
            }

        } catch (SQLException ex) {
            System.out.println("erreur trier objectifs prédéfinis par id objectifs");
            System.out.println(ex);
        }
        return objectifs;
    }

    @Override
    public ObservableList<ObjectifPred> trierObjectifPredparDesc() {
        ObservableList<ObjectifPred> objectifs = FXCollections.observableArrayList();
        try {
            Statement st = cnx.createStatement();
            String query = "select * from `objectif_pred` ORDER BY description";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                ObjectifPred o = new ObjectifPred();
                o.setIdP(rs.getString(1));
                o.setDescriptionP(rs.getString(2));
                o.setDureeP(rs.getInt(3));
                o.setIdAdminP(rs.getString(4));
                objectifs.add(o);
            }

        } catch (SQLException ex) {
            System.out.println("erreur trier objectifs prédéfinis par description objectifs");
            System.out.println(ex);
        }
        return objectifs;
    }

}
