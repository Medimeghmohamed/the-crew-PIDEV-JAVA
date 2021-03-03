/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Admin;
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

   

}
