/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    
    
    
    public String supprimerBanque(int id) {
        target = client.target(baseUrl + "/supprimer/" + id);
        response = target.request().delete();
        String reponse = response.readEntity(String.class);
        System.out.println(response.getStatusInfo());
        response.close();
        return reponse;
    }
    
        public void ajouterBanque(String nom, String ville) {

        jsonArgs.put("nom", nom);
        jsonArgs.put("ville", ville);

        String maChaine; //Transformation en chaine de caractère
        maChaine = gson.toJson(jsonArgs);
        this.target = this.client.target(baseUrl + "/creer");
        this.response = this.target.request().post(Entity.entity(maChaine, "application/xml;charset=UTF-8"));
        response.close();
    }
    
}
