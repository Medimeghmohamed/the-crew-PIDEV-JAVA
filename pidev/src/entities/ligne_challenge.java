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
public class ligne_challenge {

    private int id;
    private int id_client;
    private int id_challenge;

    public ligne_challenge() {
    }

    public ligne_challenge(int id, int id_client, int id_challenge) {
        this.id = id;
        this.id_client = id_client;
        this.id_challenge = id_challenge;
    }

    public int getId() {
        return id;
    }

    public int getId_client() {
        return id_client;
    }

    public int getId_challenge() {
        return id_challenge;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public void setId_challenge(int id_challenge) {
        this.id_challenge = id_challenge;
    }

    @Override
    public String toString() {
        return "ligne_challenge{" + "id=" + id + ", id_client=" + id_client + ", id_challenge=" + id_challenge + '}';
    }

    
}
