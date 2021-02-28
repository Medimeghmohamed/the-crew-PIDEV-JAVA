/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aura;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import entities.Activites;
import java.util.Date;
import javafx.scene.control.TextField;
import services.serviceActivites;

/**
 *
 * @author Medimegh
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private TextField duree;
    @FXML
    private TextField id;
    @FXML
    private TextField idcoach;
    @FXML
    private TextField nombremax;
    @FXML
 
    private TextField description;
    @FXML
    private TextField lieu;
    @FXML
    private DatePicker datee;
    
  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addact(ActionEvent event) {
        serviceActivites sa=new serviceActivites();
        Activites A =new Activites();
      A.setDate(null);
A.setDescription(description.getText());
A.setIdcoach(idcoach.getText());
A.setDuree(duree.getText());
A.setLieu(lieu.getText());
A.setNombremax(Integer.parseInt(nombremax.getText()));
A.setId(Integer.parseInt(id.getText()));

sa.addActivities(A);

    }
    
}
