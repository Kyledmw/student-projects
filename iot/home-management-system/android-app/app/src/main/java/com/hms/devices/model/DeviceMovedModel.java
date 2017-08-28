package com.hms.devices.model;

import com.hms.devices.model.interfaces.IDeviceMovedReadable;

/**
 * Created by Mark on 13/02/2016.
 */
public class DeviceMovedModel implements IDeviceMovedReadable {

    private long _timestamp;

    public DeviceMovedModel(long timestamp){
        _timestamp = timestamp;
    }

    public String toString() {
        return "Timestamp (Seconds): " + _timestamp;
    }


    @Override
    public long getTimestampSeconds() {
        return _timestamp;
    }
}
