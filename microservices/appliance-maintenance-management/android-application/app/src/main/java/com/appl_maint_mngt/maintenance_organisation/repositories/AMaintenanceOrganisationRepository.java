package com.appl_maint_mngt.maintenance_organisation.repositories;

import com.appl_maint_mngt.maintenance_organisation.repositories.interfaces.IMaintenanceOrganisationReadableRepository;
import com.appl_maint_mngt.maintenance_organisation.repositories.interfaces.IMaintenanceOrganisationUpdateableRepository;

import java.util.Observable;

/**
 * Created by Kyle on 09/04/2017.
 */

public abstract class AMaintenanceOrganisationRepository extends Observable implements IMaintenanceOrganisationReadableRepository, IMaintenanceOrganisationUpdateableRepository {
}
