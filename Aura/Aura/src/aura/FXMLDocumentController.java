/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aura;

import Entities.Objectif;
import Entities.ObjectifPred;
import Entities.Suivi;
import Services.ServiceObjectif;
import Services.ServiceObjectifPred;
import Services.ServiceSuivi;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
//import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Chirine
 */
public class FXMLDocumentController implements Initializable {

    private Label label;
    @FXML
    private AnchorPane tab1;
    @FXML
    private TableView<Objectif> tvobjectifs;
    @FXML
    private TableColumn<Objectif, String> colid;
    @FXML
    private TableColumn<Objectif, String> coldesc;
    @FXML
    private TableColumn<Objectif, Integer> colrep;
    @FXML
    private TableColumn<Objectif, String> coldate;
    @FXML
    private TableColumn<Objectif, Integer> colduree;
    @FXML
    private ComboBox cbobj;
    @FXML
    private TextField tfobj;
    @FXML
    private ComboBox cbrep;
    @FXML
    private TextField tfid;
    @FXML
    private TextField tfduree;
    @FXML
    private TextField tfdate;
    @FXML
    private AnchorPane tab2;
    @FXML
    private AnchorPane tab21;
    @FXML
    private TextField tfidCli;
    @FXML
    private Label erreurObj;
    @FXML
    private TextField tfid_pred;
    @FXML
    private TextField tfdesc_pred;
    @FXML
    private TextField tfduree_pred;
    @FXML
    private TableColumn<ObjectifPred, String> colid_pred;
    @FXML
    private TableColumn<ObjectifPred, String> coldes_pred;
    @FXML
    private TableColumn<ObjectifPred, Integer> colduree_pred;
    @FXML
    private TextField tfidadP;
    @FXML
    private TableColumn<ObjectifPred, String> colidad_pred;
    @FXML
    private TableView<ObjectifPred> tvObjectifPred;
    @FXML
    private Label dateFicheSuivi;
    @FXML
    private TextField tfid_suiv;
    @FXML
    private ComboBox<String> cbobjsuivi;
    @FXML
    private ComboBox<Integer> cbrep_suivi;
    @FXML
    private TableColumn<Suivi, String> colid_suivi;
    @FXML
    private TableColumn<Suivi, String> colidobj_suivi;
    @FXML
    private TableColumn<Suivi, String> coldesc_suivi;
    @FXML
    private TableColumn<Suivi, Integer> colvaleur_suivi;
    @FXML
    private TableColumn<Suivi, String> coldate_suivi;
    @FXML
    private TableColumn<Suivi, String> colidcli_suivi;
    @FXML
    private TableView<Suivi> tv_suivi;

    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceObjectifPred sop = new ServiceObjectifPred();
        ObservableList<ObjectifPred> objectifsP = sop.afficherObjectifsPred();
        ServiceObjectif so = new ServiceObjectif();
        ObservableList<Objectif> objectifs = so.afficherObjectifs();
        ServiceSuivi ssuiv= new ServiceSuivi();
        ObservableList<Suivi> suivis = ssuiv.afficherSuivi();

        ObservableList<String> listObj = sop.getValuesObjectifs();
        cbobj.setItems(listObj);
        ObservableList<String> listObjSuiv = so.getMesObjectifs(1);
        cbobjsuivi.setItems(listObjSuiv);
        tfobj.setText(null);
        //les valeurs des réponses
        ObservableList<Integer> listRep = FXCollections.observableArrayList();
        for (int i = 0; i <= 50; i++) {
            listRep.add(i);
        }
        cbrep.setItems(listRep);
        cbrep_suivi.setItems(listRep);

        afficherObjectifs();
        afficherObjectifsPred();
        afficherSuivi();

        //afficher la date courante
        tfdate.setText(new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime()));
        dateFicheSuivi.setText(new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime()));
        /* Pour récupérer après l'ID du client lors de l'authentification
        lbmesObjectifsFiche.setText(tfid_suivi.getText().toString());*/
        //lbmesObjectifsFiche.setText(so.getMesObjectifs(1));
    }

    //combobox objets prédéfinis
    @FXML
    private void selectObj(ActionEvent event) {
        String s = cbobj.getSelectionModel().getSelectedItem().toString();
    }

    @FXML
    private void ajouterObjectif(ActionEvent event) {
        ServiceObjectif sp = new ServiceObjectif();
        Objectif o = new Objectif();

        o.setId(tfid.getText());
        if (cbobj.getValue() == null && tfobj.getText() != null) {
            o.setDescription(tfobj.getText());
            o.setReponse(Integer.parseInt(cbrep.getValue().toString()));
            //o.setDate(tfdate.getText());
            o.setDuree(Integer.parseInt(tfduree.getText()));
            o.setIdCli(tfidCli.getText());
            sp.ajouterObjectif(o);
            afficherObjectifs();
            tfid.setText(null);
            erreurObj.setText(null);

        } else if (cbobj.getValue() != null && tfobj.getText() == null) {
            o.setDescription(cbobj.getValue().toString());
            o.setReponse(Integer.parseInt(cbrep.getValue().toString()));
            //o.setDate(tfdate.getText());
            o.setDuree(Integer.parseInt(tfduree.getText()));
            o.setIdCli(tfidCli.getText());
            sp.ajouterObjectif(o);
            afficherObjectifs();
            tfid.setText(null);
            erreurObj.setText(null);
        } else {
            erreurObj.setText("!! Il faut soit saisir soit choisir");
        }

    }

    @FXML
    private void modifierObjectif(ActionEvent event) {
        ServiceObjectif sp = new ServiceObjectif();
        Objectif o = new Objectif();

        o.setId(tfid.getText());
        o.setDescription(cbobj.getValue().toString());
        //o.setDescription(tfobj.getText());
        //o.setReponse(Integer.parseInt(tfRep.getText()));
        o.setReponse(Integer.parseInt(cbrep.getValue().toString()));
        //o.setDate(tfdate.getText());
        o.setDuree(Integer.parseInt(tfduree.getText()));
        o.setIdCli(tfidCli.getText());
        sp.modifierObjectif(o);
        afficherObjectifs();

    }

    @FXML
    private void supprimerObjectif(ActionEvent event) {
        ServiceObjectif sp = new ServiceObjectif();
        sp.supprimerObjectif(tfid.getText());
        afficherObjectifs();
    }

    private void afficherObjectifs() {
        ServiceObjectif sop = new ServiceObjectif();
        ObservableList<Objectif> objectifs = sop.afficherObjectifs();
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        coldesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colrep.setCellValueFactory(new PropertyValueFactory<>("reponse"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colduree.setCellValueFactory(new PropertyValueFactory<>("duree"));
        tvobjectifs.setItems(objectifs);
    }

    @FXML
    private void selectRep(ActionEvent event) {
    }

    @FXML
    private void OnMouse(MouseEvent event) {
        Objectif obj = tvobjectifs.getSelectionModel().getSelectedItem();
        tfid.setText(obj.getId());
        cbobj.setValue(obj.getDescription());
        cbrep.setValue(obj.getReponse());
        tfdate.setText(obj.getDate());
        tfduree.setText("" + obj.getDuree());
        tfidCli.setText(obj.getIdCli());
    }

    @FXML
    private void ajouterPred(ActionEvent event) {
        ServiceObjectifPred sp = new ServiceObjectifPred();
        ObjectifPred o = new ObjectifPred();

        o.setIdP(tfid_pred.getText());
        o.setDescriptionP(tfdesc_pred.getText());
        o.setDureeP(Integer.parseInt(tfduree_pred.getText()));
        o.setIdAdminP(tfidadP.getText());
        sp.ajouterObjectifPred(o);
        afficherObjectifsPred();
        //tfid.setText(null);
    }

    private void afficherObjectifsPred() {
        ServiceObjectifPred sop = new ServiceObjectifPred();
        ObservableList<ObjectifPred> objectifsP = sop.afficherObjectifsPred();
        colid_pred.setCellValueFactory(new PropertyValueFactory<>("idP"));
        coldes_pred.setCellValueFactory(new PropertyValueFactory<>("descriptionP"));
        colduree_pred.setCellValueFactory(new PropertyValueFactory<>("dureeP"));
        colidad_pred.setCellValueFactory(new PropertyValueFactory<>("idAdminP"));
        tvObjectifPred.setItems(objectifsP);
    }

    @FXML
    private void modifierPred(ActionEvent event) {
        ServiceObjectifPred sp = new ServiceObjectifPred();
        ObjectifPred o = new ObjectifPred();

        o.setIdP(tfid_pred.getText());
        o.setDescriptionP(tfdesc_pred.getText());
        o.setDureeP(Integer.parseInt(tfduree_pred.getText()));
        o.setIdAdminP(tfidadP.getText());
        sp.modifierObjectifPred(o);
        afficherObjectifsPred();

    }

    @FXML
    private void supprimerPred(ActionEvent event) {
        ServiceObjectifPred sp = new ServiceObjectifPred();
        sp.supprimerObjectifPred(tfid.getText());
        afficherObjectifsPred();
    }

    @FXML
    private void ajouterSuivi(ActionEvent event) {
        ServiceObjectif sp = new ServiceObjectif();
        ServiceSuivi ss = new ServiceSuivi();
        Suivi s = new Suivi();
          
        s.setIdSuiv(tfid_suiv.getText());
        s.setValeurSuiv(cbrep_suivi.getValue());
        s.setIdClientSuiv("1"); //!!! a changer !!!
        s.setIdObjectifSuiv(sp.getIdObj(cbobjsuivi.getValue(),1));
        s.setDateSuiv(dateFicheSuivi.getText());
        ss.ajouterSuivi(s);

    }
    
    private void afficherSuivi(){
        ServiceSuivi ss= new ServiceSuivi();
        ObservableList<Suivi> suivis = ss.afficherSuivi();
        colid_suivi.setCellValueFactory(new PropertyValueFactory<>("idSuiv"));
        colidobj_suivi.setCellValueFactory(new PropertyValueFactory<>("idObjectifSuiv"));
        //col desc
        colvaleur_suivi.setCellValueFactory(new PropertyValueFactory<>("valeurSuiv"));
        coldate_suivi.setCellValueFactory(new PropertyValueFactory<>("dateSuiv"));
        colidcli_suivi.setCellValueFactory(new PropertyValueFactory<>("idClientSuiv"));
        tv_suivi.setItems(suivis);
    }

}
