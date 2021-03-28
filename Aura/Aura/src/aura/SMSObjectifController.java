/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aura;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.net.HttpURLConnection;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Chirine
 */
public class SMSObjectifController implements Initializable {

    @FXML
    private TextField tf_api;
    @FXML
    private TextField tf_sender;
    @FXML
    private TextField tf_number;
    @FXML
    private TextField tf_message;
    @FXML
    private WebView webview_obj;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        WebEngine we= webview_obj.getEngine();
        we.load("https://www.google.com");
    }    

    @FXML
    private void envoyer_sms_obj(ActionEvent event) {
        try {
			// Construct data
			String apiKey = "apikey=" + tf_api.getText();
			String message = "&message=" + tf_message.getText();
			String sender = "&sender=" + tf_sender.getText();
			String numbers = "&numbers=" + tf_number.getText();
			
			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/?").openConnection();
			String data = apiKey + numbers + message + sender;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				//stringBuffer.append(line);
                                JOptionPane.showConfirmDialog(null, "message"+line);
			}
			rd.close();
			
			//return stringBuffer.toString();
		} catch (Exception e) {
			//System.out.println("Error SMS "+e);
                        JOptionPane.showMessageDialog(null, e);
			//return "Error "+e;
		}
    }
    
}
