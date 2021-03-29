/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;



import entities.challenge;
import entities.classement;
import entities.ligne_challenge;
import entities.participation_challenge;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import service.ServiceChallenge;
import java.util.Date;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import service.ServiceClassement;
import service.ServiceLigneChallenge;
import service.ServiceNiveau;


/**
 * FXML Controller class
 *
 * @author NOUR
 */
public class Challenge_clientController implements Initializable {

    @FXML
    private TableView<challenge> liste_challenge_client;
    @FXML
    private TableColumn<challenge, Integer> colniv_challenge_client;
    @FXML
    private TableColumn<challenge, String> coltitre_challenge_client;
    @FXML
    private TableColumn<challenge, String> coldescription_challenge_client;
    @FXML
    private TableColumn<challenge, Date> coldete_debut_challenge_client;
    @FXML
    private TableColumn<challenge, Date> coldete_fin_challenge_client;
    @FXML
    private TextField titre_challenge_client;
    @FXML
    private Button rejoindre_challenge;
    @FXML
    private Button btn_chercher_challenge_client;
    @FXML
    private Button btn_aff_challenge_client;
    @FXML
    private TextField idclient_challenge_client;
    @FXML
    private Button joined_challenge;
    @FXML
    private TableView<participation_challenge> tab_etat_client;
    @FXML
    private TableColumn<?, ?> coletat_challenge_client;

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
      
        
        afficher_challege_client();
        // TODO
    }    

    @FXML
    private void rejoidreChallenge(ActionEvent event) {
         ServiceChallenge sc = new ServiceChallenge();
        ServiceLigneChallenge scl =new ServiceLigneChallenge();
        ligne_challenge lc= new ligne_challenge();
        challenge c=new challenge ();
        //int id=Integer.parseInt(idclient_challenge_client.getText());
        String tr;
        
        c=sc.recup_challenge_titre(titre_challenge_client.getText());
        //ajout
       
        sc.rejoindre_challenge(c,idclient_challenge_client.getText());
        lc=scl.recup_LigneChallenge(c);
        
        afficher_challege_client();
        
    }

    @FXML
    private void chercherChallenge_client(ActionEvent event) {
          ServiceChallenge sn = new ServiceChallenge();
         challenge n=new challenge ();
        ObservableList<challenge> Ochallenge = sn.RechercherChallenge(titre_challenge_client.getText());
        colniv_challenge_client.setCellValueFactory(new PropertyValueFactory<>("niveau"));
        coltitre_challenge_client.setCellValueFactory(new PropertyValueFactory<>("titre"));
        coldescription_challenge_client.setCellValueFactory(new PropertyValueFactory<>("description"));
        coldete_debut_challenge_client.setCellValueFactory(new PropertyValueFactory<>("date_debut"));

        coldete_fin_challenge_client.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
//        coletat_challenge.setCellValueFactory(new PropertyValueFactory<>("etat"));

         liste_challenge_client.setItems(Ochallenge);
    }

    @FXML
    private void afficher_challege_client(ActionEvent event) {
         ServiceChallenge sc = new ServiceChallenge();
    
           ObservableList<participation_challenge> Ochallenges2 = sc.recup_participation(idclient_challenge_client.getText());
        // liste_challenge.SetCollumn(sc.afficherChallenge().toString());
         String etat=null;
        challenge c = new challenge();
        ObservableList<challenge> Ochallenges = sc.afficherChallenge_client();
        coltitre_challenge_client.setCellValueFactory(new PropertyValueFactory<challenge, String>("titre"));
        coldescription_challenge_client.setCellValueFactory(new PropertyValueFactory<challenge, String>("description"));

        coldete_debut_challenge_client.setCellValueFactory(new PropertyValueFactory<challenge, Date>("date_debut"));
        coldete_fin_challenge_client.setCellValueFactory(new PropertyValueFactory<challenge, Date>("date_fin"));
        coletat_challenge_client.setCellValueFactory(new PropertyValueFactory<>("etat"));
        colniv_challenge_client.setCellValueFactory(new PropertyValueFactory< >("niveau"));
        liste_challenge_client.setItems(Ochallenges);
        tab_etat_client.setItems(Ochallenges2);
    }
     public void afficher_challege_client() {
         ServiceChallenge sc = new ServiceChallenge();
         ServiceLigneChallenge scl =new ServiceLigneChallenge();
         
        

        ObservableList<challenge> Ochallenges = sc.afficherChallenge_client();
         ObservableList<participation_challenge> Ochallenges2 = sc.recup_participation(idclient_challenge_client.getText());
     
        
        coltitre_challenge_client.setCellValueFactory(new PropertyValueFactory<challenge, String>("titre"));
        coldescription_challenge_client.setCellValueFactory(new PropertyValueFactory<challenge, String>("description"));

        coldete_debut_challenge_client.setCellValueFactory(new PropertyValueFactory<challenge, Date>("date_debut"));
        coldete_fin_challenge_client.setCellValueFactory(new PropertyValueFactory<challenge, Date>("date_fin"));
        coletat_challenge_client.setCellValueFactory(new PropertyValueFactory<>("etat"));
        colniv_challenge_client.setCellValueFactory(new PropertyValueFactory< >("niveau"));
        liste_challenge_client.setItems(Ochallenges);
        tab_etat_client.setItems(Ochallenges2);

        
    }

    @FXML
    private void aff_joined_challenges(ActionEvent event) {
          ServiceChallenge sc = new ServiceChallenge();
         ObservableList<challenge> Ochallenges = sc.verif_challenge2(idclient_challenge_client.getText());
         ObservableList<participation_challenge> Ochallenges2 = sc.recup_participation(idclient_challenge_client.getText());
           coltitre_challenge_client.setCellValueFactory(new PropertyValueFactory<challenge, String>("titre"));
        coldescription_challenge_client.setCellValueFactory(new PropertyValueFactory<challenge, String>("description"));

        coldete_debut_challenge_client.setCellValueFactory(new PropertyValueFactory<challenge, Date>("date_debut"));
        coldete_fin_challenge_client.setCellValueFactory(new PropertyValueFactory<challenge, Date>("date_fin"));
        coletat_challenge_client.setCellValueFactory(new PropertyValueFactory<>("etat"));
        colniv_challenge_client.setCellValueFactory(new PropertyValueFactory< >("niveau"));
        liste_challenge_client.setItems(Ochallenges);
        tab_etat_client.setItems(Ochallenges2);
    }
    
}
