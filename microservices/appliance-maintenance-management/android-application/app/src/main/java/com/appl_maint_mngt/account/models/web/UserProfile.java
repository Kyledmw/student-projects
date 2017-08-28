package com.appl_maint_mngt.account.models.web;

import java.sql.Timestamp;

/**
 * Created by Kyle on 17/03/2017.
 */

public class UserProfile implements IUserProfileReadable {

    private Long accountId;
    private String firstName;
    private String surname;
    private Timestamp dateOfBirth;

    @Override
    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public Timestamp getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Timestamp dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
