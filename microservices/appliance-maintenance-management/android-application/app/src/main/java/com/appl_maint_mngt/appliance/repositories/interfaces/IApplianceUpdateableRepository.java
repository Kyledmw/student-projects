package com.appl_maint_mngt.appliance.repositories.interfaces;


import com.appl_maint_mngt.appliance.models.Appliance;

import java.util.List;

/**
 * Created by Kyle on 18/03/2017.
 */
public interface IApplianceUpdateableRepository {

    void addItem(Appliance appliance);

    void addItems(List<Appliance> appliances);
}
