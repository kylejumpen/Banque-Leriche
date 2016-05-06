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
    
    public EchangeR() {
    super();    
    }
    
    public void echangerArgent(String type, int debiteur, int crediteur, int montant){
        
        this.jsonArgs.put("type", type);
        jsonArgs.put("idCompteADebiter", String.valueOf(debiteur));
        jsonArgs.put("typeCompteADebiter", "courant");
        jsonArgs.put("idCompteACrediter", String.valueOf(crediteur));
        jsonArgs.put("typeCompteACrediter", "courant");
        jsonArgs.put("montant", String.valueOf(montant));

        String maChaine; //Transformation en chaine de caractère
        maChaine = this.gson.toJson(jsonArgs);
        this.target = this.client.target(baseUrl + "/client/compte/operer"); //vérifier l'url
        response = target.request().post(Entity.entity(maChaine, "application/xml;charset=UTF-8"));
        System.out.println("Statut de l'échange : " + String.valueOf(response.getStatus()));
        response.close();
     
    }
    
    public String getComptesCourant(int id){
            target = client.target(baseUrl + "/liste/comptes-courant"+id);
            response = target.request().get();
            String reponse = response.readEntity(String.class);
            System.out.println(reponse);
            response.close();
            return reponse;
    }
}
