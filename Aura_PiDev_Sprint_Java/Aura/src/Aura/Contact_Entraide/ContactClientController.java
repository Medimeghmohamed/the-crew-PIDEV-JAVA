/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aura.Contact_Entraide;

import Entities.Client;
import Entities.Contact;
import Service.ServiceClient;
import Service.ServiceContact;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Nour Dekhil
 */
public class ContactClientController implements Initializable {

    @FXML
    private TextArea tfMsgContact;
    @FXML
    private Button btnValider;
    @FXML
    private DatePicker tfDate;
    @FXML
    private ComboBox<String> combo_obj;

    /**
     * Initializes the controller class.
     */
    public String id_user="";
    public void initializeFxml(String id){
        System.out.println("hey"+id);
    
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ObservableList<String> listobj = FXCollections.observableArrayList("Avis", "Reclamation", "Contact", "Autre..");
        combo_obj.setItems(listobj);
    }

    @FXML
    private void AjouterContact(ActionEvent event) {
    
        ServiceContact sc = new ServiceContact();

        ServiceClient scl = new ServiceClient();
        Client cl;

        Contact c = new Contact();

        String type = combo_obj.getValue();
        String id = id_user;
        String champs = tfMsgContact.getText();
        String date = String.valueOf(tfDate.getValue());
        
        cl = scl.load_data_modify(id);
        String email=cl.getEmail();
         
        if (type ==null || id.isEmpty() || champs.isEmpty() || date.isEmpty()) {
           
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir les champs!");
            alert.showAndWait();
        } else {
            c.setType(combo_obj.getValue());
            c.setId_user(id_user);
            c.setChamp(tfMsgContact.getText());
            c.setEmail(email);
            java.sql.Date gettedDatePickerDate = java.sql.Date.valueOf(tfDate.getValue());
            c.setDate(gettedDatePickerDate);
            sc.AjouterContact(c);

        }
        System.out.println(email);
    }

}
