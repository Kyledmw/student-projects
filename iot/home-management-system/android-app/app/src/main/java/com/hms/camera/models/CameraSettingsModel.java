package com.hms.camera.models;

import com.hms.camera.models.interfaces.ICameraSettingsReadable;
import com.hms.camera.models.interfaces.ICameraSettingsWriteable;

/**
 * Created by Mark on 13/02/2016.
 */
public class CameraSettingsModel implements ICameraSettingsReadable, ICameraSettingsWriteable {

    private String _id;
    private boolean _powerSwitch;
    private boolean _recording;

    public CameraSettingsModel(String id, boolean powerSwitch, boolean recording){
        _id = id;
        _powerSwitch = powerSwitch;
        _recording = recording;
    }

    @Override
    public String getId() {
        return _id;
    }

    @Override
    public boolean getPowerSwitch() {
        return _powerSwitch;
    }

    @Override
    public boolean getRecording() {
        return _recording;
    }

    @Override
    public String toString(){
        return "ID: " + _id + " Power Switch: " + _powerSwitch + " Recording: " + _recording;

    }

    @Override
    public void flipPowerSwitch() {
        _powerSwitch = !_powerSwitch;
    }

    @Override
    public void flipRecording() {
        _recording = !_recording;
    }

    @Override
    public void setPowerSwitch(Boolean switchState) {
        this._powerSwitch = switchState;
    }

    @Override
    public void setRecordingSwitch(Boolean switchState) {
        this._recording = switchState;
    }
}

