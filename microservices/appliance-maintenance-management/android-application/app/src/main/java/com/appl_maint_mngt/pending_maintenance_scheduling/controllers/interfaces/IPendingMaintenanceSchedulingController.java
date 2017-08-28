package com.appl_maint_mngt.pending_maintenance_scheduling.controllers.interfaces;

import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;
import com.appl_maint_mngt.pending_maintenance_scheduling.forms.interfaces.ISelectedSchedule;
import com.appl_maint_mngt.pending_maintenance_scheduling.models.constants.SchedulerType;

import java.util.List;

/**
 * Created by Kyle on 15/04/2017.
 */

public interface IPendingMaintenanceSchedulingController {

    void addPendingSchedules(Long reportId, SchedulerType type, List<ISelectedSchedule> schedules, IErrorCallback callback);

    void getAllPendingScheduledByManager(Long reportId, IErrorCallback errorCallback);

    void getAllPendingScheduledByEngineer(Long reportId, IErrorCallback errorCallback);

    void getAllPendingForReportIds(List<Long> repairReportIds, IErrorCallback errorCallback);

    void acceptPendingSchedule(Long id, IErrorCallback errorCallback);

    void declinePendingSchedule(Long id, IErrorCallback errorCallback);
}
