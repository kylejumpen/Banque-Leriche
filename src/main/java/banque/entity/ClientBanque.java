package banque.entity;
// Generated 27 mars 2016 14:02:36 by Hibernate Tools 4.3.1


import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import banque.utils.HibernateUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.*;
import org.hibernate.Session;

/**
 * ClientBanque generated by hbm2java
 */
@XmlRootElement(name="clientBanque")
public class ClientBanque  implements java.io.Serializable {

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

    @XmlElement
    public Short getClientBanqueId() {
        return this.clientBanqueId;
    }

    public void setClientBanqueId(Short clientBanqueId) {
        this.clientBanqueId = clientBanqueId;
    }

    @XmlElement
    public Banque getBanque() {
        return this.banque;
    }

    public void setBanque(Banque banque) {
        this.banque = banque;
    }

    @XmlElement
    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @XmlElement
    public String getPrenom() {
        return this.prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @XmlElement
    public String getMotdepasse() {
        return this.motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }

    @XmlElement
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlElement
    public String getCodePostal() {
        return this.codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    @XmlElement
    public Set getCompteEpargnes() {
        return this.compteEpargnes;
    }

    public void setCompteEpargnes(Set compteEpargnes) {
        this.compteEpargnes = compteEpargnes;
    }

    @XmlElement
    public Set getCompteCourants() {
        return this.compteCourants;
    }

    public void setCompteCourants(Set compteCourants) {
        this.compteCourants = compteCourants;
    }

    public String toString() {
        Session session;
        session = HibernateUtil.getSessionFactory().openSession();
        Banque banque = (Banque) session.load(Banque.class, getBanque().getBanqueId());
        String maBanque = banque.toString();

        Gson gson = new Gson();

        HashMap<String, String> jsonArgs = new HashMap<String, String>();
        jsonArgs.put("clientBanqueId", getClientBanqueId().toString());
        jsonArgs.put("banque", maBanque);
        jsonArgs.put("nom", getNom());
        jsonArgs.put("prenom", getPrenom());
        jsonArgs.put("motdepasse", getMotdepasse());
        jsonArgs.put("email", getEmail());
        jsonArgs.put("codePostal", getCodePostal());
        return gson.toJson(jsonArgs);
    }
}


