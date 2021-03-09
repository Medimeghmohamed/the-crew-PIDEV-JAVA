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
public class Suivi {

    private String idSuiv;
    private int valeurSuiv;
    private Client client;
    private Objectif objectif;
    private String dateSuiv;

    public Suivi() {
    }

    public Suivi(String idSuiv, int valeurSuiv, Client client, Objectif objectif, String dateSuiv) {
        this.idSuiv = idSuiv;
        this.valeurSuiv = valeurSuiv;
        this.client = client;
        this.objectif = objectif;
        this.dateSuiv = dateSuiv;
    }

    public String getIdSuiv() {
        return idSuiv;
    }

    public void setIdSuiv(String idSuiv) {
        this.idSuiv = idSuiv;
    }

    public int getValeurSuiv() {
        return valeurSuiv;
    }

    public void setValeurSuiv(int valeurSuiv) {
        this.valeurSuiv = valeurSuiv;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Objectif getObjectif() {
        return objectif;
    }

    public void setObjectif(Objectif objectif) {
        this.objectif = objectif;
    }



    public String getDateSuiv() {
        return dateSuiv;
    }

    public void setDateSuiv(String dateSuiv) {
        this.dateSuiv = dateSuiv;
    }

    @Override
    public String toString() {
        return "Suivi{" + "idSuiv=" + idSuiv + ", valeurSuiv=" + valeurSuiv + ", client=" + client + ", Objectif=" + objectif + ", dateSuiv=" + dateSuiv + '}';
    }

}
