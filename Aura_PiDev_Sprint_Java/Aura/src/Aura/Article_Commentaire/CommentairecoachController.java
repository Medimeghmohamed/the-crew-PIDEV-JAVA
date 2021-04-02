/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aura.Article_Commentaire;

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
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.HostServices;
import javafx.collections.ObservableList;
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
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author akram
 */
public class CommentairecoachController implements Initializable {

    private TableColumn<article, String> colidc;
    private TableColumn<article, String> colidcl;
    private Label labcom;
    @FXML
    private Label espart;
    @FXML
    private TextField espcom;
    @FXML
    private Pagination pagination;

    File[] filesdoc = Paths.get("src\\pi1\\").toFile().listFiles();
    @FXML
    private TableView<commentaire> lab1;
    @FXML
    private TableColumn<commentaire, String> labe;
    @FXML
    private Button trier;
    public String id_user = "";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        serviceCommentaire sc = new serviceCommentaire();
       ObservableList <commentaire> commentaires= sc.show();
        labe.setCellValueFactory(new PropertyValueFactory<>("commentaire"));

        lab1.setItems(commentaires);

        /*   Notifications notifictaionBuilder = Notifications.create()
                    .title("Aura")
                    .text("It's time to seek a new Therapy")
                    .graphic(null)
                    .position(Pos.BOTTOM_RIGHT)
                    .onAction((ActionEvent event1) -> {
                        System.out.println("Clicked on notifictaion");
            });
            notifictaionBuilder.darkStyle();
          notifictaionBuilder.show();
         */
        pagination.setPageFactory(new Callback< Integer, Node>() {
            @Override
            public Node call(Integer pageIndex) {

                try {
                    return createPage(pageIndex);

                } catch (IOException ex) {
                    Logger.getLogger(CommentairecoachController.class.getName()).log(Level.SEVERE, null, ex);
                }
                return null;

            }

        });
        //  List<String> list_commentaire=Arrays.asList(sc.showCommentaire_combobox());

    }

    public void initializeFxml(String id) {

    }

    public VBox createPage(int index) throws IOException {

        PDFManager pdfManager = new PDFManager();
        serviceCommentaire j = new serviceCommentaire();

        File f = filesdoc[index];
        String J = f.getAbsolutePath();

        pdfManager.setFilePath(J);
        String text = pdfManager.toText();
        espart.setText(pdfManager.toText());

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
            Path to = Paths.get("src\\pi\\" + k);

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
    private void ajoutcom(ActionEvent event) {
        serviceCommentaire sc = new serviceCommentaire();
        commentaire a = new commentaire();
        if (espcom.getText().equals("")) {

            System.out.println("noooo");
            espcom.setStyle("-fx-border-color: red; -fx-borfrt-width: 2px;");
            new animatefx.animation.Shake(espcom).play();
            

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Champ manquant");
            alert.setHeaderText(null);
            alert.setContentText("IL FAUT REMPLIR TOUS LES CHAMPS");
            alert.showAndWait();
        } else {
        a.setCommentaire(espcom.getText());
        sc.ajouterc(a,id_user);
        

        
       ObservableList <commentaire> commentaires= sc.show();
        labe.setCellValueFactory(new PropertyValueFactory<>("commentaire"));

        lab1.setItems(commentaires);

    }}

    @FXML
    private void suppcom(ActionEvent event) {

        commentaire a = lab1.getSelectionModel().getSelectedItem();
        serviceCommentaire sa = new serviceCommentaire();
        sa.supprimerc(a,id_user);

       ObservableList <commentaire> commentaires= sa.show();
        labe.setCellValueFactory(new PropertyValueFactory<>("commentaire"));

        lab1.setItems(commentaires);

    }

    @FXML
    private void modcom(ActionEvent event) {
        serviceCommentaire sp = new serviceCommentaire();
        commentaire s = new commentaire();
        s.setCommentaire(espcom.getText());
        commentaire a = lab1.getSelectionModel().getSelectedItem();
        System.out.println(a.getId_user());
        s.setId_user(a.getId_user());
        System.out.println(s.getId_user());
        sp.modifier(s,id_user);
       ObservableList <commentaire> commentaires= sp.show();
        labe.setCellValueFactory(new PropertyValueFactory<>("commentaire"));

        lab1.setItems(commentaires);
    }

    @FXML
    private void trier(ActionEvent event) {
        serviceCommentaire sa = new serviceCommentaire();
        sa.trier();

       ObservableList <commentaire> commentaires= sa.trier();

        labe.setCellValueFactory(new PropertyValueFactory<>("commentaire"));

        lab1.setItems(commentaires);
    }

}
