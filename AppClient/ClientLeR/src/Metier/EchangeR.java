/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metier;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.HashMap;
import javax.ws.rs.client.Entity;

/**
 *
 * @author pauline
 * @author kyle
 */
public class EchangeR extends CoRest {
    
    public void echangerArgent(String type, int debiteur, int crediteur, int montant){
        
        this.jsonArgs.put("type", "courant");
        this.jsonArgs.put("compteDebiteurId", "\"" + debiteur + "\"");
        this.jsonArgs.put("compteCrediteurId","\"" + crediteur + "\"" );
        this.jsonArgs.put("montant", "\"" + montant + "\"");

        String maChaine; //Transformation en chaine de caractère
        maChaine = this.gson.toJson(jsonArgs);
        this.target = this.client.target(baseUrl + "/client/compte/operer"); //vérifier l'url
        response = target.request().post(Entity.entity(maChaine, "application/xml;charset=UTF-8"));
        maChaine = String.valueOf(response.getStatus());
        response.close();
        
        /*
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
