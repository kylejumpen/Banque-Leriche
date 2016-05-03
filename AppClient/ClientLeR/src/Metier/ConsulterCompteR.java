package Metier;

import com.google.gson.Gson;
import java.util.HashMap;
import javax.ws.rs.client.Entity;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

/**
 *
 * @author pauline
 */
public class ConsulterCompteR extends CoRest {

    public ConsulterCompteR() {
        super();
    }
    
    public String consulterCompteGet(int id) {
        System.out.println("coucou");
        
        target = client.target(baseUrl + "/client/compte-courant/" + id);
            response = target.request().get();
            String reponse = response.readEntity(String.class);
            response.close();
        return reponse;
  
    }

        public String supprimerCompte(int id) {
            target = client.target(baseUrl + "/client/compte-courant/supprimer/" + id);
            response = target.request().delete();
            String reponse = response.readEntity(String.class);
            response.close();
            return reponse;
    }
    
    
    
    public void debiterCompte(int montant, int compte) {
        Gson gson = new Gson();
        HashMap<String, String> jsonArgs = new HashMap<String, String>();
        jsonArgs.put("type", "courant");
        jsonArgs.put("compteDebiteurId", "\"" + compte + "\"");
        jsonArgs.put("compteCrediteurId", "1");
        jsonArgs.put("montant", "\"" + montant + "\"");

        String maChaine; //Transformation en chaine de caractère
        maChaine = gson.toJson(jsonArgs);
        this.target = this.client.target(baseUrl +"/client/compte/operer/post"); // URL a remplacer
        this.response = this.target.request().post(Entity.entity(maChaine, "application/xml;charset=UTF-8"));
        
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

        }
        */
    }
    
        public void crediterCompte(int montant, int compte) {
        Gson gson = new Gson();
        HashMap<String, String> jsonArgs = new HashMap<String, String>();
        jsonArgs.put("type", "courant");
        jsonArgs.put("compteDebiteurId","1" );
        jsonArgs.put("compteCrediteurId", "\"" + compte + "\"");
        jsonArgs.put("montant", "\"" + montant + "\"");

        String maChaine; //Transformation en chaine de caractère
        maChaine = gson.toJson(jsonArgs);
        
        this.target = this.client.target(baseUrl +"/client/compte/operer/post"); // URL a remplacer
        this.response = this.target.request().post(Entity.entity(maChaine, "application/xml;charset=UTF-8"));
        
    }
    
    
}
