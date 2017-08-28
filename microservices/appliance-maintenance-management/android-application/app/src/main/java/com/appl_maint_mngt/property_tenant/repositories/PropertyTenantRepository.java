package com.appl_maint_mngt.property_tenant.repositories;

import com.appl_maint_mngt.property_tenant.models.APropertyTenant;
import com.appl_maint_mngt.property_tenant.models.PropertyTenant;
import com.appl_maint_mngt.property_tenant.models.interfaces.IPropertyTenantReadable;
import com.appl_maint_mngt.property_tenant.repositories.constants.IPropertyTenantObserverUpdateTypes;

/**
 * Created by Kyle on 19/03/2017.
 */

public class PropertyTenantRepository extends APropertyTenantRepository {

    private APropertyTenant tenant;

    public PropertyTenantRepository() {
        tenant = new PropertyTenant();
    }

    @Override
    public void update(APropertyTenant tenant) {
        this.tenant = tenant;
        updateObservers(IPropertyTenantObserverUpdateTypes.MODEL_UPDATE);
    }

    @Override
    public IPropertyTenantReadable get() {
        return tenant;
    }

    private void updateObservers(String updateType) {
        setChanged();
        notifyObservers(updateType);
        hasChanged();
    }
}
