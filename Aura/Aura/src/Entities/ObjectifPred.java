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
public class ObjectifPred {
    private String idP;
    private String descriptionP;
    private int dureeP;
    private String idAdminP;

    public ObjectifPred() {
    }

    public ObjectifPred(String idP, String descriptionP, int dureeP, String idAdminP) {
        this.idP = idP;
        this.descriptionP = descriptionP;
        this.dureeP = dureeP;
        this.idAdminP= idAdminP;
    }

    public String getIdAdminP() {
        return idAdminP;
    }

    public void setIdAdminP(String idAdminP) {
        this.idAdminP = idAdminP;
    }

    public String getIdP() {
        return idP;
    }

    public void setIdP(String idP) {
        this.idP = idP;
    }

    public String getDescriptionP() {
        return descriptionP;
    }

    public void setDescriptionP(String descriptionP) {
        this.descriptionP = descriptionP;
    }


    public int getDureeP() {
        return dureeP;
    }

    public void setDureeP(int dureeP) {
        this.dureeP = dureeP;
    }

    @Override
    public String toString() {
        return "ObjectifPred{" + "idP=" + idP + ", descriptionP=" + descriptionP + ", dureeP=" + dureeP + ", idAdminP=" + idAdminP + '}';
    }

   


    
    
}
