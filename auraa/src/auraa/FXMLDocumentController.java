/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auraa;

import entities.article;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import services.serviceArticle;
import interfaces.Iarticle;
import static java.awt.PageAttributes.MediaType.C;
import javafx.collections.ObservableList;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author akram
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private TextField estitre;
    @FXML
    private TextField estheme;
    @FXML
    private TextField esauteur;
    @FXML
    private TextField esarticle;
    @FXML
    private TableView<article> lab;
    @FXML
    private TableColumn<article, String> coltitre;
    @FXML
    private TableColumn<article, String> coltheme;
    @FXML
    private TableColumn<article, String> colauteur;
    @FXML
    private TableColumn<article, String> coldate;
    @FXML
    private TableColumn<article, String> colarticle;
    @FXML
    private TableColumn<article, String > colidc;
    @FXML
    private TableColumn<article, String> colidcl;
    @FXML
    private TextField modtitre;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        serviceArticle sop = new serviceArticle();
        ObservableList<article> articles= sop.showArticle();
    }    

    @FXML
    private void showart() {
        serviceArticle sop = new serviceArticle();
        ObservableList <article> articles= sop.showArticle();
        coltitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        coltheme.setCellValueFactory(new PropertyValueFactory<>("theme"));
        colauteur.setCellValueFactory(new PropertyValueFactory<>("nom_auteur"));
        colarticle.setCellValueFactory(new PropertyValueFactory<>("article"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colidc.setCellValueFactory(new PropertyValueFactory<>("id_coach"));
        colidcl.setCellValueFactory(new PropertyValueFactory<>("id_client"));
        lab.setItems(articles);
    }
    @FXML
    private void addart(ActionEvent event) {
        serviceArticle s = new serviceArticle();
        article a = new article();

        a.setTitre(estitre.getText());
        a.setTheme(estheme.getText());
        a.setNom_auteur(esauteur.getText());
        a.setArticle(esarticle.getText());
        s.ajouter(a);
        
    }

   

    @FXML
    private void modar(ActionEvent event) {
          {
        serviceArticle sp = new serviceArticle();
        article a = new article();
       
       	
       //Integer.parseInt(this.id_challenge.getText());
        //c.setTitre(id_challenge.getText());
       a=sp.recup(Integer.parseInt(modtitre.getText()));
       a.setTitre(estitre.getText());
        a.setTheme(estheme.getText());
        a.setNom_auteur(esauteur.getText());
        a.setArticle(esarticle.getText());
        sp.modifier(a);
        showart();
       
    }
   

       
    }

    @FXML
    private void suppar(ActionEvent event) {
          serviceArticle sp = new serviceArticle();
         
  
     
        sp.supprimer(modtitre.getText());
        showart();
     
        
    }

    

    private void showart(ActionEvent event) {
        serviceArticle sop = new serviceArticle();
        ObservableList <article> articles= sop.showArticle();
        coltitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        coltheme.setCellValueFactory(new PropertyValueFactory<>("theme"));
        colauteur.setCellValueFactory(new PropertyValueFactory<>("auteur"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colarticle.setCellValueFactory(new PropertyValueFactory<>("article"));
        colidc.setCellValueFactory(new PropertyValueFactory<>("id_coach"));
        colidcl.setCellValueFactory(new PropertyValueFactory<>("id_client"));
        lab.setItems(articles);
    }
}


  /*  @FXML
    private void showart(ActionEvent event) {
    }*/
    

