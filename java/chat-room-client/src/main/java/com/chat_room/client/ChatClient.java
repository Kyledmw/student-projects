package com.chat_room.client;

import com.chat_room.client.rmi.RMIConnector;
import com.chat_room.client.ui.LoginDialog;
import com.chat_room.client.ui.MainFrame;

/**
 ********************************************************************
 * Main entry point of the application
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class ChatClient 
{

	private static final MainFrame _view = new MainFrame();
	
	public static void main(String[] args) {
		new LoginDialog(_view);
		RMIConnector.getInstance().addObserver(_view);
	}
}
