package Metier;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
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

        target = client.target(baseUrl + "/client/compte-courant/" + id);
        response = target.request().get();
        String reponse = response.readEntity(String.class);
        JsonElement root = new JsonParser().parse(reponse);
        if (root.getAsJsonObject().has("succes")) {
            return "KO";
        }
        //extraire montant nom et prenom     
        reponse = root.getAsJsonObject().get("montant").getAsString() + "#";

        root = new JsonParser().parse(root.getAsJsonObject().get("clientBanque")
                .getAsString());

        reponse += (root.getAsJsonObject().get("nom").getAsString() + "#");
        reponse += root.getAsJsonObject().get("prenom").getAsString();
        response.close();
        return reponse;

    }
    
        public String consulterCompteCourantClient(int id) {
        target = client.target(baseUrl + "/client/compte-courant/" + id);
        response = target.request().get();
        String reponse = response.readEntity(String.class);
        response.close();
        return reponse;
    }
    
        public String consulterCompteEpargneClient(int id) {
        target = client.target(baseUrl + "/client/compte-epargne/" + id);
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
        this.target = this.client.target(baseUrl + "/client/compte/operer/post"); // URL a remplacer
        this.response = this.target.request().post(Entity.entity(maChaine, "application/xml;charset=UTF-8"));
        response.close();
    }

    public void crediterCompte(int montant, int compte) {
        Gson gson = new Gson();
        HashMap<String, String> jsonArgs = new HashMap<String, String>();
        jsonArgs.put("type", "credit");
        jsonArgs.put("idCompteADebiter", "1");
        jsonArgs.put("typeCompteADebiter", "courant");
        jsonArgs.put("idCompteACrediter", String.valueOf(compte));
        jsonArgs.put("typeCompteACrediter", "courant");
        jsonArgs.put("montant", String.valueOf(montant));

        String maChaine; //Transformation en chaine de caractère
        maChaine = gson.toJson(jsonArgs);

        this.target = this.client.target(baseUrl + "/client/compte/operer"); // URL a remplacer
        this.response = this.target.request().post(Entity.entity(maChaine, "application/xml;charset=UTF-8"));
        response.close();
    }

}
