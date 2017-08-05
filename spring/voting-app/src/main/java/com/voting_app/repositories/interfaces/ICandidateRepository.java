package com.voting_app.repositories.interfaces;

import java.util.List;

import com.voting_app.models.Candidate;
import com.voting_app.models.Constituency;
import com.voting_app.models.PoliticalParty;

/**
 ********************************************************************
 * Interface for a generic Repository object for the Candidate model
 * 
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface ICandidateRepository extends IGenericRepository<Candidate> {
	
	/**
	 * Retrieve all candidates for the given constituency object
	 * 
	 * @param constituency Constituency to find candidates of
	 * @return List<Candidate> for the given constituency
	 */
	List<Candidate> getCandidatesForConstituency(Constituency constituency);

	/**
	 * Retrieve all candidates for the given poltical party object
	 * 
	 * @param constituency PoliticalParty to find candidates of
	 * @return List<Candidate> for the given political party
	 */
	List<Candidate> getCandidatesForPoliticalParty(PoliticalParty pparty);

	/**
	 * Delete all candidates for the given Constituency object
	 * 
	 * @param constituency Constituency to delete candidates for
	 */
	void deleteCandidatesForConstituency(Constituency constituency);
	
	/**
	 * Delete all candidates for the given political party object
	 * 
	 * @param pparty PoliticalParty to delete candidates for
	 */
	void deleteCandidatesForPoliticalParty(PoliticalParty pparty);
}
