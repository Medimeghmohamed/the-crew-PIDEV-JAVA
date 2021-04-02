/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aura.therapyActivity;

import Aura.*;
import java.io.FileNotFoundException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;


/**
 *
 * @author SeifBS
 */
public class FxmlLoader {
    private Pane view;
    public Pane getPage(String fileName) throws FileNotFoundException
    {
    
    try {
    URL fileUrl=Aura.class.getResource("/Aura/therapyActivity/"+fileName+".fxml");
    if (fileUrl==null)
    {throw new java.io.FileNotFoundException("FXML file can't be found");
    
    
    }
    view=new FXMLLoader().load(fileUrl);
    }
    catch(Exception e)
    {
        System.out.println("No Page"+fileName+"Please Check");
        
    }
    
    return view;
    }
    
}
