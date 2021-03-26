/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aura;

import Entities.Objectif;
import Entities.ObjectifPred;
import Services.ServiceClient;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Chirine
 */
public class MesObjectifsController1 implements Initializable {

    @FXML
    private TableView<Objectif> tvobjectifs;
    @FXML
    private TableColumn<Objectif, String> colid;
    @FXML
    private TableColumn<Objectif, String> coldesc;
    @FXML
    private TableColumn<Objectif, String> colrep;
    @FXML
    private TableColumn<Objectif, String> coldate;
    @FXML
    private TableColumn<Objectif, String> colduree;
    @FXML
    private ComboBox<String> cbobj;
    @FXML
    private TextField tfobj;
    @FXML
    private ComboBox<Integer> cbrep;
    @FXML
    private TextField tfid;
    @FXML
    private TextField tfduree;
    @FXML
    private TextField tfdate;
    @FXML
    private Button btnAjouterObj;
    @FXML
    private Button btnModifierObj;
    @FXML
    private Button btnSupprimerObj;
    @FXML
    private TextField tfidCli;
    @FXML
    private TextField tfrechObj;
    @FXML
    private ComboBox<String> cbtriObj;
    @FXML
    private Label erreurObj;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceObjectif so = new ServiceObjectif();
        ObservableList<Objectif> objectifs = so.afficherObjectifs();
        ServiceObjectifPred sop = new ServiceObjectifPred();

        ObservableList<String> listObj = sop.getValuesObjectifs();
        cbobj.setItems(listObj);
        ObservableList<Integer> listRep = FXCollections.observableArrayList();
        for (int i = 0; i <= 50; i++) {
            listRep.add(i);
        }
        cbrep.setItems(listRep);
        ObservableList<String> listTriObj = FXCollections.observableArrayList("par ID", "par réponse", "par description");
        cbtriObj.setItems(listTriObj);
               afficherObjectifs();
                       //afficher la date courante
        tfdate.setText(new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime()));
        new animatefx.animation.Swing(btnAjouterObj).play();
        new animatefx.animation.Swing(btnModifierObj).play();
        new animatefx.animation.Swing(btnSupprimerObj).play();
    }

   @FXML
    private void ajouterObjectif(ActionEvent event) {
        ServiceObjectif sp = new ServiceObjectif();
        ServiceClient sc = new ServiceClient();
        Objectif o = new Objectif();
//        o.setId(tfid.getText());
        if (cbobj.getValue() == null && tfobj.getText() != null) {
            o.setDescription(tfobj.getText());

            o.setReponse(Integer.parseInt(cbrep.getValue().toString()));
            o.setDuree(Integer.parseInt(tfduree.getText()));
            o.setCli(sc.load_data_modify(tfidCli.getText()));
            sp.ajouterObjectif(o);
            afficherObjectifs();
            tfid.setText(null);
            cbobj.setValue(null);
            tfobj.setText(null);
            cbrep.setValue(null);
            tfduree.setText(null);
            tfidCli.setText(null);
            erreurObj.setText(null);
            new animatefx.animation.Flash(btnAjouterObj).play();

        } else if (cbobj.getValue() != null && tfobj.getText() == null) {
            o.setDescription(cbobj.getValue().toString());
            o.setReponse(Integer.parseInt(cbrep.getValue().toString()));
            o.setDuree(Integer.parseInt(tfduree.getText()));
            o.setCli(sc.load_data_modify(tfidCli.getText()));
            sp.ajouterObjectif(o);
            afficherObjectifs();
            tfid.setText(null);
            cbobj.setValue(null);
            tfobj.setText(null);
            cbrep.setValue(null);
            tfduree.setText(null);
            tfidCli.setText(null);
            erreurObj.setText(null);
            new animatefx.animation.Flash(btnAjouterObj).play();

        } else {
            erreurObj.setText("!! Il faut soit saisir soit choisir");
            cbobj.setValue(null); //throws exception mais l'ajout se fait normalement
            tfobj.setText(null);
            new animatefx.animation.Shake(btnAjouterObj).play();
        }

    }

    @FXML
    private void modifierObjectif(ActionEvent event) {
        ServiceObjectif sp = new ServiceObjectif();
        ServiceClient sc = new ServiceClient();
        Objectif o = new Objectif();

///        o.setId(tfid.getText());
        o.setDescription(cbobj.getValue().toString());
        o.setReponse(Integer.parseInt(cbrep.getValue().toString()));
        o.setDuree(Integer.parseInt(tfduree.getText()));
        o.setCli(sc.load_data_modify(tfidCli.getText()));
        //o.setIdCli(tfidCli.getText());
        sp.modifierObjectif(o);
        afficherObjectifs();
        new animatefx.animation.Flash(btnModifierObj).play();

    }

    @FXML
    private void supprimerObjectif(ActionEvent event) {
        ServiceObjectif sp = new ServiceObjectif();
        // tvobjectifs.getItems().removeAll(tvobjectifs.getSelectionModel().getSelectedItem());
//        sp.supprimerObjectif(tfid.getText());
        afficherObjectifs();
        tfid.setText(null);
        cbobj.setValue(null);
        tfobj.setText(null);
        cbrep.setValue(null);
        tfduree.setText(null);
        tfidCli.setText(null);
        erreurObj.setText(null);
        new animatefx.animation.Flash(btnSupprimerObj).play();

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

    //appyer sur une ligne de la table ->afficher dans labels
    @FXML
    private void OnMouse(MouseEvent event) {
        Objectif obj = tvobjectifs.getSelectionModel().getSelectedItem();
//        tfid.setText(obj.getId());
        cbobj.setValue(obj.getDescription());
        cbrep.setValue(obj.getReponse());
        tfdate.setText(obj.getDate());
        tfduree.setText("" + obj.getDuree());
        tfidCli.setText(obj.getCli().getId());
    }

    @FXML
    public void rechercherObjectif() {
        ServiceObjectif sop = new ServiceObjectif();
        ObservableList<Objectif> objectifs = sop.rechercherObjectif(tfrechObj.getText());
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        coldesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colrep.setCellValueFactory(new PropertyValueFactory<>("reponse"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colduree.setCellValueFactory(new PropertyValueFactory<>("duree"));
        tvobjectifs.setItems(objectifs);

    }

    @FXML
    private void selectTriObj(ActionEvent event) {
        ServiceObjectif sop = new ServiceObjectif();
        ObservableList<Objectif> objectifs = FXCollections.observableArrayList();
        if (cbtriObj.getValue().equals("par ID")) {
            objectifs = sop.trierObjectifparId();
        } else if (cbtriObj.getValue().equals("par réponse")) {
            objectifs = sop.trierObjectifparRep();
        } else {
            objectifs = sop.trierObjectifparDesc();
        }
        tvobjectifs.setItems(objectifs);
    }

    @FXML
    private void selectObj(ActionEvent event) {
    }

}
