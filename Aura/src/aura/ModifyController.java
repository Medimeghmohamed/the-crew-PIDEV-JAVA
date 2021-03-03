/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aura;

import MaConnexion.MyConnection;
import entities.Activites;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.serviceActivites;

/**
 * FXML Controller class
 *
 * @author Medimegh
 */
public class ModifyController implements Initializable {

    @FXML
    private DatePicker datee;
    @FXML
    private TextField idcoach;
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
    private Text lid1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         fillComboBox();
        // TODO
    }    

   


    
      
     
  //remplir combobox
                
         public void fillComboBox(){
    
        try {
                        String requete = "SELECT id FROM activite";

           Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            
            while(rs.next()){
                 String name =rs.getString("id"); 
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
int c  = Integer.parseInt((String) b);      
        System.out.println(c);
    try {
                String query = "select * from activite where  id='"+c+"'";

             Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(query);
                while(rs.next()){
                   lid.setText(rs.getString("id"));
                   // id.setText(rs.getString("id"));
                    duree.setText(rs.getString("duree"));
                    lieu.setText(rs.getString("lieu"));
                    type.setText(rs.getString("type"));
                   description.setText(rs.getString("description"));
                    nombremax.setText(rs.getString("nombremax"));
                    idcoach.setText(rs.getString("idcoach"));
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
A.setDescription(description.getText());
A.setIdcoach(idcoach.getText());
A.setDuree(duree.getText());
A.setType(type.getText());
A.setLieu(lieu.getText());
A.setDate(datee.getEditor().getText());
A.setNombremax(Integer.parseInt(nombremax.getText()));
A.setId(Integer.parseInt(lid.getText()));

sa.modifierActivite(A);
       
    }

        
    
}
