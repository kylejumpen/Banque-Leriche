package Entity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 *
 * @author kyle
 */
public class Banque {

     private Short banqueId;
     private String nom;
     private String ville;

    public Banque() {
    }


    public Banque(String nom, String ville) {
        this.nom = nom;
        this.ville = ville;
    }

    public Short getBanqueId() {
        return this.banqueId;
    }

    public void setBanqueId(Short banqueId) {
        this.banqueId = banqueId;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getVille() {
        return this.ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String toString() {
        return " Nom : " + nom + " Ville : " + ville + " Id : " + banqueId;
    }
}

