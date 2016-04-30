package Metier;
//import java.io.*;
//import java.net.*;
//import Entity.Compte;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import javax.ws.rs.core.Response;
import com.google.gson.Gson;
import java.util.HashMap;
import javax.ws.rs.client.Entity;


public abstract class CoRest {
    
    protected static ResteasyClient client = new ResteasyClientBuilder().build();
    protected ResteasyWebTarget target;
    protected Response response;
    protected HashMap<String, String> jsonArgs = new HashMap<String, String>();
    protected Gson gson = new Gson();
    final static String baseUrl = "http://localhost:8001/Banque-1.0/banque";
    
    
    
    
}
