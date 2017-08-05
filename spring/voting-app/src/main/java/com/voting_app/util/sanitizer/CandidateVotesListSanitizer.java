package com.voting_app.util.sanitizer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.voting_app.models.CandidateVote;

/**
 ********************************************************************
 * @implements ICandidateVoteListSanitizer
 * 
 * Bean for sanitizing CandidateVote lists
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
@Component
public class CandidateVotesListSanitizer implements ICandidateVoteListSanitizer {

	@Override
	public List<CandidateVote> sanitizeList(List<CandidateVote> votes) {
		List<CandidateVote> sanitizedList = new ArrayList<CandidateVote>();
		for(CandidateVote vote: votes) {
			if(vote.getRank() != 0) {
				sanitizedList.add(vote);
			}
		}
		return sanitizedList;
	}

}
