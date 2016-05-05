package Metier;

import static Metier.MethodesRest.client;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.util.HashMap;
import javax.ws.rs.client.Entity;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import Security.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pauline
 */
public class ConsulterCompteR extends CoRest {

    public ConsulterCompteR() {
        super();
    }
    
    //secure
    public String consulterClientGet(int id) {

        try {
            target = client.target(baseUrl + "/client/" + Encrypt.encrypt((String.valueOf(id))));
            response = target.request().get();
            String reponse = response.readEntity(String.class);
            System.out.println(reponse);
            response.close();
            return Encrypt.decrypt(reponse);
        } catch (Exception ex) {
            return "{\"succes\": \"false\"}";
        }

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
    
    //secure
    public String consulterCompteCourantClient(int id) {
        try {
        target = client.target(baseUrl + "/compte/courant/" + Encrypt.encrypt(String.valueOf(id)));
        response = target.request().get();

        String reponse = Encrypt.decrypt(response.readEntity(String.class));
       /*  JsonElement root = new JsonParser().parse(reponse);
       if (root.getAsJsonObject().has("succes")) {
            reponse = "KO";
        }*/
        response.close();
        return reponse;
        } catch (Exception ex) {
            return "{\"succes\": \"false\"}";
        }
    }
    
    //secure
    public String consulterCompteEpargneClient(int id) {
        try {
        target = client.target(baseUrl + "/compte/epargne/" + Encrypt.encrypt(String.valueOf(id)));
        response = target.request().get();
        String reponse = Encrypt.decrypt(response.readEntity(String.class));
       JsonElement root = new JsonParser().parse(reponse);
        if (root.getAsJsonObject().has("succes")) {
            return "KO";
        }
        response.close();
        return reponse;
        } catch (Exception ex) {
            return "KO";
        }
    }

    public String supprimerCompteCourant(int id) {
        target = client.target(baseUrl + "/client/compte-courant/supprimer/" + id);
        response = target.request().delete();
        String reponse = response.readEntity(String.class);
        response.close();
        return reponse;
    }

        public String supprimerCompteEpargne(int id) {
        target = client.target(baseUrl + "/client/compte-epargne/supprimer/" + id);
        response = target.request().delete();
        String reponse = response.readEntity(String.class);
        response.close();
        return reponse;
    }

    public void debiterCompteCourant(int montant, int compte) {
        Gson gson = new Gson();
        HashMap<String, String> jsonArgs = new HashMap<String, String>();
        jsonArgs.put("type", "credit");
        jsonArgs.put("idCompteADebiter", String.valueOf(compte));
        jsonArgs.put("typeCompteADebiter", "courant");
        jsonArgs.put("idCompteACrediter", "1");
        jsonArgs.put("typeCompteACrediter", "courant");
        jsonArgs.put("montant", String.valueOf(montant));

        String maChaine; //Transformation en chaine de caractère
        maChaine = gson.toJson(jsonArgs);
        this.target = this.client.target(baseUrl + "/client/compte/operer"); // URL a remplacer
        this.response = this.target.request().post(Entity.entity(maChaine, "application/xml;charset=UTF-8"));
        response.close();
    }
    
        public void debiterCompteEpargne(int montant, int compte) {
        Gson gson = new Gson();
        HashMap<String, String> jsonArgs = new HashMap<String, String>();
        jsonArgs.put("type", "credit");
        jsonArgs.put("idCompteADebiter", String.valueOf(compte));
        jsonArgs.put("typeCompteADebiter", "epargne");
        jsonArgs.put("idCompteACrediter", "1");
        jsonArgs.put("typeCompteACrediter", "epargne");
        jsonArgs.put("montant", String.valueOf(montant));

        String maChaine; //Transformation en chaine de caractère
        maChaine = gson.toJson(jsonArgs);
        this.target = this.client.target(baseUrl + "/client/compte/operer"); // URL a remplacer
        this.response = this.target.request().post(Entity.entity(maChaine, "application/xml;charset=UTF-8"));
        response.close();
    }

    public void crediterCompteCourant(int montant, int compte) {
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
    
        public void crediterCompteEpargne(int montant, int compte) {
        Gson gson = new Gson();
        HashMap<String, String> jsonArgs = new HashMap<String, String>();
        jsonArgs.put("type", "credit");
        jsonArgs.put("idCompteADebiter", "1");
        jsonArgs.put("typeCompteADebiter", "epargne");
        jsonArgs.put("idCompteACrediter", String.valueOf(compte));
        jsonArgs.put("typeCompteACrediter", "epargne");
        jsonArgs.put("montant", String.valueOf(montant));

        String maChaine; //Transformation en chaine de caractère
        maChaine = gson.toJson(jsonArgs);

        this.target = this.client.target(baseUrl + "/client/compte/operer"); // URL a remplacer
        this.response = this.target.request().post(Entity.entity(maChaine, "application/xml;charset=UTF-8"));
        response.close();
    }

}
