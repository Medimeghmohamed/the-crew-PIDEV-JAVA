/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.article;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author akram
 */
 public interface Iarticle {
    public void ajouter (article o);
    public List<article> showArticle(String id_user);
    public void modifier (article a);
     public ObservableList<article> trier(String id_user);
     public ObservableList<article> trierc();
    public ObservableList<article> recherche(String theme ) ;
    public void supprimer(article c);
     public String recup_article (String art);
     public void approuver(article c);
     public void supprimerad(article c);
     
 }