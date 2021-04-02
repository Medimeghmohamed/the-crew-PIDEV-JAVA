/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aura.SideBar;

import Aura.Article_Commentaire.ArticlecoachController;
import Aura.Objectif_Suivi.AjouterObjController;
import Aura.Objectif_Suivi.ModifierObjController;
import Aura.Objectif_Suivi.ObjactifGoogleAgendaController;
import Aura.Objectif_Suivi.ObjectifsController;
import Aura.Profil_AdminCoachClient.HomeClientController;
import Aura.Objectif_Suivi.SuiviObjController;
import Aura.Aura;
import Aura.ChallengeClassementgg.Challenge_clientController;
import Aura.ChallengeClassementgg.Classement_clientController;
import Aura.Contact_Entraide.AddEntraideController;
import Aura.reminder.ReminderController;
import Aura.therapyActivity.ActiviteClientController;
import Aura.therapyActivity.ActivityDetailsController;
import Aura.therapyActivity.GridactController;
import Aura.therapyActivity.GridtherapyController;
import Aura.therapyActivity.TheorieClientController;
import Entities.Admin;
import Entities.Client;
import Entities.Coach;
import Service.ServiceClient;
import Service.ServiceCoach;
import Aura.Contact_Entraide.ContactClientController;
import Aura.Contact_Entraide.EntraideClientController;
import Service.ServiceNotification;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import java.io.*;
import com.sun.speech.freetts.*;
import java.text.ParseException;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;

/**
 * FXML Controller class
 *
 * @author SeifBS
 */
public class ClientMainController implements Initializable {

    @FXML
    private BorderPane mainpane;
    @FXML
    private Button suivi;
    @FXML
    private Button Profil_Client;
    public String id_user;
    private ImageView image_test;
    @FXML
    private Label name_client;
    @FXML
    private Circle image_client;
    @FXML
    private Hyperlink log_out;
    @FXML
    private Button btn_objectif;
    private static final String VOICENAME = "kevin16";
    @FXML
    private Button article_Button;
    @FXML
    private Button challenge_button;
    @FXML
    private Button classement_button;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    public void initializeFxml(String id) throws IOException, ParseException {

        load_reminder();

    }
  

    public void show_data(String id) {
        ServiceClient scl = new ServiceClient();

        Client cl = scl.load_data_modify(id);

        image_client.setStroke(Color.SEAGREEN);
        Image picture = new Image("/Images/Profile/" + cl.getPicture(), false);
        image_client.setFill(new ImagePattern(picture));
        image_client.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKSEAGREEN));
        name_client.setText("Bienvenue " + cl.getNom() + " " + cl.getPrenom());

    }

    public void load_reminder() throws IOException, ParseException {
        Pane view;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/reminder/Reminder.fxml"));

        view = loader.load();

        mainpane.setCenter(view);
        ReminderController HomeScene = loader.getController();
        ServiceClient sc = new ServiceClient();
        Client c = sc.load_data_modify(this.id_user);

        HomeScene.id_user = c.getId();
        HomeScene.initializeFxml(c.getId());

        String css = Aura.class.getResource("Style.css").toExternalForm();
        view.getStylesheets().add(css);

    }

    @FXML
    private void dashboard(MouseEvent event) {
    }

    @FXML
    public void Profil_Client(ActionEvent event) throws IOException {

        Pane view;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/Profil_AdminCoachClient/HomeClient.fxml"));

        view = loader.load();

        mainpane.setCenter(view);
        HomeClientController HomeScene = loader.getController();
        ServiceClient sc = new ServiceClient();
        Client c = sc.load_data_modify(this.id_user);

        HomeScene.id_user = c.getId();
        HomeScene.initializeFxml(c.getId());

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
//***************************************************CHIRINE***************************************

    @FXML
    private void btn_objectif(ActionEvent event) throws IOException {
        System.out.println("Objectifs clicked");
        ObservableList<String> affirmations = FXCollections.observableArrayList(
                "En me permettant d’être heureux, j’incite les autres à être heureux aussi.",
                "Le bonheur est mon droit imprescriptible. J’accepte que le bonheur devienne le cœur de mon existence.",
                "J’aime le changement et je m’adapte facilement aux nouvelles situations.",
                "Je m’accepte tel que je suis et je m’aime profondément et complètement.",
                "Je m’accepte pleinement et je sais que je suis digne des meilleures choses dans la vie.",
                "Mon univers est un endroit paisible, aimant et plein de joie.",
                "La paix m’envahit maintenant et pour toujours.",
                "J’envoie de la paix dans le monde.",
                "Je dépasse le stress. Je vis en paix.");
        Random random = new Random();
        int nb;
        nb = random.nextInt(8);
        
        ServiceNotification sn=new ServiceNotification();
        sn.Notification("Affirmation du jour ",affirmations.get(nb));
       
        
       
        Pane view;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/Objectif_Suivi/Objectifs.fxml"));
        view = loader.load();
        mainpane.setCenter(view);
        ObjectifsController HomeScene = loader.getController();
        ServiceClient sc = new ServiceClient();
        Client c = sc.load_data_modify(this.id_user);
        HomeScene.id_user = c.getId();
        HomeScene.initializeFxml(c.getId());
        String css = Aura.class.getResource("Style.css").toExternalForm();
        view.getStylesheets().add(css);
    }

    public void Retour(String id) throws FileNotFoundException, IOException {
        Pane view;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/Objectif_Suivi/Objectifs.fxml"));

        view = loader.load();
        ObjectifsController HomeScene = loader.getController();
        HomeScene.id_user = id;
        HomeScene.initializeFxml(id);

        mainpane.setCenter(view);

        String css = Aura.class.getResource("Style.css").toExternalForm();
        view.getStylesheets().add(css);
    }

    public void AjouterObjectif(String id) throws FileNotFoundException, IOException {
        Pane view;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/Objectif_Suivi/AjouterObj.fxml"));

        view = loader.load();
        AjouterObjController HomeScene = loader.getController();
        HomeScene.id_user = id;
        HomeScene.initializeFxml(id);

        mainpane.setCenter(view);

        String css = Aura.class.getResource("Style.css").toExternalForm();
        view.getStylesheets().add(css);
    }

    public void ModifierObjectif(String id, int idd, String desc, String date, int duree, int rep) throws FileNotFoundException, IOException {
        Pane view;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/Objectif_Suivi/ModifierObj.fxml"));

        view = loader.load();
        ModifierObjController HomeScene = loader.getController();
        HomeScene.id_user = id;
        HomeScene.initializeFxml(id);
        HomeScene.selected_item(idd, desc, date, duree, rep);
        mainpane.setCenter(view);

        String css = Aura.class.getResource("Style.css").toExternalForm();
        view.getStylesheets().add(css);
    }

    public void SuiviObjectif(String id, String desc) throws FileNotFoundException, IOException {
        Pane view;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/Objectif_Suivi/SuiviObj.fxml"));

        view = loader.load();
        SuiviObjController HomeScene = loader.getController();
        HomeScene.id_user = id;
        HomeScene.initializeFxml(id, null);
        mainpane.setCenter(view);

        String css = Aura.class.getResource("Style.css").toExternalForm();
        view.getStylesheets().add(css);
    }

    public void AgendaObjectif(String id) throws FileNotFoundException, IOException {
        Pane view;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/Objectif_Suivi/ObjactifGoogleAgenda.fxml"));

        view = loader.load();
        ObjactifGoogleAgendaController HomeScene = loader.getController();
        HomeScene.id_user = id;

        mainpane.setCenter(view);

        String css = Aura.class.getResource("Style.css").toExternalForm();
        view.getStylesheets().add(css);
    }

    private void btn_afaire(ActionEvent event) throws IOException {
        Pane view;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/reminder/Reminder.fxml"));

        view = loader.load();

        mainpane.setCenter(view);
        ReminderController HomeScene = loader.getController();
       ServiceClient sc = new ServiceClient();
        Client c = sc.load_data_modify(this.id_user);

        HomeScene.id_user = c.getId();
        //HomeScene.initializeFxml(a.getId());

        String css = Aura.class.getResource("Style.css").toExternalForm();
        view.getStylesheets().add(css);
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

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/Article_Commentaire/articlecoach.fxml"));

        view = loader.load();

        mainpane.setCenter(view);
        ArticlecoachController HomeScene = loader.getController();
        ServiceClient sc = new ServiceClient();
        Client c = sc.load_data_modify(this.id_user);

        HomeScene.id_user = c.getId();
        HomeScene.initializeFxml(c.getId());

        String css = Aura.class.getResource("Style.css").toExternalForm();
        view.getStylesheets().add(css);

    }

    @FXML
    private void Activite_Client(ActionEvent event) throws IOException {
        Pane view;
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/therapyActivity/ActiviteClient.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/therapyActivity/Gridact.fxml"));

        view = loader.load();

        mainpane.setCenter(view);
        GridactController HomeScene = loader.getController();
        ServiceClient sc = new ServiceClient();
        Client c = sc.load_data_modify(this.id_user);

        HomeScene.id_user = c.getId();
        HomeScene.initializeFxml(c.getId());

        String css = Aura.class.getResource("Style.css").toExternalForm();
        view.getStylesheets().add(css);

    }

    @FXML
    private void Therapie_Client(ActionEvent event) throws IOException {
        Pane view;
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/therapyActivity/ActiviteClient.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/therapyActivity/Gridtherapy.fxml"));

        view = loader.load();

        mainpane.setCenter(view);
        GridtherapyController HomeScene = loader.getController();
        ServiceClient sc = new ServiceClient();
        Client c = sc.load_data_modify(this.id_user);

        HomeScene.id_user = c.getId();
        HomeScene.initializeFxml(c.getId());

        String css = Aura.class.getResource("Style.css").toExternalForm();
        view.getStylesheets().add(css);
    }

    public void detail_nav(String id_user) throws IOException {
        Pane view;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/therapyActivity/ActivityDetails.fxml"));

        view = loader.load();

        mainpane.setCenter(view);
        ActivityDetailsController HomeScene = loader.getController();
        ServiceClient sc = new ServiceClient();
        Client c = sc.load_data_modify(this.id_user);

        HomeScene.id_user = c.getId();
        HomeScene.initializeFxml(c.getId());

        String css = Aura.class.getResource("Style.css").toExternalForm();
        view.getStylesheets().add(css);
    }

    @FXML
    private void challenge_button(ActionEvent event) throws IOException {

        Pane view;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/ChallengeClassementgg/challenge_client.fxml"));

        view = loader.load();

        mainpane.setCenter(view);
        Challenge_clientController HomeScene = loader.getController();
        ServiceClient sc = new ServiceClient();
        Client c = sc.load_data_modify(this.id_user);

        HomeScene.id_user = c.getId();
        HomeScene.initializeFxml(c.getId());

        String css = Aura.class.getResource("Style.css").toExternalForm();
        view.getStylesheets().add(css);

    }

    @FXML
    private void classement_button(ActionEvent event) throws IOException {

        Pane view;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/ChallengeClassementgg/classement_client.fxml"));

        view = loader.load();

        mainpane.setCenter(view);
        Classement_clientController HomeScene = loader.getController();
        ServiceClient sc = new ServiceClient();
        Client c = sc.load_data_modify(this.id_user);

        HomeScene.id_user = c.getId();
        HomeScene.initializeFxml(c.getId());

        String css = Aura.class.getResource("Style.css").toExternalForm();
        view.getStylesheets().add(css);
    }

    @FXML
    private void entraide_button(ActionEvent event) throws IOException {

        Pane view;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/Contact_Entraide/entraideClient.fxml"));

        view = loader.load();

        mainpane.setCenter(view);
        EntraideClientController HomeScene = loader.getController();
        ServiceClient sc = new ServiceClient();
        Client c = sc.load_data_modify(this.id_user);

        HomeScene.id_user = c.getId();
        HomeScene.initializeFxml(c.getId());

        String css = Aura.class.getResource("Style.css").toExternalForm();
        view.getStylesheets().add(css);

    }

    @FXML
    private void contact_button(ActionEvent event) throws IOException {

        Pane view;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/Contact_Entraide/contactClient.fxml"));

        view = loader.load();

        mainpane.setCenter(view);
        ContactClientController HomeScene = loader.getController();
        ServiceClient sc = new ServiceClient();
        Client c = sc.load_data_modify(this.id_user);

        HomeScene.id_user = c.getId();
        HomeScene.initializeFxml(c.getId());

        String css = Aura.class.getResource("Style.css").toExternalForm();
        view.getStylesheets().add(css);
    }

    @FXML
    private void go_to_home(MouseEvent event) throws IOException, ParseException {
        System.out.println("hey");
        load_reminder();
    }
    
   public void add_question(String id) throws IOException{
       Pane view;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/Contact_Entraide/addEntraide.fxml"));

        view = loader.load();

        mainpane.setCenter(view);
        AddEntraideController HomeScene = loader.getController();
        ServiceClient sc = new ServiceClient();
        Client c = sc.load_data_modify(id);
        

        HomeScene.id_user = c.getId();
        HomeScene.initializeFxml(c.getId());

        String css = Aura.class.getResource("Style.css").toExternalForm();
        view.getStylesheets().add(css);
   }

}
