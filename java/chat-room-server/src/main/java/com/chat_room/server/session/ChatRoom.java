package com.chat_room.server.session;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.chat_room.server.models.interfaces.IChatUpdate;
import com.chat_room.server.models.interfaces.IMessage;
import com.chat_room.server.session.interfaces.IChatSession;
import com.chat_room.server.util.MessageFactory;

/**
 ********************************************************************
 * This class acts as the ChatRoom in which all {@link IChatSession} objects observe.
 * 
 * It consumes from a shared {@link ChatBuffer} object.
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class ChatRoom extends Observable {
	
	private static final int THREAD_POOL_SIZE = 2;
	private static final int THREAD_SLEEP_TIME = 100;
	
	private ChatBuffer _chatBuffer;
	
	private boolean _chatSessionActive;
	
	public ChatRoom(ChatBuffer chatBuffer) {
		_chatBuffer = chatBuffer;
	}
	
	/**
	 * Check if ChatRoom is active
	 * 
	 * @return boolean flag if active
	 */
	public boolean isActive() {
		return _chatSessionActive;
	}
	
	/**
	 * End chat session
	 */
	public void endChatSession() {
		_chatSessionActive = false;
	}
	
	@Override
	public void addObserver(Observer o) {
		if(o instanceof UserChatSession) {
			newUserSessionMessage((UserChatSession) o);
		}
		System.out.println("addObserver");
		super.addObserver(o);
	}
	
	/**
	 * Create a new {@link IMessage} to inform existing UserChatSessions of the new user
	 * 
	 * @param session new {@link UserChatSession} that existings sessions will be notified of
	 */
	private void newUserSessionMessage(UserChatSession session) {
		IMessage message = null;
		message = new MessageFactory().createJoinMessage(session.getUser());
		performNotification(message);
	}
	
	/**
	 * Disconnect a {@link IChatSession} from the ChatRoom
	 * 
	 * @param session ChatSession to disconnect.
	 */
	private void disconnectSession(IChatSession session) {
		deleteObserver((Observer) session);
		IMessage message = new MessageFactory().createDisconnectMessage(session.getChatId());
		performNotification(message);
	}
	
	/**
	 * Worker method to notify observers
	 * 
	 * @param update {@link IChatUpdate} Update to send to all observers
	 */
	private void performNotification(IChatUpdate update) {
		System.out.println("performNotification");
		//Informs observers this object has changed
		setChanged();
		//This will notify all observers if the changed variable is true
		notifyObservers(update);
		//Reset the changed variable now that observers have been notified.
		clearChanged();
	}
	
	/**
	 * Method that starts the chat room
	 * This sets up 2 threads: 
	 * One consuming disconnects
	 * And one consuming messages
	 * These are based off the chat session active flag
	 */
	public void start() {	
		_chatSessionActive = true;
		ExecutorService es = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
		
		Thread disconnectThread = new Thread(new Runnable() {

			public void run() {
				while(isActive()) {
					IChatSession session = _chatBuffer.getSessionToDisconnect();
					disconnectSession(session);
					try {
						Thread.sleep(THREAD_SLEEP_TIME);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}			
			
		});
		
		Thread messageThread = new Thread(new Runnable() {

			public void run() {
				while(isActive()) {
					IMessage message = _chatBuffer.getMessage();
					performNotification(message);
					
					try {
						Thread.sleep(THREAD_SLEEP_TIME);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			
		});
		es.execute(disconnectThread);
		es.execute(messageThread);
		
		es.shutdown();
	}
}

