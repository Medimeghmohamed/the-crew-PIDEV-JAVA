/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;
import entities.Therapie;
import java.util.List;

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

}
