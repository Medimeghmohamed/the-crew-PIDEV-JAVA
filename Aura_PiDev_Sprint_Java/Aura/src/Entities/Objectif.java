/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Chirine
 */
public class Objectif {

    private int id;
    private String description;
    private int reponse;
    private String date;
    private int duree;
    private Client cli;
    private String icone;

    public Objectif() {
    }

    public Objectif(int id, String description, int reponse, String date, int duree, Client cli, String icone) {
        this.id = id;
        this.description = description;
        this.reponse = reponse;
        this.date = date;
        this.duree = duree;
        this.cli = cli;
        this.icone=icone;
    }

    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getReponse() {
        return reponse;
    }

    public void setReponse(int reponse) {
        this.reponse = reponse;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public Client getCli() {
        return cli;
    }

    public void setCli(Client cli) {
        this.cli = cli;
    }

//    public String getIdCli() {
//        return idCli;
//    }
//
//    public void setIdCli(String idCli) {
//        this.idCli = idCli;
//    }
    @Override
    public String toString() {
        return description;
        // return "Objectif{" + "id=" + id + ", description=" + description + ", reponse=" + reponse + ", date=" + date + ", duree=" + duree +", Cli=" + cli + '}';
    }

}
