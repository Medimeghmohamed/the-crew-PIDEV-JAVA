/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;


  import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
/**
 *
 * @author SeifBS
 */
public class SendSms {

    public void sendSms(String message_, String number) throws UnsupportedEncodingException, MalformedURLException, IOException {

   String AUTH_TOKEN = "4c3fa25108849abc11b7b5931e07b89d";
    String ACCOUNT_SID = "ACb8cc84754109b164c644d54c60404a6c";


    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

    Message message = Message.creator(new PhoneNumber("+21653709942"),
        new PhoneNumber("+13237451104"), 
        "نقص مل خرطة ولد أختي ").create();

    System.out.println(message.getSid());
}

    
}
