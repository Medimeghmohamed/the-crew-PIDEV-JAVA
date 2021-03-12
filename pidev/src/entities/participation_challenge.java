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
public class participation_challenge {
    private int id;
    private int id_challenge;
    private String id_client;
    private String etat;
    
    
    public participation_challenge() {}

    public participation_challenge(int id, int id_challenge, String id_client, String etat) {
        this.id = id;
        this.id_challenge = id_challenge;
        this.id_client = id_client;
        this.etat = etat;
    }

    
    
    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
    

    public int getId() {
        return id;
    }

    public int getId_challenge() {
        return id_challenge;
    }

    public String getId_client() {
        return id_client;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_challenge(int id_challenge) {
        this.id_challenge = id_challenge;
    }

    public void setId_client(String id_client) {
        this.id_client = id_client;
    }

    @Override
    public String toString() {
        return "participation_challenge{" + "id=" + id + ", id_challenge=" + id_challenge + ", id_client=" + id_client + ", etat=" + etat + '}';
    }

   
    
    
    
}
