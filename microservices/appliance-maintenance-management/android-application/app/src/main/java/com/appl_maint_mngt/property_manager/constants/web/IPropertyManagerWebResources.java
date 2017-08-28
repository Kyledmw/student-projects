package com.appl_maint_mngt.property_manager.constants.web;

import com.appl_maint_mngt.common.constants.ICommonConstants;
import com.appl_maint_mngt.common.constants.web.IWebConstants;

/**
 * Created by Kyle on 16/04/2017.
 */

public interface IPropertyManagerWebResources {
    String PROPERTY_MANAGER_PATH = "/property-manager";
    String DATA_PATH = "/data";
    String BASE_URL = IWebConstants.API_URL + PROPERTY_MANAGER_PATH + DATA_PATH;

    String GET_RESOURCE = BASE_URL + ICommonConstants.FORWARD_SLASH;
}
