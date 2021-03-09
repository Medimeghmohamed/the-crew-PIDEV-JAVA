/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.Admin;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author SeifBS
 */
public interface IServiceAdmin {

    public void ajouterAdmin(Admin a);

    public void modifierAdmin(Admin a);

    public void supprimerAdmin(String id);

    public List<Admin> afficherAdmin();

    public List<String> afficherAdmin_combobx();

    public Admin load_data_modify(String id);

    public Admin load_user_name(String id);

    public List<Admin> rechercherAdmin(String id,String critere);

    public void accepter_coach(String id);

    public boolean verif_super_admin(String id);


}