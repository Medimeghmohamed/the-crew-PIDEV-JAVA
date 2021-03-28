/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aura;

import Utils.MaConnexion;
import entities.Activites;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import services.serviceActivites;
import services.service_mail;

/**
 * FXML Controller class
 *
 * @author Medimegh
 */
public class PropoadactController implements Initializable {
 @FXML
  private TableView<Activites> tabact1;
    @FXML
    private TableColumn<Activites, Integer> cid1;
    @FXML
    private TableColumn<Activites, String> cidcoach1;
    @FXML
    private TableColumn<Activites, String> cduree1;
    @FXML
    private TableColumn<Activites, String> cdate1;
    @FXML
    private TableColumn<Activites, Integer>cnbm1;
    @FXML
    private TableColumn<Activites, String>ctype1;
    @FXML
    private TableColumn<Activites, String> cdes1;
    @FXML
    private TableColumn<Activites, String> clieu1;
    @FXML
    private TextArea reasact;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       loadtablepropo() ;
    }    

    @FXML
    private void appro(ActionEvent event) {
     Activites a=   tabact1.getSelectionModel().getSelectedItem();
        System.out.println( a.getId());
         serviceActivites sa=new serviceActivites();
         sa.approuveract(a);
                  sendresp("proposition acceptée");

         loadtablepropo();
    }
 private void loadtablepropo() {
        
        serviceActivites sa = new serviceActivites();
        ObservableList<Activites> activit = sa.showpropActivites();
                tabact1.setItems(activit);

        cid1.setCellValueFactory(new PropertyValueFactory<Activites,Integer>("id"));
        cidcoach1.setCellValueFactory(new PropertyValueFactory<Activites, String>("idcoach"));
        cduree1.setCellValueFactory(new PropertyValueFactory<Activites, String>("duree"));
        cdate1.setCellValueFactory(new PropertyValueFactory<Activites, String>("date"));
        cnbm1.setCellValueFactory(new PropertyValueFactory<Activites,Integer>("nombremax"));
        ctype1.setCellValueFactory(new PropertyValueFactory<Activites, String>("type"));
        cdes1.setCellValueFactory(new PropertyValueFactory<Activites, String>("description"));
        clieu1.setCellValueFactory(new PropertyValueFactory<Activites, String>("lieu"));
        tabact1.setItems(activit);

    }
    @FXML
    private void decline(ActionEvent event) {
        Activites a=  tabact1.getSelectionModel().getSelectedItem();
        System.out.println( a.getId());
         serviceActivites sa=new serviceActivites();
         sa.supprimerpropoactivite(a.getId());
                  sendresp("proposition refusée");
         loadtablepropo();
         
    }
    @FXML
    private void sendmailact(ActionEvent event) {
       sendresp(reasact.getText());
    }
    
    
    
    private void sendresp(String etat)
    {Activites t=tabact1.getSelectionModel().getSelectedItem();
         String n=t.getIdcoach().getEmail();
       
                 service_mail mai =new service_mail();
        System.out.println( n);
     // String reaso= reason.getText();
       // System.out.println(reaso);
            mai.send_mail(n,etat);
    }
}
