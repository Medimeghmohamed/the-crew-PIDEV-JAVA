/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author SeifBS
 */
public class Coach extends User {

    private String specialite;

    public Coach() {
    }

    public Coach(String id, String nom, String prenom, String email, String password, String tel, String role, String specialite) {
        super(id, nom, prenom, email, password, tel, role);

        this.specialite = specialite;
    }

    public String toString() {
        return  super.toString();
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getSpecialite() {
        return specialite;
    }

    public boolean equals(Coach a) {
        boolean id = this.getId().equals(a.getId());
        boolean nom = this.getNom().equals(a.getNom());
        boolean prenom = this.getPrenom().equals(a.getPrenom());
        boolean email = this.getEmail().equals(a.getEmail());
        boolean tel = this.getTel().equals(a.getTel());
        boolean spec = this.getSpecialite().equals(a.getSpecialite());

        if (id == true && nom == true && prenom == true && email == true && tel == true&&spec==true) {
            return true;
        }

        return false;
    }

}
