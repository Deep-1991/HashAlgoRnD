package org.zlounge.beat.encryption.algos;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AESEncryption implements Algorithm {
	
	private static String KEY = "12345678abcdefgh";
    private static String INIT_VECTOR = "abcdefgh12345678";

	@Override
	public String encrypt(String string) {
		return encrypt(KEY, INIT_VECTOR, string);
	}

	@Override
	public String decrypt(String string) {
		return decrypt(KEY, INIT_VECTOR, string);
	}
	
	public String encrypt(String key, String initVector, String value) {
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), "AES");

            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);

            byte[] encrypted = cipher.doFinal(value.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

	public String decrypt(String key, String initVector, String encrypted) {
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), "AES");

            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);

            byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));
            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
