package com.voting_app.models;

/**
 ********************************************************************
 * Model of a generic Candidate
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class Candidate {
	private int _id;
	private String _name;
	private PoliticalParty _party;
	private Constituency _constituency;
	
	public String getName() {
		return _name;
	}
	
	public void setName(String name) {
		_name = name;
	}
	
	public void setId(int id) {
		_id = id;
	}
	
	public int getId() {
		return _id;
	}
	
	public void setPoliticalParty(PoliticalParty party) {
		_party = party;
	}
	
	public PoliticalParty getPoliticalParty() {
		return _party;
	}
	
	public void setConstituency(Constituency constituency) {
		_constituency = constituency;
	}
	
	public Constituency getConstituency() {
		return _constituency;
	}
	
	@Override
	public String toString() {
		return String.format("Candidate[id=%d, name=%s]", _id, _name);
	}

	@Override
	public boolean equals(Object o) {
		if(o instanceof Candidate) {
			return ((Candidate) o).getId()== this._id;
		} else {
			return false;
		}
	}
}
