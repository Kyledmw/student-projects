package com.voting_app.dao.interfaces;

import com.voting_app.models.Candidate;
import com.voting_app.models.PoliticalParty;
import com.voting_app.models.VotingPoll;

/**
 ********************************************************************
 * Interface for a DAO of {@link VotingPoll} object
 * It also contains get method for other DAO objects
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface IVotePollDAO {
	
	/**
	 * Retrieves the relevant DAO implementation for Political Parties
	 * @return {@link IPoliticalPartyDAO} implementation
	 */
	IPoliticalPartyDAO getPoliticalPartyDAO();
	
	/**
	 * Retrieves the relevant DAO implementation for Candidate Votes
	 * @return {@link ICandidateVotesDAO} implementation
	 */
	ICandidateVotesDAO getCandidateVotesDAO();

	/**
	 * Retrieves the relevant DAO implementation for Candidates
	 * @return {@link ICandidateDAO} implementation
	 */
	ICandidateDAO getCandidateDAO();
	
	/**
	 * Increments the amount of invalid votes
	 */
	void incrementInvalidVotes();
	
	/**
	 * Increments the amount of valid votes
	 */
	void incrementVotes();
	
	/**
	 * Initialise the {@link VotingPoll} object
	 * 
	 * @param validVotes Valid votes to initialise with
	 * @param invalidVotes Invalid votes to initialise with
	 */
	void initVotingPoll(int validVotes, int invalidVotes);
	
	/**
	 * Retrieve the voting poll object for the voting poll
	 * @return {@link VotingPoll}
	 */
	VotingPoll getVotingPoll();
	
	/**
	 * Remove a political party and all related candidates
	 * 
	 * @param pparty {@link PoliticalParty} to remove
	 */
	void removePoliticalParty(PoliticalParty pparty);
	
	/**
	 * Remove a candidate and all related candidate votes
	 * 
	 * @param candidate {@link Candidate} to remove
	 */
	void removeCandidate(Candidate candidate);
}
