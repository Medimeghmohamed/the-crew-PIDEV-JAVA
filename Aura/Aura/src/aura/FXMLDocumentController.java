/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aura;

import Entities.Objectif;
import Entities.ObjectifPred;
import Entities.Suivi;
import Services.ServiceAdmin;
import Services.ServiceClient;
import Services.ServiceObjectif;
import Services.ServiceObjectifPred;
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
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Chirine
 */
public class FXMLDocumentController implements Initializable {
      private Label label;
    @FXML
    private AnchorPane tab1;
    @FXML
    private TableView<Objectif> tvobjectifs;
    @FXML
    private TableColumn<Objectif, String> colid;
    @FXML
    private TableColumn<Objectif, String> coldesc;
    @FXML
    private TableColumn<Objectif, Integer> colrep;
    @FXML
    private TableColumn<Objectif, String> coldate;
    @FXML
    private TableColumn<Objectif, Integer> colduree;
    @FXML
    private ComboBox cbobj;
    @FXML
    private TextField tfobj;
    @FXML
    private ComboBox cbrep;
    @FXML
    private TextField tfid;
    @FXML
    private TextField tfduree;
    @FXML
    private TextField tfdate;
    @FXML
    private AnchorPane tab2;
    @FXML
    private AnchorPane tab21;
    @FXML
    private TextField tfidCli;
    @FXML
    private Label erreurObj;
    @FXML
    private TextField tfid_pred;
    @FXML
    private TextField tfdesc_pred;
    @FXML
    private TextField tfduree_pred;
    @FXML
    private TableColumn<ObjectifPred, String> colid_pred;
    @FXML
    private TableColumn<ObjectifPred, String> coldes_pred;
    @FXML
    private TableColumn<ObjectifPred, Integer> colduree_pred;
    @FXML
    private TextField tfidadP;
    @FXML
    private TableColumn<ObjectifPred, String> colidad_pred;
    @FXML
    private TableView<ObjectifPred> tvObjectifPred;
    @FXML
    private Label dateFicheSuivi;
    @FXML
    private TextField tfid_suiv;
    @FXML
    private ComboBox<String> cbobjsuivi;
    @FXML
    private ComboBox<Integer> cbrep_suivi;
    @FXML
    private TableColumn<Suivi, String> colid_suivi;
    @FXML
    private TableColumn<Suivi, String> colidobj_suivi;
    @FXML
    private TableColumn<Suivi, Integer> colvaleur_suivi;
    @FXML
    private TableColumn<Suivi, String> coldate_suivi;
    @FXML
    private TableColumn<Suivi, String> colidcli_suivi;
    @FXML
    private TableView<Suivi> tv_suivi;
    @FXML
    private TextField tfrechObj;
    @FXML
    private ComboBox<String> cbtriObj;
    @FXML
    private TextField tfrechObjPred;
    @FXML
    private ComboBox<String> cbtriObjPred;
    @FXML
    private TextField tfrechSuivi;
    @FXML
    private ComboBox<String> cbtriSuivi;
    @FXML
    private ComboBox<String> cbidObjectifBilan;
    @FXML
    private ComboBox<String> cbDateBilan;
    @FXML
    private PieChart pcBilan;
    @FXML
    private Label lbResultat;
    @FXML
    private Button btnAjouterPred;
    @FXML
    private Button btnModifierPred;
    @FXML
    private Button btnSupprimerPred;
    @FXML
    private Button btnAjouterObj;
    @FXML
    private Button btnModifierObj;
    @FXML
    private Button btnSupprimerObj;

    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceObjectifPred sop = new ServiceObjectifPred();
        ObservableList<ObjectifPred> objectifsP = sop.afficherObjectifsPred();
        ServiceObjectif so = new ServiceObjectif();
        ObservableList<Objectif> objectifs = so.afficherObjectifs();
        ServiceSuivi ssuiv = new ServiceSuivi();
        ObservableList<Suivi> suivis = ssuiv.afficherSuivi();

        ObservableList<String> listObj = sop.getValuesObjectifs();
        cbobj.setItems(listObj);
        ObservableList<String> listObjSuiv = so.getMesObjectifs(12345676);
        cbobjsuivi.setItems(listObjSuiv);
        tfobj.setText(null);
        ObservableList<String> listIdObjBilan = ssuiv.getObjectifBilan("12345676");
        cbidObjectifBilan.setItems(listIdObjBilan);
        //les valeurs des réponses
        ObservableList<Integer> listRep = FXCollections.observableArrayList();
        for (int i = 0; i <= 50; i++) {
            listRep.add(i);
        }
        cbrep.setItems(listRep);
        cbrep_suivi.setItems(listRep);
        ObservableList<String> listTriObj = FXCollections.observableArrayList("par ID", "par réponse", "par description");
        cbtriObj.setItems(listTriObj);
        ObservableList<String> listTriObjPred = FXCollections.observableArrayList("par ID", "par description", "par durée");
        cbtriObjPred.setItems(listTriObjPred);
        ObservableList<String> listTriSuivi = FXCollections.observableArrayList("par ID", "par ID client", "par ID objectif");
        cbtriSuivi.setItems(listTriSuivi);

        afficherObjectifs();
        afficherObjectifsPred();
        afficherSuivi();

        //afficher la date courante
        tfdate.setText(new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime()));
        dateFicheSuivi.setText(new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime()));
    }

    //combobox objets prédéfinis
    @FXML
    private void selectObj(ActionEvent event) {
        String s = cbobj.getSelectionModel().getSelectedItem().toString();
    }

//*******************************************************CLIENT: OBJECTIFS********************************************************
    @FXML
    private void ajouterObjectif(ActionEvent event) {
        ServiceObjectif sp = new ServiceObjectif();
        ServiceClient sc = new ServiceClient();
        Objectif o = new Objectif();
//        o.setId(tfid.getText());
        if (cbobj.getValue() == null && tfobj.getText() != null) {
            o.setDescription(tfobj.getText());

            o.setReponse(Integer.parseInt(cbrep.getValue().toString()));
            o.setDuree(Integer.parseInt(tfduree.getText()));
            o.setCli(sc.load_data_modify(tfidCli.getText()));
            sp.ajouterObjectif(o);
            afficherObjectifs();
            tfid.setText(null);
            cbobj.setValue(null);
            tfobj.setText(null);
            cbrep.setValue(null);
            tfduree.setText(null);
            tfidCli.setText(null);
            erreurObj.setText(null);
            new animatefx.animation.Flash(btnAjouterObj).play();

        } else if (cbobj.getValue() != null && tfobj.getText() == null) {
            o.setDescription(cbobj.getValue().toString());
            o.setReponse(Integer.parseInt(cbrep.getValue().toString()));
            o.setDuree(Integer.parseInt(tfduree.getText()));
            o.setCli(sc.load_data_modify(tfidCli.getText()));
            sp.ajouterObjectif(o);
            afficherObjectifs();
            tfid.setText(null);
            cbobj.setValue(null);
            tfobj.setText(null);
            cbrep.setValue(null);
            tfduree.setText(null);
            tfidCli.setText(null);
            erreurObj.setText(null);
            new animatefx.animation.Flash(btnAjouterObj).play();

        } else {
            erreurObj.setText("!! Il faut soit saisir soit choisir");
            cbobj.setValue(null); //throws exception mais l'ajout se fait normalement
            tfobj.setText(null);
            new animatefx.animation.Shake(btnAjouterObj).play();
        }

    }

    @FXML
    private void modifierObjectif(ActionEvent event) {
        ServiceObjectif sp = new ServiceObjectif();
        ServiceClient sc = new ServiceClient();
        Objectif o = new Objectif();

//        o.setId(tfid.getText());
        o.setDescription(cbobj.getValue().toString());
        o.setReponse(Integer.parseInt(cbrep.getValue().toString()));
        o.setDuree(Integer.parseInt(tfduree.getText()));
        o.setCli(sc.load_data_modify(tfidCli.getText()));
        //o.setIdCli(tfidCli.getText());
        sp.modifierObjectif(o);
        afficherObjectifs();
        new animatefx.animation.Flash(btnModifierObj).play();

    }

    @FXML
    private void supprimerObjectif(ActionEvent event) {
        ServiceObjectif sp = new ServiceObjectif();
        // tvobjectifs.getItems().removeAll(tvobjectifs.getSelectionModel().getSelectedItem());
//        sp.supprimerObjectif(tfid.getText());
        afficherObjectifs();
        tfid.setText(null);
        cbobj.setValue(null);
        tfobj.setText(null);
        cbrep.setValue(null);
        tfduree.setText(null);
        tfidCli.setText(null);
        erreurObj.setText(null);
        new animatefx.animation.Flash(btnSupprimerObj).play();

    }

    private void afficherObjectifs() {
        ServiceObjectif sop = new ServiceObjectif();
        ObservableList<Objectif> objectifs = sop.afficherObjectifs();
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        coldesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colrep.setCellValueFactory(new PropertyValueFactory<>("reponse"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colduree.setCellValueFactory(new PropertyValueFactory<>("duree"));
        tvobjectifs.setItems(objectifs);
    }

    @FXML
    private void selectRep(ActionEvent event) {
    }

    //appyer sur une ligne de la table ->afficher dans labels
    @FXML
    private void OnMouse(MouseEvent event) {
        Objectif obj = tvobjectifs.getSelectionModel().getSelectedItem();
//        tfid.setText(obj.getId());
        cbobj.setValue(obj.getDescription());
        cbrep.setValue(obj.getReponse());
        tfdate.setText(obj.getDate());
        tfduree.setText("" + obj.getDuree());
        tfidCli.setText(obj.getCli().getId());
    }

    @FXML
    public void rechercherObjectif() {
        ServiceObjectif sop = new ServiceObjectif();
        ObservableList<Objectif> objectifs = sop.rechercherObjectif(tfrechObj.getText());
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        coldesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colrep.setCellValueFactory(new PropertyValueFactory<>("reponse"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colduree.setCellValueFactory(new PropertyValueFactory<>("duree"));
        tvobjectifs.setItems(objectifs);

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
        tvobjectifs.setItems(objectifs);
    }

    //*******************************************************ADMIN: OBJECTIFS PREDEFINIS***************************************
    @FXML
    private void ajouterPred(ActionEvent event) {
        ServiceObjectifPred sp = new ServiceObjectifPred();
        ObjectifPred o = new ObjectifPred();
        ServiceAdmin sa = new ServiceAdmin();

//        o.setIdP(tfid_pred.getText());
        o.setDescriptionP(tfdesc_pred.getText());
        o.setDureeP(Integer.parseInt(tfduree_pred.getText()));
        o.setAdmin(sa.load_data_modify(tfidadP.getText()));
        sp.ajouterObjectifPred(o);
        afficherObjectifsPred();
        tfid_pred.setText(null);
        tfdesc_pred.setText(null);
        tfduree_pred.setText(null);
        tfidadP.setText(null);
    }

    private void afficherObjectifsPred() {
        ServiceObjectifPred sop = new ServiceObjectifPred();
        ObservableList<ObjectifPred> objectifsP = sop.afficherObjectifsPred();
        colid_pred.setCellValueFactory(new PropertyValueFactory<>("idP"));
        coldes_pred.setCellValueFactory(new PropertyValueFactory<>("descriptionP"));
        colduree_pred.setCellValueFactory(new PropertyValueFactory<>("dureeP"));
        colidad_pred.setCellValueFactory(new PropertyValueFactory<>("idAdminP"));
        tvObjectifPred.setItems(objectifsP);

        new animatefx.animation.Flash(btnAjouterPred).play();
    }

    @FXML
    private void modifierPred(ActionEvent event) {
        ServiceObjectifPred sp = new ServiceObjectifPred();
        ObjectifPred o = new ObjectifPred();
        ServiceAdmin sa = new ServiceAdmin();
//        o.setIdP(tfid_pred.getText());
        o.setDescriptionP(tfdesc_pred.getText());
        o.setDureeP(Integer.parseInt(tfduree_pred.getText()));
        o.setAdmin(sa.load_data_modify(tfidadP.getText()));
        sp.modifierObjectifPred(o);
        afficherObjectifsPred();
        tfid_pred.setText(null);
        tfdesc_pred.setText(null);
        tfduree_pred.setText(null);
        tfidadP.setText(null);
        new animatefx.animation.Flash(btnModifierPred).play();

    }

    @FXML
    private void supprimerPred(ActionEvent event) {
        ServiceObjectifPred sp = new ServiceObjectifPred();
       // sp.supprimerObjectifPred(tfid_pred.getText());
        afficherObjectifsPred();
        tfid_pred.setText(null);
        tfdesc_pred.setText(null);
        tfduree_pred.setText(null);
        tfidadP.setText(null);
        new animatefx.animation.Flash(btnSupprimerPred).play();
    }

    @FXML
    private void rechercherObjectifPred(KeyEvent event) {
        ServiceObjectifPred sop = new ServiceObjectifPred();
        ObservableList<ObjectifPred> objectifsP = sop.rechercherObjectifPred(tfrechObjPred.getText());
        tvObjectifPred.setItems(objectifsP);
    }

    @FXML
    private void selectTriObjPred(ActionEvent event) {
        ServiceObjectifPred sop = new ServiceObjectifPred();
        ObservableList<ObjectifPred> objectifs = FXCollections.observableArrayList();
        if (cbtriObjPred.getValue().equals("par ID")) {
            objectifs = sop.trierObjectifPredparId();
        } else if (cbtriObjPred.getValue().equals("par durée")) {
            objectifs = sop.trierObjectifPredparDuree();
        } else {
            objectifs = sop.trierObjectifPredparDesc();
        }
        tvObjectifPred.setItems(objectifs);
    }

    @FXML
    private void OnMouseObjectifPred(MouseEvent event) {
        ObjectifPred obj = tvObjectifPred.getSelectionModel().getSelectedItem();
//        tfid_pred.setText(obj.getIdP());
        tfdesc_pred.setText(obj.getDescriptionP());
        tfduree_pred.setText("" + obj.getDureeP());
        tfidadP.setText(obj.getAdmin().getId());
    }
//**************************************************Client: Suivi*****************************************************************

    @FXML
    private void ajouterSuivi(ActionEvent event) {
        ServiceObjectif sp = new ServiceObjectif();
        ServiceSuivi ss = new ServiceSuivi();
        ServiceClient sc = new ServiceClient();
        Suivi s = new Suivi();

//        s.setIdSuiv(tfid_suiv.getText());
//        s.setValeurSuiv(cbrep_suivi.getValue());
//        s.setClient(sc.load_data_modify("12345676")); //!!! a changer !!!
//        s.setObjectif(sp.load_data_modify(sp.getIdObj(cbobjsuivi.getValue(), "12345676")));
//        s.setDateSuiv(dateFicheSuivi.getText());
//        ss.ajouterSuivi(s);
//        afficherSuivi();
//
//        int jourCourant = Integer.parseInt(new SimpleDateFormat("dd").format(Calendar.getInstance().getTime()));
//        System.out.println("jour courant" + jourCourant);
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
        colidobj_suivi.setCellValueFactory(new PropertyValueFactory<>("idObjectifSuiv"));
        colvaleur_suivi.setCellValueFactory(new PropertyValueFactory<>("valeurSuiv"));
        coldate_suivi.setCellValueFactory(new PropertyValueFactory<>("dateSuiv"));
        colidcli_suivi.setCellValueFactory(new PropertyValueFactory<>("idClientSuiv"));
        tv_suivi.setItems(suivis);
    }

    @FXML
    private void rechercherSuivi(KeyEvent event) {
        ServiceSuivi ss = new ServiceSuivi();
        ObservableList<Suivi> suivis = ss.rechercherSuivi(tfrechSuivi.getText());
        tv_suivi.setItems(suivis);
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

    @FXML
    private void selectIdObjBilan(ActionEvent event) {
        ServiceSuivi ss = new ServiceSuivi();
//        ObservableList<String> listDateSuiv = ss.getDateBilan(cbidObjectifBilan.getValue());
  //      cbDateBilan.setItems(listDateSuiv);
    }

    @FXML
    private void selectDateBilan(ActionEvent event) {
//        ServiceObjectif so = new ServiceObjectif();
//        ServiceSuivi ss = new ServiceSuivi();
//        ObservableList<Data> list = FXCollections.observableArrayList(
//       //         new PieChart.Data("Pas fait", (100 - (ss.getValeur(cbidObjectifBilan.getValue(), cbDateBilan.getValue()) * 100) / so.getRepObj("O4"))),
//         //       new PieChart.Data("Fait", (ss.getValeur(cbidObjectifBilan.getValue(), cbDateBilan.getValue()) * 100) / so.getRepObj(cbidObjectifBilan.getValue())));
//        pcBilan.setData(list);
//
//        for (final PieChart.Data data : pcBilan.getData()) {
//            data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
//                @Override
//                public void handle(MouseEvent event) {
//                    lbResultat.setText(String.valueOf(data.getPieValue() + " %"));
//                }
//            });
    //    }
    }

    @FXML
    private void showPieChart(ActionEvent event) {
//        ServiceObjectif so = new ServiceObjectif();
//        ServiceSuivi ss = new ServiceSuivi();
//        DefaultPieDataset pieDataset = new DefaultKeyedValuesDataset();
//        pieDataset.setValue("Pas fait", (100 - (ss.getValeur(cbidObjectifBilan.getValue(), cbDateBilan.getValue()) * 100) / so.getRepObj("O4")));
//        pieDataset.setValue("Fait", (ss.getValeur(cbidObjectifBilan.getValue(), cbDateBilan.getValue()) * 100) / so.getRepObj(cbidObjectifBilan.getValue()));
//
//        JFreeChart chart = ChartFactory.createPieChart("Pie chart", pieDataset);
//        PiePlot P = (PiePlot) chart.getPlot();
//        ChartFrame frame = new ChartFrame("Pie chart", chart);
//        frame.setVisible(true);
//        frame.setSize(450, 500);
    }   
    
}
