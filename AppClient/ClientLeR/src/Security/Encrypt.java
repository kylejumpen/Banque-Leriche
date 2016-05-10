package Security;
import java.security.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class Encrypt {

  protected static final byte[] keyValue = new byte[] {'e','a','r','a','a','o','u','&','e','a','r','a','a','o','u','z'};

    /**
     *
     * @param Data Les données sous forme de JSON à chiffrer
     * @return Les données chiffrées
     * @throws Exception
     */
  protected static String encryptData(String Data) throws Exception{
	        Key key;
             key = generateKey();
	     Cipher c = Cipher.getInstance("AES");
	     c.init(Cipher.ENCRYPT_MODE, key);
	     byte[] encVal = c.doFinal(Data.getBytes("UTF-8"));
	     return new String(new Base64().encode(encVal),"UTF-8");
	    }

    /**
     *
     * @param encryptedData les données chiffrées à déchiffrées
     * @return Les données déchiffrées
     * @throws Exception
     */
    protected static String decryptData(String encryptedData) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance("AES");
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedValue = new Base64().decode(encryptedData.getBytes("UTF-8"));
        byte[] decValue = c.doFinal(decodedValue);
        String decryptedValue = new String(decValue,"UTF-8");
        return decryptedValue;
    }

    /**
     *
     * @param id L'id à chiffrer
     * @return L'id déchiffré sous forme de chaine de caractère
     */
    public static String encryptId(int id) {
        //foutre des pourcentages
        String partun="";
        if(id % 2 == 0)
         return "" + (id * 5 +128)+"%1";
        else
         return "" +(id*(-18) +189)+"%0";
    }


    /**
     *
     * @return Génère une clé pour l'algorithme AES
     * @throws Exception
     */
    private static Key generateKey() throws Exception {
        Key key = new SecretKeySpec(keyValue, "AES");
        return key;
    }
}

