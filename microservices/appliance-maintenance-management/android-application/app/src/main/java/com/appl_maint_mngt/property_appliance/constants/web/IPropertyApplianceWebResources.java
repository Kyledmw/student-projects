package com.appl_maint_mngt.property_appliance.constants.web;

import com.appl_maint_mngt.common.constants.ICommonConstants;
import com.appl_maint_mngt.common.constants.web.IWebConstants;

/**
 * Created by Kyle on 17/04/2017.
 */

public interface IPropertyApplianceWebResources {
    String PROPERTY_APPLIANCE_PATH = "/property-appliance";
    String DATA_PATH = "/data";
    String SEARCH_PATH = "/search";

    String BASE_URL = IWebConstants.API_URL + PROPERTY_APPLIANCE_PATH + DATA_PATH;
    String SEARCH_URL = BASE_URL + SEARCH_PATH;

    String GET_RESOURCE = BASE_URL + ICommonConstants.FORWARD_SLASH;
    String FIND_BY_PROPERTY_ID = SEARCH_URL + "/findByPropertyId";
    String FIND_BY_PROPERTY_ID_IN = SEARCH_URL + "/findByPropertyIdIn";
}
