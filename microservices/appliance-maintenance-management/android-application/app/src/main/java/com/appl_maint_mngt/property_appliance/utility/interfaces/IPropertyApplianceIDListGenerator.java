package com.appl_maint_mngt.property_appliance.utility.interfaces;

import com.appl_maint_mngt.property_appliance.models.interfaces.IPropertyApplianceReadable;

import java.util.List;

/**
 * Created by Kyle on 09/04/2017.
 */

public interface IPropertyApplianceIDListGenerator {

    List<Long> generate(List<IPropertyApplianceReadable> propertyApplianceList);
}
