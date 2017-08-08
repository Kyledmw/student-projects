package com.voting_app.models;

/**
 ********************************************************************
 * Model of a generic voting poll
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class VotingPoll {

	private int _validVotes;
	private int _invalidVotes;
	
	public VotingPoll() {
		_validVotes = 0;
		_invalidVotes = 0;
	}
	
	public int getValidVotes() {
		return _validVotes;
	}
	
	public int getInvalidVotes() {
		return _invalidVotes;
	}
	
	public void setValidVotes(int validVotes) {
		_validVotes = validVotes;
	}
	
	public void setInvalidVotes(int invalidVotes) {
		_invalidVotes = invalidVotes;
	}
	
	public void incrementValidVotes() {
		_validVotes++;
	}
	
	public void incrementInvalidVotes() {
		_invalidVotes++;
	}
}
