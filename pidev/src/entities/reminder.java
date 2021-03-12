/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import service.ServiceParticipationChallenge;

/**
 *
 * @author NOUR
 */
public class reminder {
    private int id;
    private ObservableList<participation_challenge>Oparticipation=FXCollections.observableArrayList();

    public int getId() {
        return id;
    }

    public ObservableList<participation_challenge> getOparticipation(int id) {
        ServiceParticipationChallenge part=new ServiceParticipationChallenge();
        part.recup_participation(id);
        return Oparticipation;
    }

    public void setId(int id) {
        this.id = id;
    }

    /*public void setOparticipation(ObservableList<participation_challenge> Oparticipation) {
        this.Oparticipation = Oparticipation;
    }*/
    public reminder create(){
        
        
        return null;
        
    }
    
    
    
    
    
    
      
}
