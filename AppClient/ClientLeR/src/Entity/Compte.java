package Entity;

/**
 *
 * @author kyle
 */
public class Compte {
    
    private String nom;
    private String prenom;
    private String addresse;
    private String type;

    
    public Compte(String nom, String prenom, String addresse, String type) {
        this.nom = nom;
        this.prenom = prenom;
        this.addresse = addresse;
        this.type = type;
    }
    
    
    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * @param prenom the prenom to set
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * @return the addresse
     */
    public String getAddresse() {
        return addresse;
    }

    /**
     * @param addresse the addresse to set
     */
    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }
    
    
    
}
