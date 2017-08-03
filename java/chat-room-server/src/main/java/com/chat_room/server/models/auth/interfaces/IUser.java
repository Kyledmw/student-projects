package com.chat_room.server.models.auth.interfaces;


/**
 ********************************************************************
 * Interface for a User object
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface IUser {
	
	/**
	 * Retrieve unique id given to the user
	 * 
	 * @return id
	 */
	public int getID();

	/**
	 * Retrieve type of the user
	 * 
	 * @return type
	 */
	public String getType();
	
	/**
	 * Retrieve username of the user
	 * 
	 * @return username
	 */
	public String getUsername();
	
	/**
	 * Retrieve age of the user
	 * 
	 * @return age
	 */
	public int getAge();
}

