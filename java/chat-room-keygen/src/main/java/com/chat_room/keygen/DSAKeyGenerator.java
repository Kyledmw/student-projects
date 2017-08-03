package com.chat_room.keygen;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * Hello world!
 *
 */

public class DSAKeyGenerator {
	
	private static final String DSA_ALG = "DSA";
	private static final int BITS = 1024;
	
	private static final String PRIVATE_KEY = "private_key.ser";
	private static final String PUBLIC_KEY = "public_key.ser";
	
	public static void main(String[] args) {
		try {
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(DSA_ALG);
			keyPairGenerator.initialize(BITS);
			KeyPair keyPair = keyPairGenerator.generateKeyPair();
			PrivateKey privateKey = keyPair.getPrivate();
			PublicKey publicKey = keyPair.getPublic();
			
			serializeKey(privateKey, PRIVATE_KEY);
			serializeKey(publicKey, PUBLIC_KEY);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} 
	}
	
	private static void serializeKey(Key key, String filename) {
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream(filename);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(key);
			out.close();
			fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
