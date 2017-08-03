package com.chat_room.server.models.interfaces;


/**
 ********************************************************************
 * Interface for a message
 * 
 * @extends {@link IChatUpdate}
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface IMessage extends IChatUpdate {
	
	/**
	 * Get name of user who sent the message
	 * 
	 * @return name of user
	 */
	public String getUsername();
	
	/**
	 * Get actual message contents
	 * 
	 * @return message contents
	 */
	public String getMessage();
	
}
