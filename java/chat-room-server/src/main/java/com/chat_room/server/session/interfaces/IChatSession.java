package com.chat_room.server.session.interfaces;

import java.util.List;
import java.util.Observer;

import com.chat_room.server.models.interfaces.IMessage;
import com.chat_room.server.session.ChatBuffer;

/**
 ********************************************************************
 * Interface for a generic chat session
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface IChatSession {
	
	/**
	 * Get chat id of the current chat session
	 * 
	 * @return given chat id to the chat session
	 */
	public int getChatId();

	/**
	 * Get the current messages from the chat session
	 * 
	 * @return List of {@link IMessage} objects
	 */
	public List<IMessage> getMessages();
	
	/**
	 * Send message to the {@link ChatBuffer}
	 * 
	 * @param message string to send to the buffer
	 */
	public void sendMessage(String message);

	/**
	 * Disconnect session from the {@link ChatRoom}
	 *
	 * @return successful
	 */
	public boolean disconnect();
	
	/**
	 * Check if any new messages in the session
	 * 
	 * @return new message flag
	 */
	public boolean newMessages();
	
	public void addObserver(Observer o);
}

