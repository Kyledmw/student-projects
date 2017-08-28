package com.hms.camera.models;

import com.hms.camera.models.interfaces.ICameraReadable;

/**
 * Created by Mark on 13/02/2016.
 */
public class CameraModel implements ICameraReadable {

    private String _id;
    private byte[] _image;
    private long _timestamp;


    public CameraModel(String id, byte[] image, long timestamp){
        _id = id;
        _timestamp = timestamp;
        _image = image;
    }

    @Override
    public String toString(){
        return "Timestamp (Seconds): " + _timestamp;

    }

    @Override
    public byte[] getImageBytes() {
        return _image;
    }

    @Override
    public long getTimestampSeconds() {
        return _timestamp;
    }

    @Override
    public String getId() {
        return _id;
    }
}
