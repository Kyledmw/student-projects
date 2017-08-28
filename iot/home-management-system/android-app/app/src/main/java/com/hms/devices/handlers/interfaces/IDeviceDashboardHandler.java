package com.hms.devices.handlers.interfaces;

import com.hms.common.view.models.callbacks.interfaces.ICallback;
import com.hms.devices.model.interfaces.IDeviceFeatureReadable;
import com.hms.devices.model.interfaces.IDeviceReadable;

import java.util.List;

/**
 * Created by Kyle on 24/04/2016.
 */
public interface IDeviceDashboardHandler {

    void getFeaturesForDevice(IDeviceReadable device, ICallback<List<IDeviceFeatureReadable>> callback);
}
