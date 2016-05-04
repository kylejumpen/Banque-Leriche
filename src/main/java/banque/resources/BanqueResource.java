package banque.resources;

import java.lang.Short;
import java.lang.String;
import java.lang.System;
import java.util.HashMap;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.*;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.*;

import org.hibernate.*;

import banque.entity.*;
import banque.utils.*;

@Path("banque")
public class BanqueResource {
    Session session;
    String failure = "{\"succes\": \"false\"}";

//    Creer un client                           /client/creer
//    Supprimer un client                      /client/supprimer{id}
//    Obtenir les infos sur un client      /client/{id}

//    Creer une banque                           /creer
//    Supprimer une banque                       /supprimer/{id}
//    Obtenir les infos banque             /{id}

//    Creer un compte                           /client/compte/creer
//    Supprimer un compte                       /client/compte/supprimer/{id}
//    Effectuer opération                       /client/compte/operer
//    SUPPIMEE Epargner                                  /client/compte/epargner
//    SUPPRIMEE Créditer                                  /client/compte/crediter
//    SUPPRIMEE Débiter                                   /client/compte/debiter
//    SUPPRIMEE Rembourser crédit                         /client/compte/rembourser-credit
//    SUPPRIMEE Echanger argent                           /client/compte/echanger

//    Creer un membre du personnel              /personnel/creer
//    Supprimer un membre du personnel          /personnel/supprimer
//    Infos d'un membre du personnel            /personnel/{id}


    //CLIENT _______________________________________________________________________

    @POST
    @Path("/client/creer")
    @Consumes("application/xml")
    public Response creerClient(String chaine) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Gson gson = new Gson();
        HashMap<String, String> args = gson.fromJson(chaine, new TypeToken<HashMap<String, String>>() {
        }.getType());

        ClientBanque clientBanque = new ClientBanque(
                args.get("nom"),
                args.get("prenom"),
                args.get("mdp"),
                args.get("email"),
                args.get("codePostal")
        );

        Banque banque = (Banque) session.load(Banque.class, Short.parseShort(args.get("idBanque")));
        clientBanque.setBanque(banque);

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
    @Path("/client/{id}")
    @Produces("text/plain")
    public String getClientBanque(@PathParam("id") Short id) {
        session = HibernateUtil.getSessionFactory().openSession();

        try {
            ClientBanque client = (ClientBanque) session.load(ClientBanque.class, id);
            return client.toString();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
        return this.failure;
    }


    //BANQUE _______________________________________________________________________

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
        return this.failure;
    }


    @POST
    @Path("/creer")
    @Consumes("application/xml")
    public Response creerBanque(String chaine) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Gson gson = new Gson();
        HashMap<String, String> args = gson.fromJson(chaine, new TypeToken<HashMap<String, String>>() {
        }.getType());

        Banque banque = new Banque(
                args.get("nom"),
                args.get("ville")
        );

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
    @Path("/client/compte-courant/creer")
    @Consumes("application/xml")
    public Response creerCompte(String chaine) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Gson gson = new Gson();
        HashMap<String, String> args = gson.fromJson(chaine, new TypeToken<HashMap<String, String>>() {
        }.getType());

        int iban = BanqueUtil.genererIban();
        ClientBanque clientCC = (ClientBanque) session.load(ClientBanque.class, Short.parseShort(args.get("idClient")));
        CompteCourant compteCourant = new CompteCourant(clientCC, iban);

        session.save(compteCourant);
        session.getTransaction().commit();
        session.close();
        return Response.status(200).entity(compteCourant.toString()).build();
    }

    @DELETE
    @Path("/client/compte-courant/supprimer/{id}")
    public Response supprimerCompte(@PathParam("id") Short id) {
        session = HibernateUtil.getSessionFactory().openSession();
        CompteCourant cc = (CompteCourant) session.load(CompteCourant.class, id);
        session.beginTransaction();
        session.delete(cc);
        session.getTransaction().commit();
        session.close();
        return Response.status(200).entity(cc.toString()).build();
    }

    @GET
    @Path("/client/compte-courant/{id}")
    @Produces("text/plain")
    public String getCompteCourant(@PathParam("id") Short id) {
        session = HibernateUtil.getSessionFactory().openSession();

        try {
            CompteCourant compteCourant = (CompteCourant) session.load(CompteCourant.class, id);
            return compteCourant.toString();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
        return this.failure;
    }

//    @GET
//    @Path("/client/liste-comptes-courants")
//    @Produces("text/plain")
//    public String getListComptesCourants() {
//        session = HibernateUtil.getSessionFactory().openSession();
//
//        try {
//            List<CompteEpargne> comptes = session.createCriteria(CompteEpargne.class).list();
//            for(CompteEpargne temp : comptes) {
//                temp.setMontant(temp.getMontant() + temp.getMontant() * temp.getTauxInteret() * 0.01f);
//                session.update(temp);
//            }
//            return compteCourant.toString();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        } finally {
//            session.close();
//        }
//        return this.failure;
//    }





    //OPERATIONS _______________________________________________________________________
    @POST
    @Path("/client/compte/operer")
    @Consumes("application/xml")
    public Response operer(String chaine) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Gson gson = new Gson();
        HashMap<String, String> args = gson.fromJson(chaine, new TypeToken<HashMap<String, String>>() {
        }.getType());

        Operation operation = new Operation(
                args.get("type"),
                Short.parseShort(args.get("idCompteADebiter")),
                Short.parseShort(args.get("idCompteACrediter")),
                Integer.parseInt(args.get("montant"))
        );

        if (args.get("typeCompteADebiter").equals("courant")) {
            CompteCourant compteADebiter = (CompteCourant) session.load(CompteCourant.class, Short.parseShort(args.get("idCompteADebiter")));
            compteADebiter.setMontant(compteADebiter.getMontant() - Integer.parseInt(args.get("montant")));
            session.update(compteADebiter);
        } else {
            CompteEpargne compteADebiter = (CompteEpargne) session.load(CompteEpargne.class, Short.parseShort(args.get("idCompteADebiter")));
            compteADebiter.setMontant(compteADebiter.getMontant() - Integer.parseInt(args.get("montant")));
            session.update(compteADebiter);
        }

        if (args.get("typeCompteACrediter").equals("courant")) {
            CompteCourant compteACrediter = (CompteCourant) session.load(CompteCourant.class, Short.parseShort(args.get("idCompteACrediter")));
            compteACrediter.setMontant(compteACrediter.getMontant() + Integer.parseInt(args.get("montant")));
            session.update(compteACrediter);
        } else {
            CompteEpargne compteACrediter = (CompteEpargne) session.load(CompteEpargne.class, Short.parseShort(args.get("idCompteACrediter")));
            compteACrediter.setMontant(compteACrediter.getMontant() + Integer.parseInt(args.get("montant")));
            session.update(compteACrediter);
        }

        session.save(operation);
        session.getTransaction().commit();
        session.close();
        return Response.status(200).entity(operation.toString()).build();
    }


    //Personnel _______________________________________________________________________

    @GET
    @Path("/personnel/{id}")
    @Produces("text/plain")
    public String getPersonnel(@PathParam("id") Short id) {
        session = HibernateUtil.getSessionFactory().openSession();

        try {
            Personnel personnel = (Personnel) session.load(Personnel.class, id);
            return personnel.toString();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
        return this.failure;
    }


    @POST
    @Path("/personnel/creer")
//    @Consumes("application/xml")
    public Response creerPersonnel(String chaine) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Gson gson = new Gson();
        HashMap<String, String> args = gson.fromJson(chaine, new TypeToken<HashMap<String, String>>() {
        }.getType());

        Banque banque = (Banque) session.load(Banque.class, Short.parseShort(args.get("idBanque")));

        Personnel personnel = new Personnel(
                banque,
                args.get("nom"),
                args.get("mdp"),
                args.get("role")
        );

        session.save(personnel);
        session.getTransaction().commit();
        session.close();
        return Response.status(200).entity(personnel.toString()).build();
    }


    @DELETE
    @Path("/personnel/supprimer/{id}")
    public Response supprimerPersonnel(@PathParam("id") Short id) {
        session = HibernateUtil.getSessionFactory().openSession();
        Personnel personnel = (Personnel) session.load(Personnel.class, id);
        session.beginTransaction();
        session.delete(personnel);
        session.getTransaction().commit();
        session.close();
        return Response.status(200).entity(personnel.toString()).build();
    }

    @PUT
    @Path("/epargner")
    @Consumes("application/xml")
    public Response epargner(String chaine) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        List<CompteEpargne> comptes = session.createCriteria(CompteEpargne.class).list();
        for(CompteEpargne temp : comptes) {
            temp.setMontant(temp.getMontant() + temp.getMontant() * temp.getTauxInteret() * 0.01f);
            session.update(temp);
        }

        session.getTransaction().commit();
        session.close();

        return Response.status(200).entity(chaine).build();
    }
}
