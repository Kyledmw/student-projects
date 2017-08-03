package com.chat_room.client.security;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.SignedObject;

/**
 ********************************************************************
 * Class that reads in a public key using serialization and unsigns objects.
 * 
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class ObjectUnsigner {

	private static ObjectUnsigner _instance;
	
	private static final String PUBLIC_KEY = "src/main/resources/public_key.ser";
	
	private PublicKey key;
	
	private ObjectUnsigner() throws ClassNotFoundException, IOException {
		FileInputStream fileIn = new FileInputStream(PUBLIC_KEY);
		ObjectInputStream in = new ObjectInputStream(fileIn);
		key = (PublicKey) in.readObject();
		in.close();
		fileIn.close();
	}
	
	public static ObjectUnsigner getInstance() throws ClassNotFoundException, IOException {
		if(_instance == null) {
			_instance = new ObjectUnsigner();
		}
		return _instance;
	}
	
	/**
	 * Unsign the given signed object if it is verified
	 * @param object
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws SignatureException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public Object unsignObject(SignedObject object) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, ClassNotFoundException, IOException {
		Signature sig = Signature.getInstance(key.getAlgorithm());
		if(object.verify(key, sig)) {
			return object.getObject();
		}
		return null;
	}
}

