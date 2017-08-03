package com.chat_room.server.services.auth.interfaces;

import com.chat_room.server.models.auth.interfaces.IUser;

/**
 ********************************************************************
 * Service interface for users to login
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface ILoginService {
	
	/**
	 * Method that allows users to login.
	 * Returns object for that user
	 * 
	 * @param username username of the user
	 * @param age age of the user
	 * 
	 * @return {@link IUser} object for the logged in user
	 */
	public IUser login(String username, int age);
}

