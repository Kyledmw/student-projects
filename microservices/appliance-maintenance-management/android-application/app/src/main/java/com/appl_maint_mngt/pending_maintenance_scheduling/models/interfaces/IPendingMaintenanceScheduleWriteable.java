package com.appl_maint_mngt.pending_maintenance_scheduling.models.interfaces;

import com.appl_maint_mngt.pending_maintenance_scheduling.models.constants.ScheduleStatus;
import com.appl_maint_mngt.pending_maintenance_scheduling.models.constants.SchedulerType;

import java.sql.Timestamp;

/**
 * Created by Kyle on 23/03/2017.
 */

public interface IPendingMaintenanceScheduleWriteable {

    void setRepairReportId(Long repairReportId);

    void setStartTime(Timestamp startTime);

    void setId(Long id);

    void setEndTime(Timestamp endTime);

    void setSchedulerType(SchedulerType schedulerType);

    void setScheduleStatus(ScheduleStatus scheduleStatus);
}
