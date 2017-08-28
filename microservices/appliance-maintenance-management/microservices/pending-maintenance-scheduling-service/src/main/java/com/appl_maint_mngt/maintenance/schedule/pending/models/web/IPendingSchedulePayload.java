package com.appl_maint_mngt.maintenance.schedule.pending.models.web;

import java.sql.Timestamp;

public interface IPendingSchedulePayload {

	Long getReportId();
	
	Timestamp getStartTime();
	
	Timestamp getEndTime();
	
	String getSchedulerType();
	
	void setReportId(Long id);
	
	void setStartTime(Timestamp time);
	
	void setEndTime(Timestamp time);
	
	void setSchedulerType(String type);
}
