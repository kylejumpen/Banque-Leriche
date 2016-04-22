/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metier;
import java.io.*;
import java.net.*;

/**
 *
 * @author pauline
 */
public class ConsulterCompteRest {
        
    
    public static String consulterCompteGet(int id) {
       try {
        URL url = new URL("http://localhost:8001/Banque-1.0/banque/client/compteCourant/"+id) ; // remplacez l'Url
        //URL nurl = new URL("http://localhost:8081/adressedeconnexion/?username="+user+"&password="+pw);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection() ;
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept","text/plain") ;       
        BufferedReader br = new BufferedReader (new InputStreamReader((conn.getInputStream())));
        
        String response = br.readLine();
        conn.disconnect();
        return response;
        }catch(Exception e) {
        e.printStackTrace() ;
        return "erreur";
        }  
    }
}
