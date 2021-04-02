/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aura.Contact_Entraide;

import Entities.Contact;
import utils.Connexion;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Nour Dekhil
 */

public class PieChartController implements Initializable {

    @FXML
    private Pane paneview;
    @FXML
    private PieChart piechart;

    /**
     * Initializes the controller class.
     */
    String id_user="";
    public void initializeFxml(String id){
        System.out.println("");
    
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            compter();
        } catch (SQLException ex) {
            Logger.getLogger(PieChartController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void compter() throws SQLException {

        ObservableList<PieChart.Data> list = FXCollections.observableArrayList();

        try {
            Statement stm = Connexion.getInstance().getConnection().createStatement();
            String query = "select  count(type) from `contact` where type='Reclamation'";
            ResultSet rst1 = stm.executeQuery(query);

            while (rst1.next()) {

                rst1.getInt("count(type)");
                System.out.println(rst1.getInt("count(type)"));
                list.add(new PieChart.Data("Reclamation", rst1.getInt("count(type)")));
                
            }

        } catch (SQLException ex) {
            System.out.println("erreur affichage contact ");
            System.out.println(ex);
        }
        
        
        
         try {
            Statement stm = Connexion.getInstance().getConnection().createStatement();
            String query = "select  count(type) from `contact` where type='Avis'";
            ResultSet rst1 = stm.executeQuery(query);

            while (rst1.next()) {

                rst1.getInt("count(type)");
                System.out.println(rst1.getInt("count(type)"));
                list.add(new PieChart.Data("Avis", rst1.getInt("count(type)")));
                
            }

        } catch (SQLException ex) {
            System.out.println("erreur affichage contact ");
            System.out.println(ex);
        }
         
          try {
            Statement stm = Connexion.getInstance().getConnection().createStatement();
            String query = "select  count(type) from `contact` where type='Contact'";
            ResultSet rst1 = stm.executeQuery(query);

            while (rst1.next()) {

                rst1.getInt("count(type)");
                System.out.println(rst1.getInt("count(type)"));
                list.add(new PieChart.Data("Contact", rst1.getInt("count(type)")));
                
            }

        } catch (SQLException ex) {
            System.out.println("erreur affichage contact ");
            System.out.println(ex);
        }
           try {
            Statement stm = Connexion.getInstance().getConnection().createStatement();
            String query = "select  count(type) from `contact` where type='Autre'";
            ResultSet rst1 = stm.executeQuery(query);

            while (rst1.next()) {

                rst1.getInt("count(type)");
                System.out.println(rst1.getInt("count(type)"));
                list.add(new PieChart.Data("Autre", rst1.getInt("count(type)")));
                
            }

        } catch (SQLException ex) {
            System.out.println("erreur affichage contact ");
            System.out.println(ex);
        }
           PieChart Chart = new PieChart(list);
           piechart.getData().addAll(list);

    }

}
