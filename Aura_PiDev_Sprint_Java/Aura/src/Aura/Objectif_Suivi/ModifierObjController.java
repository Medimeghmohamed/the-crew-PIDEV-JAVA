/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aura.Objectif_Suivi;

import Aura.Aura;
import Aura.SideBar.ClientMainController;
import Entities.Objectif;
import Service.ServiceClient;
import Service.ServiceObjectif;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.TextFormatter;
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

    public String id_user = "";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Integer> listRep = FXCollections.observableArrayList();
        for (int i = 0; i <= 50; i++) {
            listRep.add(i);
        }
        cb_rep_modifier_obj.setItems(listRep);
    }

    public void initializeFxml(String id) {
        System.out.println(id_user);
    }

    @FXML
    private void Retour_modifier_obj(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/SideBar/ClientMain.fxml"));
        Parent root = loader.load();
        ClientMainController HomeScene = loader.getController();
        HomeScene.Retour(id_user);
        HomeScene.id_user = id_user;
        HomeScene.show_data(id_user);
        String css = Aura.class.getResource("Style.css").toExternalForm();
        root.getStylesheets().add(css);
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
        ServiceObjectif sp = new ServiceObjectif();
        Objectif o = new Objectif();
        if (tf_duree_modifier_obj.getText().matches("\\d+")) {
            //&& tf_date_modifier_obj.getText().matches("((19|2[0-9])[0-9]{2})/(0[1-9]|1[012])/(0[1-9]|[12][0-9]|3[01])")
            // && tf_date_modifier_obj.getText().matches("\\\\d{2}/\\\\d{2}/\\\\d{4}")
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation modification");
            alert.setHeaderText("Etes vous sur de modifier cet objectif?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {

                o.setId(sp.getIdObjparDesc(tf_desc_modifier_obj.getText()));
                o.setDescription(tf_desc_modifier_obj.getText());
                o.setReponse(Integer.parseInt(cb_rep_modifier_obj.getValue().toString()));
                o.setDuree(Integer.parseInt(tf_duree_modifier_obj.getText()));
                o.setDate(tf_date_modifier_obj.getText());
                sp.modifierObjectif(o);
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Information");
                alert2.setHeaderText("Modification avec succès");
                alert2.showAndWait();
            } else {
                System.out.println("Cancel");
            }

        } else {
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setTitle("Erreur");
            alert2.setHeaderText("Erreur de saisie durée ou date");
            alert2.showAndWait();
        }

    }

}
