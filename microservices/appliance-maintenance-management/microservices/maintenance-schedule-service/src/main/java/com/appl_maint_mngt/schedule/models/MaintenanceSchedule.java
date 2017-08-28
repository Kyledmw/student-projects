package com.appl_maint_mngt.schedule.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.appl_maint_mngt.schedule.models.constants.ScheduleStatus;

@Entity
@Table(name="maintenance_schedules")
public class MaintenanceSchedule {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="repair_report_id")
	@NotNull(message="msched.rprep.null")
	private Long repairReportId;
	
	@Column(name="start_time")
	@NotNull(message="msched.start_time.null")
	private Timestamp startTime;
	
	@Column(name="end_time")
	@NotNull(message="msched.end_time.null")
	private Timestamp endTime;
	
	@Enumerated(EnumType.STRING)
	@Column(name="schedule_status")
	@NotNull(message="msched.schedstat.null")
	private ScheduleStatus scheduleStatus = ScheduleStatus.NORMAL;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public ScheduleStatus getScheduleStatus() {
		return scheduleStatus;
	}

	public void setScheduleStatus(ScheduleStatus scheduleStatus) {
		this.scheduleStatus = scheduleStatus;
	}
	
	
}
