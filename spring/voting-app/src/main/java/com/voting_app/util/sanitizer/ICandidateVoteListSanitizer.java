package com.voting_app.util.sanitizer;

import java.util.List;

import com.voting_app.models.CandidateVote;

/**
 ********************************************************************
 * Interface for a List<CandidateVote> sanitizer
 * 
 * The purpose of this is to remove any candidatevote objects which are empty have a rank of 0
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface ICandidateVoteListSanitizer {

	/**
	 * Sanitize given List<CandidateVote> by removing objects which are empty/ have a rank of 0
	 * 
	 * @param votes List<CandidateVote> 
	 * @return sanitized List<CandidateVote> 
	 */
	List<CandidateVote> sanitizeList(List<CandidateVote> votes);
}
