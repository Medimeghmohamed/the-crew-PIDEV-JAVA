/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aura.Profil_AdminCoachClient;

import Aura.SideBar.CoachMainController;
import Aura.SideBar.AdminMainController;
import Aura.Aura;
import Entities.Admin;
import Entities.Coach;
import Service.SendMail;
import Service.ServiceAdmin;
import Service.ServiceCoach;
import Service.ServiceNotification;
import Service.ServicePdf;
import Service.ServiceUser;
import com.itextpdf.text.DocumentException;
import java.io.File;
import java.io.FileNotFoundException;
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
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
public class List_CoachsController implements Initializable {

    public String id_user = "";
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
    @FXML
    private Button supprimer_coach_a;
    @FXML
    private TableView<Coach> tab_coachs_a;
    @FXML
    private TableColumn<Coach, String> tab_id_c;
    @FXML
    private TableColumn<Coach, String> nom_id_c;
    @FXML
    private TableColumn<Coach, String> prenom_id_c;
    @FXML
    private TableColumn<Coach, String> email_id_c;
    @FXML
    private TableColumn<Coach, String> tel_id_c;
    @FXML
    private TableColumn<Coach, String> specialite_id_c;
    @FXML
    private Button accepter_coach_a;
    @FXML
    private ComboBox<String> combobox_tab_coach_etat_a;
    @FXML
    private PieChart stats_coachs;
    @FXML
    private Button mes_informations_nav;

    public void initializeFxml(String id) {

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
        CoachOrAdminNav();
        affichage_tab_Coach_All();

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image image = new Image(getClass().getResourceAsStream("/Images/menu.png"));
        menu_user.setGraphic(new ImageView(image));

        //Combobox coach et leurs etats
        List<String> list_etat = Arrays.asList("Tous Les Coachs", "Les Coachs Verifiés", "Les Coachs Non Verifiés");

        ObservableList<String> list_coach_etat1;
        list_coach_etat1 = FXCollections.observableArrayList(list_etat);

        combobox_tab_coach_etat_a.setItems(list_coach_etat1);
        //Combobox critere recherche adminf

        supprimer_coach_a.setVisible(false);
        accepter_coach_a.setVisible(false);
        load_data();

        // TODO
    }

    private void load_data() {

        ServiceAdmin sa = new ServiceAdmin();

        stats_coachs();

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

    public void CoachOrAdminNav() {
        ServiceUser su = new ServiceUser();

        String result = su.getRole(id_user);
        if (result.equals("CoachV")) {

            ajouter_admin_nav.setVisible(false);
            liste_des_admins_nav.setVisible(false);
            //liste_des_clients_nav.setVisible(false);
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

    public void affichage_tab_Coach_Oui() {
        ServiceUser su = new ServiceUser();
        String result = su.getRole(id_user);
        ServiceCoach sc = new ServiceCoach();
        ObservableList<Coach> list = FXCollections.observableArrayList(sc.afficherCoach_Oui());
        if ("SAdmin".equals(result)) {

            tab_id_c.setCellValueFactory(new PropertyValueFactory<>("id"));
            email_id_c.setCellValueFactory(new PropertyValueFactory<>("email"));
            tel_id_c.setCellValueFactory(new PropertyValueFactory<>("tel"));

        }

        nom_id_c.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom_id_c.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        specialite_id_c.setCellValueFactory(new PropertyValueFactory<>("specialite"));

        tab_coachs_a.setItems(list);

    }

    public void affichage_tab_Coach_Non() {
        ServiceCoach sc = new ServiceCoach();
        ObservableList<Coach> list = FXCollections.observableArrayList(sc.afficherCoach_Non());
        ServiceUser su = new ServiceUser();
        String result = su.getRole(id_user);

        if ("SAdmin".equals(result)) {

            tab_id_c.setCellValueFactory(new PropertyValueFactory<>("id"));
            email_id_c.setCellValueFactory(new PropertyValueFactory<>("email"));
            tel_id_c.setCellValueFactory(new PropertyValueFactory<>("tel"));

        }

        nom_id_c.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom_id_c.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        specialite_id_c.setCellValueFactory(new PropertyValueFactory<>("specialite"));

        tab_coachs_a.setItems(list);

    }

    public void affichage_tab_Coach_All() {
        ServiceUser su = new ServiceUser();
        String result = su.getRole(id_user);
        ServiceCoach sc = new ServiceCoach();
        ObservableList<Coach> list = FXCollections.observableArrayList(sc.afficherCoach_All());

        if ("SAdmin".equals(result)) {

            tab_id_c.setCellValueFactory(new PropertyValueFactory<>("id"));
            email_id_c.setCellValueFactory(new PropertyValueFactory<>("email"));
            tel_id_c.setCellValueFactory(new PropertyValueFactory<>("tel"));

        }

        nom_id_c.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom_id_c.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        specialite_id_c.setCellValueFactory(new PropertyValueFactory<>("specialite"));

        tab_coachs_a.setItems(list);

    }

    private void stats_coachs() {
        ServiceUser su = new ServiceUser();
        int coachnv = su.nb_coachsNV();
        int coachv = su.nb_coachsV();
        int all = coachv + coachnv;

        ObservableList<PieChart.Data> list_stat = FXCollections.observableArrayList(
                new PieChart.Data("Coachs Non Verifiés: " + (coachnv * 100) / all + "%", coachnv),
                new PieChart.Data("Coachs  Verifiés:" + (coachv * 100) / all + "%", coachv)
        );
        stats_coachs.setData(list_stat);

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
        ServiceUser su = new ServiceUser();
        String result = su.getRole(id_user);
        if (result.equals("CoachV")) {

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

        } else {

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
    private void accepter_coach_a(ActionEvent event) {

        ServiceAdmin sa = new ServiceAdmin();
        ServiceCoach sc = new ServiceCoach();
        SendMail sm = new SendMail();

        String id = tab_coachs_a.getSelectionModel().getSelectedItem().getId();

        boolean Alert_Box = Suppression_Box("Attention", "Vous Etes sur le point de Accepter ce coach ? ");

        if (Alert_Box) {
            sa.accepter_coach(id);
            Coach c = new Coach();
            c = sc.load_data_modify(id);
            sm.envoyerMail(c.getEmail(), "Mail De Confirmation", "Felicitation " + c.getNom() + " Votre Compte est verifie,Votre Identifiant est :" + c.getId());

            load_data();
        }

    }

    @FXML
    private void supprimer_coach_a(ActionEvent event) {
        ServiceAdmin sa = new ServiceAdmin();

        String id = tab_coachs_a.getSelectionModel().getSelectedItem().getId();
        boolean Alert_Box = Suppression_Box("Attention", "Vous Etes sur le point de Supprimer un Coach,Vous voulez Continuez ? ");

        if (Alert_Box) {
            sa.rejeter_coach(id);
            load_data();
            affichage_tab_Coach_Oui();
        }

    }

    @FXML
    private void combobox_tab_coach_etat_a(ActionEvent event) {
        String Value;
        Value = combobox_tab_coach_etat_a.getValue();

        if ("Tous Les Coachs".equals(Value)) {

            affichage_tab_Coach_All();

        } else if ("Les Coachs Verifiés".equals(Value)) {
            affichage_tab_Coach_Oui();

        } else if ("Les Coachs Non Verifiés".equals(Value)) {
            affichage_tab_Coach_Non();

        }
    }

    @FXML
    private void click_tab_coachs(MouseEvent event) {
        ServiceUser su = new ServiceUser();
        String result = su.getRole(id_user);
        if (!"CoachV".equals(result)) {

            ServiceCoach sc = new ServiceCoach();
            String id = tab_coachs_a.getSelectionModel().getSelectedItem().getId();

            Coach c = sc.load_data_modify(id);

            if (c.getRole().equals("CoachNV")) {
                supprimer_coach_a.setVisible(false);
                accepter_coach_a.setVisible(true);

            } else if (c.getRole().equals("CoachV")) {

                supprimer_coach_a.setVisible(true);
                accepter_coach_a.setVisible(false);

            } else {

                supprimer_coach_a.setVisible(false);
                accepter_coach_a.setVisible(false);
            }

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
        ServiceUser su = new ServiceUser();
        String result = su.getRole(id_user);
        if ("CoachV".equals(result)) {

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
        } else {

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

    }

    @FXML
    private void liste_des_clients_nav(ActionEvent event) throws IOException {
        ServiceUser su = new ServiceUser();
        String result = su.getRole(id_user);
        if ("CoachV".equals(result)) {
        
        
        
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/SideBar/CoachMain.fxml"));

            Parent root = loader.load();

            CoachMainController HomeScene = loader.getController();
            HomeScene.liste_clients(id_user);
            HomeScene.id_user = id_user;
            HomeScene.show_data(id_user);
            String css = Aura.class.getResource("Style.css").toExternalForm();

            root.getStylesheets().add(css);

            Stage window = (Stage) liste_des_coachs_nav.getScene().getWindow();

            window.setScene(new Scene(root, 1182, 718));
        }
        else{
        
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
    }}

    @FXML
    private void mes_informations_nav(ActionEvent event) throws IOException {
        ServiceUser su = new ServiceUser();
        String result = su.getRole(id_user);
        if ("CoachV".equals(result)) {
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

        } else {
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

    @FXML
    private void pdf_coachs(ActionEvent event)throws FileNotFoundException, DocumentException {
         ServiceNotification Notification = new ServiceNotification();

        boolean test = Suppression_Box("", "Vous etes sur le point de generer un fichier pdf\n Vous voulez continuez ?");
        if (test) {
            ServicePdf scpdf = new ServicePdf();
            scpdf.liste_coachs(id_user);
                Notification.Notification("Pdf Generé ", "Verifier votre repertoire");

        }
    }

}
