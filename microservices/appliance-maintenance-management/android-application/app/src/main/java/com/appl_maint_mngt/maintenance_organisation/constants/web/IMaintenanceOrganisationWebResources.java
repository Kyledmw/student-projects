package com.appl_maint_mngt.maintenance_organisation.constants.web;

import com.appl_maint_mngt.common.constants.ICommonConstants;
import com.appl_maint_mngt.common.constants.web.IWebConstants;

/**
 * Created by Kyle on 16/04/2017.
 */

public interface IMaintenanceOrganisationWebResources {
    String MAINTENANCE_ORGANISATION_PATH = "/maintenance-organisation";
    String DATA_PATH = "/data";
    String BASE_URL = IWebConstants.API_URL + MAINTENANCE_ORGANISATION_PATH + DATA_PATH;
    String GET_RESOURCE = BASE_URL + ICommonConstants.FORWARD_SLASH;
}
