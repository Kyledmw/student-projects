package com.chat_room.server.rmi.adapters;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.chat_room.rmi.interfaces.IRemoteChatSession;
import com.chat_room.rmi.interfaces.IRemoteChatSessionFactory;
import com.chat_room.rmi.interfaces.IRemoteUser;
import com.chat_room.server.constants.IUserConstants;
import com.chat_room.server.dao.auth.UsersDataAccess;
import com.chat_room.server.exceptions.auth.UserNotFoundException;
import com.chat_room.server.models.auth.interfaces.IUser;
import com.chat_room.server.rmi.constants.IRMIConstants;
import com.chat_room.server.session.interfaces.IChatSession;
import com.chat_room.server.util.session.interfaces.IChatSessionFactory;

/**
 ********************************************************************
 * Adapter of a {@link IChatSessionFactory} object for remote clients
 * 
 * @implements {@link IRemoteChatSessionFactory}
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class RemoteChatSessionFactoryAdapter implements IRemoteChatSessionFactory {
	
	private IChatSessionFactory _chatSessionFactory;
	
	public RemoteChatSessionFactoryAdapter(IChatSessionFactory chatSessionFactory) {
		_chatSessionFactory = chatSessionFactory;
	}

	public IRemoteChatSession createSession(IRemoteUser cur) throws RemoteException {
		IUser user = null;
		try {
			user = UsersDataAccess.getInstance().getUserByID(cur.getID());
		} catch (UserNotFoundException e) {
			throw new RemoteException(IUserConstants.USER_NOT_FOUND);
		}
		IChatSession chatSession = _chatSessionFactory.createSession(user);
		IRemoteChatSession remoteChatSession = new RemoteChatSessionAdapter(chatSession);
		
		return (IRemoteChatSession) UnicastRemoteObject.exportObject(remoteChatSession, IRMIConstants.OBJ_REG_PORT);
	}

}
