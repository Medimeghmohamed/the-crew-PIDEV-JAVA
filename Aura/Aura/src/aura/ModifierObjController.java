/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aura;

import Entities.Objectif;
import Services.ServiceClient;
import Services.ServiceObjectif;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Chirine
 */
public class ModifierObjController implements Initializable {

    @FXML
    private ComboBox<Integer> cb_rep_modifier_obj;
    @FXML
    private TextField tf_duree_modifier_obj;
    @FXML
    private TextField tf_date_modifier_obj;
    @FXML
    private TextField tf_desc_modifier_obj;
    @FXML
    private Button btn_confirmer_modifier_obj;
    @FXML
    private Button btn_retour_modifier_obj;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void Retour_modifier_obj(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        Parent root = loader.load();
        MainController HomeScene = loader.getController();
        Pane view;
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("Objectifs.fxml"));
        view = loader2.load();
        HomeScene.returnPane(view);
        Stage window = (Stage) btn_retour_modifier_obj.getScene().getWindow();
        window.setScene(new Scene(root, 1182, 718));
    }

    @FXML
    private void selectRep(ActionEvent event) {
    }

    public void selected_item(int id, String desc, String date, int duree, int rep) {
        System.out.println(id + desc + date + duree + rep);
        tf_desc_modifier_obj.setText(desc);
        cb_rep_modifier_obj.setValue(rep);
        tf_date_modifier_obj.setText(date);
        tf_duree_modifier_obj.setText("" + duree);
    }

    @FXML
    private void confirmer_modifier_obj(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation modification");
        alert.setHeaderText("Etes vous sur de modifier cet objectif?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            ServiceObjectif sp = new ServiceObjectif();
            Objectif o = new Objectif();
            o.setId(sp.getIdObjparDesc(tf_desc_modifier_obj.getText()));
            o.setReponse(Integer.parseInt(cb_rep_modifier_obj.getValue().toString()));
            o.setDuree(Integer.parseInt(tf_duree_modifier_obj.getText()));
            System.out.println(tf_desc_modifier_obj.getText());
            o.setDescription(tf_desc_modifier_obj.getText());
            o.setDate(tf_date_modifier_obj.getText());
            sp.modifierObjectif(o);
        } else {
            System.out.println("Cancel");
        }
    }

}
