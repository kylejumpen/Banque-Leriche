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
    /**
     * Permet d'ajouter un membre du personel à la banque
     * @param banque banque dans laquelle on veux ajouter un membre
     * @param nom nom du membre
     * @param motdepasse mot de passe du membre
     * @param role role du membre: Employe, Gerant, Admin
     */
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

        String maChaine; 
        maChaine = gson.toJson(jsonArgs);
        this.target = this.client.target(getBaseUrl() + "/personnel/creer");
        try{
        this.response = this.target.request().post(Entity.entity(encryptData(maChaine), "application/xml;charset=UTF-8"));
        response.close();
        }catch(Exception e){System.out.println(e);}

    }

}
