package com.appl_maint_mngt.property_manager.models;

import java.util.List;

/**
 * Created by Kyle on 17/03/2017.
 */

public class PropertyManager extends APropertyManager {

    private List<Long> currentPropertyIds;
    private List<Long> previousPropertyIds;

    @Override
    public List<Long> getCurrentPropertyIds() {
        return currentPropertyIds;
    }

    @Override
    public void setCurrentPropertyIds(List<Long> currentPropertyIds) {
        this.currentPropertyIds = currentPropertyIds;
    }

    @Override
    public List<Long> getPreviousPropertyIds() {
        return previousPropertyIds;
    }

    @Override
    public void setPreviousPropertyIds(List<Long> previousPropertyIds) {
        this.previousPropertyIds = previousPropertyIds;
    }
}
