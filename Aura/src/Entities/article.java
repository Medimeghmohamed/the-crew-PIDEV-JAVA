/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author akram
 */
public class article {

    /*public static void setText(String toString) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
    
   private int id;
   private String titre;
   private String theme;
   private String nom_auteur;
   private String date;
   private String article ;
   private String id_user;
   private int approuver ;

    public article(int id, String titre, String theme, String nom_auteur, String date, String article, String id_user, int approuver) {
        this.id = id;
        this.titre = titre;
        this.theme = theme;
        this.nom_auteur = nom_auteur;
        this.date = date;
        this.article = article;
        this.id_user = id_user;
        this.approuver = approuver;
    }

    public article() {
    }

    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public String getTheme() {
        return theme;
    }

    public String getNom_auteur() {
        return nom_auteur;
    }

    public String getDate() {
        return date;
    }

    public String getArticle() {
        return article;
    }

    public String getId_user() {
        return id_user;
    }

    public int getApprouver() {
        return approuver;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public void setNom_auteur(String nom_auteur) {
        this.nom_auteur = nom_auteur;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public void setApprouver(int approuver) {
        this.approuver = approuver;
    }

    @Override
    public String toString() {
        return "article{" + "id=" + id + ", titre=" + titre + ", theme=" + theme + ", nom_auteur=" + nom_auteur + ", date=" + date + ", article=" + article + ", id_user=" + id_user + ", approuver=" + approuver + '}';
    }

   
}