package com.appl_maint_mngt.maintenance_schedule.controllers.interfaces;

import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;

import java.util.List;

/**
 * Created by Kyle on 14/04/2017.
 */

public interface IMaintenanceScheduleController {
    void getForRepairReport(Long repairReportId, IErrorCallback errorCallback);
    void getForRepairReports(List<Long> repairReportIds, IErrorCallback errorCallback);
}
