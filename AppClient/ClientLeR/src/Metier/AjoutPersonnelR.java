package Metier;

import javax.ws.rs.client.Entity;

/**
 *
 * @author pauline
 */
public class AjoutPersonnelR extends CoRest {

    public void ajouterPersonnel(String banque, String nom, String motdepasse, String role) {
        String roleEnum;
        if(role.equals("Employé"))
            roleEnum = "Employe";
        else
            roleEnum = "Gerant";
        
        jsonArgs.put("banque", banque);
        jsonArgs.put("nom", nom);
        jsonArgs.put("motdepasse", motdepasse);
        jsonArgs.put("role", roleEnum);

        String maChaine; //Transformation en chaine de caractère
        maChaine = gson.toJson(jsonArgs);
        this.target = this.client.target(baseUrl +"/personnel/creer"); 
        this.response = this.target.request().post(Entity.entity(maChaine, "application/xml;charset=UTF-8"));
       
    }

}
