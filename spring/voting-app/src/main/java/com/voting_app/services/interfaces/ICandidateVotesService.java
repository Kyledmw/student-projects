package com.voting_app.services.interfaces;

import java.util.List;

import com.voting_app.models.Candidate;
import com.voting_app.models.CandidateVote;
import com.voting_app.models.CandidateVotes;
/**
 ********************************************************************
 * Generic service interface for a Candidate Repository
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface ICandidateVotesService {

	/**
	 * Retrieve CandidateVotes for the given candidate
	 * 
	 * @param obj Candidate
	 * @return Votes for the candidate
	 */
	CandidateVotes getVotesForCandidate(Candidate obj);
	
	/**
	 * Retrieve a corresponding list of candidate votes for the givne list of candidates
	 * 
	 * @param list of candidates
	 * @return list of candidate votes
	 */
	List<CandidateVotes> getVotesForCandidates(List<Candidate> list);
	
	/**
	 * Add a candidate vote
	 * 
	 * @param vote CandidateVote
	 */
	void vote(CandidateVote vote);
	
	/**
	 * Add a list of votes
	 * 
	 * @param votes list of CandidateVote objects
	 */
	void addVoteList(List<CandidateVote> votes);
	
}
