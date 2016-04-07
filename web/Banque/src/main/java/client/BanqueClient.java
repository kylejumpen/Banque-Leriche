package client;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.client.Entity;

import annuaire.Personne;

public class AnnuaireClient {
  public static void main(String[] args) {
    ResteasyClient client = new ResteasyClientBuilder().build();
    ResteasyWebTarget target;
    Response response;
    Personne asi = new Personne("Jean", "69");
    String baseURL = "http://localhost:8001/";

    target = client.target(baseURL + "annuaire/personnes");
    response = target.request().get();
    System.out.println("Test : " + response.readEntity(String.class));
    response.close(); 
  }
}
