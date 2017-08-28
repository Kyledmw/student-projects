package com.appl_maint_mngt.pending_maintenance_scheduling.utility.interfaces;

import com.appl_maint_mngt.pending_maintenance_scheduling.models.constants.ScheduleStatus;
import com.appl_maint_mngt.pending_maintenance_scheduling.models.interfaces.IPendingMaintenanceScheduleReadable;

import java.util.List;

/**
 * Created by Kyle on 15/04/2017.
 */

public interface IPendingMaintenanceSchedulingListFilter {
    List<IPendingMaintenanceScheduleReadable> filterOnScheduleStatus(List<IPendingMaintenanceScheduleReadable> list, ScheduleStatus status);
}
