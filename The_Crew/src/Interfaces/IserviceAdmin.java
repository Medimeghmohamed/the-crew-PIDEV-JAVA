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
public interface IserviceAdmin {

    public void ajouterAdmin(Admin a);

    public void modifierAdmin(Admin a);

    public void supprimerAdmin(String id);

    public List<Admin> afficherAdmin();

    public List<String> afficherAdmin_combobx();

    public Admin load_data_modify(String id);

    public boolean test_Cin(String cin);

    public boolean test_Tel(String tel);

    public boolean test_Email(String cin);

    public boolean test_Password(String password);
    
   public boolean verifier_id_email_bd(String id,String email);
   public void modifier_password(String id,String password);

}
