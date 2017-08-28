package com.appl_maint_mngt.pending_maintenance_scheduling.services.interfaces;

import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;
import com.appl_maint_mngt.pending_maintenance_scheduling.models.constants.SchedulerType;
import com.appl_maint_mngt.pending_maintenance_scheduling.models.webs.interfaces.IPendingMaintenanceSchedulePayload;

/**
 * Created by Kyle on 15/04/2017.
 */

public interface IPendingMaintenanceSchedulingService {

    void add(IPendingMaintenanceSchedulePayload payload, IErrorCallback errorCallback);

    void getPendingSchedules(Long reportId, SchedulerType type, IErrorCallback errorCallback);

    void getAllPending(Long reportId, IErrorCallback errorCallback);

    void accept(Long id, IErrorCallback errorCallback);
    void decline(Long id, IErrorCallback errorCallback);
}
