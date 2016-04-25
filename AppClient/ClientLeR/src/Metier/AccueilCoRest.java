package Metier;
import java.io.*;
import java.net.*;
import Entity.Compte;

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
    
    public static String createAccountPost(Compte account) {
        try {
         URL url = new URL("http://www.google.fr") ; // remplacez l'Url
         //URL nurl = new URL("http://localhost:8081/adressedeconnexion/
         HttpURLConnection conn = (HttpURLConnection) url.openConnection();
         conn.setDoOutput(true);
         conn.setRequestMethod("POST");
         conn.setRequestProperty("Content-Type", "application/json");
         String input = "{\"nom\":"+account.getNom()+",\"prenom\":"+account.getPrenom()
                +",\"Addresse\":"+account.getAddresse()+",\"Addresse\":"+account.getType()+"}";
        OutputStream os = conn.getOutputStream();
        os.write(input.getBytes());
        os.flush();
        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
        String output = "",temp ="";

        while ((temp = br.readLine()) != null)
        output += temp;
        conn.disconnect();
        return temp;
        } catch (Exception e) {
        e.printStackTrace();
        return "erreur";
        }
        
    }
    
}
