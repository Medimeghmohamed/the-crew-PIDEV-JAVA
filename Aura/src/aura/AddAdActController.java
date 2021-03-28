/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aura;

import entities.Activites;
import entities.Coach;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.ServiceCoach;
import services.serviceActivites;

/**
 * FXML Controller class
 *
 * @author Medimegh
 */
public class AddAdActController implements Initializable {

    @FXML
    private DatePicker datee;
    @FXML
    private TextField duree;
    @FXML
    private TextField nombremax;
    @FXML
    private TextField type;
    @FXML
    private TextField description;
    @FXML
    private TextField lieu;
    @FXML
    private Button show;
    @FXML
    private ComboBox<Coach> listcoach;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
loadlistcoach();    }    

    @FXML
    private void addact(ActionEvent event) {
       
        if((description.getText().equals("") )|| (duree.getText().equals("")) || (lieu.getText().equals("")) || (type.getText().equals("")) || (datee.getEditor().getText().equals(""))
                || (nombremax.getText().equals(""))){
            System.out.println("noooo");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Champ manquant");
                alert.setHeaderText(null);
                alert.setContentText("IL FAUT REMPLIR TOUS LES CHAMPS");
                alert.showAndWait();
        }
        else
        {System.out.println("yes");
         serviceActivites sa=new serviceActivites();
               ServiceCoach sc = new ServiceCoach();
        Activites A =new Activites();
            Random rand = new Random();
        int randID = rand.nextInt(100000);
A.setDescription(description.getText());
A.setIdcoach(listcoach.getSelectionModel().getSelectedItem());
A.setDuree(duree.getText());
A.setLieu(lieu.getText());
A.setType(type.getText());
A.setDate(datee.getEditor().getText());
A.setNombremax(Integer.parseInt(nombremax.getText()));
A.setId(randID);


sa.addActivities(A);
clearfields();
    }}

    @FXML
    private void modify(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("modify.fxml"));
Parent modif = (Parent) fxmlLoader.load();
Stage stage = new Stage();
stage.setScene(new Scene(modif));  
stage.show();
    }

    private void clearfields() {
lieu.clear();

description.clear();
type.clear();
nombremax.clear();

duree.clear();
    }
    public void loadlistcoach()
{
 ServiceCoach sa = new  ServiceCoach();
        ObservableList<Coach> activit =  (ObservableList<Coach>) sa.afficherCoach_Oui();
               listcoach.setItems(activit);

}
}
