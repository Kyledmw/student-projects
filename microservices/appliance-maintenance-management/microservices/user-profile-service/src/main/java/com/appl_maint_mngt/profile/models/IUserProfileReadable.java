package com.appl_maint_mngt.profile.models;

import java.sql.Timestamp;

public interface IUserProfileReadable {

	Long getAccountId();
	String getFirstName();
	String getSurname();
	Timestamp getDateOfBirth();
}
