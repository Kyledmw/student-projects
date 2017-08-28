package com.appl_maint_mngt.property_tenant.repositories;

import com.appl_maint_mngt.property_tenant.repositories.interfaces.IPropertyTenantReadableRepository;
import com.appl_maint_mngt.property_tenant.repositories.interfaces.IPropertyTenantUpdateableRepository;

import java.util.Observable;

/**
 * Created by Kyle on 19/03/2017.
 */
public abstract class APropertyTenantRepository extends Observable implements IPropertyTenantReadableRepository, IPropertyTenantUpdateableRepository {
}
