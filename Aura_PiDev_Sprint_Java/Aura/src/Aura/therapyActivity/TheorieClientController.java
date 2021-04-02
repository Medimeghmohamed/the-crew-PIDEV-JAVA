/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aura.therapyActivity;

import Entities.Therapie;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.control.Rating;
import Service.serviceTherapie;
/**
 * FXML Controller class
 *
 * @author Medimegh
 */
public class TheorieClientController implements Initializable {
    public String id_user = "";

 @FXML
    private TableView<Therapie> tabth2;
    @FXML
    private TableColumn<Therapie,Integer> cid2;
    @FXML
    private TableColumn<Therapie,String> cidcoach2;
    @FXML
    private TableColumn<Therapie,String> csujet2;
    @FXML
    private TableColumn<Therapie,String> cdate2;
    @FXML
    private TableColumn<Therapie,Integer> cnbm2;
    @FXML
    private TableColumn<Therapie,String> clieu2;
    @FXML
    private TableColumn<Therapie,Integer> nbpth;
    @FXML
    private Rating rateth;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         loadetableclth();
         
    }     public void initializeFxml(String id) {
     System.out.println(id_user);
    }  

    @FXML
   private void jointh(ActionEvent event) {
        
        
        Therapie t=   tabth2.getSelectionModel().getSelectedItem();
        System.out.println( t.getId());
         serviceTherapie sa=new serviceTherapie();
         sa.jointh(t,id_user);
         loadetableclth();
         org.controlsfx.control.Notifications notifictaionBuilder = org.controlsfx.control.Notifications.create()
                    .title("Aura")
                    .text("Welcome")
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
        int s =(int) rateth.getRating();
        Therapie t=   tabth2.getSelectionModel().getSelectedItem();
        System.out.println( t.getId());
         serviceTherapie sa=new serviceTherapie();
        System.out.println(s);
         sa.updaterating(t,s,id_user);
       
         loadetableclth();
        
    }private void loadetableclth()
    {
    
    serviceTherapie th = new serviceTherapie();
        ObservableList<Therapie> therapie = th.showTherapie();
                tabth2.setItems(therapie);

        cid2.setCellValueFactory(new PropertyValueFactory<Therapie,Integer>("id"));
        cidcoach2.setCellValueFactory(new PropertyValueFactory<Therapie, String>("idcoach"));
        csujet2.setCellValueFactory(new PropertyValueFactory<Therapie, String>("sujet"));
        cdate2.setCellValueFactory(new PropertyValueFactory<Therapie, String>("date"));
        cnbm2.setCellValueFactory(new PropertyValueFactory<Therapie,Integer>("nombremax"));
        clieu2.setCellValueFactory(new PropertyValueFactory<Therapie, String>("lieu"));
        nbpth.setCellValueFactory(new PropertyValueFactory<Therapie,Integer>("nombre_parti"));


                tabth2.setItems(therapie);

    }

    
}
