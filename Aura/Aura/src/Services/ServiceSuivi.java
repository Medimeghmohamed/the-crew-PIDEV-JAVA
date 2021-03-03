/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Objectif;
import Entities.Suivi;
import Interfaces.IServiceSuivi;
import Utils.MaConnexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Chirine
 */
public class ServiceSuivi implements IServiceSuivi {

    Connection cnx;

    public ServiceSuivi() {
        cnx = MaConnexion.getInstance().getCnx();
    }

    @Override
    public void ajouterSuivi(Suivi s) {
        try {
            Statement st = cnx.createStatement();
            String query = "INSERT INTO `suivi`(`id`, `valeur`, `idClient`, `idObjectif`, `date`) "
                    + "VALUES ('" + s.getIdSuiv() + "','"
                    + s.getValeurSuiv() + "','"
                    + s.getIdClientSuiv() + "','"
                    + s.getIdObjectifSuiv() + "','"
                    + s.getDateSuiv() + "')";
            st.executeUpdate(query);
            System.out.println("ajout suivi avec succes");
        } catch (SQLException ex) {
            System.out.println("erreur ajout suivi");
            System.out.println(ex);
        }
    }

    @Override
    public ObservableList<Suivi> afficherSuivi() {
        ObservableList<Suivi> suivis = FXCollections.observableArrayList();
        try {
            Statement st = cnx.createStatement();
            String query = "select * from `suivi`";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Suivi s= new Suivi();
                s.setIdSuiv(rs.getString(1));
                s.setValeurSuiv(rs.getInt(2));
                s.setIdClientSuiv(rs.getString(3));
                s.setIdObjectifSuiv(rs.getString(4));
                s.setDateSuiv(rs.getString(5));
                
                suivis.add(s);
            }

        } catch (SQLException ex) {
            System.out.println("erreur afficher suivis");
            System.out.println(ex);
        }
        return suivis;
    }

}
