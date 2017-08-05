package com.voting_app.util.calculators;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class VoteCalculator implements IVoteCalculator {
	
	private static final int ONE_HUNDRED = 100;

	@Override
	public int getTotalVotes(Map<Integer, Integer> votesByRank, int totalCandidates) {
		int totalVotes = 0;
		if(votesByRank != null && totalCandidates > 0) {
			for(Map.Entry<Integer, Integer> entry: votesByRank.entrySet()) {
				int calc =  totalCandidates - (entry.getKey() - 1);
				totalVotes += entry.getValue() * calc;
			}
		}
		return totalVotes;
	}

	@Override
	public double calculateRankOnePercentage(int candidateRankOneVotes, int validVotes) {
		if(candidateRankOneVotes == 0 && validVotes == 0) {
			return 0;
		}
		double percentage = ((double)candidateRankOneVotes /  validVotes) * ONE_HUNDRED;
		return percentage;
	}

}
