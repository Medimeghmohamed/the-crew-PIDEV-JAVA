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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import service.ServiceChallenge;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import service.ServiceClassement;
import service.ServiceNiveau;


/**
 * FXML Controller class
 *
 * @author NOUR
 */
public class Classement_clientController implements Initializable {

    @FXML
    private TableView<classement> liste_classement_client;
    @FXML
    private TableColumn<classement, Integer> colniveau_classement1;
    @FXML
    private TableColumn<classement, Integer> colposition_classement1;
    @FXML
    private TableColumn<classement, String> colclient_classement1;
    @FXML
    private TableColumn<classement, Integer> colnb_pt_classement1;
    @FXML
    private Button btn_chercher_classement_client;
    @FXML
    private TextField chercher_position_client;
    @FXML
    private Button afficher_classement_client;

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
     
        afficher_classement_client();
     
        // TODO
    }    

    @FXML
    private void chercherClassement_client(ActionEvent event) {
        
        ServiceClassement cl = new ServiceClassement();

        ObservableList<classement> Oclassements = cl.afficherClassement_client();
        colniveau_classement1.setCellValueFactory(new PropertyValueFactory<>("niveau"));
        colposition_classement1.setCellValueFactory(new PropertyValueFactory<>("position"));
        colclient_classement1.setCellValueFactory(new PropertyValueFactory<>("client"));

        colnb_pt_classement1.setCellValueFactory(new PropertyValueFactory<>("nb_points"));

        liste_classement_client.setItems(Oclassements);
    }

     
    @FXML
    private void afficher_classement_client(ActionEvent event) {
        
         ServiceClassement cl = new ServiceClassement();

        ObservableList<classement> Oclassements = cl.afficherClassement_client();
        colniveau_classement1.setCellValueFactory(new PropertyValueFactory<>("niveau"));
        colposition_classement1.setCellValueFactory(new PropertyValueFactory<>("position"));
        colclient_classement1.setCellValueFactory(new PropertyValueFactory<>("client"));

        colnb_pt_classement1.setCellValueFactory(new PropertyValueFactory<>("nb_points"));

        liste_classement_client.setItems(Oclassements);
    }
     private void afficher_classement_client() {
        
         ServiceClassement cl = new ServiceClassement();

        ObservableList<classement> Oclassements = cl.afficherClassement_client();
        colniveau_classement1.setCellValueFactory(new PropertyValueFactory<>("niveau"));
        colposition_classement1.setCellValueFactory(new PropertyValueFactory<>("position"));
        colclient_classement1.setCellValueFactory(new PropertyValueFactory<>("client"));

        colnb_pt_classement1.setCellValueFactory(new PropertyValueFactory<>("nb_points"));

        liste_classement_client.setItems(Oclassements);
    }
    
}
