/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aura.therapyActivity;

import Entities.Activites;
import Entities.Client;
import Entities.Therapie;
import Service.ServiceClient;
import Service.serviceActivites;
import Service.serviceTherapie;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author Medimegh
 */
public class DetailstherapyController implements Initializable {

    @FXML
    private Label actDate;
    @FXML
    private ImageView eventImg;
    @FXML
    private Label actnbp;
    @FXML
    private FontAwesomeIconView updateIcon;
    @FXML
    private FontAwesomeIconView deleteIcon;
    @FXML
    private Label location;
    @FXML
    private Label Description;
    @FXML
    private Rating rateact;
    @FXML
    private JFXButton joinact;
    @FXML
    private Label actnbmax;
    @FXML
    private Label coachname;
double xOffset, yOffset;
    private Therapie activit;
    private Client user;

    serviceTherapie se=new serviceTherapie();
    ActivityHolder ah = ActivityHolder.getINSTANCE();
       UserHolder uh = UserHolder.getINSTANCE();
    ServiceClient sc=new ServiceClient();
        public String id_user = "";
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
       
       Description.setText(activit.getSujet());
         //     dureeact.setText(activit.get());

        actnbmax.setText(String.valueOf(activit.getNombremax()));
                actnbp.setText(String.valueOf(activit.getNombre_parti()));

        location.setText(activit.getLieu());
        String start=activit.getDate();
       
        actDate.setText(start);
        coachname.setText(activit.getIdcoach().getNom()+" "+activit.getIdcoach().getPrenom());
                System.out.println(activit.getId() +"herrre");
    }    

    @FXML
    private void updateEvent(MouseEvent event) {
    }

    @FXML
    private void deleteEvent(MouseEvent event) {
    }

    @FXML
    private void rateact(MouseEvent event) {
           serviceTherapie sa=new serviceTherapie();
        //user id
         Alert dialogC = new Alert(Alert.AlertType.CONFIRMATION);
        dialogC.setTitle("Rate");
        dialogC.setHeaderText(null);
        dialogC.setContentText("Êtes-vous sûr de vouloir evaluer cette Therapie");
        Optional<ButtonType> answer = dialogC.showAndWait();
        if (answer.get() == ButtonType.OK) {
            
            int s =(int) rateact.getRating();
        System.out.println( activit.getId());
        System.out.println(s);
         sa.updaterating(activit,s,uh.getId());
       
         
           
        } else {
            System.out.println("User chose Cancel or closed the dialog-box");
        }
        
    }

    @FXML
    private void joinact(ActionEvent event) {
        serviceTherapie sa=new serviceTherapie();
        //user id
         Alert dialogC = new Alert(Alert.AlertType.CONFIRMATION);
        dialogC.setTitle("Rejoindre");
        dialogC.setHeaderText(null);
        dialogC.setContentText("Êtes-vous sûr de vouloir rejoindre cette Therapie");
        Optional<ButtonType> answer = dialogC.showAndWait();
        if (answer.get() == ButtonType.OK) {
          sa.jointh(activit,uh.getId());
         org.controlsfx.control.Notifications notifictaionBuilder = org.controlsfx.control.Notifications.create()
                    .title("Aura")
                    .text("Welcome")
                    .graphic(null)
                    .position(Pos.BOTTOM_RIGHT).graphic(new ImageView(new Image("/Images/logo.png")))
                    .onAction((ActionEvent event1) -> {
                        System.out.println("Clicked on notifictaion");
            });
            notifictaionBuilder.darkStyle();
            notifictaionBuilder.show();
           
        } else {
            System.out.println("User chose Cancel or closed the dialog-box");
        }
        
    }
    
}
