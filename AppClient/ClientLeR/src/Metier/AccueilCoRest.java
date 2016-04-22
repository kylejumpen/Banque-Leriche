package Metier;
import java.io.*;
import java.net.*;

/**
 *
 * @author kyle
 */
public class AccueilCoRest {
  
    public static String accueilCoGet(String user, String pw) {
       try {
        URL url = new URL("http://www.google.fr") ; // remplacez l'Url
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
