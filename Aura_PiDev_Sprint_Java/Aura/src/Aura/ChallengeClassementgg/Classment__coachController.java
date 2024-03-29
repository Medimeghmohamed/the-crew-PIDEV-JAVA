/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aura.ChallengeClassementgg;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import Entities.challenge;
import Entities.classement;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import Service.ServiceChallenge;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import Service.ServiceClassement;
import Service.ServiceNiveau;
import utils.Connexion;




/**
 * FXML Controller class
 *
 * @author NOUR
 */
public class Classment__coachController implements Initializable {

    @FXML
    private TableView<classement> liste_classement_coach;
    @FXML
    private TableColumn<classement, Integer> colniveau_classement;
    @FXML
    private TableColumn<classement, Integer> colposition_classement;
    @FXML
    private TableColumn<classement, String> colclient_classement;
    @FXML
    private TableColumn<classement, Integer> colnb_pt_classement;
    @FXML
    private TextField chercher_position_coach;
    @FXML
    private Button btn_chercher_classement_coach;
    @FXML
    private Button btn_affich_classement_coach;

    /**
     * Initializes the controller class.
     */
    public String id_user="";
    public void initializeFxml(String id){
        System.out.println(id);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ServiceChallenge sc = new ServiceChallenge();
        ServiceClassement cl = new ServiceClassement();

        ServiceNiveau n = new ServiceNiveau();

        ObservableList<challenge> Ochallenges = sc.afficherChallenge();
        ObservableList<classement> Oclassements = cl.afficherClassement();
      
        afficher_classement_coach();
        // TODO
    }    

    @FXML
    private void chercherClassement_coach(ActionEvent event) {
        
        ServiceClassement sn = new ServiceClassement();
         classement n=new classement ();
        ObservableList<classement> Oclassements = sn.RechercherClassement(chercher_position_coach.getText());
        colposition_classement.setCellValueFactory(new PropertyValueFactory<>("niveau"));
        colposition_classement.setCellValueFactory(new PropertyValueFactory<>("position"));
        colclient_classement.setCellValueFactory(new PropertyValueFactory<>("client"));

        colnb_pt_classement.setCellValueFactory(new PropertyValueFactory<>("nb_points"));

        liste_classement_coach.setItems(Oclassements);
    }

    @FXML
    private void afficher_classement_coach(ActionEvent event) {
         ServiceClassement cl = new ServiceClassement();

        ObservableList<classement> Oclassements = cl.afficherClassement();
        colniveau_classement.setCellValueFactory(new PropertyValueFactory<>("niveau"));
        colposition_classement.setCellValueFactory(new PropertyValueFactory<>("position"));
        colclient_classement.setCellValueFactory(new PropertyValueFactory<>("client"));

        colnb_pt_classement.setCellValueFactory(new PropertyValueFactory<>("nb_points"));

        liste_classement_coach.setItems(Oclassements);
    }
    private void afficher_classement_coach() {
         ServiceClassement cl = new ServiceClassement();

        ObservableList<classement> Oclassements = cl.afficherClassement();
        colniveau_classement.setCellValueFactory(new PropertyValueFactory<>("niveau"));
        colposition_classement.setCellValueFactory(new PropertyValueFactory<>("position"));
        colclient_classement.setCellValueFactory(new PropertyValueFactory<>("client"));

        colnb_pt_classement.setCellValueFactory(new PropertyValueFactory<>("nb_points"));

        liste_classement_coach.setItems(Oclassements);
    }
    
//      public void trierClassement() {
//         ServiceClassement cl = new ServiceClassement();
//
//        ObservableList<classement> Oclassements = cl.trierClassement();
//       
//        colniveau_classement.setCellValueFactory(new PropertyValueFactory<>("niveau"));
//        colposition_classement.setCellValueFactory(new PropertyValueFactory<>("position"));
//        colclient_classement.setCellValueFactory(new PropertyValueFactory<>("client"));
//
//        colnb_pt_classement.setCellValueFactory(new PropertyValueFactory<>("nb_points"));
//
//        liste_classement_coach.setItems(Oclassements);
//    }
    
    
}
