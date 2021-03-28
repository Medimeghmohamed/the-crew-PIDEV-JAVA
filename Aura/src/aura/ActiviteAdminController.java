/*
 * To change this license header, choose License Headers in Project Properties. 
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aura;

import Utils.MaConnexion;
import entities.Activites;
import entities.Coach;
import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import services.ServiceCoach;
import services.serviceActivites;
import services.service_mail;

/**
 * FXML Controller class
 *
 * @author Medimegh
 */
public class ActiviteAdminController implements Initializable {

    @FXML
    private DatePicker datee;
    private TextField id;
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
  // private TableColumn<Activites, Integer> cid;
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
    private ComboBox combodel;
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
    @FXML
    private ComboBox<Coach> listcoach;
    @FXML
    private Button update;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
   fillComboBox();
                  fillComboBoxtype();
                  fillcomboboxdate();
                  loadtablepropo();
                  loadlistcoach();
                   // addButtonToTable();

//show tab
         
        serviceActivites sa = new serviceActivites();
        ObservableList<Activites> activit = sa.showActivites();
                tabact.setItems(activit);

                // ... + noms de colonnesa
//        cid.setCellValueFactory(new PropertyValueFactory<Activites,Integer>("id"));
        cidcoach.setCellValueFactory(new PropertyValueFactory<Activites,String>("idcoach"));
        cduree.setCellValueFactory(new PropertyValueFactory<Activites, String>("duree"));
        cdate.setCellValueFactory(new PropertyValueFactory<Activites, String>("date"));
        cnbm.setCellValueFactory(new PropertyValueFactory<Activites,Integer>("nombremax"));
        ctype.setCellValueFactory(new PropertyValueFactory<Activites, String>("type"));
        cdes.setCellValueFactory(new PropertyValueFactory<Activites, String>("description"));
        clieu.setCellValueFactory(new PropertyValueFactory<Activites, String>("lieu"));
        tabact.setItems(activit);
         
    }    

    @FXML
    private void addact(ActionEvent event) {
       
        if((description.getText().equals("") ) || (idcoach.getText().equals("")) || (duree.getText().equals("")) || (lieu.getText().equals("")) || (type.getText().equals("")) || (datee.getEditor().getText().equals(""))
                || (nombremax.getText().equals(""))){
            System.out.println("noooo");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Champ manquant");
                alert.setHeaderText(null);
                alert.setContentText("IL FAUT REMPLIR TOUS LES CHAMPS");
                alert.showAndWait();
        }
        else
        {System.out.println("yes");
         serviceActivites sa=new serviceActivites();
               ServiceCoach sc = new ServiceCoach();
        Activites A =new Activites();
            Random rand = new Random();
        int randID = rand.nextInt(100000);
A.setDescription(description.getText());
A.setIdcoach(listcoach.getSelectionModel().getSelectedItem());
A.setDuree(duree.getText());
A.setLieu(lieu.getText());
A.setType(type.getText());
A.setDate(datee.getEditor().getText());
A.setNombremax(Integer.parseInt(nombremax.getText()));
A.setId(randID);


sa.addActivities(A);lodtabth();
clearfields();
    }}

    @FXML
    private void modify(ActionEvent event) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("modify.fxml"));
//Parent modif = (Parent) fxmlLoader.load();
//Stage stage = new Stage();
//stage.setScene(new Scene(modif));  
//stage.show();lodtabth();

FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        Parent root = loader.load();
        MainController HomeScene = loader.getController();
        Pane view;
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("modify.fxml"));
        view = loader2.load();
        HomeScene.returnPane(view);
        Stage window = (Stage)update.getScene().getWindow();
        window.setScene(new Scene(root, 1182, 718));
    }

    private void clearfields() {
lieu.clear();

description.clear();
type.clear();
nombremax.clear();

duree.clear();
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
    
             public void fillComboBox(){
    
        try {
                        String requete = "SELECT id FROM activite";

           Statement st = MaConnexion.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            
            while(rs.next()){
                 String id =rs.getString("id"); 
                 combodel.getItems().addAll(id);
                 
            }
            
            st.close();
            rs.close();
        } catch (SQLException ex) { 
        }        
    }

    @FXML
    private void deleteact(ActionEvent event) {
        Object b= combodel.getSelectionModel().getSelectedItem();
int c  = Integer.parseInt((String) b);  
 serviceActivites act = new serviceActivites();
 act.supprimeractivite(c);lodtabth();
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
    private void appro(ActionEvent event) {
     Activites a=   tabact1.getSelectionModel().getSelectedItem();
        System.out.println( a.getId());
         serviceActivites sa=new serviceActivites();
         sa.approuveract(a);
                  sendresp("proposition acceptée");

         loadtablepropo();lodtabth();
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
        Activites t=tabact1.getSelectionModel().getSelectedItem();
         String n=t.getIdcoach().getId();
        System.out.println("1");
        try {
             String requete = "SELECT email FROM coach  WHERE id='"+n+"'";
             System.out.println("2");
            Statement st = MaConnexion.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            System.out.println("3");
            while (rs.next()) {
              rs.getString("email"); 
              
              System.out.println("4");
                 service_mail mai =new service_mail();
        System.out.println( rs.getString("email"));
      String reaso= reasact.getText();
        System.out.println(reaso);
            mai.send_mail( rs.getString("email"),reaso);
            }
                 
         }catch (Exception e) {
        }
    }
    
    
    
    private void sendresp(String etat)
    {Activites t=tabact1.getSelectionModel().getSelectedItem();
         String n=t.getIdcoach().getId();
        try {
             String requete = "SELECT email FROM coach  WHERE id='"+n+"'";
            Statement st = MaConnexion.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
              rs.getString("email"); 
              
                 service_mail mai =new service_mail();
        System.out.println( rs.getString("email"));
      String reaso= reasact.getText();
            mai.send_mail( rs.getString("email"),etat);
            }
                 
         }catch (Exception e) {
        }
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
          
         
         
         
         
         
         Alert dialogC = new Alert(AlertType.CONFIRMATION);
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

public void loadlistcoach()
{
 ServiceCoach sa = new  ServiceCoach();
        ObservableList<Coach> activit =  (ObservableList<Coach>) sa.afficherCoach_Oui();
               listcoach.setItems(activit);

}
   
}