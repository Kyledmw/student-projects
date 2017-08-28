package com.hms.sensors.temperature.handlers.interfaces;

import com.hms.common.view.models.callbacks.interfaces.ICallback;
import com.hms.devices.model.interfaces.IDeviceReadable;
import com.hms.sensors.temperature.models.interfaces.ITemperatureReadable;

/**
 * Created by Kyle on 19/02/2016.
 */
public interface ITemperatureHandler {

    void getTemperature(IDeviceReadable device, ICallback<ITemperatureReadable> callback);

    void unRegisterAsCallback();
}
