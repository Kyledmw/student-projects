package com.appl_maint_mngt.profile.models.web;

import java.sql.Timestamp;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.appl_maint_mngt.profile.validation.DateOfBirth;

public class UserProfilePayload implements IUserProfilePayload {

	@Valid
	@NotNull
	private Long accountId;
	
	@Valid
	@NotNull(message = "{user_profile.firstName.null}")
	@Size(min=3, max=15, message="user_profile.firstName.size")
	private String firstName;

	@Valid
	@NotNull(message = "{user_profile.surname.null}")
	@Size(min=3, max=15, message="user_profile.surname.size")
	private String surname;

	@Valid
	@NotNull(message = "{user_profile.dateOfBirth.null}")
	@DateOfBirth
	private Timestamp dateOfBirth;
	
	@Override
	public Long getAccountId() {
		return accountId;
	}

	@Override
	public String getFirstName() {
		return firstName;
	}

	@Override
	public String getSurname() {
		return surname;
	}

	@Override
	public Timestamp getDateOfBirth() {
		return dateOfBirth;
	}

	@Override
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Override
	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Override
	public void setDateOfBirth(Timestamp timestamp) {
		this.dateOfBirth = timestamp;
	}

}
