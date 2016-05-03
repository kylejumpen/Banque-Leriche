/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metier;

import static Metier.CoRest.baseUrl;
import static Metier.CoRest.client;
import com.google.gson.Gson;
import java.util.HashMap;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

/**
 *
 * @author pauline
 */
public class MethodesRest {

    protected static ResteasyClient client = new ResteasyClientBuilder().build();
    protected ResteasyWebTarget target;
    protected Response response;
    protected HashMap<String, String> jsonArgs = new HashMap<String, String>();
    protected Gson gson = new Gson();
    final static String baseUrl = "http://localhost:8001/Banque-1.0/banque";

    public String accueilCoGet(String user, String pw) {
        try {
            String url = baseUrl + "/personnel/" + Integer.parseInt(user); // remplacez l'Url
            //URL nurl = new URL("http://localhost:8081/adressedeconnexion/?username="+user+"&password="+pw);
            this.target = this.client.target(url);
            this.response = this.target.request().get();
            String response = this.response.readEntity(String.class);
            System.out.println(response);
            if (!response.equals("KO")) {
                String[] parts = response.split("-");
                if (parts[2].equals(pw)) {
                    return response;
                } else {
                    return "mdp";
                }
            } else {
                return "KO";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "erreur";
        }

    }

    public boolean creerClient(String nom, String prenom, String email, String mdp, String code) {
        Gson gson = new Gson();
        HashMap<String, String> jsonArgs = new HashMap<String, String>();

        jsonArgs.put("nom", nom);
        jsonArgs.put("prenom", prenom);
        jsonArgs.put("mdp", mdp);
        jsonArgs.put("email", email);
        jsonArgs.put("codePostal", code);
        jsonArgs.put("idBanque", "1");
        

        String maChaine = this.gson.toJson(jsonArgs);

        this.target = this.client.target(baseUrl + "/client/creer");
        this.response = this.target.request().post(Entity.entity(maChaine, "application/xml;charset=UTF-8"));
        maChaine = String.valueOf(response.getStatus());
        System.out.println(response);
        response.close();
        if (maChaine.equals("200")) {
            return true;
        }

        System.out.println("Soucis de fonctionnement, code renvoyé : " + maChaine);
        return false;
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
    
    
        public String consulterClientGet(int id) {

        target = client.target(baseUrl + "/client/" + id);
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

    public void echangerArgent(int crediteur, int debiteur, int montant) {
        Gson gson = new Gson();
        HashMap<String, String> jsonArgs = new HashMap<String, String>();
        jsonArgs.put("type", "credit");
        jsonArgs.put("idCompteADebiter", String.valueOf(debiteur));
        jsonArgs.put("typeCompteADebiter", "courant");
        jsonArgs.put("idCompteACrediter", String.valueOf(crediteur));
        jsonArgs.put("typeCompteACrediter", "courant");
        jsonArgs.put("montant", String.valueOf(montant));

        String maChaine; //Transformation en chaine de caractère
        maChaine = gson.toJson(jsonArgs);

        this.target = this.client.target(baseUrl + "/client/compte/operer"); // URL a remplacer
        this.response = this.target.request().post(Entity.entity(maChaine, "application/xml;charset=UTF-8"));
        response.close();
    }

    public void ajouterPersonnel(String banque, String nom, String motdepasse, String role) {
        String roleEnum;
        if (role.equals("Employé")) {
            roleEnum = "Employe";
        } else {
            roleEnum = "Gerant";
        }

        jsonArgs.put("nom", nom);
        jsonArgs.put("mdp", motdepasse);
        jsonArgs.put("role", role);
        jsonArgs.put("idBanque", banque);

        String maChaine; //Transformation en chaine de caractère
        maChaine = gson.toJson(jsonArgs);
        this.target = this.client.target(baseUrl + "/personnel/creer");
        this.response = this.target.request().post(Entity.entity(maChaine, "application/xml;charset=UTF-8"));
        response.close();

    }

}
