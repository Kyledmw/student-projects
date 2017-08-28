package com.appl_maint_mngt.account.constants.web;

import com.appl_maint_mngt.common.constants.web.IWebConstants;

/**
 * Created by Kyle on 16/04/2017.
 */

public interface IUserAuthWebResources {
    String USER_AUTH_PATH = "/user-authentication";
    String LOGIN_PATH = "/login";
    String DETAILS_PATH = "/details";

    String LOGIN_RESOURCE = IWebConstants.API_URL + USER_AUTH_PATH + LOGIN_PATH;
    String DETAILS_RESOURCE = IWebConstants.API_URL + USER_AUTH_PATH + DETAILS_PATH;
}
