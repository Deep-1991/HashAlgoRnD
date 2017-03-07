package org.zlounge.beat.encryption.algos;

public class AlgoFetcher {

	public AlgorithmInterface getAlgorithm(String algo){
		switch (algo) {
		case "AES":
			return new AESEncryption();
		case "RSA":
			return new RSAEncryption();
		case "JWT":
			return new JWTEncryption();
		}
		return null;
	}
}
