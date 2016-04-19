package banque.clientTest;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.client.Entity;
import org.hibernate.*;
import banque.entity.*;
import banque.utils.*;

import java.lang.Short;
import java.lang.System;

public class Client {

    public static void main(String args[]) {
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target;
        Response response;
        Banque banque;
        ClientBanque clientBanque;
        Session session = HibernateUtil.getSessionFactory().openSession();
        String baseUrl = "http://localhost:8001/Banque-1.0/banque";

        //Param: CREER-CLIENT nom prenom mdp email code-postal idBanque
        if (args.length == 7 && args[0].equals("CREER-CLIENT")) {
            clientBanque = new ClientBanque(args[1], args[2], args[3], args[4], args[5]);
            banque = (Banque) session.load(Banque.class, Short.parseShort(args[6]));
            clientBanque.setBanque(banque);

            target = client.target(baseUrl + "/client/creer");
            response = target.request().post(Entity.entity(clientBanque, "application/xml;charset=UTF-8"));
            System.out.println("POST : " + response.getStatus());
            response.close();
            session.close();
        } //Param: SUPPRIMER-CLIENT idClient
        else if(args.length == 2 && args[0].equals("SUPPRIMER-CLIENT")) {
            target = client.target(baseUrl + "/client/supprimer/" + args[1]);
            response = target.request().delete();
            System.out.println("DELETE : " + response.getStatus());
            response.close();
        } //Param: CREER-BANQUE nom ville
        else if (args.length == 3 && args[0].equals("CREER-BANQUE")) {
            banque = new Banque(args[1], args[2]);
            target = client.target(baseUrl + "/creer");
            response = target.request().post(Entity.entity(banque, "application/xml;charset=UTF-8"));
            System.out.println("POST : " + response.getStatus());
            response.close();
        } //Param: SUPPRIMER-BANQUE idBanque
        else if(args.length == 2 && args[0].equals("SUPPRIMER-BANQUE")) {
            target = client.target(baseUrl + "/supprimer/" + args[1]);
            response = target.request().delete();
            System.out.println("DELETE : " + response.getStatus());
            response.close();
        }
        //Param: GET-BANQUE idBanque
        else if (args.length == 2 && args[0].equals("GET-BANQUE")) {
            target = client.target(baseUrl + "/" + args[1]);
            response = target.request().get();
            System.out.println(args[1] + " : " + response.readEntity(String.class));
            response.close();
        }
        //Param: CREER-COMPTE idBanque
        else if (args.length == 2 && args[0].equals("CREER-COMPTE")) {
            ClientBanque clientCC = (ClientBanque) session.load(ClientBanque.class, Short.parseShort(args[1]));
            int iban = BanqueUtil.genererIban();
            CompteCourant compteCourant = new CompteCourant(clientCC, iban);
            target = client.target(baseUrl + "/client/compte/creer");
            response = target.request().post(Entity.entity(compteCourant, "application/xml;charset=UTF-8"));
            System.out.println("POST : " + response.getStatus());
            response.close();
            session.close();
        } else {
            System.out.println("Le premier paramètre est mauvais ou le nb de paramètre n'est pas bon");
        }
    }
}
