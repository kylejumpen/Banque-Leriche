package banque.entity;
// Generated 23 avr. 2016 00:15:38 by Hibernate Tools 4.3.1


import com.google.gson.Gson;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;

/**
 * Operation generated by hbm2java
 */

@XmlRootElement(name="operation")
public class Operation  implements java.io.Serializable {


     private Short operationId;
     private String type;
     private short compteDebiteurId;
     private short compteCrediteurId;
     private float montant;

    public Operation() {
    }


    public Operation(String type, short compteDebiteurId, short compteCrediteurId) {
        this.type = type;
        this.compteDebiteurId = compteDebiteurId;
        this.compteCrediteurId = compteCrediteurId;
    }
    public Operation(String type, short compteDebiteurId, short compteCrediteurId, float montant) {
       this.type = type;
       this.compteDebiteurId = compteDebiteurId;
       this.compteCrediteurId = compteCrediteurId;
       this.montant = montant;
    }

    @XmlElement
    public Short getOperationId() {
        return this.operationId;
    }

    public void setOperationId(Short operationId) {
        this.operationId = operationId;
    }

    @XmlElement
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlElement
    public short getCompteDebiteurId() {
        return this.compteDebiteurId;
    }

    public void setCompteDebiteurId(short compteDebiteurId) {
        this.compteDebiteurId = compteDebiteurId;
    }

    @XmlElement
    public short getCompteCrediteurId() {
        return this.compteCrediteurId;
    }

    public void setCompteCrediteurId(short compteCrediteurId) {
        this.compteCrediteurId = compteCrediteurId;
    }

    @XmlElement
    public float getMontant() {
        return this.montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public String toString() {
        Gson gson = new Gson();

        HashMap<String, String> jsonArgs = new HashMap<String, String>();
        jsonArgs.put("operationId", Short.toString(getOperationId()));
        jsonArgs.put("type", getType());
        jsonArgs.put("compteDebiteurId", Short.toString(getCompteDebiteurId()));
        jsonArgs.put("compteCrediteurId", Short.toString(getCompteCrediteurId()));
        jsonArgs.put("montant", Float.toString(getMontant()));
        return gson.toJson(jsonArgs);
    }
}


