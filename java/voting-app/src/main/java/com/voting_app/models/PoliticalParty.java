package com.voting_app.models;

/**
 ********************************************************************
 * Model of a generic Political Party
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class PoliticalParty {

	private int _id;
	private String _name;
	
	public PoliticalParty(int id, String name) {
		_id = id;
		_name = name;
	}
	
	public int getId() {
		return _id;
	}
	
	public String getName() {
		return _name;
	}
}
