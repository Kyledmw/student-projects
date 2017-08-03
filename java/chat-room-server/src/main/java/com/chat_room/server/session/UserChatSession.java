package com.chat_room.server.session;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import com.chat_room.server.constants.IChatConstants;
import com.chat_room.server.models.auth.interfaces.IUser;
import com.chat_room.server.models.interfaces.IChatUpdate;
import com.chat_room.server.models.interfaces.IMessage;
import com.chat_room.server.session.interfaces.IChatSession;
import com.chat_room.server.util.MessageFactory;

/**
 ********************************************************************
 * Implementation of {@link IChatSession} for User clients
 * 
 * @extends {@link java.util.Observable}
 * @implements {@link java.util.Observer}, {@link IChatSession}
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class UserChatSession extends Observable implements IChatSession, Observer {
	
	private int _id;
	private List<IMessage> _messages;
	private IUser _user;
	private ChatBuffer _chatBuffer;
	private boolean _newMessages;
	
	public UserChatSession(int id, IUser user, ChatBuffer chatBuffer) {
		_messages = new ArrayList<IMessage>();
		_id = id;
		_user = user;
		_chatBuffer = chatBuffer;
		_newMessages = false;
	}

	public void update(Observable o, Object arg) {
		if(arg instanceof IChatUpdate){
			String updateType = ((IChatUpdate) arg).getUpdateType();
			if(updateType.equals(IChatConstants.NEW_MESSAGE)) {
				_messages.add((IMessage) arg);
				System.out.println(_messages);
				_newMessages = true;
			}
		}
	}

	public List<IMessage> getMessages() {
		return _messages;
	}

	public void sendMessage(String message) {
		IMessage userMessage = new MessageFactory().create(_user, message);
		_chatBuffer.putMessage(userMessage);
	}
	
	public IUser getUser() {
		return _user;
	}

	public boolean disconnect() {
		_chatBuffer.putSessionForDisconnect(this);
		return true;
	}

	public int getChatId() {
		return _id;
	}

	public boolean newMessages() {
		boolean newMessages = _newMessages;
		_newMessages = false;
		return newMessages;
	}
	
}
