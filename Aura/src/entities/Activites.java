/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author Medimegh
 */
public class Activites {
   private int id;
   private String idcoach;
   private String duree;
   private Date date;
   private int nombremax;
   private String type;
   private String description;
   private String lieu;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdcoach() {
        return idcoach;
    }

    public void setIdcoach(String idcoach) {
        this.idcoach = idcoach;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNombremax() {
        return nombremax;
    }

    public void setNombremax(int nombremax) {
        this.nombremax = nombremax;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public Activites() {
    }

    public Activites(int id, String idcoach, String duree, Date date, int nombremax, String type, String description, String lieu) {
        this.id = id;
        this.idcoach = idcoach;
        this.duree = duree;
        this.date = date;
        this.nombremax = nombremax;
        this.type = type;
        this.description = description;
        this.lieu = lieu;
    }

    @Override
    public String toString() {
        return "Activites{" + "id=" + id + ", idcoach=" + idcoach + ", duree=" + duree + ", date=" + date + ", nombremax=" + nombremax + ", type=" + type + ", description=" + description + ", lieu=" + lieu + '}';
    }
    
    
}
