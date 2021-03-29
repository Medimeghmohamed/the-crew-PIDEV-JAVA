/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import service.ServiceParticipationChallenge;

/**
 *
 * @author NOUR
 */
public class reminder {
    
    
        private SimpleStringProperty name;
	private SimpleStringProperty notes;
	private SimpleObjectProperty<LocalDate> date;
	private SimpleObjectProperty<LocalTime> time;
	private SimpleBooleanProperty priority;
	private SimpleBooleanProperty completed;
        
         //public reminder () {};
        
        public reminder (/* SimpleStringProperty nom,SimpleStringProperty notes,SimpleObjectProperty<LocalDate> date,SimpleObjectProperty<LocalTime> time, SimpleBooleanProperty priority,SimpleBooleanProperty completed*/) {
	
		this.name = new SimpleStringProperty();
		this.notes = new SimpleStringProperty("");
		this.date = new SimpleObjectProperty<LocalDate>();
		this.time = new SimpleObjectProperty<LocalTime>();
		this.priority = new SimpleBooleanProperty();
		this.completed = new SimpleBooleanProperty(false);
	}

        
        
    public SimpleStringProperty getName(){
        
        
        
        
        
        return name;
        
    }

    public SimpleStringProperty getNotes() {
        return notes;
    }

    public SimpleObjectProperty<LocalDate> getDate() {
        return date;
    }

    public SimpleObjectProperty<LocalTime> getTime() {
        return time;
    }

    public SimpleBooleanProperty getPriority() {
        return priority;
    }
     public SimpleBooleanProperty getCompleted() {
        return completed;
    }

    public void setName(SimpleStringProperty name) {
        this.name = name;
    }

    public void setNotes(SimpleStringProperty notes) {
        this.notes = notes;
    }

    public void setDate(SimpleObjectProperty<LocalDate> date) {
        this.date = date;
    }

    public void setTime(SimpleObjectProperty<LocalTime> time) {
        this.time = time;
    }

    public void setPriority(SimpleBooleanProperty priority) {
        this.priority = priority;
    }

    public void setCompleted(SimpleBooleanProperty completed) {
        this.completed = completed;
    }

   
    @Override
    public String toString() {
        return "reminder{" + "name=" + name + '}';
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /* private int id;
    private ObservableList<participation_challenge>Oparticipation = FXCollections.observableArrayList();
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
    public void setOparticipation(ObservableList<participation_challenge> Oparticipation) {
    this.Oparticipation = Oparticipation;
    }*/
   

   /* public reminder create() {
        return null;
    }*/

   

    @Override
    public boolean equals(Object obj) {
       if (obj instanceof reminder) {
		
			reminder r = (reminder) obj;
				
			if ((r.getName().equals(name.get())) && (r.getDate().equals(date.get()))) {
					
				return true;
			}
		}
		
		return false;
	}

    
    
    
    
   

    }


    
    
    
    
    
    
      




