package com.appl_maint_mngt.maintenance_engineer.repositories;


import com.appl_maint_mngt.maintenance_engineer.models.AMaintenanceEngineer;
import com.appl_maint_mngt.maintenance_engineer.models.MaintenanceEngineer;
import com.appl_maint_mngt.maintenance_engineer.models.interfaces.IMaintenanceEngineerReadable;
import com.appl_maint_mngt.maintenance_engineer.repositories.constants.IMaintenanceEngineerObserverUpdateTypes;

/**
 * Created by Kyle on 19/03/2017.
 */

public class MaintenanceEngineerRepository extends AMaintenanceEngineerRepository {

    private AMaintenanceEngineer engineer;

    public MaintenanceEngineerRepository() {
        this.engineer = new MaintenanceEngineer();
    }

    @Override
    public void update(AMaintenanceEngineer engineer) {
        this.engineer = engineer;
        updateObservers(IMaintenanceEngineerObserverUpdateTypes.MODEL_UPDATE);
    }

    @Override
    public IMaintenanceEngineerReadable get() {
        return engineer;
    }

    private void updateObservers(String updateType) {
        setChanged();
        notifyObservers(updateType);
        hasChanged();
    }
}
