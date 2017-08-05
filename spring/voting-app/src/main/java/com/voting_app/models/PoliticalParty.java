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
	
	public void setId(int id) {
		_id = id;
	}
	
	public int getId() {
		return _id;
	}
	
	public void setName(String name) {
		_name = name;
	}
	
	public String getName() {
		return _name;
	}

	@Override
	public boolean equals(Object o) {
		if(o instanceof PoliticalParty) {
			return ((PoliticalParty) o).getId()== this._id;
		} else {
			return false;
		}
	}
}
