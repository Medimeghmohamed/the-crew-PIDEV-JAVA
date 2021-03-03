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
    int id,nombremax;
    String sujet,date,lieu,idcoach;

    public Therapie() {
    }

    public Therapie(int id, int nombremax, String sujet, String date, String lieu, String idcoach) {
        this.id = id;
        this.nombremax = nombremax;
        this.sujet = sujet;
        this.date = date;
        this.lieu = lieu;
        this.idcoach = idcoach;
    }

    @Override
    public String toString() {
        return "Therapie{" + "id=" + id + ", nombremax=" + nombremax + ", sujet=" + sujet + ", date=" + date + ", lieu=" + lieu + ", idcoach=" + idcoach + '}';
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

    public String getIdcoach() {
        return idcoach;
    }

    public void setIdcoach(String idcoach) {
        this.idcoach = idcoach;
    }
    
}
