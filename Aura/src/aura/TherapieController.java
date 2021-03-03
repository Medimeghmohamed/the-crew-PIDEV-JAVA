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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fillComboBox();    
        comboth.setVisible(false);
        lidm1.setVisible(false);
        lid1.setVisible(false);
        modifier.setVisible(false);

        
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
    private void addTh(ActionEvent event) {
    serviceTherapie st=new serviceTherapie();
       Therapie T =new  Therapie();
T.setIdcoach(idcoach.getText());
T.setSujet(sujet.getText());
T.setLieu(lieu.getText());
T.setDate(datee.getEditor().getText());
T.setNombremax(Integer.parseInt(nombremax.getText()));
T.setId(Integer.parseInt(id.getText()));
st.addTherapie(T);

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
 th.supprimerTherapie(c);
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
    
}
