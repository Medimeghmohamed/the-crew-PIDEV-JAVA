/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author akram
 */
public class commentaire {
    int idc ;
    private String id_user;
   
    private int id_article;
    private String date ;
    private String commentaire ;

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setIdc(int idc) {
        this.idc = idc;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

  

    public void setId_article(int id_article) {
        this.id_article = id_article;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getIdc() {
        return idc;
    }

    public String getId_user() {
        return id_user;
    }

 
    public int getId_article() {
        return id_article;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "commentaire{" + "idc=" + idc + ", id_user=" + id_user + ", id_article=" + id_article + ", date=" + date + ", commentaire=" + commentaire + '}';
    }

   
            
}
