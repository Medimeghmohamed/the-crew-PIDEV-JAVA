/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aura.therapyActivity;

import Aura.Aura;
import Aura.SideBar.AdminMainController;
import utils.Connexion;
import Entities.Activites;
import Entities.Coach;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import Service.ServiceCoach;
import Service.serviceActivites;

/**
 * FXML Controller class
 *
 * @author Medimegh
 */
public class ModifyController implements Initializable {

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
    private ComboBox combobox;
    @FXML
    private Text lid;
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
        // TODO
    }    

  public void initializeFxml(String id) {
        System.out.println(id_user);
    }

     
  //remplir combobox
                
         public void fillComboBox(){
    
        try {
                        String requete = "SELECT description FROM activite";

           Statement st = Connexion.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(requete);
            
            while(rs.next()){
                 String name =rs.getString("description"); 
                 combobox.getItems().addAll(name);
                 
            }
            
            st.close();
            rs.close();
        } catch (SQLException ex) { 
        }        
    }
         
//remplir les champs
    @FXML
    private void fill(ActionEvent event) {
      Object b= combobox.getSelectionModel().getSelectedItem();
//int c  = Integer.parseInt((String) b);      
        System.out.println(b);
    try {
                String query = "select * from activite where  description='"+b+"'";

             Statement st = Connexion.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(query);
                while(rs.next()){
                   lid.setText(rs.getString("id"));
                   // id.setText(rs.getString("id"));
                    duree.setText(rs.getString("duree"));
                    lieu.setText(rs.getString("lieu"));
                    type.setText(rs.getString("type"));
                   description.setText(rs.getString("description"));
                    nombremax.setText(rs.getString("nombremax"));
                    //idcoach.setText(rs.getString("idcoach"));
                   
                    (datee.getEditor()).setText(rs.getString("date"));
      
                }
                rs.close();
            } catch (SQLException ex) {
               
            }
        
    }

            
        
      
    //modifier act

    @FXML
    private void updateact(ActionEvent event) {
        
            serviceActivites sa=new serviceActivites();
        Activites A =new Activites();
      ServiceCoach sc = new ServiceCoach();

A.setDescription(description.getText());
A.setIdcoach(listcoach.getSelectionModel().getSelectedItem());
A.setDuree(duree.getText());
A.setType(type.getText());
A.setLieu(lieu.getText());
A.setDate(datee.getEditor().getText());
A.setNombremax(Integer.parseInt(nombremax.getText()));
A.setId(Integer.parseInt(lid.getText()));

sa.modifierActivite(A);
 org.controlsfx.control.Notifications notifictaionBuilder = org.controlsfx.control.Notifications.create()
                    .title("Aura")
                    .text("Activité mise à jour avec succès")
                    .graphic(null)
                    .position(Pos.BOTTOM_RIGHT)
                    .onAction((ActionEvent event1) -> {
                        System.out.println("Clicked on notifictaion");
            });
            notifictaionBuilder.darkStyle();
            notifictaionBuilder.show();
       
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
        HomeScene.showactad_nav(id_user);
        HomeScene.id_user = id_user;
        HomeScene.show_data(id_user);
        String css = Aura.class.getResource("Style.css").toExternalForm();

        root.getStylesheets().add(css);

        Stage window = (Stage) BACK.getScene().getWindow();

        window.setScene(new Scene(root, 1182, 718));
    
    }
        
    
}
