/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inerfaces;

import Entities.challenge;
import Entities.participation_challenge;
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

    public void supprimerChallenge(String tr);

   
    public ObservableList<challenge> trierChallenge();
    public ObservableList<challenge> RechercherChallenge(String tr);
    public void confirmer_challenge(challenge c);
    public ObservableList<challenge> afficherChallenge_client();
     public void rejoindre_challenge(challenge c,String id);
    public ObservableList<challenge> afficherChallenge_valide();
    public void challenge_done(challenge c , String id);

}
