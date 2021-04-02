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
public class ActivityHolder {
    
    private int id;
    private final static ActivityHolder INSTANCE = new ActivityHolder();

    public static ActivityHolder getINSTANCE() {
        return INSTANCE;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
