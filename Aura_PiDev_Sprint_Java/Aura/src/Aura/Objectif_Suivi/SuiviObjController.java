/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aura.Objectif_Suivi;

import Aura.Aura;
import Aura.SideBar.ClientMainController;
import Entities.Objectif;
import Entities.Suivi;
import Service.ServiceClient;
import Service.ServiceObjectif;
import Service.ServiceSuivi;
import java.awt.Color;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
    private Label dateFicheSuivi;
    @FXML
    private Button retour;
    @FXML
    private BarChart<String, Number> Barchart_suivi;
    @FXML
    private ComboBox<String> cb_objectifs;
    public String id_user = "";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceSuivi ssuiv = new ServiceSuivi();
        ObservableList<Suivi> suivis = ssuiv.afficherSuivi();
        ObservableList<Integer> listRep = FXCollections.observableArrayList();
        for (int i = 0; i <= 50; i++) {
            listRep.add(i);
        }
        cbrep_suivi.setItems(listRep);
        dateFicheSuivi.setText(new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime()));

    }

    public void initializeFxml(String id, String desc) {
        System.out.println(id_user);
        ServiceObjectif so = new ServiceObjectif();
        ObservableList<String> listObjSuiv = so.getMesObjectifs(Integer.parseInt(id_user));
        cb_objectifs.setValue(desc);
        cb_objectifs.setItems(listObjSuiv);
    }

    @FXML
    private void ajouterSuivi(ActionEvent event) {
        ServiceObjectif sp = new ServiceObjectif();
        ServiceSuivi ss = new ServiceSuivi();
        ServiceClient sc = new ServiceClient();
        Suivi s = new Suivi();
        int id = sp.getIdObjparDesc(cb_objectifs.getValue());
        if (ss.rechercherDate(id, new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime()))) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation l'ajout du suivi");
            alert.setHeaderText("Etes vous sur d'ajouter ce suivi?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                s.setValeurSuiv(cbrep_suivi.getValue());
                s.setClient(sc.load_data_modify(id_user));
                s.setObjectif(sp.load_data_modify(id));
                s.setDateSuiv(dateFicheSuivi.getText());
                ss.ajouterSuivi(s);

                XYChart.Series<String, Number> series = new XYChart.Series<>();
                series.getData().add(new XYChart.Data<>(dateFicheSuivi.getText(), cbrep_suivi.getValue()));
                Barchart_suivi.getData().add(series);
                Barchart_suivi.setLegendVisible(false);
                //BarChart(sp.getIdObjparDesc(cb_objectifs.getValue()));
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
    }

    public void selected_item(int id) {
        Barchart_suivi.getData().clear();
        ServiceSuivi ss = new ServiceSuivi();
        ObservableList<Suivi> suivis = ss.getListSuivi(id);
        //BAR CHART
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Suivi");
        suivis.forEach((e) -> {
            series.getData().add(new XYChart.Data<>(e.getDateSuiv(), e.getValeurSuiv()));
        });
        Barchart_suivi.getData().add(series);
        Barchart_suivi.setLegendVisible(false);

    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/SideBar/ClientMain.fxml"));
        Parent root = loader.load();
        ClientMainController HomeScene = loader.getController();
        HomeScene.Retour(id_user);
        HomeScene.id_user = id_user;
        HomeScene.show_data(id_user);
        String css = Aura.class.getResource("Style.css").toExternalForm();
        root.getStylesheets().add(css);
        Stage window = (Stage) retour.getScene().getWindow();
        window.setScene(new Scene(root, 1182, 718));
    }

    public void BarChart(int id) {
        Barchart_suivi.getData().clear();
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
        ServiceObjectif so = new ServiceObjectif();
        //comparer date aujourd'hui et date debut de l'objectif
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        float res = 1;
        try {
            //date du jour
            Date dateAuj = sdf.parse(new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime()));
            //date du debut
            Date dateFin = sdf.parse(so.getDateDebutObj(so.getIdObjparDesc(cb_objectifs.getValue()), id_user));
            //datedebut + durée = date fin
            dateFin.setTime(dateFin.getTime() + (so.getDureeparDesc(cb_objectifs.getValue()) * 24 * 3600 * 1000));
            long diff = dateFin.getTime() - dateAuj.getTime();
            //si res<0.0 alors l'objectif est deja fini
            res = (diff / (1000 * 60 * 60 * 24));

            if (res <= 0.0) {
                Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setTitle("Attention");
                a.setContentText("Vous avez déja terminé cet objectif, vous ne pouvez pas entrer un suivi \n Est-ce que vous voulez le supprimer?");
                a.setHeaderText(null);
                a.showAndWait();
                Optional<ButtonType> result = a.showAndWait();

                if (result.get() == ButtonType.OK) {
                    ServiceObjectif sp = new ServiceObjectif();
                    sp.supprimerObjectif(sp.getIdObjparDesc(cb_objectifs.getValue()));
                    cb_objectifs.setValue(null);
                } else {

                }
            } else {
                ServiceObjectif sp = new ServiceObjectif();
                Barchart_suivi.getData().clear();
                BarChart(sp.getIdObjparDesc(cb_objectifs.getValue()));
                afficherSuivi(sp.getIdObjparDesc(cb_objectifs.getValue()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
