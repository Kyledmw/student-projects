package com.appl_maint_mngt.account.views.interfaces;

import com.appl_maint_mngt.account.models.constants.UserType;

/**
 * Created by Kyle on 07/04/2017.
 */

public interface IDashboardForUserTypeRetriever {

    Class get(UserType type);
}
