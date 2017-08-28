package com.hms.camera.handlers.interfaces;

import com.hms.common.view.models.callbacks.interfaces.ICallback;
import com.hms.devices.model.interfaces.IDeviceReadable;
import com.hms.camera.models.CameraSettingsModel;

/**
 * Created by Kyle on 19/02/2016.
 */
public interface ICameraSettingsHandler {

    void getCameraSettings(IDeviceReadable device, ICallback<CameraSettingsModel> callback);

    void sendCameraSettings(IDeviceReadable device, CameraSettingsModel settings, ICallback<Boolean> callback);

    void unRegisterAsCallback();
}
