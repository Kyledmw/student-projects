package com.appl_maint_mngt.schedule.models.web;

import java.sql.Timestamp;

public interface IMaintenanceSchedulePayload {
	Long getRepairReportId();
	Timestamp getStartTime();
	Timestamp getEndTime();
	
	void setRepairReportId(Long id);
	void setStartTime(Timestamp time);
	void setEndTime(Timestamp time);
}
