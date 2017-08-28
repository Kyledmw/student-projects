package com.hms.sensors.motion.model;

import com.hms.camera.models.interfaces.ICameraReadable;
import com.hms.sensors.motion.model.interfaces.IMotionDetectionReadable;

/**
 * Created by Mark on 13/02/2016.
 */
public class MotionDetectionModel implements IMotionDetectionReadable {

    private long _timestamp;
    private String _cameraDataId;
    private ICameraReadable _cameraModel;

    public MotionDetectionModel(long timestamp, String cameraDataId){
        _timestamp = timestamp;
        this._cameraDataId = cameraDataId;
    }

    @Override
    public String toString(){
        return "Timestamp (Seconds): " + _timestamp;
    }

    @Override
    public long getTimestampSeconds() {
        return _timestamp;
    }

    @Override
    public String getCameraDataId() {
        return _cameraDataId;
    }


    @Override
    public void setCameraModel(ICameraReadable cameraModel) {
        _cameraModel = cameraModel;
    }

    @Override
    public ICameraReadable getCameraData() {
        return _cameraModel;
    }
}
