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

    public void supprimerClient(String id);
    public List<Client> afficherClient();

    public List<String> afficherClient_combobx();

    public Client load_data_modify(String id);

    public boolean test_Cin(String cin);

    public boolean test_Tel(String tel);

    public boolean test_Email(String cin);

    public boolean test_Password(String password);

    public boolean verifier_id_email_bd(String id, String email);

    public void modifier_password(String id, String password);

    public boolean verifier_data(String id, String password);

    public boolean verifier_id_bd(String id);

    public Client load_user_name(String id);

    public List<Client> rechercherClient(String id);

}
