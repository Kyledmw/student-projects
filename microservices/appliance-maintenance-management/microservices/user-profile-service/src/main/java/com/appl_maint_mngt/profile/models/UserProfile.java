package com.appl_maint_mngt.profile.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_profiles")
public class UserProfile extends AUserProfile {

	@Id
	@Column(name="account_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long accountId;
	
	@Column(name="first_name") 
	private String firstName;
	
	@Column(name="surname") 
	private String surname;

	@Column(name="date_of_birth") 
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

	@Override
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

}
