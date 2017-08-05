package com.voting_app.models;

/**
 ********************************************************************
 * Model of a generic CandidateVote
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class CandidateVote {

	private int _candidateId;
	
	private int _rank;
	
	public int getCandidateId() {
		return _candidateId;
	}
	
	public void setCandidateId(int id) {
		_candidateId = id;
	}
	
	public int getRank() {
		return _rank;
	}
	
	public void setRank(int rank) {
		_rank = rank;
	}
	
	@Override 
	public String toString() {
		return String.format("CandidateVote[candidate=%d, rank=%d]", _candidateId, _rank);
	}
}
