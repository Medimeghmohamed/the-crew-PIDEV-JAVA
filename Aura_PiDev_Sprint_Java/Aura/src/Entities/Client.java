/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author SeifBS
 */
public class Client extends User {

    private String adresse;

    public Client(String id, String nom, String prenom, String email, String password, String tel,String role,String adresse,String rme,String picture,String sms) {
        super(id, nom, prenom, email, password, tel, role,rme,picture,sms);
        this.adresse = adresse;

    }

    public Client() {
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getAdresse() {
        return adresse;
    }

    @Override
    public String toString() {
        return  super.toString()+"Adresse: "+adresse; 
    }
    
      public boolean equals(Client a) {
        boolean id = this.getId().equals(a.getId());
        boolean nom = this.getNom().equals(a.getNom());
        boolean prenom = this.getPrenom().equals(a.getPrenom());
        boolean email = this.getEmail().equals(a.getEmail());
        boolean tel = this.getTel().equals(a.getTel());
        boolean add = this.getAdresse().equals(a.getAdresse());

        if (id == true && nom == true && prenom == true && email == true && tel == true&& add==true) {
            return true;
        }

        return false;
    }

}
