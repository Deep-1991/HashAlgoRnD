/**
 * 
 */
package org.zlounge.beat.encryption.algos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.zlounge.beat.encryption.common.Constants;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

/**
 * @author drastogi
 *
 */
public class JWTEncryption implements AlgorithmInterface {

	public static final String API_KEY = "12345678abcdefgh";

	public RSAPrivateKey getRSAPrivateKey() {
		try {
			RSAPrivateKey key = getPrivateKey();
			return key;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public RSAPublicKey getRSAPublicKey() {
		try {
			RSAPublicKey key = getPublicKey();
			return key;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String encrypt(Object object, String string) {
		RSAPrivateKey key = (RSAPrivateKey) object;
		String token = JWT.create().withClaim("Payload", string).sign(Algorithm.RSA256(key));
		return token;
	}

	@Override
	public String decrypt(Object object, String string) {
		RSAPublicKey publicKey = (RSAPublicKey) object;
		JWT.require(Algorithm.RSA256(publicKey)).build().verify(string);
		return null;
	}

	private RSAPrivateKey getPrivateKey() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(Constants.PRIVATE_KEY));
		RSAPrivateKey privateKey = (RSAPrivateKey) inputStream.readObject();
		inputStream.close();
		return privateKey;
	}

	private RSAPublicKey getPublicKey() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(Constants.PUBLIC_KEY));
		RSAPublicKey publicKey = (RSAPublicKey) inputStream.readObject();
		inputStream.close();
		return publicKey;
	}

}
