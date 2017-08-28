package com.appl_maint_mngt.property_appliance.repositories;

import com.appl_maint_mngt.property_appliance.repositories.interfaces.IPropertyApplianceReadableRepository;
import com.appl_maint_mngt.property_appliance.repositories.interfaces.IPropertyApplianceUpdateableRepository;

import java.util.Observable;

/**
 * Created by Kyle on 17/03/2017.
 */

public abstract class APropertyApplianceRepository extends Observable implements IPropertyApplianceReadableRepository, IPropertyApplianceUpdateableRepository {
}
