package com.appl_maint_mngt.pending_maintenance_scheduling.utility;

import com.appl_maint_mngt.pending_maintenance_scheduling.models.constants.ScheduleStatus;
import com.appl_maint_mngt.pending_maintenance_scheduling.models.interfaces.IPendingMaintenanceScheduleReadable;
import com.appl_maint_mngt.pending_maintenance_scheduling.utility.interfaces.IPendingMaintenanceSchedulingListFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyle on 15/04/2017.
 */

public class PendingMaintenanceSchedulingListFilter implements IPendingMaintenanceSchedulingListFilter {
    @Override
    public List<IPendingMaintenanceScheduleReadable> filterOnScheduleStatus(List<IPendingMaintenanceScheduleReadable> list, ScheduleStatus status) {
        List<IPendingMaintenanceScheduleReadable> filtered = new ArrayList<>();
        for(IPendingMaintenanceScheduleReadable maintSched: list) {
            if(maintSched.getScheduleStatus().equals(status)) {
                filtered.add(maintSched);
            }
        }
        return filtered;
    }
}
