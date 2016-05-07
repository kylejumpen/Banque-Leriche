package banque.security;
import java.security.*;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class Encrypt {
    
     private static final String ALGO = "AES";
     /** La clef doit faire 16 bytes */
    private static final byte[] keyValue = new byte[] {'e','a','r','a','a','o','u','&','e','a','r','a','a','o','u','z'};
    

    public static String encryptData(String Data) throws Exception{
	        Key key;
             key = generateKey();
	     Cipher c = Cipher.getInstance(ALGO);
	     c.init(Cipher.ENCRYPT_MODE, key);
	     byte[] encVal = c.doFinal(Data.getBytes("UTF-8"));  
	     return new String(new Base64().encode(encVal),"UTF-8");
	    }

    public static String decryptData(String encryptedData) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedValue = new Base64().decode(encryptedData.getBytes("UTF-8"));
        byte[] decValue = c.doFinal(decodedValue);
        String decryptedValue = new String(decValue,"UTF-8");
        return decryptedValue;
    }
    
     public static String decryptId(String idc) {
	String[] temp = idc.split("%");
        if(temp[1].equals("1"))
            return String.valueOf((Integer.parseInt(temp[0])-128)/5);
        else
            return String.valueOf((Integer.parseInt(temp[0])-189)/(-18));
    }

    private static Key generateKey() throws Exception {
        Key key = new SecretKeySpec(keyValue, ALGO);
        return key;
}

    
    
}

