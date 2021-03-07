/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Client;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SeifBS
 */
public class ServiceClient {
    Connection cnx;

    
    
    public void ajouterClient(Client a) {
        String id;

        Statement stm;
        try {
            stm = cnx.createStatement();
            id="A"+a.getId();
            

            String query = "	INSERT INTO `Client`(`id`, `nom`, `prenom`, `email`, `password`, `tel`) VALUES ('" + id + "','" + a.getNom() + "','" + a.getPrenom() + "','" + a.getEmail() + "','" + a.getPassword() + "','" + a.getTel() + "')";

            stm.executeUpdate(query);

        } catch (SQLException ex) {
            Logger.getLogger(ServiceClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void modifierClient(Client a) {

        Statement stm;
        try {
            stm = cnx.createStatement();

            String query = "UPDATE user SET id='" + a.getId() + "',nom='" + a.getNom() + "',prenom='" + a.getPrenom() + "',email='" + a.getEmail() + "',password='" + a.getPassword() + "',tel='" + a.getTel() + "' WHERE id='" + a.getId() + "'";
            stm.executeUpdate(query);

        } catch (SQLException ex) {
            Logger.getLogger(ServiceClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Client> afficherClient() {
        Statement stm = null;
        List<Client> Clients = new ArrayList<>();
       

        try {
            stm = cnx.createStatement();
            String query = "SELECT * FROM `Client` ";
            ResultSet rst = stm.executeQuery(query);

            while (rst.next()) {
                Client a = new Client();
                a.setId(rst.getString("id"));
                a.setNom(rst.getString("nom"));
                a.setPrenom(rst.getString("prenom"));
                a.setEmail(rst.getString("email"));
                a.setPassword(rst.getString("password"));
                a.setTel(rst.getString("tel"));

                Clients.add(a);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return Clients;

    }
    
}
