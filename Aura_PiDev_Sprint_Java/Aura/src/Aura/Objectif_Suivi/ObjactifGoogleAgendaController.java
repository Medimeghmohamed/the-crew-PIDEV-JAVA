/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aura.Objectif_Suivi;

import Aura.Aura;
import Aura.SideBar.ClientMainController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Chirine
 */
public class ObjactifGoogleAgendaController implements Initializable {

    @FXML
    private WebView webview_Obj;
    @FXML
    private Button retour;
    
   public  String id_user="";
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        WebEngine we = webview_Obj.getEngine();
        we.load("https://accounts.google.com/signin/v2/identifier?service=cl&passive=1209600&osid=1&continue=https%3A%2F%2Fcalendar.google.com%2Fcalendar%2Frender%3Fhl%3Dfr&followup=https%3A%2F%2Fcalendar.google.com%2Fcalendar%2Frender%3Fhl%3Dfr&hl=fr&scc=1&flowName=GlifWebSignIn&flowEntry=ServiceLogin");
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/SideBar/ClientMain.fxml"));
        Parent root = loader.load();
        ClientMainController HomeScene = loader.getController();
        HomeScene.Retour(id_user);
        HomeScene.id_user = id_user;
        HomeScene.show_data(id_user);
        String css = Aura.class.getResource("Style.css").toExternalForm();
        root.getStylesheets().add(css);
        Stage window = (Stage) retour.getScene().getWindow();
        window.setScene(new Scene(root, 1182, 718));
    }

}
