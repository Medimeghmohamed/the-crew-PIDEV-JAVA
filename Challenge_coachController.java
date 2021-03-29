/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import entities.challenge;
import entities.classement;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import service.ServiceChallenge;
import java.util.Date;
import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import service.ServiceClassement;
import service.ServiceNiveau;

/**
 * FXML Controller class
 *
 * @author NOUR
 */
public class Challenge_coachController implements Initializable {

    @FXML
    private TextField titre_challenge;
    @FXML
    private TextField desc_challenge;
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
    private TableColumn<challenge, Integer> colniveau_challenge;
    @FXML
    private TableColumn<challenge, Integer> colnb_participants_challenge;
    @FXML
    private Button btn_supp_challenge;
    @FXML
    private DatePicker date_debut_challenge;
    @FXML
    private DatePicker date_fin_challenge;
    @FXML
    private Button btn_chercher_challenge_coach;
    @FXML
    private TextField chercher_titre_challenge_coach;
    @FXML
    private Button btn_atri_challenge_admin;
    @FXML
    private Button btn_aff_challenge_valide;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         ServiceChallenge sc = new ServiceChallenge();
        ServiceClassement cl = new ServiceClassement();

        ServiceNiveau n = new ServiceNiveau();

        ObservableList<challenge> Ochallenges = sc.afficherChallenge();
        ObservableList<classement> Oclassements = cl.afficherClassement();
        afficher_challege();
       
    }    

    @FXML
    private void ajouter_challenge(ActionEvent event) {
        
        
          ServiceChallenge sc = new ServiceChallenge();
        challenge c = new challenge();
        c.setTitre(titre_challenge.getText());
        c.setDescription(desc_challenge.getText());
        c.setImg(imd_challenge.getText());
        java.sql.Date gettedDatePickerDate = java.sql.Date.valueOf(date_debut_challenge.getValue());
        c.setDate_debut(gettedDatePickerDate);
        java.sql.Date gettedDatePickerDate2 = java.sql.Date.valueOf(date_fin_challenge.getValue());
        c.setDate_fin(gettedDatePickerDate2);
      
        c.setNiveau(Integer.parseInt(niveau_challenge.getText()));

        sc.ajouterChallenge(c);
        afficher_challege();
       
        
    }

    @FXML
    private void modifier_challege(ActionEvent event) {
         ServiceChallenge sp = new ServiceChallenge();
        challenge c = new challenge();

        c = sp.recup_challenge_titre(chercher_titre_challenge_coach.getText());
        c.setTitre(titre_challenge.getText());
        c.setDescription(desc_challenge.getText());
        c.setImg(imd_challenge.getText());
        java.sql.Date gettedDatePickerDate = java.sql.Date.valueOf(date_debut_challenge.getValue());
        c.setDate_debut(gettedDatePickerDate);
        java.sql.Date gettedDatePickerDate2 = java.sql.Date.valueOf(date_fin_challenge.getValue());
        c.setDate_fin(gettedDatePickerDate2);
     
        c.setNiveau(Integer.parseInt(niveau_challenge.getText()));
        sp.modifierChallenge(c);
        afficher_challege();
    }

    @FXML
    private void afficher_challege(ActionEvent event) {
          ServiceChallenge sc = new ServiceChallenge();

        ObservableList<challenge> Ochallenges = sc.afficherChallenge();
        coltitre_challenge.setCellValueFactory(new PropertyValueFactory<challenge, String>("titre"));
        coltype_challenge.setCellValueFactory(new PropertyValueFactory<challenge, String>("type"));
        coltdescription_challenge.setCellValueFactory(new PropertyValueFactory<challenge, String>("description"));

        coldatedebut_challenge.setCellValueFactory(new PropertyValueFactory<challenge, Date>("date_debut"));
        coldatefin_challenge.setCellValueFactory(new PropertyValueFactory<challenge, Date>("date_fin"));
        colniveau_challenge.setCellValueFactory(new PropertyValueFactory<challenge, Integer>("niveau"));
        liste_challenge.setItems(Ochallenges);
    }
    
    
     private void afficher_challege() {

        ServiceChallenge sc = new ServiceChallenge();
        // liste_challenge.SetCollumn(sc.afficherChallenge().toString());

        ObservableList<challenge> Ochallenges = sc.afficherChallenge();
       // colid_challenge.setCellValueFactory(new PropertyValueFactory<challenge, Integer>("id"));
        coltitre_challenge.setCellValueFactory(new PropertyValueFactory<challenge, String>("titre"));
        coltype_challenge.setCellValueFactory(new PropertyValueFactory<challenge, String>("type"));
        coltdescription_challenge.setCellValueFactory(new PropertyValueFactory<challenge, String>("description"));

        coldatedebut_challenge.setCellValueFactory(new PropertyValueFactory<challenge, Date>("date_debut"));
        coldatefin_challenge.setCellValueFactory(new PropertyValueFactory<challenge, Date>("date_fin"));
       // coletat_challenge.setCellValueFactory(new PropertyValueFactory<>("etat"));
        colniveau_challenge.setCellValueFactory(new PropertyValueFactory<challenge, Integer>("niveau"));
        colnb_participants_challenge.setCellValueFactory(new PropertyValueFactory<>("nb_participants"));
        liste_challenge.setItems(Ochallenges);
    }

    @FXML
    private void supprimer_challenge(ActionEvent event) {
          ServiceChallenge sp = new ServiceChallenge();
        sp.supprimerChallenge(chercher_titre_challenge_coach.getText());
        afficher_challege();
    }

    @FXML
    private void chercher_challenge(ActionEvent event) {
          ServiceChallenge sn = new ServiceChallenge();
         challenge n=new challenge ();
        ObservableList<challenge> Ochallenges = sn.RechercherChallenge(chercher_titre_challenge_coach.getText());
       //colid_challenge.setCellValueFactory(new PropertyValueFactory<>("id"));
        coltitre_challenge.setCellValueFactory(new PropertyValueFactory<>("titre"));
        coltype_challenge.setCellValueFactory(new PropertyValueFactory<>("type"));
        coltdescription_challenge.setCellValueFactory(new PropertyValueFactory<>("description"));

        coldatedebut_challenge.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        coldatefin_challenge.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
       // coletat_challenge.setCellValueFactory(new PropertyValueFactory<>("etat"));
        colniveau_challenge.setCellValueFactory(new PropertyValueFactory<>("niveau"));
        liste_challenge.setItems(Ochallenges);
    }

    @FXML
    private void trierChallenge(ActionEvent event) {
        
          ServiceChallenge sc = new ServiceChallenge();

        ObservableList<challenge> Ochallenges = sc.trierChallenge();
        coltitre_challenge.setCellValueFactory(new PropertyValueFactory<challenge, String>("titre"));
        coltype_challenge.setCellValueFactory(new PropertyValueFactory<challenge, String>("type"));
        coltdescription_challenge.setCellValueFactory(new PropertyValueFactory<challenge, String>("description"));

        coldatedebut_challenge.setCellValueFactory(new PropertyValueFactory<challenge, Date>("date_debut"));
        coldatefin_challenge.setCellValueFactory(new PropertyValueFactory<challenge, Date>("date_fin"));
        colniveau_challenge.setCellValueFactory(new PropertyValueFactory<challenge, Integer>("niveau"));
        liste_challenge.setItems(Ochallenges);
    }

    @FXML
    private void afficher_challenge_valide(ActionEvent event) {
         ServiceChallenge sc = new ServiceChallenge();

        ObservableList<challenge> Ochallenges = sc.afficherChallenge_valide();
        coltitre_challenge.setCellValueFactory(new PropertyValueFactory<challenge, String>("titre"));
        coltype_challenge.setCellValueFactory(new PropertyValueFactory<challenge, String>("type"));
        coltdescription_challenge.setCellValueFactory(new PropertyValueFactory<challenge, String>("description"));

        coldatedebut_challenge.setCellValueFactory(new PropertyValueFactory<challenge, Date>("date_debut"));
        coldatefin_challenge.setCellValueFactory(new PropertyValueFactory<challenge, Date>("date_fin"));
        colniveau_challenge.setCellValueFactory(new PropertyValueFactory<challenge, Integer>("niveau"));
         colnb_participants_challenge.setCellValueFactory(new PropertyValueFactory<>("nb_participants"));
        liste_challenge.setItems(Ochallenges);
    }
    
}
