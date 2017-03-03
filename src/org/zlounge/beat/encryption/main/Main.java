package org.zlounge.beat.encryption.main;

import java.io.IOException;

import org.zlounge.beat.encryption.algos.AlgoFetcher;
import org.zlounge.beat.encryption.algos.Algorithm;
import org.zlounge.beat.encryption.common.Utils;

public class Main {
	
	public static void main(String[] args){
		
		String string = "{\"customer_number\":\"123456789012345678\", \"time\":1488386073}";
		System.out.println("original length = "+string.length());
		System.out.println("-------");
		
		AlgoFetcher fetcher = new AlgoFetcher();
		Algorithm algorithm = fetcher.getAlgorithm("RSA");
        String encryptedString = algorithm.encrypt(string);
        
        System.out.println("Encrypted string = "+encryptedString);
        System.out.println("Decrypted string = "+algorithm.decrypt(encryptedString));

        System.out.println("Encrypted and encoded string length = "+encryptedString.length());
//		System.out.println("decrypted length = "+decrypt(key, initVector, encryptedString).length());
		
		/*String compressedString = "";
		try {
			compressedString = Utils.compressString(string);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("-----------");
		System.out.println("Compressed string length = "+compressedString.length());
		
		String encryptedCompressedString = algorithm.encrypt(compressedString);
		System.out.println("Encrypted, encoded and compressed string length = "+encryptedCompressedString.length());*/
		
//		FileWriter.writeToFile("algoDemo.txt", encryptedCompressedString);
	}

}
