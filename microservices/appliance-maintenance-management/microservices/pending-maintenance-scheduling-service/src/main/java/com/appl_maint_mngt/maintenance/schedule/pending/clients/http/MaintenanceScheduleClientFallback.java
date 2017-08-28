package com.appl_maint_mngt.maintenance.schedule.pending.clients.http;

import org.springframework.stereotype.Component;

import com.appl_maint_mngt.maintenance.schedule.pending.web.constants.IResponseMessages;
import com.appl_maint_mngt.schedule.models.web.IMaintenanceSchedulePayload;

@Component
public class MaintenanceScheduleClientFallback implements IMaintenanceScheduleClient {

	@Override
	public String create(IMaintenanceSchedulePayload payload) {
		return IResponseMessages.GENERIC_ERROR;
	}

}
