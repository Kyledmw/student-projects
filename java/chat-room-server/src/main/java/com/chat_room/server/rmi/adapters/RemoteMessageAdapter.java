package com.chat_room.server.rmi.adapters;

import java.rmi.RemoteException;
import java.security.SignedObject;

import com.chat_room.rmi.interfaces.IRemoteMessage;
import com.chat_room.server.models.interfaces.IMessage;
import com.chat_room.server.security.ObjectSigner;

/**
 ********************************************************************
 * Adapter of a {@link IMessage} object for remote clients
 * 
 * @implements {@link IRemoteMessage}
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class RemoteMessageAdapter implements IRemoteMessage {
	
	private IMessage _message;
	
	public RemoteMessageAdapter(IMessage message) {
		_message = message;
	}

	public String getUpdateType() throws RemoteException {
		return _message.getUpdateType();
	}

	public String getUsername() throws RemoteException {
		return _message.getUsername();
	}

	public SignedObject getMessage() throws RemoteException {
		try {
			return ObjectSigner.getInstance().signObject(_message.getMessage());
		} catch (Exception e) {
			throw new RemoteException();
		}
	}

}
