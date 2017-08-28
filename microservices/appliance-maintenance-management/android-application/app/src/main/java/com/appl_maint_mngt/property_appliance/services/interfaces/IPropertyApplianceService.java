package com.appl_maint_mngt.property_appliance.services.interfaces;

import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;

import java.util.List;

/**
 * Created by Kyle on 08/04/2017.
 */

public interface IPropertyApplianceService {

    void get(Long id, IErrorCallback errorCallback);

    void findByPropertyId(Long propertyId, IErrorCallback callback);

    void findByPropertyIds(List<Long> propertyIds, IErrorCallback callback);
}
