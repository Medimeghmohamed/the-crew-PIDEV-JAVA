/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.Icommentaire;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import maconnexion.MyConnection;
import entities.commentaire;
import java.sql.ResultSet;

/**
 *
 * @author akram
 */

public class serviceCommentaire implements Icommentaire {

    Connection cnx;

    public serviceCommentaire() {
        cnx = MyConnection.getInstance().getCnx();
    }

    @Override
    public void ajouterc(commentaire c) {

        try {
            if (cnx != null) {
                c.setId_coach("5454");
                c.setId_client("8");
                c.setDate("02/02/20");
                c.setId_article(2);

                String requete = "INSERT INTO `commentaire` ( `idc`,`id_client`, `id_coach`, `id_article`, `commentaire`,`date`) VALUES ( '" + c.getIdc() + "','" + c.getId_client() + "', '" + c.getId_coach() + "', '" + c.getId_article() + "','" + c.getCommentaire() + "','" + c.getDate() + "') ";
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
    public String recup_commentaire(String com){
        commentaire ch = new commentaire();
        
          try {
            Statement st = cnx.createStatement();
            
            String query = "select * FROM `commentaire` WHERE commentaire='" + com + "'";
            ResultSet rst = st.executeQuery(query);
            if(rst.next()){
   ch.setCommentaire(rst.getString("commentaire"));
}
                 
            
        } catch (SQLException ex) {
            System.out.println("erreur get IdOBJ pour suivi");
            System.out.println(ex);
        }
        
            return ch.getCommentaire();
            
    }
    
    
 /*   @Override
    public void supprimerc(String com) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       
       
        try {
            Statement st = cnx.createStatement();
           String query = "DELETE FROM `commentaire` WHERE commentaire = " + "com" + "";
            st.executeUpdate(query);
            System.out.println("suppression avec succes");
        } catch (SQLException ex) {
            System.out.println("erreur supprimer challenge");
            System.out.println(ex);
        }
    }*/
    
  /*  @Override
     public void modifierc(commentaire c) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try {
            Statement st = cnx.createStatement();
           
            String query = "UPDATE  commentaitre SET commentaire = '" + c.getTitre()+ "', theme = '" + c.getTheme()+ "', nom_auteur = '" + c.getNom_auteur()+ "', article = '" + c.getArticle()+ "'  WHERE id = " + c.getId()+ "";
            st.executeUpdate(query);
            System.out.println("modification avec succes");
        } catch (SQLException ex) {
            System.out.println("erreur modifier challenge");
            System.out.println(ex);
        }
    }
   */
}
