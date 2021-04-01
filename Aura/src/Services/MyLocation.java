/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.MyAddress;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.logging.Level;

/**
 *
 * @author SeifBS
 */
public class MyLocation {

    public MyAddress CurrentLocation(String ip) {
        ip = ip.trim();
        MyAddress obj_Location_Use_Bean = new MyAddress();
        System.out.println("The ip adress is before " + ip + "  split");
        try {
            if (ip.contains(",")) {
                String temp_ip[] = ip.split(",");
                ip = temp_ip[1].trim();
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
        System.out.println("The ip adress is after " + ip + " split");

        URL url;
        try {

            url = new URL("http://api.ipinfodb.com/v3/ip-city/?key=bc72ff59eff8dbec3c813748cfe35bd68c3ba534ae321f464804cf643c0bd8cb&ip=" + ip);

            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String strTemp = "";
            String temporaray = "";
            String temp_array[] = null;

            while (null != (strTemp = br.readLine())) {
                temporaray = strTemp;

            }

            temp_array = temporaray.split(";");
            System.out.println("Str length is " + temp_array.length);
            int length = temp_array.length;

            /*while(i<length){
							System.out.println(i);
							System.out.println(temp_array[i]);
							i++;
						}*/
            if (length == 11) {

                obj_Location_Use_Bean.setIp_address(ip);

                if (temp_array[3] != null) {
                    obj_Location_Use_Bean.setCountry_code(temp_array[3]);
                }
                if (temp_array[4] != null) {
                    obj_Location_Use_Bean.setCountry(temp_array[4]);
                }

                if (temp_array[5] != null) {
                    obj_Location_Use_Bean.setState(temp_array[5]);
                }

                if (temp_array[6] != null) {
                    obj_Location_Use_Bean.setCity(temp_array[6]);
                }

                if (temp_array[7] != null) {
                    obj_Location_Use_Bean.setZip(temp_array[7]);
                }

                if (temp_array[8] != null) {
                    obj_Location_Use_Bean.setLat(temp_array[8]);
                }

                if (temp_array[9] != null) {
                    obj_Location_Use_Bean.setLon(temp_array[9]);
                }

                if (temp_array[10] != null) {
                    obj_Location_Use_Bean.setUtc_offset(temp_array[10]);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj_Location_Use_Bean;
    }

    public String MyIpAdress() throws SocketException {
        Enumeration e = NetworkInterface.getNetworkInterfaces();
        while (e.hasMoreElements()) {
            NetworkInterface n = (NetworkInterface) e.nextElement();
            Enumeration ee = n.getInetAddresses();
            while (ee.hasMoreElements()) {
                InetAddress i = (InetAddress) ee.nextElement();
                System.out.println("hey");

                System.out.println(i.getHostAddress());
                System.out.println("hey");

            }
        }

        InetAddress ip = null;
        try {
            ip = InetAddress.getLocalHost();
        } catch (UnknownHostException ex) {
            java.util.logging.Logger.getLogger(MyLocation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ip.getHostAddress();

    }

}
