/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aura.Profil_AdminCoachClient;

import Aura.SideBar.AdminMainController;
import Aura.Aura;
import Entities.Admin;
import Service.ServiceAdmin;
import Service.ServiceUser;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
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
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JFileChooser;

/**
 * FXML Controller class
 *
 * @author SeifBS
 */
public class List_AdminsController implements Initializable {

    @FXML
    private TableView<Admin> tab_admins;
    @FXML
    private TableColumn<Admin, String> tab_id;
    @FXML
    private TableColumn<Admin, String> nom_id;
    @FXML
    private TableColumn<Admin, String> prenom_id;
    @FXML
    private TableColumn<Admin, String> email_id;
    @FXML
    private TableColumn<Admin, String> tel_id;
    @FXML
    private TextField recherche_a;
    @FXML
    private ComboBox<String> combo_critere_rech_admin;
    private Button liste_des_admins;
    public String id_user = "";
    @FXML
    private MenuButton menu_user;
    @FXML
    private MenuItem rme_disable;
    @FXML
    private MenuItem activate_sms;
    @FXML
    private MenuItem desctivate_sms;
    @FXML
    private Circle picture_user;
    @FXML
    private Button ajouter_admin_nav;
    @FXML
    private Button liste_des_admins_nav;
    @FXML
    private Button liste_des_coachs_nav;
    @FXML
    private Button liste_des_clients_nav;
    @FXML
    private Button mes_informations_nav;

    /**
     * Initializes the controller class.
     */
    public void initializeFxml(String id) {

        ServiceAdmin sa = new ServiceAdmin();
        Admin a = new Admin();
        ServiceUser su = new ServiceUser();
        String result = su.getRole(id);

        a = sa.load_data_modify(id);

        Super_Admin_Tab();

        if ("Y".equals(a.getRme())) {
            rme_disable.setVisible(true);
        } else {
            rme_disable.setVisible(false);

        }

        picture_user.setStroke(Color.SEAGREEN);
        Image picture = new Image("/Images/Profile/" + a.getPicture(), false);
        picture_user.setFill(new ImagePattern(picture));
        picture_user.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKSEAGREEN));
        List<String> list_critere_a;

        if (result.equals("SAdmin")) {
            list_critere_a = Arrays.asList("id", "nom", "prenom", "email", "tel");
        } else {
            list_critere_a = Arrays.asList("nom", "prenom");

        }

        ObservableList<String> list_critere_a1;
        list_critere_a1 = FXCollections.observableArrayList(list_critere_a);

        combo_critere_rech_admin.setItems(list_critere_a1);
        load_data();
    }

    private void load_data() {

        ServiceAdmin sa = new ServiceAdmin();

        affichage_tab_admin();

        Admin a = new Admin();
        a = sa.load_data_modify(id_user);

        if ("Y".equals(a.getRme())) {
            rme_disable.setVisible(true);
        } else {
            rme_disable.setVisible(false);

        }

        if ("Y".equals(a.getSms())) {
            desctivate_sms.setVisible(true);
            activate_sms.setVisible(false);
        } else {
            desctivate_sms.setVisible(false);
            activate_sms.setVisible(true);
        }

    }

    public void affichage_tab_admin() {
        ServiceAdmin sa = new ServiceAdmin();
        ObservableList<Admin> list = FXCollections.observableArrayList(sa.afficherAdmin());
        ServiceUser su = new ServiceUser();
        String result = su.getRole(id_user);
        if ("SAdmin".equals(result)) {

            tab_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            email_id.setCellValueFactory(new PropertyValueFactory<>("email"));
            tel_id.setCellValueFactory(new PropertyValueFactory<>("tel"));

        }

        nom_id.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom_id.setCellValueFactory(new PropertyValueFactory<>("prenom"));

        tab_admins.setItems(list);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image image = new Image(getClass().getResourceAsStream("/Images/menu.png"));
        menu_user.setGraphic(new ImageView(image));

        load_data();

        // TODO
    }

    private void log_out() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/Login_CreateAccount/Login.fxml"));

        Parent root = loader.load();

        String css = Aura.class.getResource("Style.css").toExternalForm();
        Stage stage = new Stage();
        Stage window = (Stage) menu_user.getScene().getWindow();
        stage.setTitle("Login");

        root.getStylesheets().add(css);

        window.setScene(new Scene(root, 1182, 718));

    }

    public void Super_Admin_Tab() {
        ServiceAdmin sa = new ServiceAdmin();

        String id = id_user;
        boolean test = sa.verif_super_admin(id);
        if (!test) {

            ajouter_admin_nav.setDisable(true);

        }

    }

    public boolean Suppression_Box(String title, String message) {
        boolean sortie = false;
        Alert.AlertType Type = Alert.AlertType.CONFIRMATION;
        Alert alert = new Alert(Type, "");
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setTitle(title);
        alert.setContentText(message);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            sortie = true;
        } else if (result.get() == ButtonType.CANCEL) {
            sortie = false;
        }

        return sortie;

    }

    public void information_Box(String title, String message) {
        Alert dg = new Alert(Alert.AlertType.INFORMATION);
        dg.setTitle(title);
        dg.setContentText(message);
        dg.show();
    }

    @FXML
    private void recherche_a(KeyEvent event) {
        ServiceAdmin sa = new ServiceAdmin();
        ServiceUser su = new ServiceUser();
        String result = su.getRole(id_user);

        String id = recherche_a.getText();
        String critere = combo_critere_rech_admin.getValue();

        ObservableList<Admin> list = FXCollections.observableArrayList(sa.rechercherAdmin(id, critere));

        if ("SAdmin".equals(result)) {

            tab_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            email_id.setCellValueFactory(new PropertyValueFactory<>("email"));
            tel_id.setCellValueFactory(new PropertyValueFactory<>("tel"));

        }

        nom_id.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom_id.setCellValueFactory(new PropertyValueFactory<>("prenom"));

        tab_admins.setItems(list);

    }

    @FXML
    private void supprimer_moncompte(ActionEvent event) throws Exception {

        ServiceAdmin sa = new ServiceAdmin();

        String id = id_user;
        boolean Alert_Box = Suppression_Box("Attention", "Vous Etes sur le point de Supprimer Votre Compte,Vous voulez Continuez ? ");

        if (Alert_Box) {
            sa.supprimerAdmin(id);
            load_data();
            log_out();

        }
    }

    @FXML
    private void rme_disable(ActionEvent event) {
        ServiceUser su = new ServiceUser();

        boolean Alert_Box = Suppression_Box("Attention", "Vous Etes sur le point de desactiver l'option se souvenir de moi  ");

        if (Alert_Box) {
            System.out.println(id_user);
            su.updateRme(id_user, "N");
            information_Box("Modification Effectuée", "l'option se souvenir de moi est desactivée");

            rme_disable.setVisible(false);

        }

    }

    private void refresh_picture_name() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/SideBar/AdminMain.fxml"));

        Parent root = loader.load();

        AdminMainController HomeScene = loader.getController();
        HomeScene.id_user = id_user;
        HomeScene.show_data(id_user);
        ActionEvent event = null;
        HomeScene.Profil_Admin(event);
        String css = Aura.class.getResource("Style.css").toExternalForm();
        Stage window = (Stage) menu_user.getScene().getWindow();
        root.getStylesheets().add(css);

        window.setScene(new Scene(root, 1182, 718));

    }

    @FXML
    private void modifier_picture(ActionEvent event) throws IOException, Exception {

        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        String filename = f.getName();
        ServiceUser su = new ServiceUser();
        System.out.println(id_user);

        su.modifierPicture(id_user, filename);

        refresh_picture_name();

    }

    @FXML
    private void activate_sms(ActionEvent event) {
        ServiceUser su = new ServiceUser();
        boolean Alert_Box = Suppression_Box("Attention", "Vous Etes sur le point d'activer l'authentification en deux etapes ? ");

        if (Alert_Box) {
            su.changesms(id_user, "Y");
            load_data();

        }

    }

    @FXML
    private void desctivate_sms(ActionEvent event) {
        ServiceUser su = new ServiceUser();

        boolean Alert_Box = Suppression_Box("Attention", "Vous Etes sur le point de desctiver l'authentification en deux etapes ? ");

        if (Alert_Box) {
            su.changesms(id_user, "N");
            load_data();

        }

    }

    @FXML
    private void liste_des_admins_nav(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/SideBar/AdminMain.fxml"));

        Parent root = loader.load();

        AdminMainController HomeScene = loader.getController();
        HomeScene.liste_admins(id_user);
        HomeScene.id_user = id_user;
        HomeScene.show_data(id_user);
        String css = Aura.class.getResource("Style.css").toExternalForm();

        root.getStylesheets().add(css);

        Stage window = (Stage) liste_des_admins_nav.getScene().getWindow();

        window.setScene(new Scene(root, 1182, 718));

    }

    @FXML
    private void ajouter_admin_nav(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/SideBar/AdminMain.fxml"));

        Parent root = loader.load();

        AdminMainController HomeScene = loader.getController();
        HomeScene.ajouter_admin(id_user);
        HomeScene.id_user = id_user;
        HomeScene.show_data(id_user);
        String css = Aura.class.getResource("Style.css").toExternalForm();

        root.getStylesheets().add(css);

        Stage window = (Stage) ajouter_admin_nav.getScene().getWindow();

        window.setScene(new Scene(root, 1182, 718));
    }

    @FXML
    private void liste_des_coachs_nav(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/SideBar/AdminMain.fxml"));

        Parent root = loader.load();

        AdminMainController HomeScene = loader.getController();
        HomeScene.liste_coachs(id_user);
        HomeScene.id_user = id_user;
        HomeScene.show_data(id_user);
        String css = Aura.class.getResource("Style.css").toExternalForm();

        root.getStylesheets().add(css);

        Stage window = (Stage) liste_des_coachs_nav.getScene().getWindow();

        window.setScene(new Scene(root, 1182, 718));

    }

    @FXML
    private void liste_des_clients_nav(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/SideBar/AdminMain.fxml"));

        Parent root = loader.load();

        AdminMainController HomeScene = loader.getController();
        HomeScene.liste_clients(id_user);
        HomeScene.id_user = id_user;
        HomeScene.show_data(id_user);
        String css = Aura.class.getResource("Style.css").toExternalForm();

        root.getStylesheets().add(css);

        Stage window = (Stage) liste_des_clients_nav.getScene().getWindow();

        window.setScene(new Scene(root, 1182, 718));
    }

    @FXML
    private void mes_informations_nav(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/SideBar/AdminMain.fxml"));

        Parent root = loader.load();

        AdminMainController HomeScene = loader.getController();
        HomeScene.mes_informations(id_user);
        HomeScene.id_user = id_user;
        HomeScene.show_data(id_user);
        String css = Aura.class.getResource("Style.css").toExternalForm();

        root.getStylesheets().add(css);

        Stage window = (Stage) mes_informations_nav.getScene().getWindow();

        window.setScene(new Scene(root, 1182, 718));

    }

}
