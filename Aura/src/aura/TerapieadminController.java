/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aura;

import Utils.MaConnexion;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import com.restfb.types.User;
import entities.Activites;
import entities.Coach;
import entities.Therapie;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import services.ServiceCoach;
import services.serviceTherapie;
import services.service_mail;
import org.controlsfx.control.Notifications;
import services.serviceActivites;


/**
 * FXML Controller class
 *
 * @author Medimegh
 */
public class TerapieadminController implements Initializable {

    @FXML
    private Button addact;
    @FXML
    private DatePicker datee;
    private TextField id;
    private TextField idcoach;
    @FXML
    private TextField nombremax;
    @FXML
    private TextField sujet;
    @FXML
    private TextField lieu;
    @FXML
    private Button show;
    private Label lid;
    @FXML
    private ComboBox comboth;
    @FXML
    private Text lidm1;
    @FXML
    private Text lid1;
    @FXML
    private Button modifier;
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
    @FXML
    private ComboBox combodel;
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
    private Text ic;
    @FXML
    private ComboBox<Coach> listcoach;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fillComboBox(); 
         loadlistcoach();
         fillComboBoxdate();
         fillComboBoxtype();
        comboth.setVisible(false);
        lidm1.setVisible(false);
        lid1.setVisible(false);
        modifier.setVisible(false);
        loadtablepropo();

        loadtableth();
        
        
          Notifications notifictaionBuilder = Notifications.create()
                    .title("Aura")
                    .text("It's time to seek a new Therapy")
                    .graphic(null)
                    .position(Pos.BOTTOM_RIGHT)
                    .onAction((ActionEvent event1) -> {
                        System.out.println("Clicked on notifictaion");
            });
            notifictaionBuilder.darkStyle();
            notifictaionBuilder.show();
        
        
        
        
        
        
        
        
    }    

    @FXML
    private void addTh(ActionEvent event) {
    serviceTherapie st=new serviceTherapie();
    ServiceCoach sc=new ServiceCoach();
       Therapie T =new  Therapie();
            Random rand = new Random();
        int randID = rand.nextInt(100000);
T.setIdcoach(listcoach.getSelectionModel().getSelectedItem());
T.setSujet(sujet.getText());
T.setLieu(lieu.getText());
T.setDate(datee.getEditor().getText());
T.setNombremax(Integer.parseInt(nombremax.getText()));
T.setId(randID);
T.setNombre_parti(0);

st.addTherapie(T);
 loadtableth();
  fillComboBox();
  
clearfields();
   

    }
        private void clearfields() {
lieu.clear();

sujet.clear();
nombremax.clear();

    }

             public void fillComboBox(){
    
        try {
                        String requete = "SELECT sujet FROM therapie";

           Statement st = MaConnexion.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                 String id =rs.getString("sujet"); 
                 combodel.getItems().addAll(id);
                 comboth.getItems().addAll(id);   
            }
            st.close();
            rs.close();
        } catch (SQLException ex) { 
        }        
    }

    @FXML
    private void modifyth(ActionEvent event) {
        
        comboth.setVisible(true);
                lidm1.setVisible(true);
                lid1.setVisible(true);
                modifier.setVisible(true);
addact.setVisible(false);
show.setVisible(false);
//lid.setVisible(false);
id.setVisible(false);
loadtableth();
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

    @FXML
    private void deleteth(ActionEvent event) {
         Object b= combodel.getSelectionModel().getSelectedItem();
int c  = Integer.parseInt((String) b);  
 serviceTherapie th = new serviceTherapie();
 th.supprimerTherapie(c); loadtableth();
    }

    @FXML
    private void modifierth(ActionEvent event) {
          serviceTherapie Th=new serviceTherapie();
           ServiceCoach sc=new ServiceCoach();
        Therapie T =new  Therapie();
T.setSujet(sujet.getText());
T.setIdcoach(listcoach.getSelectionModel().getSelectedItem());
T.setLieu(lieu.getText());
T.setDate(datee.getEditor().getText());
T.setNombremax(Integer.parseInt(nombremax.getText()));
//T.setId(Integer.parseInt(lidm1.getText()));

Th.modifierTherapie(T);

comboth.setVisible(false);
                lidm1.setVisible(false);
                lid1.setVisible(false);
                                modifier.setVisible(false);

addact.setVisible(true);
show.setVisible(true);
lid.setVisible(true);
id.setVisible(true);
           loadtableth();
          

    }

    @FXML
    private void fillth(ActionEvent event) {
         Object b= comboth.getSelectionModel().getSelectedItem();
    try {
        
                String query = "select * from therapie where  sujet='"+b+"'";

             Statement st = MaConnexion.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(query);
                while(rs.next()){
                   lidm1.setText(rs.getString("sujet"));
                   // id.setText(rs.getString("id"));
                    sujet.setText(rs.getString("sujet"));
                    lieu.setText(rs.getString("lieu"));
                    nombremax.setText(rs.getString("nombremax"));
                    idcoach.setText(rs.getString("idcoach"));
                    (datee.getEditor()).setText(rs.getString("date"));
                }
                rs.close();
                
              
            } catch (SQLException ex) {
               
            }
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
         loadtableth();
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
         loadtableth();
}
else {
System.out.println("User chose Cancel or closed the dialog-box");
}
    }
   

    @FXML
    private void mailcoach(ActionEvent event) {
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
    public void loadlistcoach()
{
 ServiceCoach sa = new  ServiceCoach();
        ObservableList<Coach> coach =  (ObservableList<Coach>) sa.afficherCoach_Oui();
               listcoach.setItems(coach);

}
}
