package com.appl_maint_mngt.property_tenant.services.dummy;

import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;
import com.appl_maint_mngt.common.integration.IntegrationController;
import com.appl_maint_mngt.property_tenant.models.PropertyTenant;
import com.appl_maint_mngt.property_tenant.models.constants.ResidenceStatus;
import com.appl_maint_mngt.property_tenant.repositories.interfaces.IPropertyTenantUpdateableRepository;
import com.appl_maint_mngt.property_tenant.services.interfaces.IPropertyTenantService;

import java.util.ArrayList;

/**
 * Created by Kyle on 07/04/2017.
 */

public class DummyPropertyTenantService implements IPropertyTenantService {
    @Override
    public void get(Long accountId, IErrorCallback errorCallback) {
        IPropertyTenantUpdateableRepository repository = IntegrationController.getInstance().getRepositoryController().getUpdateableRepositoryRetriever().getPropertyTenantRepository();
        PropertyTenant tenant = new PropertyTenant();
        tenant.setCurrentPropertyId((long) 1);
        tenant.setResidenceStatus(ResidenceStatus.OCCUPANT);
        tenant.setPreviousPropertyIds(new ArrayList<Long>());
        repository.update(tenant);
    }
}
