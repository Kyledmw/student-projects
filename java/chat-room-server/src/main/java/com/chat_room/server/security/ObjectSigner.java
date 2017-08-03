package com.chat_room.server.security;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.SignedObject;

/**
 ********************************************************************
 * Class that reads in a private key using serialization and signs objects.
 * 
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class ObjectSigner {
	
	private static ObjectSigner _instance;
		
	private static final String PRIVATE_KEY = "src/main/resources/private_key.ser";
	
	private PrivateKey key;
	
	private ObjectSigner() throws IOException, ClassNotFoundException {
		FileInputStream fileIn = new FileInputStream(PRIVATE_KEY);
		ObjectInputStream in = new ObjectInputStream(fileIn);
		key = (PrivateKey) in.readObject();
		in.close();
		fileIn.close();
	}
	
	public static ObjectSigner getInstance() throws ClassNotFoundException, IOException {
		if(_instance == null) {
			_instance = new ObjectSigner();
		}
		return _instance;
	}
	
	/**
	 * Sign a given serializable object
	 * 
	 * @param ser serializable object
	 * @return SignedObject
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws SignatureException
	 * @throws IOException
	 */
	public SignedObject signObject(Serializable ser) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, IOException {
		Signature sig = Signature.getInstance(key.getAlgorithm());
		SignedObject signedObject = new SignedObject(ser, key, sig);
		return signedObject;
	}
}
