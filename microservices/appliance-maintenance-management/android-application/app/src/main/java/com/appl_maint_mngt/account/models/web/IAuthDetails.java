package com.appl_maint_mngt.account.models.web;

import com.appl_maint_mngt.account.models.constants.UserType;

/**
 * Created by Kyle on 15/03/2017.
 */
public interface IAuthDetails {
    Long getId();
    String getEmail();
    UserType getUserType();
}
