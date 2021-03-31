/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aura.Profil_AdminCoachClient;

import Aura.SideBar.AdminMainController;
import Aura.Aura;
import Entities.Admin;
import Entities.Client;
import Service.SendMail;
import Service.ServiceAdmin;
import Service.ServiceClient;
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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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
public class List_ClientsController implements Initializable {

    @FXML
    private Button envoyer_mail_client;
    @FXML
    private TableView<Client> tab_clients;
    @FXML
    private TableColumn<Client, String> tab_id_cl;
    @FXML
    private TableColumn<Client, String> nom_id_cl;
    @FXML
    private TableColumn<Client, String> prenom_id_cl;
    @FXML
    private TableColumn<Client, String> email_id_cl;
    @FXML
    private TableColumn<Client, String> tel_id_cl;
    @FXML
    private TableColumn<Client, String> adresse_id_cl;
    @FXML
    private TextField object_mail_client;
    @FXML
    private TextArea subject_mail_client;
    @FXML
    private Button envoyer_mail_client0;
    @FXML
    private TextField recherche_client_a;
    @FXML
    private ComboBox<String> combo_critere_rech_client;
    @FXML
    private Hyperlink reduire_mail_client;
    @FXML
    private Button mes_informations_nav;
    @FXML
    private Button liste_des_admins_nav;
    @FXML
    private Button liste_des_coachs_nav;
    @FXML
    private Button liste_des_clients_nav;
    @FXML
    private Button ajouter_admin_nav;
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
    public String id_user = "";

    /**
     * Initializes the controller class.
     */
    public void initializeFxml(String id) {
        ServiceUser su = new ServiceUser();
        String result = su.getRole(id);
        ServiceAdmin sa = new ServiceAdmin();
        Admin a = new Admin();

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

        List<String> list_critere_client = Arrays.asList();

        if (result.equals("SAdmin")) {
            list_critere_client = Arrays.asList("id", "nom", "prenom", "email", "tel", "adresse");

        } else {
            list_critere_client = Arrays.asList("nom", "prenom", "adresse");

        }

        ObservableList<String> list_critere_client1;
        list_critere_client1 = FXCollections.observableArrayList(list_critere_client);

        combo_critere_rech_client.setItems(list_critere_client1);

        load_data();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image image = new Image(getClass().getResourceAsStream("/Images/menu.png"));
        menu_user.setGraphic(new ImageView(image));

        visibilite_mail_client(false);
        envoyer_mail_client0.setVisible(false);
        // TODO
        load_data();

    }

    public void affichage_tab_Clients() {
        ServiceClient scl = new ServiceClient();
        ObservableList<Client> list_client = FXCollections.observableArrayList(scl.afficherClient());
        ServiceUser su = new ServiceUser();
        String result = su.getRole(id_user);

        if ("SAdmin".equals(result)) {
            tab_id_cl.setCellValueFactory(new PropertyValueFactory<>("id"));
            tel_id_cl.setCellValueFactory(new PropertyValueFactory<>("tel"));
            email_id_cl.setCellValueFactory(new PropertyValueFactory<>("email"));

        }

        nom_id_cl.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom_id_cl.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        adresse_id_cl.setCellValueFactory(new PropertyValueFactory<>("adresse"));

        tab_clients.setItems(list_client);

    }

    private void visibilite_mail_client(boolean test) {
        envoyer_mail_client.setVisible(test);
        reduire_mail_client.setVisible(test);
        subject_mail_client.setVisible(test);
        object_mail_client.setVisible(test);

    }

    private void load_data() {

        ServiceAdmin sa = new ServiceAdmin();

        affichage_tab_Clients();

        visibilite_mail_client(false);
        envoyer_mail_client0.setVisible(false);

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
    private void click_tab_clients(MouseEvent event) {

        envoyer_mail_client0.setVisible(true);

    }

    @FXML
    private void envoyer_mail_client(ActionEvent event) {

        ServiceClient sc = new ServiceClient();
        SendMail sm = new SendMail();

        String id = tab_clients.getSelectionModel().getSelectedItem().getId();
        String email = sc.load_data_modify(id).getEmail();

        Client c = sc.load_data_modify(id);
        String object = object_mail_client.getText();
        String subject = subject_mail_client.getText();

        sm.envoyerMail(email, object, subject);
        information_Box("Sucees", "Votre mail est bien recu");
        subject_mail_client.setText(null);
        object_mail_client.setText(null);

        visibilite_mail_client(false);

    }

    @FXML
    private void envoyer_mail_client0(ActionEvent event) {

        visibilite_mail_client(true);

    }

    @FXML
    private void reduire_mail_client(ActionEvent event) {
        visibilite_mail_client(false);

    }

    @FXML
    private void recherche_client_a(KeyEvent event) {
        ServiceUser su = new ServiceUser();
        String result = su.getRole(id_user);

        ServiceClient sc = new ServiceClient();

        String id = recherche_client_a.getText();
        String critere = combo_critere_rech_client.getValue();

        ObservableList<Client> list = FXCollections.observableArrayList(sc.rechercherClient(id, critere));

        if ("SAdmin".equals(result)) {
            tab_id_cl.setCellValueFactory(new PropertyValueFactory<>("id"));
            email_id_cl.setCellValueFactory(new PropertyValueFactory<>("email"));
            tel_id_cl.setCellValueFactory(new PropertyValueFactory<>("tel"));

        }

        nom_id_cl.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom_id_cl.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        adresse_id_cl.setCellValueFactory(new PropertyValueFactory<>("adresse"));

        tab_clients.setItems(list);

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
