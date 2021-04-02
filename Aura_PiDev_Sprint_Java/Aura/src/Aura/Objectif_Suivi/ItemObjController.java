/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aura.Objectif_Suivi;

import Aura.Objectif_Suivi.ObjectifsController;
import Entities.Objectif;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Chirine
 */
public class ItemObjController implements Initializable {

    @FXML
    private Label lb_desc;
    @FXML
    private Label lb_rep;
    
    private Objectif objectif;
    private ObjectifListenner listenner;
    @FXML
    private Label lb_date;
    @FXML
    private Label lb_duree;
    @FXML
    private ImageView icone;
    Image image;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {      
    } 
    @FXML
    private void click(MouseEvent actionEvent){
        ObjectifsController oc= new ObjectifsController();
        oc.obj=objectif;
        System.out.println("oc"+ oc.obj);
        System.out.println("objectif" +objectif);
        listenner.onClickListener(objectif);
    }
    
    public void setData(Objectif objectif, ObjectifListenner ol){
        this.objectif=objectif;
        listenner=ol;
        lb_desc.setWrapText(true);
        lb_desc.setText(objectif.getDescription());
        lb_rep.setText(""+objectif.getReponse());
        lb_date.setText(objectif.getDate());
        lb_duree.setText(""+objectif.getDuree());
        image= new Image(getClass().getResourceAsStream("/Images/Objectifs/"+objectif.getIcone()));
               // image= new Image(getClass().getResourceAsStream("/Images/Objectifs/defaut.png"));
        icone.setImage(image);
    }
}
