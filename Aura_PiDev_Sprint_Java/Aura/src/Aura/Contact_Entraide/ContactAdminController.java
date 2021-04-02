/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aura.Contact_Entraide;

import Entities.Client;
import Entities.Contact;
import Service.ServiceClient;
import Service.ServiceContact;
import Service.SendMail;
import utils.Connexion;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Nour Dekhil
 */
public class ContactAdminController implements Initializable {

    @FXML
    private TableView<Contact> tableAfficher;
    @FXML
    private TableColumn<Contact, Integer> Id;
    @FXML
    private TableColumn<Contact, String> Type;
    @FXML
    private TableColumn<Contact, String> Champ;
    @FXML
    private TableColumn<Contact, String> Date;
    @FXML
    private TableColumn<Contact, String> etat;
    @FXML
    private Button btnSupprimer;
    @FXML
    private Button btnModifier;

    /**
     * Initializes the controller class.
     */
    public String id_user="";
    public void initializeFxml(String id){
        System.out.println(id_user);
    
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AfficherContact();
        
        
        
    }  
    
        private void AfficherContact() {

        ServiceContact sc = new ServiceContact();

        ObservableList<Contact> OContact = sc.AfficherContact();
        Id.setCellValueFactory(new PropertyValueFactory<>("email"));
        Type.setCellValueFactory(new PropertyValueFactory<>("type"));
        Champ.setCellValueFactory(new PropertyValueFactory<>("champ"));
        Date.setCellValueFactory(new PropertyValueFactory<>("date"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat"));

        tableAfficher.setItems(OContact);

    }

    @FXML
    private void getSelectedAdmin(MouseEvent event) {
         int index = tableAfficher.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
    }

    @FXML
    private void SupprimerContact(MouseEvent event) {
        ServiceContact sc = new ServiceContact();
        Contact c = tableAfficher.getSelectionModel().getSelectedItem();
        sc.SupprimerContact(c.getId());
        tableAfficher.getItems().removeAll(tableAfficher.getSelectionModel().getSelectedItem());

       // Connection cnx = Connexion.getInstance().getConnexion();
       // String sql = "DELETE FROM contact WHERE id = ";
        try {
            JOptionPane.showMessageDialog(null, "voulez-vous vraiment supprimer cette ligne?");
            AfficherContact();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);

        }
    }

    @FXML
    private void ModifierContact(ActionEvent event) {
        ServiceContact sc = new ServiceContact();
        Contact c = tableAfficher.getSelectionModel().getSelectedItem();
       ServiceClient sc1= new ServiceClient();
        Client c1;
        sc.ModifierContact(c.getEmail());
        AfficherContact();
        
        SendMail sm= new SendMail();        
        sm.envoyerMail(c.getEmail(), "Aura", "On a bien recu votre mail");
        
        

    }

    @FXML
    private void getAddView4(MouseEvent event) {
           try {
            Parent parent = FXMLLoader.load(getClass().getResource("pieChart.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
            //AfficherEntraide();
        } catch (IOException ex) {
 System.out.println(ex);        }
    }

    
}
