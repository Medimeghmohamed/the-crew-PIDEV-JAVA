 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Nour Dekhil
 */
public class Contact {
    private int id;
    private String type;
    private String id_client;
    private String id_coach;
    private String champ;
    private String date;
    private String etat;

    public Contact() {
    }

    public Contact(int id, String type, String id_client, String id_coach, String champ, String date, String etat) {
        this.id = id;
        this.type = type;
        this.id_client = id_client;
        this.id_coach = id_coach;
        this.champ = champ;
        this.date = date;
        this.etat=etat;
    }

   
    
    
    
    
    

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getId_client() {
        return id_client;
    }

    public String getId_coach() {
        return id_coach;
    }

    public String getChamp() {
        return champ;
    }

    public String getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setId_client(String id_client) {
        this.id_client = id_client;
    }

    public void setId_coach(String id_coach) {
        this.id_coach = id_coach;
    }

    public void setChamp(String champ) {
        this.champ = champ;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Contact{" + "id=" + id + ", type=" + type + ", id_client=" + id_client + ", id_coach=" + id_coach + ", champ=" + champ + ", date=" + date + ", etat="+etat+  "}\n";
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    
    
    
    
    
    
    
    
    
    
    
}
