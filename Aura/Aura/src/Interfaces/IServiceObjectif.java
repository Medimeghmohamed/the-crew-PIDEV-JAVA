/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;
import Entities.Objectif;
import javafx.collections.ObservableList;
/**
 *
 * @author Chirine
 */
public interface IServiceObjectif {
    public void ajouterObjectif(Objectif o);
    public ObservableList<String> getMesObjectifs(int id);
    public String getIdObj(String obj, String id);
    public ObservableList<Objectif> afficherObjectifs();
    public void modifierObjectif(Objectif o);
    public void supprimerObjectif(String id);
    public ObservableList<Objectif> rechercherObjectif(String s);
    public ObservableList<Objectif> trierObjectifparRep();
    public ObservableList<Objectif> trierObjectifparId();
    public ObservableList<Objectif> trierObjectifparDesc();
    public int getRepObj(String idObj);
    public String getJourDateDebutObj(String idObj, String idCli);
    public int getDureeObj(String idObj, String idCli);
    public Objectif load_data_modify(String id);

    
}
