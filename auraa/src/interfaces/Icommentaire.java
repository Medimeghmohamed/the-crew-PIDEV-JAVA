/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;


import entities.commentaire;
import java.util.List;

/**
 *
 * @author akram
 */
public interface Icommentaire {
      public void ajouterc (commentaire c);
      public String recup_commentaire(String com);
     /* public void supprimerc(String com);*/
    /*public List<commentaire> showCommentaire();*/
   /* public void modifierc (commentaire c);*/
}
