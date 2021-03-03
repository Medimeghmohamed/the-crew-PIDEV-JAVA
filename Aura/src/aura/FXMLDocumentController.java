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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.text.TabableView;
import services.serviceActivites;

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
    @FXML
    private TableColumn<Activites,Button> colBtn;
    
  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                  fillComboBox();
                  fillComboBoxtype();
                  fillcomboboxdate();
                  loadtablepropo();
                   // addButtonToTable();

//show tab
         
        serviceActivites sa = new serviceActivites();
        ObservableList<Activites> activit = sa.showActivites();
                tabact.setItems(activit);

        // ... + noms de colonnesa
        cid.setCellValueFactory(new PropertyValueFactory<Activites,Integer>("id"));
        cidcoach.setCellValueFactory(new PropertyValueFactory<Activites, String>("idcoach"));
        cduree.setCellValueFactory(new PropertyValueFactory<Activites, String>("iduree"));
        cdate.setCellValueFactory(new PropertyValueFactory<Activites, String>("date"));
        cnbm.setCellValueFactory(new PropertyValueFactory<Activites,Integer>("nombremax"));
        ctype.setCellValueFactory(new PropertyValueFactory<Activites, String>("type"));
        cdes.setCellValueFactory(new PropertyValueFactory<Activites, String>("description"));
        clieu.setCellValueFactory(new PropertyValueFactory<Activites, String>("lieu"));




        tabact.setItems(activit);
         
    }    

    @FXML
    private void addact(ActionEvent event) {
        serviceActivites sa=new serviceActivites();
        Activites A =new Activites();
A.setDescription(description.getText());
A.setIdcoach(idcoach.getText());
A.setDuree(duree.getText());
A.setLieu(lieu.getText());
A.setType(type.getText());
A.setDate(datee.getEditor().getText());
A.setNombremax(Integer.parseInt(nombremax.getText()));
A.setId(Integer.parseInt(id.getText()));

sa.addActivities(A);
clearfields();
    }

    @FXML
    private void modify(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("modify.fxml"));
Parent modif = (Parent) fxmlLoader.load();
Stage stage = new Stage();
stage.setScene(new Scene(modif));  
stage.show();
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
        cduree.setCellValueFactory(new PropertyValueFactory<Activites, String>("iduree"));
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
 act.supprimeractivite(c);
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
        cduree.setCellValueFactory(new PropertyValueFactory<Activites, String>("iduree"));
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
        cduree.setCellValueFactory(new PropertyValueFactory<Activites, String>("iduree"));
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
A.setDescription(description1.getText());
A.setIdcoach(idcoach1.getText());
A.setDuree(duree1.getText());
A.setLieu(lieu1.getText());
A.setType(type1.getText());
A.setDate(datee1.getEditor().getText());
A.setNombremax(Integer.parseInt(nombremax1.getText()));
A.setId(Integer.parseInt(id1.getText()));
A.setNombre_parti(0);

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
                        colBtn.setCellValueFactory(new PropertyValueFactory<Activites,Button>("btn"));

        tabact1.setItems(activit);

    }
// private void addButtonToTable() {
//      colBtn = new TableColumn("Button Column");
//
//        Callback<TableColumn<Activites, Void>, TableCell<Activites, Void>> cellFactory = new Callback<TableColumn<Activites, Void>, TableCell<Activites, Void>>() {
//            @Override
//            public TableCell<Activites, Void> call(final TableColumn<Activites, Void> param) {
//                final TableCell<Activites, Void> cell = new TableCell<Activites, Void>() {
//
//                    private final Button btn = new Button("Action");
//
//                    {
//                        btn.setOnAction((ActionEvent event) -> {
//                            Activites data = getTableView().getItems().get(getIndex());
//                            System.out.println("selectedData: " + data);
//                        });
//                    }
//
//                    @Override
//                    public void updateItem(Void item, boolean empty) {
//                        super.updateItem(item, empty);
//                        if (empty) {
//                            setGraphic(null);
//                        } else {
//                            setGraphic(btn);
//                        }
//                    }
//                };
//                return cell;
//            }
//        };
//
//        colBtn.setCellFactory(cellFactory);
//
//        tabact1.getColumns().add(colBtn);
//
//    }
//    
// 

    @FXML
    private void appro(ActionEvent event) {
     Activites a=   tabact1.getSelectionModel().getSelectedItem();
        System.out.println( a.getId());
         serviceActivites sa=new serviceActivites();
         sa.approuveract(a);
         loadtablepropo();
    }

    @FXML
    private void decline(ActionEvent event) {
        Activites a=  tabact1.getSelectionModel().getSelectedItem();
        System.out.println( a.getId());
         serviceActivites sa=new serviceActivites();
         sa.supprimerpropoactivite(a.getId());
         loadtablepropo();
    }
  
}
