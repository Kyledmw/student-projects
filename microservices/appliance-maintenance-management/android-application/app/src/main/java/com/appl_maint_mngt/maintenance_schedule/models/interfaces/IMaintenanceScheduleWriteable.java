package com.appl_maint_mngt.maintenance_schedule.models.interfaces;

import com.appl_maint_mngt.maintenance_schedule.models.constants.ScheduleStatus;

import java.sql.Timestamp;

/**
 * Created by Kyle on 23/03/2017.
 */

public interface IMaintenanceScheduleWriteable {
    void setId(Long id);

    void setRepairReportId(Long repairReportId);

    void setStartTime(Timestamp startTime);

    void setEndTime(Timestamp endTime);

    void setScheduleStatus(ScheduleStatus scheduleStatus);
}
