package com.appl_maint_mngt.property.repositories.interfaces;


import com.appl_maint_mngt.property.models.AProperty;
import com.appl_maint_mngt.property.models.Property;

import java.util.List;

/**
 * Created by Kyle on 17/03/2017.
 */

public interface IPropertyUpdateableRepository {

    void addItem(AProperty property);

    void addItems(List<Property> properties);
}
