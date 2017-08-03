package com.chat_room.server.services.auth;

import com.chat_room.server.models.auth.interfaces.IUser;
import com.chat_room.server.services.auth.interfaces.ILoginService;
import com.chat_room.server.util.auth.UserFactory;

/**
 ********************************************************************
 * Service for users to login
 * 
 * @implements {@link ILoginService}
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class LoginService implements ILoginService {
	
	public IUser login(String username, int age) {
		return new UserFactory().createUser(username, age);
	}
}
