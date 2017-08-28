package com.appl_maint_mngt.property.models.interfaces;


import com.appl_maint_mngt.address.models.Address;

/**
 * Created by Kyle on 17/03/2017.
 */

public interface IPropertyWriteable {
    void setId(Long id);
    void setAddress(Address address);
    void setAge(int age);
    void setBedCount(int bedCount);
    void setBathroomCount(int bathroomCount);
}
