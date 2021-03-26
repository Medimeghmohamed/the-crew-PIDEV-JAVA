/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aura;

import Entities.Objectif;
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
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Medimegh
 */
public class MainController implements Initializable {

    @FXML
    private ScrollPane scroll;
    @FXML
    private VBox views;
    @FXML
    private Button home;
    @FXML
    private BorderPane mainpane;
    @FXML
    private Button about324;
    @FXML
    private Button suivi;
    @FXML
    private Button Admin;
    @FXML
    private Button challenge;
    @FXML
    private Button classement;
    @FXML
    private Button client;
    @FXML
    private Button objectifsprédéfinis;
    @FXML
    private Button Article;
    @FXML
    private Button objectifs;
    @FXML
    private Button Bilan;
    @FXML
    private AnchorPane AnchorPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("main controller");
    }

    @FXML
    private void dashboard(MouseEvent event) {
    }

    private void joinactivite(ActionEvent event) throws FileNotFoundException {
        System.out.println("join act clicked");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("ActiviteClient");
        mainpane.setCenter(view);
    }

    @FXML
    private void showact(ActionEvent event) {
    }
//*******************************************chirine**********************************************************

    @FXML
    private void BtnObjectifsPred(ActionEvent event) {
        System.out.println("Objectif predefini clicked");
        FxmlLoader object = new FxmlLoader();
        Pane view = null;
        try {
            view = object.getPage("ObjectifPredefini");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        mainpane.setCenter(view);
    }

    @FXML
    private void BtnSuivi(ActionEvent event) {
        System.out.println("Suivi clicked");
        FxmlLoader object = new FxmlLoader();
        Pane view = null;
        try {
            view = object.getPage("Suivi");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        mainpane.setCenter(view);
    }

    @FXML
    private void BtnObjectifs(ActionEvent event) {
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
         Notifications notificationBuilder= Notifications.create()
                    .title("Affirmation du jour")
                    .text("N'oubliez pas : "+ affirmations.get(nb))
                    .graphic(null)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.BOTTOM_RIGHT)
                    .onAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent event){
                        System.out.println("clickedd handle");
                    }
                    });
        notificationBuilder.showInformation();
        FxmlLoader object = new FxmlLoader();
        Pane view = null;
        try {
            view = object.getPage("Objectifs");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        mainpane.setCenter(view);
    }

    @FXML
    private void BtnBilan(ActionEvent event) {
        System.out.println("bilan clicked");
        FxmlLoader object = new FxmlLoader();
        Pane view = null;
        try {
            view = object.getPage("Bilan");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        mainpane.setCenter(view);
    }
    public void test_button(ActionEvent event, String page) throws FileNotFoundException, IOException {
//        System.out.println("hey");
//        Pane view;
//        FXMLLoader loader = new FXMLLoader(getClass().getResource(page));
//        view = loader.load();
//        mainpane.setCenter(view);
//        if (page.equals("ModifierObj.fxml")) {
//            ModifierObjController mo = loader.getController();
//            mo.selected_item(this.selected_item(pri));
//        }

}
    public void returnPane(Pane view){
        mainpane.setCenter(view);
    }
}
