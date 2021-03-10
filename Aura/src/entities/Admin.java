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
public class Admin extends User {

    public Admin(String id, String nom, String prenom, String email, String password, String tel, String role) {
        super(id, nom, prenom, email, password, tel, role);
    }

    public Admin() {
    }

    @Override
    public String toString() {
        return "Admin:" + super.toString();
    }

    public boolean equals(Admin a) {
        boolean id = this.getId().equals(a.getId());
        boolean nom = this.getNom().equals(a.getNom());
        boolean prenom = this.getPrenom().equals(a.getPrenom());
        boolean email = this.getEmail().equals(a.getEmail());
        boolean tel = this.getTel().equals(a.getTel());

        if (id == true && nom == true && prenom == true && email == true && tel == true) {
            return true;
        }

        return false;
    }





}
