package com.appl_maint_mngt.maintenance_schedule.repositories.interfaces;


import com.appl_maint_mngt.maintenance_schedule.models.MaintenanceSchedule;

import java.util.List;

/**
 * Created by Kyle on 23/03/2017.
 */

public interface IMaintenanceScheduleUpdateableRepository {

    void addItem(MaintenanceSchedule sched);
    void addItems(List<MaintenanceSchedule> scheds);
}
