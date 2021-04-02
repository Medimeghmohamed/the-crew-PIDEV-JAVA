/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aura.Article_Commentaire;

import Aura.Aura;
import Aura.SideBar.AdminMainController;
import Aura.SideBar.CoachMainController;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import Service.PDFManager;
import Entities.article;
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
import Service.PDFManager;
import Service.serviceArticle;
import Service.serviceCommentaire;
import Service.PDFManager;
import Service.serviceCommentaire;
import java.nio.file.CopyOption;
import java.nio.file.StandardCopyOption;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

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
    private TableColumn<article, Integer> colapp;
    File[] filesdoc = Paths.get("src\\pi\\").toFile().listFiles();
    @FXML
    private Button trier;
    /**
     * Initializes the controller class.
     */
    public String id_user = "";
    @FXML
    private Button articles_button;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        serviceArticle sop = new serviceArticle();
        ObservableList<article> articles = sop.showArticleAdmin();
        coltitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        coltheme.setCellValueFactory(new PropertyValueFactory<>("theme"));
        colauteur.setCellValueFactory(new PropertyValueFactory<>("nom_auteur"));
        colarticle.setCellValueFactory(new PropertyValueFactory<>("article"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colapp.setCellValueFactory(new PropertyValueFactory<>("approuver"));

        lab.setItems(articles);

    }

    public void initializeFxml(String id) {
    }

    @FXML
    private void approuverarticle(ActionEvent event) throws IOException {

        article a = lab.getSelectionModel().getSelectedItem();
        System.out.println(a.getId());
        String k;
        k = a.getArticle();
        Path from = Paths.get("src\\pi\\" + k);
        Path to = Paths.get("src\\pi1\\" + k);

        CopyOption[] options = new CopyOption[]{
            StandardCopyOption.REPLACE_EXISTING,
            StandardCopyOption.COPY_ATTRIBUTES
        };
        Files.copy(from, to, options);

        System.out.println(a.getArticle());
        serviceArticle sa = new serviceArticle();
        sa.approuver(a);

        serviceArticle sop = new serviceArticle();
        ObservableList<article> articles = sop.showArticleAdmin();
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
        article a = lab.getSelectionModel().getSelectedItem();
        serviceArticle sa = new serviceArticle();
        sa.supprimerad(a);
        serviceArticle sop = new serviceArticle();
        ObservableList<article> articles = sop.showArticleAdmin();
        coltitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        coltheme.setCellValueFactory(new PropertyValueFactory<>("theme"));
        colauteur.setCellValueFactory(new PropertyValueFactory<>("nom_auteur"));
        colarticle.setCellValueFactory(new PropertyValueFactory<>("article"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colapp.setCellValueFactory(new PropertyValueFactory<>("approuver"));

        lab.setItems(articles);

    }

    @FXML
    private void review(ActionEvent event) {

        article a = lab.getSelectionModel().getSelectedItem();
        System.out.println(a.getId());
        String k;
        k = a.getArticle();
        System.out.println(k);
        try {
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + "src\\pi\\" + k);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "check yor file");
        }
    }

    @FXML
    private void trier(ActionEvent event) {
        serviceArticle sop = new serviceArticle();
        ObservableList<article> articles = sop.trierc();

        coltitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        coltheme.setCellValueFactory(new PropertyValueFactory<>("theme"));
        colauteur.setCellValueFactory(new PropertyValueFactory<>("nom_auteur"));
        colarticle.setCellValueFactory(new PropertyValueFactory<>("article"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colapp.setCellValueFactory(new PropertyValueFactory<>("approuver"));
        lab.setItems(articles);
    }

    @FXML
    private void articles_button(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/SideBar/AdminMain.fxml"));

        Parent root = loader.load();

        AdminMainController HomeScene = loader.getController();
        HomeScene.Voir_articles_button(id_user);
        HomeScene.id_user = id_user;
        HomeScene.show_data(id_user);
        String css = Aura.class.getResource("Style.css").toExternalForm();

        root.getStylesheets().add(css);

        Stage window = (Stage) articles_button.getScene().getWindow();

        window.setScene(new Scene(root, 1182, 718));

    }

}
