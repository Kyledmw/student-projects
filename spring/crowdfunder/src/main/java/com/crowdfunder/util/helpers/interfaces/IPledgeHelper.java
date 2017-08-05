package com.crowdfunder.util.helpers.interfaces;

import java.math.BigDecimal;

import com.crowdfunder.models.Project;
import com.crowdfunder.models.User;

/**
 ********************************************************************
 * Interface which provides helper functionality for pledging and cancelling pledges
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface IPledgeHelper {

	/**
	 * Check if it is possible to pledge on a project.
	 * 
	 * @param project Project to check against
	 * @return boolean representing if it is possible to make a pledge
	 */
	boolean projectPledgeable(Project project);
	
	/**
	 * Check if a user is able to make a pledge of the given amount
	 * 
	 * @param user User to check against
	 * @param amountToPledge Amount to pledge
	 * @return boolean representing if the user can make the pledge
	 */
	boolean userCanPledge(User user, BigDecimal amountToPledge);
	
	/**
	 * Check if pledges can be cancelled for a project
	 * 
	 * @param project Project to check against
	 * @return boolean representing if pledges can be cancelled or not
	 */
	boolean pledgeCancelableForProject(Project project);
}
