package com.appl_maint_mngt.pending_maintenance_scheduling.repositories.interfaces;

import com.appl_maint_mngt.pending_maintenance_scheduling.models.PendingMaintenanceSchedule;

import java.util.List;

/**
 * Created by Kyle on 23/03/2017.
 */

public interface IPendingMaintenanceSchedulingUpdateableRepository {

    void addItem(PendingMaintenanceSchedule schedule);
    void addItems(List<PendingMaintenanceSchedule> pendingMaintenanceScheduleList);
}
