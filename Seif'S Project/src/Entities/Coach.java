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
public class Coach {

    private String id;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String tel;
    private String specialite;

    public Coach() {
    }

    @Override
    public String toString() {
        return "Coach{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", password=" + password + ", tel=" + tel + ", specialite=" + specialite + '}';
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getTel() {
        return tel;
    }

   

    public String getSpecialite() {
        return specialite;
    }

    public Coach(String id, String nom, String prenom, String email, String password, String tel, String specialite) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.tel = tel;
        this.specialite = specialite;
    }

}
