package com.chat_room.server.models.auth;

import java.io.Serializable;

import com.chat_room.server.models.auth.interfaces.IUser;

/**
 ********************************************************************
 * Implementation of a User object
 * 
 * @implements {@link IUser}
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class User implements IUser, Serializable {
	

	private static final long serialVersionUID = 2277428223902668888L;
	private int _id;
	private String _username;
	private int _age;
	private String _type;

	public User(int id, String username, int age, String type) {
		_id = id;
		_username = username;
		_age = age;
		_type = type;
	}

	public String getType() {
		return _type;
	}

	public String getUsername() {
		return _username;
	}

	public int getAge() {
		return _age;
	}

	public int getID() {
		return _id;
	}
}

