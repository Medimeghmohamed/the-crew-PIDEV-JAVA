/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aura.therapyActivity;

import Entities.Activites;
import Entities.Client;
import Service.ServiceClient;
import Service.serviceActivites;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class ActivityDetailsController implements Initializable {

    
  
    @FXML
    private Label location;
    @FXML
    private ImageView eventImg;
   
    @FXML
    private FontAwesomeIconView updateIcon;
    @FXML
    private FontAwesomeIconView deleteIcon;
    private JFXButton participateButton;
    double xOffset, yOffset;
    private Activites activit;
    private Client user;

    serviceActivites se=new serviceActivites();
    ActivityHolder ah = ActivityHolder.getINSTANCE();
       UserHolder uh = UserHolder.getINSTANCE();
    ServiceClient sc=new ServiceClient();
        public String id_user = "";
    @FXML
    private Rating rateact;
    @FXML
    private Label dureeact;
    @FXML
    private Label Description;
    @FXML
    private JFXButton joinact;
    @FXML
    private Label actDate;
    @FXML
    private Label actnbp;
    @FXML
    private Label actnbmax;
    @FXML
    private Label dureeact1;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("heere");
       // new ZoomIn(eventPane).play();
       
                        System.out.println("here user"+uh.getId());
user=sc.load_data_modify(uh.getId());
        activit=se.getAct(ah.getId());
       
       Description.setText(activit.getDescription());
              dureeact.setText(activit.getDuree());
               dureeact1.setText(activit.getIdcoach().getNom()+"  "+activit.getIdcoach().getPrenom());

        actnbmax.setText(String.valueOf(activit.getNombremax()));
                actnbp.setText(String.valueOf(activit.getNombre_parti()));

        location.setText(activit.getLieu());
        String start=activit.getDate();
       
        actDate.setText(start);
                System.out.println(activit.getId() +"herrre");


    }    
 public void initializeFxml(String id) {
     System.out.println(id_user);
    }  
    @FXML
    private void updateEvent(MouseEvent event) throws IOException {
        
    }

    @FXML
    private void deleteEvent(MouseEvent event) {
       
    }



   

    @FXML
    private void rateact(MouseEvent event) {
         
    
    
    
        Alert dialogC = new Alert(Alert.AlertType.CONFIRMATION);
        dialogC.setTitle("DELETE");
        dialogC.setHeaderText(null);
        dialogC.setContentText("Êtes-vous sûr d'attribuer la note de "+rateact.getRating()+" /5 pour cette activité");
        Optional<ButtonType> answer = dialogC.showAndWait();
        if (answer.get() == ButtonType.OK) {
            int s =(int) rateact.getRating();

        System.out.println("test"+s);
        
        System.out.println(activit.getId());
         serviceActivites sa=new serviceActivites();
         sa.updaterating(activit,s,uh.getId());
       org.controlsfx.control.Notifications notifictaionBuilder = org.controlsfx.control.Notifications.create()
                    .title("Aura")
                    .text("Merci ")
                    .graphic(null)
                    .position(Pos.BOTTOM_RIGHT).graphic(new ImageView(new Image("/Images/logo.png")))
                    .onAction((ActionEvent activit) -> {
                        System.out.println("Clicked on notifictaion");
            });
            notifictaionBuilder.darkStyle();
            notifictaionBuilder.show();
         
         
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("RATING");
                alert.setHeaderText(null);
                alert.setContentText("Fait");
                alert.showAndWait();
        } else {
            System.out.println("User chose Cancel or closed the dialog-box");
        }
    
    
    
    
    
    
    
    
    
    }

    @FXML
    private void joinact(ActionEvent event) {
          
         serviceActivites sa=new serviceActivites();
        //user id
         Alert dialogC = new Alert(Alert.AlertType.CONFIRMATION);
        dialogC.setTitle("Rejoindre");
        dialogC.setHeaderText(null);
        dialogC.setContentText("Êtes-vous sûr de vouloir rejoindre cette activité");
        Optional<ButtonType> answer = dialogC.showAndWait();
        if (answer.get() == ButtonType.OK) {
             sa.joinact(activit,uh.getId());
         org.controlsfx.control.Notifications notifictaionBuilder = org.controlsfx.control.Notifications.create()
                    .title("Aura")
                    .text("New Gladiator a rejoint l'équipe ")
                    .graphic(null)
                    .position(Pos.BOTTOM_RIGHT)
                    .onAction((ActionEvent activit) -> {
                        System.out.println("Clicked on notifictaion");
            });
            notifictaionBuilder.darkStyle();
            notifictaionBuilder.show();
           
        } else {
            System.out.println("User chose Cancel or closed the dialog-box");
        }
        
    }
    
}
