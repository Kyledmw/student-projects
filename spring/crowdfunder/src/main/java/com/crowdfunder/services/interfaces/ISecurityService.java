package com.crowdfunder.services.interfaces;

/**
 ********************************************************************
 * Interface for service object for handling security
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface ISecurityService {
	
	/**
	 * Retrieve the username for the current logged in user
	 * 
	 * @return String username of the logged in user
	 */
	public String findLoggedInUsername();
	
	/**
	 * Login a user with the given details
	 * Adds token to the security context
	 * 
	 * @param username username of the user
	 * @param password users password
	 */
	public void autoLogin(String username, String password);
}
