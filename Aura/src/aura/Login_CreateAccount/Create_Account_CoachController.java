/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aura.Login_CreateAccount;

import Aura.Aura;
import Entities.Coach;
import Service.ServiceCoach;
import Service.ServiceUser;
import com.github.sarxos.webcam.Webcam;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

/**
 * FXML Controller class
 *
 * @author SeifBS
 */
public class Create_Account_CoachController implements Initializable {

    @FXML
    private TextField id_c;
    @FXML
    private TextField nom_c;
    @FXML
    private TextField prenom_c;
    @FXML
    private TextField email_c;
    @FXML
    private PasswordField password_c;
    @FXML
    private PasswordField c_password_c;
    @FXML
    private TextField tel_c;
    @FXML
    private Label Confirmer_MotDePasse_label_c;
    @FXML
    private Label test_mail_label_c;
    @FXML
    private Hyperlink go_to_login;
    @FXML
    private Label verif_id_bd_label_c;
    @FXML
    private Label verif_tel_label_c;
    @FXML
    private TextField specialite_c;
    @FXML
    private Label verif_email_bd_label;
    @FXML
    private Label verif_tel_bd_label;
    private String link_picture_coach = "";
    @FXML
    private Text verif_mdp_label_c;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void go_to_login() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));

        Parent root = loader.load();

        String css = Aura.class.getResource("Style.css").toExternalForm();
        Stage stage = new Stage();
        Stage window = (Stage) go_to_login.getScene().getWindow();
        root.getStylesheets().add(css);
        stage.setTitle("Login");

        window.setScene(new Scene(root, 1182, 718));

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
    private void ajouter_coach(ActionEvent event) throws Exception {
        ServiceUser su = new ServiceUser();
        ServiceCoach sc = new ServiceCoach();

        String id = id_c.getText();
        String tel = tel_c.getText();
        String email = email_c.getText();
        String nom = nom_c.getText();
        String prenom = prenom_c.getText();
        String password = password_c.getText();
        String c_password = c_password_c.getText();
        String specialite = specialite_c.getText();
        String picture = link_picture_coach;

        Coach c = new Coach();
        if (!su.test_Cin(id)) {
            alert_Box("Verifier Votre Cin", "Cin doit contenir exactement 8 entiers !");

        } else if (nom.isEmpty()) {
            alert_Box("Verifier Votre Nom", "Veuillez remplir le champ Nom");

        } else if (prenom.isEmpty()) {
            alert_Box("Verifier Votre Prenom", "Veuillez remplir le champ Prenom");

        } else if (password.isEmpty()) {
            alert_Box("Verifier Votre Mot De Passe", "Veuillez remplir le champ password");

        } else if (c_password.isEmpty()) {
            alert_Box("Verifier Votre Mot De Passe", "Veuillez Confirmer Votre Mot De Passe");

        } else if (email.isEmpty()) {
            alert_Box("Verifier Votre Adresse E-mail", "Veuillez Remplir Le Champs Mail");

        } else if (tel.isEmpty()) {
            alert_Box("Verifier Votre Numero de Telephone", "Veuillez remplir le champ tel !");

        } else if (specialite.isEmpty()) {
            alert_Box("Verifier Votre Specialite", "Veuillez remplir le champ Specialite !");

        } else if (c_password.equals(password) && su.test_Email(email) && !su.verifier_id_bd(id) && su.test_Password(password) && !su.verifier_email_bd(email) && !su.verifier_tel_bd(tel) && su.test_Tel(tel)) {

            c.setId(id);
            c.setNom(nom);
            c.setPrenom(prenom);
            c.setEmail(email);
            c.setTel(tel);
            c.setPassword(su.crypter_password(password));
            c.setSpecialite(specialite);
            if ("".equals(picture)) {
                picture = "default.jpg";
            }
            c.setPicture(picture);

            sc.ajouterCoach(c);
            information_Box("Compte en cours de verification ", "Veuillez Patientez Un Email de confirmation  sera envoyé à votre adresse ");

            go_to_login();

        }

    }

    @FXML
    private void Confirmer_MotDePasse_c(KeyEvent event) {

        String password = password_c.getText();
        String c_password = c_password_c.getText();

        if (!c_password.equals(password)) {
            Confirmer_MotDePasse_label_c.setText("Verifier Votre Mot De Passe");

        } else {
            Confirmer_MotDePasse_label_c.setText("");

        }

    }

    @FXML
    private void test_Mail_c(KeyEvent event) {
        ServiceUser su = new ServiceUser();

        String email = email_c.getText();
        boolean test = su.verifier_email_bd(email);

        if (!su.test_Email(email)) {
            test_mail_label_c.setText("Verifier Votre Adresse Email");
        } else {
            test_mail_label_c.setText("");
        }

        if (test) {
            verif_email_bd_label.setText("L'Email saisi deja existe");
        } else {
            verif_email_bd_label.setText("");

        }

    }

    @FXML
    private void verif_id_bd_c(KeyEvent event) {
        ServiceUser su = new ServiceUser();

        String id = id_c.getText();
        boolean test = su.verifier_id_bd(id);
        if (test) {
            verif_id_bd_label_c.setText("Le Cin Existe Deja !");
        } else {
            verif_id_bd_label_c.setText("");

        }

    }

    @FXML
    private void verif_password_c(KeyEvent event) {

        ServiceUser su = new ServiceUser();

        String password = password_c.getText();
        boolean test = su.test_Password(password);

        if (!test) {
            verif_mdp_label_c.setText("votre mot de passe doit contenir au minimum 8 caractères\n à savoir : au moins une lettre minuscule,une lettre majuscule et un chiffre ");

        } else {
            verif_mdp_label_c.setText("");

        }

    }

    @FXML
    private void verif_tel_c(KeyEvent event) {
        String tel = tel_c.getText();
        ServiceUser su = new ServiceUser();

        boolean test = su.test_Tel(tel);
        boolean test_bd = su.verifier_tel_bd(tel);

        if (!test) {
            verif_tel_label_c.setText(" Numero non valide");
        } else {
            verif_tel_label_c.setText("");

        }

        if (test_bd) {
            verif_tel_bd_label.setText(" numero  existe deja ");
        } else {
            verif_tel_bd_label.setText("");

        }

    }

    @FXML
    private void upload_pic_coach(ActionEvent event) {
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        String filename = f.getName();
        link_picture_coach = filename;
    }

    private String code_random() {

        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString 
        StringBuilder sb = new StringBuilder(4);

        for (int i = 0; i < 4; i++) {

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
    private void take_picture() throws IOException {
        String code_random = code_random();

        Webcam webcam = Webcam.getDefault();
        webcam.open();
        String filename = "";
        filename = code_random + "_" + id_c.getText() + ".jpg";
        ImageIO.write(webcam.getImage(), "JPG", new File("src/Images/Profile/" + filename));
        link_picture_coach = filename;
        webcam.close();

    }

}
