package com.appl_maint_mngt.appliance_status.constants.web;

import com.appl_maint_mngt.common.constants.ICommonConstants;
import com.appl_maint_mngt.common.constants.web.IWebConstants;

/**
 * Created by Kyle on 16/04/2017.
 */

public interface IApplianceStatusWebResources {
    String APPLIANCE_STATUS_PATH = "/appliance-status";
    String DATA_PATH = "/data";
    String GET_RESOURCE = IWebConstants.API_URL + APPLIANCE_STATUS_PATH + DATA_PATH + ICommonConstants.FORWARD_SLASH;
}
