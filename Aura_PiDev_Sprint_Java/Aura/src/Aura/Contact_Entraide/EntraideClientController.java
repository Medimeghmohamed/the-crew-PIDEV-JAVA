/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aura.Contact_Entraide;

import Aura.Aura;
import Aura.Profil_AdminCoachClient.HomeAdminController;
import Aura.SideBar.AdminMainController;
import Aura.SideBar.ClientMainController;
import Aura.SideBar.CoachMainController;
import Entities.Admin;
import Entities.Entraide;
import Service.ServiceAdmin;
import Service.ServiceEntraide;
import Service.ServiceNotification;
import Service.ServiceUser;
import utils.Connexion;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Nour Dekhil
 */
public class EntraideClientController implements Initializable {

    @FXML
    private TableView<Entraide> tableEntraide;
    @FXML
    private TableColumn<Entraide, Integer> tb_idclient;
    @FXML
    private TableColumn<Entraide, String> tb_cat;
    @FXML
    private TableColumn<Entraide, String> tb_quest;
    @FXML
    private TableColumn<Entraide, String> tb_date;
    @FXML
    private Button btnAfficher2;
    @FXML
    private Button btn_supp;
    @FXML
    private TextField rech_client;
    @FXML
    private Button btn_contacter;

    /**
     * Initializes the controller class.
     */
    public String id_user = "";
    @FXML
    private TableColumn<Entraide, String> tb_idclient1;
    @FXML
    private ComboBox<String> combo_cat2;
    @FXML
    private TextField tf_quest2;
    @FXML
    private DatePicker tf_date2;
    @FXML
    private Button getAddView;

    public void initializeFxml(String id) {
        System.out.println(id);

        AfficherEntraide();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> listcat = FXCollections.observableArrayList("Psychologie", "Santé", "Bien-être");
        combo_cat2.setItems(listcat);

    }

    private void getSelected(MouseEvent event) {
        int index = tableEntraide.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
    }

    @FXML
    public void AfficherEntraide() {
        ServiceEntraide se = new ServiceEntraide();
        ObservableList<Entraide> OEntraide = se.AfficherEntraide();
        tb_idclient.setCellValueFactory(new PropertyValueFactory<>("email"));
        tb_cat.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        tb_quest.setCellValueFactory(new PropertyValueFactory<>("question"));
        tb_date.setCellValueFactory(new PropertyValueFactory<>("date"));

        tableEntraide.setItems(OEntraide);
    }

    public void AfficherEntraide(ActionEvent event) {
        ServiceEntraide se = new ServiceEntraide();
        ObservableList<Entraide> OEntraide = se.AfficherEntraide();
        tb_idclient.setCellValueFactory(new PropertyValueFactory<>("email"));
        tb_cat.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        tb_quest.setCellValueFactory(new PropertyValueFactory<>("question"));
        tb_date.setCellValueFactory(new PropertyValueFactory<>("date"));

        tableEntraide.setItems(OEntraide);
    }

    @FXML
    private void SupprimerEntraide(MouseEvent event) {
        ServiceEntraide se = new ServiceEntraide();

        Connection cnx = Connexion.getInstance().getConnection();

        try {
            Entraide e = tableEntraide.getSelectionModel().getSelectedItem();
            String ques = e.getQuestion();
            se.SupprimerEntraide(ques);

            JOptionPane.showMessageDialog(null, "voulez-vous vraiment supprimer cette question?");
            AfficherEntraide();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);

        }
        AfficherEntraide();
    }

    @FXML
    private void RechercherEntraide(KeyEvent event) {
        ServiceEntraide se = new ServiceEntraide();
        Entraide e = new Entraide();
        ObservableList<Entraide> OEntraide = se.Rechercher(rech_client.getText());

        tb_idclient.setCellValueFactory(new PropertyValueFactory<>("id_user"));
        tb_cat.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        tb_quest.setCellValueFactory(new PropertyValueFactory<>("question"));
        tb_date.setCellValueFactory(new PropertyValueFactory<>("date"));

        tableEntraide.setItems(OEntraide);
    }

    @FXML
    private void getAddView(MouseEvent event) throws IOException {
        ServiceUser su = new ServiceUser();
        String result = su.getRole(id_user);
        if (result.equals("Client")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/SideBar/ClientMain.fxml"));

            Parent root = loader.load();

            ClientMainController HomeScene = loader.getController();
            HomeScene.add_question(id_user);
            HomeScene.id_user = id_user;
            HomeScene.show_data(id_user);
            String css = Aura.class.getResource("Style.css").toExternalForm();

            root.getStylesheets().add(css);

            Stage window = (Stage) getAddView.getScene().getWindow();

            window.setScene(new Scene(root, 1182, 718));

        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/SideBar/CoachMain.fxml"));

            Parent root = loader.load();

            CoachMainController HomeScene = loader.getController();
            HomeScene.add_question(id_user);
            HomeScene.id_user = id_user;
            HomeScene.show_data(id_user);
            String css = Aura.class.getResource("Style.css").toExternalForm();

            root.getStylesheets().add(css);

            Stage window = (Stage) getAddView.getScene().getWindow();

            window.setScene(new Scene(root, 1182, 718));

        }

    }

    @FXML
    private void getAddView3(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("sendmailEntraide.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            String css = Aura.class.getResource("Style.css").toExternalForm();
            parent.getStylesheets().add(css);

            stage.show();
            AfficherEntraide();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void Nettoyer(ActionEvent event) {

        combo_cat2.setValue(null);
        tf_quest2.setText(null);
        tf_date2.setValue(null);
    }

    @FXML
    private void ModifierEntraide(ActionEvent event) {
        ServiceEntraide se = new ServiceEntraide();
        Entraide e = tableEntraide.getSelectionModel().getSelectedItem();

        int id = e.getId();
        System.out.println(id);

        String cat = combo_cat2.getValue();
        String ques = tf_quest2.getText();
        String date = String.valueOf(tf_date2.getValue());

        se.ModifierEntraide(id, ques, cat, date);
        ServiceNotification sn = new ServiceNotification();
        sn.Notification("Fleicitations", "Modifcation effectuée ");
        AfficherEntraide();
    }

    @FXML
    private void Nettoyer(MouseEvent event) {
    }

}
