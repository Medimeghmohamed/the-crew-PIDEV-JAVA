/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;
import javafx.scene.control.Button;

/**
 *
 * @author Nour Dekhil
 */
public class Entraide {

    private int id;
    private String categorie, id_user,question, email;
    private Date date;
   

    public Entraide() {
    }

    public Entraide(int id, String categorie, String id_user, String question, Date date, String email) {
        this.id = id;
        this.categorie = categorie;
        this.id_user = id_user;
        this.question = question;
        this.date = date;
        this.email=email;
       
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public int getId() {
        return id;
    }

    public String getCategorie() {
        return categorie;
    }

    public String getId_user() {
        return id_user;
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

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Entraide{" + "id=" + id + ", categorie=" + categorie + ", id_user=" + id_user + ", question=" + question + ", date=" + date + '}';
    }
    

}
