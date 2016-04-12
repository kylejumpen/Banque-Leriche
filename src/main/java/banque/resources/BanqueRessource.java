package banque.resources;

import java.util.HashMap;
import java.util.Iterator;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import banque.entity.ClientBanque;



public class BanqueRessource
{
    //private static HashMap<String,String> listeDePersonnes = new HashMap<String,String>();
    //private static HashMap<String,String> listeDeBureaux = new HashMap<String,String>();

    
    @POST
    @Path("/client/creer")
    @Consumes("application/xml")
    public Response creerClient() {
	ClientBanque client = new ClientBanque();
	return Response.status(200).entity(client.toString()).build();
    }
    
//    @DELETE
//    @Path("/{CLIENT}/supprimer")
//    public Response supprimerClient(@PathParam("CLIENT") final int idClient) {
//	
//    }
//    
//    @POST
//    @Path("/compte/creer")
//    @Consumes("application/xml")
//    public Response creerCompte(ClientBanque client) {
//	
//    }
//    
//    @PUT
//    @Path("/operation/effectuer")
//    @Consumes("application/xml")
//    public Response effectuerOperation(CompteCourant compte, String typeDOperation, float montant) {
//	
//    }
//    
//    @PUT
//    @Path("/operation/epargner")
//    @Consumes("application/xml")
//    public Response epargner(ClientBanque client) {
//	
//    }
//    
//    @PUT
//    @Path("/compte/crediter")
//    @Consumes("application/xml")
//    public Response crediterCompte(CompteCourant compte, float montant) {
//	
//    }
//    
//    @PUT
//    @Path("/compte/debiter")
//    @Consumes("application/xml")
//    public Response crediterCompte(CompteCourant compte, float montant) {
//	
//    }
}
