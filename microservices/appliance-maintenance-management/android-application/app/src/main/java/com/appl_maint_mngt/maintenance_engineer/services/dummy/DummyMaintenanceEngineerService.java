package com.appl_maint_mngt.maintenance_engineer.services.dummy;

import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;
import com.appl_maint_mngt.common.integration.IntegrationController;
import com.appl_maint_mngt.maintenance_engineer.models.MaintenanceEngineer;
import com.appl_maint_mngt.maintenance_engineer.repositories.interfaces.IMaintenanceEngineerUpdateableRepository;
import com.appl_maint_mngt.maintenance_engineer.services.interfaces.IMaintenanceEngineerService;

import java.util.ArrayList;

/**
 * Created by Kyle on 07/04/2017.
 */

public class DummyMaintenanceEngineerService implements IMaintenanceEngineerService {
    @Override
    public void get(Long id, IErrorCallback errorCallback) {
        IMaintenanceEngineerUpdateableRepository repository = IntegrationController.getInstance().getRepositoryController().getUpdateableRepositoryRetriever().getMaintenanceEngineerRepository();

        MaintenanceEngineer maintenanceEngineer = new MaintenanceEngineer();
        maintenanceEngineer.setCurrentOrganisationId((long) 1);
        maintenanceEngineer.setPreviousOrganisationIds(new ArrayList<Long>());
        repository.update(maintenanceEngineer);
    }
}
