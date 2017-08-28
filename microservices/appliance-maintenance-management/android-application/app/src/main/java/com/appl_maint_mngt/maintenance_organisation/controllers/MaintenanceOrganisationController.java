package com.appl_maint_mngt.maintenance_organisation.controllers;

import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;
import com.appl_maint_mngt.common.integration.IntegrationController;
import com.appl_maint_mngt.maintenance_organisation.controllers.interfaces.IMaintenanceOrganisationController;
import com.appl_maint_mngt.maintenance_organisation.services.interfaces.IMaintenanceOrganisationService;

/**
 * Created by Kyle on 09/04/2017.
 */

public class MaintenanceOrganisationController implements IMaintenanceOrganisationController {

    private IMaintenanceOrganisationService maintenanceOrganisationService;

    public MaintenanceOrganisationController() {
        maintenanceOrganisationService = IntegrationController.getInstance().getServiceFactory().createMaintenanceOrganisationService();
    }

    @Override
    public void getAll(IErrorCallback callback) {
        maintenanceOrganisationService.getAll(callback);
    }
}
