package com.voting_app.controllers.forms;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 ********************************************************************
 * Form object for creating a candidate object
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class CandidateForm {

	@Min(value=1)
	private int _constituencyId;
	
	@Min(value=1)
	private int _politicalPartyId;

	@Valid
	@NotNull(message="{forms.admin.name_not_empty}")
	@Size(min = 3, max =30, message="{forms.admin.name_size}")
	private String name;
	
	public int getConstituencyId() {
		return _constituencyId;
	}
	
	public void setConstituencyId(int constituencyId) {
		_constituencyId = constituencyId;
	}
	
	public int getPoliticalPartyId() {
		return _politicalPartyId;
	}
	
	public void setPoliticalPartyId(int politicalPartyId) {
		_politicalPartyId = politicalPartyId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
