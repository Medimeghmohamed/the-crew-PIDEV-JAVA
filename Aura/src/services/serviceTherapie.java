/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import MaConnexion.MyConnection;
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
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author Medimegh
 */
public class serviceTherapie implements Itherapie{

    @Override
    public void addTherapie(Therapie t) {
        
           try {
            String requete  = "INSERT INTO `therapie` (`id`, `sujet`, `date`, `lieu`, `nombremax`, `idcoach`,`nombre_parti`) VALUES ('"+t.getId()+"', '"+t.getSujet()+"', '"+t.getDate()+"', '"+t.getLieu()+"', '"+t.getNombremax()+"', '"+t.getIdcoach()+"','"+t.getNombre_parti()+"')";
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
                T.setNombre_parti(rs.getInt(7));
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
                        T.setNombre_parti(rs.getInt(7));
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
                T.setNombre_parti(rs.getInt(7));

                myList.add(T);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;    }

    @Override
    public void approuverTherapie(Therapie t) {
        addTherapie(t);
        try {
            supprimerpropoTherapie(t.getId());
        } catch (Exception e) {
        }
        
        
    }

    @Override
    public ObservableList<Therapie> showpropTherapie()
    {
ObservableList<Therapie> myList = FXCollections.observableArrayList();
 try {

            String requete = "SELECT * FROM proptherapie";
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
                T.setNombre_parti(rs.getInt(7));
                myList.add(T);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } return myList;
        }

    

    @Override
    public void supprimerpropoTherapie(int id) {
try {
            String requete = "DELETE FROM proptherapie WHERE id = " + id +"";
            Statement st = MyConnection.getInstance().getCnx().createStatement() ;
            st.executeUpdate(requete);
            System.out.println("proposition supprimer");
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Therapie");
                alert.setHeaderText(null);
                alert.setContentText("proposition suppri");
                alert.showAndWait();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }

    @Override
    public void addpropTherapie(Therapie t) {

           try {
            String requete  = "INSERT INTO `proptherapie` (`id`, `sujet`, `date`, `lieu`, `nombremax`, `idcoach`,`nombre_parti`) VALUES ('"+t.getId()+"', '"+t.getSujet()+"', '"+t.getDate()+"', '"+t.getLieu()+"', '"+t.getNombremax()+"', '"+t.getIdcoach()+"','"+t.getNombre_parti()+"')";
            Statement st = MyConnection.getInstance().getCnx().createStatement() ;
            st.executeUpdate(requete);
            System.out.println("Proposition ajouté");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Proposition");
                alert.setHeaderText(null);
                alert.setContentText("Proposition ajouté");
                alert.showAndWait();
                
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());
        }    }
    
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
public void updatenumpartth(Therapie a){
    try {
         String requete = " UPDATE therapie SET nombre_parti=nombre_parti+1 WHERE id='"+a.getId()+"'" ;
            PreparedStatement pst= MyConnection.getInstance().getCnx().prepareStatement(requete);
             pst.executeUpdate();

    } 
catch (Exception e) {
    }}
    
    public void addclientth(Therapie a)
    {  try {
            String requete  = "INSERT INTO `participationtherapie` (`id_client`,`id_therapie`) VALUES (10,'"+a.getId()+"') ";
            Statement st = MyConnection.getInstance().getCnx().createStatement() ;
            st.executeUpdate(requete);
            System.out.println("Client ajouté");
            Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Client ACT");
                alert.setHeaderText(null);
                alert.setContentText("Client ajouté");
                alert.showAndWait();
                
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());
        }
    }
        

            public void jointh(Therapie a){
                updatenumpartth(a);
                if(a.getNombremax()==a.getNombre_parti())
                { Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Nombre max");
                alert.setHeaderText(null);
                alert.setContentText("FULL");
                alert.showAndWait(); }
                else
                 addclientth( a);
                
            
            }
    
 }
    
    
    

