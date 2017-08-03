package com.chat_room.server.rmi.adapters;

import java.rmi.RemoteException;

import com.chat_room.rmi.interfaces.IRemoteUser;
import com.chat_room.server.models.auth.interfaces.IUser;

/**
 ********************************************************************
 * Adapter of a {@link IUser} object for remote clients
 * 
 * @implements {@link IRemoteUser}
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class RemoteUserAdapter implements IRemoteUser {
	
	private IUser _user;

	public RemoteUserAdapter(IUser user) {
		_user = user;
	}

	public int getID() throws RemoteException {
		return _user.getID();
	}

	public String getType() throws RemoteException {
		return _user.getType();
	}

	public String getUsername() throws RemoteException {
		return _user.getUsername();
	}

	public int getAge() throws RemoteException {
		return _user.getAge();
	}
}
