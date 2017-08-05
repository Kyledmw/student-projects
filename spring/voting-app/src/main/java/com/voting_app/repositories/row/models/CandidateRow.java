package com.voting_app.repositories.row.models;

/**
 ********************************************************************
 * Model object of a candidate table row used by the row mapper
 * 
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class CandidateRow {

	private int _id;
	private int _politicalPartyId;
	private int _constituencyId;
	private String _name;
	
	public int getId() {
		return _id;
	}
	
	public void setId(int id) {
		_id = id;
	}
	
	public int getPoliticalPartyId() {
		return _politicalPartyId;
	}
	
	public void setPoliticalPartyId(int politicalPartyId) {
		_politicalPartyId = politicalPartyId;
	}
	
	public int getConstituencyId() {
		return _constituencyId;
	}
	
	public void setConstituencyId(int id) {
		_constituencyId = id;
	}
	
	public String getName() {
		return _name;
	}
	
	public void setName(String name) {
		_name = name;
	}
	
	@Override
	public String toString() {
		return String.format("CandidateRow[id=%d, constituency_id=%d, political_party_id=%d, name=%s]", _id,_constituencyId, _politicalPartyId, _name);
	}
}
