/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aura.therapyActivity;

import Aura.Aura;
import Aura.SideBar.AdminMainController;
import utils.Connexion;
import Entities.Activites;
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
import Service.serviceActivites;
import Service.serviceTherapie;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javax.swing.JOptionPane;


/**
 * FXML Controller class
 *
 * @author Medimegh
 */
public class ShowadactController implements Initializable {

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
    private Button ADD;
    @FXML
    private Button Proposition;
    public String id_user = "";
    @FXML
    private TextField rechact;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lodtabth();
        fillComboBoxtype();
        fillcomboboxdate();
        recherche_avan();
       
    }

    public void initializeFxml(String id) {
        System.out.println(id_user);
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
        cnbm.setCellValueFactory(new PropertyValueFactory<Activites, Integer>("nombremax"));
        ctype.setCellValueFactory(new PropertyValueFactory<Activites, String>("type"));
        cdes.setCellValueFactory(new PropertyValueFactory<Activites, String>("description"));
        clieu.setCellValueFactory(new PropertyValueFactory<Activites, String>("lieu"));

        tabact.setItems(activit);
    }

    @FXML
    private void findtype(ActionEvent event) {
        serviceActivites sa = new serviceActivites();
        ObservableList<Activites> activit = (ObservableList<Activites>) sa.findtype((String) combotype.getSelectionModel().getSelectedItem());
        tabact.setItems(activit);

        // cid.setCellValueFactory(new PropertyValueFactory<Activites,Integer>("id"));
        cidcoach.setCellValueFactory(new PropertyValueFactory<Activites, String>("idcoach"));
        cduree.setCellValueFactory(new PropertyValueFactory<Activites, String>("duree"));
        cdate.setCellValueFactory(new PropertyValueFactory<Activites, String>("date"));
        cnbm.setCellValueFactory(new PropertyValueFactory<Activites, Integer>("nombremax"));
        ctype.setCellValueFactory(new PropertyValueFactory<Activites, String>("type"));
        cdes.setCellValueFactory(new PropertyValueFactory<Activites, String>("description"));
        clieu.setCellValueFactory(new PropertyValueFactory<Activites, String>("lieu"));

        tabact.setItems(activit);
    }

    @FXML
    private void finddate(ActionEvent event) {
        serviceActivites sa = new serviceActivites();
        ObservableList<Activites> activit = (ObservableList<Activites>) sa.finddate((String) comboate.getSelectionModel().getSelectedItem());
        tabact.setItems(activit);

        //  cid.setCellValueFactory(new PropertyValueFactory<Activites,Integer>("id"));
        cidcoach.setCellValueFactory(new PropertyValueFactory<Activites, String>("idcoach"));
        cduree.setCellValueFactory(new PropertyValueFactory<Activites, String>("duree"));
        cdate.setCellValueFactory(new PropertyValueFactory<Activites, String>("date"));
        cnbm.setCellValueFactory(new PropertyValueFactory<Activites, Integer>("nombremax"));
        ctype.setCellValueFactory(new PropertyValueFactory<Activites, String>("type"));
        cdes.setCellValueFactory(new PropertyValueFactory<Activites, String>("description"));
        clieu.setCellValueFactory(new PropertyValueFactory<Activites, String>("lieu"));

        tabact.setItems(activit);
    }

    private void lodtabth() {
      
        serviceActivites sa = new serviceActivites();
        ObservableList<Activites> activit = sa.showActivites();
        tabact.setItems(activit);

        //  cid.setCellValueFactory(new PropertyValueFactory<Activites,Integer>("id"));
        cidcoach.setCellValueFactory(new PropertyValueFactory<Activites, String>("idcoach"));
        cduree.setCellValueFactory(new PropertyValueFactory<Activites, String>("duree"));
        cdate.setCellValueFactory(new PropertyValueFactory<Activites, String>("date"));
        cnbm.setCellValueFactory(new PropertyValueFactory<Activites, Integer>("nombremax"));
        ctype.setCellValueFactory(new PropertyValueFactory<Activites, String>("type"));
        cdes.setCellValueFactory(new PropertyValueFactory<Activites, String>("description"));
        clieu.setCellValueFactory(new PropertyValueFactory<Activites, String>("lieu"));

        tabact.setItems(activit);

    }

    @FXML
    private void deletefromtable(ActionEvent event) {

        Alert dialogC = new Alert(Alert.AlertType.CONFIRMATION);
        dialogC.setTitle("DELETE");
        dialogC.setHeaderText(null);
        dialogC.setContentText("Êtes-vous sûr de vouloir supprimer cette activité");
        Optional<ButtonType> answer = dialogC.showAndWait();
        if (answer.get() == ButtonType.OK) {
            System.out.println("User chose OK");
            Activites a = tabact.getSelectionModel().getSelectedItem();
            System.out.println(a.getId());
            serviceActivites sa = new serviceActivites();
            sa.supprimeractivite(a.getId());
            System.out.println("supprimer");
            lodtabth();
        } else {
            System.out.println("User chose Cancel or closed the dialog-box");
        }

    }

    public void fillComboBoxtype() {

        try {
            String requete = "SELECT type FROM activite";

            Statement st = Connexion.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                String type = rs.getString("type");
                combotype.getItems().addAll(type);

            }
            st.close();
            rs.close();
        } catch (SQLException ex) {
        }
    }

    public void fillcomboboxdate() {
        try {
            String requete = "SELECT date FROM activite";

            Statement st = Connexion.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                String date = rs.getString("date");

                comboate.getItems().addAll(date);

            }
            st.close();
            rs.close();
        } catch (SQLException ex) {
        }
    }

    @FXML
    private void ADD(ActionEvent event) throws IOException {
       
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/SideBar/AdminMain.fxml"));

        Parent root = loader.load();

        AdminMainController HomeScene = loader.getController();
        HomeScene.AddAct_nav(id_user);
        HomeScene.id_user = id_user;
        HomeScene.show_data(id_user);
        String css = Aura.class.getResource("Style.css").toExternalForm();

        root.getStylesheets().add(css);

        Stage window = (Stage) ADD.getScene().getWindow();

        window.setScene(new Scene(root, 1182, 718));
    
    }

    @FXML
    private void Proposition(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/SideBar/AdminMain.fxml"));

        Parent root = loader.load();

        AdminMainController HomeScene = loader.getController();
        HomeScene.proposition_nav(id_user);
        HomeScene.id_user = id_user;
        HomeScene.show_data(id_user);
        String css = Aura.class.getResource("Style.css").toExternalForm();

        root.getStylesheets().add(css);

        Stage window = (Stage) Proposition.getScene().getWindow();

        window.setScene(new Scene(root, 1182, 718));
    }
        private void recherche_avan() {
        
       serviceTherapie th = new serviceTherapie();
        ObservableList<Activites> acti = FXCollections.observableArrayList();
                tabact.setItems(acti);
        ServiceCoach sc=new ServiceCoach();
      cidcoach.setCellValueFactory(new PropertyValueFactory<Activites, String>("idcoach"));
        cduree.setCellValueFactory(new PropertyValueFactory<Activites, String>("duree"));
        cdate.setCellValueFactory(new PropertyValueFactory<Activites, String>("date"));
        cnbm.setCellValueFactory(new PropertyValueFactory<Activites, Integer>("nombremax"));
        ctype.setCellValueFactory(new PropertyValueFactory<Activites, String>("type"));
        cdes.setCellValueFactory(new PropertyValueFactory<Activites, String>("description"));
        clieu.setCellValueFactory(new PropertyValueFactory<Activites, String>("lieu"));

       
        try {
        
            String requete = "SELECT * FROM activite";
         
            Connection cnx = Connexion.getInstance().getConnection();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                  
               Activites A = new Activites();
                A.setId(rs.getInt(1));
                A.setIdcoach(sc.load_data_modify(rs.getString(2)));
                A.setDuree(rs.getString(3));
                A.setDate(rs.getString(4));
                A.setNombremax(rs.getInt(5));
                A.setType(rs.getString(6));
                A.setDescription(rs.getString(7));
                A.setLieu(rs.getString(8));
               
               acti.add(A);
                
                
                System.out.println("!!!");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        ObservableList<Activites> dataList = acti;
        tabact.setItems(dataList);
        FilteredList<Activites> filteredData = new FilteredList<>(dataList, b -> true);
        rechact.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Activites -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                 if (Activites.getDate().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; 
                } else if (Activites.getLieu().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (Activites.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; 
                } else if (Integer.toString(Activites.getNombremax()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; 
                } else if (Activites.getType().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; 
                }  else {
                    return false; 
                }
            });
        });

        tabact.setItems(filteredData);
    
    }
        public static void saveRecord(String ID, String name,String age,String filepath)
    {
        try
        {
           FileWriter fw = new FileWriter(filepath,true);
           BufferedWriter bw = new BufferedWriter(fw) ;
           PrintWriter pw = new PrintWriter(bw);
           
           pw.println(ID+"    "+name+"   "+age);
                      pw.println(name);

           pw.flush();
           pw.close(); 
           
            JOptionPane.showMessageDialog(null, "Record saved");
        }
        catch(Exception E)
        {
            JOptionPane.showMessageDialog(null, "Record not saved");
        }
         


    }

}
