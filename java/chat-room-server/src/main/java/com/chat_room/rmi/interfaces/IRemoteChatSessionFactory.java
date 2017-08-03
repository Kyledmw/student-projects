package com.chat_room.rmi.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 ********************************************************************
 * Remote interface for a {@link IRemoteChatSession} factory
 * 
 * @extends {@link Remote}
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface IRemoteChatSessionFactory extends Remote {
	
	/**
	 * Create a chat session for the current chat room
	 * 
	 * @param cur user the session is created for {@link IRemoteUser}
	 * @return created chat session for the user {@link IRemoteChatSession}
	 * 
	 * @throws RemoteException
	 */
	public IRemoteChatSession createSession(IRemoteUser cur) throws RemoteException;
}
