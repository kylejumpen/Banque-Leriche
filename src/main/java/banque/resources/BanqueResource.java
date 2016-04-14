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

import banque.entity.*;
import jdk.nashorn.internal.objects.annotations.Getter;
import banque.utils.*;

@Path("banque")
public class BanqueResource {
    private static HashMap<Short, Banque> banques = new HashMap<Short, Banque>();
    private Session session;

    @GET
    @Path("/{id}")
    @Produces("text/plain")
    public String getBanque(@PathParam("id") Short id) {
        return banques.get(id).toString();
    }

    @POST
    @Path("/create")
    @Consumes("application/xml")
    public Response creerBanque(Banque banque) {
        banques.put(banque.getBanqueId(), banque);
        return Response.status(200).entity(banque.toString()).build();
    }
}
