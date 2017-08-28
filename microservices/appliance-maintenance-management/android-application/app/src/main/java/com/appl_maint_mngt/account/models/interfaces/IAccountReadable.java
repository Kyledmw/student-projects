package com.appl_maint_mngt.account.models.interfaces;

import com.appl_maint_mngt.account.models.constants.UserType;

import java.sql.Timestamp;

/**
 * Created by Kyle on 15/03/2017.
 */
public interface IAccountReadable {

    Long getId();
    UserType getUserType();
    String getEmail();
    String getFirstName();
    String getSurname();
    String getToken();
    Timestamp getDateOfBirth();
}
