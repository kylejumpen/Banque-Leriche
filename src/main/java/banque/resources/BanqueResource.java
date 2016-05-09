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
import banque.security.*;

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
	try{
        HashMap<String, String> args = gson.fromJson(Encrypt.decryptData(chaine), new TypeToken<HashMap<String, String>>() {
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
	//MailUtil.sendEmail("smtp.gmail.com", 587, "banque.inforep@gmail.com", "florian.leriche@neuf.fr", "Testance", "Envoyance de mail via java dans la fonctionnellance des familles");
        return Response.status(200).entity(Encrypt.encryptData(clientBanque.toString())).build();
}catch(Exception e){ return Response.status(500).build();}
    }

    @DELETE
    @Path("/client/supprimer/{id}")
    public Response supprimerClient(@PathParam("id") Short id) {
	try{
        session = HibernateUtil.getSessionFactory().openSession();
        ClientBanque client = (ClientBanque) session.load(ClientBanque.class, id);
        session.beginTransaction();
        session.delete(client);
        session.getTransaction().commit();
        session.close();
        return Response.status(200).entity(Encrypt.encryptData(client.toString())).build();
	}catch(Exception e){ return Response.status(500).build();}
    }


    @GET
    @Path("/client/{id}")
    @Produces("text/plain")
    public String getClientBanque(@PathParam("id") String idc) {
        session = HibernateUtil.getSessionFactory().openSession();

        try {
	    short id = Short.parseShort(Encrypt.decryptId(idc));
            ClientBanque client = (ClientBanque) session.load(ClientBanque.class, id);
            return Encrypt.encryptData(client.toString());
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
	try{
        HashMap<String, String> args = gson.fromJson(Encrypt.decryptData(chaine), new TypeToken<HashMap<String, String>>() {
        }.getType());

        Banque banque = new Banque(
                args.get("nom"),
                args.get("ville")
        );

        session.save(banque);
        session.getTransaction().commit();
        session.close();
        return Response.status(200).entity(Encrypt.encryptData(banque.toString())).build();
	}catch(Exception e) { return Response.status(500).build();}
    }

    @DELETE
    @Path("/supprimer/{id}")
    public Response supprimerBanque(@PathParam("id") String idc) {
	 try {
	    short id = Short.parseShort(Encrypt.decryptId(idc));
        session = HibernateUtil.getSessionFactory().openSession();
        Banque banque = (Banque) session.load(Banque.class, id);
        session.beginTransaction();
        session.delete(banque);
        session.getTransaction().commit();
        session.close();
        return Response.status(200).entity(Encrypt.encryptData(banque.toString())).build();
	}catch(Exception e){ return Response.status(500).build(); }
    }



    //COMPTE _______________________________________________________________________
    @GET
    @Path("/compte/courant/{id}")
    @Produces("text/plain")
    public String getCompteCourantFromClient(@PathParam("id") String idc) {
        session = HibernateUtil.getSessionFactory().openSession();

        try {
	    short id = Short.parseShort(Encrypt.decryptId(idc));
            Query q = session.createQuery(
                    "FROM CompteCourant cc WHERE cc.clientBanque.clientBanqueId=:id");
            q.setParameter("id", id);
            List<?> result = q.list();
            CompteCourant cc = (CompteCourant)result.get(0);
            return Encrypt.encryptData(cc.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
        return this.failure;
    }

    @GET
    @Path("/compte/epargne/{id}")
    @Produces("text/plain")
    public String getCompteEpargneFromClient(@PathParam("id") String idc) {
        session = HibernateUtil.getSessionFactory().openSession();

        try {
	    short id = Short.parseShort(Encrypt.decryptId(idc));
            Query q = session.createQuery(
                    "FROM CompteEpargne ce WHERE ce.clientBanque.clientBanqueId=:id");
            q.setParameter("id", id);
            List<?> result = q.list();
            CompteEpargne ce = (CompteEpargne)result.get(0);
            return Encrypt.encryptData(ce.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
        return this.failure;
    }

    @POST
    @Path("/client/compte-courant/creer")
    @Consumes("application/xml")
    public Response creerCompteCourant(String chaine) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Gson gson = new Gson();
	try{
        HashMap<String, String> args = gson.fromJson(Encrypt.decryptData(chaine), new TypeToken<HashMap<String, String>>() {
        }.getType());

        int iban = BanqueUtil.genererIban("courant");
        ClientBanque clientCC = (ClientBanque) session.load(ClientBanque.class, Short.parseShort(args.get("idClient")));
        CompteCourant compteCourant = new CompteCourant(clientCC, iban);

        session.save(compteCourant);
        session.getTransaction().commit();
        session.close();
        return Response.status(200).entity(Encrypt.encryptData(compteCourant.toString())).build();
	}catch(Exception e){return Response.status(500).build();}
    }

    @POST
    @Path("/client/compte-epargne/creer")
    @Consumes("application/xml")
    public Response creerCompteEpargne(String chaine) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Gson gson = new Gson();
	try{
        HashMap<String, String> args = gson.fromJson(Encrypt.decryptData(chaine), new TypeToken<HashMap<String, String>>() {
        }.getType());

        int iban = BanqueUtil.genererIban("epargne");
        ClientBanque clientCC = (ClientBanque) session.load(ClientBanque.class, Short.parseShort(args.get("idClient")));
        CompteEpargne compteEpargne = new CompteEpargne(clientCC, iban);

        session.save(compteEpargne);
        session.getTransaction().commit();
        session.close();
        return Response.status(200).entity(Encrypt.encryptData(compteEpargne.toString())).build();
	}catch(Exception e){ return Response.status(500).build();}
    }

    @DELETE
    @Path("/client/compte-courant/supprimer/{id}")
    public Response supprimerCompteCourant(@PathParam("id") String idc) {
        session = HibernateUtil.getSessionFactory().openSession();
	try {
	    short id = Short.parseShort(Encrypt.decryptId(idc));
            CompteCourant cc = (CompteCourant) session.load(CompteCourant.class, id);
            session.beginTransaction();
            session.delete(cc);
            session.getTransaction().commit();
            session.close();
            return Response.status(200).entity(Encrypt.encryptData(cc.toString())).build();
	    }catch(Exception e){ return Response.status(500).build();}
    }

    @DELETE
    @Path("/client/compte-epargne/supprimer/{id}")
    public Response supprimerCompteEpargne(@PathParam("id") String idc) {
        session = HibernateUtil.getSessionFactory().openSession();
	try {
	     short id = Short.parseShort(Encrypt.decryptId(idc));
        CompteEpargne ce = (CompteEpargne) session.load(CompteEpargne.class, id);
        session.beginTransaction();
        session.delete(ce);
        session.getTransaction().commit();
        session.close();
        return Response.status(200).entity(Encrypt.encryptData(ce.toString())).build();
        }catch(Exception e){ return Response.status(500).build();}
    }

    @GET
    @Path("/client/compte-courant/{id}")
    @Produces("text/plain")
    public String getCompteCourant(@PathParam("id") String idc) {
        session = HibernateUtil.getSessionFactory().openSession();

        try {
	    short id = Short.parseShort(Encrypt.decryptId(idc));
            CompteCourant compteCourant = (CompteCourant) session.load(CompteCourant.class, id);
            return Encrypt.encryptData(compteCourant.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
        return this.failure;
    }

    //    LISTES _____
    @GET
    @Path("/liste/comptes-courant/{id}")
    @Produces("text/plain")
    public String getListesComptesCourant(@PathParam("id") String idBanquec) {
        session = HibernateUtil.getSessionFactory().openSession();
        try {
	    short idBanque = Short.parseShort(Encrypt.decryptId(idBanquec));
            List<CompteCourant> comptes = session.createCriteria(CompteCourant.class).list();
            Gson gson = new Gson();
            HashMap<String, String> jsonArgs = new HashMap<String, String>();
            int i = 0;
            for (CompteCourant temp : comptes) {
                if (temp.getClientBanque().getBanque().getBanqueId().equals(idBanque)) {
                    i++;
                    jsonArgs.put("compte-" + i, temp.toString());
                }
            }
            return Encrypt.encryptData(gson.toJson(jsonArgs));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
        return this.failure;
    }

    @GET
    @Path("/liste/comptes-epargne/{id}")
    @Produces("text/plain")
    public String getListesComptesEpargne(@PathParam("id") String idBanquec) {
        session = HibernateUtil.getSessionFactory().openSession();
        try {
	    short idBanque = Short.parseShort(Encrypt.decryptId(idBanquec));
            List<CompteEpargne> comptes = session.createCriteria(CompteEpargne.class).list();
            Gson gson = new Gson();
            HashMap<String, String> jsonArgs = new HashMap<String, String>();
            int i = 0;
            for (CompteEpargne temp : comptes) {
                if (temp.getClientBanque().getBanque().getBanqueId().equals(idBanque)) {
                    i++;
                    jsonArgs.put("compte-" + i, temp.toString());
                }
            }
            return Encrypt.encryptData(gson.toJson(jsonArgs));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
        return this.failure;
    }

    //    BLOQUER
    @PUT
    @Path("/client/compte/bloquer")
    @Consumes("application/xml")
    public Response bloquerCompte(String chaine) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Gson gson = new Gson();
	try{
        HashMap<String, String> args = gson.fromJson(Encrypt.decryptData(chaine), new TypeToken<HashMap<String, String>>() {
        }.getType());

        if (args.get("type").equals("courant")) {
            CompteCourant cc = (CompteCourant) session.load(CompteCourant.class, Short.parseShort(args.get("idCompte")));
            cc.setBloque(!cc.getBloque());
            session.update(cc);
        } else {
            CompteEpargne ce = (CompteEpargne) session.load(CompteEpargne.class, Short.parseShort(args.get("idCompte")));
            ce.setBloque(!ce.getBloque());
            session.update(ce);
        }

        session.getTransaction().commit();
        session.close();

        return Response.status(200).entity(Encrypt.encryptData(chaine)).build();
	}catch(Exception e){return Response.status(500).build();}
    }


    //OPERATIONS _______________________________________________________________________
    @POST
    @Path("/client/compte/operer")
    @Consumes("application/xml")
    public Response operer(String chaine) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Gson gson = new Gson();
	try{
        HashMap<String, String> args = gson.fromJson(Encrypt.decryptData(chaine), new TypeToken<HashMap<String, String>>() {
        }.getType());

        Operation operation = new Operation(
                args.get("type"),
                Short.parseShort(args.get("idCompteADebiter")),
                Short.parseShort(args.get("idCompteACrediter")),
                Integer.parseInt(args.get("montant"))
        );

        if (args.get("typeCompteADebiter").equals("courant")) {
            CompteCourant compteADebiter = (CompteCourant) session.load(CompteCourant.class, Short.parseShort(args.get("idCompteADebiter")));
            if (!compteADebiter.getBloque()) {
                compteADebiter.setMontant(compteADebiter.getMontant() - Integer.parseInt(args.get("montant")));
                session.update(compteADebiter);
            }
        } else {
            CompteEpargne compteADebiter = (CompteEpargne) session.load(CompteEpargne.class, Short.parseShort(args.get("idCompteADebiter")));
            if (!compteADebiter.getBloque()) {
                compteADebiter.setMontant(compteADebiter.getMontant() - Integer.parseInt(args.get("montant")));
                session.update(compteADebiter);
            }
        }

        if (args.get("typeCompteACrediter").equals("courant")) {
            CompteCourant compteACrediter = (CompteCourant) session.load(CompteCourant.class, Short.parseShort(args.get("idCompteACrediter")));
            if (!compteACrediter.getBloque()) {
                compteACrediter.setMontant(compteACrediter.getMontant() + Integer.parseInt(args.get("montant")));
                session.update(compteACrediter);
            }
        } else {
            CompteEpargne compteACrediter = (CompteEpargne) session.load(CompteEpargne.class, Short.parseShort(args.get("idCompteACrediter")));
            if (!compteACrediter.getBloque()) {
                compteACrediter.setMontant(compteACrediter.getMontant() + Integer.parseInt(args.get("montant")));
                session.update(compteACrediter);
            }
        }

        session.save(operation);
        session.getTransaction().commit();
        session.close();
        return Response.status(200).entity(Encrypt.encryptData(operation.toString())).build();
	}catch(Exception e){ return Response.status(500).build();}
    }


    //Personnel _______________________________________________________________________

    @GET
    @Path("/personnel/{id}")
    @Produces("text/plain")
    public String getPersonnel(@PathParam("id") String idc) {
        session = HibernateUtil.getSessionFactory().openSession();

        try {
	    short id = Short.parseShort(Encrypt.decryptId(idc));
            Personnel personnel = (Personnel) session.load(Personnel.class, id);
            return Encrypt.encryptData(personnel.toString());
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
	try{
        HashMap<String, String> args = gson.fromJson(Encrypt.decryptData(chaine), new TypeToken<HashMap<String, String>>() {
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
        return Response.status(200).entity(Encrypt.encryptData(personnel.toString())).build();
	}catch(Exception e){
	return Response.status(500).build();
	}
    }


    @DELETE
    @Path("/personnel/supprimer/{id}")
    public Response supprimerPersonnel(@PathParam("id") Short id) {
	try{
        session = HibernateUtil.getSessionFactory().openSession();
        Personnel personnel = (Personnel) session.load(Personnel.class, id);
        session.beginTransaction();
        session.delete(personnel);
        session.getTransaction().commit();
        session.close();
        return Response.status(200).entity(Encrypt.encryptData(personnel.toString())).build();
	}catch(Exception e){return Response.status(500).build();}
    }

    @GET
    @Path("/epargner/{key}")
    @Produces("text/plain")
    public String epargner(@PathParam("key") String key) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        List<CompteEpargne> comptes = session.createCriteria(CompteEpargne.class).list();
        for (CompteEpargne temp : comptes) {
            temp.setMontant(temp.getMontant() + temp.getMontant() * temp.getTauxInteret() * 0.01f);
            session.update(temp);
        }

        session.getTransaction().commit();
        session.close();

        return "voila";
    }

    //    STATISTIQUES
    @GET
    @Path("/stats/comptes")
    @Produces("text/plain")
    public String getNbComptes() {
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            List<CompteCourant> comptesC = session.createCriteria(CompteCourant.class).list();
            List<CompteEpargne> comptesE = session.createCriteria(CompteEpargne.class).list();
            int nbCourant = comptesC.size();
            int nbEpargne = comptesE.size();
            int total = nbCourant + nbEpargne;

            Gson gson = new Gson();
            HashMap<String, String> jsonArgs = new HashMap<String, String>();
            jsonArgs.put("courants", Integer.toString(nbCourant));
            jsonArgs.put("epargnes", Integer.toString(nbEpargne));
            jsonArgs.put("total", Integer.toString(total));
            return gson.toJson(jsonArgs);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
        return this.failure;
    }

    @GET
    @Path("/stats/clients")
    @Produces("text/plain")
    public String getNbClients() {
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            List<ClientBanque> clients = session.createCriteria(ClientBanque.class).list();
            int nbClients = clients.size();

            Gson gson = new Gson();
            HashMap<String, String> jsonArgs = new HashMap<String, String>();
            jsonArgs.put("clients", Integer.toString(nbClients));
            return gson.toJson(jsonArgs);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
        return this.failure;
    }

    @GET
    @Path("/stats/operations")
    @Produces("text/plain")
    public String getNbOperations() {
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            List<Operation> operations = session.createCriteria(Operation.class).list();
            int nbOperations = operations.size();

            Gson gson = new Gson();
            HashMap<String, String> jsonArgs = new HashMap<String, String>();
            jsonArgs.put("operations", Integer.toString(nbOperations));
            return gson.toJson(jsonArgs);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
        return this.failure;
    }

}
