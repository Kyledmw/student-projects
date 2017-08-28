package com.appl_maint_mngt.property.controller;

import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;
import com.appl_maint_mngt.common.integration.IntegrationController;
import com.appl_maint_mngt.property.controller.interfaces.IPropertyController;
import com.appl_maint_mngt.property.services.IPropertyService;

import java.util.List;

/**
 * Created by Kyle on 07/04/2017.
 */

public class PropertyController implements IPropertyController {

    private IPropertyService propertyService;

    public PropertyController() {
        propertyService = IntegrationController.getInstance().getServiceFactory().createPropertyService();
    }

    @Override
    public void getProperties(List<Long> ids, IErrorCallback callback) {
        if(ids == null) return;
        propertyService.findByIds(ids, callback);
    }

    @Override
    public void getProperty(Long id, IErrorCallback callback) {
        propertyService.get(id, callback);
    }
}
