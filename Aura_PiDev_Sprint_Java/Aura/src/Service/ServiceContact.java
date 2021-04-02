/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Contact;
import Interfaces.IServiceContact;
import java.sql.Connection;
import utils.Connexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;

/**
 *
 * @author Nour Dekhil
 */
public class ServiceContact implements IServiceContact {

    Connection cnx;

    public ServiceContact() {
        cnx = Connexion.getInstance().getConnection();

    }

    @Override
    public void AjouterContact(Contact c) {

        try {

            Statement stm = cnx.createStatement();

            String query = "INSERT INTO `contact`( `id`,`type`, `id_user`,`email`, `champ`, `date`,`etat` )"
                    + "VALUES ('"
                    + c.getId() + "','"
                    + c.getType() + "','"
                    + c.getId_user() + "','"
                    + c.getEmail() + "','"
                    + c.getChamp() + "','"
                    + c.getDate() + "','"
                    + "non vérifié" + "')";

            stm.executeUpdate(query);
            System.out.println("ajout contact avec succes");
        } catch (SQLException ex) {
            System.out.println("erreur ajout contact");
            System.out.println(ex);
        }

    }

    @Override
    public ObservableList<Contact> AfficherContact() {
        ObservableList<Contact> contacts = FXCollections.observableArrayList();
        try {
            Statement stm = cnx.createStatement();
            String query = "select * from `contact`";

            ResultSet rst = stm.executeQuery(query);

            while (rst.next()) {
                Contact c = new Contact();

                c.setEmail(rst.getString(4));
                c.setType(rst.getString(2));
                c.setId_user(rst.getString(3));
                
               //email
                c.setChamp(rst.getString(5));
                c.setDate(rst.getDate(6));
                c.setEtat(rst.getString(7));
                
               // c.setEtat(rst.getString(7));

                contacts.add(c);

            }

        } catch (SQLException ex) {
            System.out.println("erreur affichage contact ");
            System.out.println(ex);
        }

        return contacts;

    }

    @Override
    public void SupprimerContact(int id) {
      
        try {
            Statement st = cnx.createStatement();
            String query = "DELETE FROM contact WHERE id =id ";
            st.executeUpdate(query);
            System.out.println("suppression avec succes");
        } catch (SQLException ex) {
            System.out.println("erreur supprimer challenge");
            System.out.println(ex);
        }
    }

    @Override
    public void ModifierContact(String mail) {
        try {
            Statement stm = cnx.createStatement();

            String query = "UPDATE  contact SET etat=   'verifie'  WHERE email ='" + mail + "'";

            stm.executeUpdate(query);
            System.out.println(" succes");
        } catch (SQLException ex) {
            System.out.println("erreur ");
            System.out.println(ex);
        }
    }

}
