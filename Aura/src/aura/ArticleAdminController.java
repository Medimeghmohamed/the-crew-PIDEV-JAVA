/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auraa;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import services.PDFManager;
import entities.article;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import services.PDFManager;
import services.serviceArticle;
import services.serviceCommentaire;
import services.PDFManager;
import services.serviceCommentaire;
import auraa.FXMLDocumentController;
import java.nio.file.CopyOption;
import java.nio.file.StandardCopyOption;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
/**
 * FXML Controller class
 *
 * @author akram
 */
public class ArticleAdminController implements Initializable {

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
    private TableColumn<article , Integer> colapp;
File[] filesdoc = Paths.get("C:\\Users\\akram\\Desktop\\pi\\").toFile().listFiles();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    
        serviceArticle sop = new serviceArticle();
        ObservableList <article> articles= sop.showArticleAdmin();
        coltitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        coltheme.setCellValueFactory(new PropertyValueFactory<>("theme"));
        colauteur.setCellValueFactory(new PropertyValueFactory<>("nom_auteur"));
        colarticle.setCellValueFactory(new PropertyValueFactory<>("article"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colapp.setCellValueFactory(new PropertyValueFactory<>("approuver"));

        
        
        lab.setItems(articles);
    
    }       
    @FXML
    private void approuverarticle(ActionEvent event) throws IOException {
       
        article a= lab.getSelectionModel().getSelectedItem();
        System.out.println(a.getId());
       String k ;
       k=a.getArticle();
       Path from = Paths.get("C:\\Users\\akram\\Desktop\\pi\\"+k);
       Path to = Paths.get("C:\\Users\\akram\\Desktop\\pi1\\"+k);
                   
        CopyOption[] options = new CopyOption[]{
                StandardCopyOption.REPLACE_EXISTING,
                StandardCopyOption.COPY_ATTRIBUTES
        };
        Files.copy(from, to, options);
       
      
        System.out.println(a.getArticle());
        serviceArticle sa=new serviceArticle();
        sa.approuver(a);
        
         
        
        
        
                
        
                 
 
        
        serviceArticle sop = new serviceArticle();
        ObservableList <article> articles= sop.showArticleAdmin();
        coltitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        coltheme.setCellValueFactory(new PropertyValueFactory<>("theme"));
        colauteur.setCellValueFactory(new PropertyValueFactory<>("nom_auteur"));
        colarticle.setCellValueFactory(new PropertyValueFactory<>("article"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colapp.setCellValueFactory(new PropertyValueFactory<>("approuver"));

        
        
        lab.setItems(articles);
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    @FXML
    private void supprimer(ActionEvent event) {
          article a= lab.getSelectionModel().getSelectedItem();
      serviceArticle sa=new serviceArticle();
        sa.supprimerad(a);
         serviceArticle sop = new serviceArticle();
        ObservableList <article> articles= sop.showArticleAdmin();
        coltitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        coltheme.setCellValueFactory(new PropertyValueFactory<>("theme"));
        colauteur.setCellValueFactory(new PropertyValueFactory<>("nom_auteur"));
        colarticle.setCellValueFactory(new PropertyValueFactory<>("article"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colapp.setCellValueFactory(new PropertyValueFactory<>("approuver"));

        
        
        lab.setItems(articles);
         
    }
    
}
