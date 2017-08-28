package com.appl_maint_mngt.appliance_status.repositories;

import com.appl_maint_mngt.appliance_status.repositories.interfaces.IApplianceStatusReadableRepository;
import com.appl_maint_mngt.appliance_status.repositories.interfaces.IApplianceStatusUpdateableRepository;

import java.util.Observable;

/**
 * Created by Kyle on 17/03/2017.
 */

public abstract class AApplianceStatusRepository extends Observable implements IApplianceStatusReadableRepository, IApplianceStatusUpdateableRepository {
}
