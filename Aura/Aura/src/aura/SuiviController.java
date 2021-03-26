/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aura;

import Entities.Suivi;
import Services.ServiceClient;
import Services.ServiceObjectif;
import Services.ServiceSuivi;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author Chirine
 */
public class SuiviController implements Initializable {


    @FXML
    private ComboBox<Integer> cbrep_suivi;
    @FXML
    private TableView<Suivi> tv_suivi;
    @FXML
    private TableColumn<Suivi, String> colid_suivi;
    @FXML
    private TableColumn<Suivi, String> colidobj_suivi;
    @FXML
    private TableColumn<Suivi, String> colvaleur_suivi;
    @FXML
    private TableColumn<Suivi, String> coldate_suivi;
    @FXML
    private TableColumn<Suivi, String> colidcli_suivi;
    @FXML
    private TextField tfrechSuivi;
    @FXML
    private ComboBox<String> cbtriSuivi;
    @FXML
    private Label dateFicheSuivi;

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
        afficherSuivi();
        ObservableList<String> listObjSuiv = so.getMesObjectifs(12345676);
//        cbobjsuivi.setItems(listObjSuiv);
        dateFicheSuivi.setText(new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime()));

    }

    @FXML
    private void ajouterSuivi(ActionEvent event) {
        ServiceObjectif sp = new ServiceObjectif();
        ServiceSuivi ss = new ServiceSuivi();
        ServiceClient sc = new ServiceClient();
        Suivi s = new Suivi();
        s.setValeurSuiv(cbrep_suivi.getValue());
        s.setClient(sc.load_data_modify("12345676")); //!!! a changer !!!
//        s.setObjectif(sp.load_data_modify(sp.getIdObj(sp.getIdObjparDesc(tv), "12345676")));
        s.setDateSuiv(dateFicheSuivi.getText());
        ss.ajouterSuivi(s);
        afficherSuivi();

        int jourCourant = Integer.parseInt(new SimpleDateFormat("dd").format(Calendar.getInstance().getTime()));
        System.out.println("jour courant" + jourCourant);
//        System.out.println("Jour date debut  " + Integer.parseInt(sp.getJourDateDebutObj(sp.getIdObj(cbobjsuivi.getValue(), "12345676"), "12345676")));
//        System.out.println("durée " + sp.getDureeObj(sp.getIdObj(cbobjsuivi.getValue(), "12345676"), "12345676"));
//
//        int reste = sp.getDureeObj(sp.getIdObj(cbobjsuivi.getValue(), "12345676"), "12345676") - (jourCourant - Integer.parseInt(sp.getJourDateDebutObj(sp.getIdObj(cbobjsuivi.getValue(), "12345676"), "12345676")));
////        AlertBoxSuivi.display("Rappel", "   Il vous reste   " + reste + "  jours pour terminer cet objectif");
    }

    private void afficherSuivi() {
        ServiceSuivi ss = new ServiceSuivi();
        ObservableList<Suivi> suivis = ss.afficherSuivi();
        colid_suivi.setCellValueFactory(new PropertyValueFactory<>("idSuiv"));
        colidobj_suivi.setCellValueFactory(new PropertyValueFactory<>("Objectif"));
        colvaleur_suivi.setCellValueFactory(new PropertyValueFactory<>("valeurSuiv"));
        coldate_suivi.setCellValueFactory(new PropertyValueFactory<>("dateSuiv"));
        colidcli_suivi.setCellValueFactory(new PropertyValueFactory<>("Client"));
        tv_suivi.setItems(suivis);
    }

    @FXML
    private void rechercherSuivi(KeyEvent event) {
       // .event.......
    }

    @FXML
    private void selectTriSuivi(ActionEvent event) {
        ServiceSuivi ss = new ServiceSuivi();
        ObservableList<Suivi> suivis = FXCollections.observableArrayList();
        if (cbtriSuivi.getValue().equals("par ID")) {
            suivis = ss.trierSuiviparId();
        } else if (cbtriSuivi.getValue().equals("par ID client")) {
            suivis = ss.trierSuiviparIdClient();
        } else {
            suivis = ss.trierSuiviparIdObjectif();
        }
        tv_suivi.setItems(suivis);
    }
    public void selected_item(int id){
        ServiceSuivi ss = new ServiceSuivi();
        System.out.println("erreur2");
        ObservableList<Suivi> suivis = ss.getListSuivi(id);
        System.out.println("erreur3");
        colidobj_suivi.setCellValueFactory(new PropertyValueFactory<>("Objectif"));
        colvaleur_suivi.setCellValueFactory(new PropertyValueFactory<>("valeurSuiv"));
        coldate_suivi.setCellValueFactory(new PropertyValueFactory<>("dateSuiv"));
       tv_suivi.setItems(suivis);
    }

}
