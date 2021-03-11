/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Entraide;
import Services.IServiceEntraide;
import Utils.Maconnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

/**
 *
 * @author Nour Dekhil
 */
public class ServiceEntraide implements IServiceEntraide {

    Connection cnx;

    public ServiceEntraide() {
        cnx = Maconnexion.getInstance().getCnx();

    }

    @Override
    public void AjouterEntraide(Entraide e) {
        try {

            Statement stm = cnx.createStatement();

            String query = "INSERT INTO `entraide`( `id`, `id_client`, `categorie`, `question`, `date`)"
                    + "VALUES ('"
                    + e.getId() + "','"
                    + e.getId_client() + "','"
                    + e.getCategorie() + "','"
                    + e.getQuestion() + "','"
                    + e.getDate()
                    + "')";

            stm.executeUpdate(query);
            System.out.println("ajout avec succes");
        } catch (SQLException ex) {
            System.out.println("erreur ajout");
            System.out.println(ex);
        }

    }

    @Override
    public ObservableList<Entraide> AfficherEntraide() {
        ObservableList<Entraide> entraides = FXCollections.observableArrayList();
        try {
            Statement stm = cnx.createStatement();
            String query = "select * from `entraide`";

            ResultSet rst = stm.executeQuery(query);

            while (rst.next()) {
                Entraide e = new Entraide();

                e.setId(rst.getInt(1));
                e.setQuestion(rst.getString(2));
                e.setCategorie(rst.getString(3));
                e.setId_client(rst.getString(4));
                e.setDate(rst.getDate(5));
                entraides.add(e);

            }

        } catch (SQLException ex) {
            System.out.println("erreur affichage ");
            System.out.println(ex);
        }

        return entraides;
    }

    @Override
    public void SupprimerEntraide(String id) {
        //int Id = Integer.parseInt(id);
        try {
            Statement st = cnx.createStatement();
            String query = "DELETE FROM entraide WHERE id = " + id + "";
            st.executeUpdate(query);
            System.out.println("suppression avec succes");
        } catch (SQLException ex) {
            System.out.println("erreur supprimer challenge");
            System.out.println(ex);
        }
    }

    @Override
    public void ModifierEntraide(String idd, String question) {
        try {
            Statement st = cnx.createStatement();

            String query = "UPDATE entraide SET question='" + question + "' WHERE id_client='" + idd + "'";
            st.executeUpdate(query);
            System.out.println("modification avec succes");
        } catch (SQLException ex) {
            System.out.println("erreur modifier");
            System.out.println(ex);
        }

    }

    @Override
    public ObservableList<Entraide> TrierEntraide() {
        ObservableList<Entraide> entraides = FXCollections.observableArrayList();
        try {
            Statement stm = cnx.createStatement();
            String query = "select * from `entraide` ORDER BY date";

            ResultSet rst = stm.executeQuery(query);

            while (rst.next()) {
                Entraide e = new Entraide();

                e.setId(rst.getInt(1));
                e.setQuestion(rst.getString(2));
                e.setCategorie(rst.getString(3));
                e.setId_client(rst.getString(4));
                e.setDate(rst.getDate(5));
                entraides.add(e);

            }

        } catch (SQLException ex) {
            System.out.println("erreur affichage ");
            System.out.println(ex);
        }

        return entraides;
        
    }

    @Override
    public ObservableList<Entraide> Rechercher(String id) {
         ObservableList<Entraide> entraides = FXCollections.observableArrayList();
        try {
            Statement stm = cnx.createStatement();
            String query = " select * from `entraide`  WHERE id_client = id ";

            ResultSet rst = stm.executeQuery(query);

            while (rst.next()) {
                Entraide e = new Entraide();

                e.setId(rst.getInt(1));
                e.setQuestion(rst.getString(2));
                e.setCategorie(rst.getString(3));
                e.setId_client(rst.getString(4));
                e.setDate(rst.getDate(5));
                entraides.add(e);

            }

        } catch (SQLException ex) {
            System.out.println("erreur affichage ");
            System.out.println(ex);
        }

        return entraides;
        
        
    }

  

}
