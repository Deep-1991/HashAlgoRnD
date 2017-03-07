package org.zlounge.beat.encryption.algos;

import java.security.Key;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AESEncryption implements AlgorithmInterface {
	
	@Override
	public String encrypt(Object key, String value) {
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(key.toString().getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);

            byte[] encrypted = cipher.doFinal(value.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
	@Override
	public String decrypt(Object key, String encrypted) {
        try {
            Key skeySpec = new SecretKeySpec(key.toString().getBytes("UTF-8"), "AES");

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
