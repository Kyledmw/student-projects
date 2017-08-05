package com.voting_app.services.interfaces;

import com.voting_app.models.Constituency;
import com.voting_app.models.ConstituencyVotes;

/**
 ********************************************************************
 * Generic service interface for a ConstituencyVotes Repository
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface IConstituencyVotesService {
	
	/**
	 * Retrieve a constituency votes object for the given constituency object
	 * 
	 * @param constituency constituency object
	 * @return ConstituencyVotes
	 */
	ConstituencyVotes getVotesForConstiuency(Constituency constituency);
	
	/**
	 * Save a ConstituencyVotes object
	 * 
	 * @param votes constituency votes
	 */
	void save(ConstituencyVotes votes);
}
