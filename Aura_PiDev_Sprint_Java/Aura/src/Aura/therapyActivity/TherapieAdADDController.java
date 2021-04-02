/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aura.therapyActivity;

import Aura.Aura;
import Aura.SideBar.AdminMainController;
import utils.Connexion;
import Entities.Coach;
import Entities.Therapie;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import Service.ServiceCoach;
import Service.serviceTherapie;

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
    private Button modifier;
    @FXML
    private ComboBox<Coach> listcoach;
    @FXML
    private Button BACK;
        public String id_user = "";


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     fillComboBox(); 
         loadlistcoach();
        
        comboth.setVisible(false);
        modifier.setVisible(false);
       org.controlsfx.control.Notifications notifictaionBuilder = org.controlsfx.control.Notifications.create()
                    .title("Aura")
                    .text("Il est temps d'ajouter une nouvelle thérapie")
                    .graphic(null)
                    .position(Pos.BOTTOM_RIGHT)
                    .onAction((ActionEvent event1) -> {
                        System.out.println("Clicked on notifictaion");
            });
            notifictaionBuilder.darkStyle();
            notifictaionBuilder.show();
    }    
  public void initializeFxml(String id) {
        System.out.println(id_user);
    }
    @FXML
    private void addTh(ActionEvent event) {
        
        if( (sujet.getText().equals("")) || (lieu.getText().equals(""))  || (datee.getEditor().getText().equals(""))
                || (nombremax.getText().equals("")))
                {
            System.out.println("empty");
            
                 new animatefx.animation.Shake(sujet).play();
                  new animatefx.animation.Shake(listcoach).play();
                   new animatefx.animation.Shake(nombremax).play();
                    new animatefx.animation.Shake(lieu).play();
                                                            new animatefx.animation.Shake(datee).play();


                    Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Champ manquant");
                alert.setHeaderText(null);
                alert.setContentText("IL FAUT REMPLIR TOUS LES CHAMPS");
                alert.showAndWait();
        }
        
        
        
        else{
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

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ajouter ");
                alert.setHeaderText(null);
                alert.setContentText("Therapie ajouté avec succes");
                alert.showAndWait();
        }

    }
        private void clearfields() {
lieu.clear();

sujet.clear();
nombremax.clear();
        
    }

             public void fillComboBox(){
    
        try {
                        String requete = "SELECT sujet FROM therapie";

           Statement st = Connexion.getInstance().getConnection().createStatement();
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

Th.modifierTherapie(T);

comboth.setVisible(false);
                                modifier.setVisible(false);

addact.setVisible(true);
show.setVisible(true);
          

    }

    @FXML
    private void fillth(ActionEvent event) {
         Object b= comboth.getSelectionModel().getSelectedItem();
    try {
        
                String query = "select * from therapie where  sujet='"+b+"'";

             Statement st = Connexion.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(query);
                while(rs.next()){
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
        ObservableList<Coach> coach =  (ObservableList<Coach>) sa.afficherCoach_Ouiob();
               listcoach.setItems(coach);

}

    @FXML
    private void BACK(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/SideBar/AdminMain.fxml"));

        Parent root = loader.load();

        AdminMainController HomeScene = loader.getController();
        HomeScene.showthad_nav(id_user);
        HomeScene.id_user = id_user;
        HomeScene.show_data(id_user);
        String css = Aura.class.getResource("Style.css").toExternalForm();

        root.getStylesheets().add(css);

        Stage window = (Stage) BACK.getScene().getWindow();

        window.setScene(new Scene(root, 1182, 718));
    
    }
}
