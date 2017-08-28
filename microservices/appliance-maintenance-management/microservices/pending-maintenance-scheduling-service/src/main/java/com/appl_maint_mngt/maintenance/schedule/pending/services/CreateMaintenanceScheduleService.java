package com.appl_maint_mngt.maintenance.schedule.pending.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appl_maint_mngt.maintenance.schedule.pending.clients.http.IMaintenanceScheduleClient;
import com.appl_maint_mngt.maintenance.schedule.pending.models.PendingMaintenanceSchedule;
import com.appl_maint_mngt.maintenance.schedule.pending.web.constants.IResponseMessages;
import com.appl_maint_mngt.schedule.models.web.IMaintenanceSchedulePayload;
import com.appl_maint_mngt.schedule.models.web.MaintenanceSchedulePayload;

@Service
public class CreateMaintenanceScheduleService implements ICreateMaintenanceScheduleService {
	
	@Autowired
	private IMaintenanceScheduleClient maintSchedClient;

	@Override
	public boolean attemptScheduleCreation(PendingMaintenanceSchedule schedule) {
		IMaintenanceSchedulePayload payload = new MaintenanceSchedulePayload();
		payload.setStartTime(schedule.getStartTime());
		payload.setEndTime(schedule.getEndTime());
		payload.setRepairReportId(schedule.getId());
		String res = maintSchedClient.create(payload);
		System.out.println("DEBUG: " + res);
		return !res.equals(IResponseMessages.GENERIC_ERROR);
	}

}
