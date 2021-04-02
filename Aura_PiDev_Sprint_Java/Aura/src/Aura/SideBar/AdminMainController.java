/*
 * To change this license header, choose License Headers in Project Properties.  
 * and open the template in the editor.
 */
package Aura.SideBar;

import Aura.Article_Commentaire.ArticleAdminController;
import Aura.Article_Commentaire.ArticlecoachController;
import Aura.Article_Commentaire.ComadminController;
import Aura.Article_Commentaire.CommentaireController;
import Aura.Objectif_Suivi.ObjectifPredefiniController;
import Aura.Profil_AdminCoachClient.List_CoachsController;
import Aura.Profil_AdminCoachClient.List_AdminsController;
import Aura.Profil_AdminCoachClient.Ajouter_AdminController;
import Aura.Profil_AdminCoachClient.List_ClientsController;
import Aura.Profil_AdminCoachClient.HomeAdminController;
import Aura.Aura;
import Aura.ChallengeClassementgg.Challenge_adminController;
import Aura.ChallengeClassementgg.Classement_adminController;
import Aura.ChallengeClassementgg.Classment__coachController;
import Aura.ChallengeClassementgg.Niveau_adminController;
import Aura.therapyActivity.AddAdActController;
import Aura.therapyActivity.ModifyController;
import Aura.therapyActivity.PropoAdTherapieController;
import Aura.therapyActivity.PropoadactController;
import Aura.therapyActivity.ShowadactController;
import Aura.therapyActivity.ShowadtTherapieController;
import Aura.therapyActivity.TherapieAdADDController;
import Entities.Admin;
import Service.ServiceAdmin;
import Aura.Contact_Entraide.ContactAdminController;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
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
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Medimegh
 */
public class AdminMainController implements Initializable {

    @FXML
    private BorderPane mainpane;
    public String id_user;
    @FXML
    private Button Profil_Admin;
    @FXML
    private Circle image_admin;
    @FXML
    private Label name_admin;
    @FXML
    private Hyperlink log_out;
    @FXML
    private Button mes_objectifs;
    @FXML
    private Button objectif_button;
    @FXML
    private Button article_Button;
    @FXML
    private Button activite_button_main;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void iniializeFxml(String id) throws IOException {

        home_button_objectif();

    }

    public void show_data(String id) {
        id_user = id;
        ServiceAdmin sa = new ServiceAdmin();

        Admin a = sa.load_data_modify(id);
        image_admin.setStroke(Color.SEAGREEN);
        Image picture = new Image("/Images/Profile/" + a.getPicture(), false);
        image_admin.setFill(new ImagePattern(picture));
        image_admin.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKSEAGREEN));

        name_admin.setText(a.getNom() + " " + a.getPrenom());
    }

    @FXML
    private void dashboard(MouseEvent event) {
    }

    @FXML
    public void Profil_Admin(ActionEvent event) throws FileNotFoundException, IOException {
        Pane view;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/Profil_AdminCoachClient/HomeAdmin.fxml"));

        view = loader.load();

        mainpane.setCenter(view);
        HomeAdminController HomeScene = loader.getController();

        ServiceAdmin sc = new ServiceAdmin();
        Admin a = sc.load_data_modify(this.id_user);

        HomeScene.id_user = a.getId();
        HomeScene.initializeFxml(a.getId());

        String css = Aura.class.getResource("Style.css").toExternalForm();
        view.getStylesheets().add(css);

    }

    private void log_out() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/Login_CreateAccount/Login.fxml"));

        Parent root = loader.load();

        String css = Aura.class.getResource("Style.css").toExternalForm();
        Stage stage = new Stage();
        Stage window = (Stage) log_out.getScene().getWindow();
        stage.setTitle("Login");

        root.getStylesheets().add(css);

        window.setScene(new Scene(root, 1182, 718));
    }

    public void liste_admins(String id) throws FileNotFoundException, IOException {
        Pane view;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/Profil_AdminCoachClient/List_Admins.fxml"));

        view = loader.load();
        List_AdminsController HomeScene = loader.getController();
        HomeScene.id_user = id;
        HomeScene.initializeFxml(id);

        mainpane.setCenter(view);

        String css = Aura.class.getResource("Style.css").toExternalForm();
        view.getStylesheets().add(css);
    }

    public void liste_coachs(String id) throws FileNotFoundException, IOException {
        Pane view;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/Profil_AdminCoachClient/List_Coachs.fxml"));

        view = loader.load();
        List_CoachsController HomeScene = loader.getController();
        HomeScene.id_user = id;
        HomeScene.initializeFxml(id);

        mainpane.setCenter(view);

        String css = Aura.class.getResource("Style.css").toExternalForm();
        view.getStylesheets().add(css);
    }

    //Exdmple taba3 bih 
    public void mes_informations(String id) throws FileNotFoundException, IOException {
        Pane view;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/Profil_AdminCoachClient/HomeAdmin.fxml"));

        view = loader.load();
        HomeAdminController HomeScene = loader.getController();
        HomeScene.id_user = id;
        HomeScene.initializeFxml(id);

        mainpane.setCenter(view);

        String css = Aura.class.getResource("Style.css").toExternalForm();
        view.getStylesheets().add(css);
    }

    public void liste_clients(String id) throws FileNotFoundException, IOException {
        Pane view;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/Profil_AdminCoachClient/List_Clients.fxml"));

        view = loader.load();
        List_ClientsController HomeScene = loader.getController();
        HomeScene.id_user = id;
        HomeScene.initializeFxml(id);

        mainpane.setCenter(view);

        String css = Aura.class.getResource("Style.css").toExternalForm();
        view.getStylesheets().add(css);
    }

    public void ajouter_admin(String id) throws FileNotFoundException, IOException {
        Pane view;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/Profil_AdminCoachClient/Ajouter_Admin.fxml"));

        view = loader.load();
        Ajouter_AdminController HomeScene = loader.getController();
        HomeScene.id_user = id;
        HomeScene.initializeFxml(id);

        mainpane.setCenter(view);

        String css = Aura.class.getResource("Style.css").toExternalForm();
        view.getStylesheets().add(css);
    }

    public void returnPane(Pane view) {
        mainpane.setCenter(view);
    }

    
    
    public void home_button_objectif() throws IOException{
        Pane view;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/Objectif_Suivi/ObjectifPredefini.fxml"));

        view = loader.load();

        mainpane.setCenter(view);
        ObjectifPredefiniController HomeScene = loader.getController();

        ServiceAdmin sc = new ServiceAdmin();
        Admin a = sc.load_data_modify(this.id_user);

        HomeScene.id_user = a.getId();
        HomeScene.initializeFxml(a.getId());

        String css = Aura.class.getResource("Style.css").toExternalForm();
        view.getStylesheets().add(css);
    
    }
    
    @FXML
    public void objectif_button(ActionEvent event) throws IOException {

        home_button_objectif();
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

    @FXML
    private void log_out_button(ActionEvent event) throws IOException {

        boolean Alert_Box = Suppression_Box("Attention", "Vous Etes sur le point de Se Deconnecter,Vous voulez Continuez ? ");

        if (Alert_Box) {

            log_out();

        }
    }

    @FXML
    private void article_Button(ActionEvent event) throws IOException {

        Pane view;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/Article_Commentaire/ArticleAdmin.fxml"));

        view = loader.load();

        mainpane.setCenter(view);
        ArticleAdminController HomeScene = loader.getController();

        ServiceAdmin sc = new ServiceAdmin();
        Admin a = sc.load_data_modify(this.id_user);

        HomeScene.id_user = a.getId();
        HomeScene.initializeFxml(a.getId());

        String css = Aura.class.getResource("Style.css").toExternalForm();
        view.getStylesheets().add(css);

    }

    public void Voir_articles_button(String id) throws IOException {
        Pane view;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/Article_Commentaire/comadmin.fxml"));

        view = loader.load();
        ComadminController HomeScene = loader.getController();
        HomeScene.id_user = id;
        HomeScene.initializeFxml(id);

        mainpane.setCenter(view);

        String css = Aura.class.getResource("Style.css").toExternalForm();
        view.getStylesheets().add(css);

    }

    @FXML
    private void activite_button_main(ActionEvent event) throws IOException {

        Pane view;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/therapyActivity/showadact.fxml"));

        view = loader.load();

        mainpane.setCenter(view);
        ShowadactController HomeScene = loader.getController();

        ServiceAdmin sc = new ServiceAdmin();
        Admin a = sc.load_data_modify(this.id_user);

        HomeScene.id_user = a.getId();
        HomeScene.initializeFxml(a.getId());

        String css = Aura.class.getResource("Style.css").toExternalForm();
        view.getStylesheets().add(css);

    }

    public void proposition_nav(String id) throws IOException {

        Pane view;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/therapyActivity/propoadact.fxml"));

        view = loader.load();
        PropoadactController HomeScene = loader.getController();
        HomeScene.id_user = id;
        HomeScene.initializeFxml(id);

        mainpane.setCenter(view);

        String css = Aura.class.getResource("Style.css").toExternalForm();
        view.getStylesheets().add(css);

    }

    public void AddAct_nav(String id) throws IOException {

        Pane view;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/therapyActivity/addAdAct.fxml"));

        view = loader.load();
        AddAdActController HomeScene = loader.getController();
        HomeScene.id_user = id;
        HomeScene.initializeFxml(id);

        mainpane.setCenter(view);

        String css = Aura.class.getResource("Style.css").toExternalForm();
        view.getStylesheets().add(css);

    }

    public void showactad_nav(String id) throws IOException {

        Pane view;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/therapyActivity/showadact.fxml"));

        view = loader.load();
        ShowadactController HomeScene = loader.getController();
        HomeScene.id_user = id;
        HomeScene.initializeFxml(id);

        mainpane.setCenter(view);

        String css = Aura.class.getResource("Style.css").toExternalForm();
        view.getStylesheets().add(css);
    }

    @FXML
    private void therapie_button_main(ActionEvent event) throws IOException {
        Pane view;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/therapyActivity/showadtTherapie.fxml"));

        view = loader.load();

        mainpane.setCenter(view);
        ShowadtTherapieController HomeScene = loader.getController();

        ServiceAdmin sc = new ServiceAdmin();
        Admin a = sc.load_data_modify(this.id_user);

        HomeScene.id_user = a.getId();
        HomeScene.initializeFxml(a.getId());

        String css = Aura.class.getResource("Style.css").toExternalForm();
        view.getStylesheets().add(css);
    }

    public void showthad_nav(String id) throws IOException {

        Pane view;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/therapyActivity/showadtTherapie.fxml"));

        view = loader.load();
        ShowadtTherapieController HomeScene = loader.getController();
        HomeScene.id_user = id;
        HomeScene.initializeFxml(id);

        mainpane.setCenter(view);

        String css = Aura.class.getResource("Style.css").toExternalForm();
        view.getStylesheets().add(css);
    }

    public void propositionth_nav(String id) throws IOException {

        Pane view;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/therapyActivity/propoAdTherapie.fxml"));

        view = loader.load();
        PropoAdTherapieController HomeScene = loader.getController();
        HomeScene.id_user = id;
        HomeScene.initializeFxml(id);

        mainpane.setCenter(view);

        String css = Aura.class.getResource("Style.css").toExternalForm();
        view.getStylesheets().add(css);
    }

    public void addth_nav(String id) throws IOException {

        Pane view;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/therapyActivity/TherapieAdADD.fxml"));

        view = loader.load();
        TherapieAdADDController HomeScene = loader.getController();
        HomeScene.id_user = id;
        HomeScene.initializeFxml(id);

        mainpane.setCenter(view);

        String css = Aura.class.getResource("Style.css").toExternalForm();
        view.getStylesheets().add(css);
    }

    public void modifieract_nav(String id) throws IOException {

        Pane view;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/therapyActivity/modify.fxml"));

        view = loader.load();
        ModifyController HomeScene = loader.getController();
        HomeScene.id_user = id;
        HomeScene.initializeFxml(id);

        mainpane.setCenter(view);

        String css = Aura.class.getResource("Style.css").toExternalForm();
        view.getStylesheets().add(css);
    }

    @FXML
    private void challenge_button(ActionEvent event) throws IOException {

        Pane view;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/ChallengeClassementgg/challenge_admin.fxml"));

        view = loader.load();

        mainpane.setCenter(view);
        Challenge_adminController HomeScene = loader.getController();

        ServiceAdmin sc = new ServiceAdmin();
        Admin a = sc.load_data_modify(this.id_user);

        HomeScene.id_user = a.getId();
        HomeScene.initializeFxml(a.getId());

        String css = Aura.class.getResource("Style.css").toExternalForm();
        view.getStylesheets().add(css);

    }

    @FXML
    private void classement_button(ActionEvent event) throws IOException {

        Pane view;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/ChallengeClassementgg/classement_admin.fxml"));

        view = loader.load();

        mainpane.setCenter(view);
        Classement_adminController HomeScene = loader.getController();

        ServiceAdmin sc = new ServiceAdmin();
        Admin a = sc.load_data_modify(this.id_user);

        HomeScene.id_user = a.getId();
        HomeScene.initializeFxml(a.getId());

        String css = Aura.class.getResource("Style.css").toExternalForm();
        view.getStylesheets().add(css);

    }

    @FXML
    private void niveau_button(ActionEvent event) throws IOException {
        Pane view;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/ChallengeClassementgg/niveau_admin.fxml"));

        view = loader.load();

        mainpane.setCenter(view);
        Niveau_adminController HomeScene = loader.getController();

        ServiceAdmin sc = new ServiceAdmin();
        Admin a = sc.load_data_modify(this.id_user);

        HomeScene.id_user = a.getId();
        HomeScene.initializeFxml(a.getId());

        String css = Aura.class.getResource("Style.css").toExternalForm();
        view.getStylesheets().add(css);

    }

    @FXML
    private void contact_button(ActionEvent event) throws IOException {

        Pane view;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/Contact_Entraide/contactAdmin.fxml"));

        view = loader.load();

        mainpane.setCenter(view);
        ContactAdminController HomeScene = loader.getController();
        ServiceAdmin sc = new ServiceAdmin();
        Admin a = sc.load_data_modify(this.id_user);

        HomeScene.id_user = a.getId();
        HomeScene.initializeFxml(a.getId());

        String css = Aura.class.getResource("Style.css").toExternalForm();
        view.getStylesheets().add(css);

    }
}
