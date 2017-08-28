package com.appl_maint_mngt.property_manager.models.interfaces;

import java.util.List;

/**
 * Created by Kyle on 17/03/2017.
 */

public interface IPropertyManagerWriteable {
    void setCurrentPropertyIds(List<Long> currentPropertyIds);

    void setPreviousPropertyIds(List<Long> previousPropertyIds);
}
