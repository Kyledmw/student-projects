package com.appl_maint_mngt.profile.models;

import java.sql.Timestamp;

public interface IUserProfileWritable {

	void setAccountId(Long accountId);
	void setFirstName(String firstName);
	void setSurname(String surname);
	void setDateOfBirth(Timestamp timestamp);
}
