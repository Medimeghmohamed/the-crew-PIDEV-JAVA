/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aura;

import Entities.Objectif;
import Entities.Suivi;
import Services.ServiceClient;
import Services.ServiceObjectif;
import Services.ServiceSuivi;
import java.awt.Color;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * FXML Controller class
 *
 * @author Chirine
 */
public class SuiviObjController implements Initializable {

    int i = 0;

    @FXML
    private ComboBox<Integer> cbrep_suivi;
    @FXML
    private TableView<Suivi> tv_suivi;
    @FXML
    private TableColumn<?, ?> colidobj_suivi;
    @FXML
    private TableColumn<?, ?> colvaleur_suivi;
    @FXML
    private TableColumn<?, ?> coldate_suivi;
    @FXML
    private TextField tfrechSuivi;
    @FXML
    private ComboBox<String> cbtriSuivi;
    @FXML
    private Label dateFicheSuivi;
    @FXML
    private Button retour;
    @FXML
    private BarChart<String, Number> Barchart_suivi;
    @FXML
    private ComboBox<String> cb_objectifs;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceSuivi ssuiv = new ServiceSuivi();
        ObservableList<Suivi> suivis = ssuiv.afficherSuivi();
        ServiceObjectif so = new ServiceObjectif();
        ObservableList<Integer> listRep = FXCollections.observableArrayList();
        for (int i = 0; i <= 50; i++) {
            listRep.add(i);
        }
        cbrep_suivi.setItems(listRep);
        ObservableList<String> listTriSuivi = FXCollections.observableArrayList("par ID", "par ID client", "par ID objectif");
        cbtriSuivi.setItems(listTriSuivi);
        dateFicheSuivi.setText(new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime()));
        ObservableList<String> listObjSuiv = so.getMesObjectifs(12345676); //a changer avec l'id du client connecté!!!!!
        cb_objectifs.setItems(listObjSuiv);
    }

    @FXML
    private void ajouterSuivi(ActionEvent event) {
        ServiceObjectif sp = new ServiceObjectif();
        ServiceSuivi ss = new ServiceSuivi();
        ServiceClient sc = new ServiceClient();
        System.out.println("aaaaaaaaaaaaa");
        Suivi s = new Suivi();
        int id = sp.getIdObjparDesc(cb_objectifs.getValue());
        System.out.println("recherche: " + ss.rechercherDate(id, new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime())));
        System.out.println("id : " + id);
        System.out.println("date :" + new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime()));
        
        if (ss.rechercherDate(id, new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime()))) {
            System.out.println("bbbbbbbbbbbbbbbbbbbb");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation l'ajout du suivi");
            alert.setHeaderText("Etes vous sur d'ajouter ce suivi?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                s.setValeurSuiv(cbrep_suivi.getValue());
                s.setClient(sc.load_data_modify("12345676")); //!!! a changer !!!
                System.out.println(id);
                s.setObjectif(sp.load_data_modify(id));
                s.setDateSuiv(dateFicheSuivi.getText());
                ss.ajouterSuivi(s);
//                afficherSuivi();
//                int jourCourant = Integer.parseInt(new SimpleDateFormat("dd").format(Calendar.getInstance().getTime()));
//                System.out.println("jour courant" + jourCourant);
//                System.out.println("Jour date debut  " + Integer.parseInt(sp.getJourDateDebutObj(id, "12345676")));
//                System.out.println("durée " + sp.getDureeObj(id, "12345676"));
//                int reste = sp.getDureeObj(id, "12345676") - (jourCourant - Integer.parseInt(sp.getJourDateDebutObj(id, "12345676")));
//                Alert a = new Alert(Alert.AlertType.INFORMATION);
//                a.setTitle("Rappel");
//                a.setContentText("   Il vous reste   " + reste + "  jours pour terminer cet objectif");
//                a.setHeaderText(null);
//                a.showAndWait();
                BarChart(sp.getIdObjparDesc(cb_objectifs.getValue()));

            } else {
                System.out.println("Cancel");
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Vous avez déja saisi le suivi de cet objectif pour cette date");
            Optional<ButtonType> result = alert.showAndWait();
        }

    }

    public void afficherSuivi(int id) {
        ServiceSuivi ss = new ServiceSuivi();
        ObservableList<Suivi> suivis = ss.getListSuivi(id);
        System.out.println(id);
        suivis.forEach((e) -> {
            System.out.println(e);
        });
        colidobj_suivi.setCellValueFactory(new PropertyValueFactory<>("Objectif"));
        colvaleur_suivi.setCellValueFactory(new PropertyValueFactory<>("valeurSuiv"));
        coldate_suivi.setCellValueFactory(new PropertyValueFactory<>("dateSuiv"));
        tv_suivi.setItems(suivis);
    }

    @FXML
    private void rechercherSuivi(KeyEvent event) {
    }

    @FXML
    private void selectTriSuivi(ActionEvent event) {
    }

    public void selected_item(int id) {
        ServiceSuivi ss = new ServiceSuivi();
        ObservableList<Suivi> suivis = ss.getListSuivi(id);
        colidobj_suivi.setCellValueFactory(new PropertyValueFactory<>("Objectif"));
        colvaleur_suivi.setCellValueFactory(new PropertyValueFactory<>("valeurSuiv"));
        coldate_suivi.setCellValueFactory(new PropertyValueFactory<>("dateSuiv"));
        tv_suivi.setItems(suivis);
        //BAR CHART
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Suivi");
        suivis.forEach((e) -> {
            series.getData().add(new XYChart.Data<>(e.getDateSuiv(), e.getValeurSuiv()));
        });
        Barchart_suivi.getData().add(series);

    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        Parent root = loader.load();
        MainController HomeScene = loader.getController();
        Pane view;
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("Objectifs.fxml"));
        view = loader2.load();
        HomeScene.returnPane(view);
        Stage window = (Stage) retour.getScene().getWindow();
        window.setScene(new Scene(root, 1182, 718));
    }

    public void BarChart(int id) {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        ServiceSuivi ss = new ServiceSuivi();
        ObservableList<Suivi> suivis = ss.getListSuivi(id);
        series.setName("Suivi");
        suivis.forEach((e) -> {
            series.getData().add(new XYChart.Data<>(e.getDateSuiv(), e.getValeurSuiv()));
        });
        Barchart_suivi.getData().add(series);
    }

    @FXML
    private void cb_selected_obj(ActionEvent event) {
        ServiceObjectif sp = new ServiceObjectif();
        BarChart(sp.getIdObjparDesc(cb_objectifs.getValue()));
        afficherSuivi(sp.getIdObjparDesc(cb_objectifs.getValue()));
    }

}
