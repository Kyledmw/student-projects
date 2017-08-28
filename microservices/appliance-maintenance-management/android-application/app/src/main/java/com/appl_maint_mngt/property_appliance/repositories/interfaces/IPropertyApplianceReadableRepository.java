package com.appl_maint_mngt.property_appliance.repositories.interfaces;

import com.appl_maint_mngt.property_appliance.models.interfaces.IPropertyApplianceReadable;

import java.util.List;

/**
 * Created by Kyle on 17/03/2017.
 */

public interface IPropertyApplianceReadableRepository {

    IPropertyApplianceReadable getForId(Long id);

    List<IPropertyApplianceReadable> getForPropertyId(Long id);
    List<IPropertyApplianceReadable> getAll();
}
