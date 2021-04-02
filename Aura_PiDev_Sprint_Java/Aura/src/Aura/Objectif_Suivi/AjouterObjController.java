/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aura.Objectif_Suivi;

import Aura.Aura;
import Aura.SideBar.ClientMainController;
import Entities.JavaMailObjectif;
import Entities.Objectif;
import Service.ServiceClient;
import Service.ServiceObjectif;
import Service.ServiceObjectifPred;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.mail.MessagingException;
import javax.swing.JFileChooser;

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
    @FXML
    private CheckBox ck_mail;

    public String id_user = "";
    @FXML
    private Button btn_AjouterIcone;
    @FXML
    private TextField tf_icon_name;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceObjectif so = new ServiceObjectif();
        // ObservableList<Objectif> objectifs = so.afficherObjectifs(id_user);
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
        tf_icon_name.setText("defaut.png");

        tfdate.setText(new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime()));

    }

    public void initializeFxml(String id) {

        System.out.println(id_user);
    }

    @FXML
    private void selectObj(ActionEvent event) {
    }

    @FXML
    private void selectRep(ActionEvent event) {
    }

    @FXML
    private void retour_ajout_obj(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/SideBar/ClientMain.fxml"));
        Parent root = loader.load();
        ClientMainController HomeScene = loader.getController();
        HomeScene.Retour(id_user);
        HomeScene.id_user = id_user;
        HomeScene.show_data(id_user);
        String css = Aura.class.getResource("Style.css").toExternalForm();
        root.getStylesheets().add(css);
        Stage window = (Stage) btn_retour_ajout_obj.getScene().getWindow();
        window.setScene(new Scene(root, 1182, 718));

    }

    @FXML
    private void confirmer_ajout_obj(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        ServiceObjectif sp = new ServiceObjectif();
        ServiceClient sc = new ServiceClient();
        Objectif o = new Objectif();

        if (cbobj.getValue() == null && tfobj.getText() != null && tfduree.getText().matches("\\d+")) {
            alert.setTitle("Confirmation ajout");
            alert.setHeaderText("Etes vous sur de vouloir ajouter cet objectif?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                o.setDescription(tfobj.getText());
                o.setReponse(Integer.parseInt(cbrep.getValue().toString()));
                o.setDuree(Integer.parseInt(tfduree.getText()));
                o.setCli(sc.load_data_modify(id_user));
                o.setIcone(tf_icon_name.getText());
                if (ck_mail.isSelected()) {
                    sp.ajouterObjectif(o, 1);
                } else {
                    sp.ajouterObjectif(o, 0);
                }
                tfobj.setText(null);
                cbrep.setValue(null);
                tfduree.setText(null);

                Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
                alert2.setTitle("Google Agenda");
                alert2.setHeaderText("Voulez-vous ajouter cet objectif à votre Google Aganda?");
                Optional<ButtonType> result2 = alert2.showAndWait();
                if (result2.get() == ButtonType.OK) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/SideBar/ClientMain.fxml"));
                    Parent root = loader.load();
                    ClientMainController HomeScene = loader.getController();
                    HomeScene.AgendaObjectif(id_user);
                    HomeScene.id_user = id_user;
                    HomeScene.show_data(id_user);
                    String css = Aura.class.getResource("Style.css").toExternalForm();
                    root.getStylesheets().add(css);
                    Stage window = (Stage) btn_confirmer_ajout_obj.getScene().getWindow();
                    window.setScene(new Scene(root, 1182, 718));
                } else {
                    System.out.println("Cancel");
                }

            }

        } else if (cbobj.getValue() != null && tfobj.getText() == null && tfduree.getText().matches("\\d+")) {
            alert.setTitle("Confirmation ajout");
            alert.setHeaderText("Etes vous sur de vouloir ajouter cet objectif?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                o.setDescription(cbobj.getValue().toString());
                o.setReponse(Integer.parseInt(cbrep.getValue().toString()));
                o.setDuree(Integer.parseInt(tfduree.getText()));
                o.setCli(sc.load_data_modify(id_user));
                o.setIcone(tf_icon_name.getText());
                if (ck_mail.isSelected()) {
                    sp.ajouterObjectif(o, 1);
                } else {
                    sp.ajouterObjectif(o, 0);
                }
                tfobj.setText(null);
                cbrep.setValue(null);
                tfduree.setText(null);

                Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
                alert2.setTitle("Google Agenda");
                alert2.setHeaderText("Voulez-vous ajouter cet objectif à votre Google Aganda?");
                Optional<ButtonType> result2 = alert2.showAndWait();
                if (result2.get() == ButtonType.OK) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/SideBar/ClientMain.fxml"));
                    Parent root = loader.load();
                    ClientMainController HomeScene = loader.getController();
                    HomeScene.AgendaObjectif(id_user);
                    HomeScene.id_user = id_user;
                    HomeScene.show_data(id_user);
                    String css = Aura.class.getResource("Style.css").toExternalForm();
                    root.getStylesheets().add(css);
                    Stage window = (Stage) btn_confirmer_ajout_obj.getScene().getWindow();
                    window.setScene(new Scene(root, 1182, 718));
                } else {
                    System.out.println("Cancel");
                }
            }

        } else {
            cbobj.setValue(null);
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Attention");
            a.setContentText("Erreur de saisie");
            a.setHeaderText(null);
            a.showAndWait();
            tfobj.setText(null);
        }

    }

    @FXML
    private void ck_mail(ActionEvent event) throws MessagingException {
    }

    @FXML
    private void tfdate_clicked(ActionEvent event) {

        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setTitle("Attention");
        a.setContentText("Vous ne pouvez pas modifier la date. Elle prend par défaut celle d'aujourd'hui");
        a.setHeaderText(null);
        a.showAndWait();
    }

    @FXML
    private void tf_duree(ActionEvent event) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setTitle("Attention");
        a.setContentText("Vous ne pouvez pas modifier la date. Elle prend par défaut celle d'aujourd'hui");
        a.setHeaderText(null);
        a.showAndWait();
    }

    @FXML
    private void AjouterIcone(ActionEvent event) {
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        
        if (f !=null){
            System.out.println(f.getName());
            tf_icon_name.setText(f.getName());
        }else{
            
        }
    }

}
