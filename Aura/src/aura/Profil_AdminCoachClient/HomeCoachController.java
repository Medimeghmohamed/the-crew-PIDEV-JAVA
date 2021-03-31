/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aura.Profil_AdminCoachClient;

import Aura.SideBar.CoachMainController;
import Aura.Aura;
import Entities.Coach;
import Service.ServiceAdmin;
import Service.ServiceCoach;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
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
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JFileChooser;

/**
 *
 * @author SeifBS
 */
public class HomeCoachController implements Initializable {

    public String id_user;

    private TableView<Coach> tab_coachs;
    private TableColumn<Coach, String> tab_id;
    private TableColumn<Coach, String> nom_id;
    private TableColumn<Coach, String> prenom_id;
    private TableColumn<Coach, String> email_id;
    private TableColumn<Coach, String> tel_id;
    private TableColumn<Coach, String> specialite_id;

    @FXML
    private TextField nom_cm;
    @FXML
    private TextField prenom_cm;
    @FXML
    private TextField email_cm;
    @FXML
    private TextField tel_cm;
    @FXML
    private PasswordField c_password_cm;
    @FXML
    private PasswordField password_cm;

    @FXML
    private TextField specialite_cm;
    @FXML
    private Label verif_mail_label_cm;
    private Label verif_password_label_cm1;
    private Label verif_password_label_cm2;
    @FXML
    private Label verif_c_password_label_cm;
    @FXML
    private Label verif_tel_label_cm1;
    private TextField recherche_c;
    @FXML
    private Hyperlink modifier_password_visibilite;
    @FXML
    private Label old_password_label;
    @FXML
    private Button modifier_password_button;
    @FXML
    private PasswordField old_password_cm;
    @FXML
    private Label verif_tel_bd_label;
    @FXML
    private Label verif_email_bd_label;
    @FXML
    private Label password_same_label;
    int visibilite_button_modifierPassword_test = 0;
    @FXML
    private MenuItem rme_disable;
    @FXML
    private MenuItem activate_sms;
    @FXML
    private MenuItem desctivate_sms;
    @FXML
    private MenuButton menu_user;
    @FXML
    private Circle picture_user;
    @FXML
    private Button mes_informations_nav;
    @FXML
    private Button liste_des_coachs_nav;
    @FXML
    private Text verif_password_label_cm;

    public void initializeFxml(String id) {
        ServiceCoach sc = new ServiceCoach();
        Coach c = new Coach();

        c = sc.load_data_modify(id);
        load_data_modify_c(c.getId());


        if ("Y".equals(c.getRme())) {
            rme_disable.setVisible(true);
        } else {
            rme_disable.setVisible(false);

        }
        
        picture_user.setStroke(Color.SEAGREEN);
        Image picture=new Image("/Images/Profile/" + c.getPicture(),false);
        picture_user.setFill(new ImagePattern(picture));
        picture_user.setEffect(new DropShadow(+25d,0d,+2d,Color.DARKSEAGREEN));

        load_data();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        visibilite_button_modifierPassword(false);
         Image image=new Image(getClass().getResourceAsStream("/Images/menu.png"));
            menu_user.setGraphic(new ImageView(image));

    }

    private void load_data() {

        ServiceCoach sc = new ServiceCoach();

        Coach c = new Coach();
        c = sc.load_data_modify(id_user);

        if ("Y".equals(c.getSms())) {
            desctivate_sms.setVisible(true);
            activate_sms.setVisible(false);
        } else {
            desctivate_sms.setVisible(false);
            activate_sms.setVisible(true);
        }

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

    

    public void visibilite_button_modifierPassword(boolean test) {
        password_cm.setVisible(test);
        c_password_cm.setVisible(test);
        modifier_password_button.setVisible(test);
        old_password_cm.setVisible(test);
        old_password_label.setVisible(test);
        password_same_label.setVisible(test);
        verif_c_password_label_cm.setVisible(test);
        verif_password_label_cm.setVisible(test);

    }

    @FXML
    private void modifier_coach(ActionEvent event) throws IOException {
        ServiceNotification Notification = new ServiceNotification();
        ServiceCoach sc = new ServiceCoach();
        ServiceUser su = new ServiceUser();

        Coach c = new Coach();
        String id = id_user;
        System.out.println("hey" + id);

        String email = email_cm.getText();
        String nom = nom_cm.getText();
        String prenom = prenom_cm.getText();

        String tel = tel_cm.getText();
        String specialite = specialite_cm.getText();

        if (nom.isEmpty()) {
            alert_Box("Verifier Votre Nom", "Veuillez remplir le champ Nom");

        } else if (prenom.isEmpty()) {
            alert_Box("Verifier Votre Prenom", "Veuillez remplir le champ Prenom");

        } else if (email.isEmpty()) {
            alert_Box("Verifier Votre Adresse E-mail", "Veuillez Remplir Le Champs Mail");

        } else if (tel.isEmpty()) {
            alert_Box("Verifier Votre Numero de Telephone", "Veuillez remplir le champ tel !");

        } else if (specialite.isEmpty()) {
            alert_Box("Verifier Votre Specialite", "Veuillez remplir le champ Specialite !");

        } else if (su.test_Email(email) && !su.verifier_email_bd_modify(email, id) && !su.verifier_tel_bd_modify(tel, id) && su.test_Tel(tel)) {
            c.setId(id);
            c.setNom(nom);
            c.setPrenom(prenom);
            c.setEmail(email);
            c.setTel(tel);
            c.setSpecialite(specialite);
            Coach old_c = sc.load_data_modify(id);

            if (!c.equals(old_c)) {

                sc.modifierCoach(c);
                refresh_picture_name();
                information_Box("Felicitations","Modification Effectuée");


            }
        }
    }

    @FXML
    private void supprimer_coach(ActionEvent event) throws Exception {
        ServiceCoach sc = new ServiceCoach();

        String id = id_user;
        boolean Alert_Box = Suppression_Box("Attention", "Vous Etes sur le point de Supprimer Votre Compte,Vous voulez Continuez ? ");

        if (Alert_Box) {
            sc.supprimerCoach(id);
            load_data();
            log_out();

        }

    }

    private void load_data_modify_c(String id) {
        ServiceCoach sc = new ServiceCoach();

        Coach c = sc.load_data_modify(id);

        nom_cm.setText(c.getNom());
        prenom_cm.setText(c.getPrenom());
        email_cm.setText(c.getEmail());
        tel_cm.setText(c.getTel());
        specialite_cm.setText(c.getSpecialite());

    }

    

    private void log_out() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/Login_CreateAccount/Login.fxml"));

        Parent root = loader.load();

        String css = Aura.class.getResource("Style.css").toExternalForm();
        Stage stage = new Stage();
        Stage window = (Stage) menu_user.getScene().getWindow();
        root.getStylesheets().add(css);
        stage.setTitle("Login");

        window.setScene(new Scene(root, 1182, 718));

    }

    @FXML
    private void verif_mail_c(KeyEvent event) {
        ServiceUser su = new ServiceUser();

        String email = email_cm.getText();
        String id = id_user;

        boolean test = su.verifier_email_bd_modify(email, id);

        if (!su.test_Email(email)) {
            verif_password_label_cm.setText("votre mot de passe doit contenir au minimum 8 caractères\n à savoir : au moins une lettre minuscule,une lettre majuscule et un chiffre ");
        } else {
            verif_password_label_cm.setText("");
        }

        if (test) {
            verif_email_bd_label.setText("L'Email saisi deja existe");
        } else {
            verif_email_bd_label.setText("");

        }

    }

    @FXML
    private void verif_tel_c(KeyEvent event) {

        String tel = tel_cm.getText();
        String id = id_user;

        ServiceUser su = new ServiceUser();

        boolean test = su.test_Tel(tel);
        boolean test_bd = su.verifier_tel_bd_modify(tel, id);

        if (!test) {
            verif_tel_label_cm1.setText("Numero non valide");
        } else {
            verif_tel_label_cm1.setText("");

        }

        if (test_bd) {
            verif_tel_bd_label.setText("Numero existe deja ");
        } else {
            verif_tel_bd_label.setText("");

        }
    }

    @FXML
    private void verif_c_password_c(KeyEvent event) {
        String password = password_cm.getText();
        String c_password = c_password_cm.getText();

        if (!c_password.equals(password)) {
            verif_c_password_label_cm.setText("Verifier Votre Mot De Passe");

        } else {
            verif_c_password_label_cm.setText("");

        }

    }

    @FXML
    private void verif_password_c(KeyEvent event) {

        ServiceUser su = new ServiceUser();

        String password_new = password_cm.getText();
        String password_old = old_password_cm.getText();

        boolean test = su.test_Password(password_new);

        if (!test) {
            verif_password_label_cm1.setText("votre mot de passe doit contenir au minimum 8 caractères");
            verif_password_label_cm2.setText("une lettre minuscule,une lettre majusculeet un chiffre");
        } else {
            verif_password_label_cm1.setText("");
            verif_password_label_cm2.setText("");

        }

        if (password_new.equals(password_old)) {
            password_same_label.setText("Vous avez saisi le meme mot de passe");
        } else {
            password_same_label.setText("");

        }
    }

   

    @FXML
    private void check_old_password(KeyEvent event) {
        String password = old_password_cm.getText();
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
    private void modifier_password(ActionEvent event) {

        ServiceUser su = new ServiceUser();
        ServiceCoach sc = new ServiceCoach();

        String new_password = password_cm.getText();
        String c_password = c_password_cm.getText();
        String id = id_user;
        String old_password = old_password_cm.getText();

        Coach c = new Coach();
        c = sc.load_data_modify(id);

        if (old_password.isEmpty()) {
            alert_Box("Verifier Votre Mot De Passe", "Veuillez remplir tous les champs");
        } else if (new_password.isEmpty() && su.check_password(id, su.crypter_password(old_password))) {
            alert_Box("Verifier Votre Mot De Passe", "Veuillez remplir tous les champs");

        } else if (!c.getPassword().equals(su.crypter_password(new_password)) && c_password.isEmpty() && su.check_password(id, su.crypter_password(old_password)) && su.test_Password(new_password)) {
            alert_Box("Verifier Votre Mot De Passe", "Veuillez remplir tous les champs");

        } else if (c_password.equals(new_password) && su.test_Password(new_password) && su.check_password(id, su.crypter_password(old_password)) && !c.getPassword().equals(su.crypter_password(new_password))) {

            su.modifierPassword(id, su.crypter_password(new_password));
            load_data();
            information_Box("Felicitations", "Votre Mot De Passe est modifié");

            visibilite_button_modifierPassword(false);
            password_cm.setText(null);
            c_password_cm.setText(null);
            old_password_cm.setText(null);
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/SideBar/CoachMain.fxml"));

        Parent root = loader.load();

        CoachMainController HomeScene = loader.getController();
        HomeScene.id_user = id_user;
        HomeScene.show_data(id_user);
        ActionEvent event = null;
        HomeScene.Profil_Coach(event);
        String css = Aura.class.getResource("Style.css").toExternalForm();
        Stage window = (Stage) menu_user.getScene().getWindow();
        root.getStylesheets().add(css);

        window.setScene(new Scene(root, 1182, 718));

    }

    @FXML
    private void modifier_picture(ActionEvent event) throws IOException {

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
    private void mes_informations_nav(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/SideBar/CoachMain.fxml"));

        Parent root = loader.load();

        CoachMainController HomeScene = loader.getController();
        HomeScene.mes_informations(id_user);
        HomeScene.id_user = id_user;
        HomeScene.show_data(id_user);
        String css = Aura.class.getResource("Style.css").toExternalForm();

        root.getStylesheets().add(css);

        Stage window = (Stage) mes_informations_nav.getScene().getWindow();

        window.setScene(new Scene(root, 1182, 718));

    }
   @FXML
    private void liste_des_coachs_nav(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/SideBar/CoachMain.fxml"));

        Parent root = loader.load();

        CoachMainController HomeScene = loader.getController();
        HomeScene.liste_coachs(id_user);
        HomeScene.id_user = id_user;
        HomeScene.show_data(id_user);
        String css = Aura.class.getResource("Style.css").toExternalForm();

        root.getStylesheets().add(css);

        Stage window = (Stage) liste_des_coachs_nav.getScene().getWindow();

        window.setScene(new Scene(root, 1182, 718));

    }
}
