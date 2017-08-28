package com.appl_maint_mngt.account.models;

import com.appl_maint_mngt.account.models.constants.UserType;

import java.sql.Timestamp;

/**
 * Created by Kyle on 15/03/2017.
 */
public class Account extends AAccount {

    private Long id;
    private UserType userType;

    private String email;
    private String firstName;
    private String surname;
    private String token;
    private Timestamp dateOfBirth;

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getSurname() {
        return surname;
    }

    @Override
    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String getToken() {
        return token;
    }

    @Override
    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public UserType getUserType() {
        return userType;
    }

    @Override
    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @Override
    public Timestamp getDateOfBirth() {
        return dateOfBirth;
    }

    @Override
    public void setDateOfBirth(Timestamp dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
