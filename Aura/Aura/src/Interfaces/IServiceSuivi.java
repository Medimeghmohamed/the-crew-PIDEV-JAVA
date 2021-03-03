/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.Suivi;
import javafx.collections.ObservableList;

/**
 *
 * @author Chirine
 */
public interface IServiceSuivi {
    public void ajouterSuivi(Suivi s);
    public ObservableList<Suivi> afficherSuivi();
    
}
