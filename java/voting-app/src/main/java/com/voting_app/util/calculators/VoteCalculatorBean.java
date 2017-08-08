package com.voting_app.util.calculators;

import java.io.Serializable;
import java.util.Map;

/**
 ********************************************************************
 * Java Bean which calculates the total votes for a given Map of votes
 * <br>
 * <br>
 * @implements {@link Serializable}
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class VoteCalculatorBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int _totalVotes = 0;
	private int _totalCandidates = 0;
	private Map<Integer, Integer> _votesByRank = null;
	
	/**
	 * Get the total votes calculated
	 * @return
	 */
	public int getTotalVotes() {
		return _totalVotes;
	}
	
	/**
	 * Set the totalVotes value
	 * @param totalVotes
	 */
	public void setTotalVotes(int totalVotes) {
		_totalVotes = totalVotes;
	}
	
	/**
	 * Get the total candidates used for the calculation
	 * @return int
	 */
	public int getTotalCandidates() {
		return getTotalCandidates();
	}
	
	/**
	 * Set the total candidates to be used for the calculation
	 * @param totalCandidates int
	 */
	public void setTotalCandidates(int totalCandidates) {
		_totalCandidates = totalCandidates;
	}
	
	/**
	 * Get a {@link Map} object of key {@link Integer} of vote ranks and value {@link Integer} amount of votes
	 * @return votes by rank 
	 */
	public Map<Integer, Integer> getVotesByRank() {
		return _votesByRank;
	}
	
	/**
	 * Get a {@link Map} object of key {@link Integer} of vote ranks and value {@link Integer} amount of votes
	 * 
	 * @param votesByRank votes by rank
	 */
	public void setVotesByRank(Map<Integer, Integer> votesByRank) {
		_votesByRank = votesByRank;
	}
	
	/**
	 * Calculate the total votes off the votesByRank Map and totalCandidates value
	 */
	public void calculateVotes() {
		_totalVotes = 0;
		if(_votesByRank != null && _totalCandidates > 0) {
			for(Map.Entry<Integer, Integer> entry: _votesByRank.entrySet()) {
				int calc =  _totalCandidates - (entry.getKey() - 1);
				_totalVotes += entry.getValue() * calc;
			}
		}
	}
}
