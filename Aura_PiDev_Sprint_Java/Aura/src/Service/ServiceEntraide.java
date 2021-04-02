/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Entraide;
import Interfaces.IServiceEntraide;
import utils.Connexion;
import java.sql.Connection;
import java.sql.Date;
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
        cnx = Connexion.getInstance().getConnection();

    }

    @Override
    public void AjouterEntraide(Entraide e) {
        try {

            Statement stm = Connexion.getInstance().getConnection().createStatement();

            String query = "INSERT INTO `entraide`( `id`, `question`, `categorie`, `id_user`, `date`, `email`)"
                    + "VALUES ('"
                    + e.getId() + "','"
                    + e.getQuestion() + "','"
                    + e.getCategorie() + "','"
                    + e.getId_user() + "','"
                    + e.getDate()+ "','"
                    +e.getEmail()
                    + "')";

            stm.executeUpdate(query);
            System.out.println("ajout question avec succes");
        } catch (SQLException ex) {
            System.out.println("erreur ajout question ");
            System.out.println(ex);
        }

    }

    @Override
    public ObservableList<Entraide> AfficherEntraide() {
        ObservableList<Entraide> entraides = FXCollections.observableArrayList();
        try {
             Statement stm = Connexion.getInstance().getConnection().createStatement();
           
            String query = "select * from `entraide`";

            ResultSet rst = stm.executeQuery(query);

            while (rst.next()) {
                Entraide e = new Entraide();

                e.setEmail(rst.getString(6));
                e.setCategorie(rst.getString(3));
                e.setQuestion(rst.getString(2));
                e.setDate(rst.getDate(5));
                entraides.add(e);

            }

        } catch (SQLException ex) {
            System.out.println("erreur affichage entraide ");
            System.out.println(ex);
        }

        return entraides;
    }

    @Override
    public void SupprimerEntraide(String question) {
        Statement stm =null;
      
        try {
            stm= Connexion.getInstance().getConnection().createStatement();
                String query = "DELETE FROM entraide WHERE question = '"+question+"' ";
                stm.executeUpdate(query);
            System.out.println("suppression avec succes");
            
        } catch (SQLException ex) {
            System.out.println("erreur supprimer entraide");
            System.out.println(ex);
        }
    }

    
      @Override
      public void ModifierEntraide(int id, String questionn, String categoriee, String datee ) {
        try {
            Statement st = cnx.createStatement();
            
            System.out.println(id);

            String query = "UPDATE entraide SET question='" + questionn +"',categorie='"+categoriee+"', date='"+datee+"'WHERE id= id ";
                    
                    
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

                e.setId_user(rst.getString(4));
                e.setQuestion(rst.getString(2));
                e.setCategorie(rst.getString(3));
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
    public ObservableList<Entraide> Rechercher(String a) {
        ObservableList<Entraide> Oentraides = FXCollections.observableArrayList();
        ServiceEntraide se = new ServiceEntraide();

        try {
            Statement stm = cnx.createStatement();
            String query = " select * from `entraide`  WHERE id_user LIKE '%" + a + "%' OR question LIKE '%" + a + "%'   OR categorie LIKE   '%" + a + "%' OR id_user LIKE '%" + a + "%' OR date LIKE '%" + a + "%'";

            ResultSet rst = stm.executeQuery(query);

            while (rst.next()) {
                Entraide e = new Entraide();
                e.setId(rst.getInt(1));
                e.setQuestion(rst.getString(2));
                e.setCategorie(rst.getString(3));
                e.setId_user(rst.getString(4));
                e.setDate(rst.getDate(5));
                Oentraides.add(e);
            }
        } catch (SQLException ex) {
            System.out.println("erreur affichage ");
            System.out.println(ex);
        }
        return Oentraides;

    }

    

    

}
