/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.challenge;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author NOUR
 */
public interface IServiceChallege {

    public void ajouterChallenge(challenge o);

    public List<challenge> afficherChallenge();

    public void modifierChallenge(challenge o);

    public void supprimerChallenge(String id);

   
    public ObservableList<challenge> trierChallenge();
    public ObservableList<challenge> RechercherChallenge(String tr);

}
