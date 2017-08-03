package com.chat_room.server;

import com.chat_room.server.rmi.registry.RMIChatServer;
import com.chat_room.server.services.auth.LoginService;
import com.chat_room.server.services.auth.interfaces.ILoginService;
import com.chat_room.server.session.ChatBuffer;
import com.chat_room.server.session.ChatRoom;
import com.chat_room.server.util.session.UserChatSessionFactory;
import com.chat_room.server.util.session.interfaces.IChatSessionFactory;

/**
 ********************************************************************
 * Main server class.
 * 
 * It is the entry point of the application
 * It sets up the security policy and security manager
 * 
 * Initializes the initial {@link ChatRoom} and {@link ChatBuffer} objects
 * 
 * Creates the RMI Server and Registry
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class ChatServer {
	
	private static final String SECURITY_POLICY_KEY = "java.security.policy";
	private static final String POLICY_FILE_PATH = "src/main/resources/server.policy";

	public static void main(String[] args) {
		
		System.setProperty(SECURITY_POLICY_KEY, POLICY_FILE_PATH);
		
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        
		ChatBuffer chatBuffer = new ChatBuffer();
		ChatRoom chatRoom = new ChatRoom(chatBuffer);
		
		IChatSessionFactory userChatSessionFactory = new UserChatSessionFactory(chatRoom, chatBuffer);
		ILoginService loginService = new LoginService();
		
		RMIChatServer rmiChatServer = new RMIChatServer(chatRoom, loginService, userChatSessionFactory);
		
		chatRoom.start();
	}

}