package Metier;

import java.io.*;
import java.net.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.HashMap;

/**
 *
 * @author pauline
 */
public class ConsulterCompteRest {

    public static String consulterCompteGet(int id) {
        try {
            URL url = new URL("http://localhost:8001/Banque-1.0/banque/client/compte-courant/" + id); // remplacez l'Url
            //URL nurl = new URL("http://localhost:8081/adressedeconnexion/?username="+user+"&password="+pw);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "text/plain");
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            String response = br.readLine();
            conn.disconnect();
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return "erreur";
        }
    }

        public static String supprimerCompte(int id) {
        try {
            URL url = new URL("http://localhost:8001/Banque-1.0/banque/client/compte-courant/supprimer/" + id); // remplacez l'Url
            //URL nurl = new URL("http://localhost:8081/adressedeconnexion/?username="+user+"&password="+pw);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("DELETE");
            conn.setRequestProperty("Accept", "text/plain");
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            String response = br.readLine();
            conn.disconnect();
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return "erreur";
        }
    }
    
    
    
    public static void debiterCompte(int montant, int compte) {
        Gson gson = new Gson();
        HashMap<String, String> jsonArgs = new HashMap<String, String>();
        jsonArgs.put("type", "courant");
        jsonArgs.put("compteDebiteurId", "\"" + compte + "\"");
        jsonArgs.put("compteCrediteurId", "1");
        jsonArgs.put("montant", "\"" + montant + "\"");

        String maChaineJson; //Transformation en chaine de caractère
        maChaineJson = gson.toJson(jsonArgs);
        try {

            URL url = new URL("http://localhost:8001/Banque-1.0/banque/client/compte/operer/post"); // remplacez l'Url
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            		conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
            out.write(maChaineJson);
            out.flush();
            out.close();
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            String response = br.readLine();
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
    
        public static void crediterCompte(int montant, int compte) {
        Gson gson = new Gson();
        HashMap<String, String> jsonArgs = new HashMap<String, String>();
        jsonArgs.put("type", "courant");
        jsonArgs.put("compteDebiteurId","1" );
        jsonArgs.put("compteCrediteurId", "\"" + compte + "\"");
        jsonArgs.put("montant", "\"" + montant + "\"");

        String maChaineJson; //Transformation en chaine de caractère
        maChaineJson = gson.toJson(jsonArgs);
        try {

            URL url = new URL("http://localhost:8001/Banque-1.0/banque/client/compte/operer/post"); // remplacez l'Url
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            		conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
            out.write(maChaineJson);
            out.flush();
            out.close();
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            String response = br.readLine();
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
    
    
}
