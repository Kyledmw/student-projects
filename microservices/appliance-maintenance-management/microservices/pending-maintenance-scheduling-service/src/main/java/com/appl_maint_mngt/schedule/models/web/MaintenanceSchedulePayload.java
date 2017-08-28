package com.appl_maint_mngt.schedule.models.web;

import java.sql.Timestamp;

public class MaintenanceSchedulePayload implements IMaintenanceSchedulePayload {
	
	private Long repairReportId;
	private Timestamp startTime;
	private Timestamp endtTime;

	@Override
	public Long getRepairReportId() {
		return repairReportId;
	}

	@Override
	public Timestamp getStartTime() {
		return startTime;
	}

	@Override
	public Timestamp getEndTime() {
		return endtTime;
	}

	@Override
	public void setRepairReportId(Long id) {
		this.repairReportId = id;
		
	}

	@Override
	public void setStartTime(Timestamp time) {
		this.startTime = time;
	}

	@Override
	public void setEndTime(Timestamp time) {
		this.endtTime = time;
	}



}
