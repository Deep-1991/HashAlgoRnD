package org.zlounge.beat.encryption.main;

import java.security.interfaces.RSAPrivateKey;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.json.JSONObject;
import org.zlounge.beat.encryption.algos.AlgoFetcher;
import org.zlounge.beat.encryption.algos.AlgorithmInterface;
import org.zlounge.beat.encryption.algos.JWTEncryption;

public class Main {
	
	private static String KEY = "12345678abcdefgh";
	
	public static void main(String[] args){
		
//		String string = "{\"customer_number\":\"123456789012345678\", \"time\":1488386073}";
		long base_cusotmerNo = 123456789012345678l;
		
		AlgoFetcher fetcher = new AlgoFetcher();
		AlgorithmInterface algorithm = fetcher.getAlgorithm("JWT");
		RSAPrivateKey key = ((JWTEncryption)algorithm).getRSAPrivateKey();
		long startTime = System.currentTimeMillis();
		
		Runtime runtime = Runtime.getRuntime();
		int availableProcessors = runtime.availableProcessors()+1;
		
		ExecutorService service = Executors.newFixedThreadPool(availableProcessors);
		for (int i = 0; i < 10000000; i++) {
			long custNumber = base_cusotmerNo+i;
			
			service.execute(new Runnable() {
				@Override
				public void run() {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("customer_number", custNumber);
					jsonObject.put("time", System.currentTimeMillis());
					String encryptedString = algorithm.encrypt(key, jsonObject.toString());
//			        System.out.println("Encrypted string = "+encryptedString);
				}
			});
		}
		
		service.shutdown();
		try {
			service.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Time taken = "+ (endTime-startTime));
		
//		FileWriter.writeToFile("algoDemo.txt", encryptedString);
	}

}
