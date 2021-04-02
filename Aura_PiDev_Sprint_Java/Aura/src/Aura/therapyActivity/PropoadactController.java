/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aura.therapyActivity;

import Aura.Aura;
import Aura.SideBar.AdminMainController;
import utils.Connexion;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import com.restfb.types.User;
import Entities.Activites;
import Entities.Therapie;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import Service.serviceActivites;
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
    private TableColumn<Activites, Integer> cnbm1;
    @FXML
    private TableColumn<Activites, String> ctype1;
    @FXML
    private TableColumn<Activites, String> cdes1;
    @FXML
    private TableColumn<Activites, String> clieu1;
    @FXML
    private TextArea reasact;
    @FXML
    private Button BACK;
    public String id_user = "";
    @FXML
    private ComboBox<String> triBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadtablepropo();
       triBox.getItems().addAll(
            "Description",
            "Lieu",
            "Date",
            "Type"
        );
    }

    public void initializeFxml(String id) {
    }

    @FXML
    private void appro(ActionEvent event) {
        Activites a = tabact1.getSelectionModel().getSelectedItem();
        System.out.println(a.getId());
        serviceActivites sa = new serviceActivites();
        sa.approuveract(a);
        sendresp("proposition acceptée");

        String accesstkn = "EAAD6VrNplZCYBAL0MUTdrV7SquSINWXACWb0mk3U5kLePWnpdB2Mivl70s6rLZCFk3M7V1MlD5xsDsuy7ZAr0xpWq3g18yF6bN3kedPZARxgYHGM2KtbDKBEkhQfGvEL8qADGLlbkYHd0Yqt2QGLCcXHuP4y1ykMwBdLtZCi8ZAZBA7Wv01W0CQ";
        FacebookClient fbc = new DefaultFacebookClient(accesstkn);
        User me = fbc.fetchObject("me", User.class);
        System.out.println(me.getName());
        // FileInputStream fis=new FileInputStream(new File("C:\\Users\\admin\\Desktop\\sec.mp4"));
        // FacebookType response= fbc.publish("102507478606490/feed",FacebookType.class,BinaryAttachment.with("sec", fis),Parameter.with("message","t"));
        FacebookType response = fbc.publish("102507478606490/feed", FacebookType.class, Parameter.with("message", "New Activity is comming up ,directed by our coach : *" + a.getIdcoach().getNom() + " * in  " + a.getDate() + "  in " + a.getLieu()));
        System.out.println("3");
        loadtablepropo();
    }

    private void loadtablepropo() {

        serviceActivites sa = new serviceActivites();
        ObservableList<Activites> activit = sa.showpropActivites();
        tabact1.setItems(activit);

        cid1.setCellValueFactory(new PropertyValueFactory<Activites, Integer>("id"));
        cidcoach1.setCellValueFactory(new PropertyValueFactory<Activites, String>("idcoach"));
        cduree1.setCellValueFactory(new PropertyValueFactory<Activites, String>("duree"));
        cdate1.setCellValueFactory(new PropertyValueFactory<Activites, String>("date"));
        cnbm1.setCellValueFactory(new PropertyValueFactory<Activites, Integer>("nombremax"));
        ctype1.setCellValueFactory(new PropertyValueFactory<Activites, String>("type"));
        cdes1.setCellValueFactory(new PropertyValueFactory<Activites, String>("description"));
        clieu1.setCellValueFactory(new PropertyValueFactory<Activites, String>("lieu"));
        tabact1.setItems(activit);

    }

    @FXML
    private void decline(ActionEvent event) {
        Activites a = tabact1.getSelectionModel().getSelectedItem();
        System.out.println(a.getId());
        serviceActivites sa = new serviceActivites();
        sa.supprimerpropoactivite(a.getId());
        sendresp("proposition refusée");
        loadtablepropo();

    }

    @FXML
    private void sendmailact(ActionEvent event) {
        sendresp(reasact.getText());
    }

    private void sendresp(String etat) {
        Activites t = tabact1.getSelectionModel().getSelectedItem();
        String n = t.getIdcoach().getEmail();

        service_mail mai = new service_mail();
        System.out.println(n);
        // String reaso= reason.getText();
        // System.out.println(reaso);
        mai.send_mail(n, etat);
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

    @FXML
    private void trier(ActionEvent event) {
         serviceActivites th = new serviceActivites();

 ObservableList<Activites> obl= th.showpropActivites();
        Comparator<Activites> comparator ;
        if(triBox.getValue()=="Description"){
         comparator = Comparator.comparing(Activites::getDescription);
         
        }
        else if(triBox.getValue()=="Date"){
         comparator = Comparator.comparing(Activites::getDate);
        
        }
         else if(triBox.getValue()=="Type"){
         comparator = Comparator.comparing(Activites::getType);
         }
          else if(triBox.getValue()=="Lieu"){
         comparator = Comparator.comparing(Activites::getLieu);
         }
        else{
         comparator = Comparator.comparingInt(Activites::getNombremax);
         
        }
        
        FXCollections.sort(obl, comparator);
           tabact1.setItems(obl);
 
    }
}
