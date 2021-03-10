/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package the_crew;

import entities.client;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import service.ServiceClient;
import service.ServiceMail;
import services.IServiceClient;
import utils.Myconnexion;


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
    @FXML
    private Button mail;

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

    @FXML
    private void sendmail(ActionEvent event) {
      //   Activites t=tabact1.getSelectionModel().getSelectedItem();
       //  String n=t.getIdcoach();
       String n =tfnom.getText();
        System.out.println("1");
        try {
             String requete = "SELECT mail FROM client  WHERE nom='"+n+"'";
             System.out.println("2");
            Statement st = Myconnexion.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(requete);
            System.out.println("3");
            while (rs.next()) {
              rs.getString("mail"); 
              
              System.out.println("4");
                 ServiceMail mai =new ServiceMail();
        System.out.println( rs.getString("mail"));
         System.out.println("5");
      String reaso= tfprenom.getText();
        System.out.println(reaso);
         System.out.println("6");
            mai.send_mail( rs.getString("mail"),reaso);
             System.out.println("7");
            }
                 
         }catch (Exception e) {
        }
    }

   
}
