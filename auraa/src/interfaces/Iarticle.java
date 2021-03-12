/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entities.article;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author akram
 */
 public interface Iarticle {
    public void ajouter (article o);
    public List<article> showArticle();
    public void modifier (article a);
    public List<article> trier();
    public ObservableList<article> recherche(String theme ) ;
     public String recup_article (String art);
     
 }