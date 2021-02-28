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
/**
 *
 * @author Medimegh
 */
public class serviceActivites implements Iactivites{

    @Override
    
    public void addActivities(Activites a) {
             try {
            String requete  = "INSERT INTO `activite` (`id`, `idcoach`, `duree`, `nombremax`, `type`, `description`, `lieu`) VALUES ('"+a.getId()+"', '"+a.getIdcoach()+"', '"+a.getDuree()+"', '"+a.getNombremax()+"', '"+a.getType()+"', '"+a.getDescription()+"', '"+a.getLieu()+"') ";
            Statement st = MyConnection.getInstance().getCnx().createStatement() ;
            st.executeUpdate(requete);
            System.out.println("Activité ajouté");
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
            String requete = " UPDATE activite SET idcoach=?, duree=?,nombremax=?,type=?,description=?,lieu=? WHERE id=?" ;
            PreparedStatement pst= MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1,a.getIdcoach());
            pst.setString(2,a.getDuree());
            pst.setInt(3,a.getNombremax());
            pst.setString(4, a.getType());
            pst.setString(5, a.getDescription());
            pst.setString(6, a.getLieu());
            pst.setInt(7, a.getId());
            pst.executeUpdate();
            System.out.println("activite modifié!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
 }
    

