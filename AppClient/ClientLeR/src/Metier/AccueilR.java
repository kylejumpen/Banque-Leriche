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
 
    public AccueilR(){
        super();
    }
     
    public String accueilCoGet(String user, String pw) {
       try {
        String url = baseUrl +"/personnel/"+ Integer.parseInt(user); // remplacez l'Url
        this.target = this.client.target(url);
        this.response =this.target.request().get();
        String responsebrut = this.response.readEntity(String.class);


        // Bloc de verification
       /* JsonParser parser = new JsonParser();
        parser.parse(responsebrut); // throws JsonSyntaxException */
       JsonElement root = new JsonParser().parse(responsebrut);
       if(root.getAsJsonObject().has("succes"))
           return "KO";
       else if(!pw.equals(root.getAsJsonObject().get("motdepasse").getAsString()))
           return "mdp";
       String reponse = root.getAsJsonObject().get("role").getAsString()+"#"+root.getAsJsonObject().get("personnelId").getAsString()+"#";
      
       root = new JsonParser().parse(root.getAsJsonObject().get("banque").getAsString());
       reponse += root.getAsJsonObject().get("banqueId").getAsInt();
       return reponse;
        }catch(Exception e) {
        e.printStackTrace() ;
        return "erreur";
        }
    }
    
    public String creerClient(String nom, String prenom, String email, String mdp, String code){

        jsonArgs.put("nom", nom);
        jsonArgs.put("prenom", prenom);
        jsonArgs.put("mdp", mdp);
        jsonArgs.put("email", email);
        jsonArgs.put("codePostal", code);
        jsonArgs.put("idBanque", "1");
        
        String maChaine = gson.toJson(jsonArgs);

        target = client.target(baseUrl + "/client/creer");
        response = target.request().post(Entity.entity(maChaine, "application/xml;charset=UTF-8"));
        maChaine = String.valueOf(response.readEntity(String.class));
        System.out.println(response);
        response.close();
        return maChaine;

    }
    
    public void creerCompteCourant(String idClient){
        jsonArgs.put("idClient", idClient);
            String maChaine = gson.toJson(jsonArgs);

            target = client.target(baseUrl + "/client/compte-courant/creer");
            response = target.request().post(Entity.entity(maChaine, "application/xml;charset=UTF-8"));
            System.out.println("POST : " + response.getStatus());
            response.close();
        
    }
    
        public void creerCompteEpargne(String idClient){
                jsonArgs.put("idClient", idClient);
            String maChaine = gson.toJson(jsonArgs);

            target = client.target(baseUrl + "/client/compte-epargne/creer");
            response = target.request().post(Entity.entity(maChaine, "application/xml;charset=UTF-8"));
            System.out.println("POST : " + response.getStatus());
            response.close();
    }
    
    
}
