/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;
import entities.Activites;
import entities.Therapie;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author Medimegh
 */
public interface Itherapie {
       public void addTherapie(Therapie a);
    public List<Therapie> showTherapie();
    public void supprimerTherapie(int id);
     public void modifierTherapie(Therapie a);
     public List<Therapie>findtype(String t);
          public List<Therapie>finddate(String t);
public void approuverTherapie(Therapie t);
public ObservableList<Therapie> showpropTherapie() ;
public void addpropTherapie(Therapie t);
public void supprimerpropoTherapie(int id);
}
