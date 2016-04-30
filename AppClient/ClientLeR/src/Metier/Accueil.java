package Metier;
//import java.io.*;
//import java.net.*;
//import Entity.Compte;

import javax.ws.rs.client.Entity;

/**
 *
 * @author kyle
 */
public class Accueil extends CoRest {
        
    public String accueilCoGet(String user, String pw) {
       try {
        String url = baseUrl + Integer.parseInt(user); // remplacez l'Url
        //URL nurl = new URL("http://localhost:8081/adressedeconnexion/?username="+user+"&password="+pw);
        this.target = this.client.target(url);
        this.response =this.target.request().get();
        String response = this.response.readEntity(String.class);
        
        if(!response.equals("KO")){
            String[] parts = response.split("-");
            if(parts[2].equals(pw)){
                return response;
            }
            else{
                return "mdp";
            }
        }else{
            return "KO";
        } 
        }catch(Exception e) {
        e.printStackTrace() ;
        return "erreur";
        }
    }
    
    public boolean createAccountPost(int idClient){
         this.jsonArgs.put("idClient", String.valueOf(idClient));
         String maChaine = this.gson.toJson(jsonArgs);

            this.target = this.client.target(baseUrl + "/client/compte-courant/creer");
            this.response = this.target.request().post(Entity.entity(maChaine, "application/xml;charset=UTF-8"));
            maChaine = String.valueOf(response.getStatus());
            response.close();
            if(maChaine.equals("200"))
                return true;
            else 
                return false;
    }
    /*
    public String createAccountPost(Compte account) {
        try {
         URL url = new URL("http://www.google.fr") ; // remplacez l'Url
         //URL nurl = new URL("http://localhost:8081/adressedeconnexion/
         HttpURLConnection conn = (HttpURLConnection) url.openConnection();
         conn.setDoOutput(true);
         conn.setRequestMethod("POST");
         conn.setRequestProperty("Content-Type", "application/json");
         String input = "{\"nom\":"+account.getNom()+",\"prenom\":"+account.getPrenom()
                +",\"Addresse\":"+account.getAddresse()+",\"Addresse\":"+account.getType()+"}";
        OutputStream os = conn.getOutputStream();
        os.write(input.getBytes());
        os.flush();
        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
        String output = "",temp ="";

        while ((temp = br.readLine()) != null)
        output += temp;
        conn.disconnect();
        return temp;
        } catch (Exception e) {
        e.printStackTrace();
        return "erreur";
        }
        
    }*/
    
}
