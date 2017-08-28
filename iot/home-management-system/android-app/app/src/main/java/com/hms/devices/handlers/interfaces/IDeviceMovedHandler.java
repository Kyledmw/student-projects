package com.hms.devices.handlers.interfaces;

import com.hms.common.view.models.callbacks.interfaces.ICallback;
import com.hms.devices.model.interfaces.IDeviceMovedReadable;
import com.hms.devices.model.interfaces.IDeviceReadable;

/**
 * Created by Kyle on 19/02/2016.
 */
public interface IDeviceMovedHandler {

    void getDeviceMoved(IDeviceReadable device, ICallback<IDeviceMovedReadable> callback);

    void unRegisterAsCallback();
}
