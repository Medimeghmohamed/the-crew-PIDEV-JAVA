 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aura.therapyActivity;

import utils.Connexion;
import Entities.Activites;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.control.Rating;
import Service.serviceActivites;
import org.controlsfx.control.Notifications;


/**
 * FXML Controller class
 *
 * @author Medimegh
 */
public class ActiviteClientController implements Initializable {
    public String id_user = "";

    @FXML
    private TableView<Activites> tabact11;
    @FXML
    private TableColumn<Activites, Integer> cid11;
    @FXML
    private TableColumn<Activites, String> cidcoach11;
    @FXML
    private TableColumn<Activites, String> cduree11;
    @FXML
    private TableColumn<Activites, String> cdate11;
    @FXML
    private TableColumn<Activites, Integer> cnbm11;
    @FXML
    private TableColumn<Activites, String> ctype11;
    @FXML
    private TableColumn<Activites, String> cdes11;
    @FXML
    private TableColumn<Activites, String>clieu11;
    @FXML
    private TableColumn<Activites, Integer> cnbpar;
    @FXML
    private Rating rateact;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      loadtablejoin() ;
      
      
       serviceActivites sa = new serviceActivites();
       int i=0;
    try {
         String requete = " SELECT  avg(rating) FROM participationactivté  WHERE id_activite = 20 " ;
            Statement st = Connexion.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(requete);
          
             while (rs.next())
             {
            i=rs.getInt(1);
                System.out.println(i);
             }

    }
catch (Exception e) {
    
    }
    
      //  System.out.println( sa.sommerating(20));
    }    
 public void initializeFxml(String id) {
     System.out.println(id_user);
    }  
    @FXML
        private void joinact(ActionEvent event) {
        
        
        Activites a=tabact11.getSelectionModel().getSelectedItem();
        System.out.println( a.getId());
         serviceActivites sa=new serviceActivites();
        //user id
         sa.joinact(a,"10");
         loadtablejoin();
         Notifications notifictaionBuilder = Notifications.create()
                    .title("Aura")
                    .text("New Gladiator a rejoint l'équipe ")
                    .graphic(null)
                    .position(Pos.BOTTOM_RIGHT)
                    .onAction((ActionEvent event1) -> {
                        System.out.println("Clicked on notifictaion");
            });
            notifictaionBuilder.darkStyle();
            notifictaionBuilder.show();
        }


    @FXML
   private void rate(ActionEvent event) {
                int s =(int) rateact.getRating();

        System.out.println("test"+s);
        
        Activites a= tabact11.getSelectionModel().getSelectedItem();
        System.out.println( a.getId());
         serviceActivites sa=new serviceActivites();
         sa.updaterating(a,s,id_user);
         loadtablejoin();
       Notifications notifictaionBuilder = Notifications.create()
                    .title("Aura")
                    .text("Merci ")
                    .graphic(null)
                    .position(Pos.BOTTOM_RIGHT)
                    .onAction((ActionEvent event1) -> {
                        System.out.println("Clicked on notifictaion");
            });
            notifictaionBuilder.darkStyle();
            notifictaionBuilder.show();
         
         
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("RATING");
                alert.setHeaderText(null);
                alert.setContentText("Fait");
                alert.showAndWait();
    }
    private void loadtablejoin() {
        
        serviceActivites sa = new serviceActivites();
        ObservableList<Activites> activit = sa.showActivites();

        cid11.setCellValueFactory(new PropertyValueFactory<Activites,Integer>("id"));
        cidcoach11.setCellValueFactory(new PropertyValueFactory<Activites, String>("idcoach"));
        cduree11.setCellValueFactory(new PropertyValueFactory<Activites, String>("duree"));
        cdate11.setCellValueFactory(new PropertyValueFactory<Activites, String>("date"));
        cnbm11.setCellValueFactory(new PropertyValueFactory<Activites,Integer>("nombremax"));
        ctype11.setCellValueFactory(new PropertyValueFactory<Activites, String>("type"));
        cdes11.setCellValueFactory(new PropertyValueFactory<Activites, String>("description"));
        clieu11.setCellValueFactory(new PropertyValueFactory<Activites, String>("lieu"));

                cnbpar.setCellValueFactory(new PropertyValueFactory<Activites, Integer>("nombre_parti"));

        tabact11.setItems(activit);
    }
}
