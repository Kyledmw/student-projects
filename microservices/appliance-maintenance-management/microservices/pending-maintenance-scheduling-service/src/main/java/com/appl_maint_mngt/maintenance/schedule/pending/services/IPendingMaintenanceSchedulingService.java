package com.appl_maint_mngt.maintenance.schedule.pending.services;

import java.util.List;

import com.appl_maint_mngt.maintenance.schedule.pending.models.PendingMaintenanceSchedule;
import com.appl_maint_mngt.maintenance.schedule.pending.models.constants.SchedulerType;

public interface IPendingMaintenanceSchedulingService {

	PendingMaintenanceSchedule acceptPendingSchedule(Long id);
	
	PendingMaintenanceSchedule declinePendingSchedule(Long id);
	
	boolean doesReportHaveAcceptedSchedule(Long reportId);
	
	List<PendingMaintenanceSchedule> getAllForReportAndSchedulerType(Long reportId, SchedulerType type);
	
	Long getRepairReportForId(Long id);
	
	boolean doesItemExist(Long id);
	
	PendingMaintenanceSchedule save(PendingMaintenanceSchedule sched);
	
	PendingMaintenanceSchedule getForId(Long id);
	
	List<PendingMaintenanceSchedule> getAllForReport(Long reportId);
}
