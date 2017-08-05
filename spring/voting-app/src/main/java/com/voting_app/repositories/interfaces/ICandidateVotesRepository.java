package com.voting_app.repositories.interfaces;

import com.voting_app.models.Candidate;
import com.voting_app.models.CandidateVote;
import com.voting_app.models.CandidateVotes;

/**
 ********************************************************************
 * Interface for a generic Repository object for the CandidateVotes model
 * 
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface ICandidateVotesRepository {
	
	/**
	 * Add a CandidateVote to the repository
	 * 
	 * @param vote CandidateVote object
	 */
	void addVoteForCandidate(CandidateVote vote);
	
	/**
	 * Deletes all candidate votes for the given candidate
	 * 
	 * @param candidate
	 */
	void deleteVotesForCandidate(Candidate candidate);
	
	/**
	 * Retrieves CandidateVotes object for the given candidate object
	 * 
	 * @param candidate Candidate to find votes on
	 * 
	 * @return CandidateVotes for the given candidate
	 */
	CandidateVotes getVotesForCandidate(Candidate candidate);

}
