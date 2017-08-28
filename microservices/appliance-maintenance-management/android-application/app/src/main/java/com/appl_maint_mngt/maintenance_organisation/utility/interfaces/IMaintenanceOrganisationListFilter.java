package com.appl_maint_mngt.maintenance_organisation.utility.interfaces;

import com.appl_maint_mngt.maintenance_organisation.models.interfaces.IMaintenanceOrganisationReadable;

import java.util.List;

/**
 * Created by Kyle on 10/04/2017.
 */

public interface IMaintenanceOrganisationListFilter {

    List<IMaintenanceOrganisationReadable> filterIds(List<IMaintenanceOrganisationReadable> maintOrgs, List<Long> filterIDs);
}
