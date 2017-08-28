package com.appl_maint_mngt.maintenance_organisation.repositories.interfaces;

import com.appl_maint_mngt.maintenance_organisation.models.interfaces.IMaintenanceOrganisationReadable;

import java.util.List;

/**
 * Created by Kyle on 18/03/2017.
 */

public interface IMaintenanceOrganisationReadableRepository {

    List<IMaintenanceOrganisationReadable> getAll();

    IMaintenanceOrganisationReadable getForId(Long id);
}
