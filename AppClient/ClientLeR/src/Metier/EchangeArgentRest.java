/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metier;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.HashMap;

/**
 *
 * @author pauline
 */
public class EchangeArgentRest {
    
    public static void echangerArgent(String type, int debiteur, int crediteur, int montant){
        /*Gson gson = new Gson();
        HashMap<String, String> jsonArgs = new HashMap<String, String>();
        jsonArgs.put("type", "courant");
        jsonArgs.put("compteDebiteurId", "\"" + debiteur + "\"");
        jsonArgs.put("compteCrediteurId","\"" + crediteur + "\"" );
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

        }*/
    }
}
