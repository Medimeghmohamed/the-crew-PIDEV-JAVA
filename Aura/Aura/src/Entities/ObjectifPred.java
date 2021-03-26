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
    //private String idP;
    private int idP;
    private String descriptionP;
    private int dureeP;
    //private String idAdminP;
    private Admin admin;

    public ObjectifPred() {
    }

    public ObjectifPred(int idP, String descriptionP, int dureeP, Admin admin) {
        this.idP = idP;
        this.descriptionP = descriptionP;
        this.dureeP = dureeP;
        this.admin=admin;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }



    public int getIdP() {
        return idP;
    }

    public void setIdP(int idP) {
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
        return "ObjectifPred{" + "idP=" + idP + ", descriptionP=" + descriptionP + ", dureeP=" + dureeP + ", Admin=" + admin + '}';
    }

   


    
    
}
