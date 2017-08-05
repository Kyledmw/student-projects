package com.voting_app.validation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.voting_app.models.CandidateVote;

/**
 ********************************************************************
 * Custom hibernate validator that checks if a List<CandidateVote> is valid i.e sequential.
 * 
 * In doing so it must check if the list is empty &/ null
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class CheckVotesValidator implements ConstraintValidator<CheckVotes, List<CandidateVote>> {

	@Override
	public void initialize(CheckVotes cv) {
	}

	@Override
	public boolean isValid(List<CandidateVote> votes, ConstraintValidatorContext context) {
		boolean valid = true;
		
		if(votes == null) {
			return false;
		}
		
		List<Integer> voteRanks = new ArrayList<Integer>();
		
		for(CandidateVote vote: votes) {
			if(vote.getRank() != 0) {
				voteRanks.add(vote.getRank());
			}
		}
		
		valid = !voteRanks.isEmpty();
		Collections.sort(voteRanks);
		
		
		for(int i = 0; i< voteRanks.size(); i++) {
			int rank = i + 1;
			if(rank != voteRanks.get(i)) {
				valid = false;
				break;
			}
		}
		
		return valid;
	}

}
