package Entity;

/**
 *
 * @author kyle
 */
public class Operation {
 
     private Short operationId;
     private String type;
     private short compteDebiteurId;
     private short compteCrediteurId;
     private Integer montant;

    public Operation() {
    }


    public Operation(String type, short compteDebiteurId, short compteCrediteurId) {
        this.type = type;
        this.compteDebiteurId = compteDebiteurId;
        this.compteCrediteurId = compteCrediteurId;
    }
    public Operation(String type, short compteDebiteurId, short compteCrediteurId, Integer montant) {
       this.type = type;
       this.compteDebiteurId = compteDebiteurId;
       this.compteCrediteurId = compteCrediteurId;
       this.montant = montant;
    }  
    
        public Short getOperationId() {
        return this.operationId;
    }

    public void setOperationId(Short operationId) {
        this.operationId = operationId;
    }
    
        public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public short getCompteDebiteurId() {
        return this.compteDebiteurId;
    }

    public void setCompteDebiteurId(short compteDebiteurId) {
        this.compteDebiteurId = compteDebiteurId;
    }
    
        public short getCompteCrediteurId() {
        return this.compteCrediteurId;
    }

    public void setCompteCrediteurId(short compteCrediteurId) {
        this.compteCrediteurId = compteCrediteurId;
    }
    
    public Integer getMontant() {
        return this.montant;
    }

    public void setMontant(Integer montant) {
        this.montant = montant;
    } 
}
