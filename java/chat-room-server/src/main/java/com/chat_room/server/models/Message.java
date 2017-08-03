package com.chat_room.server.models;

import com.chat_room.server.constants.IChatConstants;
import com.chat_room.server.models.interfaces.IMessage;

/**
 ********************************************************************
 * Implementation of the {@link IMessage} interface
 * 
 * @implements {@link IMessage}
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class Message implements IMessage {
	
	private static final String USERNAME = "Username: ";
	private static final String MESSAGE = " Message: ";
	
	private String _username;
	private String _message;
	
	public Message(String username, String message){
		_username = username;
		_message = message;
	}



	@Override
	public String toString() {
		return USERNAME + _username  + MESSAGE + _message;
	}


	public String getUpdateType() {
		return IChatConstants.NEW_MESSAGE;
	}



	public String getUsername() {
		return _username;
	}



	public String getMessage() {
		return _message;
	}

}