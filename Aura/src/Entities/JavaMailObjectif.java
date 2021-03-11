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

    public static void sendMailObj(String recepient) throws MessagingException {
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
                return new PasswordAuthentication(myAccountEmail, password); //To change body of generated methods, choose Tools | Templates.
            }
        });
        Message message = prepareMessage(session, myAccountEmail, recepient);
        System.out.println("ok");
        Transport.send(message);
        System.out.println("okkkkk");
        System.out.println("message envoye avec succes");
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recepient) {
        ObservableList<String> affirmations = FXCollections.observableArrayList(
                "En me permettant d’être heureux, j’incite les autres à être heureux aussi.",
                "Le bonheur est mon droit imprescriptible. J’accepte que le bonheur devienne le cœur de mon existence.",
                "J’aime le changement et je m’adapte facilement aux nouvelles situations.",
                "Je m’accepte tel que je suis et je m’aime profondément et complètement.",
                "Je m’accepte pleinement et je sais que je suis digne des meilleures choses dans la vie.",
                "Mon univers est un endroit paisible, aimant et plein de joie.",
                "La paix m’envahit maintenant et pour toujours.",
                "J’envoie de la paix dans le monde.",
                "Je dépasse le stress. Je vis en paix.");
        Random random = new Random();
        int nb;
        nb = random.nextInt(8);
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Bienvenue sur Aura");
            message.setText("aaaaaaaa");
            message.setText("N'oubliez pas : "+ affirmations.get(nb));
            return message;
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }

}
