/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

/**
 *
 * @author SeifBS
 */
public interface IServiceUser {

    public String verifier_data(String id, String password);

    public boolean verifier_id_email_bd(String id, String email);

    public boolean verifier_id_bd(String id);

    public void modifier_password(String id, String password);

    public boolean test_Cin(String cin);

    public boolean test_Tel(String tel);

    public boolean test_Email(String cin);

    public boolean test_Password(String password);

    public boolean check_password(String id, String password);

    public void modifierPassword(String id, String password);

}
