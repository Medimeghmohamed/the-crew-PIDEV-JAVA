/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aura;


import MaConnexion.MyConnection;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import entities.Activites;
import entities.Admin;
import entities.Client;
import entities.Coach;
import entities.Therapie;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.text.TabableView;
import org.controlsfx.control.Rating;
import services.ServiceCoach;
import services.serviceActivites;
import services.service_mail;

/**
 *
 * @author Medimegh
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private TextField duree;
    @FXML
    private TextField id;
    @FXML
    private TextField idcoach;
    @FXML
    private TextField nombremax;
    @FXML
 
    private TextField description;
    @FXML
    private TextField lieu;
    @FXML
    private DatePicker datee;
    @FXML
    private TextField type;
    @FXML
    private Button show;
    @FXML
    private TableColumn<Activites, Integer> cid;
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
    private ComboBox combodel;
    @FXML
    private ComboBox combotype;
    @FXML
    private ComboBox comboate;
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
    private DatePicker datee1;
    @FXML
    private TextField id1;
    @FXML
    private TextField idcoach1;
    @FXML
    private TextField duree1;
    @FXML
    private TextField nombremax1;
    @FXML
    private TextField type1;
    @FXML
    private TextField description1;
    @FXML
    private TextField lieu1;
    @FXML
    private Button show1;
    private TableColumn<Activites,Button> colBtn;
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
    private TextArea reasact;
    
     @FXML
    private Rating rateact;
   
  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                  fillComboBox();
                  fillComboBoxtype();
                  fillcomboboxdate();
                  loadtablepropo();
                  loadtablejoin();
                   // addButtonToTable();

//show tab
         
        serviceActivites sa = new serviceActivites();
        ObservableList<Activites> activit = sa.showActivites();
                tabact.setItems(activit);

        // ... + noms de colonnesa
        cid.setCellValueFactory(new PropertyValueFactory<Activites,Integer>("id"));
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
                || (nombremax.getText().equals(""))||(id.getText().equals(""))){
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
A.setDescription(description.getText());
A.setIdcoach(sc.load_data_modify(idcoach.getText()));
A.setDuree(duree.getText());
A.setLieu(lieu.getText());
A.setType(type.getText());
A.setDate(datee.getEditor().getText());
A.setNombremax(Integer.parseInt(nombremax.getText()));
A.setId(Integer.parseInt(id.getText()));

sa.addActivities(A);lodtabth();
clearfields();
    }}

    @FXML
    private void modify(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("modify.fxml"));
Parent modif = (Parent) fxmlLoader.load();
Stage stage = new Stage();
stage.setScene(new Scene(modif));  
stage.show();lodtabth();
    }

    private void clearfields() {
lieu.clear();
id.clear();
description.clear();
type.clear();
nombremax.clear();
idcoach.clear();
duree.clear();
    }

    @FXML
    private void loadtable(ActionEvent event) {
        
        serviceActivites sa = new serviceActivites();
        ObservableList<Activites> activit = sa.showActivites();
                tabact.setItems(activit);

        cid.setCellValueFactory(new PropertyValueFactory<Activites,Integer>("id"));
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

           Statement st = MyConnection.getInstance().getCnx().createStatement();
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

           Statement st = MyConnection.getInstance().getCnx().createStatement();
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

           Statement st = MyConnection.getInstance().getCnx().createStatement();
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

        cid.setCellValueFactory(new PropertyValueFactory<Activites,Integer>("id"));
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

        cid.setCellValueFactory(new PropertyValueFactory<Activites,Integer>("id"));
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
    private void addpropact(ActionEvent event) {
        serviceActivites sa=new serviceActivites();
        Activites A =new Activites();
ServiceCoach sc = new ServiceCoach();
A.setDescription(description1.getText());
A.setIdcoach(sc.load_data_modify(idcoach1.getText()));
A.setDuree(duree1.getText());
A.setLieu(lieu1.getText());
A.setType(type1.getText());
A.setDate(datee1.getEditor().getText());
A.setNombremax(Integer.parseInt(nombremax1.getText()));
A.setId(Integer.parseInt(id1.getText()));
A.setNombre_parti(0);
loadtablepropo();
sa.addpropActivities(A);

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
    private void joinact(ActionEvent event) {
        
        
        Activites a=   tabact11.getSelectionModel().getSelectedItem();
        System.out.println( a.getId());
         serviceActivites sa=new serviceActivites();
         sa.joinact(a,"10");
         loadtablejoin();
         loadtablepropo();lodtabth();}
        
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

    @FXML
    private void sendmailact(ActionEvent event) {
        Activites t=tabact1.getSelectionModel().getSelectedItem();
         String n=t.getIdcoach().getId();
        System.out.println("1");
        try {
             String requete = "SELECT email FROM coach  WHERE id='"+n+"'";
             System.out.println("2");
            Statement st = MyConnection.getInstance().getCnx().createStatement();
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
            Statement st = MyConnection.getInstance().getCnx().createStatement();
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

        cid.setCellValueFactory(new PropertyValueFactory<Activites,Integer>("id"));
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
    private void rate(ActionEvent event) {
                int s =(int) rateact.getRating();

        System.out.println("test"+s);
        
        Activites a= tabact11.getSelectionModel().getSelectedItem();
        System.out.println( a.getId());
         serviceActivites sa=new serviceActivites();
         sa.updaterating(a,s);
         loadtablejoin();
         loadtablepropo();lodtabth();
    }

   
}




