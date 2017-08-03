package com.chat_room.server.exceptions.auth;

import com.chat_room.server.constants.IUserConstants;

/**
 ********************************************************************
 * Exception thrown if user cannot be found on the server
 * 
 * See {@link IUserConstants} <b><i>USER_NOT_FOUND</i></b>
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class UserNotFoundException extends Exception {

	private static final long serialVersionUID = -3304986045681344979L;

	public UserNotFoundException() {
		super(IUserConstants.USER_NOT_FOUND);
	}
}

