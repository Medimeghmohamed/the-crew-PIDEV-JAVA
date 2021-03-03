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
    private String idClientSuiv;
    private String idObjectifSuiv;
    private String dateSuiv;

    public Suivi() {
    }

    public Suivi(String idSuiv, int valeurSuiv, String idClientSuiv, String idObjectifSuiv, String dateSuiv) {
        this.idSuiv = idSuiv;
        this.valeurSuiv = valeurSuiv;
        this.idClientSuiv = idClientSuiv;
        this.idObjectifSuiv = idObjectifSuiv;
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

    public String getIdClientSuiv() {
        return idClientSuiv;
    }

    public void setIdClientSuiv(String idClientSuiv) {
        this.idClientSuiv = idClientSuiv;
    }

    public String getIdObjectifSuiv() {
        return idObjectifSuiv;
    }

    public void setIdObjectifSuiv(String idObjectifSuiv) {
        this.idObjectifSuiv = idObjectifSuiv;
    }

    public String getDateSuiv() {
        return dateSuiv;
    }

    public void setDateSuiv(String dateSuiv) {
        this.dateSuiv = dateSuiv;
    }

    @Override
    public String toString() {
        return "Suivi{" + "idSuiv=" + idSuiv + ", valeurSuiv=" + valeurSuiv + ", idClientSuiv=" + idClientSuiv + ", idObjectifSuiv=" + idObjectifSuiv + ", dateSuiv=" + dateSuiv + '}';
    }
    
    
    
}
