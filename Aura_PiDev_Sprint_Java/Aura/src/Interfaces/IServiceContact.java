/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;
import Entities.Contact;
import java.util.List;

/**
 *
 * @author Nour Dekhil
 */
public interface IServiceContact {
    public void AjouterContact(Contact c );
    public List<Contact> AfficherContact();
    public void  SupprimerContact(int id);
    public void ModifierContact(String email);
    
    
}
