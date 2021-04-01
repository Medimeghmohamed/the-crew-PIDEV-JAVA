/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Interfaces.Icommentaire;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import utils.Connexion;
import Entities.commentaire;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author akram
 */
public class serviceCommentaire implements Icommentaire {

    Connection cnx;

    public serviceCommentaire() {
        cnx = Connexion.getInstance().getConnection();
    }

    @Override
    public void ajouterc(commentaire c) {
        String datee = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
        try {
            if (cnx != null) {

                c.setId_article(3);
                c.setId_user("10");

                String requete = "INSERT INTO `commentaire` ( `id`,`id_user`, `id_article`, `commentaire`,`date`) VALUES ( '" + c.getId() + "','" + c.getId_user() + "', '" + c.getId_article() + "','" + c.getCommentaire() + "','" + datee + "') ";
                Statement st = Connexion.getInstance().getConnection().createStatement();
                assert st != null;
                st.executeUpdate(requete);
                System.out.println("ajouté");
            } else {
                System.out.println("non");
            }
        } catch (SQLException ex) {
            System.out.println("erreur  ");
            System.out.println(ex);
        }

    }

    @Override
    public String recup_commentaire(String com) {
        commentaire ch = new commentaire();

        try {
            Statement st = cnx.createStatement();

            String query = "select * FROM `commentaire` WHERE commentaire='" + com + "'";
            ResultSet rst = st.executeQuery(query);
            if (rst.next()) {
                ch.setCommentaire(rst.getString("commentaire"));
            }

        } catch (SQLException ex) {
            System.out.println("erreur get IdOBJ pour suivi");
            System.out.println(ex);
        }

        return ch.getCommentaire();

    }

    @Override
    public ObservableList<commentaire> trier() {
        Statement stm = null;
        ObservableList<commentaire> commentaires = FXCollections.observableArrayList();

        try {

            //try {
            stm = Connexion.getInstance().getConnection().createStatement();

            String query = "SELECT * FROM commentaire ORDER BY commentaire ";
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                commentaire A = new commentaire();

                A.setId(rs.getInt(1));
                A.setId_user(rs.getString(2));
                A.setCommentaire(rs.getString(4));

                commentaires.add(A);
            }
            /* } catch (SQLException ex) {
            System.out.println(ex.getMessage());*/

            //}
        } catch (SQLException ex) {
            Logger.getLogger(serviceArticle.class.getName()).log(Level.SEVERE, null, ex);
        }

        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return commentaires;
    }

    @Override
    public void supprimerc(commentaire com) {

        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try {
            Statement st = cnx.createStatement();
            String query = "DELETE FROM commentaire WHERE id = '" + com.getId() + "' ";
            st.executeUpdate(query);
            System.out.println("suppression avec succes");
        } catch (SQLException ex) {
            System.out.println("erreur supprimer ");
            System.out.println(ex);
        }

    }

    /*  public void pdfto() throws IOException{
      

        File f = new File("C:\\Users\\akram\\Desktop\\unix\\Cron&At.pdf");
PDDocument document = PDDocument.load(f);
   PDFRenderer pdfRenderer = new PDFRenderer(document);
            for (int page = 0; page < document.getNumberOfPages(); ++page)
            {
                        
            
                BufferedImage bim = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);
                String fileName = "C:\\Users\\akram\\Desktop\\unix\\"+ "image-" + page + ".png";
                ImageIOUtil.writeImage(bim, fileName, 300);
            
            document.close();

}
  
    }
     */
    public void modifier(commentaire c) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try {
            Statement st = cnx.createStatement();

            String query = "UPDATE  commentaire SET commentaire = '" + c.getCommentaire() + "'  WHERE id_user = '" + c.getId_user() + "' ";
            st.executeUpdate(query);
            System.out.println("modification avec succes");
        } catch (SQLException ex) {
            System.out.println("erreur ");
            System.out.println(ex);
        }
    }

    /*    public ObservableList<String> showCommentaire_combobox() {
        Statement stm = null;
        ObservableList<String> articles = FXCollections.observableArrayList();
        

        try {

            //try {
            stm = MaConnexion.getInstance().getCnx().createStatement();

            String query = "SELECT idc FROM commentaire ";
            ResultSet rs = stm.executeQuery(query);
            
            while (rs.next()) {

                articles.add(rs.getString("idc"));
            }
            /* } catch (SQLException ex) {
            System.out.println(ex.getMessage());*/
    //}
    /* } catch (SQLException ex) {
            Logger.getLogger(serviceCommentaire.class.getName()).log(Level.SEVERE, null, ex);
        }

        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return articles;
    }*/
    @Override
    public ObservableList<commentaire> show(String id) {
        Statement stm = null;
        ObservableList<commentaire> commentaires = FXCollections.observableArrayList();

        try {

            //try {
            stm = Connexion.getInstance().getConnection().createStatement();

            String query = "SELECT * FROM commentaire  ";
            ResultSet rs = stm.executeQuery(query);

            while (rs.next()) {

                commentaire A = new commentaire();

                A.setId(rs.getInt(1));
                A.setId_user(rs.getString(2));
                A.setCommentaire(rs.getString(4));

                commentaires.add(A);
            }
            /* } catch (SQLException ex) {
            System.out.println(ex.getMessage());*/

            //}
        } catch (SQLException ex) {
            Logger.getLogger(serviceArticle.class.getName()).log(Level.SEVERE, null, ex);
        }

        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return commentaires;
    }

}
