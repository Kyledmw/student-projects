package com.hms.camera.models.interfaces;

/**
 * Created by Kyle on 13/02/2016.
 */
public interface ICameraReadable {

    byte[] getImageBytes();

    long getTimestampSeconds();

    String getId();
}
