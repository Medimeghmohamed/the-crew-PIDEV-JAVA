/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;



import entities.challenge;
import entities.classement;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import service.ServiceChallenge;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import service.ServiceClassement;
import service.ServiceMail;
import service.ServiceNiveau;
import utils.Myconnexion;




/**
 * FXML Controller class
 *
 * @author NOUR
 */
public class Classement_adminController implements Initializable {

    @FXML
    private TableView<classement> liste_clessement_admin;
    @FXML
    private TableColumn<classement, Integer> colid_calassement_admin;
    @FXML
    private TableColumn<classement, Integer> colniveau_calassement_admin;
    @FXML
    private TableColumn<classement, Integer> colnposition_calassement_admin;
    @FXML
    private TableColumn<classement, String> colclient_classement_admin;
    @FXML
    private TableColumn<classement, Integer> colpts_classement_admin;
    @FXML
    private TextField niveau_classement_admin;
    @FXML
    private TextField position_classement_admin;
    @FXML
    private TextField nb_pts_classement_admin;
    @FXML
    private Button btn_modif_classement_admin;
    @FXML
    private Button btn_supp_classement;
    @FXML
    private Button btn_ajouter_classement_admin;
    @FXML
    private TextField id_classement_admin;
    @FXML
    private TextField position_classement_admin2;
    @FXML
    private Button btn_chercher_classement_admin;
    @FXML
    private TextField nom_client_classement_admin;
    @FXML
    private TextField mail_admin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ServiceChallenge sc = new ServiceChallenge();
        ServiceClassement cl = new ServiceClassement();

        ServiceNiveau n = new ServiceNiveau();

        ObservableList<challenge> Ochallenges = sc.afficherChallenge();
        ObservableList<classement> Oclassements = cl.afficherClassement();
       
        afficher_classement_admin();
      
        // TODO
    }    

    @FXML
    private void SendMail(MouseEvent event) {
        
         classement c =liste_clessement_admin.getSelectionModel().getSelectedItem();
         String n=c.getClient();
        System.out.println("1");
        try {
             String requete = "SELECT email FROM user  WHERE id='"+n+"'";
             System.out.println("2");
             Statement stm=null;
           stm = Myconnexion.getInstance().getConnection().createStatement();
            ResultSet rs = stm.executeQuery(requete);
            System.out.println("3");
            while (rs.next()) {
              rs.getString("email"); 
              
              System.out.println("4");
                 ServiceMail mai =new ServiceMail();
        System.out.println( rs.getString("email"));
      //String reaso= mail_admin.getText();
      String reaso="you are the first";
      
        System.out.println(reaso);
            mai.send_mail( rs.getString("email"),reaso);
            }
                 
         }catch (Exception e) {
        }
    }

    @FXML
    private void modifierClassement_admin(ActionEvent event) {
          ServiceClassement cl = new ServiceClassement();
        classement c = new classement();

        c = cl.recup_classement(Integer.parseInt(id_classement_admin.getText()));
        c.setNiveau(Integer.parseInt(niveau_classement_admin.getText()));
        c.setClient(nom_client_classement_admin.getText());
        c.setPosition(Integer.parseInt(position_classement_admin.getText()));
        c.setNb_points(Integer.parseInt(nb_pts_classement_admin.getText()));

        cl.modifierClassement(c);
        afficher_classement_admin();
    }

    @FXML
    private void supprimerClassement_admin(ActionEvent event) {
        ServiceClassement cl = new ServiceClassement();
        cl.supprimerClassement(id_classement_admin.getText());
        afficher_classement_admin();
    }
      private void afficher_classement_admin() {

        ServiceClassement cl = new ServiceClassement();

        ObservableList<classement> Oclassements = cl.afficherClassement();
        colid_calassement_admin.setCellValueFactory(new PropertyValueFactory<>("id"));
        colniveau_calassement_admin.setCellValueFactory(new PropertyValueFactory<>("niveau"));
        colnposition_calassement_admin.setCellValueFactory(new PropertyValueFactory<>("position"));
        colclient_classement_admin.setCellValueFactory(new PropertyValueFactory<>("client"));

        colpts_classement_admin.setCellValueFactory(new PropertyValueFactory<>("nb_points"));

        liste_clessement_admin.setItems(Oclassements);
    }

    @FXML
    private void ajouterClassement(ActionEvent event) {
         ServiceClassement cl = new ServiceClassement();
        
        classement c = new classement();
       
        c.setNiveau(Integer.parseInt(niveau_classement_admin.getText()));
        //int i = Integer.parseInt(position_classement_admin.getText());
        String str = position_classement_admin.getText();
        try {
            int i = Integer.parseInt(str);
            c.setPosition(i);

        } catch (NumberFormatException e) {
            System.out.println("not a number");
        }

        c.setClient(nom_client_classement_admin.getText());
        c.setNb_points(Integer.parseInt(nb_pts_classement_admin.getText()));
       //  c=cl.getNomClient(c);
        cl.ajouterClassement(c);
        afficher_classement_admin();
            
    }

    @FXML
    private void chercherPosition_admin(ActionEvent event) {
         ServiceClassement sn = new ServiceClassement();
         classement n=new classement ();
        ObservableList<classement> Oclassement = sn.RechercherClassement(position_classement_admin2.getText());
        colid_calassement_admin.setCellValueFactory(new PropertyValueFactory<>("id"));
        colniveau_calassement_admin.setCellValueFactory(new PropertyValueFactory<>("niveau"));
        colnposition_calassement_admin.setCellValueFactory(new PropertyValueFactory<>("position"));
        colclient_classement_admin.setCellValueFactory(new PropertyValueFactory<>("client"));

        colpts_classement_admin.setCellValueFactory(new PropertyValueFactory<>("nb_points"));

        liste_clessement_admin.setItems(Oclassement);
    }
    
}
