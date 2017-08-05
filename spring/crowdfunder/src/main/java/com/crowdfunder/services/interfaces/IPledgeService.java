package com.crowdfunder.services.interfaces;

import java.util.List;

import com.crowdfunder.models.Pledge;

/**
 ********************************************************************
 * Interface for a service object for the Pledge domain
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface IPledgeService {
	
	/**
	 * Insert/update pledge to its DAO
	 * 
	 * @param pledge Pledge to save
	 */
	public void save(Pledge pledge);
	
	/**
	 * Cancels the given list of pledges
	 * 
	 * @param pledges Pledges to cancel
	 */
	void cancelPledges(List<Pledge> pledges);
	
	/**
	 * Cancel an individual pledge
	 * 
	 * @param pledge
	 * @return true if cancelled
	 * @return false if project already completed
	 */
	boolean cancelPledge(Pledge pledge);
}
