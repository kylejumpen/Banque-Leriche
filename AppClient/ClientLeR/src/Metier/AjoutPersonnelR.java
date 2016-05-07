package Metier;

import javax.ws.rs.client.Entity;
import Security.*;

/**
 *
 * @author pauline
 */
public class AjoutPersonnelR extends CoRest {

    public AjoutPersonnelR(){
        super();
    }
    
    //secure
    public void ajouterPersonnel(String banque, String nom, String motdepasse, String role) {
        String roleEnum;
        if (role.equals("Employé")) {
            roleEnum = "Employe";
        } else {
            roleEnum = "Gerant";
        }

        jsonArgs.put("nom", nom);
        jsonArgs.put("mdp", motdepasse);
        jsonArgs.put("role", role);
        jsonArgs.put("idBanque", banque);

        String maChaine; //Transformation en chaine de caractère
        maChaine = gson.toJson(jsonArgs);
        this.target = this.client.target(baseUrl + "/personnel/creer");
        try{
        this.response = this.target.request().post(Entity.entity(encryptData(maChaine), "application/xml;charset=UTF-8"));
        response.close();
        }catch(Exception e){System.out.println(e);}

    }

}
