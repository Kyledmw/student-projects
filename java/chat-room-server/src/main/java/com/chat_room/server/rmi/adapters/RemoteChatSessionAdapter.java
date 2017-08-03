package com.chat_room.server.rmi.adapters;

import java.rmi.RemoteException;

import com.chat_room.rmi.interfaces.IRemoteCallback;
import com.chat_room.rmi.interfaces.IRemoteChatSession;
import com.chat_room.rmi.interfaces.IRemoteMessage;
import com.chat_room.server.session.interfaces.IChatSession;

/**
 ********************************************************************
 * Adapter of a {@link IChatSession} object for remote clients
 * 
 * @implements {@link IRemoteChatSession}
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class RemoteChatSessionAdapter implements IRemoteChatSession {
	
	private IChatSession _chatSession;
	
	public RemoteChatSessionAdapter(IChatSession chatSession) {
		_chatSession = chatSession;
	}

	public int getChatId() throws RemoteException {
		return _chatSession.getChatId();
	}

	public void sendMessage(String message) throws RemoteException {
		_chatSession.sendMessage(message);
		
	}

	public boolean disconnect() throws RemoteException {
		return _chatSession.disconnect();
	}

	public void getMessage(IRemoteCallback<IRemoteMessage> message) throws RemoteException {
		RMICallbackHandler.getInstance().addCallback(message);
	}

}
