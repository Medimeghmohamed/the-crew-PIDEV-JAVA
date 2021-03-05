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
import service.ServiceClassement;
import service.ServiceNiveau;

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
    private Tab admin_niveau;
    @FXML
    private Label imd_challenge1;
    @FXML
    private Button btn_supp_challenge;
    @FXML
    private Button btn_supp_classement;
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
    private TableColumn<classement, Integer> colniveau_classement;
    @FXML
    private TableColumn<classement, Integer> colposition_classement;
    @FXML
    private TableColumn<classement, String> colclient_classement;
    @FXML
    private TableColumn<classement, Integer> colnb_pt_classement;
    @FXML
    private TableView<classement> liste_classement_coach;
    @FXML
    private TextField titre_challenge_admin;
    @FXML
    private TextField id_challenge_admin;
    @FXML
    private Button btn_confirmer_challenge;
    @FXML
    private Button btn_supp_challenge_admin;
    @FXML
    private TableView<challenge> liste_challenge_admin;
    @FXML
    private TableView<classement> liste_clessement_admin;
    @FXML
    private TableView<challenge> liste_challenge_client;
    @FXML
    private TableColumn<challenge, String> coltitre_challenge_client;
    @FXML
    private TableColumn<challenge, String> coldescription_challenge_client;
    @FXML
    private TableColumn<challenge, Date> coldete_debut_challenge_client;
    @FXML
    private TableColumn<challenge, Date> coldete_fin_challenge_client;
    @FXML
    private Button rejoindre_challenge;
    @FXML
    private TableView<classement> liste_classement_client;
    @FXML
    private TableColumn<classement, Integer> colniveau_classement1;
    @FXML
    private TableColumn<classement, Integer> colposition_classement1;
    @FXML
    private TableColumn<classement, Integer> colclient_classement1;
    @FXML
    private TableColumn<classement, Integer> colnb_pt_classement1;
    @FXML
    private Button btn_chercher_challenge_coach;
    @FXML
    private TextField chercher_titre_challenge_coach;
    @FXML
    private TextField chercher_position_coach;
    @FXML
    private Button btn_chercher_classement_coach;
    @FXML
    private Button btn_chercher_titre_challenge_admin;
    @FXML
    private TableColumn<classement, Integer> colniveau_calassement_admin;
    @FXML
    private TableColumn<classement, Integer> colnposition_calassement_admin;
    @FXML
    private TableColumn<classement, Integer> colclient_classement_admin;
    @FXML
    /* private TextField client_classement_admin;
    @FXML*/
    private TextField niveau_classement_admin;
    @FXML
    private TextField position_classement_admin;
    @FXML
    private TextField nb_pts_classement_admin;
    @FXML
    private Button btn_modif_classement_admin;
    @FXML
    private Button btn_ajouter_classement_admin;
    @FXML
    private TextField id_classement_admin;
    @FXML
    private Button btn_chercher_classement_admin;
    @FXML
    private TableView<niveau> liste_niveau_admin;
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
    private TextField titre_challenge_client;
    @FXML
    private Button btn_chercher_challenge_client;
    @FXML
    private Button btn_chercher_classement_client;
    @FXML
    private TextField chercher_position_client;
    @FXML
    private TableColumn<classement, Integer> colpts_classement_admin;
    @FXML
    private TableColumn<niveau, Integer> colid_niveau_admin;
    @FXML
    private TableColumn<niveau, String> coltitre_niveau_admin;
    @FXML
    private TableColumn<challenge, Integer> colid_challenge_admin;
    @FXML
    private TableColumn<challenge, String> colititre_challenge_admin;
    @FXML
    private TableColumn<challenge, String> coltype_challenge_admin;
    @FXML
    private TableColumn<classement, Integer> colid_calassement_admin;
    @FXML
    private TableColumn<challenge, String> colString_challenge_admin;
    @FXML
    private TableColumn<challenge, Date> coldate_debut_challenge_admin;
    @FXML
    private TableColumn<challenge, Date> coldate_fin_challenge_admin;
    @FXML
    private TableColumn<challenge, String> coldetet_challenge_admin;
    @FXML
    private TableColumn<challenge, Integer> colniveau_challenge_admin;
    /* @FXML
    private TextField client_classement_admin;*/
    @FXML
    private TextField nom_client_classement_admin;
    @FXML
    private TextField position_classement_admin2;
    @FXML
    private TextField id_niveau_admin;
    @FXML
    private Button btn_atri_challenge_admin;
    @FXML
    private Button btn_tri_classement;
    @FXML
    private Button btn_affich_classement_coach;
    @FXML
    private Button btn_trier_niveau;
    @FXML
    private Button btn_aff_challenge_client;
    @FXML
    private TableColumn<?,?> colniv_challenge_client;

    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceChallenge sc = new ServiceChallenge();
        ServiceClassement cl = new ServiceClassement();

        ServiceNiveau n = new ServiceNiveau();

        ObservableList<challenge> Ochallenges = sc.afficherChallenge();
        ObservableList<classement> Oclassements = cl.afficherClassement();
        afficher_challege();
        afficher_classement_admin();
        afficher_challenge_admin();
        afficher_classement_coach();
       
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
        afficher_challenge_admin();
    }

    @FXML
    private void modifier_challege(ActionEvent event) {
        ServiceChallenge sp = new ServiceChallenge();
        challenge c = new challenge();

        c = sp.recup_challenge(Integer.parseInt(id_challenge.getText()));
        c.setTitre(titre_challenge.getText());
        c.setType(type_challenge.getText());
        c.setDescription(desc_challenge.getText());
        c.setImg(imd_challenge.getText());
        java.sql.Date gettedDatePickerDate = java.sql.Date.valueOf(date_debut_challenge.getValue());
        c.setDate_debut(gettedDatePickerDate);
        java.sql.Date gettedDatePickerDate2 = java.sql.Date.valueOf(date_fin_challenge.getValue());
        c.setDate_fin(gettedDatePickerDate2);
        c.setEtat(etat_challenge.getText());
        sp.modifierChallenge(c);
        afficher_challege();
    }

    @FXML
    private void afficher_challege(ActionEvent event) {

        ServiceChallenge sc = new ServiceChallenge();
        // liste_challenge.SetCollumn(sc.afficherChallenge().toString());

        ObservableList<challenge> Ochallenges = sc.afficherChallenge();
        colid_challenge.setCellValueFactory(new PropertyValueFactory<challenge, Integer>("id"));
        coltitre_challenge.setCellValueFactory(new PropertyValueFactory<challenge, String>("titre"));
        coltype_challenge.setCellValueFactory(new PropertyValueFactory<challenge, String>("type"));
        coltdescription_challenge.setCellValueFactory(new PropertyValueFactory<challenge, String>("description"));

        coldatedebut_challenge.setCellValueFactory(new PropertyValueFactory<challenge, Date>("date_debut"));
        coldatefin_challenge.setCellValueFactory(new PropertyValueFactory<challenge, Date>("date_fin"));
        coletat_challenge.setCellValueFactory(new PropertyValueFactory<challenge, String>("etat"));
        colniveau_challenge.setCellValueFactory(new PropertyValueFactory<challenge, Integer>("niveau"));
        liste_challenge.setItems(Ochallenges);
    }

    private void afficher_challege() {

        ServiceChallenge sc = new ServiceChallenge();
        // liste_challenge.SetCollumn(sc.afficherChallenge().toString());

        ObservableList<challenge> Ochallenges = sc.afficherChallenge();
        colid_challenge.setCellValueFactory(new PropertyValueFactory<challenge, Integer>("id"));
        coltitre_challenge.setCellValueFactory(new PropertyValueFactory<challenge, String>("titre"));
        coltype_challenge.setCellValueFactory(new PropertyValueFactory<challenge, String>("type"));
        coltdescription_challenge.setCellValueFactory(new PropertyValueFactory<challenge, String>("description"));

        coldatedebut_challenge.setCellValueFactory(new PropertyValueFactory<challenge, Date>("date_debut"));
        coldatefin_challenge.setCellValueFactory(new PropertyValueFactory<challenge, Date>("date_fin"));
        coletat_challenge.setCellValueFactory(new PropertyValueFactory<challenge, String>("etat"));
        colniveau_challenge.setCellValueFactory(new PropertyValueFactory<challenge, Integer>("niveau"));
        liste_challenge.setItems(Ochallenges);
    }

    @FXML
    private void supprimer_challenge(ActionEvent event) {

        ServiceChallenge sp = new ServiceChallenge();
        sp.supprimerChallenge(id_challenge.getText());
        afficher_challege();

    }

    private void afficher_challenge_admin() {

        ServiceChallenge sc = new ServiceChallenge();
        // liste_challenge.SetCollumn(sc.afficherChallenge().toString());

        ObservableList<challenge> Ochallenges = sc.afficherChallenge();
        colid_challenge_admin.setCellValueFactory(new PropertyValueFactory<>("id"));
        colititre_challenge_admin.setCellValueFactory(new PropertyValueFactory<>("titre"));
        coltype_challenge_admin.setCellValueFactory(new PropertyValueFactory<>("type"));
        colString_challenge_admin.setCellValueFactory(new PropertyValueFactory<>("description"));

        coldate_debut_challenge_admin.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        coldate_fin_challenge_admin.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        coldetet_challenge_admin.setCellValueFactory(new PropertyValueFactory<>("etat"));
        colniveau_challenge_admin.setCellValueFactory(new PropertyValueFactory<>("niveau"));
        liste_challenge_admin.setItems(Ochallenges);
    }

    private void afficher_classement_admin() {

        ServiceClassement cl = new ServiceClassement();

        ObservableList<classement> Oclassements = cl.afficherClassement();
        colid_calassement_admin.setCellValueFactory(new PropertyValueFactory<>("id"));
        colniveau_calassement_admin.setCellValueFactory(new PropertyValueFactory<>("niveau"));
        colnposition_calassement_admin.setCellValueFactory(new PropertyValueFactory<>("position"));
        colclient_classement_admin.setCellValueFactory(new PropertyValueFactory<>("client"));

        colpts_classement_admin.setCellValueFactory(new PropertyValueFactory<>("nb_points"));

        liste_clessement_admin.setItems(Oclassements);
    }

    @FXML
    private void ajouterClassement(ActionEvent event) {
        ServiceClassement cl = new ServiceClassement();
        classement c = new classement();
        c.setNiveau(Integer.parseInt(niveau_classement_admin.getText()));
        //int i = Integer.parseInt(position_classement_admin.getText());
        String str = position_classement_admin.getText();
        try {
            int i = Integer.parseInt(str);
            c.setPosition(i);

        } catch (NumberFormatException e) {
            System.out.println("not a number");
        }

        c.setClient(nom_client_classement_admin.getText());
        c.setNb_points(Integer.parseInt(nb_pts_classement_admin.getText()));

        cl.ajouterClassement(c);
        afficher_classement_admin();
    }

    @FXML
    private void rejoidreChallenge(ActionEvent event) {
    }

    @FXML
    private void chercher_challenge(ActionEvent event) {
           ServiceChallenge sn = new ServiceChallenge();
         challenge n=new challenge ();
        ObservableList<challenge> Ochallenges = sn.RechercherChallenge(chercher_titre_challenge_coach.getText());
       colid_challenge.setCellValueFactory(new PropertyValueFactory<>("id"));
        coltitre_challenge.setCellValueFactory(new PropertyValueFactory<>("titre"));
        coltype_challenge.setCellValueFactory(new PropertyValueFactory<>("type"));
        coltdescription_challenge.setCellValueFactory(new PropertyValueFactory<>("description"));

        coldatedebut_challenge.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        coldatefin_challenge.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        coletat_challenge.setCellValueFactory(new PropertyValueFactory<>("etat"));
        colniveau_challenge.setCellValueFactory(new PropertyValueFactory<>("niveau"));
        liste_challenge.setItems(Ochallenges);
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
    private void confirmerChallenge_admin(ActionEvent event) {
    }

    @FXML
    private void supprimerChallenge_admin(ActionEvent event) {
        
        ServiceChallenge sp = new ServiceChallenge();
        sp.supprimerChallenge(id_challenge_admin.getText());
        afficher_challenge_admin();

    }

    @FXML
    private void chercherChallenge_admin(ActionEvent event) {
          ServiceChallenge sn = new ServiceChallenge();
         challenge n=new challenge ();
        ObservableList<challenge> Ochallenges = sn.RechercherChallenge(titre_challenge_admin.getText());
       colid_challenge_admin.setCellValueFactory(new PropertyValueFactory<>("id"));
        colititre_challenge_admin.setCellValueFactory(new PropertyValueFactory<>("titre"));
        coltype_challenge_admin.setCellValueFactory(new PropertyValueFactory<>("type"));
        colString_challenge_admin.setCellValueFactory(new PropertyValueFactory<>("description"));

        coldate_debut_challenge_admin.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        coldate_fin_challenge_admin.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        coldetet_challenge_admin.setCellValueFactory(new PropertyValueFactory<>("etat"));
        colniveau_challenge_admin.setCellValueFactory(new PropertyValueFactory<>("niveau"));
        liste_challenge_admin.setItems(Ochallenges);

       
    }

    @FXML
    private void modifierClassement_admin(ActionEvent event) {
        ServiceClassement cl = new ServiceClassement();
        classement c = new classement();

        c = cl.recup_classement(Integer.parseInt(id_classement_admin.getText()));
        c.setNiveau(Integer.parseInt(niveau_classement_admin.getText()));
        c.setClient(nom_client_classement_admin.getText());
        c.setPosition(Integer.parseInt(position_classement_admin.getText()));
        c.setNb_points(Integer.parseInt(nb_pts_classement_admin.getText()));

        cl.modifierClassement(c);
        afficher_classement_admin();
    }

    @FXML
    private void supprimerClassement_admin(ActionEvent event) {
        ServiceClassement cl = new ServiceClassement();
        cl.supprimerChallenge(id_classement_admin.getText());
        afficher_classement_admin();
    }

    @FXML
    private void chercherPosition_admin(ActionEvent event) {
        ServiceClassement sn = new ServiceClassement();
         classement n=new classement ();
        ObservableList<classement> Oclassement = sn.RechercherClassement(position_classement_admin2.getText());
        colid_calassement_admin.setCellValueFactory(new PropertyValueFactory<>("id"));
        colniveau_calassement_admin.setCellValueFactory(new PropertyValueFactory<>("niveau"));
        colnposition_calassement_admin.setCellValueFactory(new PropertyValueFactory<>("position"));
        colclient_classement_admin.setCellValueFactory(new PropertyValueFactory<>("client"));

        colpts_classement_admin.setCellValueFactory(new PropertyValueFactory<>("nb_points"));

        liste_clessement_admin.setItems(Oclassement);
        

        
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

        n = nv.recup_niveau(Integer.parseInt(id_niveau_admin.getText()));

        n.setTitre(titre_niveau_admin.getText());

        nv.modifierNiveau(n);
        afficher_niveau_admin();

    }

    private void afficher_niveau_admin(ActionEvent event) {
        ServiceNiveau nv = new ServiceNiveau();

        ObservableList<niveau> Oniveaux = nv.afficherNiveau();
        colid_niveau_admin.setCellValueFactory(new PropertyValueFactory<>("id"));
        coltitre_niveau_admin.setCellValueFactory(new PropertyValueFactory<>("titre"));

        liste_niveau_admin.setItems(Oniveaux);
    }

    private void afficher_niveau_admin() {
        ServiceNiveau nv = new ServiceNiveau();

        ObservableList<niveau> Oniveaux = nv.afficherNiveau();
        colid_niveau_admin.setCellValueFactory(new PropertyValueFactory<>("id"));
        coltitre_niveau_admin.setCellValueFactory(new PropertyValueFactory<>("titre"));

        liste_niveau_admin.setItems(Oniveaux);
    }

    @FXML
    private void supprimer_niveau_admin(ActionEvent event) {
        ServiceNiveau nv = new ServiceNiveau();
        nv.supprimerNiveau(id_niveau_admin.getText());
        afficher_niveau_admin();
    }

    @FXML
    private void chercher_niveau(ActionEvent event) {
        ServiceNiveau sn = new ServiceNiveau();
         niveau n=new niveau ();
        ObservableList<niveau> Oniveau = sn.RechercherNiveau(titre_niveau_admin.getText());
        colid_niveau_admin.setCellValueFactory(new PropertyValueFactory<>("id"));
        coltitre_niveau_admin.setCellValueFactory(new PropertyValueFactory<>("titre"));
        

        liste_niveau_admin.setItems(Oniveau);
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
        coletat_challenge.setCellValueFactory(new PropertyValueFactory<>("etat"));

        liste_challenge_client.setItems(Ochallenge);
        
    }

    @FXML
    private void trierChallenge(ActionEvent event) {
           ServiceChallenge sc = new ServiceChallenge();
        // liste_challenge.SetCollumn(sc.afficherChallenge().toString());

        ObservableList<challenge> Ochallenges = sc.trierChallenge();
        colid_challenge.setCellValueFactory(new PropertyValueFactory<challenge, Integer>("id"));
        coltitre_challenge.setCellValueFactory(new PropertyValueFactory<challenge, String>("titre"));
        coltype_challenge.setCellValueFactory(new PropertyValueFactory<challenge, String>("type"));
        coltdescription_challenge.setCellValueFactory(new PropertyValueFactory<challenge, String>("description"));

        coldatedebut_challenge.setCellValueFactory(new PropertyValueFactory<challenge, Date>("date_debut"));
        coldatefin_challenge.setCellValueFactory(new PropertyValueFactory<challenge, Date>("date_fin"));
        coletat_challenge.setCellValueFactory(new PropertyValueFactory<challenge, String>("etat"));
        colniveau_challenge.setCellValueFactory(new PropertyValueFactory<challenge, Integer>("niveau"));
        liste_challenge.setItems(Ochallenges);
        
         
    }

    @FXML
    private void trierClassement(ActionEvent event) {
         ServiceClassement cl = new ServiceClassement();

        ObservableList<classement> Oclassements = cl.trierClassement();
       
        colniveau_classement.setCellValueFactory(new PropertyValueFactory<>("niveau"));
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

    @FXML
    private void trier_niveau(ActionEvent event) {
          ServiceNiveau nv = new ServiceNiveau();

        ObservableList<niveau> Oniveaux = nv.trierNiveau();
        colid_niveau_admin.setCellValueFactory(new PropertyValueFactory<>("id"));
        coltitre_niveau_admin.setCellValueFactory(new PropertyValueFactory<>("titre"));

        liste_niveau_admin.setItems(Oniveaux);
    }

    @FXML
    private void afficher_challege_client(ActionEvent event) {
         ServiceChallenge sc = new ServiceChallenge();
        // liste_challenge.SetCollumn(sc.afficherChallenge().toString());

        ObservableList<challenge> Ochallenges = sc.afficherChallenge();
        coltitre_challenge_client.setCellValueFactory(new PropertyValueFactory<challenge, String>("titre"));
        coldescription_challenge_client.setCellValueFactory(new PropertyValueFactory<challenge, String>("description"));

        coldete_debut_challenge_client.setCellValueFactory(new PropertyValueFactory<challenge, Date>("date_debut"));
        coldete_fin_challenge_client.setCellValueFactory(new PropertyValueFactory<challenge, Date>("date_fin"));
        coletat_challenge.setCellValueFactory(new PropertyValueFactory<challenge, String>("etat"));
        colniv_challenge_client.setCellValueFactory(new PropertyValueFactory< >("niveau"));
        liste_challenge_client.setItems(Ochallenges);
        
    }
    
    

}
      