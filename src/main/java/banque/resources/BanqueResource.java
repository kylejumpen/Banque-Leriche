package banque.resources;

import java.lang.Override;
import java.lang.Short;
import java.lang.String;
import java.util.HashMap;
import java.util.Iterator;
import javax.security.auth.login.AppConfigurationEntry;
import javax.security.auth.login.Configuration;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import org.hibernate.*;

import banque.entity.*;
import jdk.nashorn.internal.objects.annotations.Getter;
import banque.utils.*;

@Path("banque")
public class BanqueResource {
    private static HashMap<Short, Banque> banques = new HashMap<Short, Banque>();
    Session session;

    @GET
    @Path("/{id}")
    @Produces("text/plain")
    public String getBanque(@PathParam("id") Short id) {
        session = HibernateUtil.getSessionFactory().openSession();

        try {
            Banque banque = (Banque) session.load(Banque.class, id);
            return banque.toString();
        }catch(Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
        return "Aucune banque d'id " + id +" trouve";
    }

    @POST
    @Path("/create")
    @Consumes("application/xml")
    public Response creerBanque(Banque banque) {
  //    banques.put(banque.getBanqueId(), banque);
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(banque);
        session.getTransaction().commit();
        session.close();
        return Response.status(200).entity(banque.toString()).build();
    }
}
