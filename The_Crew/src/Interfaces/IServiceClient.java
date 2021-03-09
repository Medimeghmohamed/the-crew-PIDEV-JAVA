/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.Client;
import java.util.List;

/**
 *
 * @author SeifBS
 */
public interface IServiceClient {

    public void ajouterClient(Client a);

    public void modifierClient(Client a);

    public Client load_data_modify(String id);

    public Client load_user_name(String id);

    public void supprimerClient(String id);

    public List<Client> afficherClient();

}
