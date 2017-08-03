package com.chat_room.client.rmi;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javax.swing.JOptionPane;

import com.chat_room.client.constants.IObserverConstants;
import com.chat_room.client.rmi.constants.IRMIConstants;
import com.chat_room.client.security.constants.IChatServerObjKeys;
import com.chat_room.client.ui.constants.IUIConstants;
import com.chat_room.rmi.interfaces.IRemoteCallback;
import com.chat_room.rmi.interfaces.IRemoteChatSession;
import com.chat_room.rmi.interfaces.IRemoteChatSessionFactory;
import com.chat_room.rmi.interfaces.IRemoteLoginService;
import com.chat_room.rmi.interfaces.IRemoteMessage;
import com.chat_room.rmi.interfaces.IRemoteUser;

/**
 ********************************************************************
 * Singleton class that controls the connection to the RMI server
 * 
 * Actively watches server for new messages after session has started
 * 
 * @extends {@link Observable}
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class RMIConnector extends Observable {
	
	private static final String SECURITY_POLICY_KEY = "java.security.policy";
	private static final String POLICY_FILE_PATH = "src/main/resources/client.policy";
	
	private static RMIConnector _instance;
	
	private Registry _registry;
	
	private IRemoteLoginService _loginService;
	
	private IRemoteUser _user;
	
	private IRemoteChatSessionFactory _chatSessionFactory;
	
	private IRemoteChatSession _session;
	
	private List<IRemoteMessage> _messages;
	
	private boolean _active;
	
	/**
	 * Here we define the security policy file as well as the security manager.
	 * We then connect to the RMI server and registry
	 */
	private RMIConnector() {
		System.setProperty(SECURITY_POLICY_KEY, POLICY_FILE_PATH);
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
			_registry = LocateRegistry.getRegistry(IRMIConstants.RMI_REGISTRY_PORT);
			_messages = new ArrayList<IRemoteMessage>();
		} catch (RemoteException e) {
			JOptionPane.showMessageDialog(null, IUIConstants.SERVER_DOWN_ERR); 
			System.exit(0);
		}
        _active = false;
	}
	
	/**
	 * Login to the remote server
	 * 
	 * @param username username for the user to login as
	 * @param age age of the user
	 */
	public void login(String username, int age) {
		try {
			_loginService = (IRemoteLoginService) _registry.lookup(IChatServerObjKeys.LOGIN_SERVICE);
			_user = _loginService.login(username, age);
		} catch (AccessException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			JOptionPane.showMessageDialog(null, IUIConstants.LOGIN_ERR);
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Start chat session between the logged in user and the server chat room
	 * Set the disconnect hook
	 * Make the active flag true
	 */
	public void startSession() {
		 try {
			_chatSessionFactory = (IRemoteChatSessionFactory) _registry.lookup(IChatServerObjKeys.CHAT_SESSION_FACTORY);
			_session = _chatSessionFactory.createSession(_user);
			setDisconnectHook();
			_active = true;
			sendMessageCallback();
		 } catch (AccessException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			JOptionPane.showMessageDialog(null, IUIConstants.SERVER_ERR);
			System.exit(0);
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Send message to the remote chat session
	 * 
	 * @param message message to send
	 */
	public void sendMessage(String message) {
		try {
			_session.sendMessage(message);
		} catch (RemoteException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, IUIConstants.SEND_MESSAGE_ERR);
		}
	}
	
	/**
	 * Notify all the objects observing with the given message
	 * 
	 * @param message message from {@link IObserverConstants}
	 */
	public void performNotification(String message) {
		setChanged();
		notifyObservers(message);
		hasChanged();
	}
	
	public void addMessage(IRemoteMessage message) {
		_messages.add(message);
		performNotification(IObserverConstants.MESSAGE_UPDATE);
		try {
			sendMessageCallback();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void sendMessageCallback() throws RemoteException{
		IRemoteCallback<IRemoteMessage> callback = (IRemoteCallback<IRemoteMessage>) UnicastRemoteObject.exportObject(new RMICallback(), 0);
		_session.getMessage(callback);
	}
	
	/**
	 * Retrieve remote messages from the RMI server
	 * 
	 * @return list of {@link IRemoteMessages}
	 * @throws RemoteException
	 */
	public List<IRemoteMessage> getMessages() throws RemoteException {
		return _messages;
	}
	
	/**
	 * Retrieve singleton instance of the RMIConnecter
	 * 
	 * @return singleton instance of this class
	 */
	public static RMIConnector getInstance() {
		if(_instance == null) {
			_instance = new RMIConnector();
		}
		return _instance;
	}
	
	/**
	 * Set a a shutdown hook for notifying the server that the chat session has disconnected
	 */
	private void setDisconnectHook() {
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {

			public void run() {
				try {
					if(_active) {
						_session.disconnect();
					}
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
			
		}));
	}
}

