package com.chat_room.client.rmi;

import com.chat_room.rmi.interfaces.IRemoteCallback;
import com.chat_room.rmi.interfaces.IRemoteMessage;

public class RMICallback implements IRemoteCallback<IRemoteMessage> {

	public void notify(IRemoteMessage object) {
		RMIConnector.getInstance().addMessage(object);
	}

}

