/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.classement;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author NOUR
 */
public interface IServiceClassement {

    public void ajouterClassement(classement o);

    public List<classement> afficherClassement();

    public void modifierClassement(classement o);

    public void supprimerClassement(String id);

    public ObservableList<classement> trierClassement();

    public ObservableList<classement> RechercherClassement(String pos);
}
