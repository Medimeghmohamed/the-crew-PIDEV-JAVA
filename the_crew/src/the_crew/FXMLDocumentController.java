/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package the_crew;

import entities.client;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import service.ServiceClient;
import services.IServiceClient;


/**
 *
 * @author NOUR
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private Button ajouter;
    @FXML
    private Button afficher;
    @FXML
    private TextField tfnom;
    @FXML
    private Label liste;
    @FXML
    private TextField tfprenom;

   /* private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }*/

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void ajouter_personne(ActionEvent event) {
        ServiceClient sc = new ServiceClient();
        client c = new client();
        c.setNom(tfnom.getText());
        c.setPrenom(tfprenom.getText());

        sc.Addclient(c);

    }

    @FXML
    private void afficher_personne(ActionEvent event) {
        ServiceClient sc = new ServiceClient();
        liste.setText(sc.Afficherclient().toString());

}

   
}
