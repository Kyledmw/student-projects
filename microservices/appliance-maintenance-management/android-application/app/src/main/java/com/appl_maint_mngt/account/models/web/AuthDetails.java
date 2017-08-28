package com.appl_maint_mngt.account.models.web;

import com.appl_maint_mngt.account.models.constants.UserType;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Kyle on 15/03/2017.
 */
public class AuthDetails implements IAuthDetails {

    private Long id;
    private String email;

    @SerializedName("userType")
    private UserType type;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public UserType getUserType() {
        return type;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserType(UserType type) {
        this.type = type;
    }
}

