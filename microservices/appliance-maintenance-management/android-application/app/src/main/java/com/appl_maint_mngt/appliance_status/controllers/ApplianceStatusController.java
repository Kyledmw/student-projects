package com.appl_maint_mngt.appliance_status.controllers;

import com.appl_maint_mngt.appliance_status.controllers.interfaces.IApplianceStatusController;
import com.appl_maint_mngt.appliance_status.services.interfaces.IApplianceStatusService;
import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;
import com.appl_maint_mngt.common.integration.IntegrationController;

/**
 * Created by Kyle on 08/04/2017.
 */

public class ApplianceStatusController implements IApplianceStatusController {

    private IApplianceStatusService applianceStatusService;

    public ApplianceStatusController() {
        applianceStatusService = IntegrationController.getInstance().getServiceFactory().createApplianceStatusService();
    }

    @Override
    public void getAll(IErrorCallback errorCallback) {
        applianceStatusService.get(errorCallback);
    }
}
