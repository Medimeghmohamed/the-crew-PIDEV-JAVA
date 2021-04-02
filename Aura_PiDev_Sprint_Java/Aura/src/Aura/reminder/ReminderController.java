/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aura.reminder;

import Aura.Aura;
import Aura.SideBar.ClientMainController;
import Entities.Activites;
import Service.serviceActivites;
import Entities.Objectif;
import Entities.challenge;
import Entities.classement;
import Service.ServiceChallenge;
import Service.ServiceClassement;
import Service.ServiceObjectif;
import com.twilio.rest.verify.v2.service.entity.Challenge;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Chirine
 */
public class ReminderController implements Initializable {

    @FXML
    private TableColumn<Activites, String> col_event;
    @FXML
    private TableColumn<challenge,String> col_chall;
    @FXML
    private TableColumn<Objectif, String> col_obj;
    @FXML
    private TableView<Activites> tv_event;
    @FXML
    private TableView<Objectif> tv_obj;
    @FXML
    private Button suivi;
    public String id_user = "";
    @FXML
    private TableView<challenge> tv_chall;
    @FXML
    private Button reminder_done;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceChallenge sc= new ServiceChallenge();
        sc.verif_date_participation();
        sc.verif_date_challenge();
       affich_reminder_challenge();

    }

    public void initializeFxml(String id) throws ParseException {
        ServiceChallenge sc= new ServiceChallenge();
        sc.verif_date_participation();
        sc.verif_date_challenge();
        affich_reminder_challenge();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        serviceActivites Ac = new serviceActivites();
        ServiceObjectif so = new ServiceObjectif();

        ObservableList<Activites> Act = Ac.showActivitesClient(id);
        ObservableList<Objectif> objectifs = so.afficherObjectifsReminder(id);

        System.out.println(Act);
        col_event.setCellValueFactory(new PropertyValueFactory<>("description"));
        tv_event.setItems(Act);

        objectifs.forEach((e) -> {
            //date du jour
            Date dateAuj;
            try {
                dateAuj = sdf.parse(new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime()));
                //date du debut
                Date dateFin = sdf.parse(so.getDateDebutObj(so.getIdObjparDesc(e.getDescription()), id_user));
                //datedebut + durée = date fin
                dateFin.setTime(dateFin.getTime() + (so.getDureeparDesc(e.getDescription()) * 24 * 3600 * 1000));
                long diff = dateFin.getTime() - dateAuj.getTime();
                //si res<0.0 alors l'objectif est deja fini
                long res = diff / (1000 * 60 * 60 * 24);
                if (res > 0.0) {
                    col_obj.setCellValueFactory(new PropertyValueFactory<>("description"));
                    tv_obj.setItems(objectifs);
                }
            } catch (ParseException ex) {
                System.out.println(ex);
            }

        });

    }

    @FXML
    private void clicked_obj(MouseEvent event) {
    }

    @FXML
    private void Suivi(ActionEvent event) throws IOException {

        if (tv_obj.getSelectionModel().getSelectedItem() == null) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Attention");
            a.setContentText("Vous devez selectionner un element du tableau");
            a.setHeaderText(null);
            a.showAndWait();
        } else {
            Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
            alert2.setTitle("Redirection");
            alert2.setHeaderText("Voulez allez etre redirigé vers la page Suivi");
            Optional<ButtonType> result2 = alert2.showAndWait();
            if (result2.get() == ButtonType.OK) {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/SideBar/ClientMain.fxml"));
                Parent root = loader.load();
                ClientMainController HomeScene = loader.getController();
                HomeScene.SuiviObjectif(id_user, tv_obj.getSelectionModel().getSelectedItem().getDescription());
                HomeScene.id_user = id_user;
                HomeScene.show_data(id_user);
                String css = Aura.class.getResource("ReminderStyle.css").toExternalForm();
                root.getStylesheets().add(css);
                Stage window = (Stage) suivi.getScene().getWindow();
                window.setScene(new Scene(root, 1182, 718));
            } else {

            }

        }
    }
    
    public void affich_reminder_challenge() {//verif_challenge2
        ServiceChallenge sc= new ServiceChallenge();
       

        ObservableList<challenge> Ochallenges = sc.verif_date_participation_reminder(id_user);
        System.out.println("hey"+id_user);
        
        
          col_chall.setCellValueFactory(new PropertyValueFactory<>("description"));
          tv_chall.setItems(Ochallenges);
        
//          sc.verif_date_participation();
//          sc.verif_date_challenge();
        
        
        
        
    }

   

    @FXML
    private void challenge_done(ActionEvent event) {
         challenge c = new challenge();
        ServiceChallenge sc = new ServiceChallenge();
        c = tv_chall.getSelectionModel().getSelectedItem();
        //challenge_done
        if (c == null) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Attention");
            a.setContentText("Vous devez selectionner un element du tableau");
            a.setHeaderText(null);
            a.showAndWait();
        } else {

            sc.challenge_done(c, id_user);
            ServiceClassement cl = new ServiceClassement();
            classement cls = new classement();
            cl.position();
             

        }
         affich_reminder_challenge();
    }

    @FXML
    private void challenge_done(MouseEvent event) {
        System.out.println("hey");
           challenge c = new challenge();
        ServiceChallenge sc = new ServiceChallenge();
        c = tv_chall.getSelectionModel().getSelectedItem();
        //challenge_done
        if (c == null) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Attention");
            a.setContentText("Vous devez selectionner un element du tableau");
            a.setHeaderText(null);
            a.showAndWait();
        } else {

            sc.challenge_done(c, id_user);
            ServiceClassement cl = new ServiceClassement();
            classement cls = new classement();
            cl.position();
            
            

        }
         affich_reminder_challenge();
    }

}
