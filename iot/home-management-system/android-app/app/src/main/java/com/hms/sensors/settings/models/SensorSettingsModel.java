package com.hms.sensors.settings.models;

import com.hms.sensors.settings.models.interfaces.ISensorSettingsReadable;
import com.hms.sensors.settings.models.interfaces.ISensorSettingsWriteable;

/**
 * Created by Mark on 13/02/2016.
 */
public class SensorSettingsModel implements ISensorSettingsReadable, ISensorSettingsWriteable {

    private String _id;
    private boolean _powerSwitch;

    public SensorSettingsModel(boolean powerSwitch){
        _powerSwitch = powerSwitch;
    }

    public String toString(){
        return "Power Switch: " + _powerSwitch;

    }

    @Override
    public boolean getPowerSwitch() {
        return _powerSwitch;
    }

    @Override
    public String getId() {
        return _id;
    }

    @Override
    public void flipPowerSwitch() {
        _powerSwitch = !_powerSwitch;
    }

    @Override
    public void setId(String id) {
        _id = id;
    }

    @Override
    public void setPowerSwitch(boolean switchState) {
        _powerSwitch = switchState;
    }
}

