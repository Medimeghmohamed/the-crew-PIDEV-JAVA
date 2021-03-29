/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.challenge;
import entities.classement;
import entities.ligne_challenge;
import entities.niveau;
import entities.participation_challenge;
import services.IServiceChallege;
import utils.Myconnexion;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import static java.util.Collections.list;
import java.util.Date;
import java.util.ListIterator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Tab;

/**
 *
 * @author NOUR
 */
public class ServiceChallenge implements IServiceChallege {

    Connection cnx;

    public ServiceChallenge() {
        cnx = Myconnexion.getInstance().getConnection();

    }

    @Override
    public void ajouterChallenge(challenge c) {

        c.setNb_participants(0);
        c.setImg("hello");

        try {
            if (cnx != null) {

                Statement stm;
                stm = Myconnexion
                        .getInstance()
                        .getConnection()
                        .createStatement();
                assert stm != null;

                String query = "INSERT INTO `challenge`(`id`, `titre`, `type`, `description`, `img`, `date_debut`,`date_fin`, `nb_participants`, `id_niveau`) "
                        + "VALUES ( NULL,'"
                        + c.getTitre() + "','"
                        + c.getType() + "','"
                        + c.getDescription() + "','"
                        + c.getImg() + "','"
                        + c.getDate_debut() + "','"
                        + c.getDate_fin() + "','"
                        + c.getNb_participants() + "','"
                    
                        + c.getNiveau() + "')";
                stm.executeUpdate(query);
                System.out.println("ajout avec succes");

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("challenge");
                alert.setHeaderText(null);
                alert.setContentText("Proposition d'un challenge ajouté");
                alert.showAndWait();
            } else {
                System.out.println("cnx NULL");
            }
        } catch (SQLException ex) {

            Logger.getLogger(ServiceChallenge.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public challenge recup_challenge(int id) {
        challenge ch = new challenge();

        try {
            Statement st = cnx.createStatement();
            String query = "select * FROM `challenge` WHERE id='" + id + "'";
            ResultSet rst = st.executeQuery(query);
            while (rst.next()) {

                ch.setId(rst.getInt(1));
                ch.setTitre(rst.getString(2));
                ch.setType(rst.getString(3));
                ch.setDescription(rst.getString(4));
                ch.setImg(rst.getString(5));
                ch.setDate_debut(rst.getDate(6));
                ch.setDate_fin(rst.getDate(7));
                ch.setNb_participants(rst.getInt(8));
              
                System.out.println("hhhhhh");
                ch.setNiveau(rst.getInt(9));

            }

        } catch (SQLException ex) {
            System.out.println("erreur get IdOBJ pour suivi");
            System.out.println(ex);
        }

        return ch;

    }

    @Override
    public ObservableList<challenge> afficherChallenge() {
        Statement stm = null;
        ObservableList<challenge> challenges = FXCollections.observableArrayList();

        try {

            stm = Myconnexion.getInstance().getConnection().createStatement();

            String query = "SELECT * FROM `challenge` ORDER BY id_niveau ";
            ResultSet rst = stm.executeQuery(query);

            while (rst.next()) {
                challenge c = new challenge();
                c.setId(rst.getInt(1));
                c.setTitre(rst.getString("titre"));
                c.setType(rst.getString("type"));
                c.setDescription(rst.getString("description"));
                // c.setImg(rst.getString(5));
                c.setDate_debut(rst.getDate("date_debut"));
                c.setDate_fin(rst.getDate("date_fin"));
                c.setNb_participants(rst.getInt("nb_participants"));
               
                c.setNiveau(rst.getInt("id_niveau"));

                challenges.add(c);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceChallenge.class.getName()).log(Level.SEVERE, null, ex);
        }

        return challenges;
    }

    @Override
    public void modifierChallenge(challenge c) {
        try {
            Statement st = cnx.createStatement();

            String query = "UPDATE  challenge SET titre= '" + c.getTitre() + "', type = '" + c.getType() + "', description = '" + c.getDescription() + "', date_debut = '" + c.getDate_debut() + "', date_fin = '" + c.getDate_fin() + "', id_niveau = '" + c.getNiveau() + "'  WHERE id = " + c.getId() + "";
            st.executeUpdate(query);
            System.out.println("modification avec succes");
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("challenge");
            alert.setHeaderText(null);
            alert.setContentText("modification avec succes");
            alert.showAndWait();
        } catch (SQLException ex) {
            System.out.println("erreur modifier challenge");
            System.out.println(ex);
        }
    }

    @Override
    public void supprimerChallenge(String tr) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        // int Id = Integer.parseInt(id);
        try {
            Statement st = cnx.createStatement();
            String query = "DELETE FROM `challenge` WHERE titre ='" + tr + "'";
            st.executeUpdate(query);
            System.out.println("suppression avec succes");
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("challenge");
            alert.setHeaderText(null);
            alert.setContentText("suppression avec succes");
            alert.showAndWait();
        } catch (SQLException ex) {
            System.out.println("erreur supprimer challenge");
            System.out.println(ex);
        }
    }

    /**
     *
     * @return
     */
    @Override
    public ObservableList<challenge> trierChallenge() {
        Statement stm = null;
        ObservableList<challenge> challenges = FXCollections.observableArrayList();

        try {

            stm = Myconnexion.getInstance().getConnection().createStatement();

            String query = "SELECT * FROM `challenge` ORDER BY date_debut";
            ResultSet rst = stm.executeQuery(query);

            while (rst.next()) {
                challenge c = new challenge();
                c.setId(rst.getInt(1));
                c.setTitre(rst.getString("titre"));
                c.setType(rst.getString("type"));
                c.setDescription(rst.getString("description"));
                // c.setImg(rst.getString(5));
                c.setDate_debut(rst.getDate("date_debut"));
                c.setDate_fin(rst.getDate("date_fin"));
                c.setNb_participants(rst.getInt("nb_participants"));
               
                c.setNiveau(rst.getInt("id_niveau"));

                challenges.add(c);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceChallenge.class.getName()).log(Level.SEVERE, null, ex);
        }

        return challenges;

    }

    @Override
    public ObservableList<challenge> RechercherChallenge(String tr) {
        ObservableList<challenge> Ochallenge = FXCollections.observableArrayList();
        try {
            Statement stm = cnx.createStatement();
            String query = " select * from challenge  WHERE  titre='" + tr + "'";

            ResultSet rst = stm.executeQuery(query);

            while (rst.next()) {
                challenge ch = new challenge();

                ch.setId(rst.getInt(1));
                ch.setTitre(rst.getString(2));
                ch.setType(rst.getString(3));
                ch.setDescription(rst.getString(4));
                ch.setImg(rst.getString(5));
                ch.setDate_debut(rst.getDate(6));
                ch.setDate_fin(rst.getDate(7));
                ch.setNb_participants(rst.getInt(8));
               

                ch.setNiveau(rst.getInt(9));
                Ochallenge.add(ch);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceNiveau.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Ochallenge;
    }

    @Override
    public void confirmer_challenge(challenge c) {

        c.setType("valide");
        try {
            Statement st = cnx.createStatement();
            String query = "UPDATE  challenge SET  type = '" + c.getType() + "' WHERE id = " + c.getId() + "";

            st.executeUpdate(query);

            System.out.println("modification avec succes");

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("challenge");
            alert.setHeaderText(null);
            alert.setContentText("modification avec succes");
            alert.showAndWait();
        } catch (SQLException ex) {
            System.out.println("erreur modifier challenge");
            System.out.println(ex);
        }

    }

    public challenge recup_challenge_titre(String tr) {
        challenge c = new challenge();

        try {
            Statement st = cnx.createStatement();
            String query = "select * FROM `challenge` WHERE titre='" + tr + "'";
            ResultSet rst = st.executeQuery(query);
            while (rst.next()) {

                c.setId(rst.getInt("id"));
                c.setTitre(rst.getString("titre"));
                c.setType(rst.getString("type"));
                c.setDescription(rst.getString("description"));
                // c.setImg(rst.getString(5));
                c.setDate_debut(rst.getDate("date_debut"));
                c.setDate_fin(rst.getDate("date_fin"));
                c.setNb_participants(rst.getInt("nb_participants"));
              
                c.setNiveau(rst.getInt("id_niveau"));

            }

        } catch (SQLException ex) {
            System.out.println("erreur get IdOBJ pour suivi");
            System.out.println(ex);
        }

        return c;

    }

    @Override
    public ObservableList<challenge> afficherChallenge_client() {
        Statement stm = null;
        ObservableList<challenge> challenges = FXCollections.observableArrayList();
        String s = "valide";

        try {

            stm = Myconnexion.getInstance().getConnection().createStatement();

            String query = "SELECT * FROM `challenge`  WHERE type='" + s + "' ORDER BY id_niveau ";
            ResultSet rst = stm.executeQuery(query);

            while (rst.next()) {
                challenge c = new challenge();
                c.setId(rst.getInt(1));
                c.setTitre(rst.getString("titre"));
                c.setType(rst.getString("type"));
                c.setDescription(rst.getString("description"));
                // c.setImg(rst.getString(5));
                c.setDate_debut(rst.getDate("date_debut"));
                c.setDate_fin(rst.getDate("date_fin"));
                c.setNb_participants(rst.getInt("nb_participants"));
               
                c.setNiveau(rst.getInt("id_niveau"));

                challenges.add(c);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceChallenge.class.getName()).log(Level.SEVERE, null, ex);
        }

        return challenges;

    }

    @Override
    public void rejoindre_challenge(challenge c, String id) {
        c.setEtat("joined");
        int id_ch = c.getId();
        int nb = c.getNb_participants() + 1;

        try {
            String etat ="joined";

            Statement st = cnx.createStatement();
            String query = "INSERT INTO `participation_challenge`(`id`, `id_challenge`, `id_client`,`etat`) " + "VALUES ( NULL ,'" + id_ch + "','" + id + "','"+etat+"')";
            st.executeUpdate(query);
            Statement st2 = cnx.createStatement();
            String query2 = "UPDATE  challenge SET   nb_participants = '" + nb + "' WHERE id = " + id_ch + "";
            st2.executeUpdate(query2);
           /* Statement st3 = cnx.createStatement();
            String query3 = "INSERT INTO `ligne_challenge`(`id`, `id_challenge`, `etat`) " + "VALUES ( NULL ,'" + id_ch + "','" + c.getEtat() + "')";

            st3.executeUpdate(query3);*/

            System.out.println("you joined the challenge");

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("challenge");
            alert.setHeaderText(null);
            alert.setContentText("you joined the challenge");
            alert.showAndWait();
        } catch (SQLException ex) {
            System.out.println("erreur rejoindre le challenge");
            System.out.println(ex);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("challenge");
            alert.setHeaderText(null);
            alert.setContentText("you already joined the challenge");
            alert.showAndWait();
        }

    }

    @Override
    public ObservableList<challenge> afficherChallenge_valide() {
        Statement stm = null;
        ObservableList<challenge> challenges = FXCollections.observableArrayList();
        String s = "valide";

        try {

            stm = Myconnexion.getInstance().getConnection().createStatement();

            String query = "SELECT * FROM `challenge`  WHERE type='" + s + "' ORDER BY id_niveau ";
            ResultSet rst = stm.executeQuery(query);

            while (rst.next()) {
                challenge c = new challenge();
                c.setId(rst.getInt(1));
                c.setTitre(rst.getString("titre"));
                c.setType(rst.getString("type"));
                c.setDescription(rst.getString("description"));
                // c.setImg(rst.getString(5));
                c.setDate_debut(rst.getDate("date_debut"));
                c.setDate_fin(rst.getDate("date_fin"));
                c.setNb_participants(rst.getInt("nb_participants"));
               
                c.setNiveau(rst.getInt("id_niveau"));

                challenges.add(c);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceChallenge.class.getName()).log(Level.SEVERE, null, ex);
        }

        return challenges;

    }

   
    
    
    public ObservableList<participation_challenge> recup_participation(String id_client) {

        Statement stm = null;
        String etat = null;
        ObservableList<Integer> ids = FXCollections.observableArrayList();
        ObservableList<participation_challenge> P_challenge = FXCollections.observableArrayList();

        try {

            stm = Myconnexion.getInstance().getConnection().createStatement();

            String query = "SELECT * FROM `participation_challenge`  WHERE id_client='" + id_client + "'  ";
            ResultSet rst = stm.executeQuery(query);

            while (rst.next()) {
                participation_challenge p_c = new participation_challenge();

                p_c.setId(rst.getInt("id"));
                p_c.setId_challenge(rst.getInt("id_challenge"));
                p_c.setId_client(rst.getString("id_client"));
                p_c.setEtat(rst.getString("etat"));

                P_challenge.add(p_c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceChallenge.class.getName()).log(Level.SEVERE, null, ex);
        }
        return P_challenge;

    }

    
    
       public ObservableList<challenge> verif_challenge2(String id) {
        Statement stm = null;
          Statement stm2 = null;

        participation_challenge p_c = new participation_challenge();
        ObservableList<challenge> challenges = FXCollections.observableArrayList();
        ObservableList<ligne_challenge> l_challenges = FXCollections.observableArrayList();
        ObservableList<Integer> ids = FXCollections.observableArrayList();

        try {

            stm = Myconnexion.getInstance().getConnection().createStatement();

            String query = "SELECT * FROM `participation_challenge`  WHERE id_client='" + id + "'  ";
            ResultSet rst = stm.executeQuery(query);

            while (rst.next()) {

                int id_challenge;
                String etat = rst.getString("etat");
                id_challenge = Integer.parseInt(rst.getString("id_challenge"));

                //ids.add(id_challenge);
                System.out.print(id_challenge);
                  try {
                        stm2 = Myconnexion.getInstance().getConnection().createStatement();
                    String query2 = "SELECT * FROM `challenge`  WHERE id = '" + id_challenge + "'";
                    ResultSet rst2 = stm2.executeQuery(query2);
                    while (rst2.next()) {
                        challenge c = new challenge();
                        c.setId(rst2.getInt("id"));
                        c.setTitre(rst2.getString("titre"));
                        c.setType(rst2.getString("type"));
                        c.setDescription(rst2.getString("description"));
                        // c.setImg(rst.getString(5));
                        c.setDate_debut(rst2.getDate("date_debut"));
                        c.setDate_fin(rst2.getDate("date_fin"));
                        c.setNb_participants(rst2.getInt("nb_participants"));
                      
                        c.setNiveau(rst2.getInt("id_niveau"));

                        challenges.add(c);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ServiceChallenge.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }

            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceChallenge.class.getName()).log(Level.SEVERE, null, ex);
        }
        return challenges;

    }
    
    
    
    

    /* private void displayAppointmentNotification() {
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");

    Timer timer = new Timer();

    TimerTask task;
        task = new TimerTask() {
            
            @Override
            public void run() {
                Statement stm = null;
                try {
                    stm = Myconnexion.getInstance().getConnection().createStatement();
                    String query = "SELECT * FROM `reminder`";
                    ResultSet rst = stm.executeQuery(query);
                    
                    
                    
                    while (rst.next()) {
                        
                        if (rst.getString("Reminder").contentEquals(dateFormat.format(new Date()))) {
                            reminder noti = reminder.create();
                            noti.text("you  have a challenge named  " + rst.getString("id_challenge")+ "  ");
                            noti.title("Reminder");
                            noti.hideAfter(Duration.seconds(30));
                            noti.position(Pos.BOTTOM_RIGHT);
                            
                            Platform.runLater(() -> {
                                noti.show();
                            });
                            
                            //Change The Appointment Status to Done
                            ps = HospitalDB.getCon().prepareStatement("Update Appointment Set Status=? Where Patient=? and Doctor=?");
                            ps.setString(1, "Done");
                            ps.setString(2, rs.getString("Patient"));
                            ps.setString(3, rs.getString("Doctor"));
                            ps.executeUpdate();
                            
                            populateTable();
                        }
                        
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
        };
    timer.schedule(task, 0, 1 * 1000);
}*/
}
