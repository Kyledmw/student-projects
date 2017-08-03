package com.chat_room.server.rmi.adapters;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.chat_room.rmi.interfaces.IRemoteLoginService;
import com.chat_room.rmi.interfaces.IRemoteUser;
import com.chat_room.server.models.auth.interfaces.IUser;
import com.chat_room.server.rmi.constants.IRMIConstants;
import com.chat_room.server.services.auth.interfaces.ILoginService;

/**
 ********************************************************************
 * Adapter of a {@link ILoginService} object for remote clients
 * 
 * @implements {@link IRemoteLoginService}
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class RemoteLoginServiceAdapter implements IRemoteLoginService {
	
	private ILoginService _loginService;
	
	public RemoteLoginServiceAdapter(ILoginService loginService) {
		_loginService = loginService;
	}

	public IRemoteUser login(String username, int age) throws RemoteException {
		IUser user = _loginService.login(username, age);
		IRemoteUser rUser = new RemoteUserAdapter(user);
		return (IRemoteUser) UnicastRemoteObject.exportObject(rUser, IRMIConstants.OBJ_REG_PORT);
	}

}
