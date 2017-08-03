package com.chat_room.server.dao.auth;

import java.util.ArrayList;
import java.util.List;

import com.chat_room.server.exceptions.auth.UserNotFoundException;
import com.chat_room.server.models.auth.interfaces.IUser;

/**
 ********************************************************************
 * Singleton Data access object for users on the server
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class UsersDataAccess {
	
	private static UsersDataAccess _instance;
	private List<IUser>	_users;
	
	private UsersDataAccess() {
		_users = new ArrayList<IUser>();
	}
	
	/**
	 * Retrieve instance of the singleton
	 * 
	 * @return singleton instance of this class
	 */
	public static UsersDataAccess getInstance() {
		if(_instance == null) {
			_instance = new UsersDataAccess();
		}
		return _instance;
	}
	
	/**
	 * Add {@link IUser} object to this object
	 * 
	 * @param user user to be added to the object
	 */
	public void addUser(IUser user) {
		_users.add(user);
	}
	
	/**
	 * Get the {@link IUser} object with the given id
	 * 
	 * @param id id of user to find
	 * @return user object of the given id
	 * @throws UserNotFoundException if user not found
	 */
	public IUser getUserByID(int id) throws UserNotFoundException {
		for(IUser cur: _users) {
			if(id == cur.getID()) {
				return cur;
			}
		}
		throw new UserNotFoundException();
	}

}
