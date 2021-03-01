/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entities.Activites;
import interfaces.Iactivites;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import MaConnexion.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
/**
 *
 * @author Medimegh
 */
public class serviceActivites implements Iactivites{

    @Override
    
    public void addActivities(Activites a) {
             try {
            String requete  = "INSERT INTO `activite` (`id`, `idcoach`, `duree`,`date`, `nombremax`, `type`, `description`, `lieu`) VALUES ('"+a.getId()+"', '"+a.getIdcoach()+"', '"+a.getDuree()+"','"+a.getDate()+"', '"+a.getNombremax()+"', '"+a.getType()+"', '"+a.getDescription()+"', '"+a.getLieu()+"') ";
            Statement st = MyConnection.getInstance().getCnx().createStatement() ;
            st.executeUpdate(requete);
            System.out.println("Activité ajouté");
            Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Activité ajouté");
                alert.showAndWait();
                
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());
        }
        
        
    }

    @Override
    public List<Activites> showActivites() {
       List<Activites> myList = new ArrayList<>();
        try {

            String requete = "SELECT * FROM activite";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Activites A = new Activites();
                A.setId(rs.getInt(1));
                A.setIdcoach(rs.getString("idcoach"));
                A.setDuree(rs.getString("duree"));
                A.setDate(rs.getString("date"));
                A.setNombremax(rs.getInt("nombremax"));
                A.setType(rs.getString("type"));
                A.setDescription(rs.getString("description"));
                A.setLieu(rs.getString("lieu"));
                myList.add(A);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
    
    
    
    
    
    
    
     @Override
    public void supprimeractivite(Activites a) {
        try {
            String requete = "DELETE FROM activite WHERE id=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1,a.getId());
            pst.executeUpdate();
            System.out.println("activite supprimer");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public void modifierActivite(Activites a) {
        try {
            String requete = " UPDATE activite SET idcoach=?, duree=?,date=?,nombremax=?,type=?,description=?,lieu=? WHERE id=?" ;
            PreparedStatement pst= MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1,a.getIdcoach());
            pst.setString(2,a.getDuree());
            pst.setString(3,a.getDate());
            pst.setInt(4,a.getNombremax());
            pst.setString(5, a.getType());
            pst.setString(6, a.getDescription());
            pst.setString(7, a.getLieu());
            pst.setInt(8, a.getId());
            pst.executeUpdate();
            System.out.println("activite modifié!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
        
 }
    

