package com.appl_maint_mngt.maintenance.schedule.pending.models.web;

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

import com.appl_maint_mngt.maintenance.schedule.pending.validation.SchedulerType;

public class PendingSchedulePayload implements IPendingSchedulePayload {
	
	@NotNull(message = "{pendschedpload.reportid.null}")
	private Long reportId;
	
	@NotNull(message = "{pendschedpload.starttime.null}")
	private Timestamp startTime;
	
	@NotNull(message = "{pendschedpload.endtime.null}")
	private Timestamp endTime;
	
	@NotNull(message = "{pendschedpload.schedtype.null}")
	@SchedulerType
	private String schedulerType;

	@Override
	public Long getReportId() {
		return reportId;
	}

	@Override
	public Timestamp getStartTime() {
		return startTime;
	}

	@Override
	public Timestamp getEndTime() {
		return endTime;
	}

	@Override
	public String getSchedulerType() {
		return schedulerType;
	}

	@Override
	public void setReportId(Long id) {
		this.reportId = id;
	}

	@Override
	public void setStartTime(Timestamp time) {
		this.startTime = time;
		
	}

	@Override
	public void setEndTime(Timestamp time) {
		this.endTime = time;
		
	}

	@Override
	public void setSchedulerType(String type) {
		this.schedulerType = type;
		
	}

}
