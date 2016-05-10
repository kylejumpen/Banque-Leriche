package Security;
import java.security.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.security.MessageDigest;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
public class Encrypt {

  protected static final byte[] keyValue = new byte[] {'e','a','r','a','a','o','u','&','e','a','r','a','a','o','u','z'};

    /**
     * <b>Kafui Atanley</b>
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
     * <b>Kafui Atanley</b>
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
     * <b>Kafui Atanley</b>
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
     * @param password Le mot de passe à hasher
     * @return Le mot de passe hasher
     */
    protected static String hashPassword(String password) throws Exception{
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(password.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< hash.length ;i++)
            sb.append(Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1));     
        return sb.toString(); 
    }
    
    protected static boolean comparePassword(String password1, String password2) throws Exception{
        
        return MessageDigest.isEqual(password1.getBytes(), password2.getBytes());
        
    }

    /**
     * <b>Kafui Atanley</b>
     *
     * @return Génère une clé pour l'algorithme AES
     * @throws Exception
     */
    private static Key generateKey() throws Exception {
        Key key = new SecretKeySpec(keyValue, "AES");
        return key;
    }
}

