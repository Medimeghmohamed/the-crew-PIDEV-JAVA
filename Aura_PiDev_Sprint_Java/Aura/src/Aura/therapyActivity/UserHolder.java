/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aura.therapyActivity;

/**
 *
 * @author Admin
 */
public class UserHolder {
    
    private String id;
    private final static UserHolder INSTANCE = new UserHolder();

    public static UserHolder getINSTANCE() {
        return INSTANCE;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
