/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aura;

import Utils.MaConnexion;
import entities.Therapie;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.serviceTherapie;

/**
 * FXML Controller class
 *
 * @author Medimegh
 */
public class ShowadtTherapieController implements Initializable {

    @FXML
    private TableView<Therapie> tabth;
    @FXML
    private TableColumn<Therapie, Integer> cid;
    @FXML
    private TableColumn<Therapie, String> cidcoach;
    @FXML
    private TableColumn<Therapie, String> csujet;
    @FXML
    private TableColumn<Therapie, String> cdate;
    @FXML
    private TableColumn<Therapie, Integer> cnbm;
    @FXML
    private TableColumn<Therapie, String> clieu;
    @FXML
    private ComboBox combotype;
    @FXML
    private ComboBox combodate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     loadtableth();
     fillComboBoxdate();fillComboBoxtype();
    }    

   @FXML
    private void loadtable(ActionEvent event) {
        serviceTherapie th = new serviceTherapie();
        String s=null;
        ObservableList<Therapie> therapie = th.showTherapie();
                tabth.setItems(therapie);

        cid.setCellValueFactory(new PropertyValueFactory<Therapie,Integer>("id"));
        cidcoach.setCellValueFactory(new PropertyValueFactory<Therapie, String>("idcoach"));
        csujet.setCellValueFactory(new PropertyValueFactory<Therapie, String>("sujet"));
        cdate.setCellValueFactory(new PropertyValueFactory<Therapie, String>("date"));
        cnbm.setCellValueFactory(new PropertyValueFactory<Therapie,Integer>("nombremax"));
        clieu.setCellValueFactory(new PropertyValueFactory<Therapie, String>("lieu"));


                tabth.setItems(therapie);


    }

     public void fillComboBoxtype(){
    
        try {
                        String requete = "SELECT sujet FROM therapie";

           Statement st = MaConnexion.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                 String sujet =rs.getString("sujet"); 
                 combotype.getItems().addAll(sujet);
                  
            }
            st.close();
            rs.close();
        } catch (SQLException ex) { 
        }        
    }
      public void fillComboBoxdate(){
    
        try {
                        String requete = "SELECT date FROM therapie";

           Statement st = MaConnexion.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                 String date =rs.getString("date"); 
                 combodate.getItems().addAll(date);
                  
            }
            st.close();
            rs.close();
        } catch (SQLException ex) { 
        }        
    } 
    @FXML
    private void findtype(ActionEvent event) {
         serviceTherapie th = new serviceTherapie();
        ObservableList<Therapie> therapie = (ObservableList<Therapie>) th.findtype((String)combotype.getSelectionModel().getSelectedItem());
                tabth.setItems(therapie);

        cid.setCellValueFactory(new PropertyValueFactory<Therapie,Integer>("id"));
        cidcoach.setCellValueFactory(new PropertyValueFactory<Therapie, String>("idcoach"));
        csujet.setCellValueFactory(new PropertyValueFactory<Therapie, String>("sujet"));
        cdate.setCellValueFactory(new PropertyValueFactory<Therapie, String>("date"));
        cnbm.setCellValueFactory(new PropertyValueFactory<Therapie,Integer>("nombremax"));
        clieu.setCellValueFactory(new PropertyValueFactory<Therapie, String>("lieu"));


                tabth.setItems(therapie);
    }

    @FXML
    private void finddate(ActionEvent event) {
          serviceTherapie th = new serviceTherapie();
        ObservableList<Therapie> therapie = (ObservableList<Therapie>) th.finddate((String)combodate.getSelectionModel().getSelectedItem());
                tabth.setItems(therapie);

        cid.setCellValueFactory(new PropertyValueFactory<Therapie,Integer>("id"));
        cidcoach.setCellValueFactory(new PropertyValueFactory<Therapie, String>("idcoach"));
        csujet.setCellValueFactory(new PropertyValueFactory<Therapie, String>("sujet"));
        cdate.setCellValueFactory(new PropertyValueFactory<Therapie, String>("date"));
        cnbm.setCellValueFactory(new PropertyValueFactory<Therapie,Integer>("nombremax"));
        clieu.setCellValueFactory(new PropertyValueFactory<Therapie, String>("lieu"));


                tabth.setItems(therapie);
    }

    @FXML
    private void deletetherapiefromtab(ActionEvent event) {
     Alert dialogC = new Alert(Alert.AlertType.CONFIRMATION);
dialogC.setTitle("DELETE");
dialogC.setHeaderText(null);
dialogC.setContentText("Are you sure you want to delete this Therapy");
Optional<ButtonType> answer = dialogC.showAndWait();
if (answer.get() == ButtonType.OK) {
System.out.println("User chose OK");
     Therapie t=   tabth.getSelectionModel().getSelectedItem();
        System.out.println( t.getId());
         serviceTherapie th=new serviceTherapie();
         th.supprimerTherapie(t.getId());
        System.out.println("th deleted");
         loadtableth();
}
else {
System.out.println("User chose Cancel or closed the dialog-box");
}     
         
    }
     private void loadtableth()
     {serviceTherapie th = new serviceTherapie();
        ObservableList<Therapie> therapie = th.showTherapie();
                tabth.setItems(therapie);

        cid.setCellValueFactory(new PropertyValueFactory<Therapie,Integer>("id"));
        cidcoach.setCellValueFactory(new PropertyValueFactory<Therapie, String>("idcoach"));
        csujet.setCellValueFactory(new PropertyValueFactory<Therapie, String>("sujet"));
        cdate.setCellValueFactory(new PropertyValueFactory<Therapie, String>("date"));
        cnbm.setCellValueFactory(new PropertyValueFactory<Therapie,Integer>("nombremax"));
        clieu.setCellValueFactory(new PropertyValueFactory<Therapie, String>("lieu"));


                tabth.setItems(therapie);}
}
