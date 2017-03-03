/**
 * 
 */
package org.zlounge.beat.encryption.algos;

import java.security.Key;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author drastogi
 *
 */
public class JWTEncryption implements Algorithm {

	public static final String API_KEY = "abcdefghijklmnop";

	@Override
	public String encrypt(String string) {

		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		byte[] key = DatatypeConverter.parseBase64Binary(API_KEY);
		Key signingKey = new SecretKeySpec(key, signatureAlgorithm.getJcaName());

		JwtBuilder builder = Jwts.builder().setPayload(string).signWith(signatureAlgorithm, signingKey);
//		JwtBuilder builder = Jwts.builder().setId("123456789012345678").setIssuedAt(new Date()).signWith(signatureAlgorithm, signingKey);
		return builder.compact();
	}

	@Override
	public String decrypt(String string) {
		/*Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(API_KEY)).parseClaimsJws(string)
				.getBody();*/
		return null;
	}

}
