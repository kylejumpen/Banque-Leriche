package Entity;

/**
 *
 * @author kyle
 */
public class Personnel {
  

     private Short personnelId;
     private Banque banque;
     private String nom;
     private String motdepasse;
     private String role;

    public Personnel() {
    }

	
    public Personnel(Banque banque, String nom) {
        this.banque = banque;
        this.nom = nom;
    }
    public Personnel(Banque banque, String nom, String motdepasse, String role) {
       this.banque = banque;
       this.nom = nom;
       this.motdepasse = motdepasse;
       this.role = role;
    }

     public Short getPersonnelId() {
        return this.personnelId;
    }
    
    public void setPersonnelId(Short personnelId) {
        this.personnelId = personnelId;
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
    
     public String getMotdepasse() {
        return this.motdepasse;
    }
    
    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }   
    
        public String getRole() {
        return this.role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
}
