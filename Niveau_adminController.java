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
public class Niveau_adminController implements Initializable {

    @FXML
    private TableView<niveau> liste_niveau_admin;
    @FXML
    private TableColumn<niveau, String> coltitre_niveau_admin;
    @FXML
    private TextField titre_niveau_admin;
    @FXML
    private Button btn_ajout_niveau_admin;
    @FXML
    private Button btn_modif_niveau_admin;
    @FXML
    private Button btn_supp_niveau_admin;
    @FXML
    private Button btn_chercher_niveau_admin;
    @FXML
    private Button btn_trier_niveau;
    @FXML
    private TextField modif_niveau;

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
       
        afficher_niveau_admin();
       
    }    

    @FXML
    private void ajouter_niveau_admin(ActionEvent event) {
         ServiceNiveau nv = new ServiceNiveau();
        niveau n = new niveau();
        n.setTitre(titre_niveau_admin.getText());

        nv.ajouterNiveau(n);
        afficher_niveau_admin();
    }

    @FXML
    private void modifier_niveau_admin(ActionEvent event) {
         niveau n = new niveau();
        ServiceNiveau nv = new ServiceNiveau();

        n = nv.recup_niveau(modif_niveau.getText());

       // n.setTitre(titre_niveau_admin.getText());

        nv.modifierNiveau(n,titre_niveau_admin.getText());
         
        afficher_niveau_admin();
    }

     private void afficher_niveau_admin() {
        ServiceNiveau nv = new ServiceNiveau();

        ObservableList<niveau> Oniveaux = nv.afficherNiveau();
        coltitre_niveau_admin.setCellValueFactory(new PropertyValueFactory<>("titre"));

        liste_niveau_admin.setItems(Oniveaux);
    }
    @FXML
    private void supprimer_niveau_admin(ActionEvent event) {
          ServiceNiveau nv = new ServiceNiveau();
        nv.supprimerNiveau(modif_niveau.getText());
        afficher_niveau_admin();
    }

    @FXML
    private void chercher_niveau(ActionEvent event) {
        ServiceNiveau sn = new ServiceNiveau();
         niveau n=new niveau ();
        ObservableList<niveau> Oniveau = sn.RechercherNiveau(titre_niveau_admin.getText());
       // colid_niveau_admin.setCellValueFactory(new PropertyValueFactory<>("id"));
        coltitre_niveau_admin.setCellValueFactory(new PropertyValueFactory<>("titre"));
        

        liste_niveau_admin.setItems(Oniveau);
    }

    @FXML
    private void trier_niveau(ActionEvent event) {
          ServiceNiveau nv = new ServiceNiveau();

        ObservableList<niveau> Oniveaux = nv.trierNiveau();
       // colid_niveau_admin.setCellValueFactory(new PropertyValueFactory<>("id"));
        coltitre_niveau_admin.setCellValueFactory(new PropertyValueFactory<>("titre"));

        liste_niveau_admin.setItems(Oniveaux);
    }
    
}
