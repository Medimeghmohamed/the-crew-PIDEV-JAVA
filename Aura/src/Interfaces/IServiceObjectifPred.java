/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.Objectif;
import Entities.ObjectifPred;
import javafx.collections.ObservableList;

/**
 *
 * @author Chirine
 */
public interface IServiceObjectifPred {

    public void ajouterObjectifPred(ObjectifPred op);

    public ObservableList<ObjectifPred> afficherObjectifsPred();

    public ObservableList<ObjectifPred> rechercherObjectifPred(String s);

    public ObservableList<String> getValuesObjectifs();

    public void modifierObjectifPred(ObjectifPred op);

    public void supprimerObjectifPred(String id);

    public ObservableList<ObjectifPred> trierObjectifPredparDuree();

    public ObservableList<ObjectifPred> trierObjectifPredparId();

    public ObservableList<ObjectifPred> trierObjectifPredparDesc();

}
