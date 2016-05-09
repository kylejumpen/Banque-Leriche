package Metier;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.util.HashMap;
import javax.ws.rs.client.Entity;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
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
    /**
     * Permet d'obtenir les informations sur un client
     *
     * @param id id du client à consulter
     * @return Les informations du client
     */
    public String consulterClientGet(int id) {

        try {
            target = client.target(getBaseUrl() + "/client/" + encryptId((id)));
            response = target.request().get();
            String reponse = response.readEntity(String.class);
            System.out.println("Dans le consulter clientget " + reponse);
            response.close();
            return decryptData(reponse);
        } catch (Exception ex) {
            return "KO";
        }

    }

    //secure
    /**
     * Permet de consulter le compte courant d'un client
     *
     * @param id id du client dont on veux consulter le compte courant
     * @return Les informations sur le compte courant du client
     */
    public String consulterCompteCourantClient(int id) {
        try {
            target = client.target(getBaseUrl() + "/compte/courant/" + encryptId(id));
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
    /**
     * Permet de consulter le compte epargne d'un client
     *
     * @param id id du client dont on veux consulter le compte epargne
     * @return Les informations sur le compte epargne du client
     */
    public String consulterCompteEpargneClient(int id) {
        try {
            target = client.target(getBaseUrl() + "/compte/epargne/" + encryptId(id));
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
    /**
     * Permet de supprimer un compte courant
     *
     * @param id id du compte courant à supprimer
     * @return les informations sur le compte supprimé
     */
    public String supprimerCompteCourant(int id) {
        target = client.target(getBaseUrl() + "/client/compte-courant/supprimer/" + encryptId(id));
        response = target.request().delete();
        String reponse = response.readEntity(String.class);
        response.close();
        return reponse;
    }

    //secure
    /**
     * Permet de supprimer un compte epargne
     *
     * @param id id du compte epargne à supprimer
     * @return les informations sur le compte supprimé
     */
    public String supprimerCompteEpargne(int id) {
        target = client.target(getBaseUrl() + "/client/compte-epargne/supprimer/" + encryptId(id));
        response = target.request().delete();
        String reponse = response.readEntity(String.class);
        response.close();
        return reponse;
    }

    // secure
    /**
     * Permet de débiter un compte courant
     *
     * @param montant montant à débiter
     * @param compte compte à débiter
     */
    public void debiterCompteCourant(int montant, int compte) {
        Gson gson = new Gson();
        HashMap<String, String> jsonArgs = new HashMap<String, String>();
        jsonArgs.put("type", "credit");
        jsonArgs.put("idCompteADebiter", String.valueOf(compte));
        jsonArgs.put("typeCompteADebiter", "courant");
        jsonArgs.put("idCompteACrediter", "1");
        jsonArgs.put("typeCompteACrediter", "courant");
        jsonArgs.put("montant", String.valueOf(montant));

	try{
        String maChaine; 
        maChaine = gson.toJson(jsonArgs);
        this.target = this.client.target(getBaseUrl() + "/client/compte/operer"); 
        this.response = this.target.request().post(Entity.entity(encryptData(maChaine), "application/xml;charset=UTF-8"));
        response.close();
	}catch(Exception e){System.out.println(e);}

    }

    //secure
    /**
     * Permet de débiter un compte epargne
     *
     * @param montant montant à débiter
     * @param compte compte à débiter
     */
    public void debiterCompteEpargne(int montant, int compte) {
        Gson gson = new Gson();
        HashMap<String, String> jsonArgs = new HashMap<String, String>();
        jsonArgs.put("type", "credit");
        jsonArgs.put("idCompteADebiter", String.valueOf(compte));
        jsonArgs.put("typeCompteADebiter", "epargne");
        jsonArgs.put("idCompteACrediter", "1");
        jsonArgs.put("typeCompteACrediter", "epargne");
        jsonArgs.put("montant", String.valueOf(montant));

	try{
        String maChaine; //Transformation en chaine de caractère
        maChaine = gson.toJson(jsonArgs);
        this.target = this.client.target(getBaseUrl() + "/client/compte/operer"); 
        this.response = this.target.request().post(Entity.entity(encryptData(maChaine), "application/xml;charset=UTF-8"));
        response.close();
	}catch(Exception e){System.out.println(e);}
    }

    //secure
    /**
     * Permet de créditer un compte courant
     *
     * @param montant montant à créditer
     * @param compte compte à créditer
     */
    public void crediterCompteCourant(int montant, int compte) {
        Gson gson = new Gson();
        HashMap<String, String> jsonArgs = new HashMap<String, String>();

        jsonArgs.put("type", "credit");
        jsonArgs.put("idCompteADebiter", "1");
        jsonArgs.put("typeCompteADebiter", "courant");
        jsonArgs.put("idCompteACrediter", String.valueOf(compte));
        jsonArgs.put("typeCompteACrediter", "courant");
        jsonArgs.put("montant", String.valueOf(montant));

	try{
        String maChaine; 
        maChaine = gson.toJson(jsonArgs);

        this.target = this.client.target(getBaseUrl() + "/client/compte/operer"); 
        this.response = this.target.request().post(Entity.entity(encryptData(maChaine), "application/xml;charset=UTF-8"));
        response.close();
	}catch(Exception e){System.out.println(e);}
    }

    //secure
    /**
     * Permet de créditer un compte epargne
     *
     * @param montant montant à créditer
     * @param compte compte à créditer
     */
    public void crediterCompteEpargne(int montant, int compte) {
        Gson gson = new Gson();
        HashMap<String, String> jsonArgs = new HashMap<String, String>();
        jsonArgs.put("type", "credit");
        jsonArgs.put("idCompteADebiter", "1");
        jsonArgs.put("typeCompteADebiter", "epargne");
        jsonArgs.put("idCompteACrediter", String.valueOf(compte));
        jsonArgs.put("typeCompteACrediter", "epargne");
        jsonArgs.put("montant", String.valueOf(montant));

        String maChaine; 
        maChaine = gson.toJson(jsonArgs);

	try{
        this.target = this.client.target(getBaseUrl() + "/client/compte/operer"); 
        this.response = this.target.request().post(Entity.entity(encryptData(maChaine), "application/xml;charset=UTF-8"));
        response.close();
	}catch(Exception e){System.out.println(e);}

    }

    //secure
    /**
     * Permet de bloquer/débloquer un compte en fonction de son état actuel 
     * @param compte compte à bloquer/débloquer
     * @param type type du compte à bloquer/débloquer (courant ou epargne)
     */
    public void bloquerDebloquer(int compte, String type) {
        Gson gson = new Gson();
        HashMap<String, String> jsonArgs = new HashMap<String, String>();
        jsonArgs.put("type", type);
        jsonArgs.put("idCompte", String.valueOf(compte));

        String maChaine; 
        maChaine = gson.toJson(jsonArgs);

        try{
        this.target = this.client.target(getBaseUrl() + "/client/compte/bloquer"); // URL a remplacer
        this.response = this.target.request().put(Entity.entity(encryptData(maChaine), "application/xml;charset=UTF-8"));
        response.close();
        }catch(Exception e){System.out.println(e);}
    }

}
