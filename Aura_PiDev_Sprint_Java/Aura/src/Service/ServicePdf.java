/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Admin;
import Entities.Client;
import Entities.Coach;
import Entities.Objectif;
import Entities.ObjectifPred;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

/**
 *
 * @author SeifBS
 */
public class ServicePdf {

    public void liste_admins(String id) throws FileNotFoundException, DocumentException {
        ServiceAdmin sa = new ServiceAdmin();
        Admin a;
        a = sa.load_data_modify(id);
        String message = "M/MME :" + a.getNom() + " " + a.getPrenom();

        String file_name = "src/PDF/liste_admins.pdf";
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(file_name));
        document.open();
        Paragraph para = new Paragraph(message);
        document.add(para);
        List<Admin> liste_admins = sa.afficherAdmin();
        PdfPTable table = new PdfPTable(2);

        PdfPCell cl = new PdfPCell(new Phrase("Nom"));
        table.addCell(cl);
        PdfPCell cl1 = new PdfPCell(new Phrase("Prenom"));
        table.addCell(cl1);

        table.setHeaderRows(1);
        document.add(table);

        int i = 0;
        for (i = 0; i < liste_admins.size(); i++) {
            table.addCell(liste_admins.get(i).getNom());
            table.addCell(liste_admins.get(i).getPrenom());

        }
        document.add(table);

        document.close();

    }

    public void liste_coachs(String id) throws FileNotFoundException, DocumentException {

        ServiceCoach sc = new ServiceCoach();
        Coach c;
        c = sc.load_data_modify(id);
        String message = "M/MME :" + c.getNom() + " " + c.getPrenom();

        String file_name = "src/PDF/liste_coachs.pdf";
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(file_name));
        document.open();
        Paragraph para = new Paragraph(message);

        document.add(para);
        List<Coach> liste_coachs = sc.afficherCoach_Oui();

        PdfPTable table = new PdfPTable(3);

        PdfPCell cl = new PdfPCell(new Phrase("Nom"));
        table.addCell(cl);
        PdfPCell cl1 = new PdfPCell(new Phrase("Prenom"));
        table.addCell(cl1);
        PdfPCell cl2 = new PdfPCell(new Phrase("Specialite"));
        table.addCell(cl2);

        table.setHeaderRows(1);
        document.add(table);

        int i = 0;
        for (i = 0; i < liste_coachs.size(); i++) {
            table.addCell(liste_coachs.get(i).getNom());
            table.addCell(liste_coachs.get(i).getPrenom());
            table.addCell(liste_coachs.get(i).getSpecialite());

        }
        document.add(table);

        document.close();

    }

    public void liste_clients(String id) throws FileNotFoundException, DocumentException {

        ServiceClient sc = new ServiceClient();
        Client c;
        c = sc.load_data_modify(id);
        String message = "M/MME :" + c.getNom() + " " + c.getPrenom();

        String file_name = "src/PDF/liste_clients.pdf";
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(file_name));
        document.open();
        Paragraph para = new Paragraph(message);
        document.add(para);
        List<Client> liste_clients = sc.afficherClient();
        PdfPTable table = new PdfPTable(3);

        PdfPCell cl = new PdfPCell(new Phrase("Nom"));
        table.addCell(cl);
        PdfPCell cl1 = new PdfPCell(new Phrase("Prenom"));
        table.addCell(cl1);
        PdfPCell cl2 = new PdfPCell(new Phrase("Adresse"));
        table.addCell(cl2);
        table.setHeaderRows(1);
        document.add(table);

        int i = 0;
        for (i = 0; i < liste_clients.size(); i++) {
            table.addCell(liste_clients.get(i).getNom());
            table.addCell(liste_clients.get(i).getPrenom());
            table.addCell(liste_clients.get(i).getAdresse());

        }
        document.add(table);

        document.close();

    }

    public void liste_objectifs(String id) throws FileNotFoundException, DocumentException {

        ServiceClient sc = new ServiceClient();
        ServiceObjectif so = new ServiceObjectif();
        Client c;
        c = sc.load_data_modify(id);
        String message = "M/MME :" + c.getNom() + " " + c.getPrenom() + ", \n Voici vote liste d'objectifs \n\n";

        String file_name = "src/PDF/liste_objectifs.pdf";
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(file_name));
        document.open();
        Paragraph para = new Paragraph(message);
        document.add(para);
        List<Objectif> liste_objectifs = so.afficherObjectifsItems(c.getId());
        PdfPTable table = new PdfPTable(4);

        PdfPCell cl = new PdfPCell(new Phrase("Description"));
        table.addCell(cl);
        PdfPCell cl1 = new PdfPCell(new Phrase("Reponse"));
        table.addCell(cl1);
        PdfPCell cl2 = new PdfPCell(new Phrase("Date debut"));
        table.addCell(cl2);
        PdfPCell cl3 = new PdfPCell(new Phrase("Duree"));
        table.addCell(cl3);
        table.setHeaderRows(1);
        document.add(table);

        int i = 0;
        for (i = 0; i < liste_objectifs.size(); i++) {
            table.addCell(liste_objectifs.get(i).getDescription());
            table.addCell("" + liste_objectifs.get(i).getReponse());
            table.addCell(liste_objectifs.get(i).getDate());
            table.addCell("" + liste_objectifs.get(i).getDuree());

        }
        document.add(table);

        document.close();

    }

    public void liste_objectifsPred() throws FileNotFoundException, DocumentException {

        ServiceObjectifPred so = new ServiceObjectifPred();
        String message = "Voici la liste d'objectifs prédéfinis \n\n";

        String file_name = "src/PDF/liste_objectifsPredefinis.pdf";
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(file_name));
        document.open();
        Paragraph para = new Paragraph(message);
        document.add(para);
        List<ObjectifPred> liste_objectifs = so.afficherObjectifsPredList();
        PdfPTable table = new PdfPTable(4);

        PdfPCell cl = new PdfPCell(new Phrase("Description"));
        table.addCell(cl);
        PdfPCell cl1 = new PdfPCell(new Phrase("duree"));
        table.addCell(cl1);
        PdfPCell cl2 = new PdfPCell(new Phrase("nom Admin"));
        table.addCell(cl2);
        PdfPCell cl3 = new PdfPCell(new Phrase("Prenom admin"));
        table.addCell(cl3);
        table.setHeaderRows(1);
        document.add(table);

        int i = 0;
        for (i = 0; i < liste_objectifs.size(); i++) {
            table.addCell(liste_objectifs.get(i).getDescriptionP());
            table.addCell("" + liste_objectifs.get(i).getDureeP());
            table.addCell(liste_objectifs.get(i).getAdmin().getNom());
            table.addCell("" + liste_objectifs.get(i).getAdmin().getPrenom());

        }
        document.add(table);

        document.close();

    }
}
