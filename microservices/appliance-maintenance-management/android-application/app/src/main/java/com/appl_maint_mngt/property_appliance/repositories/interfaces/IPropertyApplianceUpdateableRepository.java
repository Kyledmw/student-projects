package com.appl_maint_mngt.property_appliance.repositories.interfaces;


import com.appl_maint_mngt.property_appliance.models.PropertyAppliance;

import java.util.List;

/**
 * Created by Kyle on 17/03/2017.
 */

public interface IPropertyApplianceUpdateableRepository {

    void addItems(List<PropertyAppliance> propertyAppliances);

    void addItem(PropertyAppliance propertyAppliance);
}
