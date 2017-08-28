package com.appl_maint_mngt.pending_maintenance_scheduling.models.webs.interfaces;

import com.appl_maint_mngt.pending_maintenance_scheduling.models.constants.SchedulerType;

import java.sql.Timestamp;

/**
 * Created by Kyle on 15/04/2017.
 */

public interface IPendingMaintenanceSchedulePayload {

    Long getReportId();

    Timestamp getStartTime();

    Timestamp getEndTime();

    SchedulerType getSchedulerType();

    void setReportId(Long id);

    void setStartTime(Timestamp time);

    void setEndTime(Timestamp time);

    void setSchedulerType(SchedulerType type);
}
