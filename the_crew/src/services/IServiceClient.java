/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

/**
 *
 * @author NOUR
 */
import entities.client;
import java.util.List;

public interface IServiceClient 

{

    public void Addclient(client c);

    public List<client> Afficherclient();
    
 
}
