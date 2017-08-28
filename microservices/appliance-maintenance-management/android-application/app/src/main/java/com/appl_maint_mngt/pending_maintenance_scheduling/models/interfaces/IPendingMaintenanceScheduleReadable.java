package com.appl_maint_mngt.pending_maintenance_scheduling.models.interfaces;

import com.appl_maint_mngt.pending_maintenance_scheduling.models.constants.ScheduleStatus;
import com.appl_maint_mngt.pending_maintenance_scheduling.models.constants.SchedulerType;

import java.sql.Timestamp;

/**
 * Created by Kyle on 23/03/2017.
 */

public interface IPendingMaintenanceScheduleReadable {

    Long getId();

    Long getRepairReportId();

    Timestamp getStartTime();
    Timestamp getEndTime();

    SchedulerType getSchedulerType();
    ScheduleStatus getScheduleStatus();
}
