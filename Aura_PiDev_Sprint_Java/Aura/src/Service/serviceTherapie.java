/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import utils.Connexion;
import Entities.Therapie;
import Interfaces.Itherapie;
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
            String requete  = "INSERT INTO `therapie` (`id`, `sujet`, `date`, `lieu`, `nombremax`, `idcoach`,`nombre_parti`) VALUES ('"+t.getId()+"', '"+t.getSujet()+"', '"+t.getDate()+"', '"+t.getLieu()+"', '"+t.getNombremax()+"', '"+t.getIdcoach().getId()+"','"+t.getNombre_parti()+"')";
            Statement st = Connexion.getInstance().getConnection().createStatement() ;
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
             ServiceCoach sc = new ServiceCoach();
        try {

            String requete = "SELECT * FROM therapie";
            Statement st = Connexion.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Therapie T = new Therapie();
                T.setId(rs.getInt(1));
                T.setSujet(rs.getString(2));
                T.setDate(rs.getString(3));
                T.setLieu(rs.getString(4));
                T.setNombremax(rs.getInt(5));
                T.setIdcoach(sc.load_data_modify(rs.getString(6)));
                T.setNombre_parti(rs.getInt(7));
                myList.add(T);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
public List<Therapie> showTherapies() {
       List<Therapie> myList = FXCollections.observableArrayList();
             ServiceCoach sc = new ServiceCoach();
        try {

            String requete = "SELECT * FROM therapie";
            Statement st = Connexion.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Therapie T = new Therapie();
                T.setId(rs.getInt(1));
                T.setSujet(rs.getString(2));
                T.setDate(rs.getString(3));
                T.setLieu(rs.getString(4));
                T.setNombremax(rs.getInt(5));
                T.setIdcoach(sc.load_data_modify(rs.getString(6)));
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
            Statement st = Connexion.getInstance().getConnection().createStatement() ;
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
            String requete = " UPDATE therapie SET date=?,lieu=?,nombremax=?,idcoach=? WHERE sujet=?" ;
            PreparedStatement pst= Connexion.getInstance().getConnection().prepareStatement(requete);
            pst.setString(1,t.getDate());
            pst.setString(2,t.getLieu());
            pst.setInt(3,t.getNombremax());
            pst.setString(4, t.getIdcoach().getId());
            pst.setString(5,t.getSujet());
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
       ServiceCoach sc = new ServiceCoach();
        try {

            String requete = "SELECT * FROM therapie  WHERE sujet='"+t+"' ORDER BY date  ";
            Statement st = Connexion.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Therapie T = new Therapie();
                T.setId(rs.getInt(1));
                T.setSujet(rs.getString(2));
                T.setDate(rs.getString(3));
                T.setLieu(rs.getString(4));
                T.setNombremax(rs.getInt(5));
                T.setIdcoach(sc.load_data_modify(rs.getString(6)));
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
 ServiceCoach sc = new ServiceCoach();
        try {

            String requete = "SELECT * FROM therapie  WHERE date='"+t+"'  ";
            Statement st = Connexion.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Therapie T = new Therapie();
                T.setId(rs.getInt(1));
                T.setSujet(rs.getString(2));
                T.setDate(rs.getString(3));
                T.setLieu(rs.getString(4));
                T.setNombremax(rs.getInt(5));
                T.setIdcoach(sc.load_data_modify(rs.getString(6))
);
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
 ServiceCoach sc = new ServiceCoach();

 try {

            String requete = "SELECT * FROM proptherapie";
            Statement st = Connexion.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Therapie T = new Therapie();
                T.setId(rs.getInt(1));
                T.setSujet(rs.getString(2));
                T.setDate(rs.getString(3));
                T.setLieu(rs.getString(4));
                T.setNombremax(rs.getInt(5));
                T.setIdcoach(sc.load_data_modify(rs.getString(6))
);
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
            Statement st = Connexion.getInstance().getConnection().createStatement() ;
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
            String requete  = "INSERT INTO `proptherapie` (`id`, `sujet`, `date`, `lieu`, `nombremax`, `idcoach`,`nombre_parti`) VALUES ('"+t.getId()+"', '"+t.getSujet()+"', '"+t.getDate()+"', '"+t.getLieu()+"', '"+t.getNombremax()+"', '"+t.getIdcoach().getId()+"','"+t.getNombre_parti()+"')";
            Statement st = Connexion.getInstance().getConnection().createStatement() ;
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
            Statement st = Connexion.getInstance().getConnection().createStatement() ;
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
            PreparedStatement pst= Connexion.getInstance().getConnection().prepareStatement(requete);
             pst.executeUpdate();

    } 
catch (Exception e) {
    }}
    
    public void addclientth(Therapie a,String idc)
    {  try {
            String requete  = "INSERT INTO `participationtherapie` (`id_client`,`id_therapie`) VALUES ('"+idc+"','"+a.getId()+"') ";
            Statement st = Connexion.getInstance().getConnection().createStatement() ;
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
        

            public void jointh(Therapie a,String idc){
               
                if(a.getNombremax()==a.getNombre_parti())
                { Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Nombre max");
                alert.setHeaderText(null);
                alert.setContentText("FULL");
                alert.showAndWait(); }
                else
                {addclientth(a,idc); updatenumpartth(a);}
                
            
            }
            public void updaterating(Therapie a,int rate,String idc){
    try {
         String requete = " UPDATE participationtherapie SET rating='"+rate+"' WHERE id_therapie='"+a.getId()+"' AND id_client='"+idc+"'" ;
            PreparedStatement pst= Connexion.getInstance().getConnection().prepareStatement(requete);
             pst.executeUpdate();
        System.out.println("done rate");
    } 
catch (Exception e) {
    }}
            
            
    public Therapie getAct(Integer i) {
     Therapie Ac=null;
      ServiceCoach sc = new ServiceCoach();
        try {

            
             String requete = "SELECT * FROM therapie";
            Statement st = Connexion.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Therapie T = new Therapie();
                T.setId(rs.getInt(1));
                T.setSujet(rs.getString(2));
                T.setDate(rs.getString(3));
                T.setLieu(rs.getString(4));
                T.setNombremax(rs.getInt(5));
                T.setIdcoach(sc.load_data_modify(rs.getString(6)));
                T.setNombre_parti(rs.getInt(7));
              Ac=T;}

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Ac;
    }
 }
    
    
    

