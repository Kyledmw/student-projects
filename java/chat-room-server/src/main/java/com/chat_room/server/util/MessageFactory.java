package com.chat_room.server.util;

import com.chat_room.server.models.Message;
import com.chat_room.server.models.auth.interfaces.IUser;
import com.chat_room.server.models.interfaces.IMessage;

/**
 ********************************************************************
 * Factory for {@link IMessage} objects
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class MessageFactory {
	
	private static final String CHAT_ROOM_USER = "CHAT ROOM:";
	private static final String DISCONNECTED = " Disconnected.";
	private static final String SESSION = "Session: ";
	private static final String HAS_JOINED = " has joined.";
	
	/**
	 * Create a generic {@link IMessage} object
	 * 
	 * @param user	{@link IUser} details to be included in the message
	 * @param message message as a string to be included in the message
	 * @return message object
	 */
	public IMessage create(IUser user, String message) {
		return new Message(user.getUsername(), message);
	}
	
	/**
	 * Create a {@link IMessage} object containing details of a disconnect
	 * 
	 * @param chatId ID of the user that disconnected
	 * @return message object
	 */
	public IMessage createDisconnectMessage(int chatId) {
		String disconnectMessage = SESSION + chatId + DISCONNECTED;
		return new Message(CHAT_ROOM_USER, disconnectMessage);
	}
	
	/**
	 * Create a {@link IMessage} object containing details of a new chat session
	 * 
	 * @param user {@link IUser} user that has joined
	 * @return message object
	 */
	public IMessage createJoinMessage(IUser user) {
		String joinMessage = user.getUsername() + HAS_JOINED;
		return new Message(CHAT_ROOM_USER, joinMessage);
	}
}

