/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import entities.challenge;
import entities.classement;
import entities.niveau;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import service.ServiceChallenge;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.*; 


/**
 *
 * @author NOUR
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private Tab admin;
    @FXML
    private Tab admin_challenge;
    @FXML
    private TextField titre_challenge;
    @FXML
    private TextField type_challenge;
    @FXML
    private TextField desc_challenge;
    @FXML
    private DatePicker date_debut_challenge;
    @FXML
    private DatePicker date_fin_challenge;
    @FXML
    private TextField etat_challenge;
    @FXML
    private TextField niveau_challenge;
    @FXML
    private Button btn_ajout_challenge;
    @FXML
    private Button btn_modif_challenge;
    @FXML
    private Button btn_aff_challenge;
    @FXML
    private Label imd_challenge;
    @FXML
    private TableView<challenge> liste_challenge;
    @FXML
    private Tab admin_classement;
    @FXML
    private TextField client_classement;
    @FXML
    private TextField niveau_classement;
    @FXML
    private TextField position_classement;
    @FXML
    private TextField nb_pts_classement;
    @FXML
    private TableView<classement> liste_classement;
    @FXML
    private Button btn_aff_classement;
    @FXML
    private Button btn_modif_classement;
    @FXML
    private Tab admin_niveau;
    @FXML
    private TableView<niveau> liste_niveau;
    @FXML
    private TextField titre_niveau;
    @FXML
    private Button btn_ajout_niveau;
    @FXML
    private Button btn_modif_niveau;
    @FXML
    private Button btn_aff_niveau;
    @FXML
    private TextField titre_challenge1;
    @FXML
    private TextField type_challenge1;
    @FXML
    private TextField desc_challenge1;
    @FXML
    private TextField date_debut_challenge1;
    @FXML
    private TextField date_fin_challenge1;
    @FXML
    private TextField etat_challenge1;
    @FXML
    private TextField niveau_challenge1;
    @FXML
    private Button btn_ajout_challenge1;
    @FXML
    private Button btn_modif_challenge1;
    @FXML
    private Button btn_aff_challenge1;
    @FXML
    private Label imd_challenge1;
    @FXML
    private TableView<?> liste_challenge1;
    @FXML
    private Button btn_supp_challenge;
    @FXML
    private Button btn_supp_classement;
    @FXML
    private Button btn_supp_niveau;
    @FXML
    private TableColumn<challenge, String> coltitre_challenge;
    @FXML
    private TableColumn<challenge, String> coltype_challenge;
    @FXML
    private TableColumn<challenge, String> coltdescription_challenge;
    @FXML
    private TableColumn<challenge, Date> coldatedebut_challenge;
    @FXML
    private TableColumn<challenge, Date> coldatefin_challenge;
    @FXML
    private TableColumn<challenge, String> coletat_challenge;
    @FXML
    private TableColumn<challenge, Integer> colniveau_challenge;
    @FXML
    private TableColumn<challenge, Integer> colid_challenge;
    @FXML
    private TextField id_challenge;
    @FXML
    private TableColumn<?, ?> colniveau_classement;
    @FXML
    private TableColumn<?, ?> colposition_classement;
    @FXML
    private TableColumn<?, ?> colclient_classement;
    @FXML
    private TableColumn<?, ?> colnb_pt_classement;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceChallenge sc = new ServiceChallenge();

        ObservableList<challenge> Ochallenges = sc.afficherChallenge();

    }

    @FXML
    private void ajouter_challenge(ActionEvent event) {
        ServiceChallenge sc = new ServiceChallenge();
        challenge c = new challenge();
        c.setTitre(titre_challenge.getText());
        c.setType(type_challenge.getText());
        c.setDescription(desc_challenge.getText());
        c.setImg(imd_challenge.getText());
        java.sql.Date gettedDatePickerDate = java.sql.Date.valueOf(date_debut_challenge.getValue());
        c.setDate_debut(gettedDatePickerDate);
        java.sql.Date gettedDatePickerDate2 = java.sql.Date.valueOf(date_fin_challenge.getValue());
        c.setDate_fin(gettedDatePickerDate2);
        // c.setNb_participants(tfprenom.getText());
        c.setEtat(etat_challenge.getText());
        c.setNiveau(Integer.parseInt(niveau_challenge.getText()));
      
        sc.ajouterChallenge(c);
        afficher_challege();
    }

    @FXML
    private void modifier_challege(ActionEvent event) {
        ServiceChallenge sp = new ServiceChallenge();
        challenge c = new challenge();
       
       	
       //Integer.parseInt(this.id_challenge.getText());
        //c.setTitre(id_challenge.getText());
       c=sp.recup_challenge(Integer.parseInt(id_challenge.getText()));
        c.setTitre(titre_challenge.getText());
        c.setType(type_challenge.getText());
        c.setDescription(desc_challenge.getText());
        c.setImg(imd_challenge.getText());
        java.sql.Date gettedDatePickerDate = java.sql.Date.valueOf(date_debut_challenge.getValue());
        c.setDate_debut(gettedDatePickerDate);
        java.sql.Date gettedDatePickerDate2 = java.sql.Date.valueOf(date_fin_challenge.getValue());
        c.setDate_fin(gettedDatePickerDate2);
        // c.setNb_participants(tfprenom.getText());
        c.setEtat(etat_challenge.getText());
        //c.setNiveau(niveau_challenge.getText());
        sp.modifierChallenge(c);
        afficher_challege();
    }

    @FXML
    private void afficher_challege(ActionEvent event) {

        ServiceChallenge sc = new ServiceChallenge();
        // liste_challenge.SetCollumn(sc.afficherChallenge().toString());

        ObservableList<challenge> Ochallenges = sc.afficherChallenge();
        colid_challenge.setCellValueFactory(new PropertyValueFactory<challenge,Integer>("id"));
        coltitre_challenge.setCellValueFactory(new PropertyValueFactory<challenge,String>("titre"));
        coltype_challenge.setCellValueFactory(new PropertyValueFactory<challenge,String>("type"));
        coltdescription_challenge.setCellValueFactory(new PropertyValueFactory<challenge,String>("description"));

        coldatedebut_challenge.setCellValueFactory(new PropertyValueFactory<challenge,Date>("date_debut"));
        coldatefin_challenge.setCellValueFactory(new PropertyValueFactory<challenge,Date>("date_fin"));
        coletat_challenge.setCellValueFactory(new PropertyValueFactory<challenge,String>("etat"));
        colniveau_challenge.setCellValueFactory(new PropertyValueFactory<challenge,Integer>("niveau"));
        liste_challenge.setItems(Ochallenges);
    }
    
     private void afficher_challege() {

        ServiceChallenge sc = new ServiceChallenge();
        // liste_challenge.SetCollumn(sc.afficherChallenge().toString());

        ObservableList<challenge> Ochallenges = sc.afficherChallenge();
        colid_challenge.setCellValueFactory(new PropertyValueFactory<challenge,Integer>("id"));
        coltitre_challenge.setCellValueFactory(new PropertyValueFactory<challenge,String>("titre"));
        coltype_challenge.setCellValueFactory(new PropertyValueFactory<challenge,String>("type"));
        coltdescription_challenge.setCellValueFactory(new PropertyValueFactory<challenge,String>("description"));

        coldatedebut_challenge.setCellValueFactory(new PropertyValueFactory<challenge,Date>("date_debut"));
        coldatefin_challenge.setCellValueFactory(new PropertyValueFactory<challenge,Date>("date_fin"));
        coletat_challenge.setCellValueFactory(new PropertyValueFactory<challenge,String>("etat"));
        colniveau_challenge.setCellValueFactory(new PropertyValueFactory<challenge,Integer>("niveau"));
        liste_challenge.setItems(Ochallenges);
    }

    @FXML
    private void afficher_classement(ActionEvent event) {
    }

    @FXML
    private void modifier_classement(ActionEvent event) {

    }

    @FXML
    private void ajouter_niveau(ActionEvent event) {
    }

    @FXML
    private void modifier_niveau(ActionEvent event) {
    }

    @FXML
    private void afficher_niveau(ActionEvent event) {
    }

    @FXML
    private void supprimer_challenge(ActionEvent event) {
        
         ServiceChallenge sp = new ServiceChallenge();
        sp.supprimerChallenge(id_challenge.getText());
        afficher_challege();
     

    }

    @FXML
    private void supprimer_classement(ActionEvent event) {
    }

    @FXML
    private void supprimer_niveau(ActionEvent event) {
    }

}
