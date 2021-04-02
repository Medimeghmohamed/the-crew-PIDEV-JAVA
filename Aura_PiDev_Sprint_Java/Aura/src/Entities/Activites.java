/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;



/**
 *
 * @author Medimegh
 */
public class Activites {
   private int id;
   private Coach idcoach;

   private String duree;
   private String date;
   private int nombremax;
   private String type;
   private String description;
   private String lieu;
    private int nombre_parti;

    

    public int getNombre_parti() {
        return nombre_parti;
    }

    public void setNombre_parti(int nombre_parti) {
        this.nombre_parti = nombre_parti;
    }
   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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

    public Coach getIdcoach() {
        return idcoach;
    }

    public void setIdcoach(Coach idcoach) {
        this.idcoach = idcoach;
    }

    public Activites(int id, Coach idcoach, String duree, String date, int nombremax, String type, String description, String lieu, int nombre_parti) {
        this.id = id;
        this.idcoach = idcoach;
        this.duree = duree;
        this.date = date;
        this.nombremax = nombremax;
        this.type = type;
        this.description = description;
        this.lieu = lieu;
        this.nombre_parti = nombre_parti;
    }

   

    @Override
    public String toString() {
        return "Activites{" + "id=" + id + ", idcoach=" + idcoach + ", duree=" + duree + ", date=" + date + ", nombremax=" + nombremax + ", type=" + type + ", description=" + description + ", lieu=" + lieu + ", nombre_parti=" + nombre_parti + '}';
    }

  
    
}
