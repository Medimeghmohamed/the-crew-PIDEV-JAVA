/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import MaConnexion.MyConnection;
import entities.Activites;
import entities.Therapie;
import interfaces.Itherapie;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

/**
 *
 * @author Medimegh
 */
public class serviceTherapie implements Itherapie{

    @Override
    public void addTherapie(Therapie t) {
        
           try {
            String requete  = "INSERT INTO `therapie` (`id`, `sujet`, `date`, `lieu`, `nombremax`, `idcoach`) VALUES ('"+t.getId()+"', '"+t.getSujet()+"', '"+t.getDate()+"', '"+t.getLieu()+"', '"+t.getNombremax()+"', '"+t.getIdcoach()+"')";
            Statement st = MyConnection.getInstance().getCnx().createStatement() ;
            st.executeUpdate(requete);
            System.out.println("Therapie ajouté");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Therapie");
                alert.setHeaderText(null);
                alert.setContentText("Therapie ajouté");
                alert.showAndWait();
                
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());
        }
        
    }

    @Override
    public ObservableList<Therapie> showTherapie() {
       ObservableList<Therapie> myList = FXCollections.observableArrayList();
        try {

            String requete = "SELECT * FROM therapie";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Therapie T = new Therapie();
                T.setId(rs.getInt(1));
                T.setSujet(rs.getString(2));
                T.setDate(rs.getString(3));
                T.setLieu(rs.getString(4));
                T.setNombremax(rs.getInt(5));
                T.setIdcoach(rs.getString(6));
                myList.add(T);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

    @Override
    public void supprimerTherapie(int id) {
         try {
            String requete = "DELETE FROM therapie WHERE id = " + id +"";
            Statement st = MyConnection.getInstance().getCnx().createStatement() ;
            st.executeUpdate(requete);
            System.out.println("Therapie supprimer");
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Therapie");
                alert.setHeaderText(null);
                alert.setContentText("Therapie suppri");
                alert.showAndWait();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifierTherapie(Therapie t) {
    try {
            String requete = " UPDATE therapie SET sujet=?,date=?,lieu=?,nombremax=?,idcoach=? WHERE id=?" ;
            PreparedStatement pst= MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1,t.getSujet());
            pst.setString(2,t.getDate());
            pst.setString(3,t.getLieu());
            pst.setInt(4,t.getNombremax());
            pst.setString(5, t.getIdcoach());
            pst.setInt(6, t.getId());
            pst.executeUpdate();
            System.out.println("Therapie modifié!");
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Therapie");
                alert.setHeaderText(null);
                alert.setContentText("Therapie modifié");
                alert.showAndWait();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Therapie> findtype(String t) {
       ObservableList<Therapie> myList = FXCollections.observableArrayList();
        try {

            String requete = "SELECT * FROM therapie  WHERE sujet='"+t+"' ORDER BY date  ";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Therapie T = new Therapie();
                T.setId(rs.getInt(1));
                T.setSujet(rs.getString(2));
                T.setDate(rs.getString(3));
                T.setLieu(rs.getString(4));
                T.setNombremax(rs.getInt(5));
                T.setIdcoach(rs.getString(6));
                myList.add(T);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }    

    @Override
    public List<Therapie> finddate(String t) {
 ObservableList<Therapie> myList = FXCollections.observableArrayList();
        try {

            String requete = "SELECT * FROM therapie  WHERE date='"+t+"'  ";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Therapie T = new Therapie();
                T.setId(rs.getInt(1));
                T.setSujet(rs.getString(2));
                T.setDate(rs.getString(3));
                T.setLieu(rs.getString(4));
                T.setNombremax(rs.getInt(5));
                T.setIdcoach(rs.getString(6));
                myList.add(T);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;    }
    
}
