package com.chat_room.server.util.session;

import java.util.Observer;

import com.chat_room.server.models.auth.interfaces.IUser;
import com.chat_room.server.session.ChatBuffer;
import com.chat_room.server.session.ChatRoom;
import com.chat_room.server.session.UserChatSession;
import com.chat_room.server.session.interfaces.IChatSession;
import com.chat_room.server.util.session.interfaces.IChatSessionFactory;

/**
 ********************************************************************
 * Factory for {@link IChatSession} objects
 * 
 * @implements {@link IChatSessionFactory}
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class UserChatSessionFactory implements IChatSessionFactory {
	
	private static int nextChatSessionID = 1;
	
	private ChatRoom _chatRoom;
	private ChatBuffer _messageBuffer;
	
	/**
	 * Construct the UserChatSessionFactory with the current chat room and chat buffer
	 * 
	 * @param chatRoom	{@link ChatRoom} current chat room running on the server
	 * @param messageBuffer {@link ChatBuffer} current buffer used between chat sessions and the chat room
	 */
	public UserChatSessionFactory(ChatRoom chatRoom, ChatBuffer messageBuffer) {
		_chatRoom = chatRoom;
		_messageBuffer = messageBuffer;
	}
	
	public IChatSession createSession(IUser cur) {
		IChatSession chatSession = new UserChatSession(nextChatSessionID, cur, _messageBuffer);
		nextChatSessionID++;
		_chatRoom.addObserver((Observer) chatSession);
		return chatSession;
	}
}
		