package com.appl_maint_mngt.profile.models.web;

import java.sql.Timestamp;

public interface IUserProfilePayload {

	Long getAccountId();
	String getFirstName();
	String getSurname();
	Timestamp getDateOfBirth();
	
	void setAccountId(Long accountId);
	void setFirstName(String firstName);
	void setSurname(String surname);
	void setDateOfBirth(Timestamp timestamp);
}
