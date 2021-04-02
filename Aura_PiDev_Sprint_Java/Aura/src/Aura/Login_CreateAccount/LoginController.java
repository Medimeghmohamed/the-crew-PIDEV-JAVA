/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aura.Login_CreateAccount;

import Aura.SideBar.CoachMainController;
import Aura.SideBar.ClientMainController;
import Aura.SideBar.AdminMainController;
import Aura.Aura;
import Entities.Admin;
import Entities.Client;
import Entities.Coach;
import Service.SendMail;
import Service.SendSms;
import Service.ServiceAdmin;
import Service.ServiceClient;
import Service.ServiceCoach;
import Service.ServiceUser;
import com.github.sarxos.webcam.Webcam;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 *
 * @author SeifBS
 */
public class LoginController implements Initializable {

    private Button Create;
    @FXML
    private Button Create_Account_Admin;
    @FXML
    private Hyperlink forget_password;
    @FXML
    private Button Se_connecter;
    @FXML
    private TextField id_a_login;
    @FXML
    private PasswordField password_a_login;
    @FXML
    private TextField id_a_fp;
    @FXML
    private TextField email_a_fp;
    @FXML
    private Button code_email;
    @FXML
    private Button change_password;
    @FXML
    private Label confirmer_pass_label;
    @FXML
    private PasswordField password_a_fp;
    @FXML
    private PasswordField c_password_a_fp;
    @FXML
    private Label verif_password_label_a1;
    @FXML
    private Label verif_password_label_a2;
    @FXML
    private Label verif_password_label_a3;
    boolean verification = false;
    @FXML
    private Hyperlink cancel_fp;
    @FXML
    private CheckBox show_password_login;
    @FXML
    private TextField show_password_a_login;
    @FXML
    private CheckBox checkbox_rme;

    private void take_picture() throws IOException {

        Webcam webcam = Webcam.getDefault();
        webcam.open();
        ImageIO.write(webcam.getImage(), "JPG", new File("C:/Users/SeifBS/Documents/test1.jpg"));
        String current = System.getProperty("user.dir");
        System.out.println(current);

        System.setProperty("user.dir", current);

        webcam.close();

    }

    @Override

    public void initialize(URL location, ResourceBundle resources) {

        visibilite_forget_password(false, false);
        show_password_a_login.setVisible(false);

    }

    public void visibilite_forget_password(boolean test, boolean test2) {
        cancel_fp.setVisible(test);
        change_password.setVisible(test2);
        code_email.setVisible(test);
        email_a_fp.setVisible(test);
        id_a_fp.setVisible(test);

        verif_password_label_a1.setText("");
        verif_password_label_a2.setText("");
        verif_password_label_a3.setText("");
        confirmer_pass_label.setText("");
        c_password_a_fp.setVisible(test2);
        password_a_fp.setVisible(test2);

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

    @FXML
    private void Create_Account_Admin(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Create_Account_Client.fxml"));

        Stage stage = new Stage();
        Stage window = (Stage) Create_Account_Admin.getScene().getWindow();
        String css = Aura.class.getResource("Style.css").toExternalForm();
        root.getStylesheets().add(css);
        window.setScene(new Scene(root, 1182, 718));

    }

    public void go_to_home_admin(String id) throws Exception {
        ServiceAdmin sa = new ServiceAdmin();
        id = sa.load_data_modify(id).getId();
        System.out.println(id);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/SideBar/AdminMain.fxml"));

        Parent root = loader.load();

        AdminMainController HomeScene = loader.getController();

        HomeScene.id_user = id;
        HomeScene.iniializeFxml(id);

        HomeScene.show_data(id);
        String css = Aura.class.getResource("Style.css").toExternalForm();
        Stage window = (Stage) Se_connecter.getScene().getWindow();
        root.getStylesheets().add(css);

        window.setScene(new Scene(root, 1182, 718));

    }

    private void go_to_home_client(String id) throws Exception {
        ServiceClient sc = new ServiceClient();
        id = sc.load_data_modify(id).getId();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/SideBar/ClientMain.fxml"));

        Parent root = loader.load();

        ClientMainController HomeScene = loader.getController();
        HomeScene.id_user = id;
        HomeScene.initializeFxml(id);
        HomeScene.show_data(id);
        Stage window = (Stage) Se_connecter.getScene().getWindow();
        String css = Aura.class.getResource("Style.css").toExternalForm();
        root.getStylesheets().add(css);

        window.setScene(new Scene(root, 1182, 718));

    }

    @FXML
    private void forget_password(ActionEvent event) {

        visibilite_forget_password(true, false);

    }

    private void go_to_home_coach(String id) throws Exception {
        ServiceCoach sc = new ServiceCoach();
        id = sc.load_data_modify(id).getId();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/SideBar/CoachMain.fxml"));

        Parent root = loader.load();

        CoachMainController HomeScene = loader.getController();
        HomeScene.id_user = id;
        HomeScene.iniializeFxml(id);

        HomeScene.show_data(id);
        String css = Aura.class.getResource("Style.css").toExternalForm();
        Stage stage = new Stage();
        Stage window = (Stage) Se_connecter.getScene().getWindow();
        root.getStylesheets().add(css);

        window.setScene(new Scene(root, 1182, 718));

    }
    public String cin_verif_rme = "";

    @FXML
    private void cin_verif_rme(KeyEvent event) {
        ServiceAdmin sa = new ServiceAdmin();
        ServiceCoach sc = new ServiceCoach();
        ServiceClient scl = new ServiceClient();
        ServiceUser su = new ServiceUser();

        String id = id_a_login.getText();
        String resultat = su.getRole(id);

        if ("Client".equals(resultat)) {
            Client cl = new Client();
            cl = scl.load_data_modify(id);

            if ("Y".equals(cl.getRme())) {
                password_a_login.setVisible(true);

                show_password_a_login.setVisible(false);

                password_a_login.setText(cl.getPassword());
                password_a_login.setDisable(true);
                show_password_login.setVisible(false);
                cin_verif_rme = "Client";

            }

        } else if ("CoachV".equals(resultat)) {
            Coach c = new Coach();
            c = sc.load_data_modify(id);

            if ("Y".equals(c.getRme())) {
                password_a_login.setVisible(true);

                show_password_a_login.setVisible(false);

                password_a_login.setText(c.getPassword());
                password_a_login.setDisable(true);
                show_password_login.setVisible(false);
                cin_verif_rme = "CoachV";

            }

        } else if ("Admin".equals(resultat) || "SAdmin".equals(resultat)) {

            Admin a = new Admin();
            a = sa.load_data_modify(id);

            if ("Y".equals(a.getRme())) {
                password_a_login.setVisible(true);

                show_password_a_login.setVisible(false);

                password_a_login.setText(a.getPassword());
                password_a_login.setDisable(true);
                show_password_login.setVisible(false);
                cin_verif_rme = "Admin";

            }

        } else {

            password_a_login.setText("");
            show_password_login.setVisible(true);
            password_a_login.setDisable(false);

            if (show_password_login.isSelected()) {
                show_password_a_login.setVisible(true);

                password_a_login.setVisible(false);

            } else {
                password_a_login.setVisible(true);
                show_password_a_login.setVisible(false);

            }

        }

    }

    @FXML
    private void Se_connecter(ActionEvent event) throws Exception {
        boolean verifiaction = false;
        ServiceAdmin sa = new ServiceAdmin();
        ServiceCoach sc = new ServiceCoach();
        ServiceClient scl = new ServiceClient();
        ServiceUser su = new ServiceUser();
        SendMail sm = new SendMail();
        SendSms ss = new SendSms();
        String code_random = "";

        String rme = "";

        if (checkbox_rme.isSelected()) {
            rme = "Y";

        } else {
            rme = "N";
        }

        String id = id_a_login.getText();
        String password = "";

        if (show_password_login.isSelected()) {
            password = show_password_a_login.getText();

        } else {
            password = password_a_login.getText();

        }

        String resultat = su.verifier_data(id, su.crypter_password(password));

        if (id.isEmpty() || password.isEmpty()) {
            alert_Box("Verifier Vos Données", "Veuillez remplir tous les champs!");

        } else if ("CoachNV".equals(resultat)) {

            alert_Box("Veuillez Patientez", "Votre Compte n'est pas encore verifié!");

        } else if ("CoachV".equals(resultat)) {
            Coach c = new Coach();
            c = sc.load_data_modify(id);

            if ("Y".equals(rme) && "N".equals(c.getRme())) {
                su.updateRme(id, rme);
            }
            if ("Y".equals(c.getSms())) {
                code_random = code_random();
                ss.sendSms("Voice Votre Code de Verification :" + code_random, c.getTel());

                //  sm.envoyerMail(c.getEmail(), "Mail Pour Verification", "Voice Votre Code de Verification :" + code_random);
                resultat = affichage_box_code(code_random);

                if ("true".equals(resultat)) {

                    information_Box("Code Correct", "Votre Code est Correct");
                    go_to_home_coach(id);

                } else if (!"close".equals(resultat)) {
                    alert_Box("Code Incorrect", "Vous avez atteint toutes vos tentaives,Ressayez Plus Tard");

                }
            } else {

                go_to_home_coach(id);

            }
        } else if ("Admin".equals(resultat) || "SAdmin".equals(resultat)) {
            Admin a = new Admin();
            a = sa.load_data_modify(id);

            if ("Y".equals(rme) && "N".equals(a.getRme())) {
                su.updateRme(id, rme);
            }

            if ("Y".equals(a.getSms())) {
                code_random = code_random();

                ss.sendSms("Voice Votre Code de Verification :" + code_random, a.getTel());
                //  sm.envoyerMail(a.getEmail(), "Mail Pour Verification", "Voice Votre Code de Verification :" + code_random);
                resultat = affichage_box_code(code_random);

                if ("true".equals(resultat)) {

                    information_Box("Code Correct", "Votre Code est Correct");
                    go_to_home_admin(id);

                } else if (!"close".equals(resultat)) {
                    alert_Box("Code Incorrect", "Vous avez atteint toutes vos tentaives,Ressayez Plus Tard");

                }
            } else {

                go_to_home_admin(id);

            }

        } else if ("Client".equals(resultat)) {
            Client c = new Client();
            c = scl.load_data_modify(id);

            if ("Y".equals(rme) && "N".equals(c.getRme())) {
                su.updateRme(id, rme);
            }

            if ("Y".equals(c.getSms())) {
                code_random = code_random();
                ss.sendSms("Voice Votre Code de Verification :" + code_random, c.getTel());

                // sm.envoyerMail(c.getEmail(), "Mail Pour Verification", "Voice Votre Code de Verification :" + code_random);
                resultat = affichage_box_code(code_random);

                if ("true".equals(resultat)) {

                    information_Box("Code Correct", "Votre Code est Correct Veuillez modifier votre mot de passe");
                    go_to_home_client(id);

                } else if (!"close".equals(resultat)) {
                    alert_Box("Code Incorrect", "Vous avez atteint toutes vos tentaives,Ressayez Plus Tard");

                }
            } else {

                go_to_home_client(id);

            }
        } else if (password_a_login.isDisable()) {
            if (cin_verif_rme == "Admin") {

                Admin a;
                a = sa.load_data_modify(id);

                if ("Y".equals(a.getSms())) {
                    code_random = code_random();

                    ss.sendSms("Voice Votre Code de Verification :" + code_random, a.getTel());
                    //sm.envoyerMail(a.getEmail(), "Mail Pour Verification", "Voice Votre Code de Verification :" + code_random);
                    resultat = affichage_box_code(code_random);

                    if ("true".equals(resultat)) {

                        information_Box("Code Correct", "Votre Code est Correct ");
                        go_to_home_admin(id);

                    } else if (!"close".equals(resultat)) {
                        alert_Box("Code Incorrect", "Vous avez atteint toutes vos tentaives,Ressayez Plus Tard");

                    }
                } else {

                    go_to_home_admin(id);

                }

            } else if (cin_verif_rme == "CoachV") {
                Coach c;
                c = sc.load_data_modify(id);

                if ("Y".equals(c.getSms())) {
                    code_random = code_random();

                    ss.sendSms("Voice Votre Code de Verification :" + code_random, c.getTel());
                    //   sm.envoyerMail(c.getEmail(), "Mail Pour Verification", "Voice Votre Code de Verification :" + code_random);
                    resultat = affichage_box_code(code_random);

                    if ("true".equals(resultat)) {

                        information_Box("Code Correct", "Votre Code est Correct ");
                        go_to_home_coach(id);

                    } else if (!"close".equals(resultat)) {
                        alert_Box("Code Incorrect", "Vous avez atteint toutes vos tentaives,Ressayez Plus Tard");

                    }
                } else {

                    go_to_home_coach(id);

                }

            } else if (cin_verif_rme == "Client") {

                Client cl;
                cl = scl.load_data_modify(id);

                if ("Y".equals(cl.getSms())) {
                    code_random = code_random();
                    ss.sendSms("Voice Votre Code de Verification :" + code_random, cl.getTel());

                    //  sm.envoyerMail(cl.getEmail(), "Mail Pour Verification", "Voice Votre Code de Verification :" + code_random);
                    resultat = affichage_box_code(code_random);

                    if ("true".equals(resultat)) {

                        information_Box("Code Correct", "Votre Code est Correct ");
                        go_to_home_client(id);

                    } else if (!"close".equals(resultat)) {
                        alert_Box("Code Incorrect", "Vous avez atteint toutes vos tentaives,Ressayez Plus Tard");

                    }
                } else {

                    go_to_home_client(id);

                }
            }
        } else {

            alert_Box("Verifier Vos Données", "les informations que vous avez saisies sont incorrectes. vérifiez vos informations et réessayez!");
        }

    }

    public boolean alert_Box_verif_code(String title, String message) throws InterruptedException {

        boolean sortie = false;
        Alert.AlertType Type = Alert.AlertType.WARNING;

        Alert alert = new Alert(Type, "");
        alert.setTitle(title);
        alert.setContentText(message);
        alert.initModality(Modality.APPLICATION_MODAL);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.CANCEL) {
            sortie = false;
        }

        return sortie;

    }

    @FXML
    private void change_password(ActionEvent event) throws Exception {

        ServiceUser su = new ServiceUser();
        String id = id_a_fp.getText();

        String password = password_a_fp.getText();
        String c_password = c_password_a_fp.getText();

        Admin a = new Admin();

        if (this.verification) {

            if (password.isEmpty() || c_password.isEmpty()) {
                alert_Box("Verifier Vos Données", "Veuillez remplir les Champs Mot De Passe!");

            } else if (!su.test_Password(password)) {
                alert_Box("Verifier Votre Mot De Passe", "Votre mot de passe doit contenir au minimum 8 caractères, à savoir : au moins une lettre minuscule,une lettre majusculeet un chiffre");

            } else if (password.isEmpty()) {
                alert_Box("Verifier Votre Mot De Passe", "Veuillez Confirmer Votre Mot De Passe");

            } else if (c_password.isEmpty()) {
                alert_Box("Verifier Votre Mot De Passe", "Veuillez Confirmer Votre Mot De Passe");

            } else if (c_password.equals(password) && su.test_Password(password)) {

                su.modifier_password(id, su.crypter_password(password));

                information_Box("Félicitations", "votre mot de passe est modifié");
                visibilite_forget_password(false, false);

            }

        }

    }

    @FXML
    private void Confirmer_MotDePasse_fp(KeyEvent event) {
        String password = password_a_fp.getText();
        String c_password = c_password_a_fp.getText();

        if (!c_password.equals(password)) {
            confirmer_pass_label.setText("Verifier Votre Mot De Passe");

        } else {
            confirmer_pass_label.setText("");

        }

    }

    @FXML
    private void verif_password_a(KeyEvent event) {

        ServiceUser su = new ServiceUser();

        String password = password_a_fp.getText();
        boolean test = su.test_Password(password);

        if (!test) {
            verif_password_label_a1.setText("votre mot de passe doit contenir au minimum 8 caractères,");
            verif_password_label_a2.setText("à savoir : au moins");
            verif_password_label_a3.setText("une lettre minuscule,une lettre majusculeet un chiffre");
        } else {
            verif_password_label_a1.setText("");
            verif_password_label_a2.setText("");
            verif_password_label_a3.setText("");

        }

    }

    private String affichage_box_code(String code_random) throws InterruptedException {
        int i = 0;
        boolean test = false;

        while (i <= 2 && !test) {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Code Verification");
            dialog.setContentText("Un code de verification est envoyé a votre numero de telephone");
            String code_saisie;

            Optional<String> result = dialog.showAndWait();

            if (result.isPresent()) {

                code_saisie = result.get();

                if (code_saisie.equals(code_random)) {
                    return "true";

                } else if (!code_saisie.equals(code_random) && i < 2) {
                    if (i == 0) {
                        test = true;
                        test = alert_Box_verif_code("Code Incorrect", "Il vous reste deux tentatives");

                    } else if (i == 1) {
                        test = true;

                        test = alert_Box_verif_code("Code Incorrect", "Il vous reste une seule  tentative");
                    }

                    i++;

                } else {

                    return "cancel";
                }

            } else {
                return "close";
            }
        }
        return "cancel";

    }

    private String code_random() {

        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString 
        StringBuilder sb = new StringBuilder(8);

        for (int i = 0; i < 8; i++) {

            // generate a random number between 
            // 0 to AlphaNumericString variable length 
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb 
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();

    }

    @FXML
    private void code_email(ActionEvent event) throws Exception {
        ServiceUser su = new ServiceUser();

        SendMail sm = new SendMail();
        String code_random = "";

        String id = id_a_fp.getText();
        String email = email_a_fp.getText();
        String resultat = "";

        Admin a = new Admin();
        if (id.isEmpty() || email.isEmpty()) {
            alert_Box("Verifier Vos Données", "Veuillez remplir tous les champs!");

        } else if (!su.verifier_id_email_bd(id, email)) {
            alert_Box("Verifier Verifier Vos Données", "les informations que vous avez saisies sont incorrectes. vérifiez vos informations et réessayez");

        } else { //email and id sonts correct
            code_random = code_random();

            sm.envoyerMail(email, "Mail Pour Verification", "Voice Votre Code de Verification :" + code_random);
            resultat = affichage_box_code(code_random);

            if ("true".equals(resultat)) {

                information_Box("Code Correct", "Votre Code est Correct Veuillez modifier votre mot de passe");
                when_code_correct();
                this.verification = true;

            } else if (!"close".equals(resultat)) {
                alert_Box("Code Incorrect", "Vous avez atteint toutes vos tentaives,Ressayez Plus Tard");

                this.verification = false;
                when_code_incorrect_3times();
            }

        }

    }

    @FXML
    private void cancel_fp(ActionEvent event) {
        visibilite_forget_password(false, false);
        id_a_fp.setText("");
        email_a_fp.setText("");
        password_a_fp.setText("");
        c_password_a_fp.setText("");

    }

    private void when_code_correct() {

        change_password.setVisible(true);
        code_email.setVisible(false);
        email_a_fp.setVisible(false);
        id_a_fp.setVisible(false);

        c_password_a_fp.setVisible(true);
        password_a_fp.setVisible(true);
        id_a_fp.setText("");
        email_a_fp.setText("");
        password_a_fp.setText("");
        c_password_a_fp.setText("");
    }

    private void when_code_incorrect_3times() {
        id_a_fp.setText("");
        email_a_fp.setText("");
        password_a_fp.setText("");
        c_password_a_fp.setText("");
        visibilite_forget_password(false, false);

    }

    @FXML
    private void show_password_login(ActionEvent event) {

        if (show_password_login.isSelected()) {
            show_password_a_login.setVisible(true);
            show_password_a_login.setText(password_a_login.getText());

            password_a_login.setVisible(false);

        } else {
            password_a_login.setVisible(true);
            show_password_a_login.setVisible(false);
            password_a_login.setText(show_password_a_login.getText());

        }

    }

}
