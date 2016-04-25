package Entity;

import java.util.HashSet;
import java.util.Set;
/**
 *
 * @author kyle
 */
public class ClientBanque {
    
     private Short clientBanqueId;
     private Banque banque;
     private String nom;
     private String prenom;
     private String motdepasse;
     private String email;
     private String codePostal;
     private Set compteEpargnes = new HashSet(0);
     private Set compteCourants = new HashSet(0);

      public ClientBanque() {
    }

    public ClientBanque(String nom, String prenom, String motdepasse, String email, String codePostal) {
        this.nom = nom;
        this.prenom = prenom;
        this.motdepasse = motdepasse;
        this.email = email;
        this.codePostal = codePostal;
    }

    public ClientBanque(Banque banque, String nom, String prenom, String motdepasse, String email, String codePostal) {
        this.banque = banque;
        this.nom = nom;
        this.prenom = prenom;
        this.motdepasse = motdepasse;
        this.email = email;
        this.codePostal = codePostal;
    }
    
    
    public ClientBanque(Banque banque, String nom, String prenom, String motdepasse, String email, String codePostal, Set compteEpargnes, Set compteCourants) {
       this.banque = banque;
       this.nom = nom;
       this.prenom = prenom;
       this.motdepasse = motdepasse;
       this.email = email;
       this.codePostal = codePostal;
       this.compteEpargnes = compteEpargnes;
       this.compteCourants = compteCourants;
    }
    
     public Short getClientBanqueId() {
        return this.clientBanqueId;
    }
    
    public void setClientBanqueId(Short clientBanqueId) {
        this.clientBanqueId = clientBanqueId;
    }
    
    public Banque getBanque() {
        return this.banque;
    }
    
    public void setBanque(Banque banque) {
        this.banque = banque;
    }
    
    public String getNom() {
        return this.nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public String getPrenom() {
        return this.prenom;
    }
    
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    public String getMotdepasse() {
        return this.motdepasse;
    }
    
    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getCodePostal() {
        return this.codePostal;
    }
    
    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }
    
    public Set getCompteEpargnes() {
        return this.compteEpargnes;
    }
    
    public void setCompteEpargnes(Set compteEpargnes) {
        this.compteEpargnes = compteEpargnes;
    }
    
    public Set getCompteCourants() {
        return this.compteCourants;
    }
    
    public void setCompteCourants(Set compteCourants) {
        this.compteCourants = compteCourants;
    }
  
}
