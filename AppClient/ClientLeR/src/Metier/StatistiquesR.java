package Metier;

/**
 *
 * @author pauline
 */
public class StatistiquesR extends CoRest {

    /**
     * <b>author</b> pauline
     * Permet d'obtenir le nombre de clients de la fédération
     *
     * @return le nombre de clients
     */
    public String getNbClients() {
        target = client.target(getBaseUrl() + "/stats/clients");
        response = target.request().get();
        String rep = response.readEntity(String.class);
        response.close();
        return rep;
    }

    /**
     * <b>author</b> pauline
     * Permet d'obtenir le nombre d'opérations effectuées dans la fédération
     *
     * @return le nombre d'opérations
     */
    public String getNbOperations() {
        target = client.target(getBaseUrl() + "/stats/operations");
        response = target.request().get();
        String rep = response.readEntity(String.class);
        response.close();
        return rep;
    }

    /**
     * <b>author</b> pauline
     * Permet d'obtenir le nombre de comptes de la fédération
     *
     * @return le nombre de comptes courants et epargnes
     */
    public String getNbComptes() {
        target = client.target(getBaseUrl() + "/stats/comptes");
        response = target.request().get();
        String rep = response.readEntity(String.class);
        response.close();
        return rep;
    }
}
