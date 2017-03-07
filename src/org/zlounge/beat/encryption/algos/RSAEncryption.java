/**
 * 
 */
package org.zlounge.beat.encryption.algos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.zlounge.beat.encryption.common.Constants;

/**
 * @author drastogi
 *
 */
public class RSAEncryption implements AlgorithmInterface {
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.zlounge.beat.encryption.algos.Algorithm#encrypt(java.lang.String)
	 */
	@Override
	public String encrypt(Object object, String string) {
		try {
			File publicKey = new File(Constants.PUBLIC_KEY);
			if(!publicKey.exists()){
				RSAKeyCreator.generateKeys();
				System.out.println("Keys generated!!");
			}
			Cipher cipher = Cipher.getInstance("RSA");

			PublicKey key = getPublicKey();
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] encrypted = cipher.doFinal(string.getBytes());
			return Base64.getEncoder().encodeToString(encrypted);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private PublicKey getPublicKey() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(Constants.PUBLIC_KEY));
		PublicKey publicKey = (PublicKey) inputStream.readObject();
		inputStream.close();
		return publicKey;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.zlounge.beat.encryption.algos.Algorithm#decrypt(java.lang.String)
	 */
	@Override
	public String decrypt(Object object, String string) {
		try {
			File privateKey = new File(Constants.PRIVATE_KEY);
			if(!privateKey.exists()){
				System.out.println("Private Key not present!!");
				return null;
			}
			Cipher cipher = Cipher.getInstance("RSA");

			PrivateKey key = getPrivateKey();
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] decryptedText = cipher.doFinal(Base64.getDecoder().decode(string));
			return new String(decryptedText);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private PrivateKey getPrivateKey() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(Constants.PRIVATE_KEY));
		PrivateKey privateKey = (PrivateKey) inputStream.readObject();
		inputStream.close();
		return privateKey;
	}

}
