/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;
import entities.Activites;
import java.util.List;
/**
 *
 * @author Medimegh
 */
public interface Iactivites {
    public void addActivities(Activites a);
    public List<Activites> showActivites();
}
