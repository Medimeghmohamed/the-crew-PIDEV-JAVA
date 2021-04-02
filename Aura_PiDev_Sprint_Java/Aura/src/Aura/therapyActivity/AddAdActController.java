/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aura.therapyActivity;

import Aura.Aura;
import Aura.SideBar.AdminMainController;
import static Aura.therapyActivity.ShowadactController.saveRecord;
import Entities.Activites;
import Entities.Coach;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import Service.ServiceCoach;
import Service.serviceActivites;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Medimegh
 */
public class AddAdActController implements Initializable {

    @FXML
    private DatePicker datee;
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
    @FXML
    private Button show;
    @FXML
    private ComboBox<Coach> listcoach;
    @FXML
    private Button BACK;
    public String id_user = "";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
loadlistcoach();   
    
    }
 public void initializeFxml(String id) {
     System.out.println(id_user);
    }    

    @FXML
    private void addact(ActionEvent event) {
       
        if((description.getText().equals("") )|| (duree.getText().equals("")) || (lieu.getText().equals("")) || (type.getText().equals("")) || (datee.getEditor().getText().equals(""))
                || (nombremax.getText().equals(""))){
            System.out.println("empty");
            
                 new animatefx.animation.Shake(duree).play();
                  new animatefx.animation.Shake(description).play();
                   new animatefx.animation.Shake(nombremax).play();
                    new animatefx.animation.Shake(lieu).play();
                                        new animatefx.animation.Shake(type).play();
                                                            new animatefx.animation.Shake(datee).play();


                    Alert alert = new Alert(Alert.AlertType.WARNING);
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
sa.addActivities(A);
saveRecord(A.getIdcoach().getNom()+"   "+A.getIdcoach().getPrenom(), A.getDescription(),A.getDate(),"src/Charts/Activite.CSV");

clearfields();
    }}

    @FXML
    private void modify(ActionEvent event) throws IOException {
FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/SideBar/AdminMain.fxml"));

        Parent root = loader.load();

        AdminMainController HomeScene = loader.getController();
        HomeScene.modifieract_nav(id_user);
        HomeScene.id_user = id_user;
        HomeScene.show_data(id_user);
        String css = Aura.class.getResource("Style.css").toExternalForm();

        root.getStylesheets().add(css);

        Stage window = (Stage) BACK.getScene().getWindow();

        window.setScene(new Scene(root, 1182, 718));
    }

    private void clearfields() {
lieu.clear();

description.clear();
type.clear();
nombremax.clear();

duree.clear();
    }
    public void loadlistcoach()
{
 ServiceCoach sa = new  ServiceCoach();
     ObservableList <Coach> activit =   sa.afficherCoach_Ouiob();
               listcoach.setItems(activit);

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
    
    } public static void saveRecord(String ID, String name,String age,String filepath)
    {
        try
        {
           FileWriter fw = new FileWriter(filepath,true);
           BufferedWriter bw = new BufferedWriter(fw) ;
           PrintWriter pw = new PrintWriter(bw);
           
           pw.println(ID+"   "+name+"   "+age);

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
