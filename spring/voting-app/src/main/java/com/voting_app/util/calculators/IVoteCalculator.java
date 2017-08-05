package com.voting_app.util.calculators;

import java.util.Map;

/**
 ********************************************************************
 * Calculator used to calculate the rank one vote percentage of a candidate
 * Also contains function that calculates the total votes for a candidate
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface IVoteCalculator {
	
	int getTotalVotes(Map<Integer, Integer> votesByRank, int totalCandidates);
	
	double calculateRankOnePercentage(int candidateRankOneVotes, int validVotes);
}
