package com.voting_app.util.comparator;

import java.util.Comparator;

import com.voting_app.models.CandidateVotes;
import com.voting_app.util.calculators.VoteCalculatorBean;

/**
 ********************************************************************
 * {link Comparator} implementation for {@link CandidateVotes}
 * 
 * This compares the candidates in order of highest vote points
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class CandidateVotesComparator implements Comparator<CandidateVotes> {
	
	private int _totalCandidates;
	
	public CandidateVotesComparator(int totalCandidates) {
		_totalCandidates = totalCandidates;
	}

	public int compare(CandidateVotes o1, CandidateVotes o2) {
		//Uses calculator bean to calculate the votes of each 
		VoteCalculatorBean voteCalc = new VoteCalculatorBean();
		voteCalc.setTotalCandidates(_totalCandidates);
		voteCalc.setVotesByRank(o1.getVotesByRank());
		voteCalc.calculateVotes();
		int results1 = voteCalc.getTotalVotes();
		voteCalc.setVotesByRank(o2.getVotesByRank());
		voteCalc.calculateVotes();
		int results2 = voteCalc.getTotalVotes();
		
		//Compares the votes in order to have highest votes first
		return results2 - results1;
	}

}
