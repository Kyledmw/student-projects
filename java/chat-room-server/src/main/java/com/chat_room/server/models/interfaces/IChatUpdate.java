package com.chat_room.server.models.interfaces;


/**
 ********************************************************************
 * Interface for a generic chat update
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface IChatUpdate {
	
	/**
	 * Method that retrieves the type of update
	 * 
	 * @return type of update
	 */
	public String getUpdateType();
}
