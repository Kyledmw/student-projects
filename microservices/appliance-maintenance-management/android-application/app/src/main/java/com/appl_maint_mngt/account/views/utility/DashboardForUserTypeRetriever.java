package com.appl_maint_mngt.account.views.utility;

import com.appl_maint_mngt.account.models.constants.UserType;
import com.appl_maint_mngt.account.views.interfaces.IDashboardForUserTypeRetriever;
import com.appl_maint_mngt.maintenance_engineer.views.MaintenanceEngineerDashboardActivity;
import com.appl_maint_mngt.property_manager.views.PropertyManagerDashboardActivity;
import com.appl_maint_mngt.property_tenant.views.PropertyTenantDashboardActivity;

/**
 * Created by Kyle on 15/03/2017.
 */
public class DashboardForUserTypeRetriever implements IDashboardForUserTypeRetriever {

    @Override
    public Class get(UserType type) {
        switch(type) {
            case MAINTENANCE_ENGINEER:
                return MaintenanceEngineerDashboardActivity.class;
            case PROPERTY_MANAGER:
                return PropertyManagerDashboardActivity.class;
            case PROPERTY_TENANT:
                return PropertyTenantDashboardActivity.class;
            default:
                return null;
        }
    }
}
