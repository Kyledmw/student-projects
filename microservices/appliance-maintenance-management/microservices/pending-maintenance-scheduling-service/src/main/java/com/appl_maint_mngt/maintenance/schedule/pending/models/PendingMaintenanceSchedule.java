package com.appl_maint_mngt.maintenance.schedule.pending.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.appl_maint_mngt.maintenance.schedule.pending.models.constants.ScheduleStatus;
import com.appl_maint_mngt.maintenance.schedule.pending.models.constants.SchedulerType;

@Entity
@Table(name="pending_maintenance_schedules")
public class PendingMaintenanceSchedule {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long id;

	@Column(name="repair_report_id")
	private Long repairReportId;
	
	@Column(name="start_time")
	private Timestamp startTime;

	@Column(name="end_time")
	private Timestamp endTime;
	
	@Enumerated(EnumType.STRING)
	@Column(name="scheduler_type")
	private SchedulerType schedulerType;

	@Enumerated(EnumType.STRING)
	@Column(name="schedule_status")
	private ScheduleStatus scheduleStatus;
	
	public Long getId() {
		return id;
	}

	public Long getRepairReportId() {
		return repairReportId;
	}

	public void setRepairReportId(Long repairReportId) {
		this.repairReportId = repairReportId;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public SchedulerType getSchedulerType() {
		return schedulerType;
	}

	public void setSchedulerType(SchedulerType schedulerType) {
		this.schedulerType = schedulerType;
	}

	public ScheduleStatus getScheduleStatus() {
		return scheduleStatus;
	}

	public void setScheduleStatus(ScheduleStatus scheduleStatus) {
		this.scheduleStatus = scheduleStatus;
	}
	
	
	
}
