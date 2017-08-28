package com.hms.sensors.atmospheric_pressure.handlers.interfaces;

import com.hms.common.view.models.callbacks.interfaces.ICallback;
import com.hms.sensors.atmospheric_pressure.models.interfaces.IAtmosphericPressureReadable;
import com.hms.devices.model.interfaces.IDeviceReadable;

/**
 * Created by Kyle on 19/02/2016.
 */
public interface IAtmosphericPressureHandler {

    void getAtmosphericPressure(IDeviceReadable device, ICallback<IAtmosphericPressureReadable> callback);

    void unRegisterAsCallback();
}
