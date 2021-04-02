/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aura.Contact_Entraide;

import Service.SendMail;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * FXML Controller class
 *
 * @author Nour Dekhil
 */
public class SendmailEntraideController implements Initializable {

    @FXML
    private TextField emailToField;
    @FXML
    private TextField emailFromField;
    @FXML
    private TextField emailPasswordField;
    @FXML
    private TextField emailObjectField;
    @FXML
    private TextArea emailMessageField;
    @FXML
    private Label sentBoolValue;

    String id_user="";
    public void initializeFxml(String id){
        System.out.println("");
    
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void envoyerMail(MouseEvent event) throws AddressException, MessagingException {
        String to = emailToField.getText();
        String from = emailFromField.getText();
                 String password = emailPasswordField.getText();
                String object= emailObjectField.getText();
                     String subject=emailMessageField.getText();
                 
                 SendMail sm=new SendMail();
                 sm.send_mail_nour(from,password,to,object,subject);

        }}



       
   



