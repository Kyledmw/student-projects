package com.appl_maint_mngt.property_tenant.controllers;

import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;
import com.appl_maint_mngt.common.integration.IntegrationController;
import com.appl_maint_mngt.property_tenant.controllers.interfaces.IPropertyTenantController;
import com.appl_maint_mngt.property_tenant.services.interfaces.IPropertyTenantService;

/**
 * Created by Kyle on 07/04/2017.
 */

public class PropertyTenantController implements IPropertyTenantController {

    private IPropertyTenantService propertyTenantService;

    public PropertyTenantController() {
        propertyTenantService = IntegrationController.getInstance().getServiceFactory().createPropertyTenantService();
    }

    @Override
    public void getPropertyTenant(Long accountId, IErrorCallback callback) {
        propertyTenantService.get(accountId, callback);
    }
}
