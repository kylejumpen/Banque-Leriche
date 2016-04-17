package banque.resources;

import java.lang.Override;
import java.lang.Short;
import java.lang.String;
import java.lang.System;
import java.util.HashMap;
import java.util.Iterator;
import javax.annotation.PostConstruct;
import javax.security.auth.login.AppConfigurationEntry;
import javax.security.auth.login.Configuration;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import banque.clientTest.Client;
import org.hibernate.*;

import banque.entity.*;
import jdk.nashorn.internal.objects.annotations.Getter;
import banque.utils.*;

@Path("banque")
public class BanqueResource {
    Session session;

//    Creer un client == OK                     /client/creer
//    Supprimer un client                       /client/supprimer{id}
//    Obtenir les infos sur un client           /client/{id}

//    Creer une banque == OK                    /creer
//    Supprimer une banque == OK                /supprimer/{id}
//    Obtenir les infos banque ==               /{id}

//    Creer un compte                           /client/compte/creer
//    Supprimer un compte                       /client/compte/supprimer/{id}
//    Effectuer opération                       /client/compte/operer
//    Epargner                                  /client/compte/epargner
//    Créditer                                  /client/compte/crediter
//    Débiter                                   /client/compte/debiter
//    Rembourser crédit                         /client/compte/rembourser-credit
//    Echanger argent                           /client/compte/echanger

//    Creer un membre du personnel              /personnel/creer
//    Supprimer un membre du personnel          /personnel/supprimer
//    Infos d'un membre du personnel            /personnel/{id}

    @POST
    @Path("/client/creer")
    @Consumes("application/xml")
    public Response creerClient(ClientBanque clientBanque) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(clientBanque);
        session.getTransaction().commit();
        session.close();
        return Response.status(200).entity(clientBanque.toString()).build();
    }

    @DELETE
    @Path("/client/supprimer/{id}")
    public Response supprimerClient(@PathParam("id") Short id) {
        session = HibernateUtil.getSessionFactory().openSession();
        ClientBanque client = (ClientBanque) session.load(ClientBanque.class, id);
        session.beginTransaction();
        session.delete(client);
        session.getTransaction().commit();
        session.close();
        return Response.status(200).entity(client.toString()).build();
    }

    @GET
    @Path("/{id}")
    @Produces("text/plain")
    public String getBanque(@PathParam("id") Short id) {
        session = HibernateUtil.getSessionFactory().openSession();

        try {
            Banque banque = (Banque) session.load(Banque.class, id);
            return banque.toString();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
        return "Aucune banque d'id " + id + " trouve";
    }

    @POST
    @Path("/creer")
    @Consumes("application/xml")
    public Response creerBanque(Banque banque) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(banque);
        session.getTransaction().commit();
        session.close();
        return Response.status(200).entity(banque.toString()).build();
    }

    @DELETE
    @Path("/supprimer/{id}")
    public Response supprimerBanque(@PathParam("id") Short id) {
        session = HibernateUtil.getSessionFactory().openSession();
        Banque banque = (Banque) session.load(Banque.class, id);
        session.beginTransaction();
        session.delete(banque);
        session.getTransaction().commit();
        session.close();
        return Response.status(200).entity(banque.toString()).build();
    }
}
