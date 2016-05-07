package banque.security;
import java.security.*;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.*;

public class Encrypt {
    
     private static final String ALGO = "AES";
     /** La clef doit faire 16 bytes */
    private static final byte[] keyValue = new byte[] {'e','a','r','a','a','o','u','&','e','a','r','a','a','o','u','z'};
    

	public static String encryptData(String Data) throws Exception {
	        Key key = generateKey();
	        Cipher c = Cipher.getInstance(ALGO);
	        c.init(Cipher.ENCRYPT_MODE, key);
	        byte[] encVal = c.doFinal(Data.getBytes());
	        String encryptedValue = new BASE64Encoder().encode(encVal);  
	        return encryptedValue;
	    }

    public static String decryptData(String encryptedData) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
        byte[] decValue = c.doFinal(decordedValue);
        String decryptedValue = new String(decValue);
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

