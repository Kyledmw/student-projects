package com.appl_maint_mngt.pending_maintenance_scheduling.controllers;

import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;
import com.appl_maint_mngt.common.integration.IntegrationController;
import com.appl_maint_mngt.pending_maintenance_scheduling.controllers.interfaces.IPendingMaintenanceSchedulingController;
import com.appl_maint_mngt.pending_maintenance_scheduling.forms.interfaces.ISelectedSchedule;
import com.appl_maint_mngt.pending_maintenance_scheduling.models.constants.SchedulerType;
import com.appl_maint_mngt.pending_maintenance_scheduling.models.webs.PendingMaintenanceSchedulePayload;
import com.appl_maint_mngt.pending_maintenance_scheduling.services.interfaces.IPendingMaintenanceSchedulingService;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Kyle on 15/04/2017.
 */

public class PendingMaintenanceSchedulingController implements IPendingMaintenanceSchedulingController {

    private IPendingMaintenanceSchedulingService pendingMaintenanceSchedulingService;

    public PendingMaintenanceSchedulingController() {
        pendingMaintenanceSchedulingService = IntegrationController.getInstance().getServiceFactory().createPendingMaintenanceSchedulingService();
    }

    @Override
    public void addPendingSchedules(Long reportId, SchedulerType type, List<ISelectedSchedule> schedules, IErrorCallback callback) {
        for(ISelectedSchedule sched: schedules) {
            PendingMaintenanceSchedulePayload payload = new PendingMaintenanceSchedulePayload();
            payload.setStartTime(new Timestamp(sched.getStart().getMillis()));
            payload.setEndTime(new Timestamp(sched.getEnd().getMillis()));
            payload.setSchedulerType(type);
            payload.setReportId(reportId);
            pendingMaintenanceSchedulingService.add(payload, callback);
        }
    }

    @Override
    public void getAllPendingScheduledByManager(Long reportId, IErrorCallback errorCallback) {
        pendingMaintenanceSchedulingService.getPendingSchedules(reportId, SchedulerType.PROPERTY_REPRESENTITIVE, errorCallback);
    }

    @Override
    public void getAllPendingScheduledByEngineer(Long reportId, IErrorCallback errorCallback) {
        pendingMaintenanceSchedulingService.getPendingSchedules(reportId, SchedulerType.ENGINEER_REPRESENTITIVE, errorCallback);
    }

    @Override
    public void getAllPendingForReportIds(List<Long> repairReportIds, IErrorCallback errorCallback) {
        if(repairReportIds == null || repairReportIds.isEmpty()) return;
        for(Long id: repairReportIds) {
            pendingMaintenanceSchedulingService.getAllPending(id, errorCallback);
        }
    }

    @Override
    public void acceptPendingSchedule(Long id, IErrorCallback errorCallback) {
        pendingMaintenanceSchedulingService.accept(id, errorCallback);
    }

    @Override
    public void declinePendingSchedule(Long id, IErrorCallback errorCallback) {
        pendingMaintenanceSchedulingService.decline(id, errorCallback);
    }
}
