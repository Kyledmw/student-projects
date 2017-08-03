package com.chat_room.server.rmi.registry;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import com.chat_room.rmi.interfaces.IRemoteChatSessionFactory;
import com.chat_room.rmi.interfaces.IRemoteLoginService;
import com.chat_room.server.rmi.adapters.RMICallbackHandler;
import com.chat_room.server.rmi.adapters.RemoteChatSessionFactoryAdapter;
import com.chat_room.server.rmi.adapters.RemoteLoginServiceAdapter;
import com.chat_room.server.rmi.constants.IRMIConstants;
import com.chat_room.server.security.constants.IChatServerObjKeys;
import com.chat_room.server.services.auth.interfaces.ILoginService;
import com.chat_room.server.session.ChatRoom;
import com.chat_room.server.util.session.interfaces.IChatSessionFactory;

/**
 ********************************************************************
 * Class that contains the implementation of an RMI server.
 * 
 * It creates a registry at the port within {@link IRMIConstants}
 * It exports objects on the port within {@link IRMIConstants}
 * 
 * Exposes a {@link ILoginService} object as a {@link IRemoteLoginService} adapter
 * Exposes a {@link IChatSessionFactory} object as a {@link IRemoteChatSessionFactory} adapter
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class RMIChatServer {

	public RMIChatServer(ChatRoom chatRoom, ILoginService loginService, IChatSessionFactory userChatSessionFactory) {
		IRemoteChatSessionFactory remoteUserChatSessionFactory = new RemoteChatSessionFactoryAdapter(userChatSessionFactory);
		IRemoteLoginService remoteLoginService = new RemoteLoginServiceAdapter(loginService);
		RMICallbackHandler callbackHandler = RMICallbackHandler.getInstance();
		try {
			chatRoom.addObserver(callbackHandler);
			IRemoteLoginService loginServiceStub = (IRemoteLoginService) UnicastRemoteObject.exportObject(remoteLoginService, IRMIConstants.OBJ_REG_PORT);
			IRemoteChatSessionFactory chatSessionFactoryStub = (IRemoteChatSessionFactory) UnicastRemoteObject.exportObject(remoteUserChatSessionFactory, IRMIConstants.OBJ_REG_PORT);
			Registry registry = LocateRegistry.createRegistry(IRMIConstants.RMI_REGISTRY_PORT);
			registry.rebind(IChatServerObjKeys.CHAT_SESSION_FACTORY, chatSessionFactoryStub);
			registry.rebind(IChatServerObjKeys.LOGIN_SERVICE, loginServiceStub);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
	}
	
}
