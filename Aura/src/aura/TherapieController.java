/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aura;

import MaConnexion.MyConnection;
import entities.Activites;
import entities.Therapie;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import services.serviceActivites;
import services.serviceTherapie;

/**
 * FXML Controller class
 *
 * @author Medimegh
 */
public class TherapieController implements Initializable {

    @FXML
    private DatePicker datee;
    @FXML
    private TextField id;
    @FXML
    private TextField idcoach;
    @FXML
    private TextField nombremax;
    @FXML
    private TextField sujet;
    @FXML
    private TextField lieu;
    @FXML
    private Button show;
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
    private ComboBox combodel;
    @FXML
    private Label lid;
    @FXML
    private ComboBox comboth;
    @FXML
    private Text lidm1;
    @FXML
    private Text lid1;
    @FXML
    private Button addact;
    @FXML
    private Button modifier;
    @FXML
    private ComboBox combotype;
    @FXML
    private ComboBox combodate;
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
    private Button addact1;
    @FXML
    private DatePicker datee1;
    @FXML
    private TextField id1;
    @FXML
    private TextField idcoach1;
    @FXML
    private TextField nombremax1;
    @FXML
    private TextField sujet1;
    @FXML
    private TextField lieu1;
    @FXML
    private Button show1;
    @FXML
    private Label lidd1;
    @FXML
    private ComboBox<?> comboth1;
    @FXML
    private Text lidm11;
    @FXML
    private Text lid11;
    @FXML
    private Button modifier1;
    @FXML
    private AnchorPane tabpropoact;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fillComboBox(); 
         fillComboBoxdate();
         fillComboBoxtype();
        comboth.setVisible(false);
        lidm1.setVisible(false);
        lid1.setVisible(false);
        modifier.setVisible(false);
        loadtablepropo();

        loadetableclth();
        loadtableth();
    }    

    @FXML
    private void addTh(ActionEvent event) {
    serviceTherapie st=new serviceTherapie();
       Therapie T =new  Therapie();
T.setIdcoach(idcoach.getText());
T.setSujet(sujet.getText());
T.setLieu(lieu.getText());
T.setDate(datee.getEditor().getText());
T.setNombremax(Integer.parseInt(nombremax.getText()));
T.setId(Integer.parseInt(id.getText()));
T.setNombre_parti(0);

st.addTherapie(T);
 loadtableth();
  fillComboBox();
  loadetableclth();
clearfields();
   
    }
        private void clearfields() {
lieu.clear();
id.clear();
sujet.clear();
nombremax.clear();
idcoach.clear();
    }

             public void fillComboBox(){
    
        try {
                        String requete = "SELECT id FROM therapie";

           Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                 String id =rs.getString("id"); 
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
lid.setVisible(false);
id.setVisible(false);
loadetableclth();loadtableth();
    }

    @FXML
    private void loadtable(ActionEvent event) {
        serviceTherapie th = new serviceTherapie();
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
 th.supprimerTherapie(c); loadtableth();loadetableclth();
    }

    @FXML
    private void modifierth(ActionEvent event) {
          serviceTherapie Th=new serviceTherapie();
        Therapie T =new  Therapie();
T.setSujet(sujet.getText());
T.setIdcoach(idcoach.getText());
T.setLieu(lieu.getText());
T.setDate(datee.getEditor().getText());
T.setNombremax(Integer.parseInt(nombremax.getText()));
T.setId(Integer.parseInt(lidm1.getText()));

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
           loadetableclth();

    }

    @FXML
    private void fillth(ActionEvent event) {
         Object b= comboth.getSelectionModel().getSelectedItem();
int c  = Integer.parseInt((String) b);      
        System.out.println(c);
    try {
        
                String query = "select * from therapie where  id='"+c+"'";

             Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(query);
                while(rs.next()){
                   lidm1.setText(rs.getString("id"));
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

           Statement st = MyConnection.getInstance().getCnx().createStatement();
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

           Statement st = MyConnection.getInstance().getCnx().createStatement();
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
        Therapie t=   tabth1.getSelectionModel().getSelectedItem();
        System.out.println( t.getId());
         serviceTherapie th=new serviceTherapie();
         th.approuverTherapie(t);
         loadtablepropo();
         loadtableth();
         loadetableclth();
    }

    @FXML
    private void delpropoth(ActionEvent event) {
         Therapie t=  tabth1.getSelectionModel().getSelectedItem();
        System.out.println( t.getId());
        serviceTherapie th=new serviceTherapie();
        th.supprimerpropoTherapie(t.getId());
         loadtablepropo();
         loadtableth();
    }

    @FXML
    private void addpropTh(ActionEvent event) {
        serviceTherapie st=new serviceTherapie();
       Therapie T =new  Therapie();
T.setIdcoach(idcoach1.getText());
T.setSujet(sujet1.getText());
T.setLieu(lieu1.getText());
T.setDate(datee1.getEditor().getText());
T.setNombremax(Integer.parseInt(nombremax1.getText()));
T.setId(Integer.parseInt(id1.getText()));
T.setNombremax(0);

st.addpropTherapie(T);
 loadtablepropo();
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
    private void jointh(ActionEvent event) {
        
        
        Therapie t=   tabth2.getSelectionModel().getSelectedItem();
        System.out.println( t.getId());
         serviceTherapie sa=new serviceTherapie();
         sa.jointh(t);
         loadtableth();
         loadtablepropo();
         loadetableclth();
    }
    
    private void loadetableclth()
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
