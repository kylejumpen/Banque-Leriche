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

    /**
     * Permet d'obtenir le nombre de clients de la fédération
     *
     * @return le nombre de clients
     */
    public String getNbClients() {
        target = client.target(baseUrl + "/stats/clients");
        response = target.request().get();
        String rep = response.readEntity(String.class);
        response.close();
        return rep;
    }

    /**
     * Permet d'obtenir le nombre d'opérations effectuées dans la fédération
     *
     * @return le nombre d'opérations
     */
    public String getNbOperations() {
        target = client.target(baseUrl + "/stats/operations");
        response = target.request().get();
        String rep = response.readEntity(String.class);
        response.close();
        return rep;
    }

    /**
     * Permet d'obtenir le nombre de comptes de la fédération
     *
     * @return le nombre de comptes courants et epargnes
     */
    public String getNbComptes() {
        target = client.target(baseUrl + "/stats/comptes");
        response = target.request().get();
        String rep = response.readEntity(String.class);
        response.close();
        return rep;
    }
}
