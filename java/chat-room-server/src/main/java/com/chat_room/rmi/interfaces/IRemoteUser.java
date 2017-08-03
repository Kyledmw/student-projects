package com.chat_room.rmi.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 ********************************************************************
 * Remote interface for a User object
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface IRemoteUser extends Remote {
	
	/**
	 * Retrieve unique id given to the user
	 * 
	 * @return id
	 * 
	 * @throws RemoteException
	 */
	public int getID() throws RemoteException;
	
	/**
	 * Retrieve type of the user
	 * 
	 * @return type
	 */
	public String getType() throws RemoteException;
	
	/**
	 * Retrieve username of the user
	 * 
	 * @return username
	 * 
	 * @throws RemoteException
	 */
	public String getUsername() throws RemoteException ;
	
	/**
	 * Retrieve age of the user
	 * 
	 * @return age
	 * 
	 * @throws RemoteException
	 */
	public int getAge() throws RemoteException;
}
