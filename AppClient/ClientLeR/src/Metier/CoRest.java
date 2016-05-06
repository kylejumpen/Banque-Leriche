package Metier;
import Security.*;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import javax.ws.rs.core.Response;
import com.google.gson.Gson;
import java.util.HashMap;
import javax.ws.rs.client.Entity;

public abstract class CoRest extends Encrypt {
    
    protected static ResteasyClient client;
    protected ResteasyWebTarget target;
    protected Response response;
    protected HashMap<String, String> jsonArgs ;
    protected Gson gson;
    protected final static String baseUrl = "http://localhost:8001/Banque-1.0/banque";
    
    
    public CoRest(){
        this.client = new ResteasyClientBuilder().build();
        this.jsonArgs =  new HashMap<String, String>();
        this.gson = new Gson();
    }
    
}
