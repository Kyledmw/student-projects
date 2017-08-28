package com.appl_maint_mngt.maintenance_engineer.constants.web;

import com.appl_maint_mngt.common.constants.ICommonConstants;
import com.appl_maint_mngt.common.constants.web.IWebConstants;

/**
 * Created by Kyle on 16/04/2017.
 */

public interface IMaintenanceEngineerWebResources {
    String MAINTENANCE_ENGINEER_PATH = "/maintenance-engineer";
    String DATA_PATH = "/data";

    String BASE_URL = IWebConstants.API_URL + MAINTENANCE_ENGINEER_PATH + DATA_PATH;
    String GET_RESOURCE = BASE_URL + ICommonConstants.FORWARD_SLASH;
}
