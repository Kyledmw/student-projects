package com.voting_app.dao.interfaces;

import java.util.List;

import com.voting_app.models.Candidate;
import com.voting_app.models.PoliticalParty;

/**
 ********************************************************************
 * Interface for a DAO of {@link Candidate} objects
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface ICandidateDAO {

	/**
	 * Add a candidate to the implemented storage
	 * 
	 * @param name name of the candidate
	 * @param pparty {@link PoliticalParty} the candidate belongs to
	 * 
	 * @return {@link Candidate} created candidate
	 */
	Candidate addCandidate(String name, PoliticalParty pparty);
	
	/**
	 * Add a candidate to the implemented storage
	 * 
	 * @param id id of the candidate to add
	 * @param name name of the candidate
	 * @param pparty {@link PoliticalParty} the candidate belongs to
	 * 
	 * @return {@link Candidate} created candidate
	 */
	Candidate addCandidate(int id, String name, PoliticalParty pparty);
	
	/**
	 * Retrieve a candidate for the given id
	 * 
	 * @param id id of the wanted candidate
	 * @return {@link Candidate}
	 */
	Candidate getCandidate(int id);
	
	/**
	 * Remove the given candidate from the DAO
	 * 
	 * @param candidate {@link Candidate} to remove
	 */
	void removeCandidate(Candidate candidate);
	
	/**
	 * Remove the given candidate from the DAO
	 * 
	 * @param id of the candidate to remove
	 */
	void removeCandidate(int id);
	
	/**
	 * Retrieve all candidates as a list
	 * 
	 * @return {@link List} of {@link Candidate} objects
	 */
	List<Candidate> getCandidates();
	
	/**
	 * Retrieve all candidates who belong to the given {@link PoliticalParty}
	 * 
	 * @param {@link PoliticalParty} object to remove candidates for
	 * 
	 * @return {@link List} of {@link Candidate} objects who were removed
	 */
	List<Candidate> removeCandidatesInParty(PoliticalParty pparty);
	
}
