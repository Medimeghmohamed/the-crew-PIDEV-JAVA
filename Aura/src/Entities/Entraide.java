/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;

/**
 *
 * @author Nour Dekhil
 */
public class Entraide {
    private int id;
    private String id_client;
    private String categorie;
    private String question;
    private Date date;
            
            
    public Entraide() {
    }

    public Entraide(int id, String id_client, String categorie, String question, Date date) {
        this.id = id;
        this.id_client = id_client;
        this.categorie = categorie;
        this.question = question;
        this.date = date;
        
    }

    public int getId() {
        return id;
    }

    public String getId_client() {
        return id_client;
    }

    public String getCategorie() {
        return categorie;
    }

    public String getQuestion() {
        return question;
    }

    public Date getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_client(String id_client) {
        this.id_client = id_client;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Entraide{" + "id=" + id + ", id_client=" + id_client + ", categorie=" + categorie + ", question=" + question + ", date=" + date + '}';
    }
    
    
    
    
    
    
    
    

    
    
    
    
    
    
    
    
}
