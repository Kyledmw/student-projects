package com.voting_app.models;

/**
 ********************************************************************
 * Model of a generic ConstituencyVotes
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class ConstituencyVotes {

	private int _id;
	private Constituency _constituency;
	private int _validVotes;
	private int _invalidVotes;
	
	public ConstituencyVotes() {
		_validVotes = 0;
		_invalidVotes = 0;
	}
	
	public void setId(int id) {
		_id = id;
	}
	
	public int getId() {
		return _id;
	}
	
	public void setConstituency(Constituency constituency) {
		_constituency = constituency;
	}
	
	public Constituency getConstituency() {
		return _constituency;
	}
	
	public void setValidVotes(int validVotes) {
		_validVotes = validVotes;
	}
	
	public int getValidVotes() {
		return _validVotes;
	}
	
	public void setInvalidVotes(int invalidVotes) {
		_invalidVotes = invalidVotes;
	}
	
	public int getInvalidVotes() {
		return _invalidVotes; 
	}
	
	public void incrementValidVotes() {
		_validVotes++;
	}
	
	public void incrementInvalidVotes() {
		_invalidVotes++;
	}

	@Override
	public boolean equals(Object o) {
		if(o instanceof ConstituencyVotes) {
			return ((ConstituencyVotes) o).getId()== this._id;
		} else {
			return false;
		}
	}
	
}
