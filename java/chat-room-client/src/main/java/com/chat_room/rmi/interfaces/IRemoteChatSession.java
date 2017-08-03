package com.chat_room.rmi.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 ********************************************************************
 * Remote interface for a ChatSession
 * 
 * @extends {@link Remote}
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface IRemoteChatSession extends Remote {
	
	/**
	 * Get chat id of the current chat session
	 * 
	 * @return given chat id to the chat session
	 * 
	 * @throws RemoteException
	 */
	public int getChatId() throws RemoteException;
	
	/**
	 * Get new from the current chat room
	 * 
	 * @param message {@link IRemoteCallback} callback object
	 */
	public void getMessage(IRemoteCallback<IRemoteMessage> message) throws RemoteException;
	
	/**
	 * Send message to the chat room
	 * 
	 * @param message string to send to the buffer
	 * 
	 * @throws RemoteException
	 */
	public void sendMessage(String message) throws RemoteException;
	
	/**
	 * Disconnect session from the chat room
	 *
	 * @return successful
	 * 
	 * @throws RemoteException
	 */
	public boolean disconnect() throws RemoteException;
}
