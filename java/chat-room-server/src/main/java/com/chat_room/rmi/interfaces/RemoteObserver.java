package com.chat_room.rmi.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 ********************************************************************
 * Remote interface for a observer object
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface RemoteObserver extends Remote {

	/**
	 * Remote update method,
	 * Gets called when a Remote observable object changes
	 * 
	 * @throws RemoteException
	 */
	void update() throws RemoteException;
}
