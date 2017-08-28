package com.appl_maint_mngt.appliance.repositories;
import com.appl_maint_mngt.appliance.repositories.interfaces.IApplianceReadableRepository;
import com.appl_maint_mngt.appliance.repositories.interfaces.IApplianceUpdateableRepository;

import java.util.Observable;

/**
 * Created by Kyle on 18/03/2017.
 */

public abstract class AApplianceRepository extends Observable implements IApplianceReadableRepository, IApplianceUpdateableRepository {
}
