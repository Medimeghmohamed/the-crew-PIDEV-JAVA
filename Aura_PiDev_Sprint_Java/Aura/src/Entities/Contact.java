 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;
import javafx.scene.control.CheckBox;

/**
 *
 * @author Nour Dekhil
 */
public class Contact {
    private int id;
    private String type;
    private String id_user;
    private String champ;
    private Date date;
    private String etat;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Contact() {
    }

    public Contact(int id, String type, String id_user, String champ, Date date,  String etat, String email) {
        this.id = id;
        this.type = type;
        this.id_user= id_user;
        this.champ = champ;
        this.date = date;
        this.etat=etat;
        this.email=email;
    }

   
    
    
    
    
    

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getId_user() {
        return id_user;
    }

  

    public String getChamp() {
        return champ;
    }

    public Date getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    

    public void setChamp(String champ) {
        this.champ = champ;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Contact{" + "id=" + id + ", type=" + type + ", id_user=" + id_user + ", champ=" + champ + ", date=" + date + ", etat=" + etat + '}';
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    

    

    
    
    
    
    
    
    
    
    
    
    
}
