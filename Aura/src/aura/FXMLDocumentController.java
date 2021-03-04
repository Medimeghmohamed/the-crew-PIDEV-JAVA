/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aura;

import Entities.Contact;
import Service.ServiceContact;
import Utils.Maconnexion;
import com.mysql.jdbc.Statement;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Nour Dekhil
 */
public class FXMLDocumentController implements Initializable {

    private Label label;
    @FXML
    private TextArea tfMsgContact;
    @FXML
    private Button btnValider;
    @FXML
    private TextField tfClient;
    @FXML
    private TextField tfTypec;
    @FXML
    private TextField tfCoach;
    @FXML
    private Button btnAfficher;
    @FXML
    private TextField tfDate;
    @FXML
    private TableView<Contact> tableAfficher;
    @FXML
    private TableColumn<Contact, Integer> Id;
    @FXML
    private TableColumn<Contact, String> Type;
    @FXML
    private TableColumn<Contact, String> Id_Client;
    @FXML
    private TableColumn<Contact, String> Id_Coach;
    @FXML
    private TableColumn<Contact, String> Champ;
    @FXML
    private TableColumn<Contact, String> Date;

    public ObservableList<Contact> data = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Contact, String> etat;
    @FXML
    private Button btnSupprimer;
    @FXML
    private TextField tfID;
    @FXML
    private TextField tfETAT;
    @FXML
    private Label ID;
    @FXML
    private Label ETAT;
    @FXML
    private TextField tfSupp;
    @FXML
    private Button btnModifier;

    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void AjouterContact(ActionEvent event) {
        ServiceContact sc = new ServiceContact();
        Contact c = new Contact();

        c.setType(tfTypec.getText());
        c.setId_client(tfClient.getText());
        c.setId_coach(tfCoach.getText());
        c.setChamp(tfMsgContact.getText());
        c.setDate(tfDate.getText());

        sc.ajouterContact(c);

    }

    @FXML
    private void idclient(ActionEvent event) {
    }

    @FXML
    private void AfficherContact(ActionEvent event) {

        ServiceContact sc = new ServiceContact();
        // liste_challenge.SetCollumn(sc.afficherChallenge().toString());

        ObservableList<Contact> OContact = sc.AfficherContact();
        Id.setCellValueFactory(new PropertyValueFactory<>("id"));
        Type.setCellValueFactory(new PropertyValueFactory<>("type"));
        Id_Client.setCellValueFactory(new PropertyValueFactory<>("id_client"));
        Id_Coach.setCellValueFactory(new PropertyValueFactory<>("id_coach"));

        Champ.setCellValueFactory(new PropertyValueFactory<>("champ"));
        Date.setCellValueFactory(new PropertyValueFactory<>("date"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat"));

        tableAfficher.setItems(OContact);

    }

    @FXML
    private void SupprimerContact(ActionEvent event) {

        ServiceContact sc = new ServiceContact();
        sc.SupprimerContact(tfSupp.getText());
        AfficherContact(event);

    }

    @FXML
    private void ModifierContact(ActionEvent event) {
        
        ServiceContact sc = new ServiceContact();
        String id=tfID.getText();
        String etat=tfETAT.getText();
        
        sc.ModifierContact(id,etat); 
        AfficherContact(event);
        
        
        
    }

    
}
