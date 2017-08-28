package com.appl_maint_mngt.maintenance_organisation.repositories.interfaces;

import com.appl_maint_mngt.maintenance_organisation.models.MaintenanceOrganisation;

import java.util.List;

/**
 * Created by Kyle on 18/03/2017.
 */

public interface IMaintenanceOrganisationUpdateableRepository {

    void addItems(List<MaintenanceOrganisation> maintOrg);
}
