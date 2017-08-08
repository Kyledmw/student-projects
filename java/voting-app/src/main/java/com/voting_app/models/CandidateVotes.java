package com.voting_app.models;

import java.util.HashMap;
import java.util.Map;

/**
 ********************************************************************
 * Model of a candidate and their votes by rank
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class CandidateVotes {

	private Map<Integer, Integer> _votesByRank;
	private Candidate _candidate;
	
	public CandidateVotes(Candidate candidate) {
		this(candidate, new HashMap<Integer, Integer>());
	}
	
	public CandidateVotes(Candidate candidate, Map<Integer, Integer> votesByRank) {
		_votesByRank = votesByRank;
		_candidate = candidate;
	}
	
	public Candidate getCandidate() {
		return _candidate;
	}
	
	public Map<Integer, Integer> getVotesByRank() {
		return _votesByRank;
	}
	
	public void addVote(int rank) {
		Integer votes = _votesByRank.get(rank);
		if(votes == null) {
			_votesByRank.put(rank, 1);
		} else {
			votes++;
			_votesByRank.put(rank, votes);
		}
	}
	
	/**
	 * Gets the rank 1 votes, returns 0 if none found
	 * 
	 * @return get the amount of rank 1 votes
	 */
	public int getRankOneVotes() {
		return (_votesByRank.get(1) != null) ? _votesByRank.get(1) : 0;
	}
}
