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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Chirine
 */
public class MesObjectifsController implements Initializable {

    @FXML
    private Button btnAjouterObj;
    @FXML
    private Button btnModifierObj;
    @FXML
    private Button btnSupprimerObj;
    @FXML
    private TextField tfrechObj;
    @FXML
    private ComboBox<?> cbtriObj;
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
    private TextField tfidCli;
    @FXML
    private Label erreurObj;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    private void OnMouse(MouseEvent event) {
    }

    @FXML
    private void selectObj(ActionEvent event) {
    }

    @FXML
    private void selectRep(ActionEvent event) {
    }
    
}
