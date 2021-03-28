/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aura;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import com.restfb.types.User;
import entities.Therapie;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import services.serviceTherapie;
import services.service_mail;

/**
 * FXML Controller class
 *
 * @author Medimegh
 */
public class PropoAdTherapieController implements Initializable {

    @FXML
    private AnchorPane tabpropoact;
      @FXML
    private TableView<Therapie> tabth1;
    @FXML
    private TableColumn<Therapie, Integer> cid1;
    @FXML
    private TableColumn<Therapie, String> cidcoach1;
    @FXML
    private TableColumn<Therapie, String> csujet1;
    @FXML
    private TableColumn<Therapie, String> cdate1;
    @FXML
    private TableColumn<Therapie, Integer> cnbm1;
    @FXML
    private TableColumn<Therapie, String> clieu1;
    @FXML
    private TextArea reason;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       loadtablepropo();
    }    

    @FXML
    private void approuverth(ActionEvent event) {
       
               Alert dialogC = new Alert(Alert.AlertType.CONFIRMATION);
dialogC.setTitle("Approuve");
dialogC.setHeaderText(null);
dialogC.setContentText("Are you sure you want to add this Therapy");
Optional<ButtonType> answer = dialogC.showAndWait();
if (answer.get() == ButtonType.OK) {
System.out.println("User chose OK");
       Therapie t=   tabth1.getSelectionModel().getSelectedItem();
        System.out.println( t.getId());
         serviceTherapie th=new serviceTherapie();
         th.approuverTherapie(t);
         
        mailcoach("proposition acceptée");
  String accesstkn ="EAAD6VrNplZCYBAL0MUTdrV7SquSINWXACWb0mk3U5kLePWnpdB2Mivl70s6rLZCFk3M7V1MlD5xsDsuy7ZAr0xpWq3g18yF6bN3kedPZARxgYHGM2KtbDKBEkhQfGvEL8qADGLlbkYHd0Yqt2QGLCcXHuP4y1ykMwBdLtZCi8ZAZBA7Wv01W0CQ";
        FacebookClient fbc=new DefaultFacebookClient(accesstkn);
        User me= fbc.fetchObject("me",User.class);
        System.out.println(me.getName());
      // FileInputStream fis=new FileInputStream(new File("C:\\Users\\admin\\Desktop\\sec.mp4"));
      // FacebookType response= fbc.publish("102507478606490/feed",FacebookType.class,BinaryAttachment.with("sec", fis),Parameter.with("message","t"));
      FacebookType response= fbc.publish("102507478606490/feed",FacebookType.class,Parameter.with("message","New Therapy is comming up ,directed by our coach : *"+t.getIdcoach().getNom()+" * in  "+t.getDate()+"  in "+t.getLieu()));
       System.out.println("3");
         loadtablepropo();
    
}
else {
System.out.println("User chose Cancel or closed the dialog-box");
}
         
    }

    @FXML
    private void delpropoth(ActionEvent event) {
       
         
         
            Alert dialogC = new Alert(Alert.AlertType.CONFIRMATION);
dialogC.setTitle("DELETE");
dialogC.setHeaderText(null);
dialogC.setContentText("Are you sure you want to delete this Therapy");
Optional<ButtonType> answer = dialogC.showAndWait();
if (answer.get() == ButtonType.OK) {
System.out.println("User chose OK");
       Therapie t=  tabth1.getSelectionModel().getSelectedItem();
        System.out.println( t.getId());
        serviceTherapie th=new serviceTherapie();
        th.supprimerpropoTherapie(t.getId());
        mailcoach("proposition refusée");
         loadtablepropo();
       
}
else {
System.out.println("User chose Cancel or closed the dialog-box");
}
    }
   

    @FXML
    private void mailcoach(ActionEvent event) {
        mailcoach(reason.getText());
    }
      private void loadtablepropo() {
        serviceTherapie th = new serviceTherapie();
        ObservableList<Therapie> therapie = th.showpropTherapie();
                tabth1.setItems(therapie);

        cid1.setCellValueFactory(new PropertyValueFactory<Therapie,Integer>("id"));
        cidcoach1.setCellValueFactory(new PropertyValueFactory<Therapie, String>("idcoach"));
        csujet1.setCellValueFactory(new PropertyValueFactory<Therapie, String>("sujet"));
        cdate1.setCellValueFactory(new PropertyValueFactory<Therapie, String>("date"));
        cnbm1.setCellValueFactory(new PropertyValueFactory<Therapie,Integer>("nombremax"));
        clieu1.setCellValueFactory(new PropertyValueFactory<Therapie, String>("lieu"));


                tabth1.setItems(therapie);


    }
     
       private void mailcoach(String etat)
    {  Therapie t=tabth1.getSelectionModel().getSelectedItem();
         String n=t.getIdcoach().getEmail();
       
              
                 service_mail mai =new service_mail();
        System.out.println( n);
     // String reaso= reason.getText();
       // System.out.println(reaso);
            mai.send_mail(n,etat);
            }
}
