package Metier;

import javax.ws.rs.client.Entity;

/**
 *
 * @author pauline
 */
public class AjoutPersonnelR extends CoRest {

    public AjoutPersonnelR(){
        super();
    }
    
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
        this.response = this.target.request().post(Entity.entity(maChaine, "application/xml;charset=UTF-8"));
        response.close();

    }

}
