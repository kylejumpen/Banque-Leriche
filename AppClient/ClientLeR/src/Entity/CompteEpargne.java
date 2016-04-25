package Entity;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author kyle
 */
public class CompteEpargne {
   
     private Short compteEpargneId;
     private ClientBanque clientBanque;
     private Integer montant;
     private Boolean bloque;
     private Short tauxInteret;
     private short iban;
     private Set credits = new HashSet(0);

    public CompteEpargne() {
    }

	
    public CompteEpargne(ClientBanque clientBanque, short iban) {
        this.clientBanque = clientBanque;
        this.iban = iban;
    }
    public CompteEpargne(ClientBanque clientBanque, Integer montant, Boolean bloque, Short tauxInteret, short iban, Set credits) {
       this.clientBanque = clientBanque;
       this.montant = montant;
       this.bloque = bloque;
       this.tauxInteret = tauxInteret;
       this.iban = iban;
       this.credits = credits;
    }
    
    public Short getCompteEpargneId() {
        return this.compteEpargneId;
    }
    
    public void setCompteEpargneId(Short compteEpargneId) {
        this.compteEpargneId = compteEpargneId;
    }
    
    public ClientBanque getClientBanque() {
        return this.clientBanque;
    }
    
    public void setClientBanque(ClientBanque clientBanque) {
        this.clientBanque = clientBanque;
    }
    
     public Integer getMontant() {
        return this.montant;
    }
    
    public void setMontant(Integer montant) {
        this.montant = montant;
    }
    
    public Boolean getBloque() {
        return this.bloque;
    }
    
    public void setBloque(Boolean bloque) {
        this.bloque = bloque;
    }
    
    public Short getTauxInteret() {
        return this.tauxInteret;
    }
    
    public void setTauxInteret(Short tauxInteret) {
        this.tauxInteret = tauxInteret;
    }
    
    public short getIban() {
        return this.iban;
    }
    
    public void setIban(short iban) {
        this.iban = iban;
    }
    
    public Set getCredits() {
        return this.credits;
    }
    
    public void setCredits(Set credits) {
        this.credits = credits;
    }
    
}
