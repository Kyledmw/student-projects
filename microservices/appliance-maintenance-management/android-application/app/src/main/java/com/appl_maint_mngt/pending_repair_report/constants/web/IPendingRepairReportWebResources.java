package com.appl_maint_mngt.pending_repair_report.constants.web;

import com.appl_maint_mngt.common.constants.ICommonConstants;
import com.appl_maint_mngt.common.constants.web.IWebConstants;

/**
 * Created by Kyle on 17/04/2017.
 */

public interface IPendingRepairReportWebResources {
    String PENDING_REPAIR_REPORT_PATH = "/pending-repair-report";
    String BASE_URL = IWebConstants.API_URL + PENDING_REPAIR_REPORT_PATH;
    String REPOSITORY_URL = BASE_URL + "/data/search/";
    String ACCEPT_PATH = "/accept";
    String DECLINE_PATH = "/decline";

    String FIND_BY_DIAGNOSTIC_REQUEST_ID_RESOURCE = REPOSITORY_URL + "/findByDiagnosticRequestId";
    String FIND_BY_DIAGNSOTIC_REQUEST_ID_IN_RESOURCE = REPOSITORY_URL + "/findByDiagnosticRequestIdIn";
    String FIND_BY_ENGINEER_ID_RESOURCE = REPOSITORY_URL + "/findByEngineerId";

    String CREATE_RESOURCE = BASE_URL + "/create";
}
