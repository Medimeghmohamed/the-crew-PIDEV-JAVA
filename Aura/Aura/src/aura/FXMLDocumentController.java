/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aura;

import Entities.Objectif;
import Services.ServiceObjectif;
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

    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> listObj = FXCollections.observableArrayList("aaaa", "bbbb", "cccc");
        cbobj.setItems(listObj);
        tfobj.setText(null);
        ObservableList<Integer> listRep = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        cbrep.setItems(listRep);
        
        //afficher au lancement
        ServiceObjectif so = new ServiceObjectif();
        ObservableList<Objectif> objectifs = so.afficherObjectifs();
        // ... + noms de colonnes
        colid.setCellValueFactory(new PropertyValueFactory<Objectif, String>("id"));
        coldesc.setCellValueFactory(new PropertyValueFactory<Objectif, String>("description"));
        colrep.setCellValueFactory(new PropertyValueFactory<Objectif, Integer>("reponse"));
        coldate.setCellValueFactory(new PropertyValueFactory<Objectif, String>("dateDebut"));
        colduree.setCellValueFactory(new PropertyValueFactory<Objectif, Integer>("duree"));
        tvobjectifs.setItems(objectifs);

        //afficher la date courante
        tfdate.setText(new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime()));
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
            afficherObjectifs(event);
            tfid.setText(null);
            erreurObj.setText(null);

        } else if (cbobj.getValue() != null && tfobj.getText() == null) {
            o.setDescription(cbobj.getValue().toString());
            o.setReponse(Integer.parseInt(cbrep.getValue().toString()));
            //o.setDate(tfdate.getText());
            o.setDuree(Integer.parseInt(tfduree.getText()));
            o.setIdCli(tfidCli.getText());
            sp.ajouterObjectif(o);
            afficherObjectifs(event);
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
        afficherObjectifs(event);

    }

    @FXML
    private void supprimerObjectif(ActionEvent event) {
        ServiceObjectif sp = new ServiceObjectif();
        sp.supprimerObjectif(tfid.getText());
        afficherObjectifs(event);
    }

    private void afficherObjectifs(ActionEvent event) {
        ServiceObjectif so = new ServiceObjectif();
        ObservableList<Objectif> objectifs = so.afficherObjectifs();
        // ... + noms de colonnes
        colid.setCellValueFactory(new PropertyValueFactory<Objectif, String>("id"));
        coldesc.setCellValueFactory(new PropertyValueFactory<Objectif, String>("description"));
        colrep.setCellValueFactory(new PropertyValueFactory<Objectif, Integer>("reponse"));
        coldate.setCellValueFactory(new PropertyValueFactory<Objectif, String>("dateDebut"));
        colduree.setCellValueFactory(new PropertyValueFactory<Objectif, Integer>("duree"));
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

}
