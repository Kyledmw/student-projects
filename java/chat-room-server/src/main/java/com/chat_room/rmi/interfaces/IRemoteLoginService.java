package com.chat_room.rmi.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 ********************************************************************
 * Remote interface for a login service
 * 
 * @extends {@link Remote}
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface IRemoteLoginService extends Remote {
	
	/**
	 * Method that allows users to login.
	 * Returns object for that user
	 * 
	 * @param username username of the user
	 * @param age age of the user
	 * 
	 * @return {@link IRemoteUser} object for the logged in user
	 * 
	 * @throws RemoteException
	 */
	public IRemoteUser login(String username, int age) throws RemoteException;
}
