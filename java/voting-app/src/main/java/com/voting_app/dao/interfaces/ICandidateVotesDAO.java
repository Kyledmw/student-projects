package com.voting_app.dao.interfaces;

import java.util.List;
import java.util.Map;

import com.voting_app.models.Candidate;
import com.voting_app.models.CandidateVotes;

/**
 ********************************************************************
 * Interface for a DAO of {@link CandidateVotes} objects
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface ICandidateVotesDAO {

	/**
	 * Add a users vote to the DAO
	 * 
	 * The integer represents the vote rank
	 * 
	 * @param candidateVotes {@link Map} of {@link Candidate} keys {@link Integer} values
	 */
	void addVote(Map<Candidate, Integer> candidateVotes);
	
	/**
	 * Retrieve list of candidate votes objects in the DAO
	 * 
	 * @return {@link List} of {@link CandidateVotes} objects
	 */
	List<CandidateVotes> getCandidateVotes();
	
	/**
	 * Add a candidate and all the candidates votes by rank
	 * 
	 * @param candidate {@link Candidate} 
	 * @param votesByRank {@link Map} of {@link Integer} keys (Vote rank) and {@link Integer} values (amount of votes for that rank)
	 */
	void addCandidateVotes(Candidate candidate, Map<Integer, Integer> votesByRank);

	/**
	 * Removes votes for the given candidate
	 * 
	 * @param candidate {@link Candidate}
	 */
	void removeCandidateVotesForCandidate(Candidate candidate);
	
	/**
	 * Remove all vote objects for the given candidate list
	 * 
	 * @param candidates {@link List} of {@link Candidate}
	 */
	void removeCandidateVotesForCandidates(List<Candidate> candidates);
}
