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
    protected static String baseUrl;
    
    
    public CoRest(){
        this.client = new ResteasyClientBuilder().build();
        this.jsonArgs =  new HashMap<String, String>();
        this.gson = new Gson();
        this.baseUrl = "http://localhost:8001/Banque-1.0/banque";
    }
    
    
    /**
     * @return the baseUrl
     */
    public static String getBaseUrl() {
        return baseUrl;
    }

    /**
     * @param aBaseUrl the baseUrl to set
     */
    public static void setBaseUrl(String aBaseUrl) {
        baseUrl = aBaseUrl;
        System.out.println("Ici se trouve l'url de base qui devient : "+ baseUrl);
    }
    
}
