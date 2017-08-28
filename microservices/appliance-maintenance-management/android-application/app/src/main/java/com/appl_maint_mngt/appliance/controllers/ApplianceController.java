package com.appl_maint_mngt.appliance.controllers;

import com.appl_maint_mngt.appliance.controllers.interfaces.IApplianceController;
import com.appl_maint_mngt.appliance.services.interfaces.IApplianceService;
import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;
import com.appl_maint_mngt.common.integration.IntegrationController;

/**
 * Created by Kyle on 08/04/2017.
 */

public class ApplianceController implements IApplianceController {

    private IApplianceService applianceService;

    public ApplianceController() {
        applianceService = IntegrationController.getInstance().getServiceFactory().createApplianceService();
    }

    @Override
    public void getForId(String id, IErrorCallback callback) {
        applianceService.get(id, callback);
    }

    @Override
    public void getAll(IErrorCallback callback) {
        applianceService.get(callback);
    }
}
