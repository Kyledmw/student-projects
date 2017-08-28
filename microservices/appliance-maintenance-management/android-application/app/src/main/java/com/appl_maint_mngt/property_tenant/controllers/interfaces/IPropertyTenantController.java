package com.appl_maint_mngt.property_tenant.controllers.interfaces;

import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;

/**
 * Created by Kyle on 07/04/2017.
 */

public interface IPropertyTenantController {
    void getPropertyTenant(Long accountId, IErrorCallback callback);
}
