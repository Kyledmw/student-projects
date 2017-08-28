package com.appl_maint_mngt.maintenance.schedule.pending.models.converters;

import org.springframework.stereotype.Component;

import com.appl_maint_mngt.maintenance.schedule.pending.models.PendingMaintenanceSchedule;
import com.appl_maint_mngt.maintenance.schedule.pending.models.constants.ScheduleStatus;
import com.appl_maint_mngt.maintenance.schedule.pending.models.constants.SchedulerType;
import com.appl_maint_mngt.maintenance.schedule.pending.models.web.IPendingSchedulePayload;

@Component
public class PendingSchedulePayloadConverter implements IPendingSchedulePayloadConverter {

	@Override
	public PendingMaintenanceSchedule toPendingMaintSched(IPendingSchedulePayload payload) {
		PendingMaintenanceSchedule sched = new PendingMaintenanceSchedule();
		sched.setEndTime(payload.getEndTime());
		sched.setRepairReportId(payload.getReportId());
		sched.setSchedulerType(SchedulerType.valueOf(payload.getSchedulerType()));
		sched.setStartTime(payload.getStartTime());
		sched.setScheduleStatus(ScheduleStatus.PENDING);
		return sched;
	}

}
