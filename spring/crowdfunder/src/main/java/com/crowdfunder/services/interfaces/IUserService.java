package com.crowdfunder.services.interfaces;

import com.crowdfunder.models.User;

/**
 ********************************************************************
 * Interface for a service object for the User domain
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface IUserService {

	/**
	 * Insert / update user object in the DAO
	 * 
	 * @param user User to save
	 */
	public void save(User user);
	
	/**
	 * Retrieve a user object for the given username
	 * 
	 * Usernames are unique
	 * 
	 * @param username String, username to search with
	 * @return User object for the found user
	 */
	public User findByUsername(String username);
	
	/**
	 * Retrieve a user object for the given email
	 * 
	 * emails are unique
	 * 
	 * @param email String, email to search with
	 * @return User object for the found user
	 */
	public User findByEmail(String email);

}
