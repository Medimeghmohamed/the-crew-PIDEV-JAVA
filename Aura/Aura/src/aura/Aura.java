/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aura;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Chirine
 */
public class Aura extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
          System.out.println("aura.java");
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));

        Scene scene = new Scene(root);
        String css= Aura.class.getResource("style.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.setTitle("Aura Application");
        stage.show();
        new animatefx.animation.RollIn(root).play();

        //a changer l'adresse mail (récupérer celle du client connecté)
        //JavaMailObjectif.sendMailObj("chirine.nasri@esprit.tn");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
