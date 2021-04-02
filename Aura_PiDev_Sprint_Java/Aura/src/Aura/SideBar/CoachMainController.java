/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aura.SideBar;

import Aura.Article_Commentaire.ArticlecoachController;
import Aura.Article_Commentaire.CommentairecoachController;
import Aura.Profil_AdminCoachClient.List_CoachsController;
import Aura.Profil_AdminCoachClient.HomeCoachController;
import Aura.Aura;
import Aura.ChallengeClassementgg.Challenge_adminController;
import Aura.ChallengeClassementgg.Challenge_coachController;
import Aura.ChallengeClassementgg.Classment__coachController;
import Aura.Contact_Entraide.AddEntraideController;
import Aura.Contact_Entraide.ContactClientController;
import Aura.Contact_Entraide.EntraideClientController;
import Aura.Profil_AdminCoachClient.List_ClientsController;
import Aura.therapyActivity.ActiviteCoachController;
import Aura.therapyActivity.TherapieCoachController;
import Entities.Coach;
import Service.ServiceCoach;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
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
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Medimegh
 */
public class CoachMainController implements Initializable {

    @FXML
    private Button home;
    @FXML
    private Button about;
    @FXML
    private BorderPane mainpane;
    public String id_user;
    @FXML
    private Button Profil_Coach;
    private ImageView image_test;
    @FXML
    private Label name_coach;
    @FXML
    private Circle image_coach;
    @FXML
    private Hyperlink log_out;
    @FXML
    private Button article_Button;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
      public void iniializeFxml(String id) throws IOException
    {
        home_button_activite();

    
    
    }

    @FXML
    private void dashboard(MouseEvent event) {
    }


    @FXML
    public void Profil_Coach(ActionEvent event) throws IOException {

        Pane view;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/Profil_AdminCoachClient/HomeCoach.fxml"));

        view = loader.load();

        mainpane.setCenter(view);
        HomeCoachController HomeScene = loader.getController();
        ServiceCoach sc = new ServiceCoach();
        Coach c = sc.load_data_modify(this.id_user);

        HomeScene.id_user = c.getId();
        HomeScene.initializeFxml(c.getId());

        String css = Aura.class.getResource("Style.css").toExternalForm();
        view.getStylesheets().add(css);
    }

    public void show_data(String id) {
        ServiceCoach scl = new ServiceCoach();

        Coach c = scl.load_data_modify(id);

        image_coach.setStroke(Color.SEAGREEN);
        Image picture = new Image("/Images/Profile/" + c.getPicture(), false);
        image_coach.setFill(new ImagePattern(picture));
        image_coach.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKSEAGREEN));

        name_coach.setText("Bienvenue " + c.getNom() + " " + c.getPrenom());
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


    public void mes_informations(String id) throws FileNotFoundException, IOException {
        Pane view;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/Profil_AdminCoachClient/HomeCoach.fxml"));

        view = loader.load();
        HomeCoachController HomeScene = loader.getController();
        HomeScene.id_user = id;
        HomeScene.initializeFxml(id);

        mainpane.setCenter(view);

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
        ServiceCoach sc = new ServiceCoach();
        Coach c = sc.load_data_modify(this.id_user);

        HomeScene.id_user = c.getId();
        HomeScene.initializeFxml(c.getId());

        String css = Aura.class.getResource("Style.css").toExternalForm();
        view.getStylesheets().add(css);

    }
    public void  Voir_articles_button(String id) throws IOException
    {
    
    Pane view;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/Article_Commentaire/commentairecoach.fxml"));

        view = loader.load();
        CommentairecoachController HomeScene = loader.getController();
        HomeScene.id_user = id;
        HomeScene.initializeFxml(id);

        mainpane.setCenter(view);

        String css = Aura.class.getResource("Style.css").toExternalForm();
        view.getStylesheets().add(css);
    }
    
    public void  home_button_activite() throws IOException{
                
                 Pane view;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/therapyActivity/ActiviteCoach.fxml"));

        view = loader.load();

        mainpane.setCenter(view);
      ActiviteCoachController HomeScene = loader.getController();
        ServiceCoach sc = new ServiceCoach();
        Coach c = sc.load_data_modify(this.id_user);

        HomeScene.id_user = c.getId();
        HomeScene.initializeFxml(c.getId());

        String css = Aura.class.getResource("Style.css").toExternalForm();
        view.getStylesheets().add(css);
                
                }


    @FXML
    private void Activite_Coach(ActionEvent event) throws IOException {
        home_button_activite();
    }

    @FXML
    private void Therapie_Coach(ActionEvent event) throws IOException {
         Pane view;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/therapyActivity/TherapieCoach.fxml"));

        view = loader.load();

        mainpane.setCenter(view);
      TherapieCoachController HomeScene = loader.getController();
        ServiceCoach sc = new ServiceCoach();
        Coach c = sc.load_data_modify(this.id_user);

        HomeScene.id_user = c.getId();
        HomeScene.initializeFxml(c.getId());

        String css = Aura.class.getResource("Style.css").toExternalForm();
        view.getStylesheets().add(css);
    }

    @FXML
    private void challenge_button(ActionEvent event) throws IOException {
          Pane view;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/ChallengeClassementgg/challenge_coach.fxml"));

        view = loader.load();

        mainpane.setCenter(view);
        Challenge_coachController HomeScene = loader.getController();
        ServiceCoach sc = new ServiceCoach();
        Coach c = sc.load_data_modify(this.id_user);

        HomeScene.id_user = c.getId();
        HomeScene.initializeFxml(c.getId());

        String css = Aura.class.getResource("Style.css").toExternalForm();
        view.getStylesheets().add(css);
        
        
    
    }

    @FXML
    private void classement_button(ActionEvent event) throws IOException {
          Pane view;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/ChallengeClassementgg/classment__coach.fxml"));

        view = loader.load();

        mainpane.setCenter(view);
        Classment__coachController HomeScene = loader.getController();
        ServiceCoach sc = new ServiceCoach();
        Coach c = sc.load_data_modify(this.id_user);

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
        ServiceCoach sc = new ServiceCoach();
        Coach c = sc.load_data_modify(this.id_user);

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
        ServiceCoach sc = new ServiceCoach();
        Coach c = sc.load_data_modify(this.id_user);

        HomeScene.id_user = c.getId();
        HomeScene.initializeFxml(c.getId());

        String css = Aura.class.getResource("Style.css").toExternalForm();
        view.getStylesheets().add(css);
    
    
    
    }
    
    
    public void add_question(String id) throws IOException{
       Pane view;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/Contact_Entraide/addEntraide.fxml"));

        view = loader.load();

        mainpane.setCenter(view);
        AddEntraideController HomeScene = loader.getController();
        ServiceCoach sc = new ServiceCoach();
        Coach c = sc.load_data_modify(id);
        

        HomeScene.id_user = c.getId();
        HomeScene.initializeFxml(c.getId());

        String css = Aura.class.getResource("Style.css").toExternalForm();
        view.getStylesheets().add(css);
   }

}
