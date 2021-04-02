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
import Service.ServiceNotification;
import Service.ServiceUser;
import java.io.File;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JFileChooser;

/**
 *
 * @author SeifBS
 */
public class HomeAdminController implements Initializable {

    private Label label;

    @FXML
    private TextField nom_am;
    @FXML
    private TextField prenom_am;
    @FXML
    private TextField email_am;
    @FXML
    private TextField tel_am;

    @FXML
    private TextField password_am;
    @FXML
    private TextField c_password_am;
    private Hyperlink log_out;
    public String id_user;

    @FXML
    private Label verif_email_label_am;
    @FXML
    private Label verif_c_password_am;
    @FXML
    private Label verif_tel_am;

    @FXML
    private Button modifier_password_button;
    @FXML
    private PasswordField old_password_am;
    @FXML
    private Label old_password_label;
    @FXML
    private Label verif_tel_bd_label1;
    @FXML
    private Label verif_email_bd_label1;
    @FXML
    private Label password_same_label;
    @FXML
    private PieChart stats_users;
    int visibilite_button_modifierPassword_test = 0;

    @FXML
    private MenuItem rme_disable;
    @FXML
    private MenuItem activate_sms;
    @FXML
    private MenuItem desctivate_sms;
    @FXML
    private Circle picture_user;
    @FXML
    private MenuButton menu_user;
    private Button liste_des_admins;
    @FXML
    private Button modifier_admin;
    @FXML
    private Hyperlink modifier_password_visibilite;
    @FXML
    private Button ajouter_admin_nav;
    @FXML
    private Button liste_des_coachs_nav;
    @FXML
    private Button liste_des_admins_nav;
    @FXML
    private Button liste_des_clients_nav;
    @FXML
    private Button mes_informations_nav;
    @FXML
    private Text verif_password_label_am;

    public void initializeFxml(String id) {

        ServiceAdmin sa = new ServiceAdmin();
        Admin a = new Admin();

        a = sa.load_data_modify(id);
        load_data_modify_a(a.getId());
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

        load_data();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Image image = new Image(getClass().getResourceAsStream("/Images/menu.png"));
        menu_user.setGraphic(new ImageView(image));

        visibilite_button_modifierPassword(false);

    }

    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    public void alert_Box(String title, String message) {
        Alert dg = new Alert(Alert.AlertType.WARNING);
        dg.setTitle(title);
        dg.setContentText(message);
        dg.show();
    }

    public void information_Box(String title, String message) {
        Alert dg = new Alert(Alert.AlertType.INFORMATION);
        dg.setTitle(title);
        dg.setContentText(message);
        dg.show();
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

    private void stats_users() {
        ServiceUser su = new ServiceUser();
        int admin = su.nb_admins();
        int coach = su.nb_coachsV();
        int client = su.nb_clients();
        int all = client + coach + admin;

        ObservableList<Data> list_stat = FXCollections.observableArrayList(
                new PieChart.Data("Admins: " + (admin * 100) / all + "%", admin),
                new PieChart.Data("Coachs:" + (coach * 100) / all + "%", coach),
                new PieChart.Data("Clients:" + (client * 100) / all + "%", client)
        );
        stats_users.setData(list_stat);

    }

    private void load_data() {

        ServiceAdmin sa = new ServiceAdmin();

        stats_users();

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

    public void visibilite_button_modifierPassword(boolean test) {

        password_am.setVisible(test);
        c_password_am.setVisible(test);
        modifier_password_button.setVisible(test);
        old_password_am.setVisible(test);
        old_password_label.setVisible(test);
        password_same_label.setVisible(test);
        verif_c_password_am.setVisible(test);
        verif_password_label_am.setVisible(test);

    }

    public void Super_Admin_Tab() {
        ServiceAdmin sa = new ServiceAdmin();

        String id = id_user;
        boolean test = sa.verif_super_admin(id);
        if (!test) {

            ajouter_admin_nav.setDisable(true);

        }

    }

    @FXML
    private void modifier_admin(ActionEvent event) throws IOException {
        ServiceNotification Notification = new ServiceNotification();

        ServiceAdmin sa = new ServiceAdmin();
        ServiceUser su = new ServiceUser();

        Admin a = new Admin();
        String tel = tel_am.getText();
        String email = email_am.getText();
        String nom = nom_am.getText();
        String prenom = prenom_am.getText();

        String id = id_user;
        if (nom.isEmpty()) {
            alert_Box("Verifier Votre Nom", "Veuillez remplir le champ Nom");

        } else if (prenom.isEmpty()) {
            alert_Box("Verifier Votre Prenom", "Veuillez remplir le champ Prenom");

        } else if (email.isEmpty()) {
            alert_Box("Verifier Votre Adresse E-mail", "Veuillez Remplir Le Champs Mail");

        } else if (tel.isEmpty()) {
            alert_Box("Verifier Votre Numero de Telephone", "Veuillez remplir le champ tel !");

        } else if (su.test_Email(email) && !su.verifier_email_bd_modify(email, id) && !su.verifier_tel_bd_modify(tel, id) && su.test_Tel(tel)) {
            a.setId(id);
            a.setNom(nom);
            a.setPrenom(prenom);
            a.setEmail(email);
            a.setTel(tel);
            Admin old_a = sa.load_data_modify(id);

            if (!a.equals(old_a)) {
                sa.modifierAdmin(a);
                //  load_data();
                refresh_picture_name();

                Notification.Notification("Felicitations", "Modification Effectuée");
            }

        }
    }

    private void load_data_modify_a(String id) {
        ServiceAdmin sa = new ServiceAdmin();

        Admin a = sa.load_data_modify(id);

        nom_am.setText(a.getNom());
        prenom_am.setText(a.getPrenom());
        email_am.setText(a.getEmail());
        tel_am.setText(a.getTel());

    }

    private void log_out() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/SideBar/Login.fxml"));

        Parent root = loader.load();

        String css = Aura.class.getResource("Style.css").toExternalForm();
        Stage stage = new Stage();
        Stage window = (Stage) menu_user.getScene().getWindow();
        stage.setTitle("Login");

        root.getStylesheets().add(css);

        window.setScene(new Scene(root, 1182, 718));

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
    private void verif_email(KeyEvent event) {
        ServiceUser su = new ServiceUser();
        String id = id_user;
        String email = email_am.getText();
        boolean test = su.verifier_email_bd_modify(email, id);

        if (!su.test_Email(email)) {
            verif_email_label_am.setText("Verifier Votre Adresse Email");
        } else {
            verif_email_label_am.setText("");
        }

        if (test) {
            verif_email_bd_label1.setText("L'Email saisi deja existe");
        } else {
            verif_email_bd_label1.setText("");

        }

    }

    @FXML
    private void verif_tel(KeyEvent event) {
        ServiceUser su = new ServiceUser();

        String tel = tel_am.getText();
        String id = id_user;
        boolean test = su.test_Tel(tel);
        boolean test_bd = su.verifier_tel_bd_modify(tel, id);

        if (!test) {
            verif_tel_am.setText("Numero non valide");
        } else {
            verif_tel_am.setText("");

        }

        if (test_bd) {
            verif_tel_bd_label1.setText("Le numero de telephone existe deja ");
        } else {
            verif_tel_bd_label1.setText("");

        }
    }

    @FXML
    private void verif_c_password(KeyEvent event) {

        String password = password_am.getText();
        String c_password = c_password_am.getText();

        if (!c_password.equals(password)) {
            verif_c_password_am.setText("Verifier Votre Mot De Passe");

        } else {
            verif_c_password_am.setText("");

        }
    }

    @FXML
    private void verif_password(KeyEvent event) {

        ServiceUser su = new ServiceUser();

        String password_new = password_am.getText();
        String password_old = old_password_am.getText();

        boolean test = su.test_Password(password_new);

        if (!test) {

            verif_password_label_am.setText("votre mot de passe doit contenir au minimum 8 caractères\n à savoir : au moins une lettre minuscule,une lettre majuscule et un chiffre ");

        } else {
            verif_password_label_am.setText("");

        }

        if (password_new.equals(password_old)) {
            password_same_label.setText("Vous avez saisi le meme mot de passe");
        } else {
            password_same_label.setText("");

        }
    }

    @FXML
    private void modifier_password(ActionEvent event) {
        ServiceUser su = new ServiceUser();
        ServiceAdmin sa = new ServiceAdmin();

        String new_password = password_am.getText();
        String c_password = c_password_am.getText();
        String id = id_user;
        String old_password = old_password_am.getText();
        Admin a = new Admin();
        a = sa.load_data_modify(id);

        if (old_password.isEmpty()) {
            alert_Box("Verifier Votre Mot De Passe", "Veuillez remplir tous les champs");
        } else if (new_password.isEmpty() && su.check_password(id, su.crypter_password(old_password))) {
            alert_Box("Verifier Votre Mot De Passe", "Veuillez remplir tous les champs");

        } else if (!a.getPassword().equals(su.crypter_password(new_password)) && c_password.isEmpty() && su.check_password(id, su.crypter_password(old_password)) && su.test_Password(new_password)) {
            alert_Box("Verifier Votre Mot De Passe", "Veuillez remplir tous les champs");

        } else if (c_password.equals(new_password) && su.test_Password(new_password) && su.check_password(id, su.crypter_password(old_password)) && !a.getPassword().equals(su.crypter_password(new_password))) {

            su.modifierPassword(id, su.crypter_password(new_password));
            load_data();
            information_Box("Felicitations", "Votre Mot De Passe est modifié");

            visibilite_button_modifierPassword(false);
            password_am.setText(null);
            c_password_am.setText(null);
            old_password_am.setText(null);

        }

    }

    @FXML
    private void modifier_password_visibilite(ActionEvent event) {
        if (this.visibilite_button_modifierPassword_test == 0) {
            visibilite_button_modifierPassword(true);
            this.visibilite_button_modifierPassword_test = 1;
        } else {
            visibilite_button_modifierPassword(false);
            this.visibilite_button_modifierPassword_test = 0;

        }
    }

    @FXML
    private void check_old_password(KeyEvent event) {

        String password = old_password_am.getText();
        String id = id_user;
        ServiceUser su = new ServiceUser();

        boolean test = su.check_password(id, su.crypter_password(password));

        if (!test) {

            old_password_label.setText("Votre Mot De Passe est incorrect ");

        } else {
            old_password_label.setText("");

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
