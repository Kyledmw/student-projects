package com.appl_maint_mngt.diagnostic_request.constants.web;

import com.appl_maint_mngt.common.constants.ICommonConstants;
import com.appl_maint_mngt.common.constants.web.IWebConstants;

/**
 * Created by Kyle on 16/04/2017.
 */

public interface IDiagnosticRequestWebResources {

    String DIAGNOSTIC_REQUEST_PATH = "/diagnostic-request";
    String DATA_PATH = "/data";
    String SEARCH_PATH= "/search";

    String BASE_URL = IWebConstants.API_URL + DIAGNOSTIC_REQUEST_PATH + DATA_PATH;

    String POST_RESOURCE = BASE_URL;
    String PUT_RESOURCE = BASE_URL + ICommonConstants.FORWARD_SLASH;

    String FIND_BY_DIAGNOSTIC_REPORT_ID = BASE_URL + SEARCH_PATH + "/findByDiagnosticReportId";
    String FIND_BY_DIAGNOSTIC_REPORT_ID_IN  = BASE_URL + SEARCH_PATH + "/findByDiagnosticReportIdIn";
    String FIND_BY_MAINT_ORG_ID = BASE_URL + SEARCH_PATH + "/findByMaintenanceOrganisationId";
}
