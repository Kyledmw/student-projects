package com.hms.sensors.motion.handlers.interfaces;

import com.hms.common.view.models.callbacks.interfaces.ICallback;
import com.hms.devices.model.interfaces.IDeviceReadable;
import com.hms.sensors.motion.model.interfaces.IMotionDetectionReadable;

/**
 * Created by Kyle on 19/02/2016.
 */
public interface IMotionDetectionHandler {

    void getMotionDetected(IDeviceReadable device, ICallback<IMotionDetectionReadable> callback);

    void unRegisterAsCallback();
}
