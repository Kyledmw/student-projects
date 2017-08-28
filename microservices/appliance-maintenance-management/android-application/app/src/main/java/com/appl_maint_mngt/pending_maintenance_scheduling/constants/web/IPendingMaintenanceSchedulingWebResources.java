package com.appl_maint_mngt.pending_maintenance_scheduling.constants.web;

import com.appl_maint_mngt.common.constants.ICommonConstants;
import com.appl_maint_mngt.common.constants.web.IWebConstants;

/**
 * Created by Kyle on 17/04/2017.
 */

public interface IPendingMaintenanceSchedulingWebResources {
    String PENDING_MAINTENANCE_SCHEDULING_PATH = "/pending-maintenance-scheduling";
    String BASE_URL = IWebConstants.API_URL + PENDING_MAINTENANCE_SCHEDULING_PATH;
    String REPORT_PATH = "/report/";
    String PENDING_PATH = "/pending";

    String ACCEPT_PATH = "/accept";
    String DECLINE_PATH = "/decline";

    String ADD_RESOURCE = BASE_URL + ICommonConstants.FORWARD_SLASH;
    String GET_REPORT_RESOURCE = BASE_URL + REPORT_PATH;
}
