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

    //Secure
    /**
     * Permet d'effectuer un echange d'argent entre deux comptes
     *
     * @param typeDebiteur type du compte debiteur (courant ou epargne)
     * @param debiteur compte debiteur
     * @param typeCrediteur type du compte crediteur (courant ou epargne)
     * @param crediteur compte crediteur
     * @param montant montant à échanger entre les comptes
     */
    public void echangerArgent(String typeDebiteur, String debiteur, String typeCrediteur, String crediteur, String montant) {

        this.jsonArgs.put("type", "credit");
        jsonArgs.put("idCompteADebiter", debiteur);
        jsonArgs.put("typeCompteADebiter", typeDebiteur);
        jsonArgs.put("idCompteACrediter", crediteur);
        jsonArgs.put("typeCompteACrediter", typeCrediteur);
        jsonArgs.put("montant", montant);

        String maChaine; //Transformation en chaine de caractère
        maChaine = this.gson.toJson(jsonArgs);
        this.target = this.client.target(getBaseUrl() + "/client/compte/operer"); //vérifier l'url
        try{
        response = target.request().post(Entity.entity(encryptData(maChaine), "application/xml;charset=UTF-8"));
        System.out.println("Statut de l'échange : " + String.valueOf(response.getStatus()));
        response.close();
        }catch(Exception e){System.out.println(e);}

    }

    //Secure
    /**
     * Permet d'obtenir les informations les comptes courant d'une banque
     *
     * @param id id de la banque dont on veux consulter les comptes courants
     * @return les informations sur les comptes courants
     */
    public String getComptesCourant(int id) {
        target = client.target(getBaseUrl() + "/liste/comptes-courant/" + encryptId(id));
        response = target.request().get();
        try {
            String rep = response.readEntity(String.class);
            response.close();
            return decryptData(rep);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    //Secure
    /**
     * Permet d'obtenir les informations les comptes epargne d'une banque
     * @param id id de la banque dont on veux consulter les comptes epargnes
     * @return les informations sur les comptes epargnes
     */
    public String getComptesEpargne(int id) {

        target = client.target(baseUrl + "/liste/comptes-epargne/" + encryptId(id));
        response = target.request().get();
        try {
            String rep = response.readEntity(String.class);
            response.close();
            return decryptData(rep);
        } catch (Exception e) {
            return e.getMessage();
        }
}
