package com.appl_maint_mngt.maintenance_schedule.views.interfaces;

import com.appl_maint_mngt.maintenance_schedule.models.interfaces.IMaintenanceScheduleReadable;

import java.util.List;

/**
 * Created by Kyle on 14/04/2017.
 */

public interface IMaintenanceScheduleListView {

    void update(List<IMaintenanceScheduleReadable> list);
}
