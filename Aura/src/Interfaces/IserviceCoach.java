/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.Coach;
import java.util.List;

/**
 *
 * @author SeifBS
 */
public interface IserviceCoach {

    public void ajouterCoach(Coach a);

    public void modifierCoach(Coach a);

    public void supprimerCoach(String id);

    public List<Coach> afficherCoach_All();

    public List<Coach> afficherCoach_Oui();

    public List<Coach> afficherCoach_Non();


    public Coach load_data_modify(String id);

    public Coach load_user_name(String id);

    public List<Coach> rechercherCoach(String id);



}
