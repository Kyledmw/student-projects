package com.appl_maint_mngt.repair_report.constants.web;

import com.appl_maint_mngt.common.constants.web.IWebConstants;

/**
 * Created by Kyle on 17/04/2017.
 */

public interface IRepairReportWebResources {
    String REPAIR_REPORT_PATH = "/repair-report";
    String DATA_PATH = "/data";
    String SEARCH_PATH = "/search";
    String BASE_URL = IWebConstants.API_URL + REPAIR_REPORT_PATH + DATA_PATH;
    String BASE_SEARCH_URL = BASE_URL + SEARCH_PATH;
    String FIND_BY_DIAGNOSTIC_REPORT_ID = BASE_SEARCH_URL + "/findByDiagnosticReportId";
    String FIND_BY_DIAGNOSTIC_REPORT_ID_IN = BASE_SEARCH_URL + "/findByDiagnosticReportIdIn";
    String FIND_BY_ENGINEER_ID = BASE_SEARCH_URL + "/findByEngineerId";
}
