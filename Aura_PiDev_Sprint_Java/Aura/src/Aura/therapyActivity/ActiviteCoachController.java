/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aura.therapyActivity;

import Entities.Activites;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Service.ServiceCoach;
import Service.serviceActivites;

/**
 * FXML Controller class
 *
 * @author Medimegh
 */
public class ActiviteCoachController implements Initializable {

    @FXML
    private DatePicker datee1;
    @FXML
    private TextField idcoach1;
    @FXML
    private TextField duree1;
    @FXML
    private TextField nombremax1;
    @FXML
    private TextField type1;
    @FXML
    private TextField description1;
    @FXML
    private TextField lieu1;
    @FXML
    private Button show1;
    private PieChart stat;
    private TextField statid;
    public String id_user = "";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }     public void initializeFxml(String id) {
     System.out.println(id_user);
    }  

    @FXML
        private void addpropact(ActionEvent event) {
                    idcoach1.setText(id_user);

             if((description1.getText().equals("") ) || (idcoach1.getText().equals("")) || (duree1.getText().equals("")) || (lieu1.getText().equals("")) || (type1.getText().equals("")) || (datee1.getEditor().getText().equals(""))
                || (nombremax1.getText().equals(""))){
            System.out.println("noooo");
            
             new animatefx.animation.Shake(duree1).play();
                  new animatefx.animation.Shake(description1).play();
                   new animatefx.animation.Shake(nombremax1).play();
                    new animatefx.animation.Shake(lieu1).play();
                                        new animatefx.animation.Shake(type1).play();
                                                            new animatefx.animation.Shake(datee1).play();

            
            Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Champ manquant");
                alert.setHeaderText(null);
                alert.setContentText("IL FAUT REMPLIR TOUS LES CHAMPS");
                alert.showAndWait();
        }
            
             else{
        serviceActivites sa=new serviceActivites();
        Activites A =new Activites();
        Random rand = new Random();
        int randID = rand.nextInt(100000);
      //  id Coach connec
        idcoach1.setText(id_user);
ServiceCoach sc = new ServiceCoach();
A.setDescription(description1.getText());
A.setIdcoach(sc.load_data_modify(idcoach1.getText()));
A.setDuree(duree1.getText());
A.setLieu(lieu1.getText());
A.setType(type1.getText());
A.setDate(datee1.getEditor().getText());
A.setNombremax(Integer.parseInt(nombremax1.getText()));
A.setId(randID);
A.setNombre_parti(0);
sa.addpropActivities(A);
 
            Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Ajouter Proposition ");
                alert.setHeaderText(null);
                alert.setContentText("votre proposition a été envoyée avec succès, nous vous répondrons dans les plus brefs délais ");
                alert.showAndWait();
             }
    }


    @FXML
        private void modify(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("modify.fxml"));
Parent modif = (Parent) fxmlLoader.load();
Stage stage = new Stage();
stage.setScene(new Scene(modif));  
stage.show();
    }


        private void showstat(ActionEvent event) {
serviceActivites sc=new serviceActivites();
int i =Integer.parseInt(statid.getText());
    ObservableList<PieChart.Data> list = FXCollections.observableArrayList(
                new PieChart.Data("2", 200/5),
                            new PieChart.Data("3", 300/5),

                new PieChart.Data(" 5", 5/5));
    sc.sommerating(i);
        stat.setData(list);
    }

    
}
