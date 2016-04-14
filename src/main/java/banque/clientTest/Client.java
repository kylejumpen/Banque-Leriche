package banque.clientTest;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.client.Entity;

import banque.entity.*;

public class Client {

    public static void main(String args[]) {
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target;
        Response response;
        Banque banque;
        String baseUrl = "http://localhost:8001/Banque-1.0/banque";

        if (args.length >= 2 && args[0].equals("GET")) {
            target = client.target(baseUrl + "/" + args[1]);
            response = target.request().get();
            System.out.println(args[1] + " : " + response.readEntity(String.class));
            response.close();
        } else if (args.length == 4 && args[0].equals("POST")) {
            banque = new Banque(args[1], args[2]);
            banque.setBanqueId(Short.parseShort(args[3]));

            target = client.target(baseUrl + "/create");
            response = target.request().post(Entity.entity(banque, "application/xml;charset=UTF-8"));
            System.out.println("POST : " + response.getStatus());
            response.close();
        }
    }
}