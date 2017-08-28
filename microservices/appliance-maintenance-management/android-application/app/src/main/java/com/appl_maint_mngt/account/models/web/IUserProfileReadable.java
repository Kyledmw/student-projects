package com.appl_maint_mngt.account.models.web;

import java.sql.Timestamp;

public interface IUserProfileReadable {

	Long getAccountId();

    String getFirstName();
	String getSurname();
	Timestamp getDateOfBirth();
}
