/**
 * La classe BanqueResource traite toutes les requêtes REST
 *
 * @author  Kafui Atanley, Quentin Lerebours, Florian Leriche
 * @version 1.0
 * @since   2016-05-10
 */

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
    String success = "{\"succes\": \"true\"}";

    //CLIENT _______________________________________________________________________

    /**
     * <b>Auteurs:</b> Florian Leriche, Quentin Lerebours
     * Cette méthode permet de créer un client
     *
     * @param chaine Les informations en lien avec le client pour sa création (banque, nom, prenom
     *               mot de passe, email et code postal)
     * @return Response est le paramètre contenant les informations renvoyées au client pour valider la création
     */
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
	    MailUtil.sendEmail("smtp.gmail.com", 587, "banque.inforep@gmail.com", args.get("email"), "Création de compte", "Félécitation votre compte bancaire a bien été créé");
        return Response.status(200).entity(Encrypt.encryptData(clientBanque.toString())).build();
}catch(Exception e){ return Response.status(500).build();}
    }

    /**
     * <b>Auteurs:</b> Florian Leriche, Quentin Lerebours
     * Cette méthode permet de supprimer un client
     *
     * @param id L'id chiffré du compte à supprimer
     * @return Response est le paramètre contenant les informations renvoyées au client pour valider la suppression
     */
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

    /**
     * <b>Auteurs:</b> Florian Leriche, Quentin Lerebours
     * Cette méthode permet d'obtenir les informations sur un client
     *
     * @param idc L'id chiffré du client dont on souhaite obtenir les informations
     * @return Une chaine de caractère contenant les informations sur le client en JSON
     */
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

    /**
     * <b>Auteurs:</b> Florian Leriche, Quentin Lerebours
     * Cette méthode permet d'obtenir les informations sur une banque
     *
     * @param id L'id chiffré de la banque dont on souhaite obtenir les informations
     * @return Une chaine de caractère contenant les informations sur la banque en JSON
     */
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

    /**
     * <b>Auteurs:</b> Florian Leriche, Quentin Lerebours
     * Cette méthode permet de créer une banque
     *
     * @param chaine La chaine contenant les informations sur la banque à créer
     * @return Response est le paramètre contenant les informations renvoyées au client pour valider la création de la banque
     */
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

    /**
     * <b>Auteurs:</b> Florian Leriche, Quentin Lerebours
     * Cette méthode permet de supprimer une banque
     *
     * @param idc L'id chiffré de la banque qu'on souhaite supprimer
     * @return Response est le paramètre contenant les informations renvoyées au client pour valider la suppression de la banque
     */
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


    /**
     * <b>Auteurs:</b> Florian Leriche, Quentin Lerebours
     * Cette méthode permet d'obtenir les informations sur un compte courant
     *
     * @param idc L'id chiffré du compte courant dont on souhaite obtenir les informations
     * @return Une chaine de caractère contenant les informations sur le compte en JSON
     */
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

    /**
     * <b>Auteurs:</b> Florian Leriche, Quentin Lerebours
     * Cette méthode permet d'obtenir les informations sur un compte épargne
     *
     * @param idc L'id chiffré du compte épargne dont on souhaite obtenir les informations
     * @return Une chaine de caractère contenant les informations sur le compte en JSON
     */
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

    /**
     * <b>Auteurs:</b> Florian Leriche, Quentin Lerebours
     * Cette méthode permet de créer un compte courant
     *
     * @param chaine Les informations du compte courant à créer
     * @return Response est le paramètre contenant les informations renvoyées au client pour valider la création du compte
     */
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

    /**
     * <b>Auteurs:</b> Florian Leriche, Quentin Lerebours
     * Cette méthode permet de créer un compte épargne
     *
     * @param chaine Les informations du compte courant à créer
     * @return Response est le paramètre contenant les informations renvoyées au client pour valider la création du compte
     */
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

    /**
     * <b>Auteurs:</b> Florian Leriche, Quentin Lerebours
     * Cette méthode permet de supprimer un compte courant
     *
     * @param idc L'id chiffré du compte courant à supprimer
     * @return Response est le paramètre contenant les informations renvoyées au client pour valider la suppression du compte
     */
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

    /**
     * <b>Auteurs:</b> Florian Leriche, Quentin Lerebours
     * Cette méthode permet de supprimer un compte épargne
     *
     * @param idc L'id chiffré du compte épargne à supprimer
     * @return Response est le paramètre contenant les informations renvoyées au client pour valider la création du compte
     */
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

//    @GET
//    @Path("/client/compte-courant/{id}")
//    @Produces("text/plain")
//    public String getCompteCourant(@PathParam("id") String idc) {
//        session = HibernateUtil.getSessionFactory().openSession();
//
//        try {
//	    short id = Short.parseShort(Encrypt.decryptId(idc));
//            CompteCourant compteCourant = (CompteCourant) session.load(CompteCourant.class, id);
//            return Encrypt.encryptData(compteCourant.toString());
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        } finally {
//            session.close();
//        }
//        return this.failure;
//    }


    //    LISTES _____

    /**
     * <b>Auteurs:</b> Florian Leriche, Quentin Lerebours
     * Cette méthode permet d'obtenir la liste des comptes courant
     *
     * @param idBanquec L'id de la banque chiffrée dont on veut obtenir la liste des comptes courant
     * @return La chaine JSON retournant la liste des comptes courant
     */
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

    /**
     * <b>Auteurs:</b> Florian Leriche, Quentin Lerebours
     * Cette méthode permet d'obtenir la liste des comptes épargne
     *
     * @param idBanquec L'id de la banque chiffrée dont on veut obtenir la liste des comptes épargne
     * @return La chaine JSON retournant la liste des comptes épargne
     */
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

    /**
     * <b>Auteurs:</b> Florian Leriche, Quentin Lerebours
     * Cette méthode permet de bloquer un compte
     *
     * @param chaine La chaine contenant les informations sur le compte à bloquer
     * @return Response est le paramètre contenant les informations renvoyées au client pour valider le bloquage du compte
     */
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

    /**
     * <b>Auteurs:</b> Florian Leriche, Quentin Lerebours
     * Cette méthode permet d'effectuer une opération entre deux comptes
     *
     * @param chaine Les informations sur les comptes à créditer, débiter et le montant en question
     * @return Response est le paramètre contenant les informations renvoyées au client pour valider l'opération
     */
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

    /**
     * <b>Auteurs:</b> Florian Leriche, Quentin Lerebours
     * Cette méthode permet d'obtenir les informations sur un employé
     *
     * @param idc L'id chiffré du personnel dont on souhaite les informations
     * @return Les informations encodées en JSON contenant les informations souhaitées
     */
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

    /**
     * <b>Auteurs:</b> Florian Leriche, Quentin Lerebours
     * Cette méthode permet de créer un employé
     *
     * @param chaine La chaine contenant les informations sur l'employé à créer
     * @return La response contient les informations validant la création du compte
     */
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

    /**
     * <b>Auteurs:</b> Florian Leriche, Quentin Lerebours
     * Cette méthode permet de supprimer un employé
     *
     * @param id L'id chiffré sur l'employé à créer
     * @return Les informations validant la suppression de l'employé
     */
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

    /**
     * <b>Auteurs:</b> Florian Leriche, Quentin Lerebours
     * Cette méthode permet de faire gagner les interêts aux clients.
     *
     * @param key La clé unique permettant de s'assurer que seulement le cron annuel peut utiliser cette fonctionnalité
     * @return Une chaine validant le fonctionnement de la fonctionnalité
     */
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

        return this.success;
    }

    //    STATISTIQUES

    /**
     * <b>Auteurs:</b> Florian Leriche, Quentin Lerebours
     * Cette méthode permet d'obtenir les statistiques sur le nombre de comptes
     *
     * @return La chaine JSON contenant les informations souhaitées sur le nombre de comptes
     */
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
    /**
     * <b>Auteurs:</b> Florian Leriche, Quentin Lerebours
     * Cette méthode permet d'obtenir les statistiques sur le nombre de clients
     *
     * @return La chaine JSON contenant les informations souhaitées sur le nombre de clients
     */
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
    /**
     * <b>Auteurs:</b> Florian Leriche, Quentin Lerebours
     * Cette méthode permet d'obtenir les statistiques sur le nombre d'opération
     *
     * @return La chaine JSON contenant les informations souhaitées sur le nombre d'opérations
     */
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
