/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aura.therapyActivity;

import Aura.Aura;
import Aura.SideBar.AdminMainController;
import utils.Connexion;
import Entities.Therapie;
import Service.ServiceCoach;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import Service.serviceTherapie;
import java.sql.Connection;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Medimegh
 */
public class ShowadtTherapieController implements Initializable {

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
    private Button Proposition;
    @FXML
    private Button ADD;
        public String id_user = "";
    @FXML
    private TextField findtabth;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     loadtableth();
     fillComboBoxdate();fillComboBoxtype();
      recherche_avan();
    }    
     public void initializeFxml(String id) {
        System.out.println(id_user);
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

     public void fillComboBoxtype(){
    
        try {
                        String requete = "SELECT sujet FROM therapie";

           Statement st = Connexion.getInstance().getConnection().createStatement();
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

           Statement st = Connexion.getInstance().getConnection().createStatement();
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
    private void deletetherapiefromtab(ActionEvent event) {
     Alert dialogC = new Alert(Alert.AlertType.CONFIRMATION);
dialogC.setTitle("DELETE");
dialogC.setHeaderText(null);
dialogC.setContentText("Êtes-vous sûr de vouloir supprimer cette thérapie");
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
    private void Proposition(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/SideBar/AdminMain.fxml"));

        Parent root = loader.load();

        AdminMainController HomeScene = loader.getController();
        HomeScene.propositionth_nav(id_user);
        HomeScene.id_user = id_user;
        HomeScene.show_data(id_user);
        String css = Aura.class.getResource("Style.css").toExternalForm();

        root.getStylesheets().add(css);

        Stage window = (Stage) Proposition.getScene().getWindow();

        window.setScene(new Scene(root, 1182, 718));
    }

    @FXML
    private void ADD(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/SideBar/AdminMain.fxml"));

        Parent root = loader.load();

        AdminMainController HomeScene = loader.getController();
        HomeScene.addth_nav(id_user);
        HomeScene.id_user = id_user;
        HomeScene.show_data(id_user);
        String css = Aura.class.getResource("Style.css").toExternalForm();

        root.getStylesheets().add(css);

        Stage window = (Stage) Proposition.getScene().getWindow();

        window.setScene(new Scene(root, 1182, 718));
 
    }
    private void recherche_avan() {
        
       serviceTherapie th = new serviceTherapie();
        ObservableList<Therapie> therapie = FXCollections.observableArrayList();
                tabth.setItems(therapie);
        ServiceCoach sc=new ServiceCoach();
        cid.setCellValueFactory(new PropertyValueFactory<Therapie,Integer>("id"));
        cidcoach.setCellValueFactory(new PropertyValueFactory<Therapie, String>("idcoach"));
        csujet.setCellValueFactory(new PropertyValueFactory<Therapie, String>("sujet"));
        cdate.setCellValueFactory(new PropertyValueFactory<Therapie, String>("date"));
        cnbm.setCellValueFactory(new PropertyValueFactory<Therapie,Integer>("nombremax"));
        clieu.setCellValueFactory(new PropertyValueFactory<Therapie, String>("lieu"));

       
        try {
        
            String requete = "SELECT * FROM therapie";
         
            Connection cnx = Connexion.getInstance().getConnection();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                  
              Therapie T = new Therapie();
                T.setId(rs.getInt(1));
                T.setSujet(rs.getString(2));
                T.setDate(rs.getString(3));
                T.setLieu(rs.getString(4));
                T.setNombremax(rs.getInt(5));
                T.setIdcoach(sc.load_data_modify(rs.getString(6)));
                        T.setNombre_parti(rs.getInt(7));
               therapie.add(T);
                
                
                System.out.println("!!!");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        ObservableList<Therapie> dataList = therapie;
        tabth.setItems(dataList);
        FilteredList<Therapie> filteredData = new FilteredList<>(dataList, b -> true);
        findtabth.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Therapie -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                 if (Therapie.getDate().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; 
                } else if (Therapie.getLieu().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (Therapie.getSujet().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; 
                } else if (Integer.toString(Therapie.getNombremax()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; 
                }  else {
                    return false; 
                }
            });
        });

        tabth.setItems(filteredData);
    
    }
    
}
