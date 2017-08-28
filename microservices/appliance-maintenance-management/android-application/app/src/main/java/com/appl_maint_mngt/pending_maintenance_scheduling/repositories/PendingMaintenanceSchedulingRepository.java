package com.appl_maint_mngt.pending_maintenance_scheduling.repositories;

import android.util.LongSparseArray;

import com.appl_maint_mngt.maintenance_schedule.models.constants.ScheduleStatus;
import com.appl_maint_mngt.pending_maintenance_scheduling.models.PendingMaintenanceSchedule;
import com.appl_maint_mngt.pending_maintenance_scheduling.models.constants.SchedulerType;
import com.appl_maint_mngt.pending_maintenance_scheduling.models.interfaces.IPendingMaintenanceScheduleReadable;
import com.appl_maint_mngt.pending_maintenance_scheduling.repositories.constants.IPendingMaintenanceSchedulingObserverUpdateTypes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyle on 23/03/2017.
 */

public class PendingMaintenanceSchedulingRepository extends APendingMaintenanceSchedulingRepository {

    private LongSparseArray<PendingMaintenanceSchedule> pendingMaintenanceSchedules;

    public PendingMaintenanceSchedulingRepository() {
        this.pendingMaintenanceSchedules = new LongSparseArray<>();
    }

    @Override
    public void addItem(PendingMaintenanceSchedule schedule) {
        this.pendingMaintenanceSchedules.put(schedule.getId(), schedule);
        updateObservers(IPendingMaintenanceSchedulingObserverUpdateTypes.MODEL_UPDATE);
    }

    @Override
    public void addItems(List<PendingMaintenanceSchedule> pendingMaintenanceScheduleList) {
        for(PendingMaintenanceSchedule sched : pendingMaintenanceScheduleList) {
            this.pendingMaintenanceSchedules.put(sched.getId(), sched);
        }
        updateObservers(IPendingMaintenanceSchedulingObserverUpdateTypes.MODEL_UPDATE);
    }

    private void updateObservers(String updateType) {
        setChanged();
        notifyObservers(updateType);
        hasChanged();
    }

    @Override
    public List<IPendingMaintenanceScheduleReadable> getForScheduler(SchedulerType schedulerType) {
        List<IPendingMaintenanceScheduleReadable> scheds = new ArrayList<>();
        for(int i=0; i< pendingMaintenanceSchedules.size(); i++) {
            if(pendingMaintenanceSchedules.valueAt(i).getSchedulerType().equals(schedulerType)) {
                scheds.add(pendingMaintenanceSchedules.valueAt(i));
            }
        }
        return scheds;
    }

    @Override
    public List<IPendingMaintenanceScheduleReadable> getForSchedulerAndRepairReportId(SchedulerType schedulerType, Long repairReportId) {
        List<IPendingMaintenanceScheduleReadable> scheds = new ArrayList<>();
        for(int i=0; i< pendingMaintenanceSchedules.size(); i++) {
            if(pendingMaintenanceSchedules.valueAt(i).getSchedulerType().equals(schedulerType) && pendingMaintenanceSchedules.valueAt(i).getRepairReportId().equals(repairReportId)) {
                scheds.add(pendingMaintenanceSchedules.valueAt(i));
            }
        }
        return scheds;
    }

    @Override
    public List<IPendingMaintenanceScheduleReadable> getForRepairReportId(Long repairReportId) {
        List<IPendingMaintenanceScheduleReadable> scheds = new ArrayList<>();
        for(int i=0; i< pendingMaintenanceSchedules.size(); i++) {
            if(pendingMaintenanceSchedules.valueAt(i).getRepairReportId().equals(repairReportId)) {
                scheds.add(pendingMaintenanceSchedules.valueAt(i));
            }
        }
        return scheds;
    }
}
