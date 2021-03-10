/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Medimegh
 */
public class Therapie {
    int id,nombremax,nombre_parti;
    String sujet,date,lieu;
Coach idcoach;
    public int getNombre_parti() {
        return nombre_parti;
    }

    public void setNombre_parti(int nombre_parti) {
        this.nombre_parti = nombre_parti;
    }

    public Therapie() {
    }

    public Therapie(int id, int nombremax, int nombre_parti, String sujet, String date, String lieu, Coach idcoach) {
        this.id = id;
        this.nombremax = nombremax;
        this.nombre_parti = nombre_parti;
        this.sujet = sujet;
        this.date = date;
        this.lieu = lieu;
        this.idcoach = idcoach;
    }

  

    @Override
    public String toString() {
        return "Therapie{" + "id=" + id + ", nombremax=" + nombremax + ", nombre_parti=" + nombre_parti + ", sujet=" + sujet + ", date=" + date + ", lieu=" + lieu + ", idcoach=" + idcoach + '}';
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNombremax() {
        return nombremax;
    }

    public void setNombremax(int nombremax) {
        this.nombremax = nombremax;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public Coach getIdcoach() {
        return idcoach;
    }

    public void setIdcoach(Coach idcoach) {
        this.idcoach = idcoach;
    }

 
    
}
