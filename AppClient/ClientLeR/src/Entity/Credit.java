package Entity;

import java.util.Date;

/**
 *
 * @author kyle
 */
public class Credit {
    
     private Short creditId;
     private CompteEpargne compteEpargne;
     private Integer montant;
     private Short taux;
     private Date dateEmprunt;
     private short duree;

    public Credit() {
    }

	
    public Credit(CompteEpargne compteEpargne, Date dateEmprunt, short duree) {
        this.compteEpargne = compteEpargne;
        this.dateEmprunt = dateEmprunt;
        this.duree = duree;
    }
    public Credit(CompteEpargne compteEpargne, Integer montant, Short taux, Date dateEmprunt, short duree) {
       this.compteEpargne = compteEpargne;
       this.montant = montant;
       this.taux = taux;
       this.dateEmprunt = dateEmprunt;
       this.duree = duree;
    }

    public Short getCreditId() {
        return this.creditId;
    }
    
    public void setCreditId(Short creditId) {
        this.creditId = creditId;
    }
    
    public CompteEpargne getCompteEpargne() {
        return this.compteEpargne;
    }
    
    public void setCompteEpargne(CompteEpargne compteEpargne) {
        this.compteEpargne = compteEpargne;
    }
    
    public Integer getMontant() {
        return this.montant;
    }
    
    public void setMontant(Integer montant) {
        this.montant = montant;
    }

     public Short getTaux() {
        return this.taux;
    }
    
    public void setTaux(Short taux) {
        this.taux = taux;
    }
    
    public Date getDateEmprunt() {
        return this.dateEmprunt;
    }

    public void setDateEmprunt(Date dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }
    
    public short getDuree() {
        return this.duree;
    }
    
    public void setDuree(short duree) {
        this.duree = duree;
    }
}
