/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aura.therapyActivity;

import Entities.Therapie;
import Service.serviceTherapie;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Medimegh
 */
public class GridtherapyController implements Initializable {

    @FXML
    private GridPane thGrid;
   public String id_user = "";
    public int pageCount,currentPage;
    private serviceTherapie st = new serviceTherapie();
    private int gridSize,columnCount;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
public void initializeFxml(String id) {
     System.out.println(id_user);
      setData();
    }  
       public void setData(){
       
        gridSize=thGrid.getRowConstraints().size()*thGrid.getColumnConstraints().size();
        columnCount=thGrid.getColumnConstraints().size()-1;
                //currentPage=index;

        try {
            int y = 0;
            int x = 0;
           List<Therapie> activity;
             activity = st.showTherapies();
            //pagination
            pageCount=activity.size()/gridSize;
            if(activity.size()%gridSize>0){
                pageCount++;
            }
            int a,b;
            a=currentPage*gridSize; 
            if(currentPage==(pageCount-1))
                b=activity.size();
            else
                b=a+gridSize;
            for (int i = a; i < b; i++) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/Aura/therapyActivity/listtherapy.fxml"));
                AnchorPane pane = loader.load();
                ListtherapyController c = loader.getController();
                System.out.println("a");
                c.setDatath(activity.get(i),id_user);
                if (x > columnCount) {
                    y++;
                    x = 0;
                }
                thGrid.add(pane, x, y);
                x++;
            }
        } catch (IOException ex) {
            System.out.println( ex.getMessage());
        }
    }
    
}
