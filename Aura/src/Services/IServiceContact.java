/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Entities.Contact;
import java.util.List;

/**
 *
 * @author Nour Dekhil
 */
public interface IServiceContact {
    public void ajouterContact(Contact c );
    public List<Contact> AfficherContact();
    public void  SupprimerContact(String id);
    public void ModifierContact(String id, String etat);
    
    
}
