package com.chat_room.server.session;

import com.chat_room.server.models.interfaces.IMessage;
import com.chat_room.server.session.interfaces.IChatSession;

/**
 ********************************************************************
 * This class is a shared memory buffer between multiple {@link IChatSession} objects and a {@link ChatRoom} object.
 * 
 * The {@link ChatRoom} object acts as a consumer.
 * 
 * The {@link IChatSession} objects act as producers.
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class ChatBuffer {

	boolean _messageAvailable;
	boolean _disconnectAvailable;
	private IMessage _newMessage;
	private IChatSession _sessionToDisconnect;
	
	public ChatBuffer() {
		_messageAvailable = false;
		_disconnectAvailable = false;
	}
	
	/**
	 * Put a a message into the buffer to be consumed
	 * 
	 * @param message message to send to the chat room {@link IMessage}
	 */
	public synchronized void putMessage(IMessage message) {
		while(isMessageAvailable()) {
			try {
				wait();
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		_newMessage = message;
		_messageAvailable = true;
		notifyAll();
	}
	
	/**
	 * Consume the message currently in the buffer
	 * 
	 * @return current messasge in the buffer {@link IMessage}
	 */
	public synchronized IMessage getMessage() {
		while(!isMessageAvailable()) {
			try {
				wait();
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		_messageAvailable = false;
		notifyAll();
		return _newMessage;
	}
	
	/**
	 * Put a session into the buffer to be disconnected at a later time.
	 * 
	 * @param session session to be put on the buffer {@link IChatSession}
	 */
	public synchronized void putSessionForDisconnect(IChatSession session) {
		while(isDisconnectAvailable()) {
			try {
				wait();
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		_sessionToDisconnect = session;
		_disconnectAvailable = true;
		notifyAll();
	}
	
	/**
	 * Consume a session from the buffer to be disconnected.
	 * 
	 * @return session session to disconnect {@link IChatSession}
	 */
	public synchronized IChatSession getSessionToDisconnect() {
		while(!isDisconnectAvailable()) {
			try {
				wait();
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		_disconnectAvailable = false;
		notifyAll();
		return _sessionToDisconnect;
	}
	
	
	/**
	 * Check if there is a disconnect session available to be consumed
	 * 
	 * @return boolean flag for on disconnect availability
	 */
	public boolean isDisconnectAvailable() {
		return _disconnectAvailable;
	}
	
	/**
	 * Check if there is a message available to be consumed
	 * 
	 * @return boolean flag for on message availability
	 */
	public boolean isMessageAvailable() {
		return _messageAvailable;
	}
}
