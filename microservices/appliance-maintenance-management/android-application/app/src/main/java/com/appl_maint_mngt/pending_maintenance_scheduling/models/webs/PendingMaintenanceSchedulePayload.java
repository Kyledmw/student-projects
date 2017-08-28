package com.appl_maint_mngt.pending_maintenance_scheduling.models.webs;

import com.appl_maint_mngt.pending_maintenance_scheduling.models.constants.SchedulerType;
import com.appl_maint_mngt.pending_maintenance_scheduling.models.webs.interfaces.IPendingMaintenanceSchedulePayload;

import java.sql.Timestamp;

/**
 * Created by Kyle on 15/04/2017.
 */

public class PendingMaintenanceSchedulePayload implements IPendingMaintenanceSchedulePayload {

    private Long reportId;

    private Timestamp startTime;

    private Timestamp endTime;

    private SchedulerType schedulerType;

    @Override
    public Long getReportId() {
        return reportId;
    }

    @Override
    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    @Override
    public Timestamp getStartTime() {
        return startTime;
    }

    @Override
    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    @Override
    public Timestamp getEndTime() {
        return endTime;
    }

    @Override
    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    @Override
    public SchedulerType getSchedulerType() {
        return schedulerType;
    }

    @Override
    public void setSchedulerType(SchedulerType schedulerType) {
        this.schedulerType = schedulerType;
    }
}
