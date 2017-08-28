package com.appl_maint_mngt.property.models.interfaces;

import com.appl_maint_mngt.address.models.interfaces.IAddressReadable;

/**
 * Created by Kyle on 17/03/2017.
 */

public interface IPropertyReadable {
    Long getId();
    IAddressReadable getAddress();
    int getAge();
    int getBedCount();
    int getBathroomCount();
}
