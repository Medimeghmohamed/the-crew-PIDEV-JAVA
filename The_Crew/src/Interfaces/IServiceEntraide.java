/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Entraide;
import javafx.collections.ObservableList;

/**
 *
 * @author Nour Dekhil
 */
public interface IServiceEntraide {

    public void AjouterEntraide(Entraide e);

    public ObservableList<Entraide> AfficherEntraide();

    public void SupprimerEntraide(String id);

    public void ModifierEntraide(String idd, String question);
    public ObservableList<Entraide> TrierEntraide();
    public ObservableList<Entraide> Rechercher(String id);

}
