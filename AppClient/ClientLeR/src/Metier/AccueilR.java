package Metier;

import javax.ws.rs.client.Entity;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import com.google.gson.*;

/**
 *
 * @author kyle
 */
public class AccueilR extends CoRest {

    public AccueilR() {
        super();
    }
    
    //secure
    public String accueilCoGet(String user, String pw) {
        try {
            String url = baseUrl + "/personnel/" + encryptId((Integer.parseInt(user)));
            System.out.println(url);
            this.target = this.client.target(url);
            this.response = this.target.request().get();
            String responsebrut = decryptData(this.response.readEntity(String.class));
            JsonElement root = new JsonParser().parse(responsebrut);
            if (root.getAsJsonObject().has("succes")) {
                return "KO";
            } else if (!pw.equals(root.getAsJsonObject().get("motdepasse").getAsString())) {
                return "mdp";
            }
            String reponse = root.getAsJsonObject().get("role").getAsString() + "#" + root.getAsJsonObject().get("personnelId").getAsString() + "#";

            root = new JsonParser().parse(root.getAsJsonObject().get("banque").getAsString());
            reponse += root.getAsJsonObject().get("banqueId").getAsInt();
            return reponse;
        } catch (Exception e) {
            e.printStackTrace();
            return "KO";
        }
    }

    //secure
    public String creerClient(String nom, String prenom, String email, String mdp, String code) {

        jsonArgs.put("nom", nom);
        jsonArgs.put("prenom", prenom);
        jsonArgs.put("mdp", mdp);
        jsonArgs.put("email", email);
        jsonArgs.put("codePostal", code);
        jsonArgs.put("idBanque", "1");

        String maChaine = gson.toJson(jsonArgs);
        try{
        target = client.target(baseUrl + "/client/creer");
        response = target.request().post(Entity.entity(encryptData(maChaine), "application/xml;charset=UTF-8"));
        maChaine = decryptData(response.readEntity(String.class));
        response.close();
        return maChaine;
        }catch(Exception e){
            System.err.println(e);
            return maChaine;
        }
    }
    
    //Secure
    public void creerCompteCourant(String idClient) {
        jsonArgs.put("idClient", idClient);
        String maChaine = gson.toJson(jsonArgs);
        try{
        target = client.target(baseUrl + "/client/compte-courant/creer");
        response = target.request().post(Entity.entity(encryptData(maChaine), "application/xml;charset=UTF-8"));
        System.out.println("POST : " + response.getStatus());
        response.close();
        }catch(Exception e){System.out.println(e); }
    }
    
    //secure
    public void creerCompteEpargne(String idClient) {
        jsonArgs.put("idClient", idClient);
        String maChaine = gson.toJson(jsonArgs);

        try{
        target = client.target(baseUrl + "/client/compte-epargne/creer");
        response = target.request().post(Entity.entity(encryptData(maChaine), "application/xml;charset=UTF-8"));
        System.out.println("POST : " + response.getStatus());
        response.close();
        }catch(Exception e){System.out.println(e);}
    }
    
    //secure
    public String consulterCompteEpargneClient(int id) {
        try {
            target = client.target(baseUrl + "/compte/epargne/" + encryptId(id));
            response = target.request().get();
            String reponse = decryptData(response.readEntity(String.class));
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
    
    //secure
    public String consulterCompteCourantClient(int id) {
        try {
            target = client.target(baseUrl + "/compte/courant/" + encryptId(id));
            response = target.request().get();

            String reponse = decryptData(response.readEntity(String.class));
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

}
