package com.appl_maint_mngt.property_appliance.controllers;

import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;
import com.appl_maint_mngt.common.integration.IntegrationController;
import com.appl_maint_mngt.property_appliance.controllers.interfaces.IPropertyApplianceController;
import com.appl_maint_mngt.property_appliance.services.interfaces.IPropertyApplianceService;

import java.util.List;

/**
 * Created by Kyle on 08/04/2017.
 */

public class PropertyApplianceController implements IPropertyApplianceController {

    private IPropertyApplianceService propertyApplianceService;

    public PropertyApplianceController() {
        propertyApplianceService = IntegrationController.getInstance().getServiceFactory().createPropertyApplianceService();
    }

    @Override
    public void getPropertyApplianceForId(Long id, IErrorCallback errorCallback) {
        if(id == null) return;
        propertyApplianceService.get(id, errorCallback);
    }

    @Override
    public void getPropertyAppliancesForProperty(Long propertyId, IErrorCallback errorCallback) {
        if(propertyId == null) return;
        propertyApplianceService.findByPropertyId(propertyId, errorCallback);
    }

    @Override
    public void getPropertyAppliancesForProperties(List<Long> propertyIds, IErrorCallback errorCallback) {
        if(propertyIds == null || propertyIds.isEmpty()) return;
        propertyApplianceService.findByPropertyIds(propertyIds, errorCallback);
    }
}
