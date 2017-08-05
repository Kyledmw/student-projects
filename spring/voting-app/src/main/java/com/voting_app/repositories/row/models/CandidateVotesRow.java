package com.voting_app.repositories.row.models;

/**
 ********************************************************************
 * Model object of a candidatevotes table row used by the row mapper
 * 
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class CandidateVotesRow {

	private int _id;
	private int _candidateId;
	private int _voteRank;
	
	public int getId() {
		return _id;
	}
	
	public void setId(int id) {
		_id = id;
	}
	
	public int getCandidateId() {
		return _candidateId;
	}
	
	public void setCandidateId(int candidateId) {
		_candidateId = candidateId;
	}
	
	public int getVoteRank() {
		return _voteRank;
	}
	
	public void setVoteRank(int rank) {
		_voteRank = rank;
	}
}
