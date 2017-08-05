package com.voting_app.repositories.interfaces;

import com.voting_app.models.Constituency;
import com.voting_app.models.ConstituencyVotes;

/**
 ********************************************************************
 * Interface for a generic Repository object for the ConstituencyVotes model
 * 
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface IConstituencyVotesRepository extends IGenericRepository<ConstituencyVotes> {

	/**
	 * Retrieve ConstituencyVotes object for the given constituency
	 * 
	 * @param constituency Constituency to retrieve votes for
	 * @return ConstituencyVotes
	 */
	ConstituencyVotes getVotesForConstituency(Constituency constituency);
	
	/**
	 * Delete ConstituencyVotes for the given Constituency
	 * 
	 * @param constituency Constituency to delete votes for
	 */
	void deleteVotesForConstituency(Constituency constituency);
}
