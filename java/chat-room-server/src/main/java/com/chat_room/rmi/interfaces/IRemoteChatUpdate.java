package com.chat_room.rmi.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 ********************************************************************
 * Remote interface for a generic chat update
 * 
 * @extends {@link Remote}
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface IRemoteChatUpdate extends Remote {

	/**
	 * Method that retrieves the type of update
	 * 
	 * @return type of update
	 * 
	 * @throws RemoteException
	 */
	public String getUpdateType() throws RemoteException;

}
