/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test_cnx;

import Entities.Admin;
import Service.ServiceAdmin;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author SeifBS
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;

    @FXML
    private TextField id_a;
    @FXML
    private TextField nom_a;
    @FXML
    private TextField prenom_a;
    @FXML
    private TextField email_a;
    @FXML
    private TextField tel_a;
    @FXML
    private TextField nom_am;
    @FXML
    private TextField prenom_am;
    @FXML
    private TextField email_am;
    @FXML
    private TextField tel_am;
    @FXML
    private Label tab_admins;
    @FXML
    private TextField id_am;
    @FXML
    private TextField id_as;

    private void handleButtonAction(ActionEvent event) {
        System.out.println("You"
                + " clicked me!");
        label.setText("Hello World!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ServiceAdmin sa = new ServiceAdmin();

        tab_admins.setText(sa.afficherAdmin().toString());
    }

    @FXML
    private void ajouter_admin(ActionEvent event) {
        ServiceAdmin sa = new ServiceAdmin();

        Admin a = new Admin();

        a.setId(id_a.getText());
        a.setNom(nom_a.getText());
        a.setPrenom(prenom_a.getText());
        a.setEmail(email_a.getText());
        a.setTel(tel_a.getText());
        sa.ajouterAdmin(a);

        tab_admins.setText(sa.afficherAdmin().toString());

    }

    @FXML
    private void modifier_admin(ActionEvent event) {
        ServiceAdmin sa = new ServiceAdmin();
        Admin a = new Admin();
        String id = id_am.getText();

        a.setId(id);

        a.setNom(nom_am.getText());
        a.setPrenom(prenom_am.getText());
        a.setEmail(email_am.getText());
        a.setTel(tel_am.getText());
        sa.modifierAdmin(a);
        tab_admins.setText(sa.afficherAdmin().toString());

    }

    @FXML
    private void supprimer_admin(ActionEvent event) {
        ServiceAdmin sa = new ServiceAdmin();

        String id = id_as.getText();
        sa.supprimerAdmin(id);
        tab_admins.setText(sa.afficherAdmin().toString());

    }

}
