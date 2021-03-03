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
            String requete  = "INSERT INTO `activite` (`id`, `idcoach`, `duree`,`date`, `nombremax`, `type`, `description`, `lieu`,`nombre_parti`) VALUES ('"+a.getId()+"', '"+a.getIdcoach()+"', '"+a.getDuree()+"','"+a.getDate()+"', '"+a.getNombremax()+"', '"+a.getType()+"', '"+a.getDescription()+"', '"+a.getLieu()+"', '"+a.getNombre_parti()+"') ";
            Statement st = MyConnection.getInstance().getCnx().createStatement() ;
            st.executeUpdate(requete);
            System.out.println("Activité ajouté");
            Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("ACTIVITE");
                alert.setHeaderText(null);
                alert.setContentText("Activité ajouté");
                alert.showAndWait();
                
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());
        }
        
        
    }

    @Override
    
    
    public ObservableList<Activites> showActivites() {
      ObservableList<Activites> myList = FXCollections.observableArrayList();
        try {

            String requete = "SELECT * FROM activite";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Activites A = new Activites();
                A.setId(rs.getInt(1));
                A.setIdcoach(rs.getString(2));
                A.setDuree(rs.getString(3));
                A.setDate(rs.getString(4));
                A.setNombremax(rs.getInt(5));
                A.setType(rs.getString(6));
                A.setDescription(rs.getString(7));
                A.setLieu(rs.getString(8));
                A.setNombre_parti(rs.getInt(9));
                myList.add(A);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
    
    
    
    
    
    
    
     @Override
        public void supprimeractivite(int id) {
        try {
            String requete = "DELETE FROM activite WHERE id = " + id +"";
            Statement st = MyConnection.getInstance().getCnx().createStatement() ;
            st.executeUpdate(requete);
            System.out.println("activite supprimer");
             Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("ACTIVITE");
                alert.setHeaderText(null);
                alert.setContentText("Activité suppri");
                alert.showAndWait();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    @Override
    public void modifierActivite(Activites a) {
        try {
            String requete = " UPDATE activite SET idcoach=?, duree=?,date=?,nombremax=?,type=?,description=?,lieu=?,nombre_parti=? WHERE id=?" ;
            PreparedStatement pst= MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1,a.getIdcoach());
            pst.setString(2,a.getDuree());
            pst.setString(3,a.getDate());
            pst.setInt(4,a.getNombremax());
            pst.setString(5, a.getType());
            pst.setString(6, a.getDescription());
            pst.setString(7, a.getLieu());
            pst.setInt(8,a.getNombre_parti());
            pst.setInt(9, a.getId());
            
            pst.executeUpdate();
            System.out.println("activite modifié!");
             Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("ACTIVITE");
                alert.setHeaderText(null);
                alert.setContentText("Activité modifié");
                alert.showAndWait();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Activites> findtype(String t) {
ObservableList<Activites> myList = FXCollections.observableArrayList();
    
        try {

            String requete = "SELECT * FROM activite WHERE type='"+t+"' ORDER BY date  ";

            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Activites A = new Activites();
                A.setId(rs.getInt(1));
                A.setIdcoach(rs.getString(2));
                A.setDuree(rs.getString(3));
                A.setDate(rs.getString(4));
                A.setNombremax(rs.getInt(5));
                A.setType(rs.getString(6));
                A.setDescription(rs.getString(7));
                A.setLieu(rs.getString(8));
                myList.add(A);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;   }

    @Override
    public List<Activites> finddate(String t) {
        ObservableList<Activites> myList = FXCollections.observableArrayList();

 try {

            String requete = "SELECT * FROM activite WHERE date='"+t+"'   ";

            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Activites A = new Activites();
                A.setId(rs.getInt(1));
                A.setIdcoach(rs.getString(2));
                A.setDuree(rs.getString(3));
                A.setDate(rs.getString(4));
                A.setNombremax(rs.getInt(5));
                A.setType(rs.getString(6));
                A.setDescription(rs.getString(7));
                A.setLieu(rs.getString(8));
                myList.add(A);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;     }
     @Override
    public void approuveract(Activites a){
    addActivities(a);
    
     try {
            String requete  = "DELETE from propoact WHERE id ='"+a.getId()+"'";
            Statement st = MyConnection.getInstance().getCnx().createStatement() ;
            st.executeUpdate(requete);
            System.out.println("Activité supprimer from table proposition act");
            Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("ACTIVITE");
                alert.setHeaderText(null);
                alert.setContentText("Activité supprimer from table proposition act");
                alert.showAndWait();
                
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());
        }
    
    
    }
     @Override
            public ObservableList<Activites> showpropActivites() {
      ObservableList<Activites> myList = FXCollections.observableArrayList();
        try {

            String requete = "SELECT * FROM propoact";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Activites A = new Activites();
                A.setId(rs.getInt(1));
                A.setIdcoach(rs.getString(2));
                A.setDuree(rs.getString(3));
                A.setDate(rs.getString(4));
                A.setNombremax(rs.getInt(5));
                A.setType(rs.getString(6));
                A.setDescription(rs.getString(7));
                A.setLieu(rs.getString(8));
                A.setNombre_parti(rs.getInt(9));
                myList.add(A);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
 @Override
                public void addpropActivities(Activites a) {
             try {
            String requete  = "INSERT INTO `propoact` (`id`, `idcoach`, `duree`,`date`, `nombremax`, `type`, `description`, `lieu`,`nombre_parti`) VALUES ('"+a.getId()+"', '"+a.getIdcoach()+"', '"+a.getDuree()+"','"+a.getDate()+"', '"+a.getNombremax()+"', '"+a.getType()+"', '"+a.getDescription()+"', '"+a.getLieu()+"', '"+a.getNombre_parti()+"') ";
            Statement st = MyConnection.getInstance().getCnx().createStatement() ;
            st.executeUpdate(requete);
            System.out.println("proposition  ajouté");
            Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("proposition ACTIVITE");
                alert.setHeaderText(null);
                alert.setContentText("proposition ajouté");
                alert.showAndWait();
                
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());
        }
        
        
    }
            @Override
                        public void supprimerpropoactivite(int id) {
        try {
            String requete = "DELETE FROM propoact WHERE id = " + id +"";
            Statement st = MyConnection.getInstance().getCnx().createStatement() ;
            st.executeUpdate(requete);
            System.out.println("proposition supprimer");
             Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("proposition");
                alert.setHeaderText(null);
                alert.setContentText("proposition suppri");
                alert.showAndWait();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

            
 }
    

