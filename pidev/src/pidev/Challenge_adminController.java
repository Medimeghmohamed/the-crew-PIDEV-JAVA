/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aura.ChallengeClassementgg;



import Entities.challenge;
import Entities.classement;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import Service.ServiceChallenge;
import java.util.Date;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import Service.ServiceClassement;
import Service.ServiceNiveau;
import utils.Connexion;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.jdbc.JDBCCategoryDataset;

/**
 * FXML Controller class
 *
 * @author NOUR
 */
public class Challenge_adminController implements Initializable {

    @FXML
    private TextField titre_challenge_admin;
    @FXML
    private Button btn_confirmer_challenge;
    @FXML
    private Button btn_supp_challenge_admin;
    @FXML
    private Label imd_challenge1;
    @FXML
    private TableView<challenge> liste_challenge_admin;
    @FXML
    private TableColumn<challenge, Integer> colniveau_challenge_admin;
    @FXML
    private TableColumn<challenge, String> colititre_challenge_admin;
    @FXML
    private TableColumn<challenge, String> coltype_challenge_admin;
    @FXML
    private TableColumn<challenge, String> colString_challenge_admin;
    @FXML
    private TableColumn<challenge, Date> coldate_debut_challenge_admin;
    @FXML
    private TableColumn<challenge, Date> coldate_fin_challenge_admin;
    @FXML
    private TableColumn<challenge, Integer> colnb_participants_challenge_admin;
    @FXML
    private Button btn_chercher_titre_challenge_admin;
    @FXML
    private Button btn_stat_challenge;

    /**
     * Initializes the controller class.
     */
    public String id_user="";
    public void initializeFxml(String id){
        System.out.println(id);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         ServiceChallenge sc = new ServiceChallenge();
        ServiceClassement cl = new ServiceClassement();

        ServiceNiveau n = new ServiceNiveau();

        ObservableList<challenge> Ochallenges = sc.afficherChallenge();
        ObservableList<classement> Oclassements = cl.afficherClassement();
        afficher_challenge_admin();
         sc.verif_date_challenge();
        sc.verif_date_participation();
       
        // TODO
    }    
    private void afficher_challenge_admin() {

        ServiceChallenge sc = new ServiceChallenge();
        // liste_challenge.SetCollumn(sc.afficherChallenge().toString());

        ObservableList<challenge> Ochallenges = sc.afficherChallenge();
       // colid_challenge_admin.setCellValueFactory(new PropertyValueFactory<>("id"));
        colititre_challenge_admin.setCellValueFactory(new PropertyValueFactory<>("titre"));
        coltype_challenge_admin.setCellValueFactory(new PropertyValueFactory<>("type"));
        colString_challenge_admin.setCellValueFactory(new PropertyValueFactory<>("description"));

        coldate_debut_challenge_admin.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        coldate_fin_challenge_admin.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        colnb_participants_challenge_admin.setCellValueFactory(new PropertyValueFactory<>("nb_participants"));
      
        colniveau_challenge_admin.setCellValueFactory(new PropertyValueFactory<>("niveau"));
        liste_challenge_admin.setItems(Ochallenges);
    }

    @FXML
    private void confirmerChallenge_admin(ActionEvent event) {
        
          ServiceChallenge sc = new ServiceChallenge();
         challenge c=new challenge ();
         String tr;
         
           c= liste_challenge_admin.getSelectionModel().getSelectedItem();
        
         sc.confirmer_challenge(c);
         afficher_challenge_admin();
        
        
        
    }

    @FXML
    private void supprimerChallenge_admin(ActionEvent event) {
        
          ServiceChallenge sp = new ServiceChallenge();
           challenge n=new challenge ();
           n= liste_challenge_admin.getSelectionModel().getSelectedItem();
        sp.supprimerChallenge(n.getTitre());
        afficher_challenge_admin();
    }

    @FXML
    private void chercherChallenge_admin(ActionEvent event) {
          ServiceChallenge sn = new ServiceChallenge();
         challenge n=new challenge ();
        ObservableList<challenge> Ochallenges = sn.RechercherChallenge(titre_challenge_admin.getText());
       //colid_challenge_admin.setCellValueFactory(new PropertyValueFactory<>("id"));
        colititre_challenge_admin.setCellValueFactory(new PropertyValueFactory<>("titre"));
        coltype_challenge_admin.setCellValueFactory(new PropertyValueFactory<>("type"));
        colString_challenge_admin.setCellValueFactory(new PropertyValueFactory<>("description"));

        coldate_debut_challenge_admin.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        coldate_fin_challenge_admin.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
//        coldetet_challenge_admin.setCellValueFactory(new PropertyValueFactory<>("etat"));
        colniveau_challenge_admin.setCellValueFactory(new PropertyValueFactory<>("niveau"));
        liste_challenge_admin.setItems(Ochallenges);
    }

    @FXML
    private void Addchart(ActionEvent event) {
         try {
            String query ="SELECT titre,nb_participants FROM challenge WHERE type = 'valide'";
            Statement stm=null;
            stm = Connexion.getInstance().getConnection().createStatement();
            ResultSet rst = stm.executeQuery(query);
            
           // JDBCCategoryDataset​(Myconnexion.getInstance().getConnection() , query);
            JDBCCategoryDataset dataset=new JDBCCategoryDataset(Connexion.getInstance().getConnection(),query);
           
            JFreeChart chart=ChartFactory.createLineChart("STATS des challenges", "challenge", "nb participants", dataset ,PlotOrientation.VERTICAL,false,true,true);
            BarRenderer renderer = null;
            CategoryPlot plot= null;
            renderer=new BarRenderer();
            ChartFrame frame = new ChartFrame("STATS des challenges", chart);
            frame.setVisible(true);
            frame.setSize(800,800);
            
        
        
        }catch(Exception e){JOptionPane.showMessageDialog(null,e);}
    }

    @FXML
    private void refresh(ActionEvent event) {
         ServiceChallenge sc = new ServiceChallenge();
        // liste_challenge.SetCollumn(sc.afficherChallenge().toString());

        ObservableList<challenge> Ochallenges = sc.afficherChallenge();
       // colid_challenge_admin.setCellValueFactory(new PropertyValueFactory<>("id"));
        colititre_challenge_admin.setCellValueFactory(new PropertyValueFactory<>("titre"));
        coltype_challenge_admin.setCellValueFactory(new PropertyValueFactory<>("type"));
        colString_challenge_admin.setCellValueFactory(new PropertyValueFactory<>("description"));

        coldate_debut_challenge_admin.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        coldate_fin_challenge_admin.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        colnb_participants_challenge_admin.setCellValueFactory(new PropertyValueFactory<>("nb_participants"));
      
        colniveau_challenge_admin.setCellValueFactory(new PropertyValueFactory<>("niveau"));
        liste_challenge_admin.setItems(Ochallenges);
    }
    
}
