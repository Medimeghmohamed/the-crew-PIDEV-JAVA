/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aura.Contact_Entraide;

import Entities.Client;
import Entities.Entraide;
import Service.ServiceClient;
import Service.ServiceEntraide;
import Service.ServiceNotification;
import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author Nour Dekhil
 */
public class AddEntraideController implements Initializable {

    @FXML
    private ComboBox<String> combo_cat;
    @FXML
    private TextField tf_quest;
    @FXML
    private DatePicker tf_date;
    @FXML
    private Button clean;

    /**
     * Initializes the controller class.
     */
    public String id_user="";
    public void initializeFxml(String id){
        System.out.println("hey"+id);
    
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ObservableList<String> listcat = FXCollections.observableArrayList("Psychologie", "Santé", "Bien-être");
        combo_cat.setItems(listcat);

    }

    @FXML
    private void AjouterEntraide(MouseEvent event) {

        ServiceEntraide sc = new ServiceEntraide();
        Entraide e = new Entraide();
        
         ServiceClient scl = new ServiceClient();
        Client cl;

        String id = id_user;
        String cat = combo_cat.getValue();
        String ques = tf_quest.getText();
        String date = String.valueOf(tf_date.getValue());
        
        cl = scl.load_data_modify(id);
        String email=cl.getEmail();
        System.out.println(email+id);
      
        if (id.isEmpty() || cat.isEmpty() || ques.isEmpty() || date.isEmpty() ) {
            System.out.println("1");
            Alert alert = new Alert(Alert.AlertType.ERROR);
                        System.out.println("2");

            alert.setHeaderText(null);
            alert.setContentText("veuillez remplir tous les champs!");
            alert.showAndWait();
            

        } else {
            e.setId_user(id_user);         
            e.setEmail(email);
            e.setCategorie(combo_cat.getValue());
            e.setQuestion(tf_quest.getText());
            java.sql.Date gettedDatePickerDate = java.sql.Date.valueOf(tf_date.getValue());
            e.setDate(gettedDatePickerDate);
            ServiceNotification sn=new ServiceNotification();
        sn.Notification("Fleicitations","Ajout effectué ");
            sc.AjouterEntraide(e);
        }
        

    }

    @FXML
    private void Clean(MouseEvent event) {

       
        combo_cat.setValue(null);
        tf_quest.setText(null);
        tf_date.setValue(null);
    }
}
