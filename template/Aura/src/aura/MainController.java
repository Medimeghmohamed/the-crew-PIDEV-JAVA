/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aura;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

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
    private Button Therapie;
    @FXML
    private Button suivi;
    @FXML
    private Button Admin;
    @FXML
    private Button espacearticle;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void dashboard(MouseEvent event) {
    }

   



    private void joinactivite(ActionEvent event) throws FileNotFoundException {
          System.out.println("clicked");
        FxmlLoader object =new FxmlLoader();
        Pane view=object.getPage("ActiviteClient");
        mainpane.setCenter(view);
    }

    @FXML
    private void showact(ActionEvent event) {
    }

    @FXML
    private void gestiontheorie(ActionEvent event) {
    }

    @FXML
    private void BtnObjectifsPred(ActionEvent event) {
        System.out.println("clicked");
        FxmlLoader object =new FxmlLoader();
        Pane view = null;
        try {
            view = object.getPage("Objectif");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        mainpane.setCenter(view);
    }



 
    
}
