package com.hms.camera.models.interfaces;

/**
 * Created by Kyle on 13/02/2016.
 */
public interface ICameraSettingsWriteable {

    void flipPowerSwitch();

    void flipRecording();

    void setPowerSwitch(Boolean switchState);

    void setRecordingSwitch(Boolean switchState);
}
