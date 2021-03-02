/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;
import Entities.Objectif;
import java.util.List;
/**
 *
 * @author Chirine
 */
public interface IServiceObjectif {
    public void ajouterObjectif(Objectif o);
    public List<Objectif> afficherObjectifs();
    public void modifierObjectif(Objectif o);
    public void supprimerObjectif(String id);
    
}
