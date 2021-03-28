/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aura;

import Entities.Objectif;
import Services.ServiceClient;
import Services.ServiceObjectif;
import Services.ServiceObjectifPred;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Chirine
 */
public class AjouterObjController implements Initializable {

    int i = 0;
    @FXML
    private ComboBox<String> cbobj;
    @FXML
    private TextField tfobj;
    @FXML
    private ComboBox<Integer> cbrep;
    @FXML
    private TextField tfduree;
    @FXML
    private TextField tfdate;
    @FXML
    private Button btn_retour_ajout_obj;
    @FXML
    private Button btn_confirmer_ajout_obj;

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
        cbobj.setValue(null);
        tfobj.setText(null);

        tfdate.setText(new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime()));

    }

    @FXML
    private void selectObj(ActionEvent event) {
    }

    @FXML
    private void selectRep(ActionEvent event) {
    }

    @FXML
    private void retour_ajout_obj(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        Parent root = loader.load();
        MainController HomeScene = loader.getController();
        Pane view;
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("Objectifs.fxml"));
         String css= AjouterObjController.class.getResource("style.css").toExternalForm();
        root.getStylesheets().add(css);
        view = loader2.load();
        HomeScene.returnPane(view);
        Stage window = (Stage) btn_retour_ajout_obj.getScene().getWindow();
        window.setScene(new Scene(root, 1182, 718));
    }

    @FXML
    private void confirmer_ajout_obj(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation ajout");
        alert.setHeaderText("Etes vous sur d'ajouter cet objectif?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            ServiceObjectif sp = new ServiceObjectif();
            ServiceClient sc = new ServiceClient();
            Objectif o = new Objectif();

            if (cbobj.getValue() == null && tfobj.getText() != null) {
                o.setDescription(tfobj.getText());
                o.setReponse(Integer.parseInt(cbrep.getValue().toString()));
                o.setDuree(Integer.parseInt(tfduree.getText()));
                o.setCli(sc.load_data_modify("12345676"));
                sp.ajouterObjectif(o);
                //afficherObjectifs();
                //tfid.setText(null);
                cbobj.setValue(null);
                tfobj.setText(null);
                cbrep.setValue(null);
                tfduree.setText(null);

            } else if (cbobj.getValue() != null && tfobj.getText() == null) {
                o.setDescription(cbobj.getValue().toString());
                o.setReponse(Integer.parseInt(cbrep.getValue().toString()));
                o.setDuree(Integer.parseInt(tfduree.getText()));
                o.setCli(sc.load_data_modify("12345676"));
                System.out.println("aaaaaaaaaa");
                sp.ajouterObjectif(o);
                System.out.println("bbbbbbbbbbbbbbb");
                //afficherObjectifs();
                //tfid.setText(null);
                cbobj.setValue(null);
                tfobj.setText(null);
                cbrep.setValue(null);
                tfduree.setText(null);

            } else {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Attention");
                a.setContentText("Vous devez soit saisir soit selectionner un objectif");
                a.setHeaderText(null);
                a.showAndWait();
                cbobj.setValue(null); //throws exception mais l'ajout se fait normalement
                tfobj.setText(null);
            }
            Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
            alert2.setTitle("Google Agenda");
            alert2.setHeaderText("Voulez-vous ajouter cet objectif à votre Google Aganda?");
            Optional<ButtonType> result2 = alert2.showAndWait();
            if (result2.get() == ButtonType.OK) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
                Parent root = loader.load();
                MainController HomeScene = loader.getController();
                Pane view;
                FXMLLoader loader2 = new FXMLLoader(getClass().getResource("ObjactifGoogleAgenda.fxml"));
                String css = ObjectifsController.class.getResource("style.css").toExternalForm();
                root.getStylesheets().add(css);
                view = loader2.load();
                HomeScene.returnPane(view);
                Stage window = (Stage) btn_confirmer_ajout_obj.getScene().getWindow();
                window.setScene(new Scene(root, 1182, 718));
            } else {
                System.out.println("Cancel");
            }
        } else {
            System.out.println("Cancel");
        }

    }

}
