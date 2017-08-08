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
	
	public Candidate(int id, String name, PoliticalParty party) {
		_id = id;
		_name = name;
		_party = party;
	}
	
	public int getId() {
		return _id;
	}
	
	public String getName() {
		return _name;
	}
	
	public PoliticalParty getPoliticalParty() {
		return _party;
	}
	
}
