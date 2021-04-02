/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inerfaces;

import Entities.classement;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author NOUR
 */
public interface IServiceClassement {

    public void ajouterClassement(classement o);

    public ObservableList<classement> afficherClassement();

    public void modifierClassement(classement o);

    public void supprimerClassement(String id);

    public ObservableList<classement> trierClassement();

    public ObservableList<classement> RechercherClassement(String pos);
      public ObservableList<classement> RechercherClassement_client(String nom);
        public ObservableList<classement> afficherClassement_client();
}
