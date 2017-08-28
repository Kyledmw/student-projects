package com.appl_maint_mngt.maintenance_engineer.repositories;

import com.appl_maint_mngt.maintenance_engineer.repositories.interfaces.IMaintenanceEngineerReadableRepository;
import com.appl_maint_mngt.maintenance_engineer.repositories.interfaces.IMaintenanceEngineerUpdateableRepository;

import java.util.Observable;

/**
 * Created by Kyle on 19/03/2017.
 */

public abstract class AMaintenanceEngineerRepository extends Observable implements IMaintenanceEngineerReadableRepository, IMaintenanceEngineerUpdateableRepository {
}
