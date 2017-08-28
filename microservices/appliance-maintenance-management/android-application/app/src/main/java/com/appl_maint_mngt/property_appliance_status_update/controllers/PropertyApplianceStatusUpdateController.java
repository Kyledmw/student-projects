package com.appl_maint_mngt.property_appliance_status_update.controllers;

import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;
import com.appl_maint_mngt.common.integration.IntegrationController;
import com.appl_maint_mngt.property_appliance_status_update.controllers.interfaces.IPropertyApplianceStatusUpdateController;
import com.appl_maint_mngt.property_appliance_status_update.models.web.interfaces.IPropertyApplianceStatusUpdatePayload;
import com.appl_maint_mngt.property_appliance_status_update.services.interfaces.IPropertyApplianceStatusUpdateService;

/**
 * Created by Kyle on 09/04/2017.
 */

public class PropertyApplianceStatusUpdateController implements IPropertyApplianceStatusUpdateController {

    private IPropertyApplianceStatusUpdateService propertyApplianceStatusUpdateService;

    public PropertyApplianceStatusUpdateController() {
        propertyApplianceStatusUpdateService = IntegrationController.getInstance().getServiceFactory().createPropertyApplianceStatusUpdateService();
    }

    @Override
    public void updateStatus(IPropertyApplianceStatusUpdatePayload payload, IErrorCallback errorCallback) {
        propertyApplianceStatusUpdateService.update(payload, errorCallback);
    }
}
