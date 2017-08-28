package com.appl_maint_mngt.maintenance_engineer.controllers;

import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;
import com.appl_maint_mngt.common.integration.IntegrationController;
import com.appl_maint_mngt.common.services.ServiceFactory;
import com.appl_maint_mngt.maintenance_engineer.controllers.interfaces.IMaintenanceEngineerController;
import com.appl_maint_mngt.maintenance_engineer.models.MaintenanceEngineer;
import com.appl_maint_mngt.maintenance_engineer.services.interfaces.IMaintenanceEngineerService;

/**
 * Created by Kyle on 07/04/2017.
 */

public class MaintenanceEngineerController implements IMaintenanceEngineerController {

    private IMaintenanceEngineerService maintenanceEngineerService;

    public MaintenanceEngineerController() {
        maintenanceEngineerService = IntegrationController.getInstance().getServiceFactory().createMaintenanceEngineerService();
    }

    @Override
    public void getEngineer(Long accountId, IErrorCallback errorCallback) {
        maintenanceEngineerService.get(accountId, errorCallback);
    }
}
