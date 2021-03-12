/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auraa;

import entities.article;
import entities.commentaire;
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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import services.PDFManager;
import services.serviceCommentaire;

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
    @FXML
    private Label labcom;
    @FXML
    private Label espart;
    @FXML
    private TextField espcom;
    @FXML
    private TextField recherche;
    @FXML
    private Pagination pagination;
    
    File[] filesdoc = Paths.get("C:\\Users\\akram\\Desktop\\pi\\").toFile().listFiles();
    
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
    private void addart(ActionEvent event) throws IOException {
        serviceArticle s = new serviceArticle();
        PDFManager pdfManager = new PDFManager();
        article a = new article();
      String k ;
        a.setTitre(estitre.getText());
        a.setTheme(estheme.getText());
        a.setNom_auteur(esauteur.getText());
      //  a.setArticle(esarticle.getText());
       k= file();
       a.setArticle(k);
        s.ajouter(a);
         //espart.setText(s.recup_article(esarticle.getText()));
         pagination.setPageFactory(new Callback < Integer , Node >()
                 {@Override
                 public Node call(Integer pageIndex){
                     try {
                         return createPage(pageIndex);
                     } catch (IOException ex) {
                         Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                     }
                     return null;
                 }

           
                 });
    }
     public VBox createPage(int index) throws IOException {
 
      
        PDFManager pdfManager = new PDFManager();
        serviceCommentaire j = new serviceCommentaire();
        
                
       File f = filesdoc[index];
            String J = f.getAbsolutePath();
                
        
             pdfManager.setFilePath(J);
        try {
            
                    
            String text = pdfManager.toText();
          espart.setText(pdfManager.toText());
           
          
           
        } catch (IOException ex) {
            System.out.println("err");
        }
                
        VBox pageBox = new VBox();
        pageBox.getChildren();
        
        return pageBox;
    }  
    
    
    
         
    
       
             
    
      
    
        
        
        
    

    @FXML
    public String  file() throws IOException {
        String s = null ;
        
        FileChooser fc =new FileChooser();
        fc.getExtensionFilters().addAll( new ExtensionFilter ("PDF Files ","*.pdf"));
        File selectedFile = fc.showOpenDialog(null);
        
        if ( selectedFile !=null){
            //System.out.println("file ok");
            
                   s =selectedFile.getAbsolutePath();
                   
            String k = selectedFile.getName();
           
                    
                    
                 Path from = Paths.get(selectedFile.toURI());
        Path to = Paths.get("C:\\Users\\akram\\Desktop\\pi\\"+k);
                   
        CopyOption[] options = new CopyOption[]{
                StandardCopyOption.REPLACE_EXISTING,
                StandardCopyOption.COPY_ATTRIBUTES
        };
        Files.copy(from, to, options);
                   
                System.out.println(s);  
            
    }else{
            System.out.println("file is not valid");
            
        }
        return s;
    }
   

    @FXML
    private void modar(ActionEvent event) throws IOException {
          {
        serviceArticle sp = new serviceArticle();
        article a = new article();
       
       	String k ;
       //Integer.parseInt(this.id_challenge.getText());
        //c.setTitre(id_challenge.getText());
       a=sp.recup(Integer.parseInt(modtitre.getText()));
       a.setTitre(estitre.getText());
        a.setTheme(estheme.getText());
        a.setNom_auteur(esauteur.getText());
      //a.setArticle(esarticle.getText());
        k=file();
       a.setArticle(k);
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

    @FXML
    private void ajoutcom(ActionEvent event) {
           serviceCommentaire s = new serviceCommentaire();
        commentaire a = new commentaire();
        a.setCommentaire(espcom.getText());
       s.ajouterc(a);
        labcom.setText(s.recup_commentaire(espcom.getText()));
       /* try {
            s.pdfto();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
    }

    @FXML
    private void suppcom(ActionEvent event) {
        
        serviceCommentaire sp = new serviceCommentaire();
       
     
        sp.supprimerc(labcom.getText());
        labcom.setText(null);
        espcom.setText(null);
    }

   /* @FXML
    private void modcom(ActionEvent event) {
         serviceCommentaire sp = new serviceCommentaire();
        commentaire a = new commentaire();
       
       	
       //Integer.parseInt(this.id_challenge.getText());
        //c.setTitre(id_challenge.getText());
         
         a.setCommentaire(espcom.getText());
       sp.ajouterc(a);
       labcom.setText(sp.recup_commentaire(espcom.getText()));
       
       
    }*/

    @FXML
    private void triert(ActionEvent event) {
        
          serviceArticle sop = new serviceArticle();
        ObservableList <article> articles= sop.trier();
        coltitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        coltheme.setCellValueFactory(new PropertyValueFactory<>("theme"));
        colauteur.setCellValueFactory(new PropertyValueFactory<>("nom_auteur"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colarticle.setCellValueFactory(new PropertyValueFactory<>("article"));
        colidc.setCellValueFactory(new PropertyValueFactory<>("id_coach"));
        colidcl.setCellValueFactory(new PropertyValueFactory<>("id_client"));
        lab.setItems(articles);
    }

    @FXML
    private void rech(ActionEvent event) {
          serviceArticle sop = new serviceArticle();
        ObservableList <article> articles= sop.recherche(recherche.getText());
        coltitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        coltheme.setCellValueFactory(new PropertyValueFactory<>("theme"));
        colauteur.setCellValueFactory(new PropertyValueFactory<>("nom_auteur"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colarticle.setCellValueFactory(new PropertyValueFactory<>("article"));
        colidc.setCellValueFactory(new PropertyValueFactory<>("id_coach"));
        colidcl.setCellValueFactory(new PropertyValueFactory<>("id_client"));
        lab.setItems(articles);
    }

  /*  @FXML
    private void modcom(ActionEvent event) {
    }

   @FXML
    public String  file(ActionEvent event) {
        String s = null ;
        FileChooser fc =new FileChooser();
        fc.getExtensionFilters().addAll( new ExtensionFilter ("PDF Files ","*.pdf"));
        File selectedFile = fc.showOpenDialog(null);
        
        if ( selectedFile !=null){
            //System.out.println("file ok");
                   s =selectedFile.getAbsolutePath();
            
    }else{
            System.out.println("file is not valid");
            
        }
        return s;
    }
*/

    @FXML
    private void modcom(ActionEvent event) {
    }

  
    
}
    

