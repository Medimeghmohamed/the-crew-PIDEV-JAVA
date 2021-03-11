/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aura;

import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Chirine
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TextField tfid_pred;
    @FXML
    private TextField tfdesc_pred;
    @FXML
    private TextField tfduree_pred;
    @FXML
    private Button btnAjouterPred;
    @FXML
    private Button btnModifierPred;
    @FXML
    private Button btnSupprimerPred;
    @FXML
    private TextField tfidadP;
    @FXML
    private TableView<?> tvObjectifPred;
    @FXML
    private TableColumn<?, ?> colid_pred;
    @FXML
    private TableColumn<?, ?> coldes_pred;
    @FXML
    private TableColumn<?, ?> colduree_pred;
    @FXML
    private TableColumn<?, ?> colidad_pred;
    @FXML
    private TextField tfrechObjPred;
    @FXML
    private ComboBox<?> cbtriObjPred;
    @FXML
    private AnchorPane tab1;
    @FXML
    private TableView<?> tvobjectifs;
    @FXML
    private TableColumn<?, ?> colid;
    @FXML
    private TableColumn<?, ?> coldesc;
    @FXML
    private TableColumn<?, ?> colrep;
    @FXML
    private TableColumn<?, ?> coldate;
    @FXML
    private TableColumn<?, ?> colduree;
    @FXML
    private ComboBox<?> cbobj;
    @FXML
    private TextField tfobj;
    @FXML
    private ComboBox<?> cbrep;
    @FXML
    private TextField tfid;
    @FXML
    private TextField tfduree;
    @FXML
    private TextField tfdate;
    @FXML
    private Button btnAjouterObj;
    @FXML
    private Button btnModifierObj;
    @FXML
    private Button btnSupprimerObj;
    @FXML
    private TextField tfidCli;
    @FXML
    private Label erreurObj;
    @FXML
    private TextField tfrechObj;
    @FXML
    private ComboBox<?> cbtriObj;
    @FXML
    private AnchorPane tab2;
    @FXML
    private Label dateFicheSuivi;
    @FXML
    private TextField tfid_suiv;
    @FXML
    private ComboBox<?> cbobjsuivi;
    @FXML
    private ComboBox<?> cbrep_suivi;
    @FXML
    private TableView<?> tv_suivi;
    @FXML
    private TableColumn<?, ?> colid_suivi;
    @FXML
    private TableColumn<?, ?> colidobj_suivi;
    @FXML
    private TableColumn<?, ?> colvaleur_suivi;
    @FXML
    private TableColumn<?, ?> coldate_suivi;
    @FXML
    private TableColumn<?, ?> colidcli_suivi;
    @FXML
    private TextField tfrechSuivi;
    @FXML
    private ComboBox<?> cbtriSuivi;
    @FXML
    private AnchorPane tab21;
    @FXML
    private PieChart pcBilan;
    @FXML
    private ComboBox<?> cbidObjectifBilan;
    @FXML
    private ComboBox<?> cbDateBilan;
    @FXML
    private Label lbResultat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouterPred(ActionEvent event) {
    }

    @FXML
    private void modifierPred(ActionEvent event) {
    }

    @FXML
    private void supprimerPred(ActionEvent event) {
    }

    @FXML
    private void OnMouseObjectifPred(MouseEvent event) {
    }

    @FXML
    private void rechercherObjectifPred(KeyEvent event) {
    }

    @FXML
    private void selectTriObjPred(ActionEvent event) {
    }

    @FXML
    private void OnMouse(MouseEvent event) {
    }

    @FXML
    private void selectObj(ActionEvent event) {
    }

    @FXML
    private void selectRep(ActionEvent event) {
    }

    @FXML
    private void ajouterObjectif(ActionEvent event) {
    }

    @FXML
    private void modifierObjectif(ActionEvent event) {
    }

    @FXML
    private void supprimerObjectif(ActionEvent event) {
    }

    @FXML
    private void rechercherObjectif(KeyEvent event) {
    }

    @FXML
    private void selectTriObj(ActionEvent event) {
    }

    @FXML
    private void ajouterSuivi(ActionEvent event) {
    }

    @FXML
    private void rechercherSuivi(KeyEvent event) {
    }

    @FXML
    private void selectTriSuivi(ActionEvent event) {
    }

    @FXML
    private void selectIdObjBilan(ActionEvent event) {
    }

    @FXML
    private void selectDateBilan(ActionEvent event) {
    }

    @FXML
    private void showPieChart(ActionEvent event) {
    }
    
}
