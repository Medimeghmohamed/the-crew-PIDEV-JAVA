/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Objectif;
import Interfaces.IServiceObjectif;
import Utils.MaConnexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import static java.util.Collections.list;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Chirine
 */
public class ServiceObjectif implements IServiceObjectif{
    Connection cnx;

    public ServiceObjectif() {
        cnx= MaConnexion.getInstance().getCnx();
    }
    

    @Override
    public void ajouterObjectif(Objectif o) {
                
        String date = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
        try {
            Statement st = cnx.createStatement();
            String query="INSERT INTO `objectif`(`id`, `description`, `reponse`, `dateDebut`, `duree`, `idClient`) "
                    + "VALUES ('"+o.getId()+"','"
                    +o.getDescription()+"','"
                    +o.getReponse()+"','"
                    +date+"','"
                    +o.getDuree()+"','"
                    +o.getIdCli()+"')";
            st.executeUpdate(query);
            System.out.println("ajout avec succes");
        } catch (SQLException ex) {
            System.out.println("erreur ajouter objectifs");
            System.out.println(ex);
        }
    }

    @Override
    public ObservableList<Objectif> afficherObjectifs() {
        ObservableList<Objectif> objectifs = FXCollections.observableArrayList();
        try {
            Statement st= cnx.createStatement();
            String query="select * from `objectif`";
            ResultSet rs= st.executeQuery(query);          
            while (rs.next())
            {
                Objectif o= new Objectif();
                o.setId(rs.getString(1));
                o.setDescription(rs.getString(2));
                o.setReponse(rs.getInt(3));
                o.setDate(rs.getString(4));
                //o.setDate(rs.getTimestamp(4).toString());
                o.setDuree(rs.getInt(5));
                o.setIdCli(rs.getString(6));
                objectifs.add(o);
            }
            
        } catch (SQLException ex) {
            System.out.println("erreur afficher objectifs");
            System.out.println(ex);
        }
        return objectifs;
    }

    @Override
    public void modifierObjectif(Objectif o) {
        try {
            Statement st = cnx.createStatement();
            //nb: on ne peut pas modifier la date
            String query = "UPDATE  objectif SET description  = '" + o.getDescription() + "', reponse = '" + o.getReponse() + ", duree = " + o.getDuree() + " WHERE id = " + o.getId() + "";
            st.executeUpdate(query);
            System.out.println("modification avec succes");
        } catch (SQLException ex) {
            System.out.println("erreur modifier objectif");
            System.out.println(ex);
        }   
    }

    @Override
    public void supprimerObjectif(String id) {
        try {
            Statement st = cnx.createStatement();
            String query = "DELETE FROM `objectif` WHERE id = " + id +"";
            st.executeUpdate(query);
            System.out.println("suppression avec succes");
        } catch (SQLException ex) {
            System.out.println("erreur supprimer objectif");
            System.out.println(ex);
        }  
    }

    
}
