/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aura;

import MaConnexion.MyConnection;
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
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author Medimegh
 */
public class ModifyController implements Initializable {

    @FXML
    private DatePicker datee;
    @FXML
    private TextField id;
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
    private Button show;
    @FXML
    private ComboBox<?> combobox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

   
                final ObservableList options = FXCollections.observableArrayList();

     Connection conn;
    PreparedStatement pst = null;
    ResultSet rs = null;
     @FXML
    private void modify(ActionEvent event) {
       
    }

    
      
     
  
                
         public void fillComboBox(){
        options.clear();
        try {
                        String requete = "SELECT id FROM activite";

           Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            
            while(rs.next()){
                options.add(rs.getInt("id"));
            }
            
            st.close();
            rs.close();
        } catch (SQLException ex) {
        }        
    }

    @FXML
    private void addact(ActionEvent event) {
    }
    @FXML
    private void choix(ActionEvent event) {
        
                 ComboBox  comboBox = new ComboBox(options);

         fillComboBox();

        comboBox.setOnAction((e) -> {
            
            
            try {
                String query = "select * from activite where id = ?";

                pst = conn.prepareStatement(query);
                pst.setInt(1, (int) comboBox.getSelectionModel().getSelectedItem());
                rs = pst.executeQuery();
          
                while(rs.next()){
                    id.setText(rs.getString("id"));
                    duree.setText(rs.getString("duree"));
                    lieu.setText(rs.getString("lieu"));
                    type.setText(rs.getString("type"));
                   description.setText(rs.getString("description"));
                    nombremax.setText(rs.getString("nombremax"));
                    idcoach.setText(rs.getString("idcoach"));
                    (datee.getEditor()).setText(rs.getString("date"));
                    
                    
                }
                pst.close();
                rs.close();
            } catch (SQLException ex) {
               
            }
            
        
        });
    }
        
    
}
