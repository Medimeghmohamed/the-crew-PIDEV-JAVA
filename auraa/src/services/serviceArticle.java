/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import maconnexion.MyConnection;
import entities.article;
import interfaces.Iarticle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import interfaces.Iarticle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author akram
 */
public class serviceArticle implements Iarticle {

    Connection cnx;

    public serviceArticle() {
        cnx = MyConnection.getInstance().getCnx();
    }

    @Override
    public void ajouter(article a) {

        try {
            if (cnx != null) {
                a.setId_coach("5454");
                a.setId_client("8");
                a.setDate("24/25/20");

                String requete = "INSERT INTO `article` ( `id`,`titre`, `theme`, `nom_auteur`, `date`,`article`,`id_coach`,`id_client`) VALUES ( '" + a.getId() + "','" + a.getTitre() + "', '" + a.getTheme() + "', '" + a.getNom_auteur() + "','" + a.getDate() + "','" + a.getArticle() + "','" + a.getId_coach() + "','" + a.getId_client() + "') ";
                Statement st = MyConnection.getInstance().getCnx().createStatement();
                assert st != null;
                st.executeUpdate(requete);
                System.out.println("Activité ajouté");
            } else {
                System.out.println("non");
            }
        } catch (SQLException ex) {
            System.out.println("erreur ajouter objectifs");
            System.out.println(ex);
        }
    }

   
             
     
    @Override
    public ObservableList<article> showArticle() {
        Statement stm = null;
        ObservableList<article> articles = FXCollections.observableArrayList();
        

        try {

            //try {
            stm = MyConnection.getInstance().getCnx().createStatement();

            String query = "SELECT * FROM article ";
            ResultSet rs = stm.executeQuery(query);
            
            while (rs.next()) {
                article A= new article();
            
              
                A.setTitre(rs.getString(2));
                A.setTheme(rs.getString(3));
                A.setNom_auteur(rs.getString(4));
                A.setArticle(rs.getString(6));
                A.setDate(rs.getString(5));
                
                A.setId_coach(rs.getString(7));
                A.setId_client(rs.getString(8));
                articles.add(A);
            }
            /* } catch (SQLException ex) {
            System.out.println(ex.getMessage());*/

            //}
        } catch (SQLException ex) {
            Logger.getLogger(serviceArticle.class.getName()).log(Level.SEVERE, null, ex);
        }

        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return articles;
    }
    @Override
   public void modifier(article c) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try {
            Statement st = cnx.createStatement();
           
            String query = "UPDATE  article SET titre= '" + c.getTitre()+ "', theme = '" + c.getTheme()+ "', nom_auteur = '" + c.getNom_auteur()+ "', article = '" + c.getArticle()+ "'  WHERE id = " + c.getId()+ "";
            st.executeUpdate(query);
            System.out.println("modification avec succes");
        } catch (SQLException ex) {
            System.out.println("erreur modifier challenge");
            System.out.println(ex);
        }
    }
    
   public void supprimer(String id) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       
       int Id=Integer.parseInt(id);
        try {
            Statement st = cnx.createStatement();
            String query = "DELETE FROM article WHERE id = " + Id + "";
            st.executeUpdate(query);
            System.out.println("suppression avec succes");
        } catch (SQLException ex) {
            System.out.println("erreur supprimer challenge");
            System.out.println(ex);
        }
    }
    
    public article recup(int id){
        article A = new article();
        
          try {
            Statement st = cnx.createStatement();
            String query = "select * FROM article WHERE id='" + id + "'";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {   
             
              A.setId(rs.getInt(1));
               A.setTitre(rs.getString(2));
                A.setTheme(rs.getString(3));
                A.setNom_auteur(rs.getString(4));
                A.setArticle(rs.getString(6));
                A.setDate(rs.getString(5));
                
                A.setId_coach(rs.getString(7));
                A.setId_client(rs.getString(8));
                System.out.println("hhhhhh");
               
              
                /*ch.setId(("id"));
                ch.setTitre(rst.getString(2));
                ch.setType(rst.getString(3));
                ch.setDescription(rst.getString(4));
                ch.setImg(rst.getString(5));
                ch.setDate_debut(rst.getDate(6));
                ch.setDate_fin(rst.getDate(7));
                ch.setNb_participants(rst.getInt(8));
                ch.setEtat(rst.getString(9));
                ch.setNiveau(rst.getInt(10));*/
            }
            
        } catch (SQLException ex) {
            System.out.println("erreur get IdOBJ pour suivi");
            System.out.println(ex);
        }
        
            return A;
        
    }
    
    
    @Override
    public ObservableList<article> trier() {
        Statement stm = null;
        ObservableList<article> articles = FXCollections.observableArrayList();
        

        try {

            //try {
            stm = MyConnection.getInstance().getCnx().createStatement();

            String query = "SELECT * FROM article ORDER BY titre ";
            ResultSet rs = stm.executeQuery(query);
            
            while (rs.next()) {
                article A= new article();
            
              
                A.setTitre(rs.getString(2));
                A.setTheme(rs.getString(3));
                A.setNom_auteur(rs.getString(4));
                A.setArticle(rs.getString(6));
                A.setDate(rs.getString(5));
                
                A.setId_coach(rs.getString(7));
                A.setId_client(rs.getString(8));
                articles.add(A);
            }
            /* } catch (SQLException ex) {
            System.out.println(ex.getMessage());*/

            //}
        } catch (SQLException ex) {
            Logger.getLogger(serviceArticle.class.getName()).log(Level.SEVERE, null, ex);
        }

        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return articles;
    }
    
    @Override
    public ObservableList<article> recherche(String theme) {
        Statement stm = null;
        ObservableList<article> articles = FXCollections.observableArrayList();
        

        try {

            //try {
            stm = MyConnection.getInstance().getCnx().createStatement();

            String query = " select * from article  WHERE theme = themes ";
            ResultSet rs = stm.executeQuery(query);
            
            while (rs.next()) {
                article A= new article();
            
              
                A.setTitre(rs.getString(2));
                A.setTheme(rs.getString(3));
                A.setNom_auteur(rs.getString(4));
                A.setArticle(rs.getString(6));
                A.setDate(rs.getString(5));
                
                A.setId_coach(rs.getString(7));
                A.setId_client(rs.getString(8));
                articles.add(A);
            }
            /* } catch (SQLException ex) {
            System.out.println(ex.getMessage());*/

            //}
        } catch (SQLException ex) {
            Logger.getLogger(serviceArticle.class.getName()).log(Level.SEVERE, null, ex);
        }

        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return articles;
    }
    
    
}
