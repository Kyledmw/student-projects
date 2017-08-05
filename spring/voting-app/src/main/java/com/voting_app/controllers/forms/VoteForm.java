package com.voting_app.controllers.forms;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.voting_app.models.CandidateVote;
import com.voting_app.validation.CheckVotes;

/**
 ********************************************************************
 * Form object for voting for candidates of a constituency
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class VoteForm {
	
	@Valid
	@CheckVotes(message="{forms.vote.message}")
	private List<CandidateVote> votes;
	
	public VoteForm() {
		votes = new ArrayList<CandidateVote>();
	}
	
	public List<CandidateVote> getVotes() {
		return votes;
	}
	
	public void setVotes(List<CandidateVote> votes) {
		this.votes = votes;
	}
}
