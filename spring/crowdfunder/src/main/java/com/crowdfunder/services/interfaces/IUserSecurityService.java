package com.crowdfunder.services.interfaces;

import com.crowdfunder.models.User;

/**
 ********************************************************************
 * Interface that aggregates functionality from ISecurityService and IUserService
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface IUserSecurityService {

	/**
	 * Retrieve the current logged in user object
	 * 
	 * @return logged in user boject
	 */
	User getLoggedInUser();
}
