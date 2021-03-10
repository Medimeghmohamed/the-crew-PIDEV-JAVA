/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author NOUR
 */
public class ligne_challenge extends challenge{

    private int id;
    private String etat;
    //private int id_challenge;

    public ligne_challenge() {
    }

    public ligne_challenge(int id, String etat/*,int id_challenge*/) {
        this.id = id;
        this.etat = etat;
       // this.id_challenge = id_challenge;
    }

    public int getId() {
        return id;
    }

    public String getEtat() {
        return etat;
    }

    

  /*  public int getId_challenge() {
        return id_challenge;
    }*/

    public void setId(int id) {
        this.id = id;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

  
   /* public void setId_challenge(int id_challenge) {
        this.id_challenge = id_challenge;
    }*/

    /*@Override
    public String toString() {
        return "ligne_challenge{" + "id=" + id + ", etat=" + etat + ", id_challenge=" + id_challenge + '}';
    }*/

    @Override
    public String toString() {
        return "ligne_challenge{" + "id=" + id + ", etat=" + etat + '}';
    }

  
    
}
