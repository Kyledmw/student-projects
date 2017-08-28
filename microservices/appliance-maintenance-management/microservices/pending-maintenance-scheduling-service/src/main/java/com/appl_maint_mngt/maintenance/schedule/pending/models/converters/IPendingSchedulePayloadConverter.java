package com.appl_maint_mngt.maintenance.schedule.pending.models.converters;

import com.appl_maint_mngt.maintenance.schedule.pending.models.PendingMaintenanceSchedule;
import com.appl_maint_mngt.maintenance.schedule.pending.models.web.IPendingSchedulePayload;

public interface IPendingSchedulePayloadConverter {

	PendingMaintenanceSchedule toPendingMaintSched(IPendingSchedulePayload payload);
}
