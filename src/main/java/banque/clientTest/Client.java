package banque.clientTest;

import com.google.gson.reflect.TypeToken;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.client.Entity;
import org.hibernate.*;
import banque.entity.*;
import banque.utils.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.Short;
import java.lang.System;
import java.util.HashMap;
import java.util.Iterator;

public class Client {

    public static void main(String args[]) {
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target;
        Response response;
        Banque banque;
        ClientBanque clientBanque;
        Session session = HibernateUtil.getSessionFactory().openSession();
        String baseUrl = "http://localhost:8001/Banque-1.0/banque";
        Gson gson = new Gson();
        HashMap<String, String> jsonArgs = new HashMap<String, String>();
        String maChaine;

        //Param: CREER-CLIENT nom prenom mdp email code-postal idBanque
        if (args.length == 7 && args[0].equals("CREER-CLIENT")) {
            jsonArgs.put("nom", args[1]);
            jsonArgs.put("prenom", args[2]);
            jsonArgs.put("mdp", args[3]);
            jsonArgs.put("email", args[4]);
            jsonArgs.put("codePostal", args[5]);
            jsonArgs.put("idBanque", args[6]);

            maChaine = gson.toJson(jsonArgs);
  //            BanqueUtil.writeInFile("test_json.txt", maChaineCreerClient);
            target = client.target(baseUrl + "/client/creer");
            response = target.request().post(Entity.entity(maChaine, "application/xml;charset=UTF-8"));
            System.out.println("POST : " + response.getStatus());
            response.close();
            session.close();
        }
        //Param: SUPPRIMER-CLIENT idClient
        else if(args.length == 2 && args[0].equals("SUPPRIMER-CLIENT")) {
            target = client.target(baseUrl + "/client/supprimer/" + args[1]);
            response = target.request().delete();
            System.out.println("DELETE : " + response.getStatus());
            response.close();
        }
        //Param: GET-CLIENT idClient
        else if (args.length == 2 && args[0].equals("GET-CLIENT")) {
            target = client.target(baseUrl + "/client/" + args[1]);
            response = target.request().get();
            System.out.println(args[1] + " : " + response.readEntity(String.class));
            response.close();
        }


        //Param: CREER-BANQUE nom ville
        else if (args.length == 3 && args[0].equals("CREER-BANQUE")) {
            jsonArgs.put("nom", args[1]);
            jsonArgs.put("ville", args[2]);
            maChaine = gson.toJson(jsonArgs);

            target = client.target(baseUrl + "/creer");
            response = target.request().post(Entity.entity(maChaine, "application/xml;charset=UTF-8"));
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
        //Param: GET-COMPTE idCompte
        else if (args.length == 2 && args[0].equals("GET-COMPTE")) {
            target = client.target(baseUrl + "/client/compte-courant/" + args[1]);
            response = target.request().get();
            System.out.println(args[1] + " : " + response.readEntity(String.class));
            response.close();
        }
        //Param: CREER-COMPTE idClient
        else if (args.length == 2 && args[0].equals("CREER-COMPTE")) {
            jsonArgs.put("idClient", args[1]);
            maChaine = gson.toJson(jsonArgs);

            target = client.target(baseUrl + "/client/compte-courant/creer");
            response = target.request().post(Entity.entity(maChaine, "application/xml;charset=UTF-8"));
            System.out.println("POST : " + response.getStatus());
            response.close();
            session.close();
        } //Param: SUPPRIMER-COMPTE idCompte
        else if(args.length == 2 && args[0].equals("SUPPRIMER-COMPTE")) {
            target = client.target(baseUrl + "/client/compte-courant/supprimer/" + args[1]);
            response = target.request().delete();
            System.out.println("DELETE : " + response.getStatus());
            response.close();
        }
        //Param: CREER-COMPTE-COURANT idClient
        else if (args.length == 2 && args[0].equals("CREER-COMPTE-EPARGNE")) {
            jsonArgs.put("idClient", args[1]);
            maChaine = gson.toJson(jsonArgs);

            target = client.target(baseUrl + "/client/compte-epargne/creer");
            response = target.request().post(Entity.entity(maChaine, "application/xml;charset=UTF-8"));
            System.out.println("POST : " + response.getStatus());
            response.close();
            session.close();
        }
        //Param: LISTE-CC idBanque
        else if (args.length == 2 && args[0].equals("LISTE-CC")) {
            target = client.target(baseUrl + "/liste/comptes-courant/" + args[1]);
            response = target.request().get();

            String rep = response.readEntity(String.class);
            HashMap<String, String> hash1 = gson.fromJson(rep, new TypeToken<HashMap<String, String>>() {
            }.getType());
            HashMap<String, String> hash2;
            HashMap<String, String> hash3;

            //On itère sur les différents comptes pour obtenir le json de chaque compte
            Iterator it = hash1.entrySet().iterator();
            while (it.hasNext()) {
                HashMap.Entry pair = (HashMap.Entry)it.next();
                String jsonDeMonCompte = (String) pair.getValue();
                hash2 = gson.fromJson(jsonDeMonCompte, new TypeToken<HashMap<String, String>>() {
                }.getType());
                System.out.println("Id du compte : " + hash2.get("compteCourantId"));
                System.out.println("Montant : " + hash2.get("montant"));
                System.out.println("Iban : " + hash2.get("iban"));
                System.out.println("Bloqué : " + hash2.get("bloque"));

                String jsonDeMonClient = hash2.get("clientBanque");
                hash3 = gson.fromJson(jsonDeMonClient, new TypeToken<HashMap<String, String>>() {
                }.getType());
                System.out.println("Client banque id : " + hash3.get("clientBanqueId"));
//              Ainsi de suite avec les attributs qui t'interessent
//              On peut encore descendre d'un niveau puisqu'un client possède un attribut Banque
                System.out.println(" ");

                it.remove(); // avoids a ConcurrentModificationException
            }


//            System.out.println(args[0] + " : " + rep);
            response.close();
        }
        //Param: LISTE-CE
        else if (args.length == 1 && args[0].equals("LISTE-CE")) {
            target = client.target(baseUrl + "/liste/comptes-epargne");
            response = target.request().get();
            System.out.println(args[0] + " : " + response.readEntity(String.class));
            response.close();
        }

        //Param: OPERER type idCompteADebiter typeCompteADebiter idCompteACrediter typeCompteACrediter montant    type = {debit, credit} typeCompteADebiter = {courant, epargne}
        else if(args.length == 7 && args[0].equals("OPERER")) {
            jsonArgs.put("type", args[1]);
            jsonArgs.put("idCompteADebiter", args[2]);
            jsonArgs.put("typeCompteADebiter", args[3]);
            jsonArgs.put("idCompteACrediter", args[4]);
            jsonArgs.put("typeCompteACrediter", args[5]);
            jsonArgs.put("montant", args[6]);
            maChaine = gson.toJson(jsonArgs);

            target = client.target(baseUrl + "/client/compte/operer");
            response = target.request().post(Entity.entity(maChaine, "application/xml;charset=UTF-8"));
            System.out.println("POST : " + response.getStatus());
            response.close();
            session.close();
        }
        //Param: CREER-PERSONNEL nom mdp role idBanque   role = {gerant, employe, admin}
        else if (args.length == 5 && args[0].equals("CREER-PERSONNEL")) {
            jsonArgs.put("nom", args[1]);
            jsonArgs.put("mdp", args[2]);
            jsonArgs.put("role", args[3]);
            jsonArgs.put("idBanque", args[4]);
            maChaine = gson.toJson(jsonArgs);

            target = client.target(baseUrl + "/personnel/creer");
            response = target.request().post(Entity.entity(maChaine, "application/xml;charset=UTF-8"));
            System.out.println("POST : " + response.getStatus());
            response.close();
            session.close();
        }
        //Param: SUPPRIMER-PERSONNEL idPersonnel
        else if(args.length == 2 && args[0].equals("SUPPRIMER-PERSONNEL")) {
            target = client.target(baseUrl + "/personnel/supprimer/" + args[1]);
            response = target.request().delete();
            System.out.println("DELETE : " + response.getStatus());
            response.close();
        }
        //Param: GET-PERSONNEL idPersonnel
        else if (args.length == 2 && args[0].equals("GET-PERSONNEL")) {
            target = client.target(baseUrl + "/personnel/" + args[1]);
            response = target.request().get();
            System.out.println(args[1] + " : " + response.readEntity(String.class));
            response.close();
        }
        //Param: EPARGNER
        else if(args.length == 1 && args[0].equals("EPARGNER")) {
            target = client.target(baseUrl + "/epargner");
            maChaine = "coucou";
            response = target.request().put(Entity.entity(maChaine, "application/xml;charset=UTF-8"));
            System.out.println("PUT :" + response.getStatus());
            response.close();
        } else if(args.length == 2 && args[0].equals("GET-COMPTE-CLIENT")) {
            target = client.target(baseUrl + "/compte/courant/" + args[1]);
            response = target.request().get();
            System.out.println(args[1] + " : " + response.readEntity(String.class));
            response.close();
        }
        //Param: BLOQUER idCompte type
        else if(args.length == 3 && args[0].equals("BLOQUER")) {
            target = client.target(baseUrl + "/client/compte/bloquer");

            jsonArgs.put("idCompte", args[1]);
            jsonArgs.put("type", args[2]);
            maChaine = gson.toJson(jsonArgs);

            response = target.request().put(Entity.entity(maChaine, "application/xml;charset=UTF-8"));
            System.out.println("PUT :" + response.getStatus());
            response.close();
        }
        //Param: GET-STATS-COMPTES
        else if (args.length == 1 && args[0].equals("GET-STATS-COMPTES")) {
            target = client.target(baseUrl + "/stats/comptes");
            response = target.request().get();
            System.out.println(args[0] + " : " + response.readEntity(String.class));
            response.close();
        }
        //Param: GET-STATS-CLIENTS
        else if (args.length == 1 && args[0].equals("GET-STATS-CLIENTS")) {
            target = client.target(baseUrl + "/stats/clients");
            response = target.request().get();
            System.out.println(args[0] + " : " + response.readEntity(String.class));
            response.close();
        }
        //Param: GET-STATS-OPERATIONS
        else if (args.length == 1 && args[0].equals("GET-STATS-OPERATIONS")) {
            target = client.target(baseUrl + "/stats/operations");
            response = target.request().get();
            System.out.println(args[0] + " : " + response.readEntity(String.class));
            response.close();
        }

        else {
            System.out.println("Le premier paramètre est mauvais ou le nb de paramètre n'est pas bon");
        }
    }
}
