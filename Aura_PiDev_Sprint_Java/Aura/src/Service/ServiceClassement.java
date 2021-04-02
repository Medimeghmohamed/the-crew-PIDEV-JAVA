/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Client;
import Entities.classement;
import Inerfaces.IServiceClassement;
import utils.Connexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

/**
 *
 * @author NOUR
 */
public class ServiceClassement {
    
    Connection cnx;
    
    public ServiceClassement() {
        cnx = Connexion.getInstance().getConnection();
        
    }
    
    public void ajouterClassement(classement c) {
        //  c.setNb_points(0);

        try {
            if (cnx != null) {
                
                Statement stm;
                stm = Connexion
                        .getInstance()
                        .getConnection()
                        .createStatement();
                assert stm != null;
                
                String query = "INSERT INTO `ligne_classement`(`id`, `id_client`, `id_niveau`, `position`, `nb_points`) "
                        + "VALUES ( NULL,'"
                        + c.getClient() + "','"
                        + c.getNiveau() + "','"
                        + c.getPosition() + "','"
                        + c.getNb_points() + "')";
                stm.executeUpdate(query);
                System.out.println("ajout avec succes");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("CLASSEMENT");
                alert.setHeaderText(null);
                alert.setContentText("classement ajouté");
                alert.showAndWait();
            } else {
                System.out.println("cnx NULL");
            }
        } catch (SQLException ex) {
            
            Logger.getLogger(ServiceChallenge.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    /*  public classement getNomClient(classement cl){
        try {
            Statement stm=null;
            stm = Connexion.getInstance().getConnection().createStatement();
            String query2 = "SELECT * FROM `user` WHERE id='" +cl.getClient()+"' ";
            ResultSet rst2 = stm.executeQuery(query2);
            cl.setClient(rst2.getString("nom"));
             cl.setId(rst2.getInt(1));
              cl.setNiveau(rst2.getInt("id_niveau"));
                cl.setPosition(rst2.getInt("position"));
                cl.setNb_points(rst2.getInt("nb_points"));
        } catch (SQLException ex) {
            Logger.getLogger(ServiceClassement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cl;
    }*/
    public ObservableList<classement> afficherClassement() {
        Statement stm = null;
        ObservableList<classement> classements = FXCollections.observableArrayList();
        
        try {
            
            stm = Connexion.getInstance().getConnection().createStatement();
            
            String query = "SELECT * FROM `ligne_classement` ORDER BY position ";
            ResultSet rst = stm.executeQuery(query);
            
            while (rst.next()) {
                classement c = new classement();
                
                c.setId(rst.getInt(1));
                
                ServiceClient sca= new ServiceClient();
                String id=rst.getString("id_client");
                Client cl =sca.load_data_modify(id);
               c.setClient((cl.getNom() + " " + cl.getPrenom()));
                
                c.setNiveau(rst.getInt("id_niveau"));
                c.setPosition(rst.getInt("position"));
                c.setNb_points(rst.getInt("nb_points"));
                
                classements.add(c);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceChallenge.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return classements;
    }
    
    public classement recup_classement(String id_client, int id_niveau) {
        classement ch = new classement();
        
        try {
            Statement st = cnx.createStatement();
            String query = "select * FROM `ligne_classement` WHERE id_client='" + id_client + "' ";
            ResultSet rst = st.executeQuery(query);
            while (rst.next()) {
                
                ch.setId(rst.getInt(1));
                ch.setClient(rst.getString(2));
                ch.setNiveau(rst.getInt(3));
                ch.setPosition(rst.getInt(4));
                ch.setNb_points(rst.getInt(5));
                
            }
            
        } catch (SQLException ex) {
            System.out.println("erreur get IdOBJ pour suivi");
            System.out.println(ex);
        }
        
        return ch;
        
    }
    
    public void modifierClassement(classement c) {
        try {
            Statement st = cnx.createStatement();
            
            String query = "UPDATE  ligne_classement SET id_niveau= '" + c.getNiveau() + "', id_client = '" + c.getClient() + "', position = '" + c.getPosition() + "', nb_points = '" + c.getNb_points() + "'  WHERE id = " + c.getId() + "";
            st.executeUpdate(query);
            System.out.println("modification avec succes");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("CLASSEMENT");
            alert.setHeaderText(null);
            alert.setContentText("modification avec suucces");
            alert.showAndWait();
        } catch (SQLException ex) {
            System.out.println("erreur modifier classement");
            System.out.println(ex);
        }
    }
    
    public void supprimerClassement(int id) {

        //  int Id = Integer.parseInt(id);
        try {
            Statement st = cnx.createStatement();
            String query = "DELETE FROM `ligne_classement` WHERE id = " + id + "";
            st.executeUpdate(query);
            System.out.println("suppression avec succes");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("CLASSEMENT");
            alert.setHeaderText(null);
            alert.setContentText("suppression avec succes");
            alert.showAndWait();
        } catch (SQLException ex) {
            System.out.println("erreur supprimer classement");
            System.out.println(ex);
        }
    }
    
    public ObservableList<classement> trierClassement() {
        Statement stm = null;
        ObservableList<classement> classements = FXCollections.observableArrayList();
        
        try {
            
            stm = Connexion.getInstance().getConnection().createStatement();
            
            String query = "SELECT * FROM `ligne_classement` ORDER BY position  ";
            ResultSet rst = stm.executeQuery(query);
            
            while (rst.next()) {
                classement c = new classement();
                c.setId(rst.getInt(1));
                c.setClient(rst.getString("id_client"));
                c.setNiveau(rst.getInt("id_niveau"));
                c.setPosition(rst.getInt("position"));
                c.setNb_points(rst.getInt("nb_points"));
                
                classements.add(c);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceChallenge.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return classements;
        
    }
    
    public ObservableList<classement> RechercherClassement(String pos) {
        
        ObservableList<classement> Oclassement = FXCollections.observableArrayList();
        try {
            Statement stm = cnx.createStatement();
            String query = " select * from ligne_classement  WHERE  position='" + pos + "'";
            //" select * from niveau  WHERE titre = LIKE '%tr%'";

            ResultSet rst = stm.executeQuery(query);
            
            while (rst.next()) {
                classement nv = new classement();
                
                nv.setId(rst.getInt(1));
                nv.setClient(rst.getString(2));
                nv.setNiveau(rst.getInt(3));
                nv.setPosition(rst.getInt(4));
                nv.setNb_points(rst.getInt(5));
                
                Oclassement.add(nv);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceNiveau.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Oclassement;
    }
    
    public ObservableList<classement> RechercherClassement_client(String nom) {
        
        ObservableList<classement> Oclassement = FXCollections.observableArrayList();
        try {
            Statement stm = cnx.createStatement();
            String query = " select * from ligne_classement  WHERE  id_client LIKE '%" + nom + "%' ORDER BY position";
            
            ResultSet rst = stm.executeQuery(query);
            
            while (rst.next()) {
                classement nv = new classement();
                
                nv.setId(rst.getInt(1));
                nv.setClient(rst.getString(2));
                nv.setNiveau(rst.getInt(3));
                nv.setPosition(rst.getInt(4));
                nv.setNb_points(rst.getInt(5));
                
                Oclassement.add(nv);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceNiveau.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Oclassement;
    }
    
    public ObservableList<classement> afficherClassement_client() {
        Statement stm = null;
        ObservableList<classement> classements = FXCollections.observableArrayList();
        
        try {
            
            stm = Connexion.getInstance().getConnection().createStatement();
            
            String query = "SELECT * FROM `ligne_classement` ORDER  BY position ";
            ResultSet rst = stm.executeQuery(query);
            
            while (rst.next()) {
                classement c = new classement();
                c.setId(rst.getInt(1));
                
                ServiceClient sca= new ServiceClient();
                String id=rst.getString("id_client");
                Client cl =sca.load_data_modify(id);
               c.setClient((cl.getNom() + " " + cl.getPrenom()));
                c.setNiveau(rst.getInt("id_niveau"));
                c.setPosition(rst.getInt("position"));
                c.setNb_points(rst.getInt("nb_points"));
                
                classements.add(c);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceChallenge.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return classements;
    }

    // recup client dans l'integration 
    /// ajout dynamique du classement 
    public boolean verif_classement(String id) {
        Statement stm = null;
        
        ResultSet rst = null;
        
        try {
            
            stm = Connexion.getInstance().getConnection().createStatement();
            
            String query = "SELECT * FROM `ligne_classement` WHERE id_client='" + id + "'  ";
            
            rst = stm.executeQuery(query);
            
            if (rst.next()) {
                
                return true;
                
            }
            
        } catch (SQLException ex) {
            
            System.out.println(ex.getMessage());
            
        }
        
        return false;
    }
    
    public void ajouter_classement_dynamique(String id_client, int id_niveau) {

        // lazem ll classement yekhou ll id_client el id_niveu men aand ll participation 
        // wla nzid aala baadhou baaed nahi les doublons fel requete sql 
        // wakt eli tetzed paticipation yetzed classement ama lazem kol couple (id_client,id_niveau) ykoun unique eli heya deja mawjouda fe participation 
        Statement stm = null;
        
        try {
            
            stm = Connexion.getInstance().getConnection().createStatement();
            if (!verif_classement(id_client)) {
                
                classement c = new classement();
                String query = "INSERT INTO `ligne_classement`(`id`, `id_client`, `id_niveau`, `position`, `nb_points`) "
                        + "VALUES ( NULL,'"
                        + id_client + "','"
                        + id_niveau + "','"
                        + c.getPosition() + "','"
                        + c.getNb_points() + "')";
                stm.executeUpdate(query);
                System.out.println("ajout avec succes");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("CLASSEMENT");
                alert.setHeaderText(null);
                alert.setContentText("classement ajouté");
                alert.showAndWait();
                
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("CLASSEMENT");
                alert.setHeaderText(null);
                alert.setContentText("Votre nom existe dans le classement ");
                alert.showAndWait();
            }
            
            System.out.println("cnx NULL");
        } catch (SQLException ex) {
            
            Logger.getLogger(ServiceChallenge.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void position() {// wala nrodha void so tbadel fel bd kahaw 

        int pos = 0;
        Statement stm = null;
        
        ObservableList<classement> CL = FXCollections.observableArrayList();
        CL = recup_classement();
        classement currentTab = new classement();
        try {
            for (int i = 0; i < CL.size(); i++) {
                
                pos++;
                
                stm = Connexion.getInstance().getConnection().createStatement();
                //      System.out.println(pos);
                System.out.println(CL.get(i).getId());
                
                String query = "UPDATE ligne_classement SET  position ='" + pos + "' WHERE id='" + CL.get(i).getId() + "' ";
                stm.executeUpdate(query);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceClassement.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public ObservableList<classement> recup_classement() {
        
        Statement stm = null;
        String etat = null;
        ObservableList<Integer> ids = FXCollections.observableArrayList();
        ObservableList<classement> CL = FXCollections.observableArrayList();
        
        try {
            
            stm = Connexion.getInstance().getConnection().createStatement();
            
            String query = "SELECT * FROM `ligne_classement`  ORDER BY nb_points desc  ";
            ResultSet rst = stm.executeQuery(query);
            
            while (rst.next()) {
                classement cl = new classement();
                // System.out.println(rst.getInt("id"));

                cl.setId(rst.getInt("id"));
                cl.setClient(rst.getString("id_client"));
                cl.setNiveau(rst.getInt("id_niveau"));
                cl.setPosition(rst.getInt("position"));
                cl.setNb_points(rst.getInt("nb_points"));
                
                CL.add(cl);
            }
            System.out.println(CL);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceChallenge.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return CL;
        
    }
    
}
