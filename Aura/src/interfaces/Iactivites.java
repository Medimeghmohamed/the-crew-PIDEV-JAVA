/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;
import entities.Activites;
import java.util.List;
import javafx.collections.ObservableList;
/**
 *
 * @author Medimegh
 */
public interface Iactivites {
    public void addActivities(Activites a);
    public List<Activites> showActivites();
    public void supprimeractivite(int id);
public void modifierActivite(Activites a);
public List<Activites>findtype(String t);
 public List<Activites>finddate(String t);
public void approuveract(Activites a);
public ObservableList<Activites> showpropActivites() ;
public void addpropActivities(Activites a);
public void supprimerpropoactivite(int id);

}
