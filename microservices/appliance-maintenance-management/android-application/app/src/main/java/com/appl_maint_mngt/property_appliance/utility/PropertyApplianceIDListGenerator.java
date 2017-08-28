package com.appl_maint_mngt.property_appliance.utility;

import com.appl_maint_mngt.property_appliance.models.interfaces.IPropertyApplianceReadable;
import com.appl_maint_mngt.property_appliance.utility.interfaces.IPropertyApplianceIDListGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyle on 09/04/2017.
 */

public class PropertyApplianceIDListGenerator implements IPropertyApplianceIDListGenerator {
    @Override
    public List<Long> generate(List<IPropertyApplianceReadable> propertyApplianceList) {
        List<Long> list = new ArrayList<>();
        for(IPropertyApplianceReadable propertyAppliance : propertyApplianceList) {
            list.add(propertyAppliance.getId());
        }
        return list;
    }
}
