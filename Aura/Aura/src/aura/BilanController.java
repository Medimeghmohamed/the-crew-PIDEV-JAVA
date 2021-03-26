/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aura;

import Services.ServiceObjectif;
import Services.ServiceSuivi;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultKeyedValuesDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 * FXML Controller class
 *
 * @author Chirine
 */
public class BilanController implements Initializable {

    @FXML
    private PieChart pcBilan;
    @FXML
    private ComboBox<String> cbidObjectifBilan;
    @FXML
    private ComboBox<String> cbDateBilan;
    @FXML
    private Label lbResultat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceSuivi ss = new ServiceSuivi();
        ServiceSuivi ssuiv = new ServiceSuivi();
        ObservableList<String> listIdObjBilan = ssuiv.getObjectifBilan("12345676");
        //ObservableList<String> listIdObjBilan = FXCollections.observableArrayList("par ID", "par réponse", "par description");
        cbidObjectifBilan.setItems(listIdObjBilan);
//        ObservableList<String> listDateSuiv = ss.getDateBilan(cbidObjectifBilan.getValue());
  //      cbDateBilan.setItems(listDateSuiv);

    }

    @FXML
    private void selectIdObjBilan(ActionEvent event) {
        ServiceSuivi ss = new ServiceSuivi();
//        ObservableList<String> listDateSuiv = ss.getDateBilan(cbidObjectifBilan.getValue());
//        cbDateBilan.setItems(listDateSuiv);

    }

    @FXML
    private void selectDateBilan(ActionEvent event) {
//        ServiceObjectif so = new ServiceObjectif();
//        ServiceSuivi ss = new ServiceSuivi();
//        ObservableList<PieChart.Data> list = FXCollections.observableArrayList(
//                new PieChart.Data("Pas fait", (100 - (ss.getValeur(cbidObjectifBilan.getValue(), cbDateBilan.getValue()) * 100) / so.getRepObj("O4"))),
////                new PieChart.Data("Fait", (ss.getValeur(cbidObjectifBilan.getValue(), cbDateBilan.getValue()) * 100) / so.getRepObj(cbidObjectifBilan.getValue())));
//    //    pcBilan.setData(list);
////
////        for (final PieChart.Data data : pcBilan.getData()) {
////            data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
////                @Override
////                public void handle(MouseEvent event) {
////                    lbResultat.setText(String.valueOf(data.getPieValue() + " %"));
////                }
////            });
//        }
    }

    @FXML
    private void showPieChart(ActionEvent event) {
        ServiceObjectif so = new ServiceObjectif();
        ServiceSuivi ss = new ServiceSuivi();
        DefaultPieDataset pieDataset = new DefaultKeyedValuesDataset();
 //       pieDataset.setValue("Pas fait", (100 - (ss.getValeur(cbidObjectifBilan.getValue(), cbDateBilan.getValue()) * 100) / so.getRepObj("O4")));
 //       pieDataset.setValue("Fait", (ss.getValeur(cbidObjectifBilan.getValue(), cbDateBilan.getValue()) * 100) / so.getRepObj(cbidObjectifBilan.getValue()));

        JFreeChart chart = ChartFactory.createPieChart("Pie chart", pieDataset);
        PiePlot P = (PiePlot) chart.getPlot();
        ChartFrame frame = new ChartFrame("Pie chart", chart);
        frame.setVisible(true);
        frame.setSize(450, 500);
    }

}
