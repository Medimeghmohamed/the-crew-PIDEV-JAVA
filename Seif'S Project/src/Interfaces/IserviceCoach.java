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

    public List<String> afficherCoach_combobx();

    public Coach load_data_modify(String id);

    public boolean test_Cin(String cin);

    public boolean test_Tel(String tel);

    public boolean test_Email(String cin);

    public boolean test_Password(String password);

    public boolean verifier_id_email_bd(String id, String email);

    public void modifier_password(String id, String password);

    public boolean verifier_data(String id, String password);

    public boolean verifier_id_bd(String id);

    public Coach load_user_name(String id);

    public List<Coach> rechercherCoach(String id);

    public boolean verifier_etat_coach(String id);

    public List<String> afficherCoach_combobx_etat();

}
