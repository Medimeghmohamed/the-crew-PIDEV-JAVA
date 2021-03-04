/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.challenge;
import entities.classement;
import entities.niveau;
import services.IServiceChallege;
import utils.Myconnexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NOUR
 */
public class ServiceChallenge implements IServiceChallege {

    Connection cnx;

    public ServiceChallenge() {
        cnx = Myconnexion.getInstance().getConnection();

    }

    @Override
    public void ajouterChallenge(challenge c) {

        c.setNb_participants(5);
        c.setImg("hello");

        try {
            if (cnx != null) {

                Statement stm;
                stm = Myconnexion
                        .getInstance()
                        .getConnection()
                        .createStatement();
                assert stm != null;

                String query = "INSERT INTO `challenge`(`id`, `titre`, `type`, `description`, `img`, `date_debut`,`date_fin`, `nb_participants`, `etat`, `id_niveau`) "
                        + "VALUES ( NULL,'"
                        + c.getTitre() + "','"
                        + c.getType() + "','"
                        + c.getDescription() + "','"
                        + c.getImg() + "','"
                        + c.getDate_debut() + "','"
                        + c.getDate_fin() + "','"
                        + c.getNb_participants() + "','"
                        + c.getEtat() + "','"
                        + c.getNiveau() + "')";
                stm.executeUpdate(query);
                System.out.println("ajout avec succes");
            } else {
                System.out.println("cnx NULL");
            }
        } catch (SQLException ex) {

            Logger.getLogger(ServiceChallenge.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public challenge recup_challenge(int id) {
        challenge ch = new challenge();

        try {
            Statement st = cnx.createStatement();
            String query = "select * FROM `challenge` WHERE id='" + id + "'";
            ResultSet rst = st.executeQuery(query);
            while (rst.next()) {

                ch.setId(rst.getInt(1));
                ch.setTitre(rst.getString(2));
                ch.setType(rst.getString(3));
                ch.setDescription(rst.getString(4));
                ch.setImg(rst.getString(5));
                ch.setDate_debut(rst.getDate(6));
                ch.setDate_fin(rst.getDate(7));
                ch.setNb_participants(rst.getInt(8));
                ch.setEtat(rst.getString(9));
                System.out.println("hhhhhh");
                ch.setNiveau(rst.getInt(10));

            }

        } catch (SQLException ex) {
            System.out.println("erreur get IdOBJ pour suivi");
            System.out.println(ex);
        }

        return ch;

    }

    @Override
    public ObservableList<challenge> afficherChallenge() {
        Statement stm = null;
        ObservableList<challenge> challenges = FXCollections.observableArrayList();

        try {

            stm = Myconnexion.getInstance().getConnection().createStatement();

            String query = "SELECT * FROM `challenge` ";
            ResultSet rst = stm.executeQuery(query);

            while (rst.next()) {
                challenge c = new challenge();
                c.setId(rst.getInt(1));
                c.setTitre(rst.getString("titre"));
                c.setType(rst.getString("type"));
                c.setDescription(rst.getString("description"));
                // c.setImg(rst.getString(5));
                c.setDate_debut(rst.getDate("date_debut"));
                c.setDate_fin(rst.getDate("date_fin"));
                c.setNb_participants(rst.getInt("nb_participants"));
                c.setEtat(rst.getString("etat"));
                c.setNiveau(rst.getInt(10));

                challenges.add(c);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceChallenge.class.getName()).log(Level.SEVERE, null, ex);
        }

        return challenges;
    }

    @Override
    public void modifierChallenge(challenge c) {
        try {
            Statement st = cnx.createStatement();

            String query = "UPDATE  challenge SET titre= '" + c.getTitre() + "', type = '" + c.getType() + "', description = '" + c.getDescription() + "', date_debut = '" + c.getDate_debut() + "', date_fin = '" + c.getDate_fin() + "', etat = '" + c.getEtat() + "', id_niveau = '" + c.getNiveau() + "'  WHERE id = " + c.getId() + "";
            st.executeUpdate(query);
            System.out.println("modification avec succes");
        } catch (SQLException ex) {
            System.out.println("erreur modifier challenge");
            System.out.println(ex);
        }
    }

    @Override
    public void supprimerChallenge(String id) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        int Id = Integer.parseInt(id);
        try {
            Statement st = cnx.createStatement();
            String query = "DELETE FROM `challenge` WHERE id = " + Id + "";
            st.executeUpdate(query);
            System.out.println("suppression avec succes");
        } catch (SQLException ex) {
            System.out.println("erreur supprimer challenge");
            System.out.println(ex);
        }
    }

    /**
     *
     * @return
     */
    @Override
    public ObservableList<challenge> trierChallenge() {
        Statement stm = null;
        ObservableList<challenge> challenges = FXCollections.observableArrayList();

        try {

            stm = Myconnexion.getInstance().getConnection().createStatement();

            String query = "SELECT * FROM `challenge` ORDER BY date_debut";
            ResultSet rst = stm.executeQuery(query);

            while (rst.next()) {
                challenge c = new challenge();
                c.setId(rst.getInt(1));
                c.setTitre(rst.getString("titre"));
                c.setType(rst.getString("type"));
                c.setDescription(rst.getString("description"));
                // c.setImg(rst.getString(5));
                c.setDate_debut(rst.getDate("date_debut"));
                c.setDate_fin(rst.getDate("date_fin"));
                c.setNb_participants(rst.getInt("nb_participants"));
                c.setEtat(rst.getString("etat"));
                c.setNiveau(rst.getInt(10));

                challenges.add(c);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceChallenge.class.getName()).log(Level.SEVERE, null, ex);
        }

        return challenges;

    }

}
