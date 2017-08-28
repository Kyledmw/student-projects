package com.hms.sensors.motion.model.interfaces;

import com.hms.camera.models.interfaces.ICameraReadable;

/**
 * Created by Kyle on 13/02/2016.
 */
public interface IMotionDetectionReadable {

    long getTimestampSeconds();

    String getCameraDataId();

    void setCameraModel(ICameraReadable cameraModel);

    ICameraReadable getCameraData();
}
