package com.chat_room.rmi.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.security.SignedObject;

/**
 ********************************************************************
 * Remote interface for a message
 * 
 * @extends {@link Remote}, {@link IRemoteChatUpdate}
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface IRemoteMessage extends IRemoteChatUpdate {
	
	/**
	 * Get name of user who sent the message
	 * 
	 * @return name of user
	 * 
	 * @throws RemoteException
	 */
	public String getUsername() throws RemoteException;
	
	/**
	 * Get actual message contents as a signed object
	 * 
	 * @return signed message contents
	 * 
	 * @throws RemoteException
	 */
	public SignedObject getMessage() throws RemoteException;
}
