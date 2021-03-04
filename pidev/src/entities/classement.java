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
public class classement {
    private int id ;
    private String client;
    private int niveau;
    private int position ;
    private int nb_points;
    
    // integration badel les int becl varchar 
    
    
    
    public classement(){}
    public classement(int id, String client, int niveau, int position, int nb_points)
    {
        this.id=id;
        this.client=client;
        this.niveau=niveau;
        this.position=position;
        this.nb_points=nb_points;
    }

    public int getId() {
        return id;
    }

    public String getClient() {
        return client;
    }

    public int getNiveau() {
        return niveau;
    }

    public int getPosition() {
        return position;
    }

    public int getNb_points() {
        return nb_points;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setNb_points(int nb_points) {
        this.nb_points = nb_points;
    }

    @Override
    public String toString() {
        return "classement{" + "id=" + id + ", client=" + client + ", niveau=" + niveau + ", position=" + position + ", nb_points=" + nb_points + '}';
    }
    

    
}
