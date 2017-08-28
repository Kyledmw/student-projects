package com.appl_maint_mngt.maintenance_schedule.controllers;

import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;
import com.appl_maint_mngt.common.integration.IntegrationController;
import com.appl_maint_mngt.maintenance_schedule.controllers.interfaces.IMaintenanceScheduleController;
import com.appl_maint_mngt.maintenance_schedule.services.interfaces.IMaintenanceScheduleService;

import java.util.List;

/**
 * Created by Kyle on 14/04/2017.
 */

public class MaintenanceScheduleController implements IMaintenanceScheduleController {

    private IMaintenanceScheduleService maintenanceScheduleService;

    public MaintenanceScheduleController() {
        maintenanceScheduleService = IntegrationController.getInstance().getServiceFactory().createMaintenanceScheduleService();
    }

    @Override
    public void getForRepairReport(Long repairReportId, IErrorCallback errorCallback) {
        maintenanceScheduleService.findByRepairReportId(repairReportId, errorCallback);
    }

    @Override
    public void getForRepairReports(List<Long> repairReportIds, IErrorCallback errorCallback) {
        if(repairReportIds == null || repairReportIds.isEmpty()) return;
        maintenanceScheduleService.findByRepairReportIdIn(repairReportIds, errorCallback);
    }
}
