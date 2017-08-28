package com.appl_maint_mngt.maintenance_schedule.repositories.interfaces;

import com.appl_maint_mngt.maintenance_schedule.models.constants.ScheduleStatus;
import com.appl_maint_mngt.maintenance_schedule.models.interfaces.IMaintenanceScheduleReadable;

import java.util.List;

/**
 * Created by Kyle on 23/03/2017.
 */

public interface IMaintenanceScheduleReadableRepository {

    IMaintenanceScheduleReadable get(Long id);
    IMaintenanceScheduleReadable getForReportId(Long id);
    List<IMaintenanceScheduleReadable> getForStatus(ScheduleStatus scheduleStatus);
    List<IMaintenanceScheduleReadable> getAll();
}
