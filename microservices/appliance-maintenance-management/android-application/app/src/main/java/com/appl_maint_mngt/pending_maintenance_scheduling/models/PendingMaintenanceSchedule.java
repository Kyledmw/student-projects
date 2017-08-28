package com.appl_maint_mngt.pending_maintenance_scheduling.models;

import com.appl_maint_mngt.pending_maintenance_scheduling.models.constants.ScheduleStatus;
import com.appl_maint_mngt.pending_maintenance_scheduling.models.constants.SchedulerType;
import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

/**
 * Created by Kyle on 23/03/2017.
 */

public class PendingMaintenanceSchedule extends APendingMaintenanceSchedule {

    private Long id;

    private Long repairReportId;

    private Timestamp startTime;

    private Timestamp endTime;

    @SerializedName("schedulerType")
    private SchedulerType schedulerType;

    @SerializedName("scheduleStatus")
    private ScheduleStatus scheduleStatus;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getRepairReportId() {
        return repairReportId;
    }

    @Override
    public void setRepairReportId(Long repairReportId) {
        this.repairReportId = repairReportId;
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

    @Override
    public ScheduleStatus getScheduleStatus() {
        return scheduleStatus;
    }

    @Override
    public void setScheduleStatus(ScheduleStatus scheduleStatus) {
        this.scheduleStatus = scheduleStatus;
    }
}
