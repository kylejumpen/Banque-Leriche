package Entity;

/**
 *
 * @author kyle
 */
public class CompteCourant {
    
    
     private Short compteCourantId;
     private ClientBanque clientBanque;
     private Integer montant;
     private Boolean bloque;
     private int iban;

    public CompteCourant() {
    }


    public CompteCourant(ClientBanque clientBanque, int iban) {
        this.clientBanque = clientBanque;
        this.iban = iban;
        this.montant = 0;
        this.bloque = false;
    }
    public CompteCourant(ClientBanque clientBanque, Integer montant, Boolean bloque, int iban) {
       this.clientBanque = clientBanque;
       this.montant = montant;
       this.bloque = bloque;
       this.iban = iban;
    }
    
    public Short getCompteCourantId() {
        return this.compteCourantId;
    }

    public void setCompteCourantId(Short compteCourantId) {
        this.compteCourantId = compteCourantId;
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
    
    public int getIban() {
        return this.iban;
    }

    public void setIban(int iban) {
        this.iban = iban;
    }
    
}
