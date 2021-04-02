/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aura.therapyActivity;

import Entities.Therapie;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Medimegh
 */
public class ListtherapyController implements Initializable {

    @FXML
    private ImageView actImg;
    @FXML
    private Label actDay;
    @FXML
    private Label ActDescription;
    @FXML
    private Label iduser;
    @FXML
    private Label Nombremax;
    @FXML
    private Label ActLieu;
    @FXML
    private JFXButton detailact;
    @FXML
    private Label Nombremax1;
    @FXML
    private Label ActLieu1;
    int id=0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
        public void setDatath(Therapie e,String i){
        ActDescription.setText(e.getSujet());
        ActLieu.setText(e.getLieu());
        actDay.setText(e.getDate());
        Nombremax.setText(e.getIdcoach().getNom()+" "+e.getIdcoach().getPrenom());
        id=e.getId();
                iduser.setText(i);
                System.out.println("herherhehrehrhe"+iduser.getText());
       
    }


    @FXML
    private void showTaskDetailsAction(ActionEvent event) throws IOException {
        ActivityHolder th = ActivityHolder.getINSTANCE();
        System.out.println("id_user===="+iduser.getText());
        th.setId(id); 
       
               UserHolder th1 = UserHolder.getINSTANCE();
        th1.setId(iduser.getText());
        System.out.println(th1.getId());
        
        System.out.println("1   "+th.getId());
 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Detailstherapy.fxml"));
Parent modif = (Parent) fxmlLoader.load();
Stage stage = new Stage();
stage.setScene(new Scene(modif));  
stage.show();
    }
    
}
