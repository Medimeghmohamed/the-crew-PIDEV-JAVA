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
public class niveau {
    private int id;
    private String titre;
    
    public niveau(){}
    public niveau(int id, String titre)
    {
       this.id=id;
       this.titre=titre;
    }

    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    @Override
    public String toString() {
        return "niveau{" + "id=" + id + ", titre=" + titre + '}';
    }
    

}
