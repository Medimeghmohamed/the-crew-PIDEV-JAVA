/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Properties;
import java.util.Random;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.mail.Session;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Chirine
 */
public class JavaMailObjectif {

    public static void sendMailObj(String recepient, String desc) throws MessagingException {
        System.out.println("preparation pour envoyer l'email");
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String myAccountEmail = "chirinenasri13@gmail.com";
        String password = "Chouxoyz99";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });
        //Message message = prepareMessage(session, myAccountEmail, recepient);
        Message message = new MimeMessage(session);
         try {
            
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("BRAVO!");
            message.setText("BRAVO! vous avez terminé l'objectif : " + desc + ". \n Nous sommes fiers de vous. \n Vous pouvez ajouter d'autres objectifs.\n RQ: cet objectif sera supprimé de votre liste d'objectifs. \n \n L'équipe de Aura.");
        } catch (Exception ex) {
            System.out.println(ex);
        }
        Transport.send(message);
        System.out.println("message envoye avec succes");
    }

}
