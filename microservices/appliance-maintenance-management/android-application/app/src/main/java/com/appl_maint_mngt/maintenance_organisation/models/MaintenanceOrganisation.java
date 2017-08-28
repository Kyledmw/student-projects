package com.appl_maint_mngt.maintenance_organisation.models;

import com.appl_maint_mngt.address.models.Address;

/**
 * Created by Kyle on 09/04/2017.
 */

public class MaintenanceOrganisation extends AMaintenanceOrganisation {

    private Long id;

    private String name;

    private String description;

    private Address address;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Address getAddress() {
        return address;
    }

    @Override
    public void setAddress(Address address) {
        this.address = address;
    }
}