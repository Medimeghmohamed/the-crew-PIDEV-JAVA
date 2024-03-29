/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.Objectif;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author Chirine
 */
public interface IServiceObjectif {

    public void ajouterObjectif(Objectif o, int checked);

    public ObservableList<String> getMesObjectifs(int id);

    public String getIdObj(String obj, String id);

    public ObservableList<Objectif> afficherObjectifs(String idCli);

    public ObservableList<Objectif> afficherObjectifsReminder(String idCli);

    public List<Objectif> afficherObjectifsItems(String idCli);

    public void modifierObjectif(Objectif o);

    public void supprimerObjectif(int id);

   public List<Objectif> rechercherObjectif(String s);

    public List<Objectif> trierObjectifparDate();

    public List<Objectif> trierObjectifparId();

    public  List<Objectif>  trierObjectifparDesc();

    public int getRepObj(int idObj);

    public String getJourDateDebutObj(int idObj, String idCli);

    public int getDureeObj(int idObj, String idCli);

    public Objectif load_data_modify(int id);

    public int getIdObjparDesc(String desc);

    public String getDateDebutObj(int idObj, String idCli);

    public void mailCkeckedObj();

    public int retrunmailCkeckedObj(int idObj, int idCli);

    public int getDureeparDesc(String desc);

}
