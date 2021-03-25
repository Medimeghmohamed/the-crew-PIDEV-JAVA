/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.article;
import interfaces.Icommentaire;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import Utils.MaConnexion;
import entities.commentaire;
import java.sql.ResultSet;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.control.Pagination;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
 import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
        cnx = MaConnexion.getInstance().getCnx();
    }

    @Override
    public void ajouterc(commentaire c) {

        try {
            if (cnx != null) {
               
                c.setId_article(3);

                String requete = "INSERT INTO `commentaire` ( `idc`,`id_user`, `id_article`, `commentaire`,`date`) VALUES ( '" + c.getIdc() + "','" + c.getId_user() + "', '" + c.getId_article() + "','" + c.getCommentaire() + "','" + c.getDate() + "') ";
                Statement st = MaConnexion.getInstance().getCnx().createStatement();
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
    public String recup_commentaire(String com){
        commentaire ch = new commentaire();
        
          try {
            Statement st = cnx.createStatement();
            
            String query = "select * FROM `commentaire` WHERE commentaire='" + com + "'";
            ResultSet rst = st.executeQuery(query);
            if(rst.next()){
   ch.setCommentaire(rst.getString("commentaire"));
}
            
                 
            
        } catch (SQLException ex) {
            System.out.println("erreur get IdOBJ pour suivi");
            System.out.println(ex);
        }
        
            return ch.getCommentaire();
            
    }
    
    
    
   @Override
    public void supprimerc(String com) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       
       
        try {
            Statement st = cnx.createStatement();
           String query = "DELETE FROM `commentaire` WHERE commentaire = " + com + "";
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
           
            String query = "UPDATE  commentaire SET commentaire = '" + c.getCommentaire()+ "'  WHERE idc = " + c.getIdc()+ "";
            st.executeUpdate(query);
            System.out.println("modification avec succes");
        } catch (SQLException ex) {
            System.out.println("erreur ");
            System.out.println(ex);
        }
    }
   
    public ObservableList<String> showCommentaire_combobox() {
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
        } catch (SQLException ex) {
            Logger.getLogger(serviceCommentaire.class.getName()).log(Level.SEVERE, null, ex);
        }

        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return articles;
    }

}
