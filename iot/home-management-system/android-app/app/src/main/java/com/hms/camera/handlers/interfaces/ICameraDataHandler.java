package com.hms.camera.handlers.interfaces;

import com.hms.common.view.models.callbacks.interfaces.ICallback;
import com.hms.camera.models.interfaces.ICameraReadable;
import com.hms.devices.model.interfaces.IDeviceReadable;

/**
 * Created by Kyle on 19/02/2016.
 */
public interface ICameraDataHandler {

    void getCameraData(IDeviceReadable device, ICallback<ICameraReadable> callback);

    void unRegisterAsCallback();
}
