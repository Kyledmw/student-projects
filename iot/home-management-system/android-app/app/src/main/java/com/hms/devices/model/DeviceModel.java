package com.hms.devices.model;

import com.hms.devices.model.interfaces.IDeviceReadable;

/**
 * Created by Kyle on 09/02/2016.
 */
public class DeviceModel implements IDeviceReadable {


    private String _id;
    private String _deviceName;

    public DeviceModel(String id, String deviceName){
        _id = id;
        _deviceName = deviceName;
    }

    @Override
    public String toString(){
        return "ID: " + _id + " Device Name: " + _deviceName;
    }

    @Override
    public String getId() {
        return _id;
    }

    @Override
    public String getName() {
        return _deviceName;
    }
}
