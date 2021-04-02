/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aura.therapyActivity;

import Aura.Aura;
import Aura.SideBar.AdminMainController;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import com.restfb.types.User;
import Entities.Therapie;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import Service.serviceTherapie;
import Service.service_mail;
import java.util.Comparator;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;

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
    @FXML
    private Button BACK;    public String id_user = "";
    @FXML
    private ComboBox<String> triBox;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       loadtablepropo();
      triBox.getItems().addAll(
            "Sujet",
            "Date"
        );
    }    
  public void initializeFxml(String id) {
        System.out.println(id_user);
    }
    @FXML
    private void approuverth(ActionEvent event) {
       
               Alert dialogC = new Alert(Alert.AlertType.CONFIRMATION);
dialogC.setTitle("Approuve");
dialogC.setHeaderText(null);
dialogC.setContentText("Êtes-vous sûr de vouloir ajouter cette thérapie");
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
dialogC.setContentText("Êtes-vous sûr de vouloir supprimer cette thérapie");
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

    @FXML
    private void trier(ActionEvent event) {
            serviceTherapie th = new serviceTherapie();

 ObservableList<Therapie> obl= th.showpropTherapie();
        Comparator<Therapie> comparator ;
        if(triBox.getValue()=="Sujet"){
         comparator = Comparator.comparing(Therapie::getSujet);
         
        }
        else if(triBox.getValue()=="Date"){
         comparator = Comparator.comparing(Therapie::getDate);
         
        }
        else{
         comparator = Comparator.comparingInt(Therapie::getNombremax);
         
        }
        
        FXCollections.sort(obl, comparator);
           tabth1.setItems(obl);
 

    }
    
    
   
    
}
