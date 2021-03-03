/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author NOUR
 */
public class challenge {

    private int id;
    private String titre;
    private String type;
    private String description;
    private String img;
    private Date date_debut;
    private Date date_fin;
    private int nb_participants;
    private String etat;
    private int niveau;

    public challenge() {
    }

    public challenge(int id, String titre, String type, String description, String img, Date date_debut,Date date_fin, int nb_participants, String etat , int niveau ){
    
    this.id = id;
        this.titre = titre;
        this.description = description;
        this.type = type;
        this.img = img;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.nb_participants = nb_participants;
        this.etat = etat;
        this.niveau = niveau;

    }

    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getImg() {
        return img;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public int getNb_participants() {
        return nb_participants;
    }

    public String getEtat() {
        return etat;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public void setNb_participants(int nb_participants) {
        this.nb_participants = nb_participants;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    @Override
    public String toString() {
        return "challenge{" + "id=" + id + ", titre=" + titre + ", type=" + type + ", description=" + description + ", img=" + img + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", nb_participants=" + nb_participants + ", etat=" + etat + ", niveau=" + niveau + '}';
    }
    
    

}
