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
    public ObservableList<Suivi> rechercherSuivi(String s);
    public ObservableList<Suivi> trierSuiviparIdClient();
    public ObservableList<Suivi> trierSuiviparId();
    public ObservableList<Suivi> trierSuiviparIdObjectif();
    public int getValeur(String idObj, String date);
    public ObservableList<String> getObjectifBilan(String idClient);
    public ObservableList<String> getDateBilan(String idObjectif);
    public String getJour(String idSuiv);
    
}
