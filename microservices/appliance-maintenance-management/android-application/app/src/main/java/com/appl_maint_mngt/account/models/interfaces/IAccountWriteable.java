package com.appl_maint_mngt.account.models.interfaces;

import com.appl_maint_mngt.account.models.constants.UserType;

import java.sql.Timestamp;

/**
 * Created by Kyle on 15/03/2017.
 */
public interface IAccountWriteable {

    void setId(Long id);
    void setUserType(UserType type);
    void setEmail(String email);
    void setFirstName(String firstName);
    void setSurname(String surname);
    void setToken(String token);
    void setDateOfBirth(Timestamp timestamp);
}
