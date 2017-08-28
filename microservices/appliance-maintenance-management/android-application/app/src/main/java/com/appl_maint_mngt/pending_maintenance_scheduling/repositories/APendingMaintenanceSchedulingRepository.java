package com.appl_maint_mngt.pending_maintenance_scheduling.repositories;

import com.appl_maint_mngt.pending_maintenance_scheduling.repositories.interfaces.IPendingMaintenanceSchedulingReadableRepository;
import com.appl_maint_mngt.pending_maintenance_scheduling.repositories.interfaces.IPendingMaintenanceSchedulingUpdateableRepository;

import java.util.Observable;

/**
 * Created by Kyle on 23/03/2017.
 */

public abstract class APendingMaintenanceSchedulingRepository extends Observable implements IPendingMaintenanceSchedulingReadableRepository, IPendingMaintenanceSchedulingUpdateableRepository {
}
