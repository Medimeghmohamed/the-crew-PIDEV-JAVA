/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aura;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
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
public class CoachMainController implements Initializable {

    @FXML
    private ScrollPane scroll;
    @FXML
    private VBox views;
    @FXML
    private Button home;
    @FXML
    private TitledPane controls;
    @FXML
    private Button about;
    @FXML
    private BorderPane mainpane;
    @FXML
    private Button about1;
    @FXML
    private Button about2;
    @FXML
    private Button about3;
    @FXML
    private Button about31;

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



    @FXML
    private void pagination(MouseEvent event) throws FileNotFoundException {
           System.out.println("clicked");
        FxmlLoader object =new FxmlLoader();
        Pane view=object.getPage("");
        mainpane.setCenter(view);
    }



    @FXML
    private void textField(MouseEvent event) {
    }




    @FXML
    private void activshow(ActionEvent event) {
     
    }
    
}
