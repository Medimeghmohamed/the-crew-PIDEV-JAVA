/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.commentaire;
import javafx.collections.ObservableList;

/**
 *
 * @author akram
 */
public interface Icommentaire {

      public void ajouterc(commentaire c ,String id_user);

    public String recup_commentaire(String com);

    public void supprimerc(commentaire com, String id_user);
     public void modifier(commentaire c,String id_user);

    public ObservableList<commentaire> show();

    public ObservableList<commentaire> trier();

    /*public List<commentaire> showCommentaire();*/
 /*public void modifierc (commentaire c);*/
}
