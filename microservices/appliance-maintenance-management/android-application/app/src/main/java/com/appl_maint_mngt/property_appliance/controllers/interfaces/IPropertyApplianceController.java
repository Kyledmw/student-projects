package com.appl_maint_mngt.property_appliance.controllers.interfaces;

import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;

import java.util.List;

/**
 * Created by Kyle on 08/04/2017.
 */
public interface IPropertyApplianceController {

    void getPropertyApplianceForId(Long id, IErrorCallback errorCallback);

    void getPropertyAppliancesForProperty(Long propertyId, IErrorCallback errorCallback);

    void getPropertyAppliancesForProperties(List<Long> propertyIds, IErrorCallback errorCallback);
}
