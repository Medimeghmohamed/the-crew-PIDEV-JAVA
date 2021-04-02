/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aura.therapyActivity;

import Entities.Activites;
import Service.serviceActivites;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
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
public class GridactController implements Initializable {

    public int pageCount,currentPage;
    private serviceActivites st = new serviceActivites();
    private int gridSize,columnCount;
    public String id_user = "";
    @FXML
    private GridPane actGrid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("first");
        
       
    }
    public void initializeFxml(String id) {
     System.out.println(id_user);
      setData();
    }  
       public void setData(){
       
        gridSize=actGrid.getRowConstraints().size()*actGrid.getColumnConstraints().size();
        columnCount=actGrid.getColumnConstraints().size()-1;
                //currentPage=index;

        try {
            int y = 0;
            int x = 0;
           List<Activites> activity;
             activity = st.showActivite();
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
                loader.setLocation(getClass().getResource("/Aura/therapyActivity/Listact.fxml"));
                AnchorPane pane = loader.load();
                ListactController c = loader.getController();
                System.out.println("a");
                c.setData(activity.get(i),id_user);
                if (x > columnCount) {
                    y++;
                    x = 0;
                }
                actGrid.add(pane, x, y);
                x++;
            }
        } catch (IOException ex) {
            System.out.println( ex.getMessage());
        }
    }

}
