/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Client;
import Interfaces.IServiceClient;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Connexion;

/**
 *
 * @author SeifBS
 */
public class ServiceClient implements IServiceClient {

    Connection cnx;

    public ServiceClient() {
        cnx = Connexion.getInstance().getConnection();

    }

    public void ajouterClient(Client cl) {

        Statement stm;
        try {
            stm = cnx.createStatement();

            String query = "	INSERT INTO `user`(`id`, `nom`, `prenom`, `email`, `password`, `tel`,`specialite`,`adresse`,`role`) VALUES ('" + cl.getId() + "','" + cl.getNom() + "','" + cl.getPrenom() + "','" + cl.getEmail() + "','" + cl.getPassword() + "','" + cl.getTel() + "','" + "" + "','" + cl.getAdresse() + " ','Client')";

            stm.executeUpdate(query);

        } catch (SQLException ex) {
            Logger.getLogger(ServiceClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void modifierClient(Client cl) {

        Statement stm;
        try {
            stm = cnx.createStatement();

            String query = "UPDATE user SET id='" + cl.getId() + "',nom='" + cl.getNom() + "',prenom='" + cl.getPrenom() + "',email='" + cl.getEmail() + "',tel='" + cl.getTel() + "',adresse='" + cl.getAdresse() + "' WHERE id='" + cl.getId() + "'";
            stm.executeUpdate(query);

        } catch (SQLException ex) {
            Logger.getLogger(ServiceClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void supprimerClient(String id) {
        Statement stm;
        try {
            stm = cnx.createStatement();

            String query = "DELETE FROM user WHERE id='" + id + "'";
            stm.executeUpdate(query);

        } catch (SQLException ex) {
            Logger.getLogger(ServiceClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Client load_data_modify(String id) { // charger données Client pour la modification

        Statement stm = null;
        Client c = new Client();

        try {
            stm = cnx.createStatement();
            String query = "SELECT * FROM `user`  WHERE id='" + id + "' OR tel='" + id + "'OR email='" + id + "' ";
            ResultSet rst = stm.executeQuery(query);

            while (rst.next()) {
                c.setId(rst.getString("id"));
                c.setNom(rst.getString("nom"));
                c.setPrenom(rst.getString("prenom"));
                c.setEmail(rst.getString("email"));
                c.setPassword(rst.getString("password"));
                c.setTel(rst.getString("tel"));
                c.setAdresse(rst.getString("adresse"));
                c.setRole(rst.getString("role"));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return c;

    }

    public Client load_user_name(String id) { //get nom de l'identifiant apres login

        Statement stm = null;
        Client c = new Client();
        try {
            stm = cnx.createStatement();
            String query = "SELECT * FROM `user` WHERE id='" + id + "' OR tel='" + id + "'OR email='" + id + "'  ";
            ResultSet rst = stm.executeQuery(query);

            while (rst.next()) {
                c.setId(rst.getString("id"));
                c.setNom(rst.getString("nom"));
                c.setPrenom(rst.getString("prenom"));
                c.setEmail(rst.getString("email"));
                c.setPassword(rst.getString("password"));
                c.setTel(rst.getString("tel"));
                c.setAdresse(rst.getString("adresse"));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return c;

    }

}
