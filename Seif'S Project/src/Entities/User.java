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
public abstract class User {

    private String id;
    String nom;
    String prenom;
    private String email;
    private String password;
    private String tel;
    private String role;
    private String rme;
    private String picture;
    private String sms;

    public User(String id, String nom, String prenom, String email, String password, String tel, String role, String rme, String picture, String sms) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.tel = tel;
        this.role = role;
        this.rme = rme;
        this.picture = picture;
        this.sms = sms;
    }

   

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public User() {
    }

    public String getPassword() {
        return password;
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

    public String getTel() {
        return tel;
    }

    public String getRole() {
        return role;
    }

    public String getRme() {
        return rme;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setRme(String rme) {
        this.rme = rme;
    }

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }
    

}
