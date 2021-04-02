/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.Entraide;
import java.sql.Date;
import javafx.collections.ObservableList;

/**
 *
 * @author Nour Dekhil
 */
public interface IServiceEntraide {

    public void AjouterEntraide(Entraide e);

    public ObservableList<Entraide> AfficherEntraide();

    public void SupprimerEntraide(String question);

    public void ModifierEntraide(int idd, String question, String categorie, String date );
    public ObservableList<Entraide> TrierEntraide();
    public ObservableList<Entraide> Rechercher(String a);

}
