package com.appl_maint_mngt.maintenance_organisation.models.interfaces;


import com.appl_maint_mngt.address.models.Address;

/**
 * Created by Kyle on 18/03/2017.
 */

public interface IMaintenanceOrganisationWriteable {

    void setId(Long id);
    void setName(String name);
    void setDescription(String description);
    void setAddress(Address address);
}
