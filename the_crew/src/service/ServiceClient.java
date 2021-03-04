/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.client;
import java.util.List;
import services.IServiceClient;
import service.ServiceClient;
import utils.Myconnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement ;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

;

/**
 *
 * @author NOUR
 */
public class ServiceClient implements IServiceClient {
    
    Connection cnx;
    
    public ServiceClient() {
        
        cnx = Myconnexion.getInstance().getConnection();
        
    }
    
    @Override
    public void Addclient(client c) {
        
        try {
          

          Statement   stm = Myconnexion.getInstance().getConnection().createStatement();
          assert stm != null;
            String query = "INSERT INTO `client`(`id`, `nom`, `prenom`) VALUES ( NULL ,'"+ c.getNom() + "','" + c.getPrenom() + "')";


            stm.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    /**
     *
     * @return
     */
    @Override
    public List<client> Afficherclient() {
                    Statement stm = null;
            List<client> clients = new ArrayList<>();

        try {
            
            //try {
          stm = Myconnexion.getInstance().getConnection().createStatement();

            String query = "SELECT * FROM `client` ";
            ResultSet rst = stm.executeQuery(query);
            
            while (rst.next()) {
                client c = new client();
                c.setId(rst.getInt("id"));
                c.setNom(rst.getString("nom"));
                c.setPrenom(rst.getString("prenom"));
                clients.add(c);
            }
            /* } catch (SQLException ex) {
            System.out.println(ex.getMessage());*/
            
            //}
             
        } catch (SQLException ex) {
            Logger.getLogger(ServiceClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clients;
    }
                

}
