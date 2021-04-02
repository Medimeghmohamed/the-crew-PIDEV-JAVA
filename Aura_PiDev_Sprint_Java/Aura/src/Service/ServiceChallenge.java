/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.challenge;
import Entities.classement;
import Entities.participation_challenge;
import Inerfaces.IServiceChallege;
import utils.Connexion;
import java.sql.Connection;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author NOUR
 */
public class ServiceChallenge implements IServiceChallege {

    Connection cnx;

    public ServiceChallenge() {
        cnx = Connexion.getInstance().getConnection();

    }

    @Override
    public void ajouterChallenge(challenge c) {

        c.setNb_participants(0);
        c.setImg("hello");

        try {
            if (cnx != null) {

                Statement stm;
                stm = Connexion
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

            stm = Connexion.getInstance().getConnection().createStatement();

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

            stm = Connexion.getInstance().getConnection().createStatement();

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
            Statement st = Connexion.getInstance().getConnection().createStatement();
            String query = "UPDATE  challenge SET  type = '" + c.getType() + "' WHERE id = " + c.getId() + "  ";

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
        Statement st = null;

        try {
            st = cnx.createStatement();
            String query = "select * FROM `challenge`  WHERE titre='" + tr + "'";
            ResultSet rst = st.executeQuery(query);
            while (rst.next()) {
                //challenge c = new challenge();
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

            stm = Connexion.getInstance().getConnection().createStatement();

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
        // c.setEtat("joined");
        int id_ch = c.getId();
        int nb = c.getNb_participants() + 1;

        try {
            String etat = "joined";

            Statement st = cnx.createStatement();
            String query = "INSERT INTO `participation_challenge`(`id`, `id_challenge`, `id_client`,`etat`) " + "VALUES ( NULL ,'" + id_ch + "','" + id + "','" + etat + "')";
            st.executeUpdate(query);
            Statement st2 = cnx.createStatement();
            String query2 = "UPDATE  challenge SET   nb_participants = '" + nb + "' WHERE id = '" + id_ch + "' ";
            st2.executeUpdate(query2);
            /* Statement st3 = cnx.createStatement();
            String query3 = "INSERT INTO `ligne_challenge`(`id`, `id_challenge`, `etat`) " + "VALUES ( NULL ,'" + id_ch + "','" + c.getEtat() + "')";

            st3.executeUpdate(query3);*/

            System.out.println("you joined the challenge");

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("challenge");
            alert.setHeaderText(null);
            alert.setContentText("challenge rejoint");
            alert.showAndWait();
        } catch (SQLException ex) {
            System.out.println("erreur rejoindre le challenge");
            System.out.println(ex);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("challenge");
            alert.setHeaderText(null);
            alert.setContentText("challenge déjà rejoint ");
            alert.showAndWait();
        }

    }

    @Override
    public ObservableList<challenge> afficherChallenge_valide() {
        Statement stm = null;
        ObservableList<challenge> challenges = FXCollections.observableArrayList();
        String s = "valide";

        try {

            stm = Connexion.getInstance().getConnection().createStatement();

            String query = "SELECT * FROM `challenge`  WHERE type='" + s + "'  ";
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

            stm = Connexion.getInstance().getConnection().createStatement();

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
        ObservableList<Integer> ids = FXCollections.observableArrayList();

        try {

            stm = Connexion.getInstance().getConnection().createStatement();

            String query = "SELECT * FROM `participation_challenge`  WHERE id_client='" + id + "'  ";
            ResultSet rst = stm.executeQuery(query);

            while (rst.next()) {

                int id_challenge;
                String etat = rst.getString("etat");
                id_challenge = Integer.parseInt(rst.getString("id_challenge"));

                //ids.add(id_challenge);
                System.out.print(id_challenge);
                try {
                    stm2 = Connexion.getInstance().getConnection().createStatement();
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

    public participation_challenge recup_participation(int id_challenge, String id_client) {
        participation_challenge pc = new participation_challenge();

        return pc;
    }

    @Override
    public void challenge_done(challenge c, String id_client) {

        //  c.setEtat("joined");
        int id_ch = c.getId();
        int nb = c.getNb_participants() - 1;
        //int nb = c.getNb_points()+25;
        challenge ch = recup_challenge(id_ch);
        participation_challenge pc = new participation_challenge();
        //int nb_participants=ch.getNb_participants()-1;
        ServiceParticipationChallenge spc = new ServiceParticipationChallenge();
        pc = spc.verif_participation(id_client, id_ch);
        ServiceClassement sc = new ServiceClassement();
        classement cl = new classement();
        cl = sc.recup_classement(id_client, c.getNiveau());
        System.out.println(cl);
        int pnt = cl.getNb_points() + 25;

        try {

            if (pc.getId() != 0) {
                Statement st = Connexion.getInstance().getConnection().createStatement();
                String query = "DELETE FROM `participation_challenge` WHERE id_challenge ='" + pc.getId_challenge() + "' AND id_client = '" + pc.getId_client() + "' ";
                st.executeUpdate(query);

                Statement st2 = Connexion.getInstance().getConnection().createStatement();
                String query2 = "UPDATE  ligne_classement SET   nb_points = '" + pnt + "' WHERE id = '" + cl.getId() + "' ";
                st2.executeUpdate(query2);

                System.out.println("Félicitations le challenge est fini  ");

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("challenge");
                alert.setHeaderText(null);
                alert.setContentText("Félicitations le challenge est fini");
                alert.showAndWait();

            } else {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("challenge");
                alert.setHeaderText(null);
                alert.setContentText("Verfifier que le challenge est rejoint");
                alert.showAndWait();
            };

        } catch (SQLException ex) {
            System.out.println("ooops");
            System.out.println(ex);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("challenge");
            alert.setHeaderText(null);
            alert.setContentText("erreur finir challenge ");
            alert.showAndWait();
        }
    }

    public void verif_date_challenge() {
        try {

            LocalDate date = LocalDate.now();
            Statement st = cnx.createStatement();
            String query = "DELETE FROM `challenge` WHERE date_fin < '" + date + "'";
            st.executeUpdate(query);
            System.out.println("suppression avec succes");

        } catch (SQLException ex) {
            System.out.println("erreur supprimer challenge");
            System.out.println(ex);
        }

    }

    public void verif_date_participation() {
        try {

            LocalDate date = LocalDate.now();
            Statement st = cnx.createStatement();

            String query = "DELETE  FROM participation_challenge   WHERE id_challenge IN(SELECT id FROM challenge  WHERE  date_fin <'" + date + "') ";
            st.executeUpdate(query);
            System.out.println("1111 non ");

            String query2 = " DELETE  FROM challenge WHERE date_fin < '" + date + "'";
            st.executeUpdate(query2);
            System.out.println("2222222222");
            System.out.println("suppression avec succes");

        } catch (SQLException ex) {
            System.out.println("erreur supprimer challenge");
            System.out.println(ex);
        }

    }

    //+++++++++++++++++++++++++++++
    public ObservableList<challenge> verif_date_participation_reminder(String id) {

        ObservableList<challenge> challenges = FXCollections.observableArrayList();
        try {

            LocalDate date = LocalDate.now();
            System.out.println(date);
            Statement st = cnx.createStatement();
          // String query = "SELECT *  FROM challenge   WHERE id IN(SELECT id_challenge FROM participation_challenge WHERE id_client='" + id + "') AND ('"+date+"' BETWEEN date_debut AND date_fin) ";
                       String query = "SELECT *  FROM challenge c   WHERE id IN(SELECT id_challenge FROM participation_challenge WHERE id_client='"+id+"') AND '"+date+"' BETWEEN c.date_debut AND c.date_fin";



           ResultSet rst = st.executeQuery(query);
            while (rst.next()) {
                challenge c = new challenge();
                c.setId(rst.getInt("id"));
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

            System.out.println(ex);
        }

        return challenges;

    }

    //++++++++++++++++++++++++++++++++
}
