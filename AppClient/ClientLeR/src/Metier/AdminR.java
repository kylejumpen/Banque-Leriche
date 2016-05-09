package Metier;

import javax.ws.rs.client.Entity;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import com.google.gson.*;

/**
 *
 * @author pauline
 */
public class AdminR extends CoRest{

    public AdminR() {
        super();
    }
    
    
    //Secure
    public String supprimerBanque(int id) {
        target = client.target(getBaseUrl() + "/supprimer/" + encryptId(id));
        response = target.request().delete();
        try{
        String reponse = decryptData(response.readEntity(String.class));
        System.out.println(response.getStatusInfo());
        response.close();
        return reponse;
        }catch(Exception e)
        {
           return response.getStatusInfo().toString();
        }
    }
        
    //Secure
    public void ajouterBanque(String nom, String ville) {

    jsonArgs.put("nom", nom);
    jsonArgs.put("ville", ville);

    String maChaine;
    maChaine = gson.toJson(jsonArgs);
    this.target = this.client.target(getBaseUrl() + "/creer");
    try{
    this.response = this.target.request().post(Entity.entity(encryptData(maChaine), "application/xml;charset=UTF-8"));
    response.close();
    }catch(Exception e){ System.out.println(e);}
    }
    
}
