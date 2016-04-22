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

//    Creer un client                           /client/creer
//    Supprimer un client                      /client/supprimer{id}
//    TODO Obtenir les infos sur un client      /client/{id}

//    Creer une banque                           /creer
//    Supprimer une banque                       /supprimer/{id}
//    Obtenir les infos banque             /{id}

//    Creer un compte                           /client/compte/creer
//     Supprimer un compte                       /client/compte/supprimer/{id}
//    TODO Effectuer opération                       /client/compte/operer
//    TODO Epargner                                  /client/compte/epargner
//    TODO Créditer                                  /client/compte/crediter
//    TODO Débiter                                   /client/compte/debiter
//    TODO Rembourser crédit                         /client/compte/rembourser-credit
//    TODO Echanger argent                           /client/compte/echanger

//    TODO Creer un membre du personnel              /personnel/creer
//    TODO Supprimer un membre du personnel          /personnel/supprimer
//    TODO Infos d'un membre du personnel            /personnel/{id}

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

    @GET
    @Path("/client/compteCourant/{id}")
    @Produces("text/plain")
    public String getCompteCourant(@PathParam("id") Short id) {
        session = HibernateUtil.getSessionFactory().openSession();

        try {
            CompteCourant compteCourant = (CompteCourant) session.load(CompteCourant.class, id);
            return compteCourant.getCompteCourantId()+"-"+compteCourant.getClientBanque().getNom()+"-"+compteCourant.getClientBanque().getPrenom()+"-"+compteCourant.getMontant();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
        return "Aucun compte courant de numéro " + id + " trouve";
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

    //COMPTE _______________________________________________________________________

    @POST
    @Path("/client/compte/creer")
    @Consumes("application/xml")
    public Response creerCompte(CompteCourant compte) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(compte);
        session.getTransaction().commit();
        session.close();
        return Response.status(200).entity(compte.toString()).build();
    }

    @DELETE
    @Path("/client/compte/supprimer/{id}")
    public Response supprimerCompte(@PathParam("id") Short id) {
        session = HibernateUtil.getSessionFactory().openSession();
        CompteCourant cc = (CompteCourant) session.load(CompteCourant.class, id);
        session.beginTransaction();
        session.delete(cc);
        session.getTransaction().commit();
        session.close();
        return Response.status(200).entity(cc.toString()).build();
    }
}
