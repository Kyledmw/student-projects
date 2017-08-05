package com.crowdfunder.control.util.interfaces;

import java.util.List;

import com.crowdfunder.models.Pledge;
import com.crowdfunder.models.Project;

/**
 ********************************************************************
 * Interface for retrieving pledges to display for a project
 * 
 * Used for th:if statements
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface IDisplayPledges {

	/**
	 * Retrieves the required list of pledges to display for the project
	 * 
	 * @param proj Project to get pledges from
	 * @return List of pledges to display for a project
	 */
	List<Pledge> getPledgesToDisplay(Project proj);
	
	/**
	 * Retrieves the valid amount of pledges for a project
	 * 
	 * @param proj Project to count valid pledges
	 * @return int representing valid pledge amount
	 */
	int getValidPledgeAmount(Project proj);
}
