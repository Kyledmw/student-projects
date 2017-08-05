package com.voting_app.models;

import java.util.HashMap;
import java.util.Map;

/**
 ********************************************************************
 * Model of a generic CandidateVotes
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class CandidateVotes {

	private Candidate _candidate;
	private Map<Integer, Integer> _votesByRank;
	
	public CandidateVotes() {
		_votesByRank = new HashMap<Integer, Integer>();
	}
	
	public Candidate getCandidate() {
		return _candidate;
	}
	
	public void setCandidate(Candidate candidate) {
		_candidate = candidate;
	}
	
	public Map<Integer, Integer> getVotesByRank() {
		return _votesByRank;
	}
	
	public void setVotesByRank(Map<Integer, Integer> votesByRank) {
		_votesByRank = votesByRank;
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
