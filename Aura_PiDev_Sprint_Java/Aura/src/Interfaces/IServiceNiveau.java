/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.niveau;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author NOUR
 */
public interface IServiceNiveau {

    public void ajouterNiveau(niveau o);

    public List<niveau> afficherNiveau();

    public void modifierNiveau(niveau n, String tr);

    public void supprimerNiveau(String id);
     public ObservableList<niveau> RechercherNiveau(String tr);
       public ObservableList<niveau> trierNiveau() ;

}
