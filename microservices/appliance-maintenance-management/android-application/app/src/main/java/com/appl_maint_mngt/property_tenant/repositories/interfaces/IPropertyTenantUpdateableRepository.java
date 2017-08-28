package com.appl_maint_mngt.property_tenant.repositories.interfaces;

import com.appl_maint_mngt.property_tenant.models.APropertyTenant;

/**
 * Created by Kyle on 19/03/2017.
 */

public interface IPropertyTenantUpdateableRepository {

    void update(APropertyTenant tenant);
}
