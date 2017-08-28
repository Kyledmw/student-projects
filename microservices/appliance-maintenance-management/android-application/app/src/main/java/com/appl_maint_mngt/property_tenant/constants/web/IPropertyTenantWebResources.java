package com.appl_maint_mngt.property_tenant.constants.web;

import com.appl_maint_mngt.common.constants.ICommonConstants;
import com.appl_maint_mngt.common.constants.web.IWebConstants;

/**
 * Created by Kyle on 16/04/2017.
 */

public interface IPropertyTenantWebResources {
    String PROPERTY_TENANT_PATH = "/property-tenant";
    String DATA_PATH = "/data";
    String BASE_URL = IWebConstants.API_URL + PROPERTY_TENANT_PATH + DATA_PATH;
    String GET_RESOURCE = BASE_URL + ICommonConstants.FORWARD_SLASH;
}
