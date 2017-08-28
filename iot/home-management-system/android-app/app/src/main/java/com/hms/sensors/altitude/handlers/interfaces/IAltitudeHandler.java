package com.hms.sensors.altitude.handlers.interfaces;

import com.hms.common.view.models.callbacks.interfaces.ICallback;
import com.hms.sensors.altitude.models.interfaces.IAltitudeReadable;
import com.hms.devices.model.interfaces.IDeviceReadable;

/**
 * Created by Kyle on 19/02/2016.
 */
public interface IAltitudeHandler {

    void getAltitude(IDeviceReadable device, ICallback<IAltitudeReadable> callback);

    void unRegisterAsCallback();
}
