package com.appl_maint_mngt.maintenance_schedule.repositories;


import com.appl_maint_mngt.maintenance_schedule.repositories.interfaces.IMaintenanceScheduleReadableRepository;
import com.appl_maint_mngt.maintenance_schedule.repositories.interfaces.IMaintenanceScheduleUpdateableRepository;

import java.util.Observable;

/**
 * Created by Kyle on 23/03/2017.
 */

public abstract class AMaintenanceScheduleRepository extends Observable implements IMaintenanceScheduleUpdateableRepository, IMaintenanceScheduleReadableRepository {
}
