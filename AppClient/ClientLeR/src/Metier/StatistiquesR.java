/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metier;

/**
 *
 * @author pauline
 */
public class StatistiquesR extends CoRest {

    public String getNbClients() {
        target = client.target(getBaseUrl() + "/stats/clients");
        response = target.request().get();
        String rep = response.readEntity(String.class);
        response.close();
        return rep;
    }

    public String getNbOperations() {
        target = client.target(getBaseUrl() + "/stats/operations");
        response = target.request().get();
        String rep = response.readEntity(String.class);
        response.close();
        return rep;
    }

    public String getNbComptes() {
        target = client.target(getBaseUrl() + "/stats/comptes");
        response = target.request().get();
        String rep = response.readEntity(String.class);
        response.close();
        return rep;
    }
}
