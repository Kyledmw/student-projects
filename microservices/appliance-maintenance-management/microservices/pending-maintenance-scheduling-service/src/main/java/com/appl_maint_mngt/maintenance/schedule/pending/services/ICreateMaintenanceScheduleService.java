package com.appl_maint_mngt.maintenance.schedule.pending.services;

import com.appl_maint_mngt.maintenance.schedule.pending.models.PendingMaintenanceSchedule;

public interface ICreateMaintenanceScheduleService {

	boolean attemptScheduleCreation(PendingMaintenanceSchedule schedule);
}
