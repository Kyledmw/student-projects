package com.voting_app.services.interfaces;

import com.voting_app.models.Candidate;
import com.voting_app.models.Constituency;
import com.voting_app.models.PoliticalParty;

/**
 ********************************************************************
 * Generic service interface for deleting models and their related models
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface IDeleteService {

	/**
	 * Delete a candidate and its candidate votes
	 * 
	 * @param candidate Candidate to delete
	 */
	void deleteCandidate(Candidate candidate);
	
	/**
	 * Delete a political party and its candidates
	 * 
	 * @param pparty PoliticalParty
	 */
	void deletePoliticalParty(PoliticalParty pparty);
	
	/**
	 * Delete a constituency. its votes and its candidates
	 * 
	 * @param constituency Constituency to delete
	 */
	void deleteConstituency(Constituency constituency);
}
