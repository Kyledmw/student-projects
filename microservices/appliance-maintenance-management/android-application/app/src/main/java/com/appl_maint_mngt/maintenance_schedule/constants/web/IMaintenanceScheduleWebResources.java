package com.appl_maint_mngt.maintenance_schedule.constants.web;

import com.appl_maint_mngt.common.constants.web.IWebConstants;

/**
 * Created by Kyle on 16/04/2017.
 */

public interface IMaintenanceScheduleWebResources {
    String MAINTENANCE_SCHEDULE_PATH = "/maintenance-schedule";
    String DATA_PATH = "/data";
    String SEARCH_PATH = "/search";

    String SEARCH_URL = IWebConstants.API_URL + MAINTENANCE_SCHEDULE_PATH + DATA_PATH + SEARCH_PATH;
    String FIND_BY_REPAIR_REPORT_ID = SEARCH_URL + "/findByRepairReportId";
    String FIND_BY_REPAIR_REPORT_ID_IN = SEARCH_URL + "/findByRepairReportIdIn";
}
