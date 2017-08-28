package com.appl_maint_mngt.property_tenant.services.interfaces;

import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;

/**
 * Created by Kyle on 07/04/2017.
 */

public interface IPropertyTenantService {

    void get(Long accountId, IErrorCallback errorCallback);
}
