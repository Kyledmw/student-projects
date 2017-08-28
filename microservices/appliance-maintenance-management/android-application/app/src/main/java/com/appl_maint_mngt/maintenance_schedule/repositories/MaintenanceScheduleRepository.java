package com.appl_maint_mngt.maintenance_schedule.repositories;

import android.util.LongSparseArray;

import com.appl_maint_mngt.maintenance_schedule.models.AMaintenanceSchedule;
import com.appl_maint_mngt.maintenance_schedule.models.MaintenanceSchedule;
import com.appl_maint_mngt.maintenance_schedule.models.constants.ScheduleStatus;
import com.appl_maint_mngt.maintenance_schedule.models.interfaces.IMaintenanceScheduleReadable;
import com.appl_maint_mngt.maintenance_schedule.repositories.constants.IMaintenanceSchedulingObserverUpdateTypes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyle on 23/03/2017.
 */

public class MaintenanceScheduleRepository extends AMaintenanceScheduleRepository {

    private LongSparseArray<AMaintenanceSchedule> maintenanceSchedules;

    public MaintenanceScheduleRepository() {
        maintenanceSchedules = new LongSparseArray<>();
    }

    @Override
    public void addItem(MaintenanceSchedule sched) {
        maintenanceSchedules.put(sched.getId(), sched);
        updateObservers(IMaintenanceSchedulingObserverUpdateTypes.NEW_ITEM);
    }

    @Override
    public void addItems(List<MaintenanceSchedule> scheds) {
        for(MaintenanceSchedule sched: scheds) {
            maintenanceSchedules.put(sched.getId(), sched);
        }
        updateObservers(IMaintenanceSchedulingObserverUpdateTypes.NEW_ITEMS);
    }

    @Override
    public IMaintenanceScheduleReadable get(Long id) {
        return maintenanceSchedules.get(id);
    }

    @Override
    public IMaintenanceScheduleReadable getForReportId(Long id) {
        for(int i=0; i< maintenanceSchedules.size(); i++) {
            if(maintenanceSchedules.valueAt(i).getRepairReportId().equals(id)) return maintenanceSchedules.valueAt(i);
        }
        return null;
    }

    @Override
    public List<IMaintenanceScheduleReadable> getForStatus(ScheduleStatus scheduleStatus) {
        List<IMaintenanceScheduleReadable> list = new ArrayList<>();
        for(int i=0; i<maintenanceSchedules.size(); i++) {
            if(maintenanceSchedules.valueAt(i).getScheduleStatus().equals(scheduleStatus))
            list.add(maintenanceSchedules.valueAt(i));
        }
        return list;
    }

    @Override
    public List<IMaintenanceScheduleReadable> getAll() {
        List<IMaintenanceScheduleReadable> list = new ArrayList<>();
        for(int i=0; i<maintenanceSchedules.size(); i++) {
            list.add(maintenanceSchedules.valueAt(i));
        }
        return list;
    }

    private void updateObservers(String updateType) {
        setChanged();
        notifyObservers(updateType);
        hasChanged();
    }
}
