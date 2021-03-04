/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aura;

import Entities.Contact;
import Entities.Entraide;
import Service.ServiceContact;
import Service.ServiceEntraide;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;

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
    private TextField tfCoach;
    @FXML
    private Button btnAfficher;
    @FXML
    private DatePicker tfDate;
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
    @FXML
    private Tab tfIDe;
    @FXML
    private TextField tf_id;
    @FXML
    private TextField tf_quest;
    @FXML
    private DatePicker tf_date;
    @FXML
    private Button btnEnvoyer;
    @FXML
    private TableView<Entraide> tableEntraide;
    @FXML
    private TableColumn<Entraide, Integer> tb_idclient;
    @FXML
    private TableColumn<Entraide, String> tb_cat;
    @FXML
    private TableColumn<Entraide, String> tb_quest;
    @FXML
    private TableColumn<Entraide, String> tb_date;
    @FXML
    private Button btnTrier;
    @FXML
    private ComboBox<String> combo_cat;
    @FXML
    private Button btnAfficher2;
    @FXML
    private Button btnModifier2;
    @FXML
    private Button btnSupprimer2;
    @FXML
    private TextField tf_IDc;
    @FXML
    private TextField ID_m;
    @FXML
    private TextField Q_m;
    @FXML
    private TextField rech_client;
    @FXML
    private Button btnRechercher;
    @FXML
    private ComboBox<String> combo_obj;
    

    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> listcat = FXCollections.observableArrayList("Psychologie", "Santé", "bien-etre");
        combo_cat.setItems(listcat);
        
        ObservableList<String> listobj = FXCollections.observableArrayList("Avis", "Reclamation", "Contact", "Autre");
        combo_obj.setItems(listobj);
        
    }

    @FXML
    private void AjouterContact(ActionEvent event) {
        ServiceContact sc = new ServiceContact();
        Contact c = new Contact();

        c.setType(combo_obj.getValue());
        c.setId_client(tfClient.getText());
        c.setId_coach(tfCoach.getText());
        c.setChamp(tfMsgContact.getText());
        java.sql.Date gettedDatePickerDate = java.sql.Date.valueOf(tfDate.getValue());
        c.setDate(gettedDatePickerDate);

        sc.AjouterContact(c);

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
        String id1 = tfID.getText();
        String etat1 = tfETAT.getText();

        sc.ModifierContact(id1, etat1);
        AfficherContact(event);

    }

    @FXML
    private void AjouterEntraide(ActionEvent event) {

        ServiceEntraide sc = new ServiceEntraide();
        Entraide e = new Entraide();

        e.setId_client(tf_id.getText());
        e.setCategorie(combo_cat.getValue());
        e.setQuestion(tf_quest.getText());
        java.sql.Date gettedDatePickerDate = java.sql.Date.valueOf(tf_date.getValue());
        e.setDate(gettedDatePickerDate);

        sc.AjouterEntraide(e);
    }

   

    @FXML
    private void AfficherEntraide(ActionEvent event) {
        ServiceEntraide se = new ServiceEntraide();
        ObservableList<Entraide> OEntraide = se.AfficherEntraide();
        tb_idclient.setCellValueFactory(new PropertyValueFactory<>("id_client"));
        tb_cat.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        tb_quest.setCellValueFactory(new PropertyValueFactory<>("question"));
        tb_date.setCellValueFactory(new PropertyValueFactory<>("date"));

        tableEntraide.setItems(OEntraide);
    }

    @FXML
    private void SupprimerEntraide(ActionEvent event) {
        ServiceEntraide sc = new ServiceEntraide();
        sc.SupprimerEntraide(tf_IDc.getText());
        AfficherEntraide(event);
    }

    @FXML
    private void ModifierEntraide(ActionEvent event) {

        ServiceEntraide sc = new ServiceEntraide();
        String idd = ID_m.getText();
        String question1= Q_m.getText();

        sc.ModifierEntraide(idd, question1);
        AfficherEntraide(event);
        
        
        
        
    }

    @FXML
    private void TrierEntraide(ActionEvent event) {
         ServiceEntraide se = new ServiceEntraide();
        ObservableList<Entraide> OEntraide = se.TrierEntraide();
        tb_idclient.setCellValueFactory(new PropertyValueFactory<>("id_client"));
        tb_cat.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        tb_quest.setCellValueFactory(new PropertyValueFactory<>("question"));
        tb_date.setCellValueFactory(new PropertyValueFactory<>("date"));

        tableEntraide.setItems(OEntraide);
    }

    @FXML
    private void RechercherEntraide(ActionEvent event) {
         ServiceEntraide se = new ServiceEntraide();
         Entraide e=new Entraide ();
        ObservableList<Entraide> OEntraide = se.Rechercher(e.getId_client());
        tb_idclient.setCellValueFactory(new PropertyValueFactory<>("id_client"));
        tb_cat.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        tb_quest.setCellValueFactory(new PropertyValueFactory<>("question"));
        tb_date.setCellValueFactory(new PropertyValueFactory<>("date"));

        tableEntraide.setItems(OEntraide);
    }

    

}
