package com.appl_maint_mngt.appliance.repositories.interfaces;


import com.appl_maint_mngt.appliance.models.interfaces.IApplianceReadable;

/**
 * Created by Kyle on 18/03/2017.
 */

public interface IApplianceReadableRepository {

    IApplianceReadable get(String id);
}
