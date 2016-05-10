package Metier;

import Graphics.GlobalFrame;
import javax.ws.rs.client.Entity;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import com.google.gson.*;

public class AccueilR extends CoRest {

    public AccueilR() {
        super();
    }

    //secure
    /**
     * <b>author</b> kafui
     * Permet à un employé, gérant ou administrateur de la fédération de se
     * connecter
     *
     * @param user identifiant de l'utilisateur
     * @param pw mot de passe de l'utilisateur
     * @return KO si les informations entrées sont fausses, mdp sinon
     */
    public String accueilCoGet(String user, String pw) {
        try {
            String url = getBaseUrl() + "/personnel/" + encryptId((Integer.parseInt(user)));

            this.target = this.client.target(url);
            this.response = this.target.request().get();
            String responsebrut = decryptData(this.response.readEntity(String.class));
            System.out.println(responsebrut);
            JsonElement root = new JsonParser().parse(responsebrut);
            if (root.getAsJsonObject().has("succes")) {
                return "KO";
            } else if(!comparePassword(root.getAsJsonObject().get("motdepasse").getAsString(),hashPassword(pw))) {
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
    /**
     * <b>author</b> kafui
     * Permet de créer un client
     *
     * @param nom nom du client
     * @param prenom prenom du client
     * @param email email du client
     * @param mdp mot de passe du client 
     * @param code code postal du client
     * @return les informations sur le client créé sous forme de JSON
     */
    public String creerClient(String nom, String prenom, String email, String mdp, String code) {
        String maChaine ="";
        try{
        jsonArgs.put("nom", nom);
        jsonArgs.put("prenom", prenom);
        jsonArgs.put("mdp", hashPassword(mdp));
        jsonArgs.put("email", email);
        jsonArgs.put("codePostal", code);
        System.out.println("Voici le mot de passe avant l'envoi " + hashPassword(mdp));
        String[] parts = GlobalFrame.idBanquePersonnel.getText().split(":");
        jsonArgs.put("idBanque", parts[1]);

        maChaine = gson.toJson(jsonArgs);

        
        target = client.target(baseUrl + "/client/creer");
        response = target.request().post(Entity.entity(encryptData(maChaine), "application/xml;charset=UTF-8"));
        maChaine = decryptData(response.readEntity(String.class));
        response.close();
        } catch (Exception e) {     
        }
        return maChaine;
    }
    

    //Secure
    /**
     * <b>author</b> pauline
     * Permet de créer un compte courant à un client
     *
     * @param idClient id du client pour lequel on veut créer le compte
     */
    //secure
    public void creerCompteCourant(String idClient) {
        jsonArgs.put("idClient", idClient);
        String maChaine = gson.toJson(jsonArgs);
        try{
        target = client.target(getBaseUrl() + "/client/compte-courant/creer");
        response = target.request().post(Entity.entity(encryptData(maChaine), "application/xml;charset=UTF-8"));
        response.close();
        }catch(Exception e){System.out.println(e); }
    }

    //secure
    /**
     * <b>author</b> pauline
     * Permet de créer un compte epargne à un client
     *
     * @param idClient id du client pour lequel on veut créer le compte
     */
    public void creerCompteEpargne(String idClient) {
        jsonArgs.put("idClient", idClient);
        String maChaine = gson.toJson(jsonArgs);

        try{
        target = client.target(getBaseUrl() + "/client/compte-epargne/creer");
        response = target.request().post(Entity.entity(encryptData(maChaine), "application/xml;charset=UTF-8"));
        response.close();
        }catch(Exception e){System.out.println(e);}

    }

    //secure
    /**
     * <b>author</b> pauline
     * Permet d'obtenir les informations sur le compte epargne d'un client
     *
     * @param id id du client dont on veux consulter le compte
     * @return Les informations sur le compte epargne
     */
    public String consulterCompteEpargneClient(int id) {
        try {
            target = client.target(getBaseUrl() + "/compte/epargne/" + encryptId(id));
            response = target.request().get();
            String reponse = decryptData(response.readEntity(String.class));
            JsonElement root = new JsonParser().parse(reponse);
            response.close();
            if (root.getAsJsonObject().has("succes")) {
                return "KO";
            } else {
                return reponse;
            }

        } catch (Exception ex) {
            return "KO";
        }
    }

    //secure
    /**
     * <b>author</b> pauline
     * Permet d'obtenir les informations sur le compte courant d'un client
     *
     * @param id id du client dont on veux consulter le compte
     * @return Les informations sur le compte courant
     */
    public String consulterCompteCourantClient(int id) {
        try {
            target = client.target(getBaseUrl() + "/compte/courant/" + encryptId(id));
            response = target.request().get();

            String reponse = decryptData(response.readEntity(String.class));
            JsonElement root = new JsonParser().parse(reponse);
            response.close();
            if (root.getAsJsonObject().has("succes")) {
                return "KO";
            } else {
                return reponse;
            }
        } catch (Exception ex) {
            return "KO";
        }
    }

}
