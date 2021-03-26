/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aura;

import Entities.Objectif;
import Services.ServiceObjectif;
import Services.ServiceSuivi;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultKeyedValuesDataset;
import org.jfree.data.general.DefaultPieDataset;
import static org.omg.CORBA.ValueMemberHelper.id;

/**
 * FXML Controller class
 *
 * @author Chirine
 */
public class ObjectifsController implements Initializable {

    @FXML
    private TableView<Objectif> tv_obj;
    @FXML
    private TableColumn<?, ?> col_desc;
    @FXML
    private TableColumn<?, ?> col_deb;
    @FXML
    private TableColumn<?, ?> col_duree;
    @FXML
    private TextField tfrechObj;
    @FXML
    private ComboBox<String> cbtriObj;
    @FXML
    private Button btnAjouterObj;
    @FXML
    private Button btnModifierObj;
    @FXML
    private Button btnSupprimerObj;
    @FXML
    private Button btnSuiviObj1;
    @FXML
    private TableColumn<?, ?> col_rep;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> listTriObj = FXCollections.observableArrayList("par ID", "par réponse", "par description");
        cbtriObj.setItems(listTriObj);
        afficherObjectifs();
       
    }

    private void afficherObjectifs() {
        ServiceObjectif sop = new ServiceObjectif();
        ObservableList<Objectif> objectifs = sop.afficherObjectifs();
        col_desc.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_deb.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_duree.setCellValueFactory(new PropertyValueFactory<>("duree"));
        col_rep.setCellValueFactory(new PropertyValueFactory<>("reponse"));
        tv_obj.setItems(objectifs);
    }

    @FXML
    private void Select_obj(MouseEvent event) {
        //return tv_obj.getSelectionModel().getSelectedItem();       
    }

    public int Select_objectif() {
        return tv_obj.getSelectionModel().getSelectedItem().getId();
    }

    @FXML
    private void rechercherObjectif(KeyEvent event) {
        ServiceObjectif sop = new ServiceObjectif();
        ObservableList<Objectif> objectifs = sop.rechercherObjectif(tfrechObj.getText());
        col_desc.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_deb.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_duree.setCellValueFactory(new PropertyValueFactory<>("duree"));
        col_rep.setCellValueFactory(new PropertyValueFactory<>("reponse"));
        tv_obj.setItems(objectifs);
    }

    @FXML
    private void selectTriObj(ActionEvent event) {
        ServiceObjectif sop = new ServiceObjectif();
        ObservableList<Objectif> objectifs = FXCollections.observableArrayList();
        if (cbtriObj.getValue().equals("par ID")) {
            objectifs = sop.trierObjectifparId();
        } else if (cbtriObj.getValue().equals("par réponse")) {
            objectifs = sop.trierObjectifparRep();
        } else {
            objectifs = sop.trierObjectifparDesc();
        }
        tv_obj.setItems(objectifs);
    }

    @FXML
    private void ajouterObjectif(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        Parent root = loader.load();
        MainController HomeScene = loader.getController();
        Pane view;
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("AjouterObj.fxml"));
        view = loader2.load();
        HomeScene.returnPane(view);
        Stage window = (Stage) btnModifierObj.getScene().getWindow();

        window.setScene(new Scene(root, 1182, 718));
    }

    @FXML
    private void modifierObjectif(ActionEvent event) throws IOException {
        if (tv_obj.getSelectionModel().getSelectedItem() == null) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Attention");
            a.setContentText("Vous devez selectionner un element du tableau");
            a.setHeaderText(null);
            a.showAndWait();
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
            Parent root = loader.load();
            MainController HomeScene = loader.getController();
            Pane view;
            FXMLLoader loader2 = new FXMLLoader(getClass().getResource("ModifierObj.fxml"));
            view = loader2.load();
            HomeScene.returnPane(view);
            Stage window = (Stage) btnModifierObj.getScene().getWindow();

            window.setScene(new Scene(root, 1182, 718));
            ModifierObjController mc = loader2.getController();
            mc.selected_item(tv_obj.getSelectionModel().getSelectedItem().getId(),
                    tv_obj.getSelectionModel().getSelectedItem().getDescription(),
                    tv_obj.getSelectionModel().getSelectedItem().getDate(),
                    tv_obj.getSelectionModel().getSelectedItem().getDuree(),
                    tv_obj.getSelectionModel().getSelectedItem().getReponse());
        }

    }

    @FXML
    private void supprimerObjectif(ActionEvent event) {
        if (tv_obj.getSelectionModel().getSelectedItem() == null) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Attention");
            a.setContentText("Vous devez selectionner un element du tableau");
            a.setHeaderText(null);
            a.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation suppression");
            alert.setHeaderText("Etes vous sur de supprimer cet objectif?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                System.out.println("okay");
                ServiceObjectif sp = new ServiceObjectif();
                sp.supprimerObjectif(tv_obj.getSelectionModel().getSelectedItem().getId());
                afficherObjectifs();
                new animatefx.animation.Flash(btnSupprimerObj).play();
            } else {
                System.out.println("Cancel");
            }
        }
    }

    @FXML
    private void suiviObjectif(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
            Parent root = loader.load();
            MainController HomeScene = loader.getController();
            Pane view;
            FXMLLoader loader2 = new FXMLLoader(getClass().getResource("SuiviObj.fxml"));
            view = loader2.load();
            HomeScene.returnPane(view);
            Stage window = (Stage) btnSuiviObj1.getScene().getWindow();
            window.setScene(new Scene(root, 1182, 718));
            
            //SuiviObjController sc = loader2.getController();
            //sc.selected_item(tv_obj.getSelectionModel().getSelectedItem().getId());
        
//        if (tv_obj.getSelectionModel().getSelectedItem() == null) {
//            Alert a = new Alert(Alert.AlertType.ERROR);
//            a.setTitle("Attention");
//            a.setContentText("Vous devez selectionner un element du tableau");
//            a.setHeaderText(null);
//            a.showAndWait();
//        } else {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
//            Parent root = loader.load();
//            MainController HomeScene = loader.getController();
//            Pane view;
//            FXMLLoader loader2 = new FXMLLoader(getClass().getResource("SuiviObj.fxml"));
//            view = loader2.load();
//            HomeScene.returnPane(view);
//            Stage window = (Stage) btnSuiviObj1.getScene().getWindow();
//            window.setScene(new Scene(root, 1182, 718));
//            SuiviObjController sc = loader2.getController();
//            sc.selected_item(tv_obj.getSelectionModel().getSelectedItem().getId());
//
//        }
    }

    @FXML
    private void btn_bilan(ActionEvent event) {
        ServiceObjectif so = new ServiceObjectif();
        ServiceSuivi ss = new ServiceSuivi();
        DefaultPieDataset pieDataset = new DefaultKeyedValuesDataset();
        int id = tv_obj.getSelectionModel().getSelectedItem().getId();
        String date= tv_obj.getSelectionModel().getSelectedItem().getDate();
        pieDataset.setValue("Pas fait", (100 - (ss.getValeur(id, date) * 100) / so.getRepObj(id)));
        pieDataset.setValue("Fait", (ss.getValeur(id, date) * 100) / so.getRepObj(id));

        JFreeChart chart = ChartFactory.createPieChart("Bilan de l'objectif: " + tv_obj.getSelectionModel().getSelectedItem().getDescription(), pieDataset);
        PiePlot P = (PiePlot) chart.getPlot();
        ChartFrame frame = new ChartFrame("Bilan objectif", chart);
        frame.setVisible(true);
        frame.setSize(450, 500);
    }

}
