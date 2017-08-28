package com.hms.devices.model;

import com.hms.devices.model.interfaces.IDeviceFeatureReadable;

/**
 * Created by Mark on 13/02/2016.
 */
public class DeviceFeatureModel implements IDeviceFeatureReadable {

    private String _id;
    private String _type;

    public DeviceFeatureModel(String id, String type) {
        _type = type;
        _id = id;
    }

    @Override
    public String toString(){

        return "ID: " + _id  + " Type: " + _type;

    }

    @Override
    public String getId() {
        return _id;
    }

    @Override
    public String getType() {
        return _type;
    }
}
