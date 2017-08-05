package com.voting_app.repositories.row.models;

/**
 ********************************************************************
 * Model object of a constituency votes table row used by the row mapper
 * 
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class ConstituencyVotesRow {

	private int _id;
	
	private int _constituencyId;
	
	private int _validVotes;
	
	private int _invalidVotes;
	
	public int getId() {
		return _id;
	}
	
	public void setId(int id) {
		_id = id;
	}
	
	public int getConstituencyId() {
		return _constituencyId;
	}
	
	public void setConstituencyId(int constituencyId) {
		_constituencyId = constituencyId;
	}
	
	public int getValidVotes() {
		return _validVotes;
	}
	
	public void setValidVotes(int validVotes) {
		_validVotes = validVotes;
	}
	
	public int getInvalidVotes() {
		return _invalidVotes;
	}
	
	public void setInvalidVotes(int invalidVotes) {
		_invalidVotes = invalidVotes;
	}
}
