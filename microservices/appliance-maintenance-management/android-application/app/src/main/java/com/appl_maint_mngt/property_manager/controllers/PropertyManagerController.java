package com.appl_maint_mngt.property_manager.controllers;

import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;
import com.appl_maint_mngt.common.integration.IntegrationController;
import com.appl_maint_mngt.property_manager.controllers.interfaces.IPropertyManagerController;
import com.appl_maint_mngt.property_manager.services.interfaces.IPropertyManagerService;

/**
 * Created by Kyle on 07/04/2017.
 */

public class PropertyManagerController implements IPropertyManagerController {

    private IPropertyManagerService propertyManagerService;

    public PropertyManagerController() {
        propertyManagerService = IntegrationController.getInstance().getServiceFactory().createPropertyManagerService();
    }

    @Override
    public void getPropertyManager(Long accountId, IErrorCallback errorCallback) {
        propertyManagerService.get(accountId, errorCallback);
    }
}
