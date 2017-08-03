package com.chat_room.server.util.session.interfaces;

import com.chat_room.server.models.auth.interfaces.IUser;
import com.chat_room.server.session.ChatRoom;
import com.chat_room.server.session.interfaces.IChatSession;

/**
 ********************************************************************
 * Interface for a {@link IChatSession} factory
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface IChatSessionFactory {

	/**
	 * Create a chat session for the current {@link ChatRoom} 
	 * The IChatSession should observe the current chat room
	 * 
	 * @param cur user the session is created for {@link IUser}
	 * @return created chat session for the user {@link IChatSession}
	 */
	public IChatSession createSession(IUser cur);
}
