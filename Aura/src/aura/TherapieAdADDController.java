/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aura;

import Utils.MaConnexion;
import entities.Coach;
import entities.Therapie;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import services.ServiceCoach;
import services.serviceTherapie;

/**
 * FXML Controller class
 *
 * @author Medimegh
 */
public class TherapieAdADDController implements Initializable {

    @FXML
    private Button addact;
    @FXML
    private Text ic;
    @FXML
    private DatePicker datee;
    @FXML
    private TextField nombremax;
    @FXML
    private TextField sujet;
    @FXML
    private TextField lieu;
    @FXML
    private Button show;
    @FXML
    private ComboBox comboth;
    @FXML
    private Text lidm1;
    @FXML
    private Text lid1;
    @FXML
    private Button modifier;
    @FXML
    private ComboBox<Coach> listcoach;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     fillComboBox(); 
         loadlistcoach();
        
        comboth.setVisible(false);
        lidm1.setVisible(false);
        lid1.setVisible(false);
        modifier.setVisible(false);
       org.controlsfx.control.Notifications notifictaionBuilder = org.controlsfx.control.Notifications.create()
                    .title("Aura")
                    .text("It's time to seek a new Therapy")
                    .graphic(null)
                    .position(Pos.BOTTOM_RIGHT)
                    .onAction((ActionEvent event1) -> {
                        System.out.println("Clicked on notifictaion");
            });
            notifictaionBuilder.darkStyle();
            notifictaionBuilder.show();
    }    

    @FXML
    private void addTh(ActionEvent event) {
    serviceTherapie st=new serviceTherapie();
    ServiceCoach sc=new ServiceCoach();
       Therapie T =new  Therapie();
            Random rand = new Random();
        int randID = rand.nextInt(100000);
T.setIdcoach(listcoach.getSelectionModel().getSelectedItem());
T.setSujet(sujet.getText());
T.setLieu(lieu.getText());
T.setDate(datee.getEditor().getText());
T.setNombremax(Integer.parseInt(nombremax.getText()));
T.setId(randID);
T.setNombre_parti(0);

st.addTherapie(T);

  fillComboBox();
  
clearfields();
   

    }
        private void clearfields() {
lieu.clear();

sujet.clear();
nombremax.clear();

    }

             public void fillComboBox(){
    
        try {
                        String requete = "SELECT sujet FROM therapie";

           Statement st = MaConnexion.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                 String id =rs.getString("sujet"); 
                
                 comboth.getItems().addAll(id);   
            }
            st.close();
            rs.close();
        } catch (SQLException ex) { 
        }        
    }

    @FXML
    private void modifyth(ActionEvent event) {
        
        comboth.setVisible(true);
                lidm1.setVisible(true);
                lid1.setVisible(true);
                modifier.setVisible(true);
addact.setVisible(false);
show.setVisible(false);
//lid.setVisible(false);

    }


    @FXML
    private void modifierth(ActionEvent event) {
          serviceTherapie Th=new serviceTherapie();
           ServiceCoach sc=new ServiceCoach();
        Therapie T =new  Therapie();
T.setSujet(sujet.getText());
T.setIdcoach(listcoach.getSelectionModel().getSelectedItem());
T.setLieu(lieu.getText());
T.setDate(datee.getEditor().getText());
T.setNombremax(Integer.parseInt(nombremax.getText()));
//T.setId(Integer.parseInt(lidm1.getText()));

Th.modifierTherapie(T);

comboth.setVisible(false);
                lidm1.setVisible(false);
                lid1.setVisible(false);
                                modifier.setVisible(false);

addact.setVisible(true);
show.setVisible(true);
          

    }

    @FXML
    private void fillth(ActionEvent event) {
         Object b= comboth.getSelectionModel().getSelectedItem();
    try {
        
                String query = "select * from therapie where  sujet='"+b+"'";

             Statement st = MaConnexion.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(query);
                while(rs.next()){
                   lidm1.setText(rs.getString("sujet"));
                   // id.setText(rs.getString("id"));
                    sujet.setText(rs.getString("sujet"));
                    lieu.setText(rs.getString("lieu"));
                    nombremax.setText(rs.getString("nombremax"));
                    (datee.getEditor()).setText(rs.getString("date"));
                }
                rs.close();
                
              
            } catch (SQLException ex) {
               
            }
    }
 
     public void loadlistcoach()
{
 ServiceCoach sa = new  ServiceCoach();
        ObservableList<Coach> coach =  (ObservableList<Coach>) sa.afficherCoach_Oui();
               listcoach.setItems(coach);

}
}
