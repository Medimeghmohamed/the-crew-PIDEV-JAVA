/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.participation_challenge;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.Myconnexion;

/**
 *
 * @author NOUR
 */
public class ServiceParticipationChallenge {

    Connection cnx;

    public ServiceParticipationChallenge() {
        cnx = Myconnexion.getInstance().getConnection();
    }
    
    

    public ObservableList<participation_challenge> recup_participation(int id_client) {
        participation_challenge n = new participation_challenge();
        ObservableList<participation_challenge>Oparticipation=FXCollections.observableArrayList();

        try {
            Statement st = cnx.createStatement();
            String query = "select * FROM `participation_challenge` WHERE id='" + id_client + "'";
            ResultSet rst = st.executeQuery(query);
            while (rst.next()) {

                n.setId(rst.getInt(1));
                n.setId_challenge(rst.getInt("id_challenge"));
                n.setId_client(rst.getString("id_client"));
                Oparticipation.add(n);
                

            }

        } catch (SQLException ex) {
            System.out.println("erreur get IdOBJ pour suivi");
            System.out.println(ex);
        }

        return Oparticipation;

    }
}
