package com.appl_maint_mngt.property.constants.web;

import com.appl_maint_mngt.common.constants.ICommonConstants;
import com.appl_maint_mngt.common.constants.web.IWebConstants;

/**
 * Created by Kyle on 16/04/2017.
 */

public interface IPropertyWebResources {
    String PROPERTY_PATH = "/property";
    String DATA_PATH = "/data";
    String SEARCH_PATH = "/search";

    String BASE_URL = IWebConstants.API_URL + PROPERTY_PATH + DATA_PATH;
    String GET_RESOURCE = BASE_URL + ICommonConstants.FORWARD_SLASH;
    String FIND_BY_ID_IN_RESOURCE = BASE_URL + SEARCH_PATH + "/findByIdIn";
}
