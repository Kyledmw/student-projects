package com.hms.sensors.settings.handlers.interfaces;

import com.hms.common.view.models.callbacks.interfaces.ICallback;
import com.hms.devices.model.interfaces.IDeviceReadable;
import com.hms.sensors.settings.models.SensorSettingsModel;

/**
 * Created by alan on 21/04/16.
 */
public interface ISensorSettingsHandler {


    void getSensorSettings(IDeviceReadable device, ICallback<SensorSettingsModel> callback);

    void setSensorSettings(IDeviceReadable device, SensorSettingsModel settings, ICallback<Boolean> callback);

    void unRegisterAsCallback();
}
