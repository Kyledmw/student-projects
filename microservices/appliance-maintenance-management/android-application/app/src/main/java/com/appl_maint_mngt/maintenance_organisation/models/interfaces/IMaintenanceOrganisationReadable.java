package com.appl_maint_mngt.maintenance_organisation.models.interfaces;


import com.appl_maint_mngt.address.models.Address;

/**
 * Created by Kyle on 18/03/2017.
 */
public interface IMaintenanceOrganisationReadable {
    Long getId();

    String getName();

    String getDescription();

    Address getAddress();
}
