package com.appl_maint_mngt.diagnostic_report.constants.web;

import com.appl_maint_mngt.common.constants.ICommonConstants;
import com.appl_maint_mngt.common.constants.web.IWebConstants;

/**
 * Created by Kyle on 16/04/2017.
 */

public interface IDiagnosticReportWebResources {

    String DIAGNOSTIC_REPORT_PATH = "/diagnostic-report";
    String DATA_PATH = "/data";
    String SEARCH_PATH = "/search";
    String BASE_URL = IWebConstants.API_URL + DIAGNOSTIC_REPORT_PATH + DATA_PATH;

    String POST_RESOURCE = BASE_URL;
    String GET_RESOURCE = BASE_URL + ICommonConstants.FORWARD_SLASH;
    String FIND_BY_PROP_APPL_ID_RESOURCE = BASE_URL  + SEARCH_PATH + "/findByPropApplId";
    String FIND_BY_PROP_APPL_ID_IN_RESOURCE = BASE_URL +  SEARCH_PATH + "/findByPropApplIdIn";
    String FIND_BY_ID_IN_RESOURCE = BASE_URL + SEARCH_PATH + "/findByIdIn";
}
