package Metier;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.HashMap;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

/**
 *
 * @author pauline
 * @author kyle
 */
public class EchangeR extends CoRest {

    public EchangeR() {
        super();
    }

    public void echangerArgent(String typeDebiteur, String debiteur,String typeCrediteur,  String crediteur, String montant) {

        this.jsonArgs.put("type", "credit");
        jsonArgs.put("idCompteADebiter", debiteur);
        jsonArgs.put("typeCompteADebiter", typeDebiteur);
        jsonArgs.put("idCompteACrediter", crediteur);
        jsonArgs.put("typeCompteACrediter", typeCrediteur);
        jsonArgs.put("montant", montant);

        String maChaine; //Transformation en chaine de caractère
        maChaine = this.gson.toJson(jsonArgs);
        this.target = this.client.target(baseUrl + "/client/compte/operer"); //vérifier l'url
        response = target.request().post(Entity.entity(maChaine, "application/xml;charset=UTF-8"));
        System.out.println("Statut de l'échange : " + String.valueOf(response.getStatus()));
        response.close();

    }

    public String getComptesCourant(int id) {
        target = client.target(baseUrl + "/liste/comptes-courant/" + id);
        response = target.request().get();
        String rep = response.readEntity(String.class);
        response.close();
        return rep;
    }
    
        public String getComptesEpargne(int id) {
        target = client.target(baseUrl + "/liste/comptes-epargne/" + id);
        response = target.request().get();
        String rep = response.readEntity(String.class);
        response.close();
        return rep;
    }
}
