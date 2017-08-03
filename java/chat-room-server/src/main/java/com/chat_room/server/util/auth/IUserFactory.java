package com.chat_room.server.util.auth;

import com.chat_room.server.models.auth.interfaces.IUser;

/**
 ********************************************************************
 * Interface for a {@link IUser} factory
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface IUserFactory {

	/**
	 * Create a {@link IUser} object
	 * 
	 * @param username username for the user object
	 * @param age age of the user
	 * @return user object
	 */
	public IUser createUser(String username, int age);
}
