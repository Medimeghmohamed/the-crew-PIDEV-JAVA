/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.util.List;

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

    boolean test_num_telephonique(String tel);

    public boolean test_Tel(String tel);

    public boolean test_Email(String cin);

    public boolean test_Password(String password);

    public boolean check_password(String id, String password);

    public void modifierPassword(String id, String password);

    public boolean verifier_tel_bd(String tel);

    public boolean verifier_email_bd(String email);

    public boolean verifier_tel_bd_modify(String tel, String id);

    public boolean verifier_email_bd_modify(String email, String id);

    public int nb_admins();

    public int nb_clients();

    public int nb_coachsV();

    public int nb_coachsNV();

    public String crypter_password(String password);

    public void updateRme(String id, String rme);

    public String getRole(String id);

    public void modifierPicture(String id, String picture);

    public void changesms(String id, String sms);

}
