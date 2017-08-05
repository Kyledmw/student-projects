package com.voting_app.models;

/**
 ********************************************************************
 * Model of a generic Constituency
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class Constituency {

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
	public String toString() {
		return String.format("Constituency[id=%d, name=%s]", _id, _name);
	}
	
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Constituency) {
			return ((Constituency) o).getId()== this._id;
		} else {
			return false;
		}
	}
}
