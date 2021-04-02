/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aura.Objectif_Suivi;

import Aura.Aura;
import Aura.SideBar.ClientMainController;
import Entities.Objectif;
import Service.SendMail;
import Service.ServiceClient;
import Service.ServiceObjectif;
import Service.ServicePdf;
import Service.ServiceSuivi;
import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultKeyedValuesDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 * FXML Controller class
 *
 * @author Chirine
 */
public class ObjectifsController implements Initializable {

    @FXML
    private TextField tfrechObj;
    @FXML
    private ComboBox<String> cbtriObj;
    @FXML
    private Button btnAjouterObj;
    @FXML
    private Button btnModifierObj;
    @FXML
    private Button btnSupprimerObj;
    @FXML
    private Button btnSuiviObj1;
    @FXML
    private ScrollPane scroll_obj;
    @FXML
    private GridPane grid_obj;
    private ObjectifListenner listenner;

    public String id_user = "";
    public Objectif obj = null;
    @FXML
    private TextField hidden;
    @FXML
    private HBox hbox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        hidden.setText("0");
        hidden.setStyle("-fx-text-fill: transparent;");
        ObservableList<String> listTriObj = FXCollections.observableArrayList("par date", "par description");
        cbtriObj.setItems(listTriObj);

    }

    private void setChosenObjectif(Objectif objectif) {
        //select row 
        hidden.setText("" + objectif.getId());

    }

    public void AfficherIcons(String id, List<Objectif> objectifs) {
        grid_obj.getChildren().clear();
        System.out.println(id_user);
        ServiceObjectif so = new ServiceObjectif();
        if (objectifs.size() > 0) {
            listenner = new ObjectifListenner() {
                @Override
                public void onClickListener(Objectif objectif) {
                    setChosenObjectif(objectif);
                }
            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < objectifs.size(); i++) {
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("/Aura/Objectif_Suivi/ItemObj.fxml"));
                AnchorPane anchorPane = fxmlloader.load();
                ItemObjController itmc = fxmlloader.getController();
                itmc.setData(objectifs.get(i), listenner);
                if (column == 2) {
                    column = 0;
                    row++;
                }
                grid_obj.add(anchorPane, column++, row);
                grid_obj.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid_obj.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid_obj.setMaxWidth(Region.USE_PREF_SIZE);

                grid_obj.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid_obj.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid_obj.setMaxHeight(Region.USE_PREF_SIZE);

                //grid_obj.setStyle("-fx-background-image: url(\"/Images/back.png\");");
                grid_obj.setStyle("-fx-background-color: rgba(220,245,198,0.5)");

                GridPane.setMargin(anchorPane, new Insets(10));

            }
        } catch (IOException ex) {
            Logger.getLogger(ObjectifsController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void initializeFxml(String id) {
        grid_obj.getChildren().clear();
        List<Objectif> objectifs = new ArrayList<>();
        ServiceObjectif so = new ServiceObjectif();
        ObservableList<Objectif> list = so.afficherObjectifs(id);
        mailObjectif(list);
        objectifs = so.afficherObjectifsItems(id);
        if (objectifs.size() > 0) {
            //setChosenObjectif(objectifs.get(0));
            listenner = new ObjectifListenner() {
                @Override
                public void onClickListener(Objectif objectif) {
                    setChosenObjectif(objectif);
                }
            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < objectifs.size(); i++) {
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("/Aura/Objectif_Suivi/ItemObj.fxml"));
                AnchorPane anchorPane = fxmlloader.load();
                ItemObjController itmc = fxmlloader.getController();
                itmc.setData(objectifs.get(i), listenner);
                if (column == 2) {
                    column = 0;
                    row++;
                }
                grid_obj.add(anchorPane, column++, row);
                grid_obj.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid_obj.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid_obj.setMaxWidth(Region.USE_PREF_SIZE);

                grid_obj.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid_obj.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid_obj.setMaxHeight(Region.USE_PREF_SIZE);
                //grid_obj.setStyle("-fx-background-image: url(\"/Images/back.png\");");
                grid_obj.setStyle("-fx-background-color: rgba(220,245,198,0.5)");

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException ex) {
            Logger.getLogger(ObjectifsController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @FXML
    private void rechercherObjectif(KeyEvent event) {
        System.out.println("recherche");
        ServiceObjectif sop = new ServiceObjectif();
        List<Objectif> objectifs = sop.rechercherObjectif(tfrechObj.getText());
        AfficherIcons(id_user, objectifs);
//        col_desc.setCellValueFactory(new PropertyValueFactory<>("description"));
//        col_deb.setCellValueFactory(new PropertyValueFactory<>("date"));
//        col_duree.setCellValueFactory(new PropertyValueFactory<>("duree"));
//        col_rep.setCellValueFactory(new PropertyValueFactory<>("reponse"));
//        tv_obj.setItems(objectifs);
    }

    @FXML
    private void selectTriObj(ActionEvent event) {
        ServiceObjectif sop = new ServiceObjectif();
        List<Objectif> objectifs = new ArrayList<>();
        if (cbtriObj.getValue().equals("par ID")) {
            objectifs = sop.trierObjectifparId();
            AfficherIcons(id_user, objectifs);
        } else if (cbtriObj.getValue().equals("par date")) {
            objectifs = sop.trierObjectifparDate();
            AfficherIcons(id_user, objectifs);
        } else {
            objectifs = sop.trierObjectifparDesc();
            AfficherIcons(id_user, objectifs);
        }
    }

    @FXML
    private void ajouterObjectif(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/SideBar/ClientMain.fxml"));
        Parent root = loader.load();
        ClientMainController HomeScene = loader.getController();
        HomeScene.AjouterObjectif(id_user);
        HomeScene.id_user = id_user;
        HomeScene.show_data(id_user);
        String css = Aura.class.getResource("Style.css").toExternalForm();
        root.getStylesheets().add(css);
        Stage window = (Stage) btnAjouterObj.getScene().getWindow();
        window.setScene(new Scene(root, 1182, 718));
    }

    @FXML
    private void modifierObjectif(ActionEvent event) throws IOException {
        ServiceObjectif so = new ServiceObjectif();
        Objectif obj = new Objectif();

        if (hidden.getText().equals("0")) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Attention");
            a.setContentText("Vous devez selectionner un element du tableau");
            a.setHeaderText(null);
            a.showAndWait();
        } else {
            obj = so.load_data_modify(Integer.parseInt(hidden.getText()));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/SideBar/ClientMain.fxml"));
            Parent root = loader.load();
            ClientMainController HomeScene = loader.getController();
            HomeScene.ModifierObjectif(id_user, obj.getId(),
                    obj.getDescription(),
                    obj.getDate(),
                    obj.getDuree(),
                    obj.getReponse());
            HomeScene.id_user = id_user;
            HomeScene.show_data(id_user);
            String css = Aura.class.getResource("Style.css").toExternalForm();
            root.getStylesheets().add(css);
            Stage window = (Stage) btnModifierObj.getScene().getWindow();
            window.setScene(new Scene(root, 1182, 718));
        }
        hidden.setText("0");
    }

    @FXML
    private void supprimerObjectif(ActionEvent event) {
        if (hidden.getText().equals("0")) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Attention");
            a.setContentText("Vous devez selectionner un element du tableau");
            a.setHeaderText(null);
            a.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation suppression");
            alert.setHeaderText("Etes vous sur de supprimer cet objectif?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                System.out.println("okay");
                ServiceObjectif sp = new ServiceObjectif();
                List<Objectif> objectifs = new ArrayList<>();
                sp.supprimerObjectif(Integer.parseInt(hidden.getText()));
                System.out.println(id_user);
                objectifs = sp.afficherObjectifsItems(id_user);
                AfficherIcons(id_user, objectifs);
                new animatefx.animation.Flash(btnSupprimerObj).play();
            } else {
                System.out.println("Cancel");
            }
        }
    }

    @FXML
    private void suiviObjectif(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aura/SideBar/ClientMain.fxml"));
        Parent root = loader.load();
        ClientMainController HomeScene = loader.getController();
        HomeScene.SuiviObjectif(id_user, "");
        HomeScene.id_user = id_user;
        HomeScene.show_data(id_user);
        String css = Aura.class.getResource("Style.css").toExternalForm();
        root.getStylesheets().add(css);
        Stage window = (Stage) btnSuiviObj1.getScene().getWindow();
        window.setScene(new Scene(root, 1182, 718));
    }

    @FXML
    private void btn_bilan(ActionEvent event) {
        ServiceObjectif sp = new ServiceObjectif();
        ServiceSuivi ss = new ServiceSuivi();
        Objectif obj = new Objectif();
        if (hidden.getText().equals("0")) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Attention");
            a.setContentText("Vous devez selectionner un element du tableau");
            a.setHeaderText(null);
            a.showAndWait();
        } else {
            obj = sp.load_data_modify(Integer.parseInt(hidden.getText()));
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Bilan");
            alert.setHeaderText("Veuillez choisir une date ");
            ComboBox<String> cb = new ComboBox();
            ObservableList<String> list = ss.getDateBilan(obj.getId());
            cb.setItems(list);
            alert.setGraphic(cb);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {

                DefaultPieDataset pieDataset = new DefaultKeyedValuesDataset();
                int id = obj.getId();
                pieDataset.setValue("Pas fait", (100 - (ss.getValeur(id, cb.getValue()) * 100) / sp.getRepObj(id)));
                pieDataset.setValue("Fait", (ss.getValeur(id, cb.getValue()) * 100) / sp.getRepObj(id));

                JFreeChart chart = ChartFactory.createPieChart("Bilan de l'objectif: " + obj.getDescription(), pieDataset);
                PiePlot P = (PiePlot) chart.getPlot();
                ChartFrame frame = new ChartFrame("Bilan objectif", chart);
                frame.setVisible(true);
                frame.setSize(450, 500);
            } else {
                System.out.println("Cancel");
            }
        }

    }

    public void mailObjectif(ObservableList<Objectif> list) {
        ServiceObjectif so = new ServiceObjectif();
        System.out.println(list);
        list.forEach((e) -> {
            System.out.println("aaaaaaa");
            //comparer date aujourd'hui et date debut de l'objectif
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            float res = 1;
            try {
                //date du jour
                Date dateAuj = sdf.parse(new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime()));
                System.out.println("date auj" + dateAuj);
                //date du debut
                Date dateFin = sdf.parse(so.getDateDebutObj(so.getIdObjparDesc(e.getDescription()), e.getCli().getId()));
                System.out.println("date fin " + dateFin);
                //datedebut + durée = date fin
                dateFin.setTime(dateFin.getTime() + (e.getDuree() * 24 * 3600 * 1000));
                long diff = dateAuj.getTime() - dateFin.getTime();
                //si res = 0.0 alors les deux dates sont égales, donc fin de l'objectif
                res = (diff / (1000 * 60 * 60 * 24));
                System.out.println("res " + res);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            System.out.println(res == 0.0);
            System.out.println(so.retrunmailCkeckedObj(e.getId(), Integer.parseInt(e.getCli().getId())) == 1);
            if ((res == 0.0) && (so.retrunmailCkeckedObj(e.getId(), Integer.parseInt(e.getCli().getId())) == 1)) {
                System.out.println("envoi du mail");
                SendMail sm = new SendMail();
                ServiceClient sc = new ServiceClient();
                sm.envoyerMail(sc.load_data_modify(id_user).getEmail(), "Bravo Mr/Mme" + sc.load_data_modify(id_user).getNom() + " " + sc.load_data_modify(id_user).getPrenom(), "BRAVO! vous avez terminé l'objectif : " + e.getDescription() + ". \n Nous sommes fiers de vous. \n Vous pouvez ajouter d'autres objectifs.\n RQ: cet objectif sera supprimé de votre liste d'objectifs. \n \n L'équipe de Aura.");
                System.out.println("suppression de l'objectif deja terminé: " + e.getDescription());
                ServiceObjectif sp = new ServiceObjectif();
                ServiceSuivi ss = new ServiceSuivi();
                ss.getSuiviparid(e.getId()).forEach((s) -> {
                    ss.supprimerObjectif(s.getIdSuiv());
                });
                sp.supprimerObjectif(e.getId());
            }
        });
    }

    @FXML
    private void PDF(ActionEvent event) throws FileNotFoundException, DocumentException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de création du PDF");
        alert.setHeaderText("Etes vous sur de vouloir créer un PDF qui contient la liste de vos objectifs?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            ServicePdf sp = new ServicePdf();
            sp.liste_objectifs(id_user);
        }
    }

}
