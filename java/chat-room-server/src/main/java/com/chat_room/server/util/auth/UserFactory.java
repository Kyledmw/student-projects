package com.chat_room.server.util.auth;

import com.chat_room.server.dao.auth.UsersDataAccess;
import com.chat_room.server.models.auth.User;
import com.chat_room.server.models.auth.interfaces.IUser;

/**
 ********************************************************************
 * Factory for {@link IUser} objects
 * 
 * @implements {@link IUserFactory}
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class UserFactory implements IUserFactory {
	
	private static final int ADULT_AGE = 18;
	
	private static int nextUser = 1;


	public IUser createUser(String username, int age){
		String type = getType(age);
		IUser user = new User(nextUser, username, age, type);
		UsersDataAccess.getInstance().addUser(user);
		nextUser++;
		return user;
	}

	/**
	 * Get type of user based off the given age
	 * 
	 * @param age age of the user
	 * @return type of user as a string
	 */
	private String getType(int age) {
		if(age < ADULT_AGE ) {
			return UserType.CHILD.type();
		} else {
			return UserType.ADULT.type();
		}
	}
	
	/**
	 ********************************************************************
	 * ENUM for different UserTypes in the system
	 *
	 * @author Kyle Williamson
	 * @version 1.0.0
	 ********************************************************************
	 */
	private enum UserType {
		
		CHILD ("Child"),
		ADULT("Adult");
		
		private final String _type;
		UserType(String type) {
			this._type = type;
		}
		
		/**
		 * Get string representation of the type
		 * 
		 * @return type of user
		 */
		private String type() {
			return _type;
		}
	}
}
