/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aura.Article_Commentaire;

import Service.ServiceNotification;
import utils.Connexion;
import Entities.article;
import Entities.commentaire;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import Service.serviceArticle;
import Interfaces.Iarticle;
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
import java.sql.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.HostServices;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Pagination;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import static javafx.scene.input.KeyCode.S;
import static javafx.scene.input.KeyCode.T;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import static org.apache.pdfbox.cos.COSName.S;
import static org.apache.pdfbox.cos.COSName.T;
import Service.PDFManager;
import Service.serviceCommentaire;

/**
 * FXML Controller class
 *
 * @author akram
 */
public class ArtutiliController implements Initializable {
    public String id_user="";

    @FXML
    private TextField estitre;
    @FXML
    private TextField estheme;
    @FXML
    private TextField esauteur;
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
    private TableColumn<article, String> colidc;
    private TableColumn<article, String> colidcl;
    private Label labcom;
    private Label espart;
    @FXML
    private TextField recherche;
    private Pagination pagination;

    File[] filesdoc = Paths.get("C:\\Users\\akram\\Desktop\\pi1\\").toFile().listFiles();
    @FXML
    private Label label;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        serviceArticle sop = new serviceArticle();
        serviceCommentaire sc = new serviceCommentaire();

        ObservableList<article> articles = sop.showArticle("10");
        //serviceCommentaire sc = new serviceCommentaire ();

        /*         pagination.setPageFactory(new Callback < Integer , Node >()
                 {@Override
                 public Node call(Integer pageIndex){
                     try {
                         return createPage(pageIndex);
                     } catch (IOException ex) {
                         Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                     }
                     return null;
                 }

         
                 });*/
        //  List<String> list_commentaire=Arrays.asList(sc.showCommentaire_combobox());
    }

    @FXML
    private void showart() {
        serviceArticle sop = new serviceArticle();
        ObservableList<article> articles = sop.showArticle("10");
        coltitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        coltheme.setCellValueFactory(new PropertyValueFactory<>("theme"));
        colauteur.setCellValueFactory(new PropertyValueFactory<>("nom_auteur"));
        colarticle.setCellValueFactory(new PropertyValueFactory<>("article"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));

        lab.setItems(articles);
    }

    @FXML
    private void addart(ActionEvent event) throws IOException {
        serviceArticle s = new serviceArticle();
        PDFManager pdfManager = new PDFManager();
        article a = new article();
        String k;
        k = file();
        System.out.println(k);
        if ((estitre.getText().equals("")) || (estheme.getText().equals("")) || (esauteur.getText().equals("")) || (k == null)) {
            estitre.setStyle("-fx-border-color: red; -fx-borfrt-width: 2px;");
            new animatefx.animation.Shake(estitre).play();
            estheme.setStyle("-fx-border-color: red; -fx-borfrt-width: 2px;");
            new animatefx.animation.Shake(estheme).play();
            esauteur.setStyle("-fx-border-color: red; -fx-borfrt-width: 2px;");
            new animatefx.animation.Shake(esauteur).play();
            System.out.println("noooo");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Champ manquant");
            alert.setHeaderText(null);
            alert.setContentText("IL FAUT REMPLIR TOUS LES CHAMPS");
            alert.showAndWait();
        } else {

            a.setTitre(estitre.getText());
            a.setTheme(estheme.getText());
            a.setNom_auteur(esauteur.getText());
            a.setId_user("10");
            //  a.setArticle(esarticle.getText());

            a.setArticle(k);
            s.ajouter(a);
            ServiceNotification Notification = new ServiceNotification();
            Notification.Notification("Aura", "article ajouté");
            //espart.setText(s.recup_article(esarticle.getText()));

        }
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

    public String file() throws IOException {
        String s = null;

        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new ExtensionFilter("PDF Files ", "*.pdf"));
        File selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {
            //System.out.println("file ok");

            s = selectedFile.getName();

            String k = selectedFile.getName();

            Path from = Paths.get(selectedFile.toURI());
            Path to = Paths.get("C:\\Users\\akram\\Desktop\\pi\\" + k);

            CopyOption[] options = new CopyOption[]{
                StandardCopyOption.REPLACE_EXISTING,
                StandardCopyOption.COPY_ATTRIBUTES
            };
            Files.copy(from, to, options);

            System.out.println(s);

        } else {
            System.out.println("file is not valid");

        }
        return s;
    }

    @FXML
    private void modar(ActionEvent event) throws IOException {

        serviceArticle s = new serviceArticle();
        PDFManager pdfManager = new PDFManager();
        article a = new article();
        String k;
        k = file();
        System.out.println(k);
        if ((estitre.getText().equals("")) || (estheme.getText().equals("")) || (esauteur.getText().equals("")) || (k == null)) {
            estitre.setStyle("-fx-border-color: red; -fx-borfrt-width: 2px;");
            new animatefx.animation.Shake(estitre).play();
            estheme.setStyle("-fx-border-color: red; -fx-borfrt-width: 2px;");
            new animatefx.animation.Shake(estheme).play();
            esauteur.setStyle("-fx-border-color: red; -fx-borfrt-width: 2px;");
            new animatefx.animation.Shake(esauteur).play();
            System.out.println("noooo");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Champ manquant");
            alert.setHeaderText(null);
            alert.setContentText("IL FAUT REMPLIR TOUS LES CHAMPS");
            alert.showAndWait();
        }

        serviceArticle sa = new serviceArticle();

        article b = lab.getSelectionModel().getSelectedItem();
        a.setId(b.getId());
        System.out.println("1");

        a.setTitre(estitre.getText());
        a.setTheme(estheme.getText());
        a.setNom_auteur(esauteur.getText());
        k = file();
        System.out.println(a.getId());
        System.out.println("2");
        a.setArticle(k);
        System.out.println(a.getId());
        System.out.println("3");

        System.out.println(a.getId());
        System.out.println("4");

        sa.modifier(a);

        showart();

        System.out.println(a.getId());
        System.out.println("5");

    }

    @FXML
    private void suppar(ActionEvent event) {
        article a = lab.getSelectionModel().getSelectedItem();
        serviceArticle sa = new serviceArticle();
        sa.supprimer(a);
        serviceArticle sop = new serviceArticle();
        ObservableList<article> articles = sop.showArticle("10");
        coltitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        coltheme.setCellValueFactory(new PropertyValueFactory<>("theme"));
        colauteur.setCellValueFactory(new PropertyValueFactory<>("nom_auteur"));
        colarticle.setCellValueFactory(new PropertyValueFactory<>("article"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));

        lab.setItems(articles);
    }

    @FXML
    private void triert(ActionEvent event) {
        serviceArticle sop = new serviceArticle();
        
        ObservableList<article> articles = sop.trier(id_user);

        coltitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        coltheme.setCellValueFactory(new PropertyValueFactory<>("theme"));
        colauteur.setCellValueFactory(new PropertyValueFactory<>("nom_auteur"));
        colarticle.setCellValueFactory(new PropertyValueFactory<>("article"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));

        lab.setItems(articles);
    }

    @FXML
    private void rech(ActionEvent event) {
        serviceArticle sop = new serviceArticle();
        ObservableList<article> articles = sop.recherche(recherche.getText());
        coltitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        coltheme.setCellValueFactory(new PropertyValueFactory<>("theme"));
        colauteur.setCellValueFactory(new PropertyValueFactory<>("nom_auteur"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colarticle.setCellValueFactory(new PropertyValueFactory<>("article"));

        lab.setItems(articles);

        /*   public String  file(ActionEvent event) {
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
    }
    private HostServices hostServices;

    public HostServices getHostServices() {
        return hostServices;
    }

    public void setHostServices(HostServices hostServices) {
        this.hostServices = hostServices;
    }

    @FXML
    private void review(ActionEvent event) {

        article a = lab.getSelectionModel().getSelectedItem();
        System.out.println(a.getId());
        String k;
        k = a.getArticle();
        System.out.println(k);
        try {
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + "C:\\Users\\akram\\Desktop\\pi\\" + k);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "check yor file");
        }
    }
}
