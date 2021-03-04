/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Contact;
import Services.IServiceContact;
import java.sql.Connection;
import Utils.Maconnexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Nour Dekhil
 */
public class ServiceContact implements IServiceContact {

    Connection cnx;

    public ServiceContact() {
        cnx = Maconnexion.getInstance().getCnx();

    }

    @Override
    public void AjouterContact(Contact c) {

        try {

            Statement stm = cnx.createStatement();
            
            
            
            
            String query = "INSERT INTO `contact`( `id`,`type`, `id_client`, `id_coach`, `champ`, `date`,`etat`)"
                    + "VALUES ('"
                    + c.getId() + "','"
                    + c.getType() + "','"
                    + c.getId_client() + "','"
                    + c.getId_coach() + "','"
                    + c.getChamp() + "','"
                    + c.getDate() + "','"
                    + "non verifié" + "')";

            stm.executeUpdate(query);
            System.out.println("ajout avec succes");
        } catch (SQLException ex) {
            System.out.println("erreur ajout");
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

                c.setId(rst.getInt(1));
                c.setType(rst.getString(2));
                c.setId_client(rst.getString(3));
                c.setId_coach(rst.getString(4));
                c.setChamp(rst.getString(5));
                c.setDate(rst.getDate(6));
                c.setEtat(rst.getString(7));

                contacts.add(c);

            }

        } catch (SQLException ex) {
            System.out.println("erreur affichage ");
            System.out.println(ex);
        }

        return contacts;

    }

    @Override
    public void SupprimerContact(String id) {
        int Id = Integer.parseInt(id);
        try {
            Statement st = cnx.createStatement();
            String query = "DELETE FROM contact WHERE id = " + id + "";
            st.executeUpdate(query);
            System.out.println("suppression avec succes");
        } catch (SQLException ex) {
            System.out.println("erreur supprimer challenge");
            System.out.println(ex);
        }
    }

   
    @Override
    public void ModifierContact(String id, String etat) {
        try {
            Statement stm = cnx.createStatement();

         
            String query = "UPDATE  contact SET etat= '" + etat + "' WHERE id ='" + id + "'";

            stm.executeUpdate(query);
            System.out.println(" succes");
        } catch (SQLException ex) {
            System.out.println("erreur ");
            System.out.println(ex);
        }
    }

}
