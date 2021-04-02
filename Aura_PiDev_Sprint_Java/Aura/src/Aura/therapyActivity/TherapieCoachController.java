

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aura.therapyActivity;

import Entities.Therapie;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import Service.ServiceCoach;
import Service.serviceTherapie;

/**
 * FXML Controller class
 *
 * @author Medimegh
 */
public class TherapieCoachController implements Initializable {

    @FXML
    private Button addact1;
    @FXML
    private DatePicker datee1;
    private TextField id1;
    @FXML
    private TextField idcoach1;
    @FXML
    private TextField nombremax1;
    @FXML
    private TextField sujet1;
    @FXML
    private TextField lieu1;
    @FXML
    private Text lidm11;
        public String id_user = "";


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
public void initializeFxml(String id) {
     System.out.println(id_user);
    }  
    @FXML
        private void addpropTh(ActionEvent event) {
                 idcoach1.setText(id_user);

            if( (sujet1.getText().equals("")) || (lieu1.getText().equals(""))  || (datee1.getEditor().getText().equals(""))
                || (nombremax1.getText().equals("")))
                {
            System.out.println("empty");
            
                 new animatefx.animation.Shake(sujet1).play();
                   new animatefx.animation.Shake(nombremax1).play();
                    new animatefx.animation.Shake(lieu1).play();
                                                            new animatefx.animation.Shake(datee1).play();


                    Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Champ manquant");
                alert.setHeaderText(null);
                alert.setContentText("IL FAUT REMPLIR TOUS LES CHAMPS");
                alert.showAndWait();
        }
        
        
        
        else{
        serviceTherapie st=new serviceTherapie();
              ServiceCoach sc = new ServiceCoach();
     Random rand = new Random();
        int randID = rand.nextInt(100000);
       Therapie T =new  Therapie();
     //coach id
     
T.setIdcoach(sc.load_data_modify(idcoach1.getText()));
T.setSujet(sujet1.getText());
T.setLieu(lieu1.getText());
T.setDate(datee1.getEditor().getText());
T.setNombremax(Integer.parseInt(nombremax1.getText()));
T.setId(randID);
T.setNombre_parti(0);

st.addpropTherapie(T);
   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Proposition");
                alert.setHeaderText(null);
                alert.setContentText("votre proposition a été envoyée avec succès, nous vous répondrons dans les plus brefs délais");
                alert.showAndWait();
        }
    }


    
}
