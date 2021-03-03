/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Admin;
import Services.IserviceAdmin;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import utils.Connexion;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SeifBS
 */
public class ServiceAdmin implements IserviceAdmin {

    Connection cnx;

    public ServiceAdmin() {
        cnx = Connexion.getInstance().getConnection();
    }

    public void ajouterAdmin(Admin a) {

        Statement stm;
        try {
            stm = cnx.createStatement();

            String query = "	INSERT INTO `admin`(`id`, `nom`, `prenom`, `email`, `tel`) VALUES ('" + a.getId() + "','" + a.getNom() + "','" + a.getPrenom() + "','" + a.getEmail() + "','" + a.getTel() + "')";

            stm.executeUpdate(query);

        } catch (SQLException ex) {
            Logger.getLogger(ServiceAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void modifierAdmin(Admin a) {

        Statement stm;
        try {
            stm = cnx.createStatement();

            String query = "UPDATE admin SET id='" + a.getId() + "',nom='" + a.getNom() + "',prenom='" + a.getPrenom() + "',email='" + a.getEmail() + "',tel='" + a.getTel() + "' WHERE id='" + a.getId() + "'";
            stm.executeUpdate(query);

        } catch (SQLException ex) {
            Logger.getLogger(ServiceAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Admin> afficherAdmin() {
        Statement stm = null;
        List<Admin> Admins = new ArrayList<>();

        try {
            stm = cnx.createStatement();
            String query = "SELECT * FROM `admin` ";
            ResultSet rst = stm.executeQuery(query);

            while (rst.next()) {
                Admin a = new Admin();
                a.setId(rst.getString("id"));
                a.setNom(rst.getString("nom"));
                a.setPrenom(rst.getString("prenom"));
                a.setEmail(rst.getString("email"));
                a.setTel(rst.getString("tel"));

                Admins.add(a);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return Admins;

    }

    

    public void supprimerAdmin(String id) {
        Statement stm;
        try {
            stm = cnx.createStatement();

            String query = "DELETE FROM admin  WHERE id='" + id + "'";
            stm.executeUpdate(query);

        } catch (SQLException ex) {
            Logger.getLogger(ServiceAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    


    

}
