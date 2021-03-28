/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aura;

import Utils.MaConnexion;
import entities.Activites;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import services.serviceActivites;

/**
 * FXML Controller class
 *
 * @author Medimegh
 */
public class ShowadactController implements Initializable {

       @FXML
    private TableColumn<Activites, String> cidcoach;
    @FXML
    private TableColumn<Activites, String> cduree;
    @FXML
    private TableColumn<Activites, String> cdate;
    @FXML
    private TableColumn<Activites, Integer> cnbm;
    @FXML
    private TableColumn<Activites, String> ctype;
    @FXML
    private TableColumn<Activites, String> cdes;
    @FXML
    private TableColumn<Activites, String> clieu;
    @FXML
    private TableView<Activites> tabact;
    @FXML
    private ComboBox combotype;
    @FXML
    private ComboBox comboate;
    @FXML
    private Button ADD;
    @FXML
    private Button Proposition;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      lodtabth();fillComboBoxtype();fillcomboboxdate();
    }    

    @FXML
    private void loadtable(ActionEvent event) {
        
        serviceActivites sa = new serviceActivites();
        ObservableList<Activites> activit = sa.showActivites();
                tabact.setItems(activit);

      //  cid.setCellValueFactory(new PropertyValueFactory<Activites,Integer>("id"));
        cidcoach.setCellValueFactory(new PropertyValueFactory<Activites, String>("idcoach"));
        cduree.setCellValueFactory(new PropertyValueFactory<Activites, String>("duree"));
        cdate.setCellValueFactory(new PropertyValueFactory<Activites, String>("date"));
        cnbm.setCellValueFactory(new PropertyValueFactory<Activites,Integer>("nombremax"));
        ctype.setCellValueFactory(new PropertyValueFactory<Activites, String>("type"));
        cdes.setCellValueFactory(new PropertyValueFactory<Activites, String>("description"));
        clieu.setCellValueFactory(new PropertyValueFactory<Activites, String>("lieu"));

        tabact.setItems(activit);
    }

    @FXML
    private void findtype(ActionEvent event) {
       serviceActivites sa = new serviceActivites();
        ObservableList<Activites> activit = (ObservableList<Activites>) sa.findtype((String)combotype.getSelectionModel().getSelectedItem());
                tabact.setItems(activit);

       // cid.setCellValueFactory(new PropertyValueFactory<Activites,Integer>("id"));
        cidcoach.setCellValueFactory(new PropertyValueFactory<Activites, String>("idcoach"));
        cduree.setCellValueFactory(new PropertyValueFactory<Activites, String>("duree"));
        cdate.setCellValueFactory(new PropertyValueFactory<Activites, String>("date"));
        cnbm.setCellValueFactory(new PropertyValueFactory<Activites,Integer>("nombremax"));
        ctype.setCellValueFactory(new PropertyValueFactory<Activites, String>("type"));
        cdes.setCellValueFactory(new PropertyValueFactory<Activites, String>("description"));
        clieu.setCellValueFactory(new PropertyValueFactory<Activites, String>("lieu"));

        tabact.setItems(activit); 
    }

    @FXML
    private void finddate(ActionEvent event) {
         serviceActivites sa = new serviceActivites();
        ObservableList<Activites> activit = (ObservableList<Activites>) sa.finddate((String)comboate.getSelectionModel().getSelectedItem());
                tabact.setItems(activit);

      //  cid.setCellValueFactory(new PropertyValueFactory<Activites,Integer>("id"));
        cidcoach.setCellValueFactory(new PropertyValueFactory<Activites, String>("idcoach"));
        cduree.setCellValueFactory(new PropertyValueFactory<Activites, String>("duree"));
        cdate.setCellValueFactory(new PropertyValueFactory<Activites, String>("date"));
        cnbm.setCellValueFactory(new PropertyValueFactory<Activites,Integer>("nombremax"));
        ctype.setCellValueFactory(new PropertyValueFactory<Activites, String>("type"));
        cdes.setCellValueFactory(new PropertyValueFactory<Activites, String>("description"));
        clieu.setCellValueFactory(new PropertyValueFactory<Activites, String>("lieu"));

        tabact.setItems(activit);
    }

    private void lodtabth() {
        serviceActivites sa = new serviceActivites();
        ObservableList<Activites> activit = sa.showActivites();
                tabact.setItems(activit);

       // cid.setCellValueFactory(new PropertyValueFactory<Activites,Integer>("id"));
        cidcoach.setCellValueFactory(new PropertyValueFactory<Activites, String>("idcoach"));
        cduree.setCellValueFactory(new PropertyValueFactory<Activites, String>("duree"));
        cdate.setCellValueFactory(new PropertyValueFactory<Activites, String>("date"));
        cnbm.setCellValueFactory(new PropertyValueFactory<Activites,Integer>("nombremax"));
        ctype.setCellValueFactory(new PropertyValueFactory<Activites, String>("type"));
        cdes.setCellValueFactory(new PropertyValueFactory<Activites, String>("description"));
        clieu.setCellValueFactory(new PropertyValueFactory<Activites, String>("lieu"));

        tabact.setItems(activit);
        
        
    }

    @FXML
    private void deletefromtable(ActionEvent event) {
          
         
         
         
         
         
         Alert dialogC = new Alert(Alert.AlertType.CONFIRMATION);
dialogC.setTitle("DELETE");
dialogC.setHeaderText(null);
dialogC.setContentText("Are you sure you want to delete this activity");
Optional<ButtonType> answer = dialogC.showAndWait();
if (answer.get() == ButtonType.OK) {
System.out.println("User chose OK");
  Activites a=   tabact.getSelectionModel().getSelectedItem();
        System.out.println( a.getId());
         serviceActivites sa=new serviceActivites();
         sa.supprimeractivite(a.getId());
                 System.out.println("supprimer");
         lodtabth();
}
else {
System.out.println("User chose Cancel or closed the dialog-box");
}
         
    
    }

     public void fillComboBoxtype(){
    
        try {
                        String requete = "SELECT type FROM activite";

           Statement st = MaConnexion.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                 String type =rs.getString("type"); 
                 combotype.getItems().addAll(type);
                  
            }
            st.close();
            rs.close();
        } catch (SQLException ex) { 
        }        
    }
public void fillcomboboxdate(){
 try {
                        String requete = "SELECT date FROM activite";

           Statement st = MaConnexion.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                 String date =rs.getString("date"); 
                
                 comboate.getItems().addAll(date);
                  
            }
            st.close();
            rs.close();
        } catch (SQLException ex) { 
        }     }

    @FXML
    private void ADD(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        Parent root = loader.load();
        MainController HomeScene = loader.getController();
        Pane view;
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("addAdAct.fxml"));
        view = loader2.load();
        HomeScene.returnPane(view);
        Stage window = (Stage)ADD.getScene().getWindow();
        window.setScene(new Scene(root, 1182, 718));
    }

    @FXML
    private void Proposition(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));

        Parent root = loader.load();
        MainController HomeScene = loader.getController();
        Pane view;
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("propoadact.fxml"));
        view = loader2.load();
        HomeScene.returnPane(view);
        Stage window = (Stage)Proposition.getScene().getWindow();
        window.setScene(new Scene(root, 1182, 718));
    }
}
